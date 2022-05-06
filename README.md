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

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- [x] codding as a hobby 와 같은 결과 반환
  ```sql
  set @rowCount = (select count(hobby) from programmer);
  select hobby,  round(COUNT( * ) / @rowCount * 100, 1) AS percentage from programmer
  group by hobby DESC;
  ```
  hobby index로 지정 - full index scan으로 전환
  ```sql
  CREATE INDEX `idx_programmer_hobby`  ON `subway`.`programmer` (hobby) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
  ```
  실행 결과 966ms -> 64ms

- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  ```sql
  select c.id, h.name from programmer as p
  inner join covid as c on c.programmer_id = p.id
  inner join hospital as h on h.id = c.hospital_id;
  ```

  programmer, hospital id 키 unique 및 pk 지정
  ```sql
  ALTER TABLE `subway`.`hospital` 
  CHANGE COLUMN `id` `id` INT(11) NOT NULL ,
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
  
  ALTER TABLE `subway`.`programmer` 
  CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
  ```

  covid programmer_id, hospital_id 복합 인덱스 키 지정
  ```sql
    CREATE INDEX `idx_covid_programmer_id_hospital_id`  ON `subway`.`covid` (programmer_id, hospital_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
  ```

  실행 결과 292ms -> 10ms

- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  ```sql
  select c.id, h.name, hobby as user_hobby, p.dev_type, p.years_coding from covid as c
   inner join hospital h on c.hospital_id = h.id
   inner join 
      (select p.id, p.hobby, p.dev_type, p.years_coding from programmer as p where (hobby = 'Yes' and student = 'Yes') or years_coding = '0-2 years') as p 
          ON p.id = c.programmer_id;
  ```
  ```sql
  create index programmer_hobby_student_index
      on programmer (hobby, student);
  create index programmer_years_coding_index
      on programmer (years_coding);
  create index hospital_id_index
      on hospital (id);
  create index covid_hospital_id_index
      on covid (hospital_id);
  ```
  실행 결과 928ms -> 8ms

- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  ```sql
  select c.stay, COUNT(c.id) from covid as c
    inner join (select id from hospital where name = '서울대병원') as h on h.id = c.hospital_id
    inner join (select id, age from member where age in (20, 21, 22, 23, 24) or age in (25, 26, 27, 28, 29)) as m on m.id = c.member_id
    inner join (select id, country from programmer where country = 'india') as p on p.id = c.programmer_id
  group by c.stay
  ```
  
  ```sql
  create index hospital_name_index
      on hospital (name);
  create index member_age_index
      on member (age);
  create index programmer_country_index
      on programmer (country);
  ```

  실행 결과 1276ms -> 8ms

- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  ```sql
  select exercise, count(p.id) from programmer as p
    inner join covid c on c.programmer_id = p.id
    inner join (select id from hospital where name = '서울대병원') as h on h.id = c.hospital_id
    inner join (select id, age from member where age in (30, 31, 32, 33, 34) or age in (35, 36, 37, 38, 39)) as m on m.id = c.member_id
  group by p.exercise
  ```

  실행 결과 80ms

  
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
