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

#### A. 쿼리 최적화
1. 쿼리 최적화 요구사항

```TEXT
- 쿼리 작성만으로 1s 이하로 반환한다.
- 인덱스 설정을 추가하여 50 ms 이하로 반환한다.
```
2. 퀴리 시간 결과

```TEXT
- 인덱스 설정 전 : 0.422s
- 인덱스 설정 후 : 0.000s -> 사원출입기록 테이블에 사원번호 인덱스 생성 후 측정

참고) ALTER TABLE `tuning`.`사원출입기록` ADD INDEX `I_사원번호` (`사원번호` ASC);
```

3. 퀴리문

```TEXT
SELECT 관리자연봉TOP5.사원번호,
       관리자연봉TOP5.이름,
       관리자연봉TOP5.연봉,
       관리자연봉TOP5.직급명,
       F.입출입시간,
       F.지역,
       F.입출입구분
FROM   (SELECT B.사원번호,
               E.이름,
               D.연봉,
               C.직급명
        FROM   부서 A
               JOIN 부서관리자 B
                 ON A.부서번호 = B.부서번호
                    AND A.비고 = 'active'
                    AND B.종료일자 = '9999-01-01'
               JOIN 직급 C
                 ON B.사원번호 = C.사원번호
                    AND C.직급명 = 'Manager'
               JOIN 급여 D
                 ON C.사원번호 = D.사원번호
                    AND C.종료일자 = D.종료일자
               JOIN 사원 E
                 ON D.사원번호 = E.사원번호
        ORDER  BY D.연봉 DESC
        LIMIT  5) 관리자연봉TOP5
       JOIN 사원출입기록 F
         ON 관리자연봉TOP5.사원번호 = F.사원번호
            AND F.입출입구분 = 'o'
ORDER  BY 관리자연봉TOP5.연봉 DESC,
          F.입출입시간 DESC;
```

#### B. 인덱스 설계
#### 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환

1. Coding as a Hobby 와 같은 결과를 반환하세요.

```TEXT
인덱스 설정 후 쿼리 시간(image/B_1_after.png)
 - Duration : 0.078s
 - Fetch : 0.000s
인덱스 설정 전 쿼리 시간(image/B_1_before.png)
 - Duration : 4.641s
 - Fetch : 0.000s

쿼리문
SELECT hobby,
       Round(( Count(hobby) / (SELECT Count(*)
                               FROM   programmer) * 100 ), 1) AS response
FROM   programmer
GROUP  BY hobby
ORDER  BY hobby DESC; 

programmer 테이블의 id 칼럼 PK 설정
programmer 테이블의 hobby 칼럼 인덱스 설정
```

2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

```TEXT
인덱스 설정 후 쿼리 시간(image/B_2_after.png)
 - Duration : 0.000s
 - Fetch : 0.016s
인덱스 설정 전 쿼리 시간(image/B_2_before.png)
 - Duration : 0.015s
 - Fetch : 0.016s

SELECT cv.id,
       hp.name
FROM   programmer pg
       JOIN covid cv
         ON pg.id = cv.programmer_id
       JOIN hospital hp
         ON hp.id = cv.hospital_id; 
         
hospital 테이블의 id PK 설정
covid 테이블의 id PK 설정    
```

3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

```TEXT
인덱스 설정 후 쿼리 시간(image/B_3_after.png)
 - Duration : 0.016s
 - Fetch : 0.047s
인덱스 설정 전 쿼리 시간(image/B_3_before.png)
 - Duration : 1.360s
 - Fetch : 0.000s

SELECT cv.id,
       hp.name,
       user.hobby,
       user.dev_type,
       user.years_coding
FROM   covid cv
       JOIN (SELECT *
             FROM   subway.programmer
             WHERE  hobby = 'YES'
                    AND ( student LIKE 'YES%'
                           OR years_coding = '0-2 years' )) AS user
         ON cv.programmer_id = user.id
       JOIN hospital hp
         ON cv.hospital_id = hp.id
ORDER  BY user.id; 
         
covid 테이블의 programmer_id 칼럼 인덱스 설정 

programmer 테이블은 아래 4가지 방법의 인덱스 중에서 가장 쿼리 속도가 빨랐던 1번을 선택하였습니다.(동시에 만들었을 때 인덱스도 1번 hobby 칼럼을 탐)
-> image/B_3_visual_explain.png
-> B_3_programmer_Index.png
1) programmer 테이블의 [hobby] 칼럼 인덱스 설정  
2) programmer 테이블의 [hobby > years_coding] 와 [hobby > student] 순서로 하는 인덱스 설정 
3) programmer 테이블의 [hobby > years_coding > student] 순으로 하는 인덱스 설정
4) programmer 테이블의 [hobby > student > years_coding] 순으로 하는 인덱스 설정
```


4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

```TEXT
인덱스 설정 후 쿼리 시간(image/B_4_after.png)
 - Duration : 0.125s
 - Fetch : 0.000s
인덱스 설정 전 쿼리 시간(image/B_4_before.png)
 - Duration : 3.531s
 - Fetch : 0.000s

SELECT cv.stay,
       Count(cv.stay)
FROM   covid cv
       JOIN member m
         ON cv.member_id = m.id
       JOIN hospital hp
         ON hp.id = cv.hospital_id
       JOIN programmer pg
         ON pg.member_id = m.id
WHERE  pg.country = 'India'
       AND hp.name = '서울대병원'
       AND m.age BETWEEN 20 AND 29
GROUP  BY cv.stay;  
         
covid 테이블의 hospital_id 인덱스 설정
programmer 테이블의 memeber_id 인덱스 설정
```


5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

```TEXT
쿼리시간(image/B_5.png)
 - Duration : 0.078s
 - Fetch : 0.000s

SELECT exercise,
       Count(exercise)
FROM   covid cv
       JOIN hospital hp
         ON cv.hospital_id = hp.id
       JOIN member m
         ON cv.member_id = m.id
       JOIN programmer pg
         ON cv.programmer_id = pg.id
WHERE  age >= 30
       AND age <= 39
       AND hp.name = '서울대병원'
GROUP  BY exercise; 

```

3. 페이징 쿼리를 적용한 API endpoint를 알려주세요
- http://3.35.48.111/favorites
- a@a.a/123

4. 데이터베이스 이중화 적용
