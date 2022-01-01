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
    
    a. 쿼리 최적화
    - 쿼리 최적화 (1s 이하 반환)
        ```sql
        SELECT 상위연봉자.사원번호, 상위연봉자.이름, 상위연봉자.연봉, 상위연봉자.직급명, L.입출입구분, L.지역, L.입출입시간
        FROM (
                 SELECT A.사원번호, E.이름, S.연봉, R.직급명
                 FROM 부서 상위연봉자
                          INNER JOIN 부서관리자 A
                                     ON 상위연봉자.부서번호 = A.부서번호
                                         AND A.종료일자 = '9999-01-01'
                          INNER JOIN 급여 S
                                     ON A.사원번호 = S.사원번호
                                         AND S.종료일자 = '9999-01-01'
                          INNER JOIN 부서사원_매핑 M
                                     ON S.사원번호 = M.사원번호
                                         AND M.종료일자 = '9999-01-01'
                          INNER JOIN 사원 E
                                     ON M.사원번호 = E.사원번호
                          INNER JOIN 직급 R
                                     ON E.사원번호 = R.사원번호
                                         AND R.종료일자 = '9999-01-01'
                          WHERE 상위연봉자.비고 = 'ACTIVE'
                          ORDER BY S.연봉 DESC
                          LIMIT 5
        ) 상위연봉자
                 INNER JOIN 사원출입기록 L
                      ON L.사원번호 = 상위연봉자.사원번호
                                AND L.입출입구분 = 'O'
        ORDER BY 상위연봉자.연봉 DESC, L.지역;
        ```
        | 사원번호 | 이름 | 연봉 | 직급명 | 입출입구분 | 지역 | 입출입시간 |
        | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
        | 110039 | Vishwani | 106491 | Manager | O | a | 2020-09-05 20:30:07 |
        | 110039 | Vishwani | 106491 | Manager | O | b | 2020-08-05 21:01:50 |
        | 110039 | Vishwani | 106491 | Manager | O | d | 2020-07-06 11:00:25 |
        | 111133 | Hauke | 101987 | Manager | O | a | 2020-01-24 02:59:37 |
        | 111133 | Hauke | 101987 | Manager | O | b | 2020-05-07 16:30:37 |
        | 110114 | Isamu | 83457 | Manager | O | a | 2020-05-29 19:38:12 |
        | 110114 | Isamu | 83457 | Manager | O | b | 2020-09-03 01:33:01 |
        | 110114 | Isamu | 83457 | Manager | O | c | 2020-11-12 02:29:00 |
        | 110114 | Isamu | 83457 | Manager | O | d | 2020-04-25 08:28:54 |
        | 110567 | Leon | 74510 | Manager | O | a | 2020-10-17 19:13:31 |

    - 인덱싱 설정 (50ms 이 반환)
        ```sql
        CREATE INDEX 사원출입기록_사원번호_INDEX ON 사원출입기록 (사원번호);
        ```

    b. 인덱스 설계
    - Coding as a Hobby 와 같은 결과를 반환하세요.
        ```sql
        CREATE INDEX programmer_hobby_index on programmer (hobby);

        SELECT ROUND(SUM(IF(HOBBY = 'YES', 1, 0)) / COUNT(HOBBY) * 100, 1) AS Yes,
               ROUND(SUM(IF(HOBBY = 'NO', 1, 0)) / COUNT(HOBBY) * 100, 1) AS No
        FROM programmer;
        ```
        | Yes | No |
        | :--- | :--- |
        | 80.8 | 19.2 |

    - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
        ```sql
        CREATE INDEX hospital_id_index_2 ON hospital (id);
        CREATE INDEX covid_hospital_id_index ON covid (hospital_id);
        CREATE INDEX covid_member_id_index ON covid (member_id);
        CREATE INDEX programmer_member_id_index ON programmer (member_id);

        SELECT C.id, H.name
        FROM hospital H
                 INNER JOIN covid C
                                    ON H.id = C.hospital_id
                 INNER JOIN programmer P
                                    ON C.member_id = P.member_id;
        ```  
        | id   | name |
        |-----| :--- |
        | 1   | 고려대병원 |
        | 2   | 분당서울대병원 |
        | 3   | 경희대병원 |
        | 4   | 우리들병원 |
        | --- | --- |
        | 5   | 우리들병원 |
    - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
        ```sql
        CREATE INDEX hospital_id_index ON hospital (id);
        CREATE INDEX covid_hospital_id_index ON covid (hospital_id);
        CREATE INDEX covid_member_id_index ON covid (member_id);
        CREATE INDEX programmer_member_id_index ON programmer (member_id);

        SELECT C.id, H.name, P.hobby, P.student, P.dev_type, P.years_coding
        FROM hospital H
                 INNER JOIN covid C
                            ON H.id = C.hospital_id
                 INNER JOIN programmer P
                            ON C.member_id = P.member_id
        WHERE P.hobby = 'YES'
          AND (P.years_coding = '0-2 years' OR P.student like 'YES%')
        ORDER BY P.member_id;
        ```
        | id | name | hobby | student | dev_type | years_coding |
        | :--- | :--- | :--- | :--- | :--- | :--- |
        | 1 | 고려대병원 | Yes | No | Full-stack developer | 3-5 years |
        | 2 | 분당서울대병원 | Yes | No | Database administrator;DevOps specialist;Full-stack developer;System administrator | 30 or more years |
        | 3 | 경희대병원 | Yes | No | Engineering manager;Full-stack developer | 24-26 years |  
    - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
        ```sql
        CREATE INDEX hospital_id_index ON hospital (id);
        CREATE INDEX covid_hospital_id_index ON covid (hospital_id);
        CREATE INDEX covid_member_id_index ON covid (member_id);
        CREATE INDEX covid_stay_index ON covid (stay);
        CREATE INDEX programmer_country_index ON programmer (country);
        CREATE INDEX programmer_member_id_index ON programmer (member_id);
        CREATE INDEX member_id_index ON member (id);
        CREATE INDEX member_age_index ON member (age);
        
        SELECT C.stay `병원이 머문 기간`, count(M.id) `환자 수`
        FROM hospital H
                 INNER JOIN covid C
                            ON H.id = C.hospital_id
                                AND H.name = '서울대병원'
                 INNER JOIN programmer H
                            ON C.member_id = H.member_id
                                AND H.country = 'india'
                 INNER JOIN member M
                            ON C.member_id = M.id
                                AND M.age between 20 AND 29
        GROUP BY C.stay;
        ```  
        | 병원에 머문 기간 | 환자 수 |
        | :--- | :--- |
        | 0-10 | 3 |
        | 11-20 | 25 |
        | 21-30 | 30 |
        | 31-40 | 18 |
        | 41-50 | 2 |
        | 51-60 | 17 |
        | 71-80 | 6 |
        | 81-90 | 1 |
        | 91-100 | 1 |
        | More than 100 Days | 2 |
    - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
        ```sql
        CREATE INDEX covid_hospital_id_index ON covid (hospital_id);
        CREATE INDEX covid_member_id_index ON covid (member_id);
        CREATE INDEX programmer_member_id_index ON programmer (member_id);
        CREATE INDEX programmer_exercise_index ON programmer (exercise);

        SELECT P.exercise, count(M.id)
        FROM hospital H
                 INNER JOIN covid C
                            ON H.id = C.hospital_id
                                AND H.name = '서울대병원'
                 INNER JOIN programmer P
                            ON C.member_id = P.member_id
                 INNER JOIN member M
                            ON C.member_id = M.id
                                AND M.age between 30 AND 39
        GROUP BY P.exercise;
        ```
        | 운동 횟수 | 환자 수 |
        | :--- | :--- |
        | 1 - 2 times per week | 171 |
        | 3 - 4 times per week | 113 |
        | Daily or almost every day | 91 |
        | I don't typically exercise | 223 |
        | NA | 219 |

3. 페이징 쿼리를 적용한 API endpoint를 알려주세요
