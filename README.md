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
프로젝트 내 k6 폴더에 정리해두었습니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- nginx gzip 압축, cache, HTTP/2 설정을 통해 리버스 프록시를 개선했습니다.
- redis와 Spring Data Cache를 이용하여 애플리케이션 내 조회 기능에 캐싱을 적용해 조회 성능을 개선했습니다.

<br/>
---

### 2단계 - 스케일 아웃
#### 기능 구현 목록
- [x] gzip 설정하기
- [x] 캐싱 설정
  - [x] 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증하기
  - [x] css 확장자 파일의 경우 max-age를 1년, js인 경우 no-cache, private 설정하기
  - [x] 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?   
  > 캐시가 필요하지 않은 상황이라면 no-cache, no-store 설정을 동시에 진행할 수 있을 것 같습니다.   
  > 아래 참고한 내용에 따르면 HTTP 스펙이라는 것이 모호한 부분이 존재하고, 
  > HTTP1.1을 지원하지만 조금 오래된 브라우저의 호환, 그리고 버그 등의 문제들로 인해   
  > 구글이나 주요 네이버 등 메이저 사이트의 응답에는 캐시가 필요하지 않은 상황에서는
  > no-cache와 no-store를 동시에 사용하는 것을 확인할 수 있었습니다.   
  > 따라서 정적 자원도 필요한 경우 설정할 수 있지 않을까 라는 생각이 듭니다!..   
  > [참고](https://www.inflearn.com/questions/112647/no-store-%EB%A1%9C%EB%8F%84-%EC%B6%A9%EB%B6%84%ED%95%A0-%EA%B2%83-%EA%B0%99%EC%9D%80%EB%8D%B0-no-cache-must-revalidate-%EB%8A%94-%EC%99%9C-%EA%B0%99%EC%9D%B4-%EC%B6%94%EA%B0%80%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B8%EA%B0%80%EC%9A%94)
  

- [x] Launch Template 작성하기
- [x] 로드 밸런서 생성하기
- [x] Auto Scaling Group 생성하기
- [x] Smoke, Load, Stress 테스트 후 결과를 기록하기  

<br/>

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0b2cdf2e301dfb7b1  

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
해당 프로젝트에 auto-scale 폴더에 추가 해두었습니다.

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
select
        t.id as '사원번호',
        t.name as '이름',
        t.position as '직급명',
        t.annual_income as '연봉',
        r.time as '입출입시간',
        r.region as '지역',
        r.record_symbol as '입출입구분'
from record r,
     (select
        e.id as id,
        e.last_name as name,
        p.position_name as position, 
        s.annual_income as annual_income
      from employee e
        inner join manager m on e.id = m.employee_id and m.end_date > now()
        inner join position p on e.id = p.id and p.end_date > now()
        inner join salary s on s.id = e.id and s.end_date > now()
        inner join department d on d.id = m.department_id and d.note = 'active'
      where e.join_date < now()
      order by s.annual_income desc limit 5) t
where r.employee_id = t.id and r.record_symbol = 'O';
```
실행 결과
- 프로젝트 내 step3 폴더에 result.png 파일을 추가 해두었습니다.

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- 프로젝트 내 step4 폴더에 문제별로 결과를 추가해두었습니다.

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
