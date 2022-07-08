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
url: https://yang-infra-subway.p-e.kr/  
결과: mission/step1 디렉토리에 첨부


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선
  - gzip 압축, Cache, TLS, HTTP/2 설정
- WAS 성능 개선
    - Redis Spring Data Cache

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

[yangseungin-template](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-021d4737b6300b06c)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)  
[서버자원사용](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/cloudwatch%20%EC%84%9C%EB%B2%84%EC%9E%90%EC%9B%90%EC%82%AC%EC%9A%A9.png)  
[인스턴스생성](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4%EC%A6%9D%EA%B0%80.png)
[cloudwatch인스턴스증가](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/cloudwatch%20%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4%EC%A6%9D%EA%B0%80.png)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)  
[smoke](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/smoke.png)  
[load](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/load.png)  
[stress_750](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/stress_vuser_750.png)  
[stress_1000](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/stress_vuser_1000.png)  
[stress_1200](https://github.com/yangseungin/infra-subway-performance/blob/step2/mission/step2/stress_vuser1200.png)  

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```mysql
select adms.id as 사원번호,
       adms.last_name as 이름,
       adms.annual_income as 연봉,
       adms.position_name as 직급명,
       r.time as 입출입시간,
       r.region as 지역,
       r.record_symbol as 입출입구분
from record r
       inner join (
  select adm.id, adm.last_name, s.annual_income, adm.position_name
  from salary s
         inner join (
    select e.id, e.last_name, p.position_name
    from department d
           inner join manager m on d.id = m.department_id and d.note = 'active'
           inner join employee e on e.id = m.employee_id and m.end_date >= now()
           inner join position p on e.id = p.id and p.position_name = 'manager'
  ) adm
                    on s.id = adm.id and s.end_date >= now()
  order by s.annual_income desc
  limit 5
) adms
on r.employee_id = adms.id and r.record_symbol = 'O';


```
[실행결과](https://github.com/yangseungin/infra-subway-performance/blob/step3/mission/step3/%EC%8B%A4%ED%96%89%EA%B2%B0%EA%B3%BC.png)
---

### 4단계 - 인덱스 설계
인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
실행시간과 실행계획은 mission/step4 디렉토리에 첨부하였습니다.

4-1. Coding as a Hobby 와 같은 결과를 반환하세요.
```mysql
select hobby, round(count(hobby) * 100 / (select count(*) from programmer), 1) a
from programmer
group by hobby;
```
변경전  
시간: 598ms  
query cost: 99594

변경후  
hobby에 인덱스 추가  
```mysql
CREATE INDEX idx_programmer_hobby on subway.programmer (hobby);
```
시간: 76ms  
query cost: 28384

4-2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)


- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)


- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)



- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)





---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
