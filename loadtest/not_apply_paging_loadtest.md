# Paging 적용 후

## Load Test

```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '5s', target: 200 },
        { duration: '10s', target: 500 },
        { duration: '20s', target: 500 },
        { duration: '10s', target: 200 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
        'logged in successfully': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://15.164.72.205:8080/';

export default function ()  {

    http.get(`${BASE_URL}/stations`);

    sleep(1);
};

```

![image](https://user-images.githubusercontent.com/10750614/147835049-2c4ddac6-e5b2-4f8c-9ef7-1cec15824ccd.png)



## Smoke Test

```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    vus: 1, // 1 user looping for 1 minute
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://15.164.72.205:8080/';

export default function ()  {

    http.get(`${BASE_URL}/stations`);

    sleep(1);
};
```

![image](https://user-images.githubusercontent.com/10750614/147835083-2b31637a-a18c-457c-a546-13d5d7f4e32d.png)



## Stress Test

```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '5s', target: 100 },
        { duration: '10s', target: 300 },
        { duration: '10s', target: 600 },
        { duration: '20s', target: 600 },
        { duration: '10s', target: 600 },
        { duration: '20s', target: 1200 },
        { duration: '10s', target: 600 },
        { duration: '10s', target: 300 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
        'logged in successfully': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://15.164.72.205:8080/';

export default function ()  {

    http.get(`${BASE_URL}/stations`);

    sleep(1);
};
```

![image](https://user-images.githubusercontent.com/10750614/147835319-2dd31d7f-dc6e-4fce-a4c8-0c2b112ccd42.png)


```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '5s', target: 100 },
        { duration: '10s', target: 270 },
        { duration: '10s', target: 540 },
        { duration: '20s', target: 540 },
        { duration: '10s', target: 540 },
        { duration: '20s', target: 1080 },
        { duration: '10s', target: 540 },
        { duration: '10s', target: 270 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
        'logged in successfully': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://15.164.72.205:8080/';

export default function ()  {

    http.get(`${BASE_URL}/stations`);

    sleep(1);
};
```

![image](https://user-images.githubusercontent.com/10750614/147835209-f047fd02-0ab1-4df2-9a2e-ee9f775fe559.png)


```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '5s', target: 100 },
        { duration: '10s', target: 250 },
        { duration: '10s', target: 500 },
        { duration: '20s', target: 500 },
        { duration: '10s', target: 500 },
        { duration: '20s', target: 1000 },
        { duration: '10s', target: 500 },
        { duration: '10s', target: 250 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
        'logged in successfully': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://15.164.72.205:8080/';

export default function ()  {

    http.get(`${BASE_URL}/stations`);

    sleep(1);
};

```

![image](https://user-images.githubusercontent.com/10750614/147835136-2eae9fdf-47c3-4b9e-868f-dd91e266d997.png)
