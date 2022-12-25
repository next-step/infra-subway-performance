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
   - resources/docs/load
   - resources/docs/smoke
   - resources/docs/stress
   - https://subway.sixthou.kro.kr/
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
  - 리버스 프록시개선과 was성능개선 두가지 스탭으로 나눠서 진행했습니다.
  - 리버스 프로시개선
    - gzip, cache, tls, https/2
  - was 성능개선
    - redis 설정
    
#### 요구사항
- [x] 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선 
  - [x] 개선 전 / 후를 직접 계측하여 확인
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
    - [링크](https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0b4895710bf98580b)
```
#!/bin/bash

sudo apt-get update
sudo apt -y install default-jre
sudo apt -y install openjdk-8-jre-headless

sudo apt install unzip 
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

sudo apt-get update && \
sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common && \
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add - && \
sudo apt-key fingerprint 0EBFCD88 && \
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" && \
sudo apt-get update && \
sudo apt-get install -y docker-ce && \
sudo usermod -aG docker ubuntu && \
sudo curl -L https://github.com/docker/compose/releases/download/v2.9.0/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose && \
sudo chmod +x /usr/local/bin/docker-compose && \
sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

sudo mkdir -p /home/ubuntu/app
sudo mkdir -p /home/ubuntu/data/log
sudo chown -R ubuntu:ubuntu /home/ubuntu/app
sudo chown -R ubuntu:ubuntu /home/ubuntu/data/log

sudo -i -u ubuntu aws s3 cp s3://nextstep-camp-pro/sixthou-deploy.sh /home/ubuntu/app
sudo -i -u ubuntu chmod 755 /home/ubuntu/app/sixthou-deploy.sh 
sudo -i -u ubuntu /bin/bash /home/ubuntu/app/sixthou-deploy.sh step2 prod
```
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
![cloutwatch](src/main/resources/docs/step2/cloudwatch/cloudwatch.png)
```sh
$ stress -c 2
```
3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- smoke
  ![smoke](src/main/resources/docs/step2/smoke/smoke-k6.png)
- load
  ![load](src/main/resources/docs/step2/load/load-k6.png)
- stress
  ![stress](src/main/resources/docs/step2/stress/stress-k6.png)

#### 요구사항
- [x] springboot에 HTTP Cache, gzip 설정하기
- [x] Launch Template 작성하기
- [x] Auto Scaling Group 생성하기
- [x] Smoke, Load, Stress 테스트 후 결과를 기록

#### 미션 요구사항
미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?
- no-cache
  - 캐시를 하지말라는 것이아니라 캐싱된 복사본을 사용자에게 보여주기 이전에, 재검증을 위한 요청을 원 서버로 보내도록 강제 복사본을 사용해도 되는지 확인을 하라는 옵션입니다.
- no-store
  - 클라이언트 요청 혹은 서버 응답에 관해서 어떤 것도 캐싱하지 않습니다.
- must-revalidate
  - 캐시는 사용하기 이전에 기존 리소스의 상태를 반드시 확인하고 만료된 리소스는 사용하지 않습니다.

no-store 옵션만으로도 캐시가 무효화되어야 하는것이 맞으나, 버그 및 구버전과의 호한성을 위해 위 3가지 옵션을 함께 가져갑니다. 아래와 같이 설정하면 가능하다고 생각합니다!

```java
registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/**")
        .addResourceLocations("classpath:/static/")
        .setCacheControl(CacheControl.noCache().cachePrivate())
        .setCacheControl(CacheControl.noStore().mustRevalidate());
```

- 참고
  - [how-do-we-control-web-page-caching-all-browsers](https://stackoverflow.com/questions/49547/how-do-we-control-web-page-caching-across-all-browsers)
  - [no-store 로도 충분할 것 같은데...](https://www.inflearn.com/questions/112647/no-store-%EB%A1%9C%EB%8F%84-%EC%B6%A9%EB%B6%84%ED%95%A0-%EA%B2%83-%EA%B0%99%EC%9D%80%EB%8D%B0-no-cache-must-revalidate-%EB%8A%94-%EC%99%9C-%EA%B0%99%EC%9D%B4-%EC%B6%94%EA%B0%80%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B8%EA%B0%80%EC%9A%94#84841)
  - [Cache-Control](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Cache-Control)
---
### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
- 쿼리
    ```sql
    select mem.id            사원번호,
           mem.last_name     이름,
           mem.annual_income 연봉,
           mem.position_name 직급명,
           r.time            입출국시간,
           r.region          지역,
           r.record_symbol   입출국구분
    from record r
             inner join (select e.id,
                                e.last_name,
                                s.annual_income,
                                p.position_name
                         from employee e
                                  inner join employee_department ed on e.id = ed.employee_id
                                  inner join department d on ed.department_id = d.id
                                  inner join manager m on d.id = m.department_id and e.id = m.employee_id
                                  inner join salary s on s.id = e.id
                                  inner join position p on p.id = e.id
                         where d.note = 'active'
                           and m.end_date > now()  and m.start_date <= now()
                           and ed.end_date > now() and ed.start_date <= now()
                           and s.end_date > now()  and s.start_date <= now()
                           and p.end_date > now() and s.start_date <= now()
                           and UPPER(p.position_name) = UPPER('Manager')
                         order by annual_income desc
                             limit 5) as mem on r.employee_id = mem.id
    where record_symbol = 'O'
    ```
- 실행결과
  - ![응답결과](src/main/resources/docs/step3/응답결과.png)
- 응답시간(M1)
  - ![응답시간](src/main/resources/docs/step3/응답시간.png) 
#### 요구사항
- [x] 활동중인(Active) 부서의 현재 부서관리자(manager) 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실(O)했는지 조회
- [x] 인덱스 설정을 추가하지 않고 200ms 이하로 반환합니다.
  - M1의 경우엔 시간 제약사항을 달성하기 어렵습니다.
- [x] 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

#### 요구사항
- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  - M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 2배를 기준 진행.

1. Coding as a Hobby 와 같은 결과를 반환하세요.
    - Coding as a Hobby
    - ![Coding as a Hobby](src/main/resources/docs/step4/CodingAsAHobby.png)
    - 쿼리
      ```sql
      select  p.hobby
            , round(count(*)/(select count(*) from programmer) * 100, 1) as rate
      from    programmer p
      group by p.hobby
      order by p.hobby desc
      ```
   - 실행결과
     - ![실행결과](src/main/resources/docs/step4/1실행결과.png)
   - 응답시간
     - ![응답시간](src/main/resources/docs/step4/1응답시간.png)
   - 실행계획
     - ![실행계획](src/main/resources/docs/step4/1실행계획1.png)
     
     - ![실행계획](src/main/resources/docs/step4/1실행계획2.png)
   - 변경사항
     - hobby 인덱스 생성
     - hobby 테이블이 id값에 pk 설정
     - 처음에 hobby에 인덱스만 걸었을때는 응답시간이 200ms 이상 나왔고, id 값에 pk설정 후 200ms 아래로 응답되는걸 확인할 수 있엇습니다.
       ```sql
       ALTER TABLE `subway`.`programmer` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       CREATE INDEX `idx_programmer_hobby`  ON `subway`.`programmer` (hobby) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;
       ```

2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
   - 쿼리
    ```sql
    select c.id,
           h.name
    from programmer p
             inner join covid c on p.id = c.programmer_id
             inner join hospital h on c.hospital_id = h.id
    ```
   - 실행결과
     - ![실행결과](src/main/resources/docs/step4/2실행결과.png)
   - 응답시간
     - ![실행결과](src/main/resources/docs/step4/2응답시간.png)
   - 실행계획
     - ![실행계획](src/main/resources/docs/step4/2실행계획1.png)
     - ![실행계획](src/main/resources/docs/step4/2실행계획2.png)
   - 변경사항
     - programmer, covid, hospital 테이블 pk설정
     - covid 테이블 programmer_id 인덱스 생성
     ```sql
     ALTER TABLE `subway`.`programmer` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
     ALTER TABLE `subway`.`covid` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
     ALTER TABLE `subway`.`hospital` CHANGE COLUMN `id` `id` INT(11) NOT NULL ,ADD PRIMARY KEY (`id`);
     CREATE INDEX `idx_covid_programmer_id`  ON `subway`.`covid` (programmer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
     ```

3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
   - 쿼리
    ```sql
    select c.id,
           h.name,
           p.hobby,
           p.dev_type,
           p.years_coding
    from programmer p
             inner join covid c on p.id = c.programmer_id
             inner join hospital h on c.hospital_id = h.id
    where (p.student = 'Yes' || p.student like 'Yes%')
       or p.years_coding = '0-2 years'
    order by p.id
    ```
    - 실행결과
        - ![실행결과](src/main/resources/docs/step4/3실행결과.png)
    - 응답시간
        - ![실행결과](src/main/resources/docs/step4/3응답시간.png)
    - 실행계획
        - ![실행계획](src/main/resources/docs/step4/3실행계획1.png)
        - ![실행계획](src/main/resources/docs/step4/3실행계획2.png)
   - 변경사항
       - programmer, covid, hospital 테이블 pk설정
       - covid 테이블 programmer_id 인덱스 생성
       ```sql
       ALTER TABLE `subway`.`programmer` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       ALTER TABLE `subway`.`covid` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       ALTER TABLE `subway`.`hospital` CHANGE COLUMN `id` `id` INT(11) NOT NULL ,ADD PRIMARY KEY (`id`);
       CREATE INDEX `idx_covid_programmer_id`  ON `subway`.`covid` (programmer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
       ```     
4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
   - 쿼리
   ```sql
    select c.stay        머문기간,
           count(*) 인원
    from hospital h
             inner join covid c on h.id = c.hospital_id
             inner join programmer p on c.programmer_id = p.id
             inner join member m on c.member_id = m.id and p.member_id = m.id
    where h.name = '서울대병원'
      and m.age between 20 and 29
      and p.country = 'india'
    group by c.stay
    ```
     - 실행결과
        - ![실행결과](src/main/resources/docs/step4/4실행결과.png)
    - 응답시간
        - ![실행결과](src/main/resources/docs/step4/4응답시간.png)
    - 실행계획
        - ![실행계획](src/main/resources/docs/step4/4실행계획1.png)
        - ![실행계획](src/main/resources/docs/step4/4실행계획2.png)
     - 변경사항
       - hospital, covid, programmer, member 테이블 pk 설정
       - hospital 테이블 name 컬럼 유니크 지정
       - covid 테이블 hospital_id, programmer_id, member_id 복합 인덱스, stay 인덱스 설정
       ```sql
       ALTER TABLE `subway`.`programmer` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       ALTER TABLE `subway`.`covid` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       ALTER TABLE `subway`.`hospital` CHANGE COLUMN `id` `id` INT(11) NOT NULL ,ADD PRIMARY KEY (`id`),ADD UNIQUE INDEX `name_UNIQUE` (`name` ASC);
       ALTER TABLE `subway`.`member` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       
       CREATE INDEX `idx_covid_hospital_id_member_id_programmer_id`  ON `subway`.`covid` (hospital_id, member_id, programmer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
       ```
5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
   - 쿼리
        ```sql
         select p.exercise 운동횟수,
                count(*) 인원
         from hospital h
                  inner join covid c on h.id = c.hospital_id
                  inner join programmer p on c.programmer_id = p.id
                  inner join member m on c.member_id = m.id and p.member_id = m.id
         where h.name = '서울대병원'
           and m.age between 30 and 39
         group by p.exercise;
        ```
    - 실행결과
        - ![실행결과](src/main/resources/docs/step4/5실행결과.png)
    - 응답시간
        - ![실행결과](src/main/resources/docs/step4/5응답시간.png)
    - 실행계획
        - ![실행계획](src/main/resources/docs/step4/5실행계획1.png)
        - ![실행계획](src/main/resources/docs/step4/5실행계획2.png)
    - 변경사항
        - hospital, covid, programmer, member 테이블 pk 설정
        - hospital 테이블 name 컬럼 유니크 지정
        - covid 테이블 hospital_id, programmer_id, member_id 복합 인덱스 설정
       ```sql
       ALTER TABLE `subway`.`programmer` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       ALTER TABLE `subway`.`covid` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       ALTER TABLE `subway`.`hospital` CHANGE COLUMN `id` `id` INT(11) NOT NULL ,ADD PRIMARY KEY (`id`),ADD UNIQUE INDEX `name_UNIQUE` (`name` ASC);
       ALTER TABLE `subway`.`member` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,ADD PRIMARY KEY (`id`);
       
       CREATE INDEX `idx_covid_hospital_id_member_id_programmer_id`  ON `subway`.`covid` (hospital_id, member_id, programmer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
       ```
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
