## Mysql-技巧

#### SQL执行顺序

```mysql
select 	distinct name --第七步
	from tab_provider 	--第一步
	join tab_consumer --第三步
	on <condition>	  --第二步
	where <condition>	--第四步
	group by list_   --第五步
	having string  --第六步
	order by desc|asc   --第八步
	limit start pageSize --第九步
```

简洁版

```MYSQL
#先->后
FROM ON JOIN WHERE GROUP_BY HAVING SELECT_DINSTINCT ORDER_BY LIMIT
```

#### 正则表达式

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

#### 优化集

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

##### INSERT

- ```MYSQL
  传统方案
  insert into tableA values('1','NAME')；
  insert into tableA values('2','NAMEA')；
  优化方案:
  insert into tableA values('1','NAME'),('2','NAMEA')
  ```

##### 排序

- 两种排序方案:

  ```
  Using filesort:
  1.性能:效率低
  2.算法:
      一次扫描算法：磁盘IO开销大
      两次扫描算法：性能较低
  Using index:效率高
  ```

- ORDER BY，多个字段情形

  ```
  1.排序要么都升序要么都降序
  
  2.排序的顺序需要和索引的顺序一致
  
  3.同样可以利用索引排序
  ```

- order by null:不排序操作，提升效率