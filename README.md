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
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
  (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
    <details>
      <summary> 조회 쿼리(0.409s) </summary>
  
          select 상위연봉자.사원번호, 상위연봉자.이름, 상위연봉자.연봉, 상위연봉자.직급명, 사원출입기록.입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분
          from 사원출입기록
          join
          (
              select 부서관리자.사원번호, 사원.이름, 급여.연봉, 직급.직급명
              from 부서관리자
                  join 부서 on 부서관리자.부서번호 = 부서.부서번호 and 부서.비고 = 'active'
                  join 급여 on  부서관리자.사원번호 = 급여.사원번호 and 급여.종료일자 = '99990101'
                  join 사원 on 급여.사원번호 = 사원.사원번호
                  join 직급 on 사원.사원번호 = 직급.사원번호 and 직급.종료일자 = '99990101'
              where 부서관리자.종료일자 = '99990101'
              order by 급여.연봉 desc limit 5) as 상위연봉자
          on 상위연봉자.사원번호 = 사원출입기록.사원번호
          where 사원출입기록.입출입구분 = 'O'
          order by 상위연봉자.연봉 desc;

    </details>
    <details>
      <summary> 인덱스 생성 </summary>

        create index idx_사원번호 on tuning.사원출입기록(사원번호)

    </details>
    <details>
        <summary> 인덱스 적용후 조회 (0.003s) </summary>
    <div markdown="1">
  
    ![after_index.png](인덱스적용후.png)
    </div>
    </details>


3. 페이징 쿼리를 적용한 API endpoint를 알려주세요

