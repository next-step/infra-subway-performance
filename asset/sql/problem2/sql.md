```sql
SELECT p.id, h.name
FROM programmer p
JOIN covid c ON p.id = c.programmer_id
JOIN hospital h ON h.id = c.hospital_id
LIMIT 0, 10;
```
```sql
create index idx_covid on covid(programmer_id);
```
