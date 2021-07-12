import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  stages: [
    { duration: '1m', target: 20 },
    { duration: '1m', target: 20 },
    { duration: '1m', target: 40 },
    { duration: '1m', target: 40 },
    { duration: '1m', target: 60 },
    { duration: '1m', target: 60 },
    { duration: '10s', target: 0 }
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'],
    http_reqs: ['rate<12.5'],
    http_req_duration: ['p(99)<100'], // 99% of requests must complete below 100ms
    'logged in successfully': ['p(99)<100'], // 99% of requests must complete below 100ms
  },
};

const BASE_URL = 'https://dohchoi91.kro.kr';

export function index() {
	return http.get(`${BASE_URL}`);
}

export function stations() {
	return http.get(`${BASE_URL}/stations`);
}
export function findPath(source, target) {
  return http.get(`${BASE_URL}/paths/?source=` + source + `&target=` + target).json();
};

export default function() {
    check(index(), { "Index Check": (r) => r.status == 200 });
    check(stations(), { "Stations Check": (r) => r.status == 200 });
    check(findPath(1, 2), { "Path check": obj => obj.stations.length != 0 });
    sleep(1);
}