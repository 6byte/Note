## Mysql-关键字

#### 执行顺序

```MYSQL
select 	distinct name --第七步
	from tab_provider 	--第一步
	join tab_consumer --第三步
	on <condition>	  --第二步
	where <condition>	--第四步
	group by list_   --第五步
	having string  --第六步
	order by desc|asc   --第八步
	limit start pageSize --第九步
	
#先->后
FROM ON JOIN WHERE GROUP_BY HAVING SELECT_DINSTINCT ORDER_BY LIMIT
取表 取条件 分组 分条件 查询去重 排序 分页
```



#### OFFSET

```
句法： LIMIT M OFFSET N
解释:	 M决定起点
使用:	 selete * from testtable limit 2 offset 1;

```

#### 正则查询

```MYSQL
1.select * from tableA where id regexp '^A';
解释:查询所有以A开头的数据
2.select * from tableA where id regexp 'A$';
解释:查询所有以A结尾的数据
3.select * from tableA where id regexp '[ABC]';
解释:查询包含ABC中任何一个单词
```

#### 子查询

```
exists:
```

