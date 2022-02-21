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

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)


MySQL Workbench 기준으로 0.158sec 걸렸습니다.

````sql
SELECT 
	연봉_탑5_부서관리자.사원번호, 사원.이름, 연봉_탑5_부서관리자.연봉, 직급.직급명, 사원출입기록.입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분 
    FROM (
		SELECT 부서관리자.사원번호, 연봉
		FROM tuning.부서 as 부서 
		INNER JOIN tuning.부서관리자 as 부서관리자
		ON 부서.부서번호 = 부서관리자.부서번호 AND 부서.비고 = 'active'
		INNER JOIN tuning.급여 as 급여 
		ON 부서관리자.사원번호 = 급여.사원번호 AND 급여.종료일자 > now() 
		WHERE 부서관리자.종료일자 > now()
        ORDER BY 급여.연봉 DESC 
        LIMIT 5) as 연봉_탑5_부서관리자  
    INNER JOIN tuning.사원출입기록 as 사원출입기록
    ON 연봉_탑5_부서관리자.사원번호 = 사원출입기록.사원번호 AND 사원출입기록.입출입구분 = 'O'
    INNER JOIN tuning.사원 as 사원
    ON 연봉_탑5_부서관리자.사원번호 = 사원.사원번호
    INNER JOIN tuning.직급 as 직급
    ON 연봉_탑5_부서관리자.사원번호 = 직급.사원번호 AND 직급.종료일자 > now()
````

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
