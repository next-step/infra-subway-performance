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
   1. 쿼리 최적화
      - [X] 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요
         (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
        - Duration : `0.0047`
        - Fetch Time : `0.000020`
          ```sql
          SELECT 부서관리자.사원번호, 사원.이름, 급여.연봉, 직급.직급명, 출입기록.지역, 출입기록.입출입시간, 출입기록.입출입구분
          FROM tuning.부서관리자
          INNER JOIN tuning.부서 부서
          ON 부서관리자.부서번호 = 부서.부서번호
          AND 부서.비고 = 'Active'
          INNER JOIN tuning.급여 급여
          ON 급여.사원번호 = 부서관리자.사원번호
          AND 급여.종료일자 = '9999-01-01'
          INNER JOIN tuning.사원 사원
          ON 사원.사원번호 = 급여.사원번호
          INNER JOIN tuning.직급 직급
          ON 직급.사원번호 = 사원.사원번호
          AND 직급.종료일자 = '9999-01-01'
          INNER JOIN tuning.사원출입기록 출입기록
          ON 출입기록.사원번호 = 부서관리자.사원번호
          AND 출입기록.입출입구분 = 'O'
          ORDER BY 사원.이름 DESC;
          ```
        - 진행 내용
          - 사원출입기록의 사원번호 인덱스 추가
          - 대소문자 구분하여 부서.비고의 'Active' 조회 시 미션 스크린샷과 내용이 달라서 대소문자 구분없이 진행
   2. 인덱스 설계
      - [X] Coding as a Hobby 와 같은 결과를 반환하세요.
        - Duration : `0.043`
        - Fetch Time : `0.0000069`
          ```sql
          SELECT hobby, COUNT(id) * 100 / (SELECT COUNT(id) FROM subway.programmer) AS RATE
          FROM subway.programmer
          GROUP BY hobby;
          ```
        - 진행 내용
          * 대신 id 필드를 명시해서 속도 개선
      - [X] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
        - Duration : `0.011`
        - Fetch Time : `0.0019`
          ```sql
          SELECT sp.id, sc.id, sh.name
          FROM subway.programmer sp
          INNER JOIN subway.covid sc
          ON sp.id = sc.programmer_id
          INNER JOIN subway.hospital sh
          ON sc.hospital_id = sh.id;
          ```
        - 진행내용
          - 조인 조건 진행
      - [X] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
        - Duration : `0.013`
        - Fetch Time : `0.0027`
          ```sql
          SELECT sh.name
          FROM subway.hospital sh
          INNER JOIN subway.covid sc
          ON sc.hospital_id = sh.id
          INNER JOIN subway.programmer sp
          ON sp.id = sc.programmer_id
          WHERE (sp.hobby = 'Yes' AND sp.student LIKE 'Yes%')
          OR (sp.years_coding = '0-2 years')
          ORDER BY sp.id;
          ```
        - 진행내용
            - 각 조인 조건들을 인덱스 추가
      - [X] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
        - Duration : `0.045`
        - Fetch Time : `0.0000079`
          ```sql
          SELECT sc.stay, COUNT(*) AS CNT
          FROM subway.hospital sh
          INNER JOIN subway.covid sc
          ON sc.hospital_id = sh.id
          INNER JOIN subway.programmer sp
          ON sp.id = sc.programmer_id
          INNER JOIN subway.member sm
          ON sm.id = sp.member_id
          WHERE sp.country = 'India'
          AND sh.name = '서울대병원'
          AND (sm.age > 19 AND sm.age < 30)
          GROUP BY sc.stay;
          ```
        - 진행내용
          - 각 조인 조건들을 인덱스 추가
          - hospital name 인덱스 추가
          - programmer country 인덱스 추가
      - [X] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
        - Duration : `0.065`
        - Fetch Time : `0.0000091`
          ```sql
          SELECT sp.exercise, COUNT(*) AS CNT
          FROM subway.hospital sh
          INNER JOIN subway.covid sc
          ON sc.hospital_id = sh.id
          INNER JOIN subway.programmer sp
          ON sp.id = sc.programmer_id
          INNER JOIN subway.member sm
          ON sm.id = sp.member_id
          WHERE sh.name = '서울대병원'
          AND (sm.age > 29 AND sm.age < 40)
          GROUP BY sp.exercise;
          ```
        - 진행내용
            - 각 조인 조건들을 인덱스 추가
            - programmer exercise 인덱스 추가
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
   - HTTP 메소드 : GET 
   - endpoint : http://127.0.0.1/members
   - 행위 : 멤버 목록 페이지 조회
