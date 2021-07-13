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
- 목푯값 설정 (latency, throughput, 부하 유지기간)
   - DAU : 통계수치를 바탕으로 I사 하루 사용량 예상: 3만명 가량으로 예상
   - 사용자가 보통 5번씩 사용한다고 가정
   - 1일 총 접속수: 3만명 * 5 = 15만회
   - 150,000 / 86400 = 2rps
   - 1일 최대 rps: 2 * 100 / 10 = 20 rps
   - 사용자가 1분 내외로 사용한다고 가정.
- 부하 테스트 시 저장될 데이터 건수 및 크기
   - 준비 된 운영 DB 데이터

- 개선 전 테스트 결과
   * docs/beforeTest.txt

- 개선 후 테스트 결과
   * docs/afterTest.txt

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* nginx
   - gzip compress 적용
   - css|js|gif|png|jpg|jpeg에 대해 nginx 캐시 적용
   - http 2.0 적용
   
* redis cache
   - private 서버에 redis를 띄움
   - prod profile에서는 private 서브넷의 redis에 접속
   - 많은 엔티티를 조회 후 조합해서 응답을 만들어내는 findPath메서드에 캐시를 적용해서 redis에 저장

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
  - Coding as a Hobby 와 같은 결과를 반환하세요.
    ```
    CREATE INDEX idx_programmer_hobby ON subway.programmer(hobby);
    ```
    ```
    select
    hobby,
    ((count(hobby)/(select count(hobby) from subway.programmer)) * 100) as percentage
    from subway.programmer
    group by hobby
    order by hobby desc;
    ```

  - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
    - 조회
    ```
    CREATE INDEX idx_covidHobby_p ON subway.covid(programmer_id, hospital_id);
    ```
    - covid - pk 생성
    ```
    ALTER TABLE subway.covid ADD CONSTRAINT covid_PK PRIMARY KEY (id);
    ```
    - programmer - pk 생성
    ```
    ALTER TABLE subway.programmer ADD CONSTRAINT programmer_PK PRIMARY KEY (id);
    ```
    - hospital - pk 생성
    ```
    ALTER TABLE subway.hospital ADD CONSTRAINT hospital_PK PRIMARY KEY (id);
    ```
    - hospital_id 타입 변경
    ```
    ALTER TABLE subway.covid MODIFY COLUMN hospital_id bigint(11) NULL;
    ```
    - 인덱스(Non Clustered Index) 적용전 174ms에서 적용 후 14ms 개선, pk(Clustered Index) 적용 후 9ms로 개선
    ```sql
    SELECT
    p.id as programer_id,
    c.id as covid_id,
    h.name as hospital_name
    FROM
    subway.programmer p
    INNER JOIN  subway.covid c on p.id = c.programmer_id
    INNER JOIN  subway.hospital h on h.id  = c.hospital_id
    where
    p.member_id = c.member_id
    and c.hospital_id = h.id;
    ```

  - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
     ```sql
    select
    c.id as covid_id,
    h.name as hospital_name,
    u.hobby,
    u.dev_type,
    u.years_coding
    from subway.covid c
    INNER JOIN subway.hospital h ON c.hospital_id = h.id
    INNER JOIN
    (
    select
    p.id,
    p.hobby,
    p.dev_type,
    p.years_coding
    from subway.programmer p
    INNER JOIN subway.member m ON p.member_id = m.id
    where
    (p.hobby = 'yes' or years_coding = '0-2 years') and p.member_id = m.id
    ) u ON u.id = c.programmer_id
    where c.hospital_id = h.id
    and u.id = c.programmer_id;
    ```

  - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  - 인덱스 생성
  ```
  CREATE INDEX `idx_user_Country`  ON `subway`.`programmer` (Country);
  CREATE INDEX `idx_user_Age`  ON `subway`.`member` (age);
  ```
  ```sql
  select stay, count(p.id) cnt
    from subway.hospital h
    inner join subway.covid c on h.id = c.hospital_id
    inner join subway.programmer p on c.programmer_id = p.id
````
CREATE INDEX idx_programmer_hobby ON subway.programmer(hobby);
````
- 기존 속도 475ms에서 93ms로 개선
````
select
hobby,
((count(hobby)/(select count(hobby) from subway.programmer)) * 100) as percentage
from subway.programmer
group by hobby
order by hobby desc;
````    inner join subway.member m on c.member_id = m.id
    where h.name ='서울대병원' and p.country = 'India' AND m.age between 20 AND 29
    group by c.stay;
  ```

  - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
    - 인덱스 생성
    ```
    CREATE INDEX `idx_user_Hospital_Id`  ON `subway`.`covid` (hospital_id);
    ```
    ```sql
    select
       p.exercise,
       count(p.id) member_cnt
    from
       (select id from subway.member where age between 30 and 39) m,
       (select member_id, hospital_id, programmer_id from subway.covid) c,
       (select id from subway.hospital where name = '서울대병원') h,
       (select id, exercise from subway.programmer) p
    where m.id = c.member_id
    and c.hospital_id = h.id
    and c.programmer_id = p.id
    group by
       p.exercise;
    ```
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
- https://www.reversalspring.p-e.kr/favorites