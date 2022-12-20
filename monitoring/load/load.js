import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  // 평균 VUser : 10
  // 최대 VUser : 20
  stages: [
    { duration: '10s', target: 5 }, // ramping up
    { duration: '1m', target: 5 },
    { duration: '10s', target: 7 }, // ramping up
    { duration: '2m', target: 7 },
    { duration: '10s', target: 10 }, // ramping up
    { duration: '4m', target: 10 },
    { duration: '10s', target: 7 }, // ramping down
    { duration: '2m', target: 7 },
    { duration: '10s', target: 5 }, // ramping down
    { duration: '1m', target: 5 },
    { duration: '10s', target: 0 }
  ],
  thresholds: {
    http_req_duration: ['p(99)<500'], // 99% of requests must complete below 0.5s
  },
};

const BASE_URL = 'https://shshon-infra.o-r.kr';

export default function ()  {
  // 메인 페이지 -> 경로 탐색 페이지 -> 경로 탐색 요청
  loadMainPage();
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

function loadPathPage() {
  const response = http.get(`${BASE_URL}/path`);
  check(response, {
    'loaded path page in successfully': (res) => res.status === 200,
  });
}

function findPath() {
  let source = Math.floor(Math.random() * 10 + 1);
  let target = Math.floor(Math.random() * 10 + 1);
  const response = http.get(`${BASE_URL}/paths?source=${source}&target=${target}`);
  check(response, {
    'get path info in successfully': (res) => res.status === 200,
  });
}
