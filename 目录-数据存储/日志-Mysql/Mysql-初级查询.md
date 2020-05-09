## Mysql-查询

### Mysql-简单查询

### Mysql-Join

#### 概览

```
1.内连接2.左连接，3.右连接4.完全外连接
```

#### Mysql-内连接

```mysql
#Inner join
作用  	产生的结果是A和B的交集（相同列里面的相同值）
语句		select * from 表1 INNER JOIN 表2 on 条件;
示例：
		查询a表和b表合集:
			select * from a INNER JOIN b;
		查询a表和b表共值
			select * 
			from a INNER JOIN b 
			on a.name = b.name;
注意:on和where的区别

1、 on
	只用于生成的临时表，无论条件是否为真，都返回左表的记录。
2、where
	在临时表生成后，对临时表进行过滤，条件不为真的就全部过滤掉。
```

#### Mysql-左外连接

```mysql
#Left outer join
作用	  1.返回A表的完全集，和B表符合on条件的值，空值填NULL
语句	   SELECT * FROM 表A Left outer join 表B 条件
示例		
		SELECT * FROM a Left outer join b on b.bname=A.aname
     #返回左边表中所有值，b表符合条件的值，不符合填充null
结果
aid<左>aname 	bid	    bname<右表>
1		赵八		null	null
2		张三		1		张三
3		冯七		5		冯七
4		田九		null	null
5		凌十		null	null
```

#### Mysql-右外连接

```MYSQL
#Right Outer Join
作用		1.返回右边表的完全集，和左表符合on条件的值，空值填NULL
语句		select *from 左表 RIGHT JOIN 左表 on 条件
示例		select *from a RIGHT JOIN b on a.aname =b.bname
结果
aid<左>aname 	bid	    bname<右表>
2		张三	   1	   张三
null	null	2		李四
null	null	3		王五
null	null	4		马六
3		冯七	   5	   冯七
```

#### Mysql-连接应用

```
1.查询权限
select  表1  inner join 表2 on   查询条件
```



#### Mysql-基础分页

```
SELECT * FROM 表名 limit 起始N,M条数据;
SELECT * FROM 表名 limit 6,5;
：从第7条记录行开始算，取出5条数据
```

