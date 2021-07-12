import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  vus: 1, // 1 user looping for 1 minute
  duration: '5s',
  thresholds: {
    http_req_failed: ['rate<0.01'],
    http_req_duration: ['p(99)<50'], // 99% of requests must complete below 50ms
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