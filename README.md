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

1. TODO
    - Reverse Proxy 개선 
        - gzip 압축
        - cache
        - TLS, HTTP/2 설정
    - WAS 성능 개선
        - Spring Data Cache
        - DB Connection Pooling

2. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
    - [TABLE 정보]
        - department : 부서정보
        - employee : 직원정보
        - employee_department : 어떤 직원이 어느 부서에 속해있는지
        - manager : 어떤 부서에 어떤 직원이 언제부터 언제까지 메니저를 했는지
        - position : 어떤 직원이 어떤 역할을 언제부터 언제까지 했는지
        - record :  직원별 필드 정보
        - salary : 급여정보
    - [INDEX 정보]
        - department : department_name / (id, note)
        - employee : join_date / (sex / fist_name)
        - employee_department : department_id
        - manager : department_id
        - record : region / time / door / record_symbol
        - salary : used

- 최종 쿼리
```
SELECT c.employee_id   as 사원번호,
       c.last_name     AS 이름,
       c.annual_income AS 연봉,
       c.position_name AS 직급명,
       r.time          AS 입출입시간,
       r.region        AS 지역,
       r.record_symbol AS 입출입구분
FROM (SELECT m.employee_id,
             e.last_name,
             s.annual_income,
             p.position_name
      FROM manager m
	   INNER JOIN department d ON d.id = m.department_id
	   INNER JOIN position p ON p.id = m.employee_id
	   INNER JOIN employee e ON e.id = m.employee_id
	   INNER JOIN salary s ON s.id = e.id
      WHERE d.note = 'active'
        AND p.position_name = 'manager'
        AND NOW() BETWEEN m.start_date AND m.end_date
        AND NOW() BETWEEN s.start_date AND s.end_date
      ORDER BY s.annual_income DESC
      LIMIT 5) c
 INNER JOIN record r ON r.employee_id = c.employee_id
WHERE r.record_symbol = 'O'
ORDER BY c.annual_income DESC
```

- 실행계획
![step3_실행계획.png](/src/main/resources/templates/step3_실행계획.png)

	- 실행계획을 확인해보니, 다른 컬럼에는 적절하게 인덱싱이 걸려있지만 r.employee_id에 인덱스가 없어 인덱스를 걸면 좀더 빠른 결과가 있을듯 하다!
	
- 결과
![step3_결과.png](/src/main/resources/templates/step3_결과.png)
	
---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
