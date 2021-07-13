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
   - k6Test 디렉토리 하위에 결과 파일 첨부했습니다.
     - 기존 설정으로 테스트 결과 3종
     - Reverse-proxy 개선 후 테스트 결과 3종
     - redis-cache적용 후 테스트 결과 3종
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   - 이전 단계에서 Reverse-proxy 압축 설정으로 성능 개선을 확인했었고, 추가적으로 캐싱 설정을 추가했습니다. 그 결과 압축 설정으로 큰 성능 개선이 이루어짐을 확인했고 캐싱은 비교적 성능 개선점이 나타나지는 않았습니다.
   - 추가적으로 was에 redis 캐시를 적용하여 db 부하를 줄이는 작업을 진행했습니다.
   - 동일한 부하 테스트에 대해 was캐싱 적용이후 성공하는것을 확인 할 수 있었습니다.

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

   쿼리당 인덱스를 모두 초기화 한 후 다시 진행하는 방식으로 수행했습니다.

   인덱스가 적용되지 않는 컬럼의 설정은 varchar(255) 로 수정

   조건이 들어간 필드에 대해 index를 추가하고 결과를 확인했습니다.

   - Codding as a Hobby 와 같은 결과 반환

     ```mysql
     CREATE INDEX `idx_programmer_hoby`  ON `subway`.`programmer` (hobby);
     
     select hobby, ROUND((count(id)/(select count(id) from `subway`.`programmer`) * 100),1) as 'HobbyCount' from `subway`.`programmer` Group By hobby;
     ```

   - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name

     ```mysql
     create index idx_covid_pid_hid on `subway`.`covid` (programmer_id, hospital_id);
     
     select p.id,h.name
     from `subway`.`programmer` as p
     inner join `subway`.`covid` as c on p.id = c.programmer_id
     inner join `subway`.`hospital` as h on c.hospital_id = h.id;
     ```

   - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

     ```mysql
     CREATE INDEX `idx_programmer_hobby` ON `subway`.`programmer` (hobby);
     CREATE INDEX `idx_programmer_hsy` ON `subway`.`programmer` (hobby,student,years_coding);
     CREATE INDEX `idx_covid_programmerId` ON `subway`.`covid` (programmer_id);
     
     
     select jp.id, h.name, jp.hobby, jp.dev_type, jp.years_coding 
     from (select p.id, p.hobby, p.dev_type, p.years_coding from `subway`.`programmer` as p where p.hobby = 'Yes' and (p.student like 'Yes%' or p.years_coding like '0-2%')) as jp
      inner join `subway`.`covid` as c on c.programmer_id = jp.id
      inner join `subway`.`hospital` as h on c.hospital_id = h.id;
     ```

   - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

     ```mysql
     CREATE INDEX `idx_member_age` ON `subway`.`member` (age);
     CREATE INDEX `idx_programmer_cm` ON `subway`.`programmer` (country);
     CREATE INDEX `idex_covid_hid` ON `subway`.`covid` (hospital_id,stay);
     
     select c.stay, count(*) from (select id from `subway`.`member` where age >= 20 and age <30) as mem
     inner join (select id, member_id,country from `subway`.`programmer` as pr where pr.country = 'India') as p on p.member_id = mem.id
     inner join `subway`.`covid` as c on c.programmer_id = p.id
     inner join (select id from `subway`.`hospital` as h where h.name = '서울대병원') as h on h.id = c.hospital_id
     group by stay;
     ```

   - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

     ```mysql
     CREATE INDEX `idx_member_age` ON `subway`.`member` (age);
     CREATE INDEX `idx_programmer_ex` ON `subway`.`programmer` (exercise);
     CREATE INDEX `idex_covid_hid` ON `subway`.`covid` (hospital_id);
      
     select p.exercise, count(p.id) from (select id from `subway`.`member` where age >= 30 and age <40) as mem
     inner join (select * from `subway`.`programmer` as pr) as p on p.member_id = mem.id
     inner join `subway`.`covid` as c on c.programmer_id = p.id
     inner join (select id from `subway`.`hospital` as h where h.name = '서울대병원') as h on h.id = c.hospital_id
     group by exercise
     order by null
     ```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

   yzzzzun.o-r.kr
   id/pw : test@test.com / test
