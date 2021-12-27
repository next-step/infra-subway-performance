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
* [x] WAS 성능 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* 테스트 스크립트
  * [Smoke 테스트 스크립트](/k6/smoke.js)
 * [Load 테스트 스크립트](/k6/load.js)
 * [Stress 테스트 스크립트](/k6/stress.js)
* 개선 전
  * [Smoke 테스트 결과](/k6/before/smoke.log)
  * [Load 테스트 결과](/k6/before/load.log)
  * [Stress 테스트 결과](/k6/before/stress.log)
* 개선 후
  * [Smoke 테스트 결과](/k6/after/smoke.log)
  * [Load 테스트 결과](/k6/after/load.log)
  * [Stress 테스트 결과](/k6/after/stress.log)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
 Redis 캐시 적용, Async Thread Pool 설정 했습니다. 
 전체적으로 40% 정도 성능차이가 났고 특히 stress테스트에서 fail이 났던게 사라져서 
 더 많은 request를 받을 수 있게 되었습니다.
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* [x] 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
  * [x] 쿼리 작성만으로 1s 이하로 반환한다.
    ~~~
    -- 조회 쿼리  307 ms
    select 연봉상위자.사원번호, 연봉상위자.이름, 연봉상위자.연봉, 연봉상위자.직급명, 사원출입기록.입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분
    from 사원출입기록
           join
         (select 사원.사원번호, 사원.이름, 급여.연봉, 직급.직급명
          from 사원
                   inner join 직급 on 사원.사원번호 = 직급.사원번호 and 직급.종료일자 = '9999-01-01'
                   inner join 부서관리자 on 사원.사원번호 = 부서관리자.사원번호
                   inner join 부서 on 부서.부서번호 = 부서관리자.부서번호 and upper(부서.비고) = 'ACTIVE'
                   inner join 급여 on 급여.사원번호 = 사원.사원번호 and 급여.종료일자 = '9999-01-01'
          ORDER BY 급여.연봉 DESC
          limit 5) as 연봉상위자
         on 사원출입기록.사원번호 = 연봉상위자.사원번호
    where 사원출입기록.입출입구분 = 'o'
    ORDER BY 연봉상위자.연봉 DESC, 사원출입기록.지역
    ~~~
  * [x] 인덱스 설정을 추가하여 50 ms 이하로 반환한다.
    ~~~
    -- 조회 쿼리  36 ms
    create index i_사원출입기록_사원번호 on 사원출입기록 (사원번호);
    ~~~
* [ ] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  * [x] Coding as a Hobby 와 같은 결과를 반환하세요. 
    ~~~
    쿼리
    select hobby, round(count(hobby) / (select count(*) from programmer) * 100, 1)
    from programmer
    group by hobby
    order by hobby desc;
    
    인덱스
    create index programmer_hobby_index on programmer (hobby);
    
    비교 결과
    인덱스 전 275ms 
    인덱스 후 67ms
    ~~~ 
  * [ ] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
    ~~~
    쿼리
    인덱스
    비교 결과
    인덱스 전 275ms 
    인덱스 후 67ms
    ~~~
  * [ ] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
     ~~~
    쿼리
    인덱스
    비교 결과
    인덱스 전 275ms
    인덱스 후 67ms
    ~~~
  * [ ] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
     ~~~
    쿼리
    인덱스
    비교 결과
    인덱스 전 275ms
    인덱스 후 67ms
    ~~~
  * [ ] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
     ~~~
    쿼리
    인덱스
    비교 결과
    인덱스 전 275ms
    인덱스 후 67ms
    ~~~
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

