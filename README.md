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


## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 2단계 - 조회 성능 개선하기
####1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
##A. 쿼리 최적화
1) 조회 건수를 줄이자
   -> 최대한 조회 건수를 줄여나가는 것이 좋을거라고 생각해서
   MANAGER인 사람들이 데이터의 기준이 되니깐 부서관리자 테이블과 부서로 처음 JOIN 하였습니다.

2) EXPLAIN을 통해서 plan을 확인하고, type을 ALL인 부분을 index로 바꾸는 작업을 하였습니다.
* before query
![beforeQuery](./tuning/before/before_query.png)
* before result
![beforeResult](./tuning/before/before_result.png)
* before explain
![beforeExplain](./tuning/before/before_explain.png)
---

* after create index

  ![afterCreateIndex](./tuning/after/after_create_index.png)

* after query
  ![afterQuery](./tuning/after/after_query.png)
* after result
  ![afterResult](./tuning/after/after_result.png)
* after explain
  ![afterExplain](./tuning/after/after_explain.png)

##B. 인덱스 설계
---
####2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
