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
	- [BEFORE_K6_TEST_REPORT](k6/BEFORE_K6_TEST.md)
	- [AFTER_K6_TEST_REPORT](k6/AFTER_K6_TEST.md)
	- 기타 테스트 실행 js 파일 위치 : [/k6](k6)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
	- nginx 설정 변경
		+ 가용 Connection 10240로 셋팅
		+ gzip 설정
		+ Proxy 캐시 설정
		+ 8080 포트와 8082 포트로 로드 벨런싱 설정
		+ http2 설정
	- Was 성능개선 설정
		+ redis 적용 : Line 조회, 수정, 삭제 메소드에 캐싱 적용하여 반복, 동일한 값을 요청하는 내용을 메모리에 캐싱처리
		+ Thread poll 수정 : 최대 사용 Thread 수와 대기 Queue 수를 셋팅

---

### 2단계 - 조회 성능 개선하기

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- 실습 과정에서 요구하는 조회 기준 컬럼들에 index를 적용하였습니다.
- [X] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
	- [X] Coding as a Hobby 와 같은 결과를 반환하세요.
		+ `hobby`컬럼에 인덱스명 `INDEX_HOBBY`으로 INDEX 적용.
      ### 실행결과
	    ```sql
		-- 실행 로그
		18:22:06
	  
		select round(a.cnt / (a.cnt + b.cnt) * 100, 1) as Yes   
			 , round(b.cnt / (a.cnt + b.cnt) * 100, 1) as No   
		  from (select count(hobby) as cnt, hobby as title, 1 as connect from programmer where hobby = 'Yes' group by hobby) a      
			 , (select count(hobby) as cnt, hobby as title, 1 as connect from programmer where hobby = 'No' group by hobby)  b  
		 where a.connect = b.connect
		  
		 LIMIT 0, 1000	
		 1 row(s) returned	
		 0.064 sec / 0.000062 sec
		```
	  |Yes|No|
	          |---|---|
	  |80.8|19.2|
	
	  ### 실행결과
	    ```sql
		18:29:21
	  
		select a.hobby      
			 , round((case when a.hobby = 'Yes' then (a.yes_cnt / (a.yes_cnt + a.no_cnt) * 100) else (a.no_cnt / (a.yes_cnt + a.no_cnt) * 100) end), 1) as percent   
		  from (select hobby     
					 , (select count(hobby) from programmer t where hobby = 'Yes' group by hobby) as yes_cnt     
					 , (select count(hobby) from programmer t where hobby = 'No' group by hobby)  as no_cnt    
				  from programmer   
				 group by hobby) a 
		 order by a.hobby desc
		  
		LIMIT 0, 1000	
		2 row(s) returned	
		0.080 sec / 0.000015 sec
		```
	  |hobby|percent|
	          |---|---|
	  |Yes|80.8|
	  |No|19.2|
	
	- [X] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
		+ `covid` 테이블에 인덱스명 `INDEX_PROGRAMMER_ID` 으로 `programmer_id` 컬럼 INDEX 적용
		+ `covid` 테이블에 `id`컬럼 `PK`적용. (기본정렬로 조회 성능 개선이 확인 되지만, 사용자가 직접 `ORDER BY`를 지정할 경우를 위해 `PK` 설정. 오름차순 정렬의 성능히 월등히 개선됨. `250~300ms -> 10ms`대로 진입)
		+ `hospital` 테이블에 `id`컬럼 `PK` 적용.

      ### 실행결과
	    ```sql
		-- 실행로그
		18:53:21	

		select b.id     as covid_id      
			 , c.name   as hospital_name   
		  from programmer a      
			 , covid      b      
			 , hospital   c  
		 where a.id          = b.programmer_id    
		   and b.hospital_id = c.id 
		
		LIMIT 0, 1000	
		1000 row(s) returned	
		0.022 sec / 0.00082 sec
		```
	
	  |covid_id|hospital_name|
	          |---|---|
	  |1|고려대병원   |
	  |2|분당서울대병원|
	  |3|경희대병원   |
	  |4|우리들병원   |
	  |5|우리들병원   |
	  |...|...|
	
	- [X] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding|
		+ 추가 인덱스 적용은 하지 않았습니다.

      ### 실행결과
	    ```sql
		20:47:32
	  
		select b.id           as covid_id   
			 , c.name         as hospital_name      
			 , a.hobby        as hobby      
			 , a.dev_type     as dev_type     
			 , a.years_coding as years_coding   
		  from programmer  a      
			 , covid       b      
			 , hospital    c  
		 where a.hobby       = 'Yes'    
		   and a.id          = b.programmer_id    
		   and b.hospital_id = c.id    
		   and (a.student like 'Yes%' or a.years_coding like '0-2%') 
		 order by a.id 
	  
		LIMIT 0, 1000
		1000 row(s) returned
		0.041 sec / 0.099 sec
		```
	
	  |covid_id|hospital_name|hobby|dev_type|years_coding|
	          |---|---|---|---|---|
	  |5 |우리들병원    |Yes|Data or business analyst;Desktop or enterprise applications developer;Game or graphics developer;QA or test developer;Student|6-8 years|
	  |8 |강남성심병원   |Yes|Designer;Front-end developer;QA or test developer|0-2 years|
	  |12|우리들병원    |Yes|Back-end developer;Front-end developer;Full-stack developer|0-2 years|
	  |20|중앙대병원    |Yes|Back-end developer;Front-end developer;Full-stack developer;QA or test developer;Student|3-5 years|
	  |...|...|...|...|...|
	
	- [X] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
		+ `hospital`에 `name`, `member`에 `age`, `programmer`에 `country`, `covid`에 `hospital_id`를 인덱스로 추가하였습니다.
      ### 실행결과
	    ```sql
		00:01:21
		
		select a.stay, a.cnt   
		  from (select b.stay
					 , count(*) as cnt     
				  from programmer a     
					 , covid b     
					 , hospital c     
					 , member d    
				 where a.country = 'India'      
				   and a.id = b.programmer_id      
				   and b.hospital_id = c.id      
				   and c.name = '서울대병원'      
				   and b.member_id = d.id      
				   and d.age >= 20      
				   and d.age < 30    
				   group by b.stay    
				   order by null   
				) a  
		 order by a.stay
		 
		LIMIT 0, 1000
		10 row(s) returned
		0.066 sec / 0.000034 sec
		```
	  |stay|cnt|
	          |---|---|
	  |0-10|3   |
	  |11-20|25 |
	  |21-30|30 |
	  |31-40|18 |
	  |41-50|2  |
	  |51-60|17 |
	  |71-80|6  |
	  |81-90|1  |
	  |91-100|1 |
	  |More than 100 Days|2|
	
	- [X] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
		+ 별도의 추가 인덱스는 없었습니다.
      ### 실행결과
	    ```sql
		00:17:37
		
		select a.exercise
			 , count(*) as cnt   
		  from programmer a   
			 , covid b   
			 , hospital c   
			 , member d  
		 where a.id = b.programmer_id    
		   and b.hospital_id = c.id    
		   and c.name = '서울대병원'    
		   and b.member_id = d.id    
		   and d.age >= 30    
		   and d.age < 40  
		 group by a.exercise
		
		LIMIT 0, 1000
		5 row(s) returned
		0.095 sec / 0.000032 sec
		
		```
	  |exercise|cnt|
	          |---|---|
	  |1 - 2 times per week|171|
	  |3 - 4 times per week|113|
	  |Daily or almost every day|91|
	  |I don\'t typically exercise|223|
	  |NA|219|
	
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

