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

## 안정적인 서비스 만들기

### 1단계 - 화면 응답 개선하기

#### 요구사항

- [x] 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
    - [x] Reverse Proxy 개선
        - [x] gzip 압축
        - [x] cache
        - [x] TLS, HTTP/2 설정
    - [x] WAS 성능 개선
        - [x] Spring Data Cache 적용
- [x] 부하테스트 결과 첨부

#### Answer

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- `src/main/resources/load/` 폴더 결과 첨부

3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- Reverse Proxy 개선
    - gzip 압축
    - cache 적용
    - TLS, HTTP/2 설정
- WAS 성능 개선
    - Spring Data Cache

---

### 2단계 - 스케일 아웃

#### 요구사항

- [x] springboot에 HTTP Cache, gzip 설정
- [x] Launch Template 작성
- [x] Auto Scaling Group 생성
- [x] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.

- [hahoho87-template](https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-009c4f48b61249b07)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

- `src/main/resources/auto-scaling` 폴더 결과 첨부

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- `src/main/resources/auto-scaling` 폴더 결과 첨부

4. 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?

- 가능합니다.

```java
registry.addResourceHandler(PREFIX_STATIC_RESOURCES+"/"+version.getVersion()+"/**")
	.addResourceLocations("classpath:/static")
	.setCacheControl(CacheControl.noCache().cachePrivate())
	.setCacheControl(CacheControl.noStore().mustRevalidate());
```

- `Cache-Control` 헤더에 `no-cache`와 `no-store`를 설정하면 됩니다.
- 원래는 `no-stroe`만 설정해도 캐시가 무효화 되어야 하지만, HTTP 스펙, 오래된 브라우저와의 호환, 버그등의 문제로 `no-cache`도 같이 설정해야 합니다.
- reference:
    - [How do we control web page caching, across all browsers?](https://stackoverflow.com/questions/49547/how-do-we-control-web-page-caching-across-all-browsers)
    - [No cache, must-revalidate 사용 이유](https://www.inflearn.com/questions/112647/no-store-%EB%A1%9C%EB%8F%84-%EC%B6%A9%EB%B6%84%ED%95%A0-%EA%B2%83-%EA%B0%99%EC%9D%80%EB%8D%B0-no-cache-must-revalidate-%EB%8A%94-%EC%99%9C-%EA%B0%99%EC%9D%B4-%EC%B6%94%EA%B0%80%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B8%EA%B0%80%EC%9A%94#84841)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
#
12 rows
# 1.669 sec (m1)
SELECT inline.id AS '사원번호', inline.last_name AS '이름', inline.annual_income AS '연봉', inline.position_name AS '직급명', rec.region AS '지역', rec.record_symbol AS '입출입구분', rec.time AS '입출입시가'
FROM (SELECT emp.id AS 'id', emp.last_name AS 'last_name', sal.annual_income AS 'annual_income', pos.position_name AS 'position_name'
      FROM department dep
               INNER JOIN manager man ON man.department_id = dep.id
               INNER JOIN salary sal ON sal.id = man.employee_id
               INNER JOIN employee emp ON emp.id = man.employee_id
               INNER JOIN position pos ON pos.id = emp.id
      WHERE dep.note = 'ACTIVE'
        AND man.end_date = '9999-01-01'
        AND sal.end_date = '9999-01-01'
        AND pos.end_date = '9999-01-01' 
        LIMIT 5) inline
         INNER JOIN
     record rec ON rec.employee_id = inline.id
WHERE rec.record_symbol = 'O';

```

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

#### 요구사항 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환(m1 의 경우 2배 기준)

- [x] Coding as a Hobby 와 같은 결과를 반환하세요.

```sql
/**
  2 rows
  0,256 sec (m1)
 */
ALTER TABLE `subway`.`programmer` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL, ADD PRIMARY KEY (`id`), ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);;
CREATE INDEX `idx_programmer_hobby` ON `subway`.`programmer` (hobby) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

SELECT hobby, round(count(*) / (SELECT count(*) fRom programmer) * 100, 1) AS rate
FROM programmer
GROUP BY hobby
```

- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

```sql
/**
  96180 rows
  0.044 sec (m1)
 */
ALTER TABLE `subway`.`hospital` change COLUMN `id` `id` BIGINT(20) NOT NULL, ADD PRIMARY KEY (`id`);
ALTER TABLE covid
    ADD FOREIGN KEY (programmer_id) REFERENCES programmer (id);
ALTER TABLE covid
    ADD FOREIGN KEY (hospital_id) REFERENCES hospital (id);

SELECT p.id, h.name
FROM hospital h
         INNER JOIN covid c on c.hospital_id = h.id
         INNER JOIN programmer p on p.id = c.programmer_id;
```

- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby,
  user.DevType, user.YearsCoding)

```sql

/**
  26764 rows
  0.049 sec (m1)
 */
CREATE INDEX `idx_hospital_name` ON `subway`.`hospital` (name);

SELECT c.id, h.name, p.hobby, p.dev_type, p.years_coding
FROM hospital h
         INNER JOIN covid c ON c.hospital_id = h.id
         INNER JOIN programmer p ON p.id = c.programmer_id
WHERE (p.hobby = 'Yes' AND p.student LIKE ('Yes%'))
   OR p.years_coding = '0-2 years'
ORDER BY p.id
```

- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

```sql
/**
  10 rows
  0.142 sec (m1)
 */
ALTER TABLE member
    ADD PRIMARY KEY (id);
ALTER TABLE programmer
    ADD FOREIGN KEY (member_id) REFERENCES member (id);
CREATE INDEX index_covid_stay ON covid (stay);

SELECT c.stay, count(1)
FROM hospital h
         INNER JOIN covid c ON c.hospital_id = h.id
         INNER JOIN programmer p ON p.id = c.programmer_id
         INNER JOIN member m ON m.id = p.member_id
WHERE h.name = '서울대병원'
  AND p.country = 'India'
  AND m.age BETWEEN 20 AND 29
GROUP BY c.stay;
```

- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

```sql
/**
  5 rows
  0.142 sec (m1)
 */
SELECT p.exercise, count(1)
FROM hospital h
         INNER JOIN COVID C ON C.HOSPITAL_ID = H.ID
         INNER JOIN PROGRAMMER P ON P.ID = C.PROGRAMMER_ID
         INNER JOIN member m on m.id = p.member_id
WHERE m.age BETWEEN 30 AND 39
  AND h.name = '서울대병원'
GROUP BY p.exercise

``` 

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
