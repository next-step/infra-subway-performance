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
```sql
SELECT DRI.사원번호, HR.이름, DRI.연봉, RK.직급명, AR.입출입시간, AR.지역, AR.입출입구분
  FROM 사원출입기록 AR
  JOIN (
	SELECT DISTINCT PY.사원번호, MAX(PY.연봉) AS 연봉
	  FROM 급여 PY
	  JOIN 부서관리자 BA ON (BA.사원번호 = PY.사원번호)
	  JOIN 부서 DM ON (DM.부서번호 = BA.부서번호)
	  WHERE 'ACTIVE' = upper(DM.비고)
		AND PY.종료일자 = '9999-01-01'
	    AND BA.종료일자 = '9999-01-01'
	  GROUP BY PY.사원번호
	  ORDER BY MAX(PY.연봉) DESC
	  LIMIT 5
    ) DRI ON (DRI.사원번호 = AR.사원번호)
  JOIN 사원 HR ON (HR.사원번호 = DRI.사원번호)
  JOIN 직급 RK ON (RK.사원번호 = DRI.사원번호)
 WHERE RK.직급명 = 'Manager'
   AND AR.입출입구분 = 'O'
 ORDER BY DRI.연봉 DESC;
```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
