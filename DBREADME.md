**department**

| Field           | Type    |
|:----------------|:--------|
| id              | CHAR    |
| department_name | VARCHAR |
| note            | VARCHAR |

![](dbimage/부서_레코드.png)

* 전체 레코드 수 : 9

**employee**

| Field      | Type    |
|:-----------|:--------|
| id         | INT     |
| birth      | DATE    |
| first_name | VARCHAR |
| last_name  | VARCHAR |
| sex        | ENUM    |
| join_date  | DATE    |

![](dbimage/직원_레코드.png)

* 전체 레코드 수 : 30만

**employee_department**

| Field         | Type |
|:--------------|:-----|
| employee_id   | INT  |
| department_id | CHAR |
| start_date    | DATE |
| end_date      | DATE |

![](dbimage/부서_직원_레코드.png)

* 전체 레코드 수 : 33만

**manager**

| Field         | Type |
|:--------------|:-----|
| employee_id   | INT  |
| department_id | CHAR |
| start_date    | DATE |
| end_date      | DATE |

![](dbimage/부서_관리자_레코드.png)

* 전체 레코드 수 : 33만

**position**

| Field         | Type    |
|:--------------|:--------|
| id            | INT     |
| position_name | VARCHAR |
| start_date    | DATE    |
| end_date      | DATE    |

![](dbimage/직책_레코드.png)

* 전체 레코드 수 : 44만

**record**

| Field         | Type      |
|:--------------|:----------|
| id            | INT       |
| employee_id   | INT       |
| time          | TIMESTAMP |
| record_symbol | CHAR      |
| door          | CHAR      |
| region        | CHAR      |

![](dbimage/출입_레코드.png)

* 전체 레코드 수 : 66만

**salary**

| Field         | Type |
|:--------------|:-----|
| id            | INT  |
| annual_income | INT  |
| start_date    | DATE |
| end_date      | DATE |
| used          | CHAR |

![](dbimage/연봉_레코드.png)

* 전체 레코드 수 : 284만


