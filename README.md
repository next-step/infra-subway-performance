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

- M1 환경입니다.

```
select top5OfSalary.사번, 사.이름, top5OfSalary.연봉, 직.직급명, 출입기록.입출입시간, 출입기록.지역, 출입기록.입출입구분
from (select Active관리자.사원번호 as 사번, max(급.연봉) as 연봉
      from (select 부서번호 from 부서 where 비고 = 'Active') activeDepart
               join (select 사원번호, 부서번호 from 부서관리자 where 종료일자 = '9999-01-01') Active관리자
                    on activeDepart.부서번호 = Active관리자.부서번호
               join 급여 급 on Active관리자.사원번호 = 급.사원번호
      group by Active관리자.사원번호
      order by 연봉 desc
      limit 5) top5OfSalary
         join 사원 사 on top5OfSalary.사번 = 사.사원번호
         join (select 사원번호, 직급명 from 직급 where 직급명 = 'Manager') 직 on top5OfSalary.사번 = 직.사원번호
         join (select * from 사원출입기록 where 입출입구분 = 'O') 출입기록 on top5OfSalary.사번 = 출입기록.사원번호;
```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
