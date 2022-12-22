import http from 'k6/http';
import {check, sleep} from 'k6';

export let options = {
  stages: [
    {duration: '1m', target: 1},
    {duration: '1m', target: 2},
    {duration: '1m', target: 3},
    {duration: '3m', target: 4},
    {duration: '1m', target: 3},
    {duration: '1m', target: 2},
    {duration: '1m', target: 1}
  ],
  thresholds: {
    http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
  },
};

const BASE_URL = 'https://tech-pro.jimbae.com';
const USERNAME = 'jimbae@gmail.com';
const PASSWORD = 'qwer!@#$';

export default function () {

  var payload = JSON.stringify({
    email: USERNAME,
    password: PASSWORD,
  });

  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);

  check(loginRes, {
    'logged in successfully': (resp) => resp.json('accessToken') !== '',
  });

  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  check(myObjects, {'retrieved member': (obj) => obj.id != 0});

  let line = http.get(`${BASE_URL}/lines/1`, authHeaders);
  check(line, {'find path': (line) => line.status === 200});

  let main = http.get(`${BASE_URL}`);
  check(main, {'retrieved main': (main) => main.status === 200});

  sleep(1);
};
