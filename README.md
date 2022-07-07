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
- smoke
    + before
    ![smoke_test_before](/result/k6/smoke_test_before.png)
    + after
    ![smoke_test_after](/result/k6/smoke_test_after.png)
- load
    + before
    ![load_test_before](/result/k6/load_test_before.png)
    + after
    ![load_test_after](/result/k6/load_test_after.png)
- stress
    + before
    ![stress_test_before](/result/k6/stress_test_before.png)
    + after
    ![stress_test_after](/result/k6/stress_test_after.png)
- PageSpeed - mobile
    + before
    ![PageSpeed_mobile_before](/result/pagespeed/PageSpeed_mobile_before.png)
    + after
    ![PageSpeed_mobile_after](/result/pagespeed/PageSpeed_mobile_after.png)
- PageSpeed - mobile
    + before
    ![PageSpeed_pc_before](/result/pagespeed/PageSpeed_pc_before.png)
    + after
    ![PageSpeed_pc_after](/result/pagespeed/PageSpeed_pc_after.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선
    + gzip 압축
    + 캐싱
    + TLS, HTTP/2 설정
- WAS 성능 개선
    - Redis Spring Data Cache 적용

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-08c6e2cac8490e43e

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
  ![](/result/step2/cloudwatch1.png)
  ![](/result/step2/cloudwatch2.png)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- smoke
  ![smoke_test](/result/step2/smoke_test.png)
+ load
  ![load_test](/result/step2/load_test.png)
- stress
  ![stress_test](/result/step2/stress_test.png)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
    ```
    SELECT e.id as '사원번호'
         , e.last_name as '이름'
         , high_salary.annual_income as '연봉'
         , p.position_name as '직급명'
         , r.region as '지역'
         , r.record_symbol as '입출입구분'
         , r.time as '입출입시간'
      FROM tuning.employee e
           INNER JOIN (SELECT id
                            , annual_income
                         FROM tuning.salary s
                        WHERE id IN (SELECT employee_id
                                       FROM tuning.manager
                                      WHERE department_id in (SELECT id FROM tuning.department WHERE note = 'Active')
                                        AND start_date <= now() AND end_date >= now())
                          AND start_date <= now() AND end_date >= now()
                        ORDER BY annual_income desc
                        LIMIT 5) high_salary
                   ON high_salary.id = e.id
           INNER JOIN tuning.position p
                   ON p.id = e.id
                  AND p.start_date <= now() AND p.end_date >= now()
           INNER JOIN tuning.record r
                   ON r.employee_id = e.id
                  AND r.record_symbol = 'O'
    ```
    ![](/result/step3/result_grid.png)
    ![](/result/step3/output.png)

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
