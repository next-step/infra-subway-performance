import http from "k6/http";
import {check, group, sleep, fail} from "k6";

export let options = {
    stages: [
        {duration: "30s", target: 120}, // simulate ramp-up of traffic from 1 to 100 users over 5 minutes.
        {duration: "1m", target: 120}, // stay at 100 users for 10 minutes
        {duration: "10s", target: 0}, // ramp-down to 0 users
    ],
    thresholds: {
        http_req_duration: ["p(99)<1500"], // 99% of requests must complete below 1.0s
    },
};

const BASE_URL = 'https://www.nextstep-hun.kro.kr';
const USERNAME = 'ini8262@naver.com';
const PASSWORD = 'qwas1234';

export default function () {
    let loginRes = post_login();

    get_lines(loginRes, 1);

    get_path(loginRes, 1, 5);

    sleep(1);
}

export function post_login() {
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

    return loginRes;
}

export function get_lines(loginRes, lineNumber) {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${loginRes.json('accessToken')}`,
        },
    };

    let lineRes = http.get(`${BASE_URL}/lines/` + lineNumber, authHeaders).json();

    check(lineRes, {
        'lines success!!': (resp) => resp['id'] == lineNumber,
    });

    return lineRes
}

export function get_path(loginRes, source, target) {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${loginRes.json('accessToken')}`,
        },
    };

    return http.get(`${BASE_URL}/paths/?source=` + source + `&target=`+target, authHeaders).json();
}

