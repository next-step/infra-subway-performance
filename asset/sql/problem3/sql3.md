```sql
SELECT p.id, h.name
FROM programmer p
         JOIN covid c on p.id = c.programmer_id
         JOIN hospital h on h.id = c.hospital_id
WHERE p.hobby='Yes' AND (p.student LIKE 'YES%' OR p.years_coding = '0-2 years')
order by p.id;;
```

```sql
create index idx_programmer on(hobby, student, years_coding)
```
