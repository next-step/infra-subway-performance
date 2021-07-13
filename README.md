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
    - 아래 폴더 참조
        - smoke/script
        - smoke/result

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

```text
1. nginx 설정 변경
  - reverse-proxy 개선
  - http/2 사용
  - content gzip 압축

2. 캐시 설정 (redis)
  - Line 관련 서비스에 캐시 적용

3. 비동기 설정
  - Thread pool 설정 (최대 개수, queue 설정 등) 
```

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    - data가 포함된 mysql 설치: `docker run -d -p 13306:3306 brainbackdoor/data-subway:0.0.2`
    - Coding as a Hobby 와 같은 결과를 반환
        - programmer table에 `hobby` 단일 컬럼으로 인덱스 생성하는 것은 낭비(분포도가 낮음)
        
        ```sql
        select (count(id) / (select count(1) from programmer)) * 100 as count
        from programmer
        group by hobby
        order by hobby desc; 
        ```
           
    -  프로그래머별로 해당하는 병원 이름을 반환
        - PK는 별도로 인덱스를 생성하지 않아도 자동 인덱싱(별도의 인덱스 추가 없어도 빠르게 탐색)
        - programmer_id 가 null인 데이터가 존재하여 조건에 추가

        ```sql
        select c.id as covid_id, c.programmer_id, h.name hospital_name
        from covid c
        inner join hospital h on c.hospital_id = h.id
        where programmer_id is not null;
        ```    

    - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬
        - (프로그래밍이 취미인 학생), (프로그래밍이 취미인 0-2년 주니어) 반환 필요
        - hobby는 'Yes', 'No' 두 가지 타입만 존재하고(Coding as a Hobby에서 증명) student는 최소 2개 이상
        - 따라서 상대적으로 분포도가 큰 순서대로인 student, hobby 복합 인덱스를 생성
        - years_coding, hobby 복합 인덱스도 생성
        - OR은 인덱스를 타지 않으므로 student, hobby 조건과 years_coding, hobby 조건으로 검색한 뒤(커버링 인덱스) union
        - 이 과정에서 각 id는 이미 정렬되어 있는 상태이므로 별도의 order by 불필요
    
        ```sql
        select p.id, c.name
        from (
             select id
             from programmer
             where student LIKE 'Yes%' and hobby = 'Yes'
        
             union
        
             select id
             from programmer
             where years_coding = '0-2 years' and hobby = 'Yes'
        ) p
        inner join (
            select programmer_id, h.name
            from covid c
            inner join (
                select id, name
                from hospital
            ) as h on h.id = c.hospital_id
        ) c on c.programmer_id = p.id;
        ```
      
    - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계
        - group by가 사용되어야 하므로 먼저 탐색 대상을 줄여야 한다.
        - programmer.country, member.age로 인덱스 생성
        - 위에서 만든 인덱스로 검색한 결과를 첫 번째 from으로 사용
        - 서울대병원에 입원한 member 추출 결과를 inner join table로 사용

        ```sql
        select c.stay, count(p.id)
        from (
             select p.member_id id
             from programmer p
             inner join member m on p.member_id = m.id
             where p.country = 'India' and m.age between 20 and 29
        ) p
        inner join (
            select c.member_id, c.stay
            from covid c
            inner join hospital h on c.hospital_id = h.id
            where h.name = '서울대병원'
        ) c on c.member_id = p.id
        group by c.stay;
        ```
      
    - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계
        - 위와 같은 방법으로 조회
        - group by 대상이 exercise로 변경
        
        ```sql
        select p.exercise, count(p.id)
        from (
            select p.member_id id, p.exercise
            from programmer p
            inner join member m on p.member_id = m.id
            where m.age between 30 and 39
        ) p
        inner join (
            select c.member_id, c.stay
            from covid c
            inner join hospital h on c.hospital_id = h.id
            where h.name = '서울대병원'
        ) c on c.member_id = p.id
        group by p.exercise;
        ```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
    - GET /favorites
