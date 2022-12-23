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
- load : 2.64ms -> 2.84ms
- smoke : 4.96ms -> 7.82ms
- stress : 1.04s -> 304ms
- 다른 부분은 차이가 없거나 더 늘어났으나 stress는 크게 개선됐습니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- 1차 튜닝 :  gzip 압축, cache, HTTP2 적용
- 2차 튜닝 : Spring Data Cache를 적용했습니다.
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- snh15978-template
- https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-099e53bb11892771a
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
- step2 디렉토리에 추가했습니다.
```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- step2 디렉토리에 추가했습니다.
- load
  - before : 998.1us, after : 1.19ms
- smoke
  - before : 1.35ms, after : 5.4ms
- stress
  - vus 300 -> 600까지 확인
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```mysql
SELECT a.id            as '사원번호',
       a.last_name     as '이름',
       a.annual_income as '연봉',
       a.position_name as '직급명',
       r.time          AS '입출입시간',
       r.region        AS '지역',
       r.record_symbol AS '입출입구분'
FROM (SELECT e.id, e.last_name, s.annual_income, p.position_name
      FROM manager m
               INNER JOIN department d ON m.department_id = d.id
               INNER JOIN employee e ON m.employee_id = e.id
               INNER JOIN salary s on e.id = s.id
               INNER JOIN position p on m.employee_id = p.id
      WHERE d.note = 'active'
        AND p.position_name = 'manager'
        AND NOW() BETWEEN m.start_date AND m.end_date
        AND NOW() BETWEEN s.start_date AND s.end_date
      ORDER BY s.annual_income DESC
      LIMIT 5) a
         INNER JOIN record r ON r.employee_id = a.id
WHERE r.record_symbol = 'O'
ORDER BY a.annual_income DESC;
```
---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
