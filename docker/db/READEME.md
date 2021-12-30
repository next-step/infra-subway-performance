+# 데이터 베이스 도커 설정
1. 마스터

```shell
docker run --name mysql-master -p 13306:3306 -v /db/master:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=masterpw -d mysql

$ docker exec -it mysql-master /bin/bash
$ mysql -u root -p
$ CREATE DATABASE subway;
$ CREATE USER 'replication_user'@'%' IDENTIFIED WITH mysql_native_password by 'replication_pw';  
$ GRANT REPLICATION SLAVE ON *.* TO 'replication_user'@'%'; 
$ SHOW MASTER STATUS\G  
 
```

2. 복제
```shell
docker run --name mysql-slave -p 13307:3306 -v /db/slave:/etc/mysql/conf.d --link mysql-master -e MYSQL_ROOT_PASSWORD=slavepw -d mysql

$ docker exec -it mysql-slave /bin/bash
$ mysql -u root -p
$ SET GLOBAL server_id = 2;
$ CHANGE MASTER TO MASTER_HOST='mysql-master', MASTER_PORT = 3306, MASTER_USER='replication_user', MASTER_PASSWORD='replication_pw', MASTER_LOG_FILE='{*}', MASTER_LOG_POS={*};
$ START SLAVE;  
$ SHOW SLAVE STATUS\G
```