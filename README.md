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
    
    a. 쿼리 최적화
    - 쿼리 최적화 (1s 이하 반환)
        ```sql
        SELECT 상위연봉자.사원번호, 상위연봉자.이름, 상위연봉자.연봉, 상위연봉자.직급명, L.입출입구분, L.지역, L.입출입시간
        FROM (
                 SELECT A.사원번호, E.이름, S.연봉, R.직급명
                 FROM 부서 상위연봉자
                          INNER JOIN 부서관리자 A
                                     ON 상위연봉자.부서번호 = A.부서번호
                                         AND A.종료일자 = '9999-01-01'
                          INNER JOIN 급여 S
                                     ON A.사원번호 = S.사원번호
                                         AND S.종료일자 = '9999-01-01'
                          INNER JOIN 부서사원_매핑 M
                                     ON S.사원번호 = M.사원번호
                                         AND M.종료일자 = '9999-01-01'
                          INNER JOIN 사원 E
                                     ON M.사원번호 = E.사원번호
                          INNER JOIN 직급 R
                                     ON E.사원번호 = R.사원번호
                                         AND R.종료일자 = '9999-01-01'
                          WHERE 상위연봉자.비고 = 'ACTIVE'
                          ORDER BY S.연봉 DESC
                          LIMIT 5
        ) 상위연봉자
                 INNER JOIN 사원출입기록 L
                      ON L.사원번호 = 상위연봉자.사원번호
                                AND L.입출입구분 = 'O'
        ORDER BY 상위연봉자.연봉 DESC, L.지역;
        ```
        
        | 사원번호 | 이름 | 연봉 | 직급명 | 입출입구분 | 지역 | 입출입시간 |
        | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
        | 110039 | Vishwani | 106491 | Manager | O | a | 2020-09-05 20:30:07 |
        | 110039 | Vishwani | 106491 | Manager | O | b | 2020-08-05 21:01:50 |
        | 110039 | Vishwani | 106491 | Manager | O | d | 2020-07-06 11:00:25 |
        | 111133 | Hauke | 101987 | Manager | O | a | 2020-01-24 02:59:37 |
        | 111133 | Hauke | 101987 | Manager | O | b | 2020-05-07 16:30:37 |
        | 110114 | Isamu | 83457 | Manager | O | a | 2020-05-29 19:38:12 |
        | 110114 | Isamu | 83457 | Manager | O | b | 2020-09-03 01:33:01 |
        | 110114 | Isamu | 83457 | Manager | O | c | 2020-11-12 02:29:00 |
        | 110114 | Isamu | 83457 | Manager | O | d | 2020-04-25 08:28:54 |
        | 110567 | Leon | 74510 | Manager | O | a | 2020-10-17 19:13:31 |

    - 인덱싱 설정 (50ms 이 반환)
        ```sql
        CREATE INDEX 사원출입기록_사원번호_INDEX ON 사원출입기록 (사원번호);
        ```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
