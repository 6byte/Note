## Mysql-技巧

#### Mysql-查询

查询

```MYSQL
SELECT 字段名 FROM 表名 WHERE 列名 REGEXP '正则表达式'
SELECT * FROM `student` where name regexp '^陈';
--查询student表中所有姓陈的学生
```

使用

```
SELECT * FROM `student` where name regexp '^陈';
```

#### Mysql-优化

```mysql
- 字符串类型必须用双引号
正:select * from tableA where name = "1000"
误:select * from tableA where name = 1000
- OR
  - 1.OR的两边都得用索引列
  	select * from table_name where id = 1 or name ="name"
  - 2.不能使用复合索引
  - 3.使用union代替or
```

##### 优化-INSERT

```MYSQL
传统方案
insert into tableA values('1','NAME')；
insert into tableA values('2','NAMEA')；
优化方案:
insert into tableA values('1','NAME'),('2','NAMEA')
```

##### 优化-排序

排序方案:

```
---Using filesort:
1.性能:效率低
2.算法:
    一次扫描算法：磁盘IO开销大
    两次扫描算法：性能较低
    
---Using index:效率高
```

ORDER BY

```
在多个字段
1.排序要么都升序要么都降序

2.排序的顺序需要和索引的顺序一致

3.同样可以利用索引排序
```

order by null:不排序操作，提升效率

##### 优化-OR

```
优化or:
	1.OR的两边都必须要用到索引
	2.不能使用复合索引
	3.使用union代替or
```

##### 优化-缓存

```MYSQL
1.命中缓存，SQL必须一致，如大小写不一致，不走缓存
select * from tableA
Select * from tableA
2.查询语句中结果出现随机值
select now()
3.不使用任何表查询
select "hello"
4.查询系统数据库不走缓存
select * from mysql
5.当表发生更改时，缓存被清空
```





