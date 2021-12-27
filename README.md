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

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기

#### 1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

##### - 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

<u>쿼리</u>
```sql
select b.사원번호, b.이름,c.직급명, d.입출입시간, d.지역, d.입출입구분, d.입출입시간
from (
       select c.사원번호, max(c.연봉) 연봉
       from 부서 a
              join 부서관리자 b on a.부서번호 = b.부서번호
              join 급여 c on c.사원번호 = b.사원번호
       where a.비고 = 'active'
         and now() BETWEEN b.시작일자 AND b.종료일자
         and c.시작일자 between b.시작일자 AND b.종료일자
       group by c.사원번호
       order by 연봉 desc
         limit 5
     ) a
       join 사원 b on b.사원번호 = a.사원번호
       join 직급 c on c.사원번호 = a.사원번호
       join 사원출입기록 d on d.사원번호 = a.사원번호
where d.입출입구분  = 'O'
  and now() BETWEEN c.시작일자 AND c.종료일자;
```

<u>인덱스</u>

- 사원출입기록.사원기록에 인덱스 추가

```sql
create index 사원출입기록_사원번호_index on 사원출입기록 (사원번호);
```

##### - Coding as a Hobby 와 같은 결과를 반환하세요.

<u>쿼리</u>

```sql
-- Coding as a Hobby
select	round(hobby_sum / total * 100, 1) as "Yes",
		round((total - hobby_sum) / total * 100, 1) as "No"
from (
	select sum(case when hobby = 'Yes' then 1 else 0 end) hobby_sum,
	       count(*) total
	from programmer p
) p;
```

<u>조치사항</u>

- programmer.hobby에 인덱스 설정
  - create index programmer_hobby_index on programmer (hobby);

##### - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

<u>쿼리</u>
```sql
select c.id, h.name 
from covid c
	join hospital h on h.id = c.hospital_id
	join programmer p on p.id = c.programmer_id;
```

<u>조치사항</u>

- programmer.id에 PK 설정
  - ALTER TABLE programmer ADD PRIMARY KEY (id);

##### - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

<u>쿼리</u>

```sql
select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from covid c
	join hospital h on h.id = c.hospital_id
	join programmer p on p.id = c.programmer_id
where p.hobby = 'Yes'
and p.years_coding = '0-2 years';
```

<u>조치사항</u>

- programmer에 hobby, years_coding 복합 인덱스 설정
  - create index programmer_hobby_years_coding_index on programmer (hobby, years_coding);
- hospital.id에 PK 설정
  - ALTER TABLE hospital ADD PRIMARY KEY (id);

##### - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

<u>쿼리</u>

```sql
select c.stay, count(*) cnt
from covid c
	join hospital h on h.id = c.hospital_id
	join programmer p on p.id = c.programmer_id
	join `member` m on m.id = c.member_id
where h.id = 9 /* 서울대병원 */
and p.country = 'India'
and m.age BETWEEN 20 and 29
group by c.stay;
```

<u>조치사항</u>

- member.id에 PK 설정
  - ALTER TABLE member ADD PRIMARY KEY (id);
- programmer.stay에 인덱스 설정
  - create index programmer_stay_index on programmer (stay);

##### - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

<u>쿼리</u>

```sql
select p.exercise, count(*) cnt
from covid c
	join hospital h on h.id = c.hospital_id
	join programmer p on p.id = c.programmer_id
	join `member` m on m.id = c.member_id
where h.id = 9 /* 서울대병원 */
and m.age BETWEEN 30 and 39
group by p.exercise;

```

<u>조치사항</u>

- 조치사항 없음

3. 페이징 쿼리를 적용한 API endpoint를 알려주세요

