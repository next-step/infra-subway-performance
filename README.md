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
    - 실습 환경: macOS M1 Monterey 12.0.1

#### A. 쿼리 최적화

- [x] 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.  
  (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
- 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.

  ```mysql
  SELECT employee_rank.사원번호, employee_rank.이름, top_salary.연봉, employee_rank.직급명, access.지역, access.입출입구분, access.입출입시간
  FROM 사원출입기록 AS access
  INNER JOIN (
      SELECT salary.사원번호, salary.연봉
      FROM 급여 AS salary
        INNER JOIN 부서관리자 AS manager
          ON salary.사원번호 = manager.사원번호
          AND NOW() < manager.종료일자
        INNER JOIN 부서 AS department
          ON manager.부서번호 = department.부서번호
          AND department.비고 = 'active'
      WHERE NOW() < salary.종료일자
      ORDER BY salary.연봉
      DESC LIMIT 5 
  ) AS top_salary
    ON access.사원번호 = top_salary.사원번호
  INNER JOIN (
      SELECT employee.사원번호, employee.이름, rank.직급명
      FROM 사원 AS employee
        INNER JOIN 직급 AS rank
          ON employee.사원번호 = rank.사원번호
          AND NOW() < rank.종료일자 
  ) AS employee_rank
    ON access.사원번호 = employee_rank.사원번호
  WHERE access.입출입구분 = 'O'
  ORDER BY top_salary.연봉 DESC, access.지역;
  ```
- 인덱스 적용 전
  > 14 row(s) returned	2.562 sec / 0.000022 sec

  ![before-1](./query/top_salary_manager_access.png)

- 인덱스 적용 (1)
    - ``CREATE INDEX `idx_access_employee_number` ON `사원출입기록` (사원번호);``
  > 14 row(s) returned	0.032 sec / 0.000016 sec

  ![after-1](./query/top_salary_manager_access.png)

- 인덱스 적용 (2)
    - ``CREATE INDEX `idx_manager_end_date` ON `부서관리자` (`종료일자`)``
    - ``CREATE INDEX `idx_department_activation` ON `부서` (`비고`);``
  > 14 row(s) returned	0.021 sec / 0.000013 sec

  ![after-1](./query/top_salary_manager_department_index.png)

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

