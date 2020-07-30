## Mysql-属性

#### OFFSET

句法： LIMIT X OFFSET Y。

① selete * from testtable limit 2,1;
② selete * from testtable limit 2 offset 1;

区别：
①从第三条开始查询
②从数据库中的第二条数据开始查询两条数据，即第二条和第三条。

注意：
1.数据库数据计算是从0开始的
2.offset X是跳过X个数据，limit Y是选取Y个数据
3.limit X,Y 中X表示跳过X个数据，读取Y个数据



#### 正则查询

```MYSQL
1.select * from tableA where id regexp '^A';
解释:查询所有以A开头的数据
2.select * from tableA where id regexp 'A$';
解释:查询所有以A结尾的数据
3.select * from tableA where id regexp '[ABC]';
解释:查询包含ABC中任何一个单词
```

