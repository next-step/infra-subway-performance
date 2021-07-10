import http from 'k6/http';
import {check, sleep} from 'k6';

export let options = {
    vus: 250, // 1 user looping for 1 minute
    duration: '100s',

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

    let pathRes = http.get(`${BASE_URL}/paths?source=1&target=2`, params);

    check(pathRes, {
        'find path in successfully': (resp) => resp.status == 200
    });
    sleep(1);
};