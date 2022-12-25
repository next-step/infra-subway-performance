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

   <details>
   <summary>성능 개선 결과</summary>

    - Smoke 테스트
        - Smoke k6 before
        - ![smoke-k6-before](./docs/step1/smoke/smoke_k6_before.png)
        - Smoke k6 after_nginx
        - ![smoke-k6-after](./docs/step1/smoke/smoke_k6_after1_nginx.png)
        - Smoke k6 after_nginx_was
        - ![smoke-k6-after](./docs/step1/smoke/smoke_k6_after2_nginx_was.png)
        - Smoke grafana before
        - ![smoke-grafana-before](./docs/step1/smoke/smoke_grafana_before.png)
        - Smoke grafana after_nginx
        - ![smoke-grafana-after](./docs/step1/smoke/smoke_grafana_after1_nginx.png)
        - Smoke grafana after_nginx_was
        - ![smoke-grafana-after](./docs/step1/smoke/smoke_grafana_after2_nginx_was.png)
    - Load 테스트
        - Load k6 before
        - ![load-k6-before](./docs/step1/load/load_k6_before.png)
        - Load k6 after_nginx
        - ![load-k6-after](./docs/step1/load/load_k6_after1_nginx.png)
        - Load k6 after_nginx_was
        - ![load-k6-after](./docs/step1/load/load_k6_after2_nginx_was.png)
        - Load grafana before
        - ![load-grafana-before](./docs/step1/load/load_grafana_before.png)
        - Load grafana after_nginx
        - ![load-grafana-after](./docs/step1/load/load_grafana_after1_nginx.png)
        - Load grafana after_nginx_was
        - ![load-grafana-after](./docs/step1/load/load_grafana_after2_nginx_was.png)
    - Stress 테스트
        - Stress k6 before
        - ![stress-k6-before](./docs/step1/stress/stress_k6_before.png)
        - Stress k6 after_nginx
        - ![stress-k6-after](./docs/step1/stress/stress_k6_after1_nginx.png)
        - Stress k6 after_nginx_was
        - ![stress-k6-after](./docs/step1/stress/stress_k6_after2_nginx_was.png)
        - Stress grafana before
        - ![stress-grafana-before](./docs/step1/stress/stress_grafana_before.png)
        - Stress grafana after_nginx
        - ![stress-grafana-after](./docs/step1/stress/stress_grafana_after1_nginx.png)
        - Stress grafana after_nginx
        - ![stress-grafana-after](./docs/step1/stress/stress_grafana_after2_nginx_was.png)

    </details> 

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    - nginx: cache, http2, gzip 적용
    - spring redis 적용, cache, json 적용
    - 기본상태로 배포 후 k6 측정 -> nginx 적용 후 k6 측정 -> was 적용 후 k6 측정

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
    - https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0c9861cf3286fac64

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

   <details>
   <summary>cpu 부하 실행 후 추가생성 결과</summary>

    - clout watch monitoring
        - ![clout-watch-monitoring](./docs/step2/new_stress_cloud_watch_monitoring.png)

   </details>

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

   기존 스트레스 테스트의 경우 경로를 조회하는 간단한 로직이라 부하를 줘도 캐시 및 was 설정으로 부하가 심하지 않아
   같은 로직에서 **vus를 5000까지 설정한 테스트**와 **DB를 자주 접근하는 스트레스 테스트**를 진행했습니다.  
   생각보다 cpu성능이 높아지지 않아 동적 크기 정책을 30%로 설정했습니다.

   <details>
   <summary>성능 개선 결과</summary>

    - 기존 Stress 테스트
        - ![stress-k6](./docs/step2/stress_k6.png)
        - ![stress-grafana](./docs/step2/stress_grafana.png)
    - 강화 Stress 테스트
        - [force script](./docs/step2/force_stress.js)
        - ![stress-k6](./docs/step2/force_stress_k6.png)
        - ![stress-grafana](./docs/step2/force_stress_grafana.png)
    - 새로운 Stress 테스트
        - [script](./docs/step2/new_stress.js)
        - ![stress-k6](./docs/step2/new_stress_k6.png)
        - ![stress-grafana](./docs/step2/new_stress_grafana.png)

   </details>

4. 모든 정적 자원에 대해 no-cache, no-store 설정 가능한가요?

   가능합니다. 컨트롤러에서 HttpServletResponse 를 받은 후 직접 `Cache-Controle`을 할당하여 설정할 수 있습니다.
   [참조 링크](https://stackoverflow.com/questions/49547/how-do-we-control-web-page-caching-across-all-browsers)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

   <details>
   <summary>결과</summary>

    - query
      ``` sql
         SELECT 
           e.id AS '사원번호',
           e.last_name AS '이름',
           top.annual_income AS '연봉',
           p.position_name AS '직급명',
           r.time AS '입출입시간',
           r.region AS '지역',
           r.record_symbol AS '입출입구분'
         FROM employee e
         INNER JOIN 
         (
           SELECT s.id, s.annual_income
           FROM salary s
           WHERE s.id IN
           (
             SELECT employee_id
             FROM manager
             WHERE department_id IN (SELECT id FROM department WHERE note = 'active')
               AND start_date <= now() 
               AND end_date >= now()
           )
             AND s.start_date <= now()
             AND s.end_date >= now()
           ORDER BY s.annual_income DESC
           LIMIT 5
         ) top ON top.id = e.id
         INNER JOIN record r ON r.employee_id = e.id AND r.record_symbol = 'O'
         INNER JOIN position p ON p.id = e.id AND p.start_date <= now() AND p.end_date >= now()
         ORDER BY top.annual_income DESC, r.time DESC
      ```

    - Visual Explain
    - ![visual-explain](./docs/step3/visual_explain.png)
    - Result
    - ![result](./docs/step3/result.png)

   </details>

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

    <details>
    <summary>결과</summary>

    1. Coding as a Hobby 와 같은 결과를 반환
        - query
          ``` sql
          SELECT
            p.hobby,
            CONCAT
            (
              ROUND
              (
                COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100, 1
              ), '%'
            ) AS 'percent'
          FROM programmer p
          GROUP BY p.hobby;
          ```
        - 설정
            - programmer.id pk 추가
            - programmer.hobby index 설정
        - 결과: 280ms
        - ![result](./docs/step4/quiz1_result.png)

    2. 프로그래머별로 해당하는 병원 이름을 반환
        - query
          ``` sql
          SELECT
            c.id,
            h.name
          FROM hospital h
          INNER JOIN covid c ON h.id = c.hospital_id
          INNER JOIN programmer p ON p.id = c.programmer_id;
          ```
        - 설정
            - hospital.id pk 추가
            - programmer.id pk 추가
            - covid.id pk 추가
            - covid.programmer_id index 설정
        - 결과: 20ms
        - ![result](./docs/step4/quiz2_result.png)

    3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬
        - query
          ``` sql
           SELECT
             c.id,
             h.name,
             p.hobby,
             p.dev_type,
             p.years_coding
           FROM programmer p
           INNER JOIN covid c ON c.programmer_id = p.id
           INNER JOIN hospital h ON h.id = c.hospital_id
           WHERE p.hobby = 'yes'
             AND
             (
               (p.years_coding = '0-2 years')
               OR
               (p.student LIKE 'yes%')
             )
           ORDER BY p.id;
          ```
        - 설정
            - programmer.id pk 추가
            - covid.id pk 추가
            - hospital.id pk 추가
            - programmer.hobby index 설정
        - 결과: 3.6s -> 46ms
        - ![result](./docs/step4/quiz3_result.png)

    4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계
        - query
          ``` sql
          SELECT
            c.stay,
            count(*)
          FROM hospital h
          INNER JOIN covid c ON c.hospital_id = h.id
          INNER JOIN programmer p ON p.id = c.programmer_id
          INNER JOIN member m ON m.id = p.member_id
          WHERE h.name = '서울대병원'
            AND m.age BETWEEN 20 AND 29
            AND p.country = 'India'
          GROUP BY c.stay;
          ```
        - 설정
            - hospital.id pk 추가
            - covid.id pk 추가
            - programmer.id pk 추가
            - member.id pk 추가
            - covid.hospital_id index 설정
            - covid.programmer_id index 설정
            - hospital.name index 설정
        - 결과: 11s -> 193ms
        - ![result](./docs/step4/quiz4_result.png)

    5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계
        - query
          ``` sql
          SELECT
            p.exercise,
            count(*)
          FROM hospital h
          INNER JOIN covid c ON c.hospital_id = h.id
          INNER JOIN programmer p ON p.id = c.programmer_id
          INNER JOIN member m ON m.id = c.member_id
          WHERE h.name = '서울대병원'
            AND m.age BETWEEN 30 AND 39
          GROUP BY p.exercise;
          ```
        - 설정
            - hospital.id pk 추가
            - covid.id pk 추가
            - programmer.id pk 추가
            - member.id pk 추가
            - hospital.name index 설정
        - 결과: 251ms
        - ![result](./docs/step4/quiz5_result.png)
    </details>

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
    - https://haservi.r-e.kr/stations/1?size=10

    <details>
    <summary>상세 결과</summary>

    - DB 분리
        - master
        - ![master_server](./docs/step5/master_server.png)
        - slave
        - ![slave_server](./docs/step5/slave_server.png)
        - 지하철 생성 시 slave 생성 여부 확인
        - ![db_status](./docs/step5/db_status.png)
        - 지하철 조회 시 slave에서 데이터 read 확인
        - ![read_slave_db](./docs/step5/read_slave_db.png)
    - 페이징
        - 지하철 페이징 결과
        - ![station_page_result](./docs/step5/station_page_result.png)

    </details>

---

### 1단계 - 화면 응답 개선하기

1. 요구사항
    - [x] 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
        - [x] 개선 전 / 후를 직접 계측하여 확인
    - [x] 개선 방법
        - [x] gzip 압축하기

### 2단계 - 스케일 아웃 (with ASG)

1. 요구사항
    - [x] springboot에 HTTP Cache, gzip 설정하기
    - [x] Launch Template 작성하기
    - [x] Auto Scaling Group 생성하기
    - [x] Smoke, Load, Stress 테스트 후 결과를 기록
2. 실습 요구사항
    - [x] 모든 정적 자원에 대해 no-cache, private 설정과 테스트 코드 검증
    - [x] 확장자는 css인 경우 max-age 1년, js인 경우 no-cache, private 설정
    - [x] 모든 정적 자원에 대해 no-cache, no-store 설정

### 3단계 - 쿼리 최적화

1. 요구사항
    - [x] 활동중인(Active) 부서의 현재 부서관리자(manager) 중 연봉 상위 5위안에 드는 사람들이 최근에
      각 지역별로 언제 퇴실(O)했는지 조회하기(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
    - [x] 인덱스 설정을 추가하지 않고 200ms 이하로 반환합니다.

### 4단계 - 인덱스 설계

1. 요구사항
    - [x] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
        - [x] Coding as a Hobby 와 같은 결과를 반환하세요.
        - [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
        - [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
          (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
        - [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
        - [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

### 추가 미션

1. 요구사항
    - [x] 페이징 쿼리 추가
    - [x] MySQL Replication 설정
