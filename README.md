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

### A. 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.

(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

- 익덱스 적용전 쿼리
    - 개선전 속도 430~ms
    - 개선후 적용 후 속도 50~60ms
    - 개선 내용
        - `create index 사원출입기록_사원번호_index on 사원출입기록 (사원번호)`
        - 10배 정도 속도 개선효과를 볼수 있었습니다.

```text
# 쿼리
SELECT
    사원출입기록.사원번호,
    사원.이름,
    상위사원.연봉,
    직급.직급명,
    사원출입기록.지역,
    사원출입기록.입출입구분,
    사원출입기록.입출입시간
FROM 사원출입기록
    JOIN
        (
            SELECT
                급여.사원번호,
                급여.연봉
            FROM 급여
                JOIN 부서관리자 on 급여.사원번호 = 부서관리자.사원번호
                JOIN 부서 ON 부서.부서번호 = 부서관리자.부서번호
            WHERE
                부서.비고 = 'Active'
              AND now() BETWEEN 급여.시작일자 and 급여.종료일자
              AND now() BETWEEN 부서관리자.시작일자 and 부서관리자.종료일자
            ORDER BY 급여.연봉
            DESC LIMIT 5
        ) AS 상위사원
    ON
        사원출입기록.사원번호 = 상위사원.사원번호
    JOIN
        직급
    ON
        직급.사원번호 = 상위사원.사원번호 AND now() BETWEEN 직급.시작일자 AND 직급.종료일자
    JOIN
        사원
    ON
        사원.사원번호 = 상위사원.사원번호
    WHERE
        사원출입기록.입출입구분 = 'O'
    ORDER BY 상위사원.연봉 DESC, 사원출입기록.지역;

```

### B. 인덱스 설계

[X] Coding as a Hobby 와 같은 결과를 반환하세요

- 개선전 속도 : 370ms
- 개선 후 속도 : 85ms
- 개선 내용
    - `pk` 설정과, `hobby 컬럼`에 인덱스를 추가해여 개선하였습니다.
    - `create index programmer_hobby_index on programmer (hobby)`
    - `alter table programmer add constraint programmer_pk  primary key (id)`

```text
# 쿼리
SELECT hobby,
       ROUND((COUNT(*) / (SELECT COUNT(*) FROM programmer)) * 100, 1) AS 퍼센트
FROM programmer
GROUP BY hobby;
```

[X] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

- 개선전 속도 : 60ms ~
- 개선후 속도 : 35ms ~
- 개선 내용
    - `covid` 테이블 `hospital_id` 컬럼 인덱스 생성
        - `create index covid_hospital_id_index on covid (hospital_id)`
    - `hospital` 테이블 `ID` PK 지정
        - `alter table hospital  add constraint hospital_pk  primary key (id)`

```text
SELECT 코비드.id, 병원.name
FROM covid 코비드
         JOIN hospital 병원
              ON 코비드.hospital_id = 병원.id;

```

[X] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name,
user.Hobby, user.DevType, user.YearsCoding)

- 개선전 속도 : 3.5s ~
- 개선후 속도 : 35ms ~
- 개선 내용
- `covid` 테이블의 `programmer_id` 인덱스 생성
- `create index covid_programmer_id_index  on covid (programmer_id)`
- `create index programmer_student_index on programmer (student)`
- `create index programmer_years_coding_index on programmer (years_coding)`

```text
SELECT covid.id,
       hospital.name,
       programmer.Hobby,
       programmer.dev_type,
       programmer.years_coding
FROM programmer
         JOIN covid ON programmer.id = covid.programmer_id
         JOIN hospital on covid.hospital_id = hospital.id
WHERE programmer.hobby = 'Yes'
  AND (programmer.student LIKE 'Yes%' OR programmer.years_coding = '0-2 years')
ORDER BY programmer.id;
```

[X] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

- 개선전 속도 : 120ms ~
- 개선후 속도 : 68ms ~
- 개선 내용
    - `programmer.country` 인덱스 추가 하였습니다.
    - `alter table programmer add constraint programmer_pk primary key (id);`
    - `create index covid_hospital_id_member_id_programmer_id_index  on covid (hospital_id, member_id, programmer_id);`
    - `alter table hospital add constraint hospital_pk primary key (id)`
    - `alter table hospital modify name varchar(255) null`
    - `create index hospital_name_index on hospital (name)`

```text
SELECT
    covid.stay, count(*)
FROM
     covid
JOIN member m on m.id = covid.member_id
JOIN hospital h on h.id = covid.hospital_id
JOIN programmer p on p.id = covid.programmer_id
WHERE
    h.name='서울대병원'
    AND p.country='india'
    AND m.age between 20 and 29
GROUP BY covid.stay;
```

[X] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

- 속도 : 80ms ~
- 이미 인덱스가 모두 적용되어 있어서 따로 개선점을 찾지 못하였습니다.

```text
SELECT p.exercise,
       count(*) as 횟수
FROM covid
         JOIN member m on m.id = covid.member_id
         JOIN programmer p on p.id = covid.programmer_id
         JOIN hospital h on h.id = covid.hospital_id
WHERE h.name = '서울대병원'
  AND m.age between 30 and 39
GROUP BY p.exercise
ORDER BY 횟수 DESC;

```

----

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

- [X] C. 페이징 쿼리
- 역 페이징 조회 : GET https://wooobo.r-e.kr/stations?size={size}&page={page}
- 좋아요 페이징 조회 : GET https://wooobo.r-e.kr/favorites?size={size}&page={page}

> 테스트 파일 참고 해주세요~

- /httptest/favorite.http
- /httptest/station.http

- [X] D. MySQL Replication with JPA
    - 데이터베이스 EC2 : `EC2-wooobo-database`

### ALB,WAF,ACM

- [ACM](https://ap-northeast-2.console.aws.amazon.com/acm/home?region=ap-northeast-2#/certificates/164a3186-e637-49b4-9dfd-9fc282ecb2de)
    - 도메인 이름 : `wooobo.r-e.kr`
- [Target Group](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#TargetGroup:targetGroupArn=arn:aws:elasticloadbalancing:ap-northeast-2:843255971531:targetgroup/wooobo-webservice/b0af8fa04d7b0f64)
    - Name : `wooobo-webservice`
- [ALB](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LoadBalancers:search=wooobo;sort=loadBalancerName)
    - Name : `wooobo-abl`
- [WAF]
    - NAME = `wooobo-abl`

### 접속

0. pem 파일 이름 : wooobo-nextstep.pem
1. `EC2-wooobo-bastion` 관리망 접속
2. 웹서비스 접속 : `ssh ubuntu@webservice`
3. 데이터베이스 접속 : `ssh ubuntu@database`
