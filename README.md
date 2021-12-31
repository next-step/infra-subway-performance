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
```
select SQL_NO_CACHE e.사원번호, e.이름, s.연봉, p.직급명, m.입출입시간, m.지역, m.입출입구분 from 사원 e
inner join 
(select r.사원번호, r.지역, r.입출입구분, max(입출입시간) as 입출입시간 from 사원출입기록 r
inner join (
	select s.사원번호, s.연봉, p.직급명 from 부서관리자 dm
	inner join 부서 d
	on d.부서번호 = dm.부서번호
	inner join 급여 s
	on dm.사원번호 = s.사원번호
	inner join 직급 p
	on s.사원번호 = p.사원번호
	where d.비고 = 'active' 
	and s.종료일자 = '9999-01-01'
    and p.종료일자 = '9999-01-01'
	order by 연봉 desc
	limit 0, 5
) t
on r.사원번호 = t.사원번호
where r.입출입구분 = 'O'
group by r.사원번호, r.지역, r.입출입구분) m
on m.사원번호 = e.사원번호
inner join 직급 p
on e.사원번호 = p.사원번호
inner join 급여 s
on e.사원번호 = s.사원번호
where p.종료일자 = '9999-01-01'
and s.종료일자 = '9999-01-01';
```

```
사원출입기록에 사원번호, 지역, 입출입구분 복합칼럼을 가지는 인덱스 추가
```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

