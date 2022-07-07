import http from 'k6/http';
import {check, sleep} from 'k6';

export let options = {
    vus: 1, // 1 user looping for 1 minute
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://weno-nextstep.p-e.kr';
const USERNAME = 'wenodev@nextstep.com';
const PASSWORD = '1234';

export default function ()  {
    // mainPage 접근
    accessMainPage();

    // login
    let accessToken = login(USERNAME, PASSWORD);


    let authHeaders = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    };


    // 경로 조회 페이지 접근
    accessFindPathPage();

    // 경로 검색
    searchPath(1,2);
    searchPath(1,3);
    searchPath(1,4);

    sleep(1);
};

function accessMainPage() {
    let mainResponse = http.get(`${BASE_URL}`);
    check(mainResponse, {
        'main page success': (response) => response.status == 200
    });
}

function login(username, password) {
    let loginUri = `${BASE_URL}/login/token`;
    let loginPayload = JSON.stringify({
        email: username,
        password: password,
    });

    let loginParams = {
        headers: {
            'Content-Type': 'application/json',
        },
    };


    let loginResponse = http.post(loginUri, loginPayload, loginParams);

    check(loginResponse, {
        'logged in successfully': (response) => response.json('accessToken') !== '',
    });

    return loginResponse.json('accessToken');
}

function accessFindPathPage() {
    let findPathResponse = http.get(`${BASE_URL}/path`);

    check(findPathResponse, {
        'find path page success': (response) => response.status == 200
    });
}

function searchPath(source, target) {
    let searchPathResponse = http.get(`${BASE_URL}/path?source=${source}&target=${target}`);

    check(searchPathResponse, {
        'search path success': (response) => response.status == 200
    });
}
