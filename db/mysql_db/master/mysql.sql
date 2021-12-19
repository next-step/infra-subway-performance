use exam
CREATE USER 'repl'@'%' IDENTIFIED WITH 'mysql_native_password' BY '1234';
GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
GRANT INSERT,SELECT,UPDATE,DELETE ON `exam`.* TO `repl`@`%`;

FLUSH privileges;
