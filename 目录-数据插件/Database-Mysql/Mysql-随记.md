## Mysql-随记

### MYSQL-命令

杂记

```MYSQL
# 是否启用binlog日志
show variables like 'log_bin';
```

统计

```
mysqlshow 
```



MYSQL-随记

```
执行顺序
FROM,ON,WHERE,GROUP BY, ORDER BY,LIMIT,行
模糊匹配正则表达式

1.select * from tableA where id regexp '^A';
解释:查询所有以A开头的数据
2.select * from tableA where id regexp 'A$';
解释:查询所有以A结尾的数据
3.select * from tableA where id regexp '[ABC]';
解释:查询包含ABC中任何一个单词
```

### MYSQL-优化

#### 优化-索引失效

```sql
1.varchar:值没有使用双引号
select * from tableA where name = 1000
```



#### 优化-OR

```SQL
优化or:
	1.OR的两边都必须要用到索引
	2.不能使用复合索引
	3.使用union代替or
```

#### 优化-INSERT

```mysql
传统方案
insert into tableA values('1','NAME')；
insert into tableA values('2','NAMEA')；
优化方案:
insert into tableA values('1','NAME'),('2','NAMEA')
```

#### 优化-排序

```mysql
1.MYSQL有两种排序方案:
Using filesort:
		1.性能:效率低
		2.算法:
			一次扫描算法：磁盘IO开销大
			两次扫描算法：性能较低
Using index:效率高


2.ORDER BY，多个字段情形:
	1.排序要么都升序要么都降序
	2.排序的顺序需要和索引的顺序一致
	3.同样可以利用索引排序
	
3.order by null:不排序操作，提升效率
```

#### 优化-其他

```MYSQL
关闭唯一性校验:SET UNIQUE_CHECK = 0
SHOW STATUS like '%innodb_rows_%'	--分析当前表增删查改情况
show processlist:查看实时SQL执行情况
explain 结合上面的 show processlist分析语句
```



缓存失效

```SQL
1.命中缓存，SQL必须一致，如大小写不一致，不走缓存
select * from tableA
Select * from tableA

2.查询语句中结果出现随机值
select now()

3.不使用任何表查询
select "hello"

4.查询系统数据库不走缓存
select * from mysql

5.当表的发生更改时，缓存被清空
```

### MYSQL-锁

隔离级别

```
四种隔离级别:读未提交，读提交，可重复读，可序化

读未提交:A事务写数据，别的事务允许读，不允许写。B事务可能读到A事务为提交的数据。

读提交:A事务读数据，则允许其他事务读写，A事务写数据，其他事务禁止操作。

```

概览

```MySQL
分类
表锁，行锁，读锁(共享锁)，写锁（排他锁）,间隙锁
行锁:锁字段
表锁:锁所有字段

特点
表锁:开销小，粒度大，速度快
行锁:开销大，粒度小
```



```

```

应用

```
set autocommit = 0 :关闭事务自动提交
show status like "innodb_row_lock%";查看锁的状态
	Innodb_row_lock_current_waits：正在等待行锁的数量
	Innodb_row_lock_time:锁定的时长
	Innodb_row_lock_time_avg：锁定的平均时长
	Innodb_row_lock_waits：一共等待多少次数
```

笔记

```

```



### MYSQL-备份

#### 备份SQL

```MYSQL
load data local infile '本地绝对路径' into table '表格'
fields terminated by ',' lines terminated by '\n'
解释语句
load data local infile:添加文件固定写法
fields terminated by ',':通过 ','分隔每个语句
lines terminated by '\n':通过 '\n'分隔每一行
SQL语句中有大量分号，所以需要上面分隔符处理
```

命令

```
#mysqldump:备份数据，不同数据库之间迁移，备份内容:创建表，插入表的SQL语句
#mysqlimport/source:导入mysqldump导出的文件
```



### MYSQL-索引

概念

```
1.本质是一张（二叉树）表
2.会占用磁盘空间
3.加快查询。但每次添加数据都会更新表,影响增删改
4.查询频率高,数据量比较大才建索引
5.在where之后的字段建立索引
```

注意

```
行锁升级为表锁
1.索引失效，InnoDB对表中的所有记录加锁，将形成表锁
```

操作

```
show index from tableA:查看索引	
```

### MYSQL-日志

```
日志类型:错误日志，二进制日志，查询日志，慢查询日志
常用命令:
	#查看日志状态
	1.show master status;
	#查看日志条数
	2.show master logs;
```

#### 错误日志

```mysql
作用:记录MYSQL所有报错信息
默认:错误日志默认开启
#MYSQL错误日志的位置
show variables like 'log_error%';
```

#### 二进制日志

```
作用:
	1.记录所有增删改语句
	2.记录数据结构
	3.用于数据恢复
	4.MSYQL主从复制
默认:不开启，需要到配置文件中开启，并配置格式
```

日志格式

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

#### 查询日志

```
1.默认不开启
2.记录所有SQL语句
```

#### 慢查询日志

```
1.默认关闭
```

删除日志

```
第一种方式:reset master
```

### MYSQL-缓存

#### 查询缓存

```MYSQL
#查询缓存是否开启
show VARIABLES like 'query_cache_type';
#查询缓存大小
show VARIABLES like 'query_cache_size';
#查询缓存空间
show status like 'Qcache%';
```

### MYSQL-集群

#### 主从复制

```
概念：
	通过二进制文件作为载体，将主数据库和从数据库的内容保持一致
	基于二进制文件完成的
步骤
	1.Master主库提交事务时，会把变更记录在二进制文件A.LOG中
	2.主库推送A.LOG二进制文件到从库的中继日志
	3.SLAVE重做中继日志的事件
优势
	1.主库出现问题，可以快速切换到从库提供服务
	2.可以完成读写分离操作
	3.分离备份
```

