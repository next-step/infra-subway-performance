## 개선전
```sql
SELECT (total_cnt - yes_cnt) / total_cnt * 100 'hobby'
         FROM
        (
         SELECT count(id) yes_cnt
         FROM programmer
         WHERE hobby = 'YES'
     ) t1,
              (
         SELECT count(id) total_cnt
         FROM programmer
     )t2
UNION 
SELECT yes_cnt / total_cnt * 100 'hobby'
         FROM
        (
         SELECT count(id) yes_cnt
         FROM programmer
         WHERE hobby = 'YES'
     ) t1,
              (
         SELECT count(id) total_cnt
         FROM programmer
     )t2;

```

### 인덱스
```sql
CREATE INDEX idx_programmer ON programmer (hobby);
```
