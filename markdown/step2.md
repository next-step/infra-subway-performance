# 🚀 2단계 - 조회 성능 개선하기

## 요구사항

- [X] 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
  - 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
    - Coding as a Hobby 와 같은 결과를 반환하세요.

      ```SQL
      SELECT
         hobby,
         ROUND((COUNT(id)/(SELECT COUNT(id) FROM programmer)*100), 1) 'HobbyCount'
      FROM programmer 
      GROUP BY hobby;      
      ```

      - 수정전 -> 357ms
      - 개선 -> PK 설정과 GROUP BY에 사용되는 hobby에 인덱스 걸기
      - programmer 테이블의 id 컬럼 PK 적용
      - programmer 테이블의 hobby 컬럼 인덱스 적용
      - 수정후 -> 58ms

    - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

      ```SQL
      SELECT 
         p.id `programmer.id`,
         h.name `병원 이름`
      FROM programmer p
            JOIN covid c
            ON p.id = c.programmer_id
               JOIN hospital h 
               ON c.hospital_id = h.id;
      ```

      - 수정전 -> 42ms
      - 개선 -> 개선하기전 쿼리도 100ms 안팎이나 성능 향상을 위해 id 컬럼 PK 적용 및 병원이름 varchar 변경 후 인덱스 적용
      - covid 테이블의 id 컬럼 PK 적용
      - hospital 테이블의 id 컬럼 PK 적용
      - hospital 테이블의 name 컬럼 varchar 변경 후 인덱스 적용
      - 수정후 -> 21ms

    - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

      ```SQL
      SELECT 
         A.id `user.id`,
         B.name `병원 이름`
      FROM (SELECT id FROM programmer WHERE hobby = 'Yes' AND (student in ('Yes, full-time', 'Yes, part-time') OR years_coding='0-2 years')) A
         JOIN (SELECT c.programmer_id, h.name FROM covid c JOIN hospital h ON c.hospital_id = h.id) B
         ON A.id = B.programmer_id;
      ```

      - 수정전 -> 103ms
      - 개선 -> 서브쿼리의 조건에 인덱스를 적용
      - programmer 테이블의 hobby -> student -> years_coding 순서로 인덱스 설정
      - covid 테이블의 programmer_id 인덱스 설정
      - covid 테이블의 hospital_id 인덱스 설정
      - 수정후 -> 43ms

    - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

      ```SQL
      SELECT 
         stay `머문 기간`,
         COUNT(B.member_id) `20대 인도환자수`
      FROM (SELECT id FROM member WHERE age BETWEEN 20 AND 29) A
         JOIN (SELECT member_id FROM programmer WHERE country = 'India') B
         ON A.id = B.member_id
            JOIN (SELECT c.id, c.member_id, hospital_id, stay FROM covid c 
               JOIN (SELECT id FROM hospital WHERE name = '서울대병원') D
               ON c.hospital_id = D.id) C
            ON A.id = C.member_id
      GROUP BY stay
      ORDER BY null;
      ```

      - 수정전 -> 11203ms
      - 개선 -> 서브쿼리들의 조건에 해당되는 컬럼에 인덱스 적용, ORDER BY null 을통해 filesort 안하게
      - member 테이블의 age 컬럼 인덱스 적용
      - programmer 테이블의 member_id 컬럼 인덱스 적용
      - hospital 테이블의 name 유니크 적용
      - ORDER BY null 추가
      - 수정후 -> 95ms

    - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

      ```SQL
      SELECT 
         exercise, 
         COUNT(C.id) `운동횟수`
      FROM (SELECT id FROM member WHERE age between 30 and 39) A
         JOIN (SELECT member_id, hospital_id, programmer_id FROM covid) B
         ON A.id = B.member_id
            JOIN (SELECT id, exercise FROM programmer) C
            ON B.programmer_id = C.id
               JOIN (SELECT id FROM hospital WHERE name = '서울대병원') D
               ON B.hospital_id = D.id
      group by exercise
      order by null;
      ```

      - 수정전 -> 61ms
      - 앞선 단계에서의 인덱스 설정으로 인해 추가설정 할 것은 ORDER BY null 만 추가함
      - 수정후 -> 56ms

- [X] 즐겨찾기 페이지에 페이징 쿼리를 적용
  - [X] 로그인한 사용자는 최근에 추가한 즐겨찾기만 관심이 있기에 한번에 5개의 즐겨찾기만 보고 싶다.
- [ ] 데이터베이스 이중화
