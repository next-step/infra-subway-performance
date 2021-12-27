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

- 아래 요구사항에 기록해두겠습니다.

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

#### 요구사항

##### A. 쿼리 최적화

###### 쿼리 작성만으로 1s 이하로 반환한다.

> 0.410 sec

```sql
SELECT employee.사원번호,
       employee.이름,
       pay.연봉,
       rank.직급명,
       access.입출입시간,
       access.지역,
       access.입출입구분
FROM 부서 department
         INNER JOIN 부서관리자 manager
                    ON manager.부서번호 = department.부서번호
                        AND department.비고 = 'active'
                        AND manager.종료일자 = '9999-01-01'
         INNER JOIN 사원 employee
                    ON employee.사원번호 = manager.사원번호
         INNER JOIN 급여 pay
                    ON pay.사원번호 = employee.사원번호
                        AND pay.종료일자 = '9999-01-01'
         INNER JOIN 직급 rank
                    ON rank.사원번호 = employee.사원번호
                        AND rank.종료일자 = '9999-01-01'
         INNER JOIN 사원출입기록 access
                    ON access.사원번호 = employee.사원번호
                        AND access.입출입구분 = 'O'
ORDER BY pay.연봉 DESC, access.지역
```

###### 인덱스 설정을 추가하여 50 ms 이하로 반환한다.

사원출입기록에서 비용이 제일 많이 발생하는 것으로 보고 사원번호에 대한 인덱스를 생성함.

**인덱스 생성 전**

![img_1.png](img_1.png)

**인덱스 생성 후**

![img_2.png](img_2.png)

```sql
ALTER TABLE `tuning`.`사원출입기록`
    ADD INDEX `I_사원번호` (`사원번호` ASC);
```

> 0.0014 sec
