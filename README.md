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
    <summary>(1) 쿼리 작성만으로 1s 이하로 반환</summary>
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
    <summary>(2) 인덱스 설정을 추가하여 50 ms 이하로 반환</summary>
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

3. 페이징 쿼리를 적용한 API endpoint를 알려주세요

