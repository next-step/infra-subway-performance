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

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- A. 쿼리 최적화
  <details>
    <summary>(1) 쿼리 작성만으로 1s 이하로 반환 - 573 ms</summary>
    <div>
  
      ```sql
      SELECT 연봉상위5위.*, 사원출입기록.입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분
      FROM (
          SELECT 사원.사원번호, 사원.이름, 급여.연봉, 직급.직급명
          FROM tuning.부서 AS 부서,
              tuning.부서관리자 AS 부서관리자,
              tuning.사원 AS 사원,
              tuning.직급 AS 직급,
              tuning.급여 AS 급여
          WHERE upper(부서.비고)='ACTIVE'
              AND 부서.부서번호=부서관리자.부서번호
              AND now() <= 부서관리자.종료일자
              AND 부서관리자.사원번호=사원.사원번호
              AND 사원.사원번호=직급.사원번호    
              AND now() <= 직급.종료일자
              AND 사원.사원번호=급여.사원번호
              AND now() <= 급여.종료일자
              ORDER BY 급여.연봉 DESC
              LIMIT 5
      ) AS 연봉상위5위, tuning.사원출입기록 AS 사원출입기록
      WHERE 연봉상위5위.사원번호=사원출입기록.사원번호
      AND 사원출입기록.입출입구분='O';
      ```

    </div>
  </details>

  <details>
    <summary>(2) 인덱스 설정을 추가하여 50 ms 이하로 반환 - 26 ms</summary>
    <div>

      ```sql
      CREATE INDEX I_종료일자 ON tuning.부서관리자 (종료일자);
      CREATE INDEX I_종료일자 ON tuning.직급 (종료일자);
      CREATE INDEX I_종료일자 ON tuning.급여 (종료일자);
      CREATE INDEX I_입출입구분 ON tuning.사원출입기록 (입출입구분);
      CREATE INDEX I_사원번호 ON tuning.사원출입기록 (사원번호);
      ```

    </div>
  </details>
  
- B. 인덱스 설계
  - 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  ```sql
  -- base --
  ALTER TABLE subway.covid ADD primary key(id);
  ALTER TABLE subway.hospital ADD primary key(id);
  ALTER TABLE subway.programmer ADD primary key(id);
  ```

  <details>
    <summary>Coding as a Hobby 와 같은 결과를 반환하세요. - 81 ms</summary>
    <div>
  
      ```sql
      CREATE INDEX I_hobby ON subway.programmer (hobby);

      SELECT hobby,
        round(100 * count(*) / (SELECT count(*) FROM subway.programmer), 1)
      FROM subway.programmer
      GROUP BY hobby;
      ```
  
    </div>
  </details>
  <details>
    <summary>프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name) - 41 ms</summary>
    <div>

      ```sql
      CREATE INDEX I_programmer_id ON subway.covid (programmer_id);
      CREATE INDEX I_hospital_id ON subway.covid (hospital_id);

      SELECT programmer.id, covid.id, hospital.name
      FROM subway.hospital
        INNER JOIN subway.covid ON hospital.id=covid.hospital_id
        INNER JOIN subway.programmer ON covid.programmer_id=programmer.id;
      ```
  
    </div>
  </details>
  <details>
    <summary>프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding) - 21 ms</summary>
    <div>

      ```sql
      CREATE INDEX I_years_coding ON subway.programmer (years_coding);

      SELECT covid.id, hospital.name, programmer.hobby, programmer.dev_type, programmer.years_coding
      FROM subway.programmer
          INNER JOIN subway.covid ON programmer.id=covid.programmer_id
          INNER JOIN subway.hospital ON covid.hospital_id=hospital.id
      WHERE hobby='yes' AND (student LIKE 'yes%' OR years_coding='0-2 years')
      ORDER BY programmer.id;
      ```

    </div>
  </details>
  <details>
    <summary>서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay) - 72 ms</summary>
    <div>

      ```sql
      ALTER TABLE subway.hospital MODIFY name varchar(255);
      CREATE INDEX I_name ON subway.hospital (name);
      CREATE INDEX I_country ON subway.programmer (country);
      CREATE INDEX I_member_id ON subway.covid (member_id);
      CREATE INDEX I_member_age ON subway.member (age);

      SELECT covid.stay, count(*)
      FROM (SELECT id FROM subway.programmer WHERE country='india') AS indian
          INNER JOIN subway.covid
              ON indian.id=covid.programmer_id
          INNER JOIN (SELECT id FROM subway.hospital WHERE name='서울대병원') AS seoul
              ON covid.hospital_id=seoul.id
          INNER JOIN (SELECT id FROM subway.member WHERE age BETWEEN 20 AND 29) AS member
              ON covid.member_id=member.id
      GROUP BY covid.stay;
      ```

    </div>
  </details>
  <details>
    <summary>서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise) - 85 ms</summary>
    <div>

      ```sql
      SELECT programmer.exercise, count(*)
      FROM subway.programmer
          INNER JOIN subway.covid
              ON programmer.id=covid.programmer_id
          INNER JOIN (SELECT id FROM subway.hospital WHERE NAME='서울대병원') AS seoul
              ON covid.hospital_id=seoul.id
          INNER JOIN (SELECT id FROM subway.member WHERE age BETWEEN 30 AND 39) AS member
              ON covid.member_id=member.id
      GROUP BY programmer.exercise;
      ```

    </div>
  </details>

3. 페이징 쿼리를 적용한 API endpoint를 알려주세요
