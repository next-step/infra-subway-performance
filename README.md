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

<img width="1629" alt="CleanShot 2022-02-26 at 17 03 19@2x" src="https://user-images.githubusercontent.com/37217320/155835455-62e43e00-8f17-48ce-a8de-a5c1e08c0a4d.png">

<img width="1738" alt="CleanShot 2022-02-26 at 17 04 29@2x" src="https://user-images.githubusercontent.com/37217320/155835493-c132884b-b8a7-499e-a163-3a63868232b7.png">


```mysql
select u.사원번호, u.이름, u.연봉, u.직급명, h.입출입시간, h.지역, h.입출입구분
from 사원출입기록 h
         right join (select a.사원번호, u.이름, r.직급명, a.부서번호, p.연봉
                     from 부서관리자 a
                              join (select p1.사원번호, p1.연봉
                                    from 급여 p1
                                    where p1.종료일자 > now()) p
                                   on a.사원번호 = p.사원번호
                              join 사원 u
                                   on a.사원번호 = u.사원번호
                              join 부서 d
                                   on a.부서번호 = d.부서번호
                              join (select r1.사원번호, r1.직급명
                                    from 직급 r1
                                    where r1.종료일자 > now()) as r
                                   on r.사원번호 = u.사원번호
                     where d.비고 = 'active'
                       and a.종료일자 > now()
                     order by p.연봉 desc
                     limit 5) u
                    on h.사원번호 = u.사원번호
where h.입출입구분 = 'O'
order by u.연봉 desc, h.지역;


```


---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요


#### [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은 결과를 반환하세요.
 ```mysql
select years_coding,
       ROUND((count(*) / (select count(*)
                          from programmer
                          where years_coding <> 'NA')) * 100, 1) as percentage
from programmer
where years_coding <> 'NA'
group by years_coding
order by years_coding * 1;
```

인덱스 적용
```
 create index programmer_years_coding_index
    on programmer (years_coding);
```

performance: 4.637ms -> 629ms




---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
