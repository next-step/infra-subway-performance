import http from 'k6/http';
import {check, sleep} from 'k6';
import {BASE_URL} from '../config/TestInfo.js';

export let options = {
  stages: [
    { duration: '10s', target: 1 },
    { duration: '10s', target: 180 },
    { duration: '20s', target: 240 },
    { duration: '10s', target: 180 },
    { duration: '10s', target: 0 }
  ],

  thresholds: {
    http_req_duration: ['p(95)<50'],
  },
};

export default function ()  {
  let 메인페이지_결과 = 메인페이지_요청();
  메인페이지_결과_확인(메인페이지_결과);

  let 지하철역관리_조회요청_결과 = 지하철역관리_조회요청()
  지하철역관리_조회요청_결과_확인(지하철역관리_조회요청_결과);

  sleep(1);
};

export function 메인페이지_요청() {
  return http.get(`${BASE_URL}`);
}

export function 메인페이지_결과_확인(메인페이지_결과) {
  check(메인페이지_결과, {
    '메인페이지가 정상적으로 응답함': (response) => response.status === 200
  });
}

export function 지하철역관리_조회요청(출발역, 도착역) {

  return http.get(`${BASE_URL}/stations`);
}

export function 지하철역관리_조회요청_결과_확인(지하철역관리_조회요청_결과) {
  check(지하철역관리_조회요청_결과, {
    '지하철역이 정상적으로 조회됨': (response) => response.json().length > 0
  });
}