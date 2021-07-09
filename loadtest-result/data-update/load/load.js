import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  vus: 250, // 1 user looping for 1 minute
  duration: '100s',

  thresholds: {
    http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
  },
};

let i = 0;
const BASE_URL = 'https://infra-subway.kro.kr';
const USERNAME = 'qa@qa.com';
const PASSWORD = '1234';

export default function ()  {

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

  var updatePayload = JSON.stringify({
      email: USERNAME,
      password: PASSWORD,
      age: i++
    });

  let authHeaders = {
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };

  let updateRes = http.put(`${BASE_URL}/members/me`, updatePayload, authHeaders);
  check(updateRes, {
          'updated in successfully': (resp) => resp.status == 200,
      });
  sleep(1);
};