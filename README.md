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
select a.사원번호, a.이름, a.연봉, a.직급명, en.지역, en.입출입구분, en.입출입시간
from  (
  select da.사원번호, em.이름, sal.연봉, r.직급명
  from 부서관리자 da
  join 급여 sal on da.사원번호 = sal.사원번호
  join 부서 dep on da.부서번호 = dep.부서번호
  join 직급 r on r.사원번호 = da.사원번호
  join 사원 em on em.사원번호 = da.사원번호
  join 부서사원_매핑 dm on da.사원번호 = dm.사원번호 and da.부서번호 = dm.부서번호
  where dep.비고='active'
  and da.종료일자 > now()
  and r.종료일자 > now()
  and dm.종료일자 > now()
  and sal.종료일자 > now()
  order by sal.연봉 desc
  limit 5
) a
left join 사원출입기록 en on en.사원번호 = a.사원번호
where en.입출입구분 = 'o'
;
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
