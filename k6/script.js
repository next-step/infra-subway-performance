import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

const BASE_URL = 'https://cwjonhpark-subway-px.o-r.kr/';
const USERNAME = 'cwpark@mail.com';
const PASSWORD = '1234';

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
  
  let source = Math.floor(Math.random() * 1000) % 439;
  let target = Math.floor(Math.random() * 1000) % 439;
  let url = `${BASE_URL}/paths?source=${source}&target=${target}`;	
  let findpath = http.get(url, authHeaders);
  check(findpath, { 'find path': (r) => r.status === 200});
};
