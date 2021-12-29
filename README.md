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

    #### A. 쿼리 최적화 
    1) 쿼리 작성만으로 1s 이하로 반환한다. <br/>
    2) 인덱스 설정을 추가하여 50 ms 이하로 반환한다. <br/><br/>

    - 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. 
(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간) <br/>
      - 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.


   #### 1) 쿼리 작성
- 실행 시간:  `250ms`

```sql
select E.*, D.입출입시간, D.지역, D.입출입구분
from 사원출입기록 D,
     (select C.사원번호, H.이름, C.연봉, G.직급명
      from 부서관리자 A, 부서 B, 급여 C, 사원 H, 직급 G
      where A.부서번호 = B.부서번호
        and B.비고 like 'active'
        and A.사원번호 = C.사원번호
        and H.사원번호 = A.사원번호
        and G.사원번호 = A.사원번호
        and C.시작일자 < now() and C.종료일자 > now()
        and A.시작일자 < now() and A.종료일자 > now()
        and G.시작일자 < now() and G.종료일자 > now()
      order by C.연봉 desc limit 5) E
where D.사원번호 = E.사원번호
  and 입출입구분 = 'O'
order by E.연봉 desc;
```
#### 2) 인덱스 설정 추가
- 실행 시간: `0ms`
```sql
CREATE INDEX `idx_사원출입기록_사원번호`  ON `tuning`.`사원출입기록` (사원번호) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

