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
    - 개선전 속도 750ms ~ 850ms
    - 개선후 적용 후 속도 80~90ms
    - 개선 내용
        - `사원출력기록.사원번호`에서 풀스캔이 걸리고 있어서 익덱싱을 걸었습니다.   
          `create index 사원출입기록_사원번호_index on 사원출입기록 (사원번호 desc)`
        - 캡처 이미지 : ![explain](/temp/db_img_01.png)
        - 10배 정도 속도 개선효과를 볼수 있었습니다.

```txt
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

[X] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

[X] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

[X] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

[X] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)


----
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

### C. 페이징 쿼리

### D. MySQL Replication with JPA