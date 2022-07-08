import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages : [
        { duration: '5m', target: 20},
        { duration: '5m', target: 120},
        { duration: '5m', target: 20},
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'],
    },
};

const BASE_URL = 'https://kmmin78-infra-subway.p-e.kr';
const USERNAME = 'probitanima98855@gmail.com';
const PASSWORD = '11';

const login = () => {
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

    return loginRes.json('accessToken');
}

const myInfo = (accessToken) => {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    };
    let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
    check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });
}

function getLines(accessToken) {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    };
    let response = http.get(`${BASE_URL}/lines`, authHeaders);
    check(response, {'retrieved lines': (response) => response.status === 200 });
}

function getStations(accessToken) {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    };
    let response = http.get(`${BASE_URL}/stations`, authHeaders);
    check(response, {'retrieved stations': (response) => response.status === 200 });
}

function getFavorites(accessToken) {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    };
    let response = http.get(`${BASE_URL}/favorites`, authHeaders);
    check(response, {'retrieved favorites': (response) => response.status === 200 });
}

function getPaths(source, target) {
    let response = http.get(`${BASE_URL}/path?source=${source}&target=${target}`);

    check(response, {'retrieved paths': (response) => response.status == 200});
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
}

export default function ()  {
    let accessToken = login();
    myInfo(accessToken);
    getLines(accessToken);
    getStations(accessToken);
    getFavorites(accessToken);
    getPaths(getRandomInt(1, 10), getRandomInt(11, 20));
    sleep(1);
};
