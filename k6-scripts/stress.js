import { check, sleep } from 'k6'
import http from 'k6/http'

/**
 * 데이터를 조회하는데 여러 데이이터를 참조하는 페이지 - 경로 검색
 * 지하철 역 목록 조회 - 최단 거리 조회
 *
 * Throughput = 11.8 ~ 177
 * Latency = 100ms
 *
 * vus
 * 11.8 x (3x1.5 ) /3 = 18,
 * 177 x (3x1.5)/3 = 266,
 */

export let options = {
  stages: [
    { duration: '10s', target: 100 },
    { duration: '10s', target: 200 },
    { duration: '10s', target: 300 },
    { duration: '10s', target: 400 },
    { duration: '10s', target: 500 },
    { duration: '10s', target: 600 },
    { duration: '10s', target: 700 },
    { duration: '10s', target: 800 },
    { duration: '10s', target: 900 },
    { duration: '10s', target: 1000 },
    { duration: '10s', target: 1100 },
  ],
  thresholds: {
    http_req_duration: ['p(99)<100'], // 99% of requests must complete below 100ms
  },
}

const BASE_URL = 'https://shinmj-nextstep.n-e.kr'

export default function () {
  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  }

  let stationsRes = http.get(`${BASE_URL}/stations`, params)
  check(stationsRes, { 'retrieved stations': (resp) => resp.status === 200 })
  sleep(1)

  let paths = http.get(`${BASE_URL}/paths?source=1&target=78`, params)
  check(paths, { 'retrieved paths': (resp) => resp.status === 200 })
  sleep(1)
}
