### Mysql-视图

##### 概叙

```
概览
    1.是一种虚拟存在的表
    2.不在数据库中实际存在
    3.实质是封装了一个select表
作用
	1.限定开发者看到的数据
```

##### 视图-操作

基本语法

```mysql
创建
create view 视图名 as 查询语句		--创建
create view view_name as select name from table_name;

更新(不建议更新)
update 视图名 set 列 = 值 where 列 = 值

drop view [if exists] 视图名
```



