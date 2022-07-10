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
- Smoke Test
    - 적용 전
![적용전](loadtest/step1/before/smoke_result_k6.PNG)
    - 적용 후
![적용후](loadtest/step1/after/smoke_result_k6.PNG)
- Load Test
    - 적용 전
![적용전](loadtest/step1/before/load_result_k6.PNG)
    - 적용 후
![적용후](loadtest/step1/after/load_result_k6.PNG)
- Stress Test
    - 적용 전
![적용전](loadtest/step1/before/stress_result_k6.PNG)
    - 적용 후
![적용후](loadtest/step1/after/stress_result_k6.PNG)    
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선
    - gzip 압축
    - cache 설정
    - TLS, HTTP/2 설정
- Was 개선
    - Redis Server, Spring Data Cache 적용
---

### 2단계 - 스케일 아웃
1. Launch Template 링크를 공유해주세요.  
https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0ad25643698f5a02d

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
![EC2 추가생성 결과](./loadtest/step2/instance_count.PNG)

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- Smoke Test
![smoke](./loadtest/step2/smoke_test.PNG)
- Stress Test
![smoke](./loadtest/step2/stress_test.PNG)
---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```mysql
select 
	e.id                as '사원번호',
	e.last_name         as '이름',
	top5.annual_income  as '연봉',
	p.position_name     as '직책명',
	r.time              as '입출입시간',
	r.region            as '지역',
	r.record_symbol     as '입출입구분'
from employee e
inner join (
	select s.id, s.annual_income
	from salary s
	where id in (select employee_id from manager
		where department_id in (select id from department where note = 'Active')
		and start_date <= now() 
		and end_date >= now()
	)
	and s.start_date <= now() 
	and s.end_date >= now()
	order by s.annual_income desc
	limit 5
) top5 on top5.id = e.id
inner join record r on r.employee_id = e.id and record_symbol = 'O'
inner join position p on p.id = e.id and p.start_date <= now() and p.end_date >= now();
```
- 실행결과
![결과](./docs/query.PNG)
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
