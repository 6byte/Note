## Mysql-随记

#### 将sql文件添加到数据库

```mysql
load data local infile '本地绝对路径' into table '表格'
fields terminated by ',' lines terminated by '\n'

load data local infile:添加文件固定写法
fields terminated by ',':通过 ','分隔每个语句
lines terminated by '\n':通过 '\n'分隔每一行
```

#### 优化语句

```SQL
关闭唯一性校验:SET UNIQUE_CHECK = 0

order by null:不排序操作，提升效率

优化or:
	1.OR的两边都必须要用到索引
	2.不能使用复合索引
	3.没有索引应该考虑添加索引
	4.使用union代替or
```

#### 优化insert

```mysql
传统方案
insert into tableA values('1','NAME')；
insert into tableA values('2','NAMEA')；
优化方案:
insert into tableA values('1','NAME'),('2','NAMEA')
```

#### 优化排序

```mysql
MYSQL有两种排序方案:
Using filesort:
		1.性能:效率低
		2.算法:
			一次扫描算法：磁盘IO开销大
			两次扫描算法：性能较低
Using index:效率高


2.ORDER BY:
	1.多个字段排序要么都升序要么都降序
	2.排序的顺序需要和索引的顺序一致
	3.同样可以利用索引排序
```

##### 查询缓存

```mysql
#查询缓存是否开启
show VARIABLES like 'query_cache_type';
#查询缓存大小
show VARIABLES like 'query_cache_size';
#查询缓存空间
show status like 'Qcache%';
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

#### 锁

```MySQL
表锁，行锁，读锁(共享锁)，写锁（排他锁）
```

| 锁类型 |                        特点                        |
| :----: | :------------------------------------------------: |
| 表级锁 | 粒度大,开销小,加锁快,不会出现死锁,发生冲突概率最低 |
| 行级锁 |                   粒度小,开销小                    |
|        |                                                    |

