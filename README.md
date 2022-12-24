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
+ k6 파일 폴더에 넣어뒀습니다 !


#### 성능 개선 전 (http_req_duration 기준 표 작성)
|        | http_req_duration(mean) | http_req_duration(max) | http_req_duration(med) |
|--------|-------------------------|------------------------|------------------------|
| LOAD   | 8.67ms                  | 424.79ms               | 8.21ms                 | 
| SMOKE  | 15.90ms                 | 781.65ms               | 9.04ms                 | 
| STRESS | 57.80ms                 | 353.37ms               | 39.08ms                | 


#### 성능 개선 후 (http_req_duration 기준 표 작성)
|        | http_req_duration(mean) | http_req_duration(max) | http_req_duration(med) |
|--------|-------------------------|------------------------|------------------------|
| LOAD   | 11.07ms                 | 879.08ms               | 9.08ms                 | 
| SMOKE  | 10.75ms                 | 128.89ms               | 10.34ms                | 
| STRESS | 11.16ms                 | 240.36ms               | 10.23ms                | 

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- nginx gzip 압축, cache, HTTP/2 설정을 통해 리버스 프록시를 개선
-  redis와 Spring Data Cache를 이용하여 애플리케이션 내 조회 기능에 캐싱을 적용해 조회 성능을 개선
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
   https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-07e6e939a016ef930
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
``` sql
select A.id                 as 사원번호,
       A.last_name          as 이름,
       A.annual_income      as 연봉,
       A.position_name      as 직급명,
       record.time          as 입출입시간,
       record.region        as 지역,
       record.record_symbol as 입출입구분
from (select employee.id,
             employee.last_name,
             position.position_name,
             salary.annual_income
      from department
               join manager on department.id = manager.department_id
               join position on position.id = manager.employee_id
               join employee on employee.id = manager.employee_id
               join salary on salary.id = manager.employee_id
      where department.note = 'active'
        and manager.end_date = '9999-01-01'
        and position.end_date = '9999-01-01'
        and salary.end_date = '9999-01-01'
      order by salary.annual_income desc limit 5) as A
         join record on employee_id = A.id
where record.record_symbol = 'O';
```
---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
