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

### 1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

### A. 쿼리 최적화

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.

1. 쿼리 작성만으로 1s 이하로 반환한다. (0.341 sec)

    ```mysql
    SELECT S1.사원번호, S1.이름, S1.연봉, S1.직급명, T1.입출입구분, T1.지역, T1.입출입시간
    FROM (
        SELECT S2.사원번호, S5.이름, S3.연봉, S6.직급명
        FROM 부서 S1
        INNER JOIN 부서관리자 S2
            ON S1.부서번호 = S2.부서번호
            AND S2.종료일자 = '9999-01-01'
        INNER JOIN 급여 S3
            ON S2.사원번호 = S3.사원번호
            AND S3.종료일자 = '9999-01-01'
        INNER JOIN 부서사원_매핑 S4
            ON S3.사원번호 = S4.사원번호
            AND S4.종료일자 = '9999-01-01'
        INNER JOIN 사원 S5
            ON S4.사원번호 = S5.사원번호
        INNER JOIN 직급 S6
            ON S5.사원번호 = S6.사원번호
            AND S6.종료일자 = '9999-01-01'
        WHERE S1.비고 = 'ACTIVE'
        ORDER BY S3.연봉 DESC limit 5
        ) S1
    INNER JOIN 사원출입기록 T1
      ON T1.사원번호 = S1.사원번호
     AND T1.입출입구분 = 'O'
    GROUP BY T1.입출입구분, S1.이름, S1.연봉, S1.직급명, S1.사원번호, T1.지역, T1.입출입시간
    HAVING MAX(T1.순번)
    ORDER BY S1.연봉 DESC, T1.지역
    ```
    ![쿼리작성.png](쿼리작성.png)

2. 인덱스 설정을 추가하여 50 ms 이하로 반환한다.
    ```mysql
    create index 부서_비고_index
        on 부서 (비고);
    
    create index 부서관리자_종료일자_index_2
        on 부서관리자 (종료일자);
    
    create index 사원출입기록_사원번호_index
        on 사원출입기록 (사원번호);
    
    create index 사원출입기록_입출입구분_index
        on 사원출입기록 (입출입구분);
    ```
    ![인덱스적용.png](인덱스적용.png)


### 2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

