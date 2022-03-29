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
```
select 사원출입기록.사원번호, max(활동중인부서관리자이름연봉직급.이름) as 이름,  max(활동중인부서관리자이름연봉직급.연봉) as 연봉, max(활동중인부서관리자이름연봉직급.직급명) as 직급명, 사원출입기록.지역, max(사원출입기록.입출입구분) as 입출입구분, max(사원출입기록.입출입시간) as 입출입시간
from tuning.사원출입기록 as 사원출입기록
inner join
	(select 활동중인부서관리자이름연봉.사원번호, max(직급.직급명) as 직급명, max(활동중인부서관리자이름연봉.이름) as 이름, max(활동중인부서관리자이름연봉.연봉) as 연봉
	from tuning.직급 as 직급
	join
		(select 급여.사원번호, 활동중인부서관리자이름.이름, 급여.연봉
		from tuning.급여 as 급여
		join
			(select 사원.사원번호, 사원.이름
			from tuning.사원 as 사원
			join 
				(select 부서관리자.사원번호
				from tuning.부서관리자 as 부서관리자
				join 
				(SELECT 부서번호 FROM tuning.부서
				where 비고 = 'active') 활동부서
				on 활동부서.부서번호 = 부서관리자.부서번호
				where 부서관리자.종료일자 = '9999-01-01') 활동중인부서관리자
				on 사원.사원번호 = 활동중인부서관리자.사원번호) 활동중인부서관리자이름
		on 활동중인부서관리자이름.사원번호 = 급여.사원번호) 활동중인부서관리자이름연봉
	on 직급.사원번호 = 활동중인부서관리자이름연봉.사원번호
    group by 사원번호
    order by 연봉 desc
    limit 5) 활동중인부서관리자이름연봉직급
on 사원출입기록.사원번호 = 활동중인부서관리자이름연봉직급.사원번호
group by 사원출입기록.사원번호, 사원출입기록.지역;
```
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
