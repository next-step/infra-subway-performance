- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  - [x] [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은 결과를 반환하세요.
  - [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  - [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  - [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  - [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)



## 1. [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은 결과를 반환하세요.

![image-20210711132727484](/Users/len/Library/Application Support/typora-user-images/image-20210711132727484.png)

```sql
SELECT hobby, ( count(id) / (SELECT count(id) FROM subway.programmer) ) * 100 AS rating FROM subway.programmer GROUP BY hobby;
```

![image-20210711183649586](https://tva1.sinaimg.cn/large/008i3skNgy1gsd59zckvxj307w0dkmz3.jpg)



```sql
CREATE INDEX `idx_hobby` ON subway.programmer(hobby);
```

![image-20210711140926479](https://tva1.sinaimg.cn/large/008i3skNgy1gscxjt4tr3j31rg0dugqu.jpg)

![image-20210711183820382](https://tva1.sinaimg.cn/large/008i3skNgy1gsd5bjz79cj31oq016myh.jpg)

## 2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

```sql
SELECT c.id, hospital.name 
FROM subway.covid AS c 
JOIN subway.hospital ON hospital.id = c.hospital_id 
```

![image-20210711143027180](https://tva1.sinaimg.cn/large/008i3skNgy1gscy5p041xj30gw0f077f.jpg)



- 각 테이블(`hospital`,` covid`)에 primary key 설정
- 그 뒤에, `hospital` 테이블의 `name`이 TEXT 타입이여서 Index 설정이 아래와 같은 이슈로 불가하여- `VARCHAR(100)` 로 변경 후 `name` 에 `Index`를 설정
- covid 테이블의 hospital_id 를 `CREATE INDEX`
- hospital 테이블의 name 을 `CREATE INDEX`

![image-20210711183353370](https://tva1.sinaimg.cn/mw1024/008i3skNgy1gsd56xhbx5j60jo0hwwil02.jpg)

![image-20210711183415223](https://tva1.sinaimg.cn/mw1024/008i3skNgy1gsd57b8a07j31oa01smyu.jpg)

- 0.0020 sec 로 해결됨.

## 3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 programmer.id 기준으로 정렬하세요.

```sql
SELECT P.id, C.name
FROM (
	SELECT id
	FROM subway.programmer
    WHERE Hobby = 'Yes' AND (student LIKE 'Yes%' OR years_coding = '0-2 years')) AS P
JOIN (
	SELECT covid.programmer_id, name FROM subway.covid
	JOIN (SELECT hospital.id, name FROM subway.hospital) AS H ON H.id = covid.hospital_id   
) AS C ON C.programmer_id = P.id;
```

![image-20210711152326179](https://tva1.sinaimg.cn/large/008i3skNgy1gsczos5it4j30ry0hoq8h.jpg)

![image-20210711182147308](https://tva1.sinaimg.cn/large/008i3skNgy1gsd4ucmmcoj31os01q75r.jpg)

- covid 테이블 의 programmer_id 에 `CREATE INDEX`
- programmer 테이블의 hobby 에 `CREATE INDEX`

![image-20210711182639412](https://tva1.sinaimg.cn/mw1024/008i3skNgy1gsd4zfbrkqj30w80i4agd.jpg)

![image-20210711182652261](https://tva1.sinaimg.cn/mw1024/008i3skNgy1gsd4zmjbnwj31oq01edh5.jpg)



## 4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

```sql
SELECT stay, count(member_id)
FROM 
	(SELECT MP.member_id, stay, hospital_id 
		FROM subway.covid AS CO 
		INNER JOIN (
			SELECT member_id
            FROM subway.member AS MM 
            INNER JOIN subway.programmer ON MM.id = subway.programmer.member_id 
            WHERE age BETWEEN 20 AND 29 AND country = 'India') AS MP
	ON CO.member_id = MP.member_id
	) AS M
INNER JOIN subway.hospital AS H ON H.id = M.hospital_id
WHERE name = "서울대병원"
GROUP BY STAY;
```

![image-20210711171751411](https://tva1.sinaimg.cn/large/008i3skNgy1gsd2zsckvmj311k0mi7d0.jpg)

- programmer 테이블에 member_id 에 `CREATE INDEX` 생성

- covid 테이블에, hospital_id 에 `CREATE INDEX` 생성

**처음에는 24초가 걸렸으나, 위 Index 를 생성한 이후에는 0.051 sec** 

- `SQL` 끝에 `order by null` 을 넣어 GROUP 시 발생하는 filesort 부분 해결. 
- 마지막에 `ORDER BY NULL` 넣을시, **0.029 sec**

![image-20210711174541759](https://tva1.sinaimg.cn/large/008i3skNgy1gsd3sr9x6uj31e40mwqda.jpg)

## 5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

위 쿼리 최적화시 생성했던 모든 `Index`를 삭제 후,

```sql
SELECT exercise, count(member_id)
FROM 
	(
    SELECT MP.member_id, hospital_id, exercise
		FROM subway.covid AS CO
		INNER JOIN 
        (
			SELECT member_id, exercise
			FROM subway.member AS MM 
			INNER JOIN subway.programmer ON MM.id = subway.programmer.member_id 
			WHERE age BETWEEN 30 AND 39 
		) AS MP
	ON CO.member_id = MP.member_id
	) AS M
INNER JOIN subway.hospital AS H ON H.id = M.hospital_id
WHERE name = "서울대병원"
GROUP BY exercise
```

![image-20210711180623965](https://tva1.sinaimg.cn/mw1024/008i3skNgy1gsd4ebpau3j31p801c0tz.jpg)

6.754 초 소모됨

![image-20210711180703247](https://tva1.sinaimg.cn/large/008i3skNgy1gsd4f1a47rj30yw0megtr.jpg)

이제 위 쿼리를 최적화해보겠습니다.

- 각 테이블에 id 는 Primary Key 설정.

- hospital 테이블에 name을 `CREATE INDEX`
- member 테이블에 age을 `CREATE INDEX`

- programmer 테이블 memer_id `CREATE INDEX`

- covid 테이블에 hospital_id 를 `CREATE INDEX`
- 마지막에 불필요한 filesort 를 막기 위해서 `ORDER BY null` 추가 

![image-20210711181625681](https://tva1.sinaimg.cn/large/008i3skNgy1gsd4oucnx1j61e80muqd602.jpg)

![image-20210711181643360](https://tva1.sinaimg.cn/large/008i3skNgy1gsd4p2rt0bj31oq018q44.jpg)

소요시간이 0.057 sec 소모됨