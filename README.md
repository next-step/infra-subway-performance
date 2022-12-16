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

     checks.....................: 100.00% ✓ 13706     ✗ 0
     data_received..............: 5.1 MB  8.4 kB/s
     data_sent..................: 2.7 MB  4.6 kB/s
     http_req_blocked...........: avg=14.68µs min=954ns   med=2.81µs  max=47.07ms  p(90)=5.13µs  p(95)=5.59µs
     http_req_connecting........: avg=1.15µs  min=0s      med=0s      max=7.11ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..........: avg=3.67ms  min=1.93ms  med=3.35ms  max=34.16ms  p(90)=5.13ms  p(95)=6.35ms
     http_req_failed............: 100.00% ✓ 13706     ✗ 0
     http_req_receiving.........: avg=53.92µs min=13.53µs med=47.5µs  max=3.76ms   p(90)=74.45µs p(95)=87.72µs
     http_req_sending...........: avg=20.28µs min=5.15µs  med=16.97µs max=871.88µs p(90)=32.21µs p(95)=41.48µs
     http_req_tls_handshaking...: avg=7.17µs  min=0s      med=0s      max=30.33ms  p(90)=0s      p(95)=0s
     http_req_waiting...........: avg=3.59ms  min=1.89ms  med=3.27ms  max=34.08ms  p(90)=5.04ms  p(95)=6.26ms
     http_reqs..................: 13706   22.808168/s
     iteration_duration.........: avg=1s      min=1s      med=1s      max=1.05s    p(90)=1.01s   p(95)=1.01s
     iterations.................: 6853    11.404084/s
     vus........................: 22      min=1       max=22
     vus_max....................: 23      min=23      max=23
```

### Stress 테스트 결과

```markdown
     ✗ logged in successfully
      ↳  75% — ✓ 89600 / ✗ 29468
     ✓ retrieved member

     checks.....................: 85.78%  ✓ 177828     ✗ 29468
     data_received..............: 304 MB  778 kB/s
     data_sent..................: 63 MB   160 kB/s
     http_req_blocked...........: avg=114.41ms min=0s       med=2.19µs  max=2.55s    p(90)=429.55ms p(95)=939.04ms
     http_req_connecting........: avg=50.31ms  min=0s       med=0s      max=1.56s    p(90)=235.77ms p(95)=348.14ms
   ✗ http_req_duration..........: avg=24.98ms  min=0s       med=4.04ms  max=2.69s    p(90)=40.47ms  p(95)=113.54ms
     http_req_failed............: 100.00% ✓ 208668     ✗ 0
     http_req_receiving.........: avg=863.01µs min=0s       med=25.39µs max=958.63ms p(90)=53.16µs  p(95)=111.33µs
     http_req_sending...........: avg=4.02ms   min=0s       med=10.4µs  max=2.22s    p(90)=1.1ms    p(95)=16.65ms
     http_req_tls_handshaking...: avg=91.58ms  min=0s       med=0s      max=2.39s    p(90)=352.07ms p(95)=702.03ms
     http_req_waiting...........: avg=20.09ms  min=0s       med=3.65ms  max=2.29s    p(90)=26.8ms   p(95)=84.5ms
     http_reqs..................: 208668  533.729869/s
     iteration_duration.........: avg=1.07s    min=296.01µs med=1s      max=4.89s    p(90)=1.46s    p(95)=1.92s
     iterations.................: 119068  304.551479/s
     vus........................: 72      min=1        max=998
     vus_max....................: 1000    min=1000     max=1000
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
