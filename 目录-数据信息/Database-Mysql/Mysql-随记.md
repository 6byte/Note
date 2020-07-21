## Mysql-随记

#### 优化-其他

```MYSQL
关闭唯一性校验:SET UNIQUE_CHECK = 0
SHOW STATUS like '%innodb_rows_%'	--分析当前表增删查改情况
show processlist:查看实时SQL执行情况
explain 结合上面的 show processlist分析语句
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



应用

```
set autocommit = 0 :关闭事务自动提交
show status like "innodb_row_lock%";查看锁的状态
	Innodb_row_lock_current_waits：正在等待行锁的数量
	Innodb_row_lock_time:锁定的时长
	Innodb_row_lock_time_avg：锁定的平均时长
	Innodb_row_lock_waits：一共等待多少次数
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

