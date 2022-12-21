import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  stages: [
    { duration: '10s', target: 40 }, // ramping up
    { duration: '10s', target: 80 }, // ramping up
    { duration: '10s', target: 160 }, // ramping up
    { duration: '2m', target: 160 },
    { duration: '10s', target: 320 }, // ramping up
    { duration: '3m', target: 320 },
    { duration: '10s', target: 640 }, // ramping up
    { duration: '4m', target: 640 },
    { duration: '10s', target: 320 }, // ramping down
    { duration: '3m', target: 320 },
    { duration: '10s', target: 160 }, // ramping down
    { duration: '2m', target: 160 },
    { duration: '10s', target: 80 }, // ramping down
    { duration: '10s', target: 40 }, // ramping down
    { duration: '10s', target: 0 }, // ramping down
  ],
  thresholds: {
    http_req_duration: ['p(99)<500'], // 99% of requests must complete below 0.5s
  },
};

const BASE_URL = 'https://shshon-infra.o-r.kr';
const USERNAME = 'test@test.com';
const PASSWORD = 'test';

export default function ()  {
  // 메인 페이지 -> 로그인 페이지 -> 로그인 -> 경로 탐색 페이지 -> 경로 탐색 요청
  loadMainPage();
  loadLoginPage();
  let token = login();
  completeLogin(token);
  loadPathPage();
  findPath();
  sleep(1);
};

function loadMainPage() {
  const response = http.get(BASE_URL);
  check(response, {
    'loaded main page in successfully': (res) => res.status === 200,
  });
}

function loadLoginPage() {
  let loginPageResponse = http.get(`${BASE_URL}/login`);
  check(loginPageResponse, {
    'loaded login page in successfully': (response) => response.status === 200,
  });
}

function login() {
  const payload = JSON.stringify({
    email: USERNAME,
    password: PASSWORD,
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let loginResponse = http.post(`${BASE_URL}/login/token`, payload, params);
  check(loginResponse, {
    'Logged in successfully': (response) => response.json('accessToken') !== '',
  });

  return loginResponse.json('accessToken');
}

function completeLogin(accessToken) {
  let authHeaders = {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  };

  let completeLoginResponse = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  check(completeLoginResponse, {
    'Complete logged in successfully': (response) => response.id !== 0,
  });
}

function loadPathPage() {
  const response = http.get(`${BASE_URL}/path`);
  check(response, {
    'loaded path page in successfully': (res) => res.status === 200,
  });
}

function findPath() {
  let source = Math.floor(Math.random() * 15 + 1);
  let target = Math.floor(Math.random() * 15 + 1);
  const response = http.get(`${BASE_URL}/paths?source=${source}&target=${target}`);
  check(response, {
    'get path info in successfully': (res) => res.status === 200,
  });
}
