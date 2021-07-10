import http from 'k6/http';
import {check, sleep} from 'k6';

export let options = {
    stages: [
        { duration: '2s', target: 100 },
        { duration: '5s', target: 100 },
        { duration: '2s', target: 200 },
        { duration: '5s', target: 200 },
        { duration: '2s', target: 300 },
        { duration: '5s', target: 300 },
        { duration: '2s', target: 400 },
        { duration: '5s', target: 400 },
        { duration: '2s', target: 500 },
        { duration: '5s', target: 500 },
        { duration: '2s', target: 600 },
        { duration: '5s', target: 600 },
        { duration: '2s', target: 700 },
        { duration: '5s', target: 700 },
        { duration: '2s', target: 800 },
        { duration: '5s', target: 800 },
        { duration: '2s', target: 900 },
        { duration: '5s', target: 900 },
        { duration: '2s', target: 1000 },
        { duration: '5s', target: 1000 },
        { duration: '2s', target: 1100 },
        { duration: '5s', target: 1100 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://infra-subway.kro.kr';

export default function () {

    var params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let pathRes = http.get(`${BASE_URL}/paths?source=1&target=16`, params);

    check(pathRes, {
        'find path in successfully': (resp) => resp.status == 200
    });
    sleep(1);
};