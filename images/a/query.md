##### Q1. 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
SELECT A.사원번호, A.이름, A.연봉, 직급.직급명, 사원출입기록.지역, 사원출입기록.입출입구분, 사원출입기록.입출입시간
FROM (SELECT 급여.연봉, 부서관리자.사원번호, 사원.이름
      FROM 부서
               INNER JOIN 부서관리자 ON 부서.부서번호 = 부서관리자.부서번호
               INNER JOIN 사원 ON 사원.사원번호 = 부서관리자.사원번호
               INNER JOIN 급여 ON 부서관리자.사원번호 = 급여.사원번호
      WHERE 부서.비고 = 'active'
        AND 부서관리자.종료일자 = '9999-01-01'
        AND 급여.종료일자 = '9999-01-01'
      GROUP BY 부서관리자.사원번호, 급여.연봉
      ORDER BY SUM(급여.연봉) DESC
          LIMIT 5) AS A
         INNER JOIN 직급 ON A.사원번호 = 직급.사원번호
         INNER JOIN 사원출입기록 ON A.사원번호 = 사원출입기록.사원번호
WHERE 직급.종료일자 = '9999-01-01'
  AND 사원출입기록.입출입구분 = 'O';
```

##### BEFORE (약 506ms)
| id | select\_type | table | partitions | type | possible\_keys | key | key\_len | ref | rows | filtered | Extra |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | SIMPLE | 부서 | NULL | ALL | PRIMARY | NULL | NULL | NULL | 9 | 11.11 | Using where |
| 1 | SIMPLE | 부서관리자 | NULL | ref | PRIMARY,I\_부서번호 | I\_부서번호 | 12 | tuning.부서.부서번호 | 2 | 10 | Using where |
| 1 | SIMPLE | 직급 | NULL | ref | PRIMARY | PRIMARY | 4 | tuning.부서관리자.사원번호 | 1 | 10 | Using where |
| 1 | SIMPLE | 급여 | NULL | ref | PRIMARY | PRIMARY | 4 | tuning.부서관리자.사원번호 | 9 | 10 | Using where |
| 1 | SIMPLE | 사원 | NULL | eq\_ref | PRIMARY | PRIMARY | 4 | tuning.부서관리자.사원번호 | 1 | 100 | NULL |
| 1 | SIMPLE | 사원출입기록 | NULL | ALL | NULL | NULL | NULL | NULL | 658935 | 1 | Using where; Using join buffer \(Block Nested Loop\) |

##### AFTER 
| id | select\_type | table | partitions | type | possible\_keys | key | key\_len | ref | rows | filtered | Extra |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | PRIMARY | &lt;derived2&gt; | NULL | ALL | NULL | NULL | NULL | NULL | 2 | 100 | NULL |
| 1 | PRIMARY | 직급 | NULL | ref | PRIMARY,직급\_\_index001 | 직급\_\_index001 | 8 | A.사원번호,const | 1 | 100 | Using index |
| 1 | PRIMARY | 사원출입기록 | NULL | ref | 사원출입기록\_\_index001 | 사원출입기록\_\_index001 | 7 | A.사원번호,const | 2 | 100 | NULL |
| 2 | DERIVED | 부서 | NULL | index | PRIMARY,부서\_부서번호\_uindex,부서\_부서번호\_비고\_index | 부서\_부서번호\_비고\_index | 134 | NULL | 9 | 11.11 | Using where; Using index; Using temporary; Using filesort |
| 2 | DERIVED | 부서관리자 | NULL | ref | PRIMARY,I\_부서번호 | I\_부서번호 | 12 | tuning.부서.부서번호 | 2 | 10 | Using where |
| 2 | DERIVED | 사원 | NULL | eq\_ref | PRIMARY | PRIMARY | 4 | tuning.부서관리자.사원번호 | 1 | 100 | NULL |
| 2 | DERIVED | 급여 | NULL | ref | PRIMARY,급여\_\_index001 | 급여\_\_index001 | 7 | tuning.부서관리자.사원번호,const | 1 | 100 | NULL |

#### 변경 내용 
 ```sql
    alter table 부서 modify 비고 varchar(40) not null;
    create index 부서__index001 on 부서 (부서번호, 비고);
    create index 사원출입기록__index001 on 사원출입기록 (사원번호, 입출입구분);
    create index 직급__index001 on 직급 (사원번호, 종료일자);
    create index 급여__index001 on 급여 (사원번호, 종료일자);
 ```

#### 결과 
 - 14 rows retrieved starting from 1 in 33 ms (`execution: 6 ms`, fetching: 27 ms)