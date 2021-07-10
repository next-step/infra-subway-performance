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
    1. /k6Test/smoke
    2. /k6Test/load
    3. /k6Test/stress

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    1. reverse-proxy를 개선하여 캐시 설정을 하고 
        하나의 연결로 여러 클라이언트에게 응답을 보내는 http2 설정을 했습니다.
       (was가 여러 서버에 있어 연결이 적은 곳으로 로드밸런싱역할을 수행하면 
       더 개선될 것이라 생각합니다.)
   2. cache 활용 , 같은 내용의 요청과 응답을 지속적인 connection과 disconnection을 
    줄이기 위해 redis를 이용해 cache 기능을 활용했습니다. value의 cache 에 자주 사용하는
      메서드와 key로 요청과 응답을 줄였습니다.

---

### 2단계 - 조회 성능 개선하기

#### 요구사항 정의
- [x] 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- [x] 즐겨찾기 페이지에 페이징 쿼리를 적용
    * 로그인한 사용자는 최근에 추가한 즐겨찾기만 관심이 있기에 한번에 5개의 즐겨찾기만 보고 싶다.
- [x] 데이터베이스 이중화
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    * 키를 사용하지 않아 조회 횟수가 많고 속도가 느릴 것이라 생각한 곳에 index를 추가했습니다.
        * ```CREATE INDEX IDX_SECTION_PATH ON subway.section (down_station_id, up_station_id);```
          * Line table 과 조인 하기 위해 line_id index 추가
        ![img.png](img.png)
        * ```CREATE INDEX idx_member_01 ON subway.member (email);```
    1. Coding as a Hobby 와 같은 결과를 반환하세요.
        * hobby에 index를 걸어 주었습니다.
        ```
        create index idx_subway_hoddy on subway.programmer (hobby);
       
        select  ((select count(hobby) from subway.programmer where hobby = 'Yes') / count(hobby)) * 100 as 'Yes',
        ((select count(hobby) from subway.programmer where hobby = 'No') / count(hobby)) * 100 as 'No'
        from subway.programmer;
        ```
    2. 프로그래머별로 해당하는 병원 이름을 반환하세요.
        * 두 테이블이 key로 join을 하기 때문에 인덱스를 따로 걸진 않았습니다.
        ```
        select covid.id as "ProgrammerId", hospital.name "HospitalName"
        from subway.covid as covid
        left join subway.hospital as hospital on covid.id = hospital.id;
        ```
    3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name,
       * id 가 key값으로 자동 정렬 되어 order by 는 넣지 않았습니다
        ```
        select *
        from (
        select id as id
        from subway.programmer p
        where p.hobby = 'yes'
        union
        select id as id
        from subway.programmer a
        where a.years_coding = '0-2 years'
        ) a join (
        select c.programmer_id, h.name
        from subway.covid c
        join subway.hospital h on c.hospital_id = h.id
        ) b on a.id = b.programmer_id;
        ```
    4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
       * 다른 쿼리를 먼저하느라 인덱스가 잡혀있어서 하나만 잡았습니다
        ```
        create index idx_programmer_country on subway.programmer (country);
       
        select stay
        , count(m.id)
        from subway.member m
        join (select member_id FROM subway.programmer WHERE country = 'India') p on m.id = p.member_id
        join (select c.member_id, c.stay from subway.covid c left join subway.hospital h on c.hospital_id and h.name = '서울대병원') c
        on m.id = c.member_id
        where age between 20 and 29
        group by stay;

        ```
    5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
       * subway name을 fulltext 인덱스로 잡고 programmer_id / hospital_id 를 인덱스로
        age를 인덱스로 설정했습니다
        ```
        create fulltext index idx_hospital_name on subway.hospital (name);
        create index idx_member_age on subway.member (age);
        create index idx_covid_ph on subway.covid (programmer_id, hospital_id);  
       
        select exercise
        , count(m.id)
        from subway.member m
        join subway.covid c on m.id = c.member_id
        join subway.programmer p on c.programmer_id = p.id
        join subway.hospital h on c.hospital_id = h.id and h.name = '서울대병원'
        where age between 30 and 39
        group by exercise;
        ```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
    * https://chungsun.kro.kr/favorites
    ![img_2.png](img_2.png)
    

