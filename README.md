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

- 표 우측으로 갈수록 기능 누적 추가

|http_req_duration| before  | +gzip   | +cache  | +http2| +redis |
|-----------------|---------|---------|---------|-------|--------|
| smoke           | 66.16ms | 39.83ms | 36.53ms | 38.52s| 0.0s |
| load            | 49.42ms | 48.50ms | 49.39ms | 51.02s| 0.0s |
| stress          | 490.78ms| 454.78ms| 451.65ms| 4.9s  | 0.0s |

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- [x] gzip 압축 수행(인터넷 구간에서 가장 큰 개선 효과)
    - nginx 설정파일에서 gzip 옵션 추가
    - smoke 부하 테스트는 36ms 속도 개선
- [x] cache 적용
    - nginx 설정파일에서 cache 옵션 추가
    - gzip 압축때와 비교하여 상대적으로 속도 개선 없었음
- [x] http2 적용
    - nginx 설정파일에서 http2 옵션 추가
    - cache까지 적용했을 때보다 오히려 속도가 살짝 느려짐

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
