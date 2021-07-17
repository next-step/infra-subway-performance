import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '10s', target: 240 },
        { duration: '20s', target: 240 },
        { duration: '5s', target: 0 }
    ],
    thresholds: {
        http_req_duration: ['p(99)< 50'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://www.jeongminkyo.kro.kr';
const USERNAME = 'test@email.com';
const PASSWORD = 'test';

export default function ()  {

    var payload = JSON.stringify({
        email: USERNAME,
        password: PASSWORD,
    });

    var params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };


    let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);

    check(loginRes, {
        'logged in successfully': (resp) => resp.json('accessToken') !== '',
    });


    let authHeaders = {
        headers: {
            Authorization: `Bearer ${loginRes.json('accessToken')}`,
        },
    };
    let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
    check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });
    경로조회(loginRes);
    sleep(1);
};

export function 경로조회(loginRes){
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${loginRes.json('accessToken')}`,
        },
    };
    return http.get(`${BASE_URL}/paths/?source=1&target=2`, authHeaders).json();
};
