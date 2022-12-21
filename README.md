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
* [smoke 전](/k6/smoke/before)
* [smoke 후](/k6/smoke/after)
* [load 전](/k6/load/before)
* [load 후](/k6/load/after)
* [stress 전](/k6/stress/before)
* [stress 후](/k6/stress/after)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
*  gzip 압축, cache, TLS, HTTP/2 설정을 적용을 통해서 Reverse Proxy를 개선했습니다.
*  Redis, Spring Data Cache 사용을 통해서 WAS 성능 개선했습니다.
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
* [Launch Template](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0f6f887586835953a)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
* [Cloudwatch](/step2/cloudwatch)
```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* [smoke](/step2/smoke)
* [load](/step2/load)
* [stress](/step2/stress)
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```
select 
    t.id as '사원번호', 
    t.last_name as '이름', 
    t.annual_income as '연봉', 
    t.position_name as '직급',
    r.time as '입출입시간',
    r.region as '지역', 
    r.record_symbol as '입출입구분' 
from 
    record r
inner join (
    select 
        e.id, e.last_name, s.annual_income, p.position_name
	from 
	    employee e
    inner join position p on p.id = e.id and p.position_name = 'manager' and p.start_date <= now() and p.end_date >= now()
	inner join manager m on m.employee_id = e.id and m.start_date <= now() and m.end_date >= now()
	inner join department d on d.id = m.department_id and d.note = 'active'
	inner join salary s on s.id = e.id and s.start_date <= now() and s.end_date >= now()
	where 
	    e.join_date <= now()
	order by 
	    s.annual_income desc limit 5
) t 
on 
    t.id = r.employee_id and r.record_symbol = 'o';
```
* [결과](/step3)
---

### 4단계 - 인덱스 설계

#### 요구사항
* 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  * M1의 경우엔 시간 제약사항을 달성하기 어렵워서 2배를 기준으로 해본 후 일단 리뷰요청

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 
와 같은 결과를 반환
  * 쿼리를 작성 후 313ms가 나온 것을 확인 후 hobby에 인덱스를 적용한 뒤에 062ms 감소하였습니다.
  * [결과 이미지](/step4/1)
```
-- 쿼리
select hobby, round(count(*) / (select count(*) from programmer p) * 100, 1) AS percent
from programmer
group by hobby;

-- 인덱스 생성 쿼리
create index programmer_hobby_index on programmer (hobby);
```
* 프로그래머별로 해당하는 병원 이름을 반환 (covid.id, hospital.name)
```

```
* 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬
  (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```

```
* 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계 (covid.Stay)
```

```
* 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계 (user.Exercise)
```

```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
