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

#### 개선 전

1. Smoke Test(http://3.35.223.220/d/bvXIAmq7z/k6-smoke-testing-results?orgId=1&from=1656599880000&to=1656600599000)
2. Load Test(http://3.35.223.220/d/080n0mqnz/k6-load-testing-results?orgId=1&from=1656597900000&to=1656599100000)
3. Stress Test(http://3.35.223.220/d/TMFSAmq7k/k6-stress-test-result?orgId=1&from=1656598800000&to=1656599699000)

#### 개선 후
1. Smoke Test(http://3.35.223.220/d/bvXIAmq7z/k6-smoke-testing-results?orgId=1&from=1656668640000&to=1656669299000)
2. Load Test(http://3.35.223.220/d/080n0mqnz/k6-load-testing-results?orgId=1&from=1656669300000&to=1656670200000)
3. Stress Test(http://3.35.223.220/d/TMFSAmq7k/k6-stress-test-result?orgId=1&from=1656667140000&to=1656667920000)

#### 성능 비교 표(pageSpeed)

| https://jhsong2580.kro.kr/ | FCP(Sec) | LCP(Sec) | TTI(Sec) | TBT(mSec) |
|:--------------------------:|:--------:|:--------:|:--------:|:---------:|
|            개선전             |   14.8   |   15.3   |   15.4   |    510    |
|     1차 개선 (캐싱/Gzip설정)      |   5.4    |   6.0    |   6.1    |    560    |
|      3차 개선 (Http2설정)       |   5.2    |   5.7    |   5.7    |    400    |


| https://jhsong2580.kro.kr/stations | FCP(Sec) | LCP(Sec) | TTI(Sec) | TBT(mSec) |
|:--------------------------:|:--------:|:--------:|:--------:|:---------:|
|            개선전             |   16.7   |   16.7   |   25.7   |   8_510   |
|      1차 개선 (캐싱/Gzip설정)       |   7.1    |   7.1    |   17.1   |   9_720   |
|      2차 개선 (redis 캐싱설정)       |   6.8    |   6.8    |   13.4   |   6_330   |
|      3차 개선 (Http2설정)       |   6.8    |   6.8    |   12.9   |   5_950   |

| https://jhsong2580.kro.kr/lines | FCP(Sec) | LCP(Sec) | TTI(Sec) | TBT(mSec) |
|:-------------------------------:|:--------:|:--------:|:--------:|:---------:|
|               개선전               |   16.2   |   16.2   |   17.9   |   1_210   |
|        1차 개선 (캐싱/Gzip설정)        |   6.8    |   6.8    |   8.3    |   1_240   |
|       2차 개선 (redis 캐싱설정)        |   6.8    |   6.8    |   8.0    |   1_040   |
|       3차 개선 (Http2설정)         |   6.7    |   6.8    |   7.9    |   1_030   |

#### 성능비교 표(K6)
| testType |                      개선전                      |                      개선후                      | 
|:--------:|:---------------------------------------------:|:---------------------------------------------:|
|  smoke   | <img src="readmeSource/step1/개선전_smoke.png">  | <img src="readmeSource/step1/개선후_smoke.png">  |   
|   load   |  <img src="readmeSource/step1/개선전_load.png">  |  <img src="readmeSource/step1/개선후_load.png">  |   
|  stress  | <img src="readmeSource/step1/개선전_stress.png"> | <img src="readmeSource/step1/개선후_stress.png"> |   
 
###개선 전 후 vuser 수용 가능량

|      | 수용 가능한 vuser |                  Cloudwatch                  |                  비고                  |
|:----:|:------------:|:--------------------------------------------:|:------------------------------------:|
| 개선 전 |     338      | <img src="readmeSource/step1/개선전_vuser.png"> | vuser 338명 이후로 급격하게 요청시간이 늘어남을 알수 있다 |
| 개선 후 |     360      | <img src="readmeSource/step1/개선후_vuser.png"> | vuser 360명 이후로 급격하게 요청시간이 늘어남을 알수 있다 |


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   0. 점검
      1. thread.dump 점검 결과 교착 걸린 부분이나 지나치게 CPU 점유기간이 긴 쓰레드는 없음
   1. 1차 개선
      1. Gzip : 가장 높은 압축률(9)로 압축 설정
      2. Proxy Cache 설정 : css,이미지들을 한달간 캐싱한다.
   2. 2차개선 (/lines, /stations)
      1. line.findAll(), station.findAll()에 대해 캐싱한다
   3. 3차개선 (Http2 설정)


---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
   https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-090ddf89b23bf5e3c
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
   <img src="readmeSource/step2/scaleout결과.png">
  
   1. 세션별 Reverse proxy기능을 위해 Target Group에 대해 Stickiness옵션 설정
```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

### 개선 전/후 vuser 수용 가능량

|                        | 수용 가능한 vuser |                       Cloudwatch                       |                  비고                   |
|:----------------------:|:------------:|:------------------------------------------------------:|:-------------------------------------:|
|          개선 전          |     338      | <img width=350 src="readmeSource/step1/개선전_vuser.png"> | vuser 338명 이후로 급격하게 요청시간이 늘어남을 알수 있다  |
|      개선 후(step1)       |     360      | <img width=350 src="readmeSource/step1/개선후_vuser.png"> | vuser 360명 이후로 급격하게 요청시간이 늘어남을 알수 있다  |
| 개선 후(step2 - scaleOut) |     1340     | <img width=350 src="readmeSource/step2/개선후_vuser.png"> | vuser 1340명 이후로 급격하게 요청시간이 늘어남을 알수 있다 |



#### 성능비교 표(K6)
| testType |                      개선전                      |                      개선후                      | 
|:--------:|:---------------------------------------------:|:---------------------------------------------:|
|  smoke   | <img src="readmeSource/step1/개선전_smoke.png">  | <img src="readmeSource/step2/개선후_smoke.png">  |   
|   load   |  <img src="readmeSource/step1/개선전_load.png">  |  <img src="readmeSource/step2/개선후_load.png">  |   
|  stress  | <img src="readmeSource/step2/개선전_stress.png"> | <img src="readmeSource/step2/개선후_stress.png"> | 

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    1. key, foreign key 매핑하기
       1. alter table covid add primary key (id);
       2. alter table hospital add primary key(id);
       3. alter table programmer add primary key(id);
       4. alter table hospital modify column id bigint(20);
       5. alter table covid add foreign key(hospital_id) references hospital(id);
       6. alter table covid add foreign key(programmer_id) references programmer(id);
   
    2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
       1. 일단 조회하기(4.14sec)
          select c.programmer_id, h.name from hospital as h
              join covid as c on c.hospital_id = h.id
              join programmer as p on c.programmer_id = p.id;
       2. indexing 후 쿼리(0.0053sec)
          1. 각 테이블 별 기본키 / 외래키 설정을 통한 자동 인덱싱
       3. 개선 전 실행계획
          [img](/readmeSource/step4/개선전_1.png)
       4. 


    3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding) 
       1. 일단 조회하기(조회불가)
          1. 특이사항 : 시작 모수를 programmer가 아닌 covid를 driving table로 설정시, 시작 모수가 많아 executing시간이 1분을 넘어간다.
       select c.id, h.name, p.hobby, p.dev_type, p.years_coding  from programmer as p    -- and (years_coding = "0-2 years" or (student in ("Yes, part-time", "Yes, full-time")) and
           join covid as c on (c.programmer_id is not null and hobby = "Yes" and (years_coding = "0-2 years" or (student in ("Yes, part-time", "Yes, full-time"))) and p.id = c.programmer_id)
           join hospital as h on h.id = c.hospital_id
           order by c.programmer_id;
       2. indexing 후 쿼리 (0.304sec)
          1. 각 테이블 별 기본키 / 외래키 설정을 통한 자동 인덱싱
          
    4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
       1. 일단 조회하기(조회불가)
       select c.stay, count(c.stay) from covid as c where
          c.hospital_id = (select id from hospital where name = "서울대병원")
          and programmer_id in (select id from programmer where country = "India")
          and member_id in (select id from member where age >= 20 and age <= 29)
          group by c.stay;
       2. indexing 후 쿼리(0.073sec)
          1. 각 테이블 별 기본키 / 외래키 설정을 통한 자동 인덱싱
          2. create index programmer_country on subway.programmer(id, country);
             create index member_age on subway.member(id, age);
       
    5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
       1. 일단 조회하기(0.495sec)
          select exercise, count(exercise) from programmer as p
              where p.id in (select programmer_id from covid where hospital_id in (select id from hospital where name = "서울대병원"))
              and p.id in (select id from member where age >= 30 and age <= 39)
              group by exercise;
       2. indexing후 쿼리(0.055sec)
          1. create index member_id_age on subway.member(id, age); -> age의 범위로 찾기위해 인덱싱. 효율적인 탐색을 위해 key값 인덱스에 추가
             create index programmer_id on subway.programmer(id);  -> 데이터가 많은 covid 테이블에, join key로 사용되는 programmer_id 추가 
             create index programmer_exercise on subway.programmer(exercise); -> groupby 조건은 indexing을 하는것이 효율적이다.


    6. 
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요









### study
#### http2 protocol
1. 헤더를 압축해서 보낸다
2. Connection 1개로 여러개의 요청을 처리할수 있다(비동기적)
3. 리소스간 의존관계에 따른 우선 순위를 설정하여, 리소스 로드문제를 해결할수 있다

#### mysql indexing
1. 인덱스 설정을 해본다
   1. 설정 조건
      1. 테이블 내 데이터 양이 많고 조건 검색을 하는경우
      2. WHERE문, 결합 , ORDER BY문을 이용하는 경우
      3. NULL값이 많은 데이터로 부터 NULL이외의 값을 검색하는 경우
      4. 인덱스 시 테이블 엑세스를 최소화 하기 위해 인덱스에 primary key를 추가 (가장 앞에)
   2. 인덱스가 사용될때
      1. 컬럼값을 정수와 비교할때
      2. 컬럼값 전체로 JOIN할때
      3. 컬럼값의 범위를 요구할때
      4. LIKE로 문자열의 선두가 고정일때
      5. MIN(),MAX()
      6. ORDER BY, GROUP BY
   3. 인덱스가 사용 안될때
      1. LIKE문이 와일드 카드(*)로 시작될때
      2. DB전체를 읽는것이 빠르다고 MySQL이 판단할때
      3. WHERE과 ORDER BY의 컬럼이 다를때에는 한쪽만 사용