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
* 성능 개선 전 : k6/before 
* 성능 개선 후 : k6/after

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* reverse-proxy 개선 : gzip 압축 > proxy cache 적용 > http/2 적용
* application 개선 : redis 캐시 적용

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
* https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0df73cc8d06748862

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```
* /auto_scaling 폴더에 캡처 첨부했습니다.

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* /auto_scaling/k6 폴더에 캡처 첨부했습니다.
  (smoke_auto_scaling, load_auto_scaling, stress_auto_scaling)
* smoke  결과 : http_req_duration 28.66 ms -> 21.89 ms
* load   결과 : http_req_duration 11.04 ms -> 10.89 ms
* stress 결과 : http_req_duration 10.35 ms -> 10.03 ms

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
* 쿼리 : /query_optimizing/query.sql
* 결과 : /query_optimizing/query_result.png

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
> Coding as a Hobby 와 같은 결과를 반환하세요
>> 1. programmer table id primary key 설정 
>> 2. programmer table hobby 인덱스 추가 
>> * 결과 : query_index/1.coding_as_a_hobby_result.png 추가
>> * 쿼리 
>> <pre>select hobby, ( round(count(hobby) / (select count(id) from programmer) * 100, 1) ) as respondents 
>> from programmer p 
>> group by hobby order by hobby desc;</pre>

> 프로그래머별로 해당하는 병원 이름을 반환하세요.
>> * 결과 : /query_index/2.hospital_by_programmer.png 추가 
>> * 쿼리
>> <pre>select c.id, h.name 
>> from covid c, hospital h, programmer p
>> where h.id = c.hospital_id and p.id = c.programmer_id; </pre>

> 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
>> 1. hospital table id pk 추가
>> 2. covid table programmer id index 추가
>> * 결과 : /query_index/3. student_or_junior_programmer_hospital.png 추가
>> * 쿼리
>> <pre>select c.id, (select h.name from hospital h where h.id = c.hospital_id) as hospital_name, user.hobby, user.student, user.years_coding, user.dev_type 
>> from programmer user, covid c
>> where user.hobby ='yes'
>> and ( user.student like 'yes%' or user.years_coding like '0-2%' )
>> and c.programmer_id = user.id
>> order by user.id;</pre>

> 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
>> 1. member id pk 추가 / member age index 추가
>> 2. programmer country index 추가
>> 3. covid member_id index 추가
>> * 결과 : /query_index/4.20_29_age_stay_in_seoul_univ_hospital.png 추가
>> * 쿼리
>> <pre>
>> select c.stay, count(c.stay) as count_stay
>> from member m, programmer p, covid c
>> where m.id = p.id
>> and m.age between 20 and 29
>> and p.country = 'india'
>> and p.id = c.member_id
>> and h.id = c.hospital_id
>> and h.name = '서울대병원'
>> group by c.stay;
>> </pre>

> 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
>> 1. programmer member_id index 추가
>> 2. covid hospital_id index 추가
>> 3. hospital name index 추가
>> * 결과 : /query_index/5.30_39_age_exercise_in_seoul_univ_hospital.png 추가
>> * 쿼리
>> <pre>
>> select p.exercise, count(*)
>> from member m, covid c, hospital h, programmer p
>> where m.id = c.member_id
>> and m.age between 30 and 39
>> and h.id = c.hospital_id
>> and h.name = '서울대병원'
>> and m.id = p.member_id
>> group by p.exercise;
>> </pre>

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
