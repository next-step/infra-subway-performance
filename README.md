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

개선한 지하철 노선도 사이트 (신규) : https://www.yong2ss-me.kro.kr/

[개선전]  
    - load_before.png
    - smoke_before.png
    - stress_before.png
[개선후]
    - load_db.png
    - smoke_db.png
    - stress.db.png

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

-  Reverse Proxy 개선
    * gzip 압축
    * cache 설정
    * TLS, HTTP/2 설정

- WAS 성능 개선
    * redis cache 설정

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-006941f6235c7e381

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
    - resources/static/step2/cloudwatch/cloud-watch-capture.png
```sh
$ stress -c 2
```
3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
    - resources/static/step2/k6/step2_load.png
    - resources/static/step2/k6/step2_smoke.png
    - resources/static/step2/k6/step2_stress.png

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요 구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

[sql]
- resources/static/step3/step3.sql

[실행결과 data]
- resources/static/step3/step3_result.png

[실행시간]
- resources/static/step3/step3_time.png

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    [Index 및 DDL 적용사항]
    - resources/static/step3/DDL
        - covid
        - hospital
        - member
        - programmer
        
    [Q1] Coding as a Hobby 와 같은 결과를 반환하세요.
        [sql]
            - resources/static/step4/Q1/sql1    
        [explain]
             - resources/static/step4/Q1/Q1_image
        [result]
             - resources/static/step4/Q1/Q1_result
     
    [Q2] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
        [sql]
            - resources/static/step4/Q1/sql1    
        [explain]
             - resources/static/step4/Q1/Q1_image
        [result]
             - resources/static/step4/Q1/Q1_result
    
    [Q3] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
        [sql]
            - resources/static/step4/Q1/sql1    
        [explain]
             - resources/static/step4/Q1/Q1_image
        [result]
             - resources/static/step4/Q1/Q1_result
                 
    [Q4] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
        [sql]
            - resources/static/step4/Q1/sql1    
        [explain]
             - resources/static/step4/Q1/Q1_image
        [result]
             - resources/static/step4/Q1/Q1_result
             
    [Q5] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
        [sql]
            - resources/static/step4/Q1/sql1    
        [explain]
             - resources/static/step4/Q1/Q1_image
        [result]
             - resources/static/step4/Q1/Q1_result
             
---

### 추가 미션
 
1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
