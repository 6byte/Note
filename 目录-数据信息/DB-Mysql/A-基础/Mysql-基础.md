## Mysql-基础

#### 数据库操作

```
添加
create database name;

删除
drop database name;
mysqladmin -u -p drop name

选择数据库
use msyql;
```

#### 数据类型

```
数值类型
tinyint,samllint,int,bigint,double,decimal

日期
data,time,datatime,timestamp

字符串类型
char,varchar,tinyblog,tinytext,blog,longblog,mediumblog,text,mediumtext,longtext
```

#### 数据表操作

```
create table name(列名,数据类型);
drop table name;
```

#### 数据操作

添加数据

```
添加整列数据：insert into 表名 values()
	特点:	每列必须有值
	
添加指定列数据
	语法: insert into 表名 (列名1,列名2)values("值1","值2")
	
添加多个值
	语法: insert into 表名 
		(列1,列2)values("v1","v2")
		(列1,列2)values("v2","v3")
```

查询数据

```
select distinct 列名... from 表名 
	where 条件 group by 列名 
	having 条件 order by 列名 (ASC|DESC) limit n,m
```

删除数据

```
delete from name where 条件
```

修改数据

```
update from 表名 set 列名 = 值 where 条件
```

