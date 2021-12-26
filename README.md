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
 - `/k6/public1/scenario3_result.md` -> 캐시 미적용 인스턴스 결과
 - `/k6/public2/scenario3_result.md` -> 캐시 적용 인스턴스 결과
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   `단건 조회` 시 캐시를 적용하였습니다.
   station, line 에 대해 캐시를 적용하여 경로 조회 시 성능을 개선하였습니다.
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    1) 쿼리를 작성한다.
    2) mysql `explain` 과 workbench 에서 제공하는 `explain ui` 기능으로 쿼리 성능 및 cost를 검증한다.
    3) full scan 에 대한 부분에 인덱스를 사용한다.
    4) 조인 순서를 변경하여 성능을 개선한다.

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
    1) `/lines/page`
    2) `/stations/page`

--- 

### 2단계 요구사항 

#### A.쿼리 최적화
 - 쿼리 작성만으로 1s 이하로 반환한다.
 - 인덱스 설정을 추가하여 50 ms 이하로 반환한다.
 - [x] 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
   - (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
   - 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.
   - result
     - ![image info](./images/a/image.png)
     
     - `부서.비고` 인덱스 설정 
     - `부서관리자.종료일자` 인덱스 설정
     - `사원출입기록.입출입구분&사원번호` 인덱스 설정

#### B. 인덱스 설계
 * 요구사항
- [x] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
   - [x] Coding as a Hobby 와 같은 결과를 반환하세요.
   - result 
     - ![image info](./images/b/image1.png)
     
     - `programmer.hobby` 인덱스 설정
     - `programmer.id` pk 설정 
   
   - [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
   - result
     - ![image info](./images/b/image2.png)
    
     - `hospital.id` pk 설정
     - `hospital.name` not null, varchar 타입으로 변경
     - `covid.programmer_id` 인덱스 설정
     
   - [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
   - result
      - ![image info](./images/b/image3.png)
            
      - `programmer.hobby&student&years_coding` 인덱스 설정  

   - [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
   - result 
      - ![image info](./images/b/image3.png)

      - `member.age`  인덱스 설정 
     
   - [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
   - result
      - ![image info](./images/b/image3.png)
     
      - 추가 설정 없음.

#### C. 페이징 쿼리
 - [x] 페이징 쿼리 구현
    - /lines/page
    - /stations/page

#### D. MySQL Replication with JPA
 - [x] replication 설정