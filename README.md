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

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
    ```
  select s.사원번호, s.이름, g.연봉, j.직급명, sr.입출입시간, sr.지역, sr.입출입구분
  from (select g.사원번호, g.연봉
        from tuning.급여 g
        inner join tuning.부서관리자 bm on g.사원번호 = bm.사원번호
        where bm.종료일자 = '9999-01-01'
        and g.종료일자 = '9999-01-01'
        order by 연봉 desc
        limit 5) as g
  inner join (select 사원번호, 이름 from tuning.사원) as s on s.사원번호 = g.사원번호
  inner join (select 사원번호, 입출입시간, 지역, 입출입구분 from tuning.사원출입기록 where 입출입구분 = 'O') as sr on s.사원번호 = sr.사원번호
  inner join (select 사원번호, 직급명 from tuning.직급 where 종료일자 = '9999-01-01') as j on j.사원번호 = s.사원번호
    ```
### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요.
   - 적용 인덱스
     ```
     
     -- subway.member
     ALTER TABLE subway.member add constraint PK_MEMBER__ID primary key (`id`);
     CREATE INDEX `IDX_MEMBER__AGE` ON subway.member (`age`);
     
     
     -- subway.covid     
     ALTER TABLE subway.covid add constraint PK_COVID__ID primary key (`id`);
     CREATE INDEX `idx_covid_programmer_id` ON subway.covid (`programmer_id`);
     CREATE INDEX `idx_covid_member_id` ON subway.covid  (`member_id`);
     CREATE INDEX `idx_covid_hospital_id` ON subway.covid  (`hospital_id`);
  
     -- subway.programmer
     ALTER TABLE subway.programmer add constraint PK_PROGRAMMER__ID primary key (`id`); 
     CREATE INDEX `IDX_PROGRAMMER__MEMBER_ID` ON subway.programmer (`member_id`);
     CREATE INDEX `IDX_PROGRAMMER__COUNTRY` ON subway.programmer (`country`);
     CREATE INDEX `idx_programmer_hobby_student_years_coding` on subway.programmer (`hobby`,`student`,`years_coding`)  
     
  
     ALTER TABLE subway.hospital add constraint PK_HOSPITAL__ID primary key (`id`);
     CREATE INDEX `idx_hospital_name_id` ON subway.hospital (`name`,`id`)
     ```
- Coding as a Hobby 와 같은 결과를 반환하세요. 
  - hobby 인덱스 삭제하고 idx_programmer_hobby_student_years_coding 를 태웠습니다 
      ```
      select concat(round(count(case when hobby = 'Yes' then 1 end) / count(*) * 100, 1), '%') yes,
      concat(round(count(case when hobby = 'No' then 1 end) / count(*) * 100, 1), '%') No
      from subway.programmer;
      ```
  
- 프로그래머별로 해당하는 병원 이름을 반환하세요.
  - programmer id로 Range Index 를 태웠습니다. 
  - 페이징 쿼리 적용하여 Fetch를 줄였습니다.
    ```
    select c.id, h.name
    from (select id, hospital_id, programmer_id from subway.covid) c     
    inner join (select id from subway.programmer) p
    on c.programmer_id = p.id
    inner join (select id, name from subway.hospital) h 
    on c.hospital_id = h.id
    where p.id >= 0
    limit 0, 1000;
    ```

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
   - 이전 작업은 (취미 and 학생) or (주니어 and no 학생) 조건으로 진행 했었습니다.
   - 다른 곳에 피드백하신 쿼리조건 참고하여 다시 수정하였습니다.
   - 한국어 참 어렵네요..;;
   - hobby, student, years_coding 복합 인덱스 적용
   - 페이징쿼리 적용
     ```
     select c.id, h.name, p.hobby, p.dev_type, p.years_coding, student
     from (select id, hobby, student, dev_type, years_coding from subway.programmer
           where hobby = 'Yes' and (years_coding = '0-2 years' or student like 'Yes%')) p
     inner join (select id, programmer_id, hospital_id from subway.covid) c
     on p.id = c.programmer_id
     inner join (select id, name from subway.hospital) h
     on h.id = c.hospital_id  
     order by p.id 0, limit 100;
     ```

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
  - File Sort 제거하였습니다.
      ```
     select c.stay, count(*) count
     from (select id from subway.member where age between 20 and 29)  m
     inner join (select id from subway.programmer where country = 'India') p 
     on m.id = p.id
     inner join (select id, hospital_id, stay from subway.covid) c 
     on p.id = c.id
     inner join (select id from subway.hospital where id = 9) h 
     on h.id = c.hospital_id
     group by c.stay
     order by null;
      ```
  
- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
  - File Sort 제거하였습니다. 
      ```
    select p.exercise, count(*) count
    from (select id, member_id, hospital_id, programmer_id from subway.covid) c   
    inner join (select id from subway.hospital where id = 9) h
    on c.hospital_id = h.id
    inner join (select id, exercise from subway.programmer) p
    on c.programmer_id  = p.id
    inner join (select id, age from subway.member where age between 30 and 39) m
    on c.member_id = m.id
    group by p.exercise
    order by null;
      ```
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
