

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

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요


---

1단계 - 화면 응답 개선하기

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
 - 기존 성능은 아래와 같습니다.

테스트를 진행해보니, 생각보다 흥미로운 부분이 많았습니다.
일단 테스트를 진행하다가 script 가 총 9개 너무 많다고 느껴서, 하나의 결과를 통해서 공유드리고자 합니다.
(전체 결과는 다음 `script/Connectionfrequency /result.md` 에서 찾아볼 수 있습니다.)

맨 처음에는 아무것도 설정하지 않은 상태에서 시작했습니다.(중요하다고 여겨지는 지표만 분리해서 요약했습니다)

|             | checks                   | http_req_duration(avg) | http_reqs     |
| ----------- | ------------------------ | ---------------------- | ------------- |
| Load test   | 100.00% ✓ 56888          | 26.81ms                | 563.273188/s  |
| Smoke test  | 100.00% ✓ 200            | 1.35ms                 | 1.992402/s    |
| Stress test | 86.23% ✓ 85949   ✗ 13722 | 62.61ms                | 1132.896511/s |

이렇게 된 상태에서 Redis 캐시와 정적리소스 호출시 비동기로 받아오도록 수정했습니다.

|             | checks                  | http_req_duration(avg) | http_reqs    |
| ----------- | ----------------------- | ---------------------- | ------------ |
| Load test   | 100.00% ✓ 56704         | 29.02ms                | 561.418494/s |
| Smoke test  | 100.00% ✓ 200           | 1.5ms                  | 1.991849/s   |
| Stress test | 96.27% ✓ 53458   ✗ 2069 | 436.61ms               | 633.338505/s |

확실히, 캐시를 적용하니 서비스가 안정되는 모습을 보여줬습니다. 스트레스 테스트에서 Fail 나는 부분이 적어졌을 뿐만 아니라, HTTP_Reqs 가 줄어드는 효과가 나타났습니다.



이번에는 reverse-proxy 를 개선하는 작업을 수행해보았습니다.

먼저 reverse-proxy를 개선하는 작업을 하기전 아래와 같은 nginx.conf 내용으로 수행했습니다.

```shell
events {}

http {       
  upstream app {
    server 172.17.0.1:8080;
  }
  
  # Redirect all traffic to HTTPS
  server {
    listen 80;
    return 301 https://$host$request_uri;
  }

  server {
    listen 443 ssl;  
    ssl_certificate /etc/letsencrypt/live/[도메인주소]/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/[도메인주소]/privkey.pem;

    # Disable SSL
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;

    # 통신과정에서 사용할 암호화 알고리즘
    ssl_prefer_server_ciphers on;
    ssl_ciphers ECDH+AESGCM:ECDH+AES256:ECDH+AES128:DH+3DES:!ADH:!AECDH:!MD5;

    # Enable HSTS
    # client의 browser에게 http로 어떠한 것도 load 하지 말라고 규제합니다.
    # 이를 통해 http에서 https로 redirect 되는 request를 minimize 할 수 있습니다.
    add_header Strict-Transport-Security "max-age=31536000" always;

    # SSL sessions
    ssl_session_cache shared:SSL:10m;
    ssl_session_timeout 10m;      

    location / {
      proxy_pass http://app;    
    }
  }
}
```

이 상태에서 성능 테스트를 해본 결과 아래와 같았습니다. (redis 캐시 적용 되어있습니다.)

|             | checks                   | http_req_duration(avg) | http_reqs    |
| ----------- | ------------------------ | ---------------------- | ------------ |
| Load test   | 99.97%  ✓ 53398  ✗ 14    | 51.68ms                | 528.841772/s |
| Smoke test  | 100.00% ✓ 200            | 2.57ms                 | 1.986918/s   |
| Stress test | 48.50%  ✓ 27197  ✗ 28871 | 96.09ms                | 646.726603/s |

이후 위 `nginx 세팅하기`에서 http2, reverse 캐시, 정적리소스 압축 등을 추가하여 다시 성능 테스트를 해본 결과 아래와 같았습니다.

|             | checks                     | http_req_duration(avg) | http_reqs    |
| ----------- | -------------------------- | ---------------------- | ------------ |
| Load test   | 100.00% ✓ 51730  ✗ 0       | 66.98ms                | 512.190122/s |
| Smoke test  | 100.00% ✓ 200              | 2.12ms                 | 1.97ms       |
| Stress test | 93.92% ✓ 72378      ✗ 4684 | 150.96ms               | 877.031136/s |

`nginx 개선하기` 세팅을 하고난 이후로, 스트레스 테스트에서 더 많은 트래픽을 견디는 효과가 났습니다. 그리고, Load, Smoke 테스트에서 조금 더 나은 개선효과를 보여줬습니다.




힌트에서 알려주는 할 수 있는 행위들

1. 정적 파일 경량화
   - [x] webpack-bundle-analyzer 플로그인 설치 후 불필요한 import 제거하기
   - [x] code Splitting 플러그인 적용하기
   - [x] 다이나믹 임포트 적용하기(이미 적용됨을 확인)
   - [x] 웹 폰트 최적화(이미 적용됨을 확인)
   
2. Reverse Proxy 개선하기

   - [x] SSL 에 추가 개선하기
   
3. WAS 성능 개선하기
   
   - [x] Spring Data Cache - radis 설정

4. 비동기 처리하기
   - [x] 적절한 Thread pool size 구하기
     ```shell
      $ cat /proc/cpuinfo | grep "model name" | uniq -c | awk '{print $5 $6, $7,$8, $9, $10 $11}'
      Intel(R)Xeon(R) CPU E5-2676 v3 @2.40GHz
      [WEB2][10:57:45][ubuntu@ip-172-22-1-28 ~]
      $ cat /proc/cpuinfo | grep "cpu cores" | tail -1 | awk '{print $4}'
      2
      [WEB2][10:57:57][ubuntu@ip-172-22-1-28 ~]
      $ cat /proc/cpuinfo | grep "physical id" | sort -u | wc -l
      1
      [WEB2][10:58:05][ubuntu@ip-172-22-1-28 ~]
      $ grep -c processor /proc/cpuinfo
      2
     ```
   - [x] Async Thread pool 설정
> cheatsheet
> 
> 공인 IP 확인 >  curl bot.whatismyipaddress.com
> 
> docker run -d -p 3306:3306 brainbackdoor/data-subway:0.0.1
> 
> find ./* -name "*.jar"
>
> java -jar ./build/libs/subway-0.0.1-SNAPSHOT.jar
>
> k6 run smoke.js
> 
> https://k6.io/docs/getting-started/results-output/

