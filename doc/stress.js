import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  stages: [
    { duration: '15s', target: 20 },
    { duration: '30s', target: 20 },
    { duration: '15s', target: 0 },
    { duration: '30s', target: 270 },
    { duration: '60s', target: 270 },
    { duration: '30s', target: 0 },
    { duration: '15s', target: 20 },
    { duration: '30s', target: 20 },
    { duration: '15s', target: 0 }
  ],

  thresholds: {
    http_req_duration: ['p(99)<1500'],
  }
};

const BASE_URL = 'https://reversalspring.n-e.kr/';
const USERNAME = 'p_illusion@naver.com';
const PASSWORD = 'password';

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


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });

  경로조회(loginRes);

  sleep(1);
};

export function 경로조회(loginRes){

  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  return http.get(`${BASE_URL}/paths/?source=1&target=2`, authHeaders).json();
};