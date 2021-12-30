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
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    - A. 쿼리 최적화
        1. 쿼리 작성만으로 1s 이내
        
            쿼리 실행시간: 0.33초
            
            ```sql
            select t1.사원번호, t1.이름, t1.연봉, 직급.직급명, 사원출입기록.지역, 사원출입기록.입출입구분, 사원출입기록.입출입시간
            from (
                     select 사원.사원번호, 사원.이름, 급여.연봉
                     from 사원
                              Join 부서사원_매핑 on 부서사원_매핑.사원번호 = 사원.사원번호
                              join 부서 on 부서.부서번호 = 부서사원_매핑.부서번호
                              join 급여 on 급여.사원번호 = 사원.사원번호
                              join 부서관리자 on 부서관리자.사원번호 = 사원.사원번호
                     where 부서.비고 = 'active'
                       and 부서관리자.시작일자 < NOW()
                       and 부서관리자.종료일자 > NOW()
                       and 급여.시작일자 < NOW()
                       and 급여.종료일자 > NOW()
                     order by 급여.연봉 desc
                     limit 5
                 ) t1
                     join 직급 On t1.사원번호 = 직급.사원번호
                     join 사원출입기록 on t1.사원번호 = 사원출입기록.사원번호
            where 사원출입기록.입출입구분 = 'O'
              and 직급.시작일자 < NOW()
              and 직급.종료일자 > NOW()
            order by t1.연봉 desc, 사원출입기록.입출입시간 desc
            ```
           
        2. 인덱스 설정을 추가하여 50 ms 이하로 반환한다
            사원출입기록.입출입구분 index 추가 -> 210ms
            
            부서.비고 Index 추가 -> 193ms
            
            사원출입기록.사원번호 Index추가 -> 36ms
            
    - B. 인덱스 설계
        - Coding as a Hobby 와 같은 결과를 반환하세요.
            
            311ms -> 71ms
            
            programmer.hobby 인덱스 추가
            
            ```sql
               select concat(round(100 * count(if(hobby='yes', hobby, null)) / count(*),1), '%') AS 'yes',
                      concat(round(100 * count(if(hobby='no', hobby, null)) / count(*),1), '%') AS 'no'
               from programmer
            ```
           
        - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
        
            358ms -> 5ms
            
            programmer에 PK(id) 추가
            covid.programmer_id 인덱스 추가
            covid.hospital_id 인덱스 추가
            hospital에 PK(id) 추가
            
            ```sql
            select c.id, h.name
            from programmer p
                join covid c on p.id = c.programmer_id
                join hospital h on c.hospital_id = h.id
            ```
        
        - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
        
            39ms
            
            ```sql
                select h.name
                from programmer p
                join covid c on p.id = c.programmer_id
                join hospital h on c.hospital_id = h.id
                where (p.hobby='yes' and p.dev_type = 'student') or p.years_coding='0-2 years'
                order by p.id
            ```
        
        - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
           
            317ms -> 86ms
            
            programmer.country 인덱스 추가
            member.age 인덱스 추가
            
            ```sql
            select c.stay, count(*)
            from hospital h
                join covid c on h.id = c.hospital_id
                join programmer p on c.programmer_id = p.id
                join member m on p.member_id = m.id
            where h.name = '서울대병원'
                and p.country = 'india'
                and m.age > 19 and m.age < 30
            group by c.stay
            ```
        
        - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
        
            44ms
            
            ```sql
            select p.exercise, count(*)
            from hospital h
                join covid c on h.id = c.hospital_id
                join programmer p on c.programmer_id = p.id
                join member m on p.member_id = m.id
            where h.name = '서울대병원'
              and m.age > 29 and m.age < 40
            group by p.exercise
            ```
   
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

    - /stations?page=1&size=3
