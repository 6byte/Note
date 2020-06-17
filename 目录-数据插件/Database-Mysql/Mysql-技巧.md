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

