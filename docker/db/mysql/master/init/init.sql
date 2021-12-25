CREATE USER 'replication_user'@'%' IDENTIFIED WITH mysql_native_password by 'replication_pw';
GRANT REPLICATION SLAVE ON *.* TO 'replication_user'@'%';

SHOW MASTER STATUS\G
