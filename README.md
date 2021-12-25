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
##### 1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* 테스트 환경
  * 2018 MacBook Pro
    * cpu - i7 2.6G 6코어
    * memory - 16GB
    * Mac OS 12.0.1 (Monterey)
    * docker desktop 4.3.0
   
A. 쿼리 최적화
   - [x] 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
   (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
  ``` sql
  SELECT 대상관리자.사원번호, 대상관리자.이름, 대상관리자.연봉, 대상관리자.직급명, 사원출입기록.입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분
  FROM (
      SELECT 사원.사원번호, 사원.이름, 급여.연봉, 직급.직급명
      FROM 사원, 부서관리자, 급여, 직급, 부서
      WHERE 부서관리자.사원번호 = 사원.사원번호
      AND CURRENT_DATE BETWEEN 부서관리자.시작일자 AND 부서관리자.종료일자
      AND 부서관리자.부서번호 = 부서.부서번호
      AND 부서.비고 = 'ACTIVE'
      AND 직급.사원번호 = 사원.사원번호
      AND CURRENT_DATE BETWEEN 직급.시작일자 AND 직급.종료일자
      AND 급여.사원번호 = 사원.사원번호
      AND CURRENT_DATE BETWEEN 급여.시작일자 AND 급여.종료일자
      ORDER BY 급여.연봉 DESC
      LIMIT 5
  ) 대상관리자, 사원출입기록
  WHERE 사원출입기록.사원번호 = 대상관리자.사원번호
  AND 사원출입기록.입출입구분 = 'O'
  ORDER BY 대상관리자.연봉 DESC, 사원출입기록.지역;
  ```
* 인덱스 설정 이전 (0.434 sec, 434 ms)
  * ![](image/A.쿼리최적화/쿼리최적화-인덱스-설정하기전.png)

* 인덱스 설정 이후 (0.0022 sec, 2 ms)
  ``` sql
  create index 사원출입기록_사원번호_index on 사원출입기록 (사원번호);
  ```
  ![](image/A.쿼리최적화/쿼리최적화-인덱스-설정후.png)

B. 인덱스 설계
* 아래의 조회 결과를 100 ms 이하로 반환 
- [x] Coding as a Hobby
  ``` sql
  SELECT hobby, ROUND(COUNT(hobby) / (SELECT COUNT(hobby) from programmer) * 100, 1) as percentage
  from programmer
  group by hobby
  order by hobby desc;
  ```
  * 인덱스 설정 이전 (0.517 sec, 517 ms)
  * ![](image/B.인덱스설계/1-1_Coding-as-a-Hobby.png)
  * 인덱스 설정 이후 (0.093 sec, 93 ms)
  ``` sql
  create index programmer_hobby_index on programmer (hobby);
  ```
  * ![](image/B.인덱스설계/1-2_Coding-as-a-Hobby.png)

- [x] 프로그래머 별로 해당하는 병원 이름을 반환하세요
  ```sql
  SELECT covid.id, hospital.name
  FROM covid, hospital, programmer
  WHERE covid.programmer_id = programmer.id
  AND covid.hospital_id = hospital.id;
  ```
  * 인덱스 설정 이전 (1.271 sec, 1271 ms)
  * ![](image/B.인덱스설계/2-1_프로그래머별_병원이름.png)
  * 인덱스 설정 이후 (0.0062 sec, 6 ms)
  ```sql
  create index covid_programmer_id_index on covid (programmer_id);
  create index hospital_id_index on hospital (id);
  create index programmer_id_index on programmer (id);
  ```
  * ![](image/B.인덱스설계/2-2_프로그래머별_병원이름.png)

##### 2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

