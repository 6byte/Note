### Mysql-日志

概览

```MYSQL
--日志类型:
	错误日志，二进制日志，查询日志，慢查询日志
--常用命令:
	#查看日志状态
	1.show master status;
	#查看日志条数
	2.show master logs;
	# 是否启用binlog日志
	3.show variables like 'log_bin';
```

##### 二进制日志

```
作用:
	1.记录所有增删改语句
	2.记录数据结构
	3.用于数据恢复
	4.MSYQL主从复制
默认:不开启，需要到配置文件中开启，并配置格式
```

##### 错误日志

```MYSQL
作用:记录MYSQL所有报错信息
默认:错误日志默认开启
#MYSQL错误日志的位置
show variables like 'log_error%';
```

##### 查询日志

```
1.默认不开启
2.记录所有SQL语句
```

##### 慢查询日志

```
1.开启
slow_query_log = 1
slow_query_log_file = low_query.log
low_query_time = 3
```

##### 日志格式

```
STATEMENT 
	作用：所有记录都以SQL语句形式记录，通过mysqlbinlog查看
	优点：减少了binlog日志量，节约IO，提高性能。
	缺点：主从复制时,会将日志解析成原文本,并在从库(slave)中重新执行一次

ROW
	作用：记录每条语句的数据变更
MIXED
	作用:以上两种日志的混合使用，
```

### Mysql-缓存

##### 查询缓存

```MYSQL
#查看是否支持缓存
show VARIABLES LIKE 'hava_query_cache';
#查询缓存是否开启
show VARIABLES like 'query_cache_type';
#查询缓存大小
show VARIABLES like 'query_cache_size';
#查询缓存空间
show status like 'Qcache%';
```
