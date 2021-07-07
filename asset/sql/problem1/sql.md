
```sql
SELECT (COUNT(id) / (SELECT COUNT(id) FROM programmer) * 100) as 'Hobby'
FROM programmer
GROUP BY hobby;
```

### 인덱스
```sql
CREATE INDEX idx_programmer ON programmer (hobby);
```
