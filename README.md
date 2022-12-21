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
- /src/main/resources/step1 디렉토리 내 이미지 파일 참고 부탁드립니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- redis를 이용하여 노선 조회, 역 조회, 경로 조회 기능에 캐싱을 적용했습니다.
- gzip 압축, cache 사용, TLS, HTTP/2 설정을 통해 리버스 프록시를 개선했습니다.

---

### 2단계 - 스케일 아웃
- spring boot에 HTTP Cache, gzip 설정하기 
- Launch Template 작성하기 
- Auto Scaling Group 생성하기
- Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.
- [바로가기](https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0a8521e6842d65ea9)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

- /src/main/resources/step2 디렉토리 내 이미지 파일 참고 부탁드립니다.

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- /src/main/resources/step2 디렉토리 내 이미지 파일 참고 부탁드립니다.
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
SELECT 
    manager_salary_top5.사원번호,
    manager_salary_top5.이름,
    manager_salary_top5.연봉,
    manager_salary_top5.직급명,
    r.time AS 입출입시간,
    r.region AS 지역,
    r.record_symbol AS 입출입구분
FROM
    (SELECT 
        m.employee_id AS 사원번호,
            e.last_name AS 이름,
            s.annual_income AS 연봉,
            p.position_name AS 직급명
    FROM
        manager AS m
    JOIN department AS d ON d.id = m.department_id
    JOIN position AS p ON p.id = m.employee_id
    JOIN employee AS e ON e.id = m.employee_id
    JOIN salary AS s ON s.id = e.id
    WHERE
        d.note = 'active'
	AND p.position_name = 'Manager'
	AND NOW() BETWEEN m.start_date AND m.end_date
	AND NOW() BETWEEN s.start_date AND s.end_date
    ORDER BY s.annual_income DESC
    LIMIT 5) AS manager_salary_top5
JOIN record AS r ON r.employee_id = manager_salary_top5.사원번호
WHERE r.record_symbol = 'O'
ORDER BY manager_salary_top5.연봉 DESC;
```

---

### 4단계 - 인덱스 설계
### 요구사항
- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
    - Coding as a Hobby 와 같은 결과를 반환하세요.
    - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
    - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
    - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
    - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
