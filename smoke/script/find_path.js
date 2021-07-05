import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  vus: 100, // 1 user looping for 1 minute
  duration: '10s',

  thresholds: {
    http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
  },
};

const BASE_URL = 'https://my-subway.r-e.kr';

export default function ()  {

  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let path = http.get(`${BASE_URL}/paths?source=1&target=21`, params);

  check(path, {
    'logged in successfully': (path) => path.json('stations') !== '',
  });

  sleep(1);
};
