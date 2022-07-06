import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '20s', target: 30 },
        { duration: '30s', target: 50 },
        { duration: '60s', target: 75 },
        { duration: '30s', target: 50 },
        { duration: '20s', target: 30 }
    ],

  thresholds: {
    http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
  },
};

const BASE_URL = 'https://lakey001.kro.kr';
const USERNAME = 't2@test.com';
const PASSWORD = 'test';


export default function () {
  //메인 페이지
  mainPage()

  //로그인
  let token = login();

  //경로찾기
  searchPath(1, 10);

  sleep(1);
};

function mainPage(){
  let mainRes = http.get(`${BASE_URL}`);
  check(mainRes, {
    'go mainPage successfully': (resp) => resp.status == 200
  });
}

function login(){
  var payload = JSON.stringify({
    email: `${USERNAME}`,
    password: `${PASSWORD}`
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

  return loginRes.json('accessToken');
}

function searchPath(source, target) {
    let response = http.get(`${BASE_URL}/paths?source=${source}&target=${target}`);
    check(response, {'searchPath succeessfully': (res) => res.status === 200})
}

