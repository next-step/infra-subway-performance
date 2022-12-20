import http from 'k6/http';
import { targets } from './targets.js';
import { check, group, sleep, fail } from 'k6';

const BASE_URL = 'https://cwjonhpark-subway-px.o-r.kr';
const USERNAME = 'cwpark@mail.com';
const PASSWORD = '1234';

const getRandomTarget = (exclude) => {
  const length = targets.length;
  const rand = targets[Math.floor(Math.random() * length)];
  if (rand == exclude) {
    getRandomTarget(exclude);
  }
  return rand;
}
const checkFindPath = () => {
  
  let source = getRandomTarget(-1);
  let target = getRandomTarget(source);
    let url = `${BASE_URL}/paths?source=${source}&target=${target}`;
  let findpath = http.get(url);
    if (findpath.status !== 200) {
      return;
    }
    check(findpath, { 'find path': (r) => r.status === 200 });
    sleep(1);
}
export default function ()  {

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


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });
  sleep(1);
  
  checkFindPath();
};


