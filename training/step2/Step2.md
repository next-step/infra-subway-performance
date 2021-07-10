# 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
1. 실행쿼리

```sql
select c.id, h.name from subway.covid as c join hospital as h on c.hospital_id = h.id;
```

![인덱스생성전](./step2_before_index.png)

소요시간 : 0.049 sec

2. 인덱스 생성

covid테이블의 hospital_id의 값을 인덱스로 추가하였고 hospital에서 id를 PK설정과 name을 UK로 설정하였습니다.

![인덱스생성후](./step2_after_index.png)

소요시간 : 0.036 sec

