import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    vus: 600, // 1 user looping for 1 minute
    duration: '1800s',

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://alb.seogineer.kro.kr';
const USERNAME = 'dgseo8981@gmail.com';
const PASSWORD = '1234';
const SOURCE = 3;
const TARGET = 24;

export default function ()  {
    // lending page
    let homeUrl = `${BASE_URL}`;
    let lendingPageResponse = http.get(homeUrl);
    check(lendingPageResponse, {
        'lending page running': (response) => response.status === 200
    });

    // login
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

    // myinfo
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${loginRes.json('accessToken')}`,
        },
    };
    let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
    check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });
    sleep(1);

    // create line
    let createLineUrl = `${BASE_URL}/lines`;
    let lineRandomNumber = Math.random().toString().split('.')[1];
    let createLinePayload = JSON.stringify({
        name: `testLine-${lineRandomNumber}`,
        color: "grey darken-4",
        upStationId: 1,
        downStationId: 2,
        distance: 10,
    });
    let createLineParams = {
        headers: {
            'Authorization': `Bearer ${loginRes.json('accessToken')}`,
            'Content-Type': 'application/json',
        },
    };
    let createLinesResponse = http.post(createLineUrl, createLinePayload, createLineParams);
    check(createLinesResponse, {
        'created Line successfully': (response) => response.status === 201,
    });

    // find path
    let response = http.get(`${BASE_URL}/paths?source=${SOURCE}&target=${TARGET}`);

    check(response, {
        'find path ok': (res) => res.status === 200
    });
};
