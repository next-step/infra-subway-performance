import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        {duration: '30s', target: 100},
        {duration: '1m', target: 100},
        {duration: '30s', target: 300},
        {duration: '1m', target: 300},
        {duration: '30s', target: 500},
        {duration: '1m', target: 500},
        {duration: '30s', target: 1000},
        {duration: '1m', target: 1000},
        {duration: '30s', target: 1500},
        {duration: '2m', target: 1500},
        {duration: '30s', target: 500},
        {duration: '30s', target: 0}
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://yulmucha.r-e.kr/';
// const BASE_URL = 'http://3.39.6.158:8080';
const USERNAME = 'yul@mucha.com';
const PASSWORD = 'qwer';

export default function () {
    toMain();
    toLogin();
    const accessToken = login();
    toPath();
    findPath();
};

function toMain() {
    let res = http.get(`${BASE_URL}`);
    check(res, {
        'Main Page status is 200': (r) => r.status === 200
    });
}

function toLogin() {
    let res = http.get(`${BASE_URL}/login`);
    check(res, {
        'Login Page status is 200': (r) => r.status === 200
    });
}

function login() {
    let payload = JSON.stringify({
        email: USERNAME,
        password: PASSWORD,
    });
    let params = {
        headers: {
            "Content-Type": "application/json",
        },
    };
    let res = http.post(`${BASE_URL}/login/token`, payload, params);
    check(res, {
        "login status is 200": (resp) => resp.json("accessToken") !== "",
    });

    return res.json("accessToken");
}

function toPath(accessToken) {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        },
    };
    let res = http.get(`${BASE_URL}/path`, authHeaders);
    check(res, {
        'Path Page status is 200': (r) => r.status === 200
    });
}

function findPath() {
    let res = http.get(`${BASE_URL}/paths?source=1&target=2`);
    check(res, {
        'Path Finding status is 200': (r) => r.status === 200,
    });
}