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
1. 성능 개선 결과를 공유해주세요 (webpagetest 테스트 결과)

#### 목표값


|                                     | 경로 검색 페이지 | 경로 검색 결과 페이지 |
|-------------------------------------|------------------|-----------------------|
| 최종 목표                           | 최대 TTI 2s      | 최대 LCP 3s           |
| 웹서버의 정적 리소스 반환             | 최대 132ms       | 최대 45ms           |
| WAS 로직 처리                       |                  | 최대 27.8ms           |
| DB 조회                             |                  | 최대 65ms             |

경로 검색 결과 페이지의 경우 URL이 바뀌지 않고 데이터만 통신하는 대신 경로 검색 페이지와 같은 화면을 공유하기 때문에 경로 검색 페이지의 성능을 TTI 2s LCP 3s 아래로 개선하는데 집중해 보겠습니다.

#### 경로 검색 페이지 개선 전 성능

TTI 5.2초
![경로 검색 페이지 개선전 TTI](https://user-images.githubusercontent.com/71138398/220517215-32f70f10-ed8f-420e-b559-ffd5470bb6cc.png)

LCP 4.7초
![경로 검색 페이지 개선 전 LCP](https://user-images.githubusercontent.com/71138398/220517212-89b9e8de-87f1-4a68-8d14-43722ad413cf.png)


#### gzip 설정 후

TTI 1.7초
![gzip 설정 후 TTI](https://user-images.githubusercontent.com/71138398/220517183-7b5ab347-4be6-4508-bdce-8e4695df27ea.png)

LCP 1.7초
![gzip 설정 후 LCP](https://user-images.githubusercontent.com/71138398/220517097-211c0d06-740a-44b1-ad02-150efd2f104f.png)

#### Nginx 캐시 설정 전 Repeat View (gzip 설정 후)

TTI 0.9초
![Nginx 캐시 설정 전 TTI](https://user-images.githubusercontent.com/71138398/220517210-17698fe9-580f-4052-ab66-5aa0ebc8557b.png)

LCP 0.8초
![Nginx 캐시 설정 전 LCP](https://user-images.githubusercontent.com/71138398/220517207-b5afadcb-97bd-42f2-83a0-fec54ce204ed.png)

#### Nginx 캐시 설정 후 Repeat View

TTI 1.1초
![Nginx 캐시 설정 후 TTI](https://user-images.githubusercontent.com/71138398/220517204-3c4bbf70-1eeb-4a46-b015-12a0fcba4140.png)

LCP 1초
![Nginx 캐시 설정 후 LCP](https://user-images.githubusercontent.com/71138398/220517254-d6e47341-9286-490a-be06-56c802fc1026.png)

#### HTTP2 적용 전

TTI 1.7초
![gzip 설정 후 TTI](https://user-images.githubusercontent.com/71138398/220517183-7b5ab347-4be6-4508-bdce-8e4695df27ea.png)

LCP 1.7초
![gzip 설정 후 LCP](https://user-images.githubusercontent.com/71138398/220517097-211c0d06-740a-44b1-ad02-150efd2f104f.png)

#### HTTP2 적용 후

TTI 1.8초
![HTTP2 적용 후 TTI](https://user-images.githubusercontent.com/71138398/220517650-4dad82bd-b74d-465a-be67-1d80402d5272.png)

LCP 1.8초
![HTTP2 적용 후 LCP](https://user-images.githubusercontent.com/71138398/220517206-b453bbb9-34ff-44ea-bbce-919250a59988.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

먼저 gzip 적용을 해보았습니다. 드라마틱하게 개선되어서 바로 목표를 이루는 성공했습니다.

그리고 리버스 프록시 캐싱을 적용했는데요. 왜인지는 모르겠지만 이미 이전부터 WAS에서 자동으로 캐싱을 적용하고 있더라구요. 그래서 그런지 성능 변화가 없었습니다.

이 후 HTTP2를 적용했습니다. 경로 검색 페이지에서는 HOL 이슈가 없어서 그런지? HTTP2도 마찬가지로 성능 변화가 없었습니다. 

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)

---

### 3단계 - WAS 개선하기

1. springboot에 HTTP Cache, gzip 설정하기

2. Data Cache 설정하기

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)
