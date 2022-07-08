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
- ./performance/results/before (개선 전)
- ./performance/results/after (개선 후)
- page speed 결과 
  - 모바일 : 31 -> 49
  - 데스크탑 : 67 -> 94

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- [x] webserver (nginx) 
  - [x] nginx.conf 파일에 gzip encoding 설정을 진행했습니다.
  - [x] nginx.conf 파일에 cache 설정을 진행했습니다.
  - [x] nginx.conf 파일에 http2 설정을 진행했습니다.
- [x] was
  - [x] redis 설정을 진행했습니다.
  - [x] service 클래스의 read, update, delete 로직에 Cache 어노테이션을 적용했습니다.
- 

---

### 2단계 - 스케일 아웃

- [x] springboot에 HTTP Cache, gzip 설정하기
- [x] Launch Template 작성하기
- [x] Auto Scaling Group 생성하기
- [x] DNS 설정
- [x] Smoke, Load, Stress 테스트 후 결과를 기록
  - ./performance/step2/results/smoke_result.png
  - ./performance/step2/results/load_result.png
  - ./performance/step2/results/stress_result.png

1. Launch Template 링크를 공유해주세요.
   - https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-01e8d81098e933acb
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
   - ./performance/step2/cloudwatch/auto_scale_cpu_stress.png
   - ./performance/step2/cloudwatch/auto_scale_instance.png
   - ./performance/step2/cloudwatch/auto_scale_instance_increase.png

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
  - ./performance/step2/results/smoke_result.png
  - ./performance/step2/results/load_result.png
  - ./performance/step2/results/stress_result.png

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

./performance/step3/query.sql
./performance/step3/query.png

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- [x] Coding as a Hobby 와 같은 결과를 반환하세요.
  - 쿼리 : ./performance/step4/1_실행쿼리.sql
  - 실행계획 : ./performance/step4/1_실행계획.png
  - programmer 테이블에 hobby 컬럼을 index로 추가
- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  - 쿼리 : ./performance/step4/2_실행쿼리.sql
  - 실행계획 : ./performance/step4/2_실행계획.png
  - covid, programmer, hospital 테이블의 id에 PK 적용, programmer의 id에 index 적용
- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  - 쿼리 : ./performance/step4/3_실행쿼리.sql
  - 실행계획 : ./performance/step4/3_실행계획.png
- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  - 쿼리 : ./performance/step4/4_실행쿼리.sql
  - 실행계획 : ./performance/step4/4_실행계획.png
  - member 테이블 id PK 적용
- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  - 쿼리 : ./performance/step4/4_실행쿼리.sql
  - 실행계획 : ./performance/step4/4_실행계획.png

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
