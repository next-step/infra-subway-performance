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
* `/step1/k6/origin` - 기존 어플리케이션
* `/step1/k6/reverse-proxy` - 리버스 프록시 개선
* `/step1/k6/redis-cache` - 레디스 캐시 설정
* `/step1/k6/test-script` - k6 테스트 스크립트

- Reverse Proxy 개선을 통해 웹페이지 속도가 향상되었습니다.
- WAS 성능 개선을 통해 http_req_duration이 향상되었습니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선
  - gzip 압축 (정적 컨텐츠 파일 압축)
  - cache 설정 (정적 컨텐츠 대상으로 유저의 쿠키를 통해 캐싱)
  - TLS, HTTP/2 설정
- WAS 성능 개선
  - Redis(In-Memory DB)를 사용하여 각 조회 결과 캐싱

---

### 2단계 - 스케일 아웃

* `cache-control` - 헤더에 설정하여 HTTP Caching 설정을 한다.
  * `no-store` - Cache를 사용하지 않는다는 의미
  * `no-cache` - `max-age=0`과 동일한 의미, Cache를 사용하며, 요청시 유효성 검사를 통해 올바른 경우에만 Cache를 사용
  * `max-age` - Cache의 유효기간을 설정


  * `public`은 모든 사용자와 중간 서버가 캐시를 저장할 수 있음을 나타내고, `private`은 엔드포인트의 사용자 브라우저만 캐시를 저장할 수 있음 나타낸다.
  * `Etag` - 리소스의 특정 버전에 대한 식별자로, HTTP 컨텐츠가 바뀌었는지를 검사할 수 있는 태그

- [x] 미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
- [x] 미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
- [x] 미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?
  - HTTP 스펙이 모든 상황을 완벽하게 제어하는 것이 아니기 때문에 가능합니다.
  - 오래된 브라우저, HTTP 1.0, 버그, 수 많은 프록시 캐시 업체들과 그에 대한 구현방법등이 다르기 때문에 대처하기 위해 함께 사용될 수도 있습니다.

- [x] springboot에 HTTP Cache, gzip 설정하기
  - [x] HTTP Cache 설정하기
  - [x] gzip 설정하기
- [x] Launch Template 작성하기
- [x] Auto Scaling Group 생성하기
- [x] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0a86898e1489db9b9

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
* `/step2/cloudwatch` - 폴더 내 결과 캡쳐

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* `/step2/k6` - 폴더 내 결과 캡처

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요. 
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```mysql
SELECT
    dm.id AS '사원번호',
    dm.last_name AS '이름',
    dm.annual_income AS '연봉',
    dm.position_name AS '직급명',
    r.region AS '지역',
    r.record_symbol AS '입출입구분',
    r.time AS '입출입시간'
FROM
    (SELECT
        e.id,
        e.last_name,
        s.annual_income,
        p.position_name
    FROM department d
    INNER JOIN manager m
        ON d.id = m.department_id
            AND d.note = 'ACTIVE'
            AND m.end_date > NOW()
    INNER JOIN salary s
        ON m.employee_id = s.id
            AND s.end_date > NOW()
    INNER JOIN employee e
        ON m.employee_id = e.id
    INNER JOIN position p
        ON e.id = p.id
            AND p.end_date > NOW()
    ORDER BY s.annual_income DESC
    LIMIT 5) dm
LEFT OUTER JOIN record r
    ON dm.id = r.employee_id
WHERE r.record_symbol = 'O';
```
* `/step3` - 폴더 내 결과 캡처

---

### 4단계 - 인덱스 설계


#### 요구사항

- [ ] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환 (M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 2배를 기준으로 해보시고 어렵다면, 일단 리뷰요청 부탁드려요)
  - [x] Coding as a Hobby 와 같은 결과를 반환하세요.
  - [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  - [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  - [ ] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  - [ ] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* `/step4` - 폴더 내 결과 캡처
- hobby
```mysql
# Coding as a Hobby
# Return rows : 2 / Duration time : 0.274s
ALTER TABLE `subway`.`programmer` ADD INDEX `ix_programmer_hobby` (`hobby` DESC);

SELECT hobby, ROUND((COUNT(1) / (SELECT COUNT(1) FROM programmer) * 100), 1) as 'Coding'
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;
```

- hospital
```mysql
# 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
# Return rows : 3610 / Duration time : 0.030s
ALTER TABLE `subway`.`programmer` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL, ADD PRIMARY KEY (`id`);
ALTER TABLE `subway`.`hospital` CHANGE COLUMN `id` `id` INT(11) NOT NULL, ADD PRIMARY KEY (`id`);
ALTER TABLE `subway`.`covid` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL, ADD PRIMARY KEY (`id`);
ALTER TABLE `subway`.`covid` ADD INDEX `ix_covid_hospital_id` (`hospital_id`);

SELECT c.id, h.`name`
FROM hospital h
     INNER JOIN covid c ON h.id = c.hospital_id
     INNER JOIN programmer p ON c.programmer_id = p.id;
```

- student
```mysql
# 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
# Return rows : 3610 / Duration time : 0.034s
ALTER TABLE `subway`.`programmer` ADD INDEX `ix_programmer_years_coding` (`years_coding`);
ALTER TABLE `subway`.`programmer` ADD INDEX `ix_programmer_student` (`student`);

SELECT c.id, h.`name`, p.hobby, p.dev_type, p.years_coding
FROM hospital h
       INNER JOIN covid c ON h.id = c.hospital_id
       INNER JOIN programmer p ON c.programmer_id = p.id
WHERE p.hobby = 'Yes'
  AND p.student LIKE 'Yes%'
  AND p.years_coding = '0-2 years';
```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
