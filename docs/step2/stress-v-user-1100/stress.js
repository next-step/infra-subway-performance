import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  stages: [
    { duration: '10s', target: 183 },
    { duration: '30s', target: 363 },
    { duration: '1m', target: 450 },
    { duration: '1m', target: 620 },
    { duration: '2m', target: 780 },
    { duration: '3m', target: 1100 },
    { duration: '2m', target: 915 },
    { duration: '1m', target: 450 },
    { duration: '1m', target:  0 }
  ],
  thresholds: {
    http_req_duration: ['p(99)<500'], // 99% of requests must complete below 1.5s
  },
};

const BASE_URL = 'https://woo.subway-limwoobin.p-e.kr';
const USERNAME = 'testWb@naver.com';
const PASSWORD = '1234';

export default function ()  {
  // mainPage 접근
  accessMainPage();

  // login
  let accessToken = login(USERNAME, PASSWORD);


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  };


  // 경로 조회 페이지 접근
  accessFindPathPage();

  // 경로 검색
  searchPath();

  sleep(1);
};

function accessMainPage() {
  let mainResponse = http.get(`${BASE_URL}`);
  check(mainResponse, {
    'main page success': (response) => response.status == 200
  });
}

function login(username, password) {
  let loginUri = `${BASE_URL}/login/token`;
  let loginPayload = JSON.stringify({
    email: username,
    password: password,
  });

  let loginParams = {
    headers: {
      'Content-Type': 'application/json',
    },
  };


  let loginResponse = http.post(loginUri, loginPayload, loginParams);

  check(loginResponse, {
    'logged in successfully': (response) => response.json('accessToken') !== '',
  });

  return loginResponse.json('accessToken');
}

function accessFindPathPage() {
  let findPathResponse = http.get(`${BASE_URL}/path`);

  check(findPathResponse, {
    'find path page success': (response) => response.status == 200
  });
}

function searchPath() {
  let searchPathResponse = http.get(`${BASE_URL}/paths?source=1&target=5`);

  check(searchPathResponse, {
    'search path success': (response) => response.status == 200
  });
}
