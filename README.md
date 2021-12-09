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
## 1단계 - 화면 응답 개선하기
### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

#### 지하철역 조회
- Smoke
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.0s), 0/1 VUs, 37 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 지하철역이 정상적으로 조회됨

     checks.........................: 100.00% ✓ 74       ✗ 0
     data_received..................: 2.7 MB  272 kB/s
     data_sent......................: 7.6 kB  763 B/s
     http_req_blocked...............: avg=808.25µs min=2.91µs   med=3.03µs   max=59.58ms  p(90)=3.15µs   p(95)=3.27µs
     http_req_connecting............: avg=14.71µs  min=0s       med=0s       max=1.08ms   p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=133.37ms min=9.74ms   med=133.83ms max=296.15ms p(90)=261.19ms p(95)=264.26ms
       { expected_response:true }...: avg=133.37ms min=9.74ms   med=133.83ms max=296.15ms p(90)=261.19ms p(95)=264.26ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 74
     http_req_receiving.............: avg=1.37ms   min=59.74µs  med=188.23µs max=7.46ms   p(90)=3.41ms   p(95)=4.71ms
     http_req_sending...............: avg=70.11µs  min=55.36µs  med=65.57µs  max=223.77µs p(90)=81.91µs  p(95)=91.87µs
     http_req_tls_handshaking.......: avg=260.45µs min=0s       med=0s       max=19.27ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=131.92ms min=9.6ms    med=132.61ms max=291.28ms p(90)=258.82ms p(95)=262.36ms
     http_reqs......................: 74      7.392778/s
     iteration_duration.............: avg=270.47ms min=253.98ms med=266.12ms max=379.24ms p(90)=279.02ms p(95)=281.34ms
     iterations.....................: 37      3.696389/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

ERRO[0011] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Load
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m29.9s), 000/240 VUs, 431 complete and 72 interrupted iterations
default ✓ [======================================] 000/240 VUs  1m0s

     ✓ 메인페이지가 정상적으로 응답함
     ✗ 지하철역이 정상적으로 조회됨
      ↳  62% — ✓ 270 / ✗ 161

     checks.........................: 82.76% ✓ 773       ✗ 161
     data_received..................: 21 MB  236 kB/s
     data_sent......................: 211 kB 2.3 kB/s
     http_req_blocked...............: avg=2.26ms   min=2.71µs   med=3.09µs   max=44.01ms p(90)=9.05ms   p(95)=10.5ms
     http_req_connecting............: avg=588.62µs min=0s       med=0s       max=9.81ms  p(90)=2.21ms   p(95)=3.08ms
   ✗ http_req_duration..............: avg=11.98s   min=7.75ms   med=2.74s    max=39.76s  p(90)=32.68s   p(95)=33.81s
       { expected_response:true }...: avg=8.08s    min=7.75ms   med=830.81ms max=39.76s  p(90)=32.8s    p(95)=34.06s
     http_req_failed................: 17.23% ✓ 161       ✗ 773
     http_req_receiving.............: avg=1.44ms   min=34.09µs  med=89.81µs  max=32.02ms p(90)=4.35ms   p(95)=7.85ms
     http_req_sending...............: avg=92.12µs  min=41.61µs  med=67.15µs  max=2.43ms  p(90)=145.64µs p(95)=160.01µs
     http_req_tls_handshaking.......: avg=1.61ms   min=0s       med=0s       max=30.93ms p(90)=6.44ms   p(95)=7.51ms
     http_req_waiting...............: avg=11.97s   min=7.61ms   med=2.74s    max=39.75s  p(90)=32.68s   p(95)=33.81s
     http_reqs......................: 934    10.383819/s
     iteration_duration.............: avg=25.52s   min=252.77ms med=30.07s   max=42.66s  p(90)=36.43s   p(95)=37.64s
     iterations.....................: 431    4.791676/s
     vus............................: 2      min=1       max=240
     vus_max........................: 240    min=240     max=240

ERRO[0091] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Stress
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m25s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 55s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)
INFO[0033] {"remote_ip":"172.67.219.112","remote_port":443,"url":"https://lunechaser.testchaser.site/stations","status":500,"status_text":"500 Internal Server Error","proto":"HTTP/2.0","headers":{"Expect-Ct":"max-age=604800, report-uri=\"https://report-uri.cloudflare.com/cdn-cgi/beacon/expect-ct\"","Cf-Cache-Status":"DYNAMIC","Report-To":"{\"endpoints\":[{\"url\":\"https:\\/\\/a.nel.cloudflare.com\\/report\\/v3?s=POS3twb4OIvUG7PsfBwhLQ4Kg%2FCIIj7ikKYC27O33b9IO5wL1TpT8IQYPqxsZKFnWRnM9zl1Pj2%2FfCi%2BWMhr8TTRIQmpSOihAM0ujN2H2yESXwzB%2BAqb6pUl%2Fk6ijj7CgS1JQ8MLBG4dyW2bkQ%3D%3D\"}],\"group\":\"cf-nel\",\"max_age\":604800}","Nel":"{\"success_fraction\":0,\"report_to\":\"cf-nel\",\"max_age\":604800}","Cf-Ray":"6bacb2e64d5e0154-ICN","Server":"cloudflare","Strict-Transport-Security":"max-age=31536000","Alt-Svc":"h3=\":443\"; ma=86400, h3-29=\":443\"; ma=86400, h3-28=\":443\"; ma=86400, h3-27=\":443\"; ma=86400","Date":"Thu, 09 Dec 2021 08:00:16 GMT","Content-Type":"text/html"},"cookies":{},"body":"<html>\r\n<head><title>500 Internal Server Error</title></head>\r\n<body>\r\n<center><h1>500 Internal Server Error</h1></center>\r\n<hr><center>nginx/1.21.4</center>\r\n</body>\r\n</html>\r\n","timings":{"duration":24.292081,"blocked":0.002797,"looking_up":0,"connecting":0,"tls_handshaking":0,"sending":0.045193,"waiting":24.18908,"receiving":0.057808},"tls_version":"tls1.3","tls_cipher_suite":"TLS_AES_128_GCM_SHA256","ocsp":{"produced_at":1638785548,"this_update":1638784622,"next_update":1639386722,"revoked_at":-62135596800,"revocation_reason":"unspecified","status":"good"},"error":"","error_code":1500,"request":{"method":"GET","url":"https://lunechaser.testchaser.site/stations","headers":{"User-Agent":["k6/0.35.0 (https://k6.io/)"]},"body":"","cookies":{}}}  source=console
ERRO[0045] cannot parse json due to an error at line 1, character 2 , error: invalid character '<' looking for beginning of value
running at reflect.methodValueCall (native)
default at 지하철역이 정상적으로 조회됨 (file:///app/infra-subway-performance/k6/line/stress.js:46:60(3))
        at go.k6.io/k6/js/common.Bind.func1 (native)
        at 지하철역관리_조회요청_결과_확인 (file:///app/infra-subway-performance/k6/line/stress.js:45:561(7))
        at file:///app/infra-subway-performance/k6/line/stress.js:26:47(13)  executor=ramping-vus scenario=default source=stacktrace

running (1m22.6s), 000/300 VUs, 4108 complete and 54 interrupted iterations
default ✓ [======================================] 000/300 VUs  55s

     ✗ 메인페이지가 정상적으로 응답함
      ↳  10% — ✓ 441 / ✗ 3721
     ✗ 지하철역이 정상적으로 조회됨
      ↳  6% — ✓ 252 / ✗ 3856

     checks.........................: 8.37%  ✓ 693       ✗ 7577
     data_received..................: 22 MB  271 kB/s
     data_sent......................: 506 kB 6.1 kB/s
     http_req_blocked...............: avg=412.5µs  min=2.35µs  med=2.89µs   max=106.51ms p(90)=3.23µs   p(95)=36.81µs
     http_req_connecting............: avg=114.84µs min=0s      med=0s       max=29.16ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=1.31s    min=8.07ms  med=21.82ms  max=40.6s    p(90)=55.42ms  p(95)=3.16s
       { expected_response:true }...: avg=9.18s    min=9.81ms  med=2.05s    max=40.6s    p(90)=32.93s   p(95)=34.56s
     http_req_failed................: 91.62% ✓ 7577      ✗ 693
     http_req_receiving.............: avg=2.46ms   min=24.22µs med=385.48µs max=94.64ms  p(90)=7.84ms   p(95)=12.69ms
     http_req_sending...............: avg=311.59µs min=29.86µs med=55.54µs  max=56.15ms  p(90)=173.99µs p(95)=544.78µs
     http_req_tls_handshaking.......: avg=268.88µs min=0s      med=0s       max=54.13ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=1.31s    min=0s      med=19.83ms  max=40.6s    p(90)=45.98ms  p(95)=3.16s
     http_reqs......................: 8270   100.09131/s
     iteration_duration.............: avg=2.62s    min=19.42ms med=46.07ms  max=41.93s   p(90)=2.38s    p(95)=30.06s
     iterations.....................: 4108   49.718876/s
     vus............................: 3      min=1       max=299
     vus_max........................: 300    min=300     max=300

ERRO[0084] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

#### 경로 탐색 조회
- Smoke
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.4s), 0/1 VUs, 26 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 경로가 정상적으로 검색됨

     checks.........................: 100.00% ✓ 52       ✗ 0
     data_received..................: 119 kB  11 kB/s
     data_sent......................: 2.4 kB  232 B/s
     http_req_blocked...............: avg=476.65µs min=2.96µs   med=3.06µs   max=24.62ms  p(90)=3.26µs   p(95)=3.35µs
     http_req_connecting............: avg=57.71µs  min=0s       med=0s       max=3ms      p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=198.65ms min=12.08ms  med=194.67ms max=397.16ms p(90)=390.85ms p(95)=392.51ms
       { expected_response:true }...: avg=198.65ms min=12.08ms  med=194.67ms max=397.16ms p(90)=390.85ms p(95)=392.51ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 52
     http_req_receiving.............: avg=87.82µs  min=51.47µs  med=85.78µs  max=238.25µs p(90)=101.6µs  p(95)=112.2µs
     http_req_sending...............: avg=80.34µs  min=52.16µs  med=70.42µs  max=390.14µs p(90)=89.21µs  p(95)=98.32µs
     http_req_tls_handshaking.......: avg=398.96µs min=0s       med=0s       max=20.74ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=198.48ms min=11.92ms  med=194.41ms max=396.99ms p(90)=390.68ms p(95)=392.36ms
     http_reqs......................: 52      5.012973/s
     iteration_duration.............: avg=398.89ms min=380.84ms med=396.88ms max=446.97ms p(90)=409.17ms p(95)=409.5ms
     iterations.....................: 26      2.506486/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

ERRO[0011] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Load
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)

ERRO[0084] TypeError: Cannot read property 'length' of undefined
running at 경로가 정상적으로 검색됨 (file:///app/infra-subway-performance/k6/map/load.js:43:54(6))
default at go.k6.io/k6/js/common.Bind.func1 (native)
        at 경로탐색_결과_확인 (file:///app/infra-subway-performance/k6/map/load.js:42:38(7))
        at file:///app/infra-subway-performance/k6/map/load.js:24:28(15)  executor=ramping-vus scenario=default source=stacktrace

running (1m28.9s), 000/240 VUs, 409 complete and 69 interrupted iterations
default ✓ [======================================] 000/240 VUs  1m0s

     ✗ 메인페이지가 정상적으로 응답함
      ↳  99% — ✓ 477 / ✗ 1
     ✗ 경로가 정상적으로 검색됨
      ↳  47% — ✓ 195 / ✗ 214

     checks.........................: 75.76% ✓ 672      ✗ 215
     data_received..................: 2.1 MB 24 kB/s
     data_sent......................: 164 kB 1.8 kB/s
     http_req_blocked...............: avg=2.33ms  min=2.38µs   med=3.04µs   max=41.32ms  p(90)=8.94ms   p(95)=10.59ms
     http_req_connecting............: avg=583.2µs min=0s       med=0s       max=6.42ms   p(90)=2.15ms   p(95)=3.06ms
   ✗ http_req_duration..............: avg=12.93s  min=9.3ms    med=2.96s    max=41.7s    p(90)=32.97s   p(95)=34.27s
       { expected_response:true }...: avg=7.38s   min=9.3ms    med=793.23ms max=41.7s    p(90)=33.97s   p(95)=34.51s
     http_req_failed................: 24.23% ✓ 215      ✗ 672
     http_req_receiving.............: avg=96.69µs min=34.48µs  med=83.36µs  max=1.77ms   p(90)=115.67µs p(95)=134.1µs
     http_req_sending...............: avg=90.14µs min=37.03µs  med=69.22µs  max=537.86µs p(90)=152.01µs p(95)=169.43µs
     http_req_tls_handshaking.......: avg=1.68ms  min=0s       med=0s       max=22.71ms  p(90)=6.42ms   p(95)=7.78ms
     http_req_waiting...............: avg=12.93s  min=9.09ms   med=2.96s    max=41.7s    p(90)=32.97s   p(95)=34.27s
     http_reqs......................: 887    9.978399/s
     iteration_duration.............: avg=27.45s  min=192.09ms med=30.06s   max=45.09s   p(90)=36.44s   p(95)=39.09s
     iterations.....................: 409    4.601088/s
     vus............................: 14     min=1      max=240
     vus_max........................: 240    min=240    max=240

ERRO[0090] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Stress
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m25s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 55s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)

ERRO[0083] TypeError: Cannot read property 'length' of undefined
running at 경로가 정상적으로 검색됨 (file:///app/infra-subway-performance/k6/map/stress.js:45:54(6))
default at go.k6.io/k6/js/common.Bind.func1 (native)
        at 경로탐색_결과_확인 (file:///app/infra-subway-performance/k6/map/stress.js:44:536(7))
        at file:///app/infra-subway-performance/k6/map/stress.js:26:28(15)  executor=ramping-vus scenario=default source=stacktrace

running (1m23.1s), 000/300 VUs, 5608 complete and 65 interrupted iterations
default ↓ [======================================] 185/300 VUs  55s

     ✗ 메인페이지가 정상적으로 응답함
      ↳  7% — ✓ 426 / ✗ 5247
     ✗ 경로가 정상적으로 검색됨
      ↳  3% — ✓ 169 / ✗ 5439

     checks.........................: 5.27%  ✓ 595        ✗ 10686
     data_received..................: 5.6 MB 67 kB/s
     data_sent......................: 571 kB 6.9 kB/s
     http_req_blocked...............: avg=299.93µs min=2.28µs  med=2.89µs   max=121.06ms p(90)=3.16µs   p(95)=8.65µs
     http_req_connecting............: avg=81.58µs  min=0s      med=0s       max=56.72ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=987.5ms  min=7.13ms  med=20.85ms  max=42.61s   p(90)=44.02ms  p(95)=120.99ms
       { expected_response:true }...: avg=7.78s    min=9.66ms  med=1.06s    max=42.61s   p(90)=33.9s    p(95)=34.85s
     http_req_failed................: 94.72% ✓ 10686      ✗ 595
     http_req_receiving.............: avg=2.42ms   min=22.93µs med=479.65µs max=83.74ms  p(90)=6.78ms   p(95)=10.89ms
     http_req_sending...............: avg=266.26µs min=28.86µs med=52.18µs  max=72.46ms  p(90)=159.05µs p(95)=432.34µs
     http_req_tls_handshaking.......: avg=196.82µs min=0s      med=0s       max=64.14ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=984.81ms min=0s      med=18.81ms  max=42.61s   p(90)=38.52ms  p(95)=84.57ms
     http_reqs......................: 11281  135.812202/s
     iteration_duration.............: avg=1.97s    min=20.37ms med=44.47ms  max=45.03s   p(90)=107.91ms p(95)=30.03s
     iterations.....................: 5608   67.514833/s
     vus............................: 1      min=1        max=300
     vus_max........................: 300    min=300      max=300

ERRO[0084] some thresholds have failed

```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>





### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

## 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

