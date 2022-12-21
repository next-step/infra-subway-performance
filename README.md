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

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
