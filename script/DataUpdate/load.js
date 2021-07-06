import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {

    vus: 300, // 1 user looping for 1 minute
    duration: '100s',

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

let i = 0;
const BASE_URL = 'http://13.124.137.208:8080';
const USERNAME = 'zxc@naver.com' + i++;
const PASSWORD = 'zxc' + i++;

export default function ()  {

    var payload = JSON.stringify({
        email: USERNAME,
        password: PASSWORD,
        age: 30,
    });

    var params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let loginRes = http.post(`${BASE_URL}/members`, payload, params);

    check(loginRes, {
        'created in successfully': (resp) => resp.status == 201,
    });
    sleep(1);
};
