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
- Before ([경로](/result/before))
    - Http Request Duration 평균
        - Smoke  : 7.1ms
        - Load   : 7.65ms
        - Stress : 138.6ms
    - VUser 263 부터 오류발생
    - LightHouse
         | Desktop  | Lighthouse 성능    | FCP(s) | TTI(s) |
         | :------- | ------------------| ---------- | ----------- |
         | 메인      | 70                | 2.5        | 2.6         |
         | 역관리     | 42                | 2.6       | 3.5         |
         | 노선관리   | 68                | 2.6        | 2.8         |
         | 구간관리   | 65                | 3.0        | 3.2         |
         | 경로검색   | 66                | 2.7        | 2.8        | 
      
- After ([경로](/result/after))
    - Http Request Duration 평균
        - Smoke  : 6.42ms (-약 9%)
        - Load   : 6.85ms (-약 10%)
        - Stress : 106.83ms (-약 20% )
    - VUser 258 부터 오류발생
        - LightHouse ()
          | Desktop  | Lighthouse 성능    | FCP(s) | TTI(s) |
          | :------- | ------------------| ---------- | ----------- |
          | 메인      | 97  (+20)         | 0.9        | 1.1         |
          | 역관리    | 70  (+28)          | 1.0        | 2.0         |
          | 노선관리   | 89  (+21)         | 1.0        | 1.5         |
          | 구간관리   | 94  (+29)          | 1.0        | 1.3         |
          | 경로검색   | 95  (+29)          | 1.0        | 1.3        |


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요 

- Reverse Proxy 개선 - nginx Gzip encode 설정
    - LightHouse
      | Desktop  | Lighthouse 성능    | FCP(s) | TTI(s) |
      | :------- | ------------------| ---------- | ----------- |
      | 메인      | 93                | 1.2        | 1.3         |
      | 역관리     | 63               | 1.3        | 2.1         |
      | 노선관리   | 87                | 1.3        | 1.6         |
      | 구간관리   | 94                | 1.0        | 1.3         |
      | 경로검색   | 90                | 1.4        | 1.4        |

   - 메인페이지 기준 2.6 MB -> 648 KB 네트워크 전송 감소
   - Http Request Duration 평균 :  138 -> 130 ms (stress 기준)
    
- Reverse Proxy 개선 - cache & http2 설정
    - LightHouse
      | Desktop  | Lighthouse 성능    | FCP(s) | TTI(s) |
      | :------- | ------------------| ---------- | ----------- |
      | 메인      | 97                | 0.9        | 1.1         |
      | 역관리     | 70               | 1.0        | 2.0         |
      | 노선관리   | 95                | 1.0        | 1.3         |
      | 구간관리   | 94                | 1.0        | 1.3         |
      | 경로검색   | 95                | 1.0        | 1.3        |

  - Http Request Duration 평균 :  130 -> 116 ms (stress 기준)
    
- WAS 성능 개선하기 - Redis Cache 설정
    - 지하철, 노선, 즐겨찾기 목록 조회 및 구간 조회 Cache 설정
    - LightHouse
      | Desktop  | Lighthouse 성능    | FCP(s) | TTI(s) |
      | :------- | ------------------| ---------- | ----------- |
      | 메인      | 97                | 0.9        | 1.1         |
      | 역관리     | 70               | 1.0        | 2.0         |
      | 노선관리   | 89 (- 6)          | 1.0        | 1.5         |
      | 구간관리   | 94                | 1.0        | 1.3         |
      | 경로검색   | 95                | 1.0        | 1.3        | 

   - Http Request Duration 평균 :  116 -> 106 ms (stress 기준)
    
- 번외 
    - hikari connection-timeout 30 -> 10초, maximum-pool-size 20 -> 10 설정
    - Http Request Duration 평균 :  ~~106 -> 113 ms (stress 기준) 오히려 증가~~
      -> 재시도 1차 107ms, 2차 108ms 차이없는 것으로 정정
      -> Redis Cache를 사용하고 있어 DB Connection이 많지 않아 성능 차이 없는 것으로 확인
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---
### [추가] 1단계 - 쿠버네티스로 구성하기
1. 클러스터를 어떻게 구성했는지 알려주세요~ (마스터 노드 : n 대, 워커 노드 n대)

2. 스트레스 테스트 결과를 공유해주세요 (기존에 container 한대 운영시 한계점도 같이 공유해주세요)

3. 현재 워커노드에서 몇대의 컨테이너를 운영중인지 공유해주세요

---

### [추가] 2단계 - 클러스터 운영하기
1. kibana 링크를 알려주세요

2. grafana 링크를 알려주세요

3. 지하철 노선도는 어느정도로 requests를 설정하는게 적절한가요?

4. t3.large로 구성할 경우 Node의 LimitRange, ResourceQuota는 어느정도로 설정하는게 적절한가요?

5. 부하테스트를 고려해볼 때 Pod은 몇대정도로 구성해두는게 좋다고 생각하나요?

6. Spinaker 링크를 알려주세요.
