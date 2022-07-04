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
   - [after](/docs/step1/after)
   - [before](/docs/step1/before)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

    - nginx cache, http2, gzip
    - spring redis 적용

    - pagespeed 성능지표 기준 67 -> 94 상승
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-037f5f6c39cc0e207

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
   - [stress 1100 VUs](./docs/step2/ec2_smoke1100.png)
```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
   - [smoke](./docs/step2/smoke.png)
   - [load](./docs/step2/load.png)
   - [stress 350 VUs](./docs/step2/stress350.png)
   - [stress 700 VUs](./docs/step2/stress700.png)
   - [stress 1100 VUs](./docs/step2/stress1100.png)
---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
   - [query](./docs/step3/query.sql)
   - [result](./docs/step3/query_result.png)
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
   - Coding as a Hobby 와 같은 결과를 반환하세요.
     - 인덱스 추가 전 / 후 : 4.912 sec / 0.267 sec
       ```sql
       --인덱스 추가
       CREATE UNIQUE INDEX `idx_programmer_id`  ON `subway`.`programmer` (id);
       CREATE INDEX `idx_programmer_hobby`  ON `subway`.`programmer` (hobby);
       
       -- query
       SELECT hobby,
       count(*) * 100 / (SELECT count(*) FROM programmer) as '비율'
       FROM programmer
       GROUP BY hobby;
       ```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
