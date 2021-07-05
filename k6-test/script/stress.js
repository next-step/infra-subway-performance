import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';
import { Trend } from 'k6/metrics';

export let options = {
    scenarios: {
        testWithVUs10: {
            executor: 'constant-vus', vus: 10, duration: '30s',
        },
        testWithVUs30: {
            executor: 'constant-vus', startTime: '30s', vus: 30, duration: '30s',
        },
        testWithVUs50: {
            executor: 'constant-vus', startTime: '60s', vus: 50, duration: '30s',
        },
        testWithVUs70: {
            executor: 'constant-vus', startTime: '90s', vus: 70, duration: '30s',
        },
        testWithVUs90: {
            executor: 'constant-vus', startTime: '120s', vus: 90, duration: '30s',
        },
        testWithVUs110: {
            executor: 'constant-vus', startTime: '150s', vus: 110, duration: '30s',
        },
        testWithVUs130: {
            executor: 'constant-vus', startTime: '180s', vus: 130, duration: '30s',
        },
        testWithVUs150: {
            executor: 'constant-vus', startTime: '210s', vus: 150, duration: '30s',
        },
    },

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

let loginTrend = new Trend('DURATION TIME - logged in successfully', true);
let myInfoTrend = new Trend('DURATION TIME - retrieved member', true);
let infoUpdateTrend = new Trend('DURATION TIME - information updated', true);
let findPathTrend = new Trend('DURATION TIME - retrieved path', true);
let lineTrend = new Trend('DURATION TIME - retrieved lines', true);
let stationTrend = new Trend('DURATION TIME - retrieved stations', true);

const BASE_URL = 'https://xn--vo5bi4h.xn--yq5b.xn--3e0b707e';
const USERNAME = 'abc@gmail.com';
const PASSWORD = '123456';

export default function ()  {

    const payload = JSON.stringify({
        email: USERNAME,
        password: PASSWORD,
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);
    loginTrend.add(loginRes.timings.duration);

    check(loginRes, {
        'logged in successfully': (resp) => resp.json('accessToken') !== '',
    });


    let authHeaders = {
        headers: {
            'Authorization': `Bearer ${loginRes.json('accessToken')}`,
        },
    };

    let myInfoRes = http.get(`${BASE_URL}/members/me`, authHeaders);
    myInfoTrend.add(myInfoRes.timings.duration);

    let myInfoObjects = myInfoRes.json();
    check(myInfoObjects, {
        'retrieved member': (obj) => obj.id !== 0,
    });


    const updatePayload = JSON.stringify({
        'age': '32',
        'email': USERNAME,
        'password': PASSWORD,
    });

    let updateHeaders = {
        headers: {
            'Authorization': `Bearer ${loginRes.json('accessToken')}`,
            'Content-Type': 'application/json',
        },
    };
    let updateRes = http.put(`${BASE_URL}/members/me`, updatePayload, updateHeaders);
    infoUpdateTrend.add(updateRes.timings.duration);
    check(updateRes, {
        'information updated': (res) => res.status === 200
    });


    let pathRes = http.get(`${BASE_URL}/paths/?source=7&target=29`);
    findPathTrend.add(pathRes.timings.duration);

    let pathObjects = pathRes.json();
    check(pathObjects, {
        'retrieved path': (obj) => obj.stations !== undefined
    });

    let lineRes = http.get(`${BASE_URL}/lines`);
    lineTrend.add(lineRes.timings.duration);

    check(lineRes, {
        'retrieved lines': (res) => res.status === 200
    });

    let stationRes = http.get(`${BASE_URL}/stations`);
    stationTrend.add(stationRes.timings.duration);

    check(stationRes, {
        'retrieved stations': (res) => res.status === 200
    });

    sleep(1);
}
