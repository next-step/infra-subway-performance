import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    vus: 1, // 1 user looping for 1 minute
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<500'], // 99% of requests must complete below 1.5s
//    'logged in successfully': ['p(99)<1500'],
    },
};

export default function ()  {

    const BASE_URL = 'https://dibtp1221.kro.kr/';

    http.get(`${BASE_URL}/stations`);
    http.get(`${BASE_URL}/lines`);

    let pathObject = http.get(`${BASE_URL}/paths?source=24&target=12`).json();
    console.log(pathObject.distance);
    check(pathObject, {'correct path1': (obj) => obj.distance === 25 });

    let pathObject2 = http.get(`${BASE_URL}/paths?source=3&target=5`).json();
    console.log(pathObject2.distance);
    check(pathObject2, {'correct path2': (obj) => obj.distance === 2 });

    let lines1 = http.get(`${BASE_URL}/lines/1`).json();
    console.log(lines1.stations.length);
    check(lines1, {'correct lines1': (obj) => obj.stations.length === 62});

    sleep(1);
};
