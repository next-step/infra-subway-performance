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

#### 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

A. 쿼리 최적화

- 쿼리 작성만으로 1s 이하로 반환한다.
- 실행시간: 600~700ms

```sql
select 급여.사원번호, 사원.이름, 급여.연봉, 직급.직급명, max(사원출입기록.입출입시간) as 입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분
from 사원출입기록,
     (
         select 급여.사원번호, 급여.연봉
         from 급여,
              부서관리자
         where 급여.사원번호 = 부서관리자.사원번호
           and curdate() between 부서관리자.시작일자 and 부서관리자.종료일자
           and curdate() between 급여.시작일자 and 급여.종료일자
         order by 연봉 desc
         limit 5
     ) 급여,
     (
         select 사원번호, 직급명
         from 직급
         where curdate() between 시작일자 and 종료일자
     ) 직급,
     사원
where 사원출입기록.사원번호 = 급여.사원번호
  and 사원출입기록.사원번호 = 직급.사원번호
  and 사원출입기록.사원번호 = 사원.사원번호
  and 사원출입기록.입출입구분 = 'O'
group by 급여.사원번호, 급여.연봉, 직급.직급명, 급여.연봉, 사원출입기록.지역, 사원출입기록.입출입구분
order by 급여.연봉 desc, 사원출입기록.지역;
```

- 인덱스 설정을 추가하여 50 ms 이하로 반환한다.
- 인덱스 추가 시 full scan에서 uniqu index scan으로 실헁계획이 바뀌는 것을 확인했습니다.
- 인덱스 적용 후 실행시간: 15~20ms

```sql
create index idx_사원출입기록_사원번호 on tuning.사원출입기록 (사원번호);
create index idx_부서관리자_사원번호_시작일자_종료일자 on tuning.부서관리자 (사원번호, 시작일자, 종료일자);
```

#### 페이징 쿼리를 적용한 API endpoint를 알려주세요

