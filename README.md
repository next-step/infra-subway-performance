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
   * 아래의 내용 참고 부탁드립니다!
      - /docs/step1/performance_test : 테스트 결과 캡쳐
      - /docs/step1/step1.md : 개선 전 후 측정결과 비교


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   * Reverse Proxy Server (nginx) 개선 항목
      1. gzip 압축
      2. chashing
      3. 프로토콜 개선 (HTTP 2.0)

   * WAS 개선 항목
      1. Redis 적용 (Spring Date chaching)


---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
   - https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-01657876b79c51e31

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
   - /docs/step2/cloudwatch 디렉토리에 결과 넣어두었습니다!

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
    - /docs/step2/performance_test2 디렉토리에 결과 넣어두었습니다!

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
  <br>(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
  <br>
  <br>
  - 조회 소요 시간 : 약 1.8s (M1 사용)
  - 작성 쿼리
    ```sql
      select
          te.id as '사원번호',
          concat(te.first_name,' ',te.last_name) as '이름',
          te.annual_income as '연봉',
          rcd.position_name as '직급명',
          rcd.region as '지역',
          rcd.time as '입출입구분'
      from
      (   select
              p.id,
              p.position_name,
              r.region,
              r.time
          from (select id, position_name from position where end_date >= now() or end_date is null) p
          inner join (select employee_id, region, time from record where record_symbol = 'O') r
          where p.id = r.employee_id
      ) rcd
      inner join
      (   select
              m.employee_id,
              s.annual_income,
              e.id,
              e.first_name,
              e.last_name
          from manager m
          inner join employee_department d
          inner join salary s
          inner join employee e
          where m.end_date >= now()
              and d.start_date <= now() and d.end_date >= now()
              and s.end_date >= now()
              and m.employee_id = d.employee_id
              and m.employee_id = s.id
              and m.employee_id = e.id
          order by s.annual_income desc
          limit 5
      ) te
      where rcd.id = te.employee_id
      order by te.id, region
      ;
    ```

  <br>
  
  - 최종적으로 아래의 2가지 쿼리를 두고 실행계획 비교해보고 2번째 쿼리를 선택했습니다
    - 현재는 두 쿼리가 소요시간에 큰 차이를 보이지는 않고 있으나
      <br>두번째 쿼리를 선택 이유는 첫번째 경우에 사원정보 테이블과 조인하면서 16.75ms가 추가되었으나
      <br>두번째 경우는 사원정보를 결과개수가 적은 5명을 구한 결과와 먼저 조인함으로써 (더 작은 드라이빙 테이블)
      <br>사원정보 조인에 걸리는 소요시간이 거의 추가되지 않아 더 나은 실행계획을 보였기 때문입니다
      <br>
      <br>

      1. 연봉순 5명 구한 결과 & 최종출력에 맞게 사원정보 조합했을 때
      ![연봉순 5명 구한 결과 & 최종출력에 맞게 사원정보 조합](./docs/step3/5명%20구하기.png)
      <br>
      <br>

      2. 연봉순 5명 구한 후 employee 테이블과 조인한 결과 & 최종출력에 맞게 사원정보 조합했을 때
      ![연봉순 5명 구한 후 employee 테이블과 조인한 결과 & 최종출력에 맞게 사원정보 조합](./docs/step3/5명%20구하기*사원%20조인.png)




---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
