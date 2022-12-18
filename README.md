<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 인프라공방 샘플 서비스 - 지하철 노선도

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew clean build
```
<br>

## 서비스 주소
- https://cwjonhpark-subway-px.o-r.kr/

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.


### 1단계 - 화면 응답 개선하기

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

### Smoke 테스트 결과
```markdown
     ✓ logged in successfully
     ✓ retrieved member

     checks.....................: 100.00% ✓ 596      ✗ 0
     data_received..............: 220 kB  733 B/s
     data_sent..................: 119 kB  397 B/s
     http_req_blocked...........: avg=55.2µs  min=1.65µs  med=4.16µs  max=30.67ms  p(90)=5.13µs  p(95)=5.52µs
     http_req_connecting........: avg=501ns   min=0s      med=0s      max=298.72µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..........: avg=2.95ms  min=2.15ms  med=3.02ms  max=17.89ms  p(90)=3.5ms   p(95)=3.86ms
     http_req_failed............: 100.00% ✓ 596      ✗ 0
     http_req_receiving.........: avg=56.06µs min=26.79µs med=54µs    max=235.37µs p(90)=70.88µs p(95)=76.8µs
     http_req_sending...........: avg=20.4µs  min=7.88µs  med=21.22µs max=169.74µs p(90)=30.42µs p(95)=33.2µs
     http_req_tls_handshaking...: avg=29.83µs min=0s      med=0s      max=17.78ms  p(90)=0s      p(95)=0s
     http_req_waiting...........: avg=2.87ms  min=2.08ms  med=2.94ms  max=17.8ms   p(90)=3.41ms  p(95)=3.78ms
     http_reqs..................: 596     1.986125/s
     iteration_duration.........: avg=1s      min=1s      med=1s      max=1.03s    p(90)=1s      p(95)=1s
     iterations.................: 298     0.993063/s
     vus........................: 1       min=1      max=1
     vus_max....................: 1       min=1      max=1
```

### Load 테스트 결과
```markdown
    ✓ logged in successfully
    ✓ retrieved member
    ✓ find path

    checks.........................: 100.00% ✓ 10365     ✗ 0    
    data_received..................: 11 MB   18 kB/s
    data_sent......................: 2.4 MB  4.0 kB/s
    http_req_blocked...............: avg=12.72µs min=1.04µs  med=4.26µs  max=16.58ms  p(90)=5.28µs  p(95)=5.72µs 
    http_req_connecting............: avg=693ns   min=0s      med=0s      max=481.18µs p(90)=0s      p(95)=0s     
    ✓ http_req_duration..............: avg=11.46ms min=2.66ms  med=4.18ms  max=363.64ms p(90)=27.19ms p(95)=32.91ms
    { expected_response:true }...: avg=11.72ms min=2.71ms  med=4.22ms  max=363.64ms p(90)=27.42ms p(95)=33.27ms
    http_req_failed................: 3.22%   ✓ 345       ✗ 10365
    http_req_receiving.............: avg=63.21µs min=15.46µs med=59.74µs max=4.51ms   p(90)=80.71µs p(95)=89.82µs
    http_req_sending...............: avg=21.52µs min=5.47µs  med=19.55µs max=3.94ms   p(90)=29.87µs p(95)=34.18µs
    http_req_tls_handshaking.......: avg=6.14µs  min=0s      med=0s      max=12.83ms  p(90)=0s      p(95)=0s     
    http_req_waiting...............: avg=11.38ms min=2.59ms  med=4.09ms  max=363.56ms p(90)=27.1ms  p(95)=32.81ms
    http_reqs......................: 10710   17.792301/s
    iteration_duration.............: avg=1.93s   min=1s      med=2.03s   max=2.37s    p(90)=2.04s   p(95)=2.05s  
    iterations.....................: 3570    5.930767/s
    vus............................: 13      min=1       max=22
    vus_max........................: 23      min=23      max=23
```

### Stress 테스트 결과

```markdown
    ✗ logged in successfully
     ↳  99% — ✓ 6026 / ✗ 12
    ✓ retrieved member
    ✓ find path

    checks.........................: 99.92% ✓ 17058     ✗ 12    
    data_received..................: 19 MB  38 kB/s
    data_sent......................: 4.2 MB 8.4 kB/s
    http_req_blocked...............: avg=37.18ms min=0s       med=2.84µs  max=5m30s   p(90)=5.03µs  p(95)=8.06µs  
    http_req_connecting............: avg=7.2µs   min=0s       med=0s      max=14.78ms p(90)=0s      p(95)=0s      
    ✗ http_req_duration..............: avg=2.89s   min=0s       med=5ms     max=5m49s   p(90)=53.88ms p(95)=85.74ms
    { expected_response:true }...: avg=1.28s   min=2.54ms   med=5.31ms  max=5m49s   p(90)=53.85ms p(95)=83.41ms
    http_req_failed................: 5.51%  ✓ 996       ✗ 17058
    http_req_receiving.............: avg=1.05s   min=0s       med=42.15µs max=5m44s   p(90)=79.85µs p(95)=111.24µs
    http_req_sending...............: avg=72.67ms min=0s       med=13.22µs max=5m36s   p(90)=28.64µs p(95)=39.33µs
    http_req_tls_handshaking.......: avg=50.25µs min=0s       med=0s      max=30.82ms p(90)=0s      p(95)=0s      
    http_req_waiting...............: avg=1.77s   min=0s       med=4.92ms  max=5m36s   p(90)=53.76ms p(95)=85.59ms
    http_reqs......................: 18054  36.34331/s
    iteration_duration.............: avg=4.32s   min=254.36µs med=2.03s   max=5m50s   p(90)=2.12s   p(95)=2.19s   
    iterations.....................: 5892   11.860794/s
    vus............................: 716    min=1       max=716
    vus_max........................: 1000   min=1000    max=1000
    
    time="2022-12-17T13:45:22Z" level=error msg="some thresholds have failed"
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

## Reverse Proxy (Nginx) 성능 개선 결과
- 다음 표는 Lighthouse를 이용해 검사한 리버스 프록시 성능 개선 적용 이전과 이후 결과 입니다.
- FCP(First Contentful Paint)와 TTI(Time to Interactive), SI(Speed Index)가 크게 개선되었습니다. 

|        | FCP   | TTI   | SI    | TBT   | LCP   | CLS   |
|--------|-------|-------|-------|-------|-------|-------|
| AS-IS  | 13.8s | 14.9s | 14.4s | 410ms | 13.8s | 0.041 |
| TO-BE  | 4.5s  | 5.8s  | 4.5 s | 600ms | 5.0s  | 0.039 |

js 파일의 크기가 최대 20배까지 줄었음을 알 수 있습니다. 
- /js/vendors.js (2,125.4 KiB -> 416 KiB) 
- /js/main.js (637.3 KiB -> 29.2 KiB)


---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
