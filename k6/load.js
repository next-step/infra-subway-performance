import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  vus: 300, 
  duration: '30s',
  thresholds: {
    http_req_duration: ['p(90) < 50', 'p(95) < 100', 'p(99.9) < 200'],
	checks: ['rate > 0.99'],
  },
};

const BASE_URL = 'https://performance.honbabzone.com';
const USERNAME = 'user@test.com';
const PASSWORD = '1234';

export default function ()  {
  let loginResponse = 로그인();
  

  즐겨찾기_조회하기(loginResponse);

  경로_조회하기(loginResponse)

  sleep(1);
};

export function 로그인() {
  const payload = JSON.stringify({
    email: USERNAME,
    password: PASSWORD,
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const response = http.post(`${BASE_URL}/login/token`, payload, params);
  
   check(response, {
    'logged in successfully': (response) => response.json('accessToken') !== '',
  });

  return response;
}

export function 즐겨찾기_조회하기(loginResponse) {
  const authHeaders = {
    headers: {
      Authorization: `Bearer ${loginResponse.json('accessToken')}`,
    },
  };

  const favorites = http.get(`${BASE_URL}/favorites`, authHeaders).json();

  check(favorites, { 'retrieved favorites': (obj) => obj.length > 0 });
}


export function 경로_조회하기(loginRes) {
  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };

  const favorites = http.get(`${BASE_URL}/paths/?source=1&target=2`, authHeaders).json();
  check(favorites, { 'retrieved favorites': (obj) => obj.distance === 22 });
}
