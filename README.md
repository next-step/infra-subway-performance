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

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
# explain 실행계획

# +--+-----------+----------+----------+------+---------------------------------+-------------------------+-------+--------------------------+------+--------+---------------------------------------------------------+
# |id|select_type|table     |partitions|type  |possible_keys                    |key                      |key_len|ref                       |rows  |filtered|Extra                                                    |
# +--+-----------+----------+----------+------+---------------------------------+-------------------------+-------+--------------------------+------+--------+---------------------------------------------------------+
# |1 |PRIMARY    |<derived2>|NULL      |ALL   |NULL                             |NULL                     |NULL   |NULL                      |2     |100     |NULL                                                     |
# |1 |PRIMARY    |record    |NULL      |ref   |idx_record_record_symbol         |idx_record_record_symbol |3      |const                     |329467|10      |Using index condition                                    |
# |2 |DERIVED    |department|NULL      |index |PRIMARY,idx_department_id_note   |idx_department_id_note   |135    |NULL                      |9     |11.11   |Using where; Using index; Using temporary; Using filesort|
# |2 |DERIVED    |manager   |NULL      |ref   |PRIMARY,idx_manager_department_id|idx_manager_department_id|12     |tuning.department.id      |2     |10      |Using where                                              |
# |2 |DERIVED    |position  |NULL      |ref   |PRIMARY                          |PRIMARY                  |4      |tuning.manager.employee_id|1     |10      |Using where                                              |
# |2 |DERIVED    |salary    |NULL      |ref   |PRIMARY                          |PRIMARY                  |4      |tuning.manager.employee_id|9     |10      |Using where                                              |
# |2 |DERIVED    |employee  |NULL      |eq_ref|PRIMARY                          |PRIMARY                  |4      |tuning.manager.employee_id|1     |100     |NULL                                                     |
# +--+-----------+----------+----------+------+---------------------------------+-------------------------+-------+--------------------------+------+--------+---------------------------------------------------------+


select sql_no_cache                                                                                                                                                                                            e.last_name as 이름,
       t.annual_income as 직급명,
       t.position_name as 직급명,
       r.time as 입출입시간,
       r.region as 지역,
       r.record_symbol 입출입구분
from (
         select annual_income, employee_id
              , p.position_name
         from department d
                  inner join manager m on d.id = m.department_id
                  inner join salary s on m.employee_id = s.id
                  inner join position p on m.employee_id = p.id
         where d.note = 'active'
           and p.position_name='manager'
           and p.end_date = STR_TO_DATE('9999-01-01', '%Y-%m-%d')
           and m.end_date = STR_TO_DATE('9999-01-01', '%Y-%m-%d')
           and s.end_date = STR_TO_DATE('9999-01-01', '%Y-%m-%d')
         order by annual_income desc limit 5
     ) t
         join employee e on t.employee_id = e.id
         join record r on t.employee_id = r.employee_id
where r.record_symbol = 'O'
                                                                                                                                                                                                                       [2022-12-23 00:41:09] [HY000][1681] 'SQL_NO_CACHE' is deprecated and will be removed in a future release.
[2022-12-23 00:41:09] 14 rows retrieved starting from 1 in 387 ms (execution: 335 ms, fetching: 52 ms)

```

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
