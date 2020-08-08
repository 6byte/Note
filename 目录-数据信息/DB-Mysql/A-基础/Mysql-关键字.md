## Mysql-关键字

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

