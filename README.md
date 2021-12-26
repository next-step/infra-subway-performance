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
- **A. 쿼리 최적화**
- 최초 작성한 쿼리는 아래와 같고, 조회 건수, Druation / Fetch Time은 `14 row(s) returned	0.312 sec / 0.000 sec` 입니다. 실행계획은 아래 첨부한 이미지와 같습니다. 비교적 적은 건수를 담고 있는 부서 테이블로 Drive하도록 편안하게 작성했습니다.

    ```
    SELECT A.사원번호, A.이름, A.연봉, A.직급명, B.입출입시간, B.지역, B.입출입구분
    FROM
    (SELECT B.사원번호, E.이름, C.연봉, D.직급명 FROM 부서 A
    LEFT JOIN 부서관리자 B ON A.부서번호 = B.부서번호
    LEFT JOIN 직급 D ON B.사원번호 = D.사원번호
    LEFT JOIN 급여 C ON B.사원번호 = C.사원번호
    LEFT JOIN 사원 E ON B.사원번호 = E.사원번호
    WHERE A.비고 = 'ACTIVE'
    AND B.종료일자 = '9999-01-01'
    AND C.종료일자 = '9999-01-01'
    AND D.종료일자 = '9999-01-01'
    ORDER BY 연봉 DESC
    LIMIT 5) A LEFT JOIN 사원출입기록 B ON A.사원번호 = B.사원번호
    WHERE B.입출입구분 = 'O'
    ORDER BY A.연봉 DESC;
    ```
  ![./images_explain/쿼리최적화1.png](./images_explain/쿼리최적화1.png)
- 실행계획에서 확인한 것처럼 사원출입기록 테이블과 Join할 때 Full Table Scan이 일어나는 것을 확인했습니다. 해당 과정에서 많은 코스트가 발생하여 사원출입기록 테이블에 인덱스를 걸어주기로 했습니다. PK가 사원번호, 순번에 걸려있기는 했지만 사원번호만으로 조회할 때는 인덱스를 타지 않으므로, 사원번호 하나에만 인덱스를 걸어줍니다. duration이 비약적으로 줄어든 것을 확인할 수 있습니다. `14 row(s) returned	0.000 sec / 0.000 sec` 인덱스를 잘 타는 것도 보입니다
  ![./images_explain/쿼리최적화2.png](./images_explain/쿼리최적화2.png)

- **B. 인덱스 설계**

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

