import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 200 },
        { duration: '1m', target: 200 },
        { duration: '30s', target: 250 },
        { duration: '30s', target: 200 },
        { duration: '30s', target: 200 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<100'], // 99% of requests must complete below 100ms
    },
};

const BASE_URL = 'https://subway.geunhwanlee.p-e.kr';
const USERNAME = 'gunan@gmail.com';
const PASSWORD = '1234';
const SOURCE = 3;
const TARGET = 24;

export default function ()  {
    // 로그인 - 경로찾기 - 즐겨찾기 - 즐겨찾기 해제
    let accessToken = login();
    findPath(SOURCE, TARGET);
    let location = favorite(accessToken, SOURCE, TARGET);
    deleteFavorite(accessToken, location);
    sleep(1);
};

function login() {
    let payload = JSON.stringify({
        email: USERNAME,
        password: PASSWORD,
    });
    let params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };
    let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);
    check(loginRes, {
        'logged in successfully': (res) => res.json('accessToken') !== '',
    });
    return loginRes.json('accessToken');
}

function findPath(source, target) {
    let response = http.get(`${BASE_URL}/paths?source=${source}&target=${target}`);

    check(response, {
        'find path ok': (res) => res.status === 200
    });
}

function favorite(accessToken, source, target) {
    let payload = JSON.stringify({
        source: source,
        target: target,
    });
    let params = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
            'Content-Type': 'application/json',
        },
    };
    let response = http.post(`${BASE_URL}/favorites`, payload, params);
    check(response, {
        'favorite created': (res) => res.status === 201
    });
    return response.headers['Location'];
}

function deleteFavorite(accessToken, location) {
    let params = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
            'Content-Type': 'application/json',
        },
    };
    let response = http.del(`${BASE_URL+location}`, null, params);
    check(response, {
        'favorite deleted': (res) => res.status === 204
    });
}
