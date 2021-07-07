```sql
select exercise, count(p.id) cnt
from hospital h
         join covid c on h.id = c.hospital_id
         join programmer p on c.programmer_id = p.id
         join member m on c.programmer_id = m.id
where h.name ='서울대병원' and m.age between 30 AND 39
group by p.exercise;
```

```sql
create unique index idx_programmer_exercise
	on programmer (exercise);
```

