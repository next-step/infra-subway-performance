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

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```
select 사원출입기록.사원번호, max(활동중인부서관리자이름연봉직급.이름) as 이름,  max(활동중인부서관리자이름연봉직급.연봉) as 연봉, max(활동중인부서관리자이름연봉직급.직급명) as 직급명, 사원출입기록.지역, max(사원출입기록.입출입구분) as 입출입구분, max(사원출입기록.입출입시간) as 입출입시간
from tuning.사원출입기록 as 사원출입기록
inner join
	(select 활동중인부서관리자이름연봉.사원번호, max(직급.직급명) as 직급명, max(활동중인부서관리자이름연봉.이름) as 이름, max(활동중인부서관리자이름연봉.연봉) as 연봉
	from tuning.직급 as 직급
	join
		(select 급여.사원번호, 활동중인부서관리자이름.이름, 급여.연봉
		from tuning.급여 as 급여
		join
			(select 사원.사원번호, 사원.이름
			from tuning.사원 as 사원
			join 
				(select 부서관리자.사원번호
				from tuning.부서관리자 as 부서관리자
				join 
				(SELECT 부서번호 FROM tuning.부서
				where 비고 = 'active') 활동부서
				on 활동부서.부서번호 = 부서관리자.부서번호
				where 부서관리자.종료일자 = '9999-01-01') 활동중인부서관리자
				on 사원.사원번호 = 활동중인부서관리자.사원번호) 활동중인부서관리자이름
		on 활동중인부서관리자이름.사원번호 = 급여.사원번호) 활동중인부서관리자이름연봉
	on 직급.사원번호 = 활동중인부서관리자이름연봉.사원번호
    group by 사원번호
    order by 연봉 desc
    limit 5) 활동중인부서관리자이름연봉직급
on 사원출입기록.사원번호 = 활동중인부서관리자이름연봉직급.사원번호
group by 사원출입기록.사원번호, 사원출입기록.지역;
```
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

아래와 같이 인덱스를 설정해줬습니다.
```
ALTER TABLE subway.hospital
MODIFY COLUMN id bigint(20) PRIMARY KEY;

ALTER TABLE subway.covid
MODIFY COLUMN id bigint(20) PRIMARY KEY;

ALTER TABLE subway.programmer
MODIFY COLUMN id bigint(20) PRIMARY KEY;

ALTER TABLE subway.covid ADD INDEX stay (stay, id);

ALTER TABLE subway.hospital ADD INDEX name ( name, id );

ALTER TABLE subway.programmer ADD INDEX country ( country,id );

ALTER TABLE subway.member ADD INDEX age ( age, id );

ALTER TABLE subway.covid ADD INDEX programmer_id ( programmer_id, id );

ALTER TABLE subway.programmer ADD INDEX p_where ( years_coding_prof, student );
```
- Coding as a Hobby 와 같은 결과를 반환하세요.
```
SELECT hobby, count(hobby) * 100 / (select count(*) from subway.programmer ) as ratio
FROM subway.programmer group by hobby
order by ratio desc;
```


- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```
select c.id, h.name
from subway.covid as c
join subway.hospital as h
on h.id = c.hospital_id;
```
  

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```
SELECT c.id, h.name, p.hobby, p.dev_type, p.years_coding FROM subway.covid as c
join subway.hospital as h
on h.id = c.hospital_id
join subway.programmer as p
on p.id = c.programmer_id
where (p.years_coding_prof = '0-2 years' or p.student != 'No') and !(p.years_coding_prof = 'NA' and p.student = 'NA')
order by p.id;
```
  
- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```
select c.stay, count(c.stay)
from subway.covid as c
join 
(SELECT p.id
from subway.programmer as p
join
	(SELECT member.id
	from subway.member
    where member.age between 20 and 30) m
on m.id = p.id 
where p.country = 'India' ) as p
on c.id = p.id
where c.hospital_id =
(select h.id
from subway.hospital as h
where h.name = '서울대병원')
group by c.stay;
```

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```


select p.exercise,p.id
from subway.programmer as p
join 
(select m.id
from subway.member as m
where m.age between 30 and 40) m
on m.id = p.id
join
(select c.programmer_id
from subway.covid as c
where c.hospital_id = 9) c
on c.programmer_id = p.id;
```
### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
