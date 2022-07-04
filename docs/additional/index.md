## 1. 페이징 쿼리를 적용한 API endpoint를 알려주세요

```http request
GET https://heowc.kro.kr/lines/pages?id={id}&pageSize={pageSize}
```

### Replication 구성

![img master](./mysql_master.png)

![img slave](./mysql_slave.png)

#### 확인

![img netstat](./netstat.png)