import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '3m', target: 82 },
        { duration: '3m', target: 82 },
        { duration: '3m', target: 164 },
        { duration: '3m', target: 164 },
        { duration: '3m', target: 246 },
        { duration: '3m', target: 246 },
        { duration: '3m', target: 328 },
        { duration: '3m', target: 328 },
        { duration: '4m', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<100']
    },
};

const BASE_URL = 'https://heowc.kro.kr';
const USERNAME = 'heowc1992@gmail.com';
const PASSWORD = 'password';

export default function ()  {
    // 메인 페이지 접속
    staticPage('', 'access main page');

    // 로그인 페이지 접속
    staticPage('login', 'access login page');

    // 로그인 요청
    var accessToken = login(USERNAME, PASSWORD);
    // 경로 조회 페이지 접속
    staticPage('path', 'access paths page');

    // 지하철역 목록 조회
    findStations();

    // 랜덤한 출발지/목적지 선택(2호선)
    let stationIds = [ 128, 127, 143, 144, 126, 125, 124, 123, 122, 96,
        138, 137, 136, 135, 134, 133, 132, 119, 118, 7,
        145, 116, 115, 114, 113, 111, 110, 109, 108, 142,
        107, 106, 105, 104, 103, 102, 101, 100, 99, 98,
        131, 130, 129 ]
    let stationId = peekStationId(stationIds);
    stationIds.pop(stationId);
    let nextStationId = peekStationId(stationIds);

    // 경로 검색 조회
    findPath(stationId, nextStationId);

    // 즐겨찾기 목록 조회
    findFavorites(accessToken);

    sleep(1);
};

function staticPage(path, desc) {
    var page = http.get(`${BASE_URL}/${path}`);
    var obj = {};
    obj[desc] = (resp) => resp.status === 200;
    check(page, obj);
}

function login(email, password) {
    var payload = JSON.stringify({
        email: email,
        password: password,
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

function findStations() {
    let loginRes = http.get(`${BASE_URL}/stations`);

    check(loginRes, {
        'retrieved stations': (resp) => resp.status === 200,
    });
}

function peekStationId(stationIds) {
    return stationIds[ Math.floor(Math.random() * stationIds.length) ];
}

function findPath(stationId, nextStationId) {
    let loginRes = http.get(`${BASE_URL}/paths?source=${stationId}&target=${nextStationId}`);

    check(loginRes, {
        'retrieved path': (resp) => resp.status === 200,
    });
}

function findFavorites(accessToken) {
    let authHeaders = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    };
    let loginRes = http.get(`${BASE_URL}/favorites`, authHeaders);

    check(loginRes, {
        'retrieved favorites': (resp) => resp.status === 200,
    });
}
