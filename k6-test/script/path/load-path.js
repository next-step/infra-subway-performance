import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 22 },
        { duration: '1m', target: 22 },
        { duration: '1m', target: 100 },
        { duration: '1m', target: 100 },
        { duration: '1m', target: 209 },
        { duration: '1m', target: 209 },
        { duration: '1m', target: 100 },
        { duration: '1m', target: 100 },
        { duration: '1m', target: 22 },
        { duration: '1m', target: 22 },
        { duration: '1m', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<900'], // 99% of requests must complete below 1.5s
        'logged in successfully': ['p(99)<900'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://jerry92k-subway.n-e.kr/';

export default function ()  {

    var params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let source =24 // (동작역)
    let target =21 // (광교역)

    const result = http.get(`${BASE_URL}/paths?source=${source}&target=${target}`, params);

    check(result, {'is status 200(ok)': (r) => r.status === 200});
    sleep(1);
};
