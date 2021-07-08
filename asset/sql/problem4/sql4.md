```sql
select stay, count(p.id) cnt
from hospital h
join covid c on h.id = c.hospital_id
join programmer p on c.programmer_id = p.id
join member m on c.member_id = m.id
where h.name ='서울대병원' and p.country = 'India' AND m.age between 20 AND 29
group by c.stay;
```

```sql
create index idx_covid on covid (hospital_id, programmer_id, stay);
create index idx_programmer_country on programmer (country);
```
