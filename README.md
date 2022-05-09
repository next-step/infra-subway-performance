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

### stations 스모크 테스트  
![img_1.png](img_1.png)

### stations 로드 테스트
![img_2.png](img_2.png)

### stations 스트레스 테스트
![img_6.png](img_6.png)
   - 기존에는 VUser 가 500 일때, max 가 15.98s 였으나 현재는 VUser 가 1000 일때도 max 가 3.93s 로 성능이 향상되었습니다.
   - ![img_7.png](img_7.png)
     - Spring 로그를 보다보니 HTTP 1.0 이라 나오는데, WebPage 에서는 AssetFile 들은 HTTP2 로 받아오던데, Spring 에서 응답하는 값도 HTTP2 로 보내기 위해서는 톰캣설정이 필요한건가요?

3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- 정적 파일 gzip 압축
- bundle minimization
- http1 -> http2
- 잘 변하지 않고, 조회가 잦은 값에 cache 적용

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
   1. https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0ef469cf9e3dd716d
   2. 기존에 존재하는 Instance 를 Launch Template 으로 생성하였습니다.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
   1. 

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
SELECT sub_result.사원번호,
       sub_result.이름,
       sub_result.연봉,
       p.position_name as '직급명',
       r.time          as '입출입 시간',
       r.region        as '지역',
       r.record_symbol as '입출입구분'
FROM (
         SELECT employee.id as '사원번호', employee.last_name as '이름', s.annual_income as '연봉'
         FROM tuning.employee
                  INNER JOIN tuning.manager m on m.employee_id = employee.id AND m.end_date >= NOW()
                  INNER JOIN tuning.department d on d.id = m.department_id AND d.note = 'active'
                  INNER JOIN tuning.salary s on s.id = employee.id AND s.end_date > NOW()
         ORDER BY annual_income DESC
         LIMIT 5
     ) as sub_result
         INNER JOIN tuning.position p ON p.id = sub_result.사원번호 AND p.position_name = 'manager'
         INNER JOIN tuning.record r ON r.employee_id = sub_result.사원번호 AND r.record_symbol = 'o';

```
현재 M1 사용중입니다. Explain 보면서 최대한 빠르게 쿼리를 구성했습니다.
![img_4.png](img_4.png)
---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
   1. codding as hobby
      ```sql
       set @totalCount = (select count(hobby) from programmer);
    
       EXPLAIN SELECT no.count, yes.count FROM
       programmer
       INNER JOIN (SELECT hobby, round(COUNT(hobby) / @totalCount * 100, 1) AS count FROM programmer WHERE hobby = 'NO' GROUP BY hobby) as no
       INNER JOIN (SELECT hobby, round(COUNT(hobby) / @totalCount * 100, 1) AS count FROM programmer WHERE hobby = 'YES' GROUP BY hobby) as yes
       LIMIT 1;
      ```
     - 원래 실행시간 (2s 791ms)
     - 원래 실행계획
       - ![img_5.png](img_5.png)
     - 인덱스 생성
       ```sql
          CREATE INDEX idx_hobby ON programmer (hobby);
       ```
       - hobby 의 카디널리티가 너무 높아 사실 인덱스를 만들어도 의미가 없을것 같습니다.
     - 인덱스 생성 쿼리 결과
       - 커버링 인덱스로 생성 완료
       - ![img_10.png](img_10.png)
   2. 프로그래머별로 해당하는 병원을 출력하시오 (covid.id, hospital.name)
   ```sql
   # programmer_id 당 hospital 이 두개 이상 있는 경우를 확인하는 작업  
   SELECT programmer_id, COUNT(*) as c
   FROM covid
   WHERE programmer_id IS NOT NULL
   GROUP BY programmer_id
   HAVING c > 1;
   # result = 0 따라서 1:1 맵핑임

   SELECT c.id, h.name
   FROM covid c
   INNER JOIN hospital h on c.hospital_id = h.id; 
   #(executeTime = 158ms) M1 의 경우라 두배(200ms 이하)로 지정
   ```
      - Primary Key 지정
        - covid -> id is primary key
        - programmer -> id is primary key
        - hospital -> id is primary key
        - member -> id is primary key
      - 인덱스 생성
        ```sql
            CREATE UNIQUE INDEX unique_idx_programmer_id_hospital_id ON covid (programmer_id, hospital_id);
        ```
   3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
   ```sql
   select c.id, h.name, result.hobby, result.dev_type, result.years_coding
    from covid as c
    inner join hospital h on c.hospital_id = h.id
    inner join
    (
    select p.id, p.hobby, p.dev_type, p.years_coding
    from programmer as p
    where (hobby = 'Yes' and student = 'Yes')
    or years_coding = '0-2 years'
    ) as result
    ON result.id = c.programmer_id;
    
    SHOW INDEX FROM programmer;
    
    create index idx_hobby_student
    on programmer (hobby, student);
    
    create index idx_years_coding
    on programmer (years_coding);
   ```
    
   - Execute Time -> 160ms (M1 기준)
   - 다만 하면서도 years_coding 의 cardinality 를 감안했을때 Index 를 거는게 Row 가 많아진다면 유의미할까? 라는 생각도 들긴 했습니다.

   4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
   ```sql
    SELECT covid.stay, COUNT(*)
    FROM covid
    INNER JOIN programmer p on covid.programmer_id = p.id AND p.country = 'India'
    INNER JOIN (SELECT id FROM hospital WHERE id = 9) as h on covid.hospital_id = h.id
    inner join (select id from member where age in (20, 21, 22, 23, 24) or age in (25, 26, 27, 28, 29)) as m on m.id = covid.member_id
    GROUP BY covid.stay
    ORDER BY null;
    
    CREATE UNIQUE INDEX uidx_pk ON covid (id);
    CREATE UNIQUE INDEX uidx_pk ON programmer (id);
    CREATE UNIQUE INDEX uidx_pk ON hospital (id);
    CREATE UNIQUE INDEX uidx_pk ON member (id);
    
    CREATE INDEX idx_programmer_id ON covid (programmer_id);
    CREATE INDEX idx_h_id ON covid (hospital_id);
    CREATE INDEX idx_member_age ON member (id, age);
   ```
   
   - 리뷰받고 난뒤 인덱스 성능 개선
     - ![img_11.png](img_11.png)
     
   5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
    ```sql
    select exercise, count(p.id) from programmer as p
    inner join covid c on c.programmer_id = p.id
    inner join hospital h on c.hospital_id = h.id AND h.id = 9
    inner join (select id, age from member where age in (30, 31, 32, 33, 34) or age in (35, 36, 37, 38, 39)) as m on m.id = c.member_id
    group by p.exercise
    order by null;
    ```
   
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
