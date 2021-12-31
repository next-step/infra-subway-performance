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

![image](https://user-images.githubusercontent.com/10750614/147834433-5ac1a679-9ce1-4839-8bc9-26ea9530cba5.png)




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


![image](https://user-images.githubusercontent.com/10750614/147834485-62a378eb-0be6-4742-a18a-75f0ed1057fa.png)



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

![image](https://user-images.githubusercontent.com/10750614/147834611-8c32e66b-21ab-4c8e-9a0a-94fb18c214a3.png)



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

![image](https://user-images.githubusercontent.com/10750614/147834707-eece4ffe-1082-4b89-895a-17eece2127a6.png)



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

![image](https://user-images.githubusercontent.com/10750614/147834788-be5ee8c9-fda9-49c2-acdb-09a4019a4a81.png)


