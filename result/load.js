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

function getRandomStationId(stationSize, stations) {
  return stations[Math.floor(Math.random() * (stationSize - 0) + 0)].id;
}

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

  let moveTomain = http.get(`${BASE_URL}`);
  check(moveTomain, {'메인페이지로이동': (main) => main.status === 200});

  let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);
  check(loginRes, {
    '로그인 진행': (resp) => resp.json('accessToken') !== '',
  });
  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };

  let moveToPath = http.get(`${BASE_URL}/path`);
  check(moveToPath, {'경로 검색 페이지로 이동': (path) => path.status === 200});

  let stations = http.get(`${BASE_URL}/stations`).json();
  let stationSize = stations.length;

  let findPath = http.get(`${BASE_URL}/paths/?source=${getRandomStationId(stationSize, stations)}&target=${getRandomStationId(stationSize, stations)}`);
  check(findPath, {'경로 랜덤 조회': (path) => path.status === 200 || path.status === 500});

  sleep(1);
};