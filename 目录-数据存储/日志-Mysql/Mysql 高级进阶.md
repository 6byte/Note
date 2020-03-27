# Mysql-高级进阶

### Mysql-索引



| 索引类型 |            作用            |
| :------: | :------------------------: |
| 普通索引 |          加速查找          |
| 主键索引 |   加速查找+唯一+不能为空   |
| 唯一索引 |       加速查找+唯一        |
| 联合索引 | 联合主键+联合唯一+联合普通 |

概览

```mysql
说明:直接创佳索引会创建额外的临时文件，以某种文件存储，每次查询的时候都会先查这个表

索引种类:Hash索引，btree索引，覆盖索引
hash索引：单值快，范围查找慢
btree索引

创建和删除索引

覆盖索引
	
索引合并:<and查询>
	1.给表中的id 和 email 创建一个索引
		create index on table1(id);
		create index on table1(email);
	2.在索引文件中查询
		select * from table1 where id=1 and email = "12@12"
	这个过程叫做索引合并，非真实索引
	
效率:		组合索引>索引合并

```

#### 创建索引方式

```MYSQL
#普通索引:
	CREATE INDEX index_name ON table(column(length))
	drop  index 索引名称 on table
#唯一索引:
	create uniqu index 索引名称 on 表名(列名)
	drop unique index 索引名称 on 表名
#组合索引
	create unique index 索引名称 on table 表名(列名1,列名2)
	drop unique index 索引名称 on 表名
	！！！注意：有最左前缀匹配,列名1和列名2的查询不能颠倒顺序
	
	
	create unique index 索引名称 on table 表名(name,age)
	#下面查询索引会失效
	select * from table1 where age=12 and name="jhon"
	
```

#### 索引失效

```

1.避免使用like,or,!= , 大于号:> ,order by
2.避免使用函数，如反转函数
3.必须数据类型一致
4.组合索引不按前缀规则查询
5.用count(列)代替count(*)
6.使用join 代替 (Sub-Queries)
7.重复值较少，不适合做索引，如性别
8.尽量用char代替varchar

char 和varchar的区别
	1.char的长度不可变，varchar的长度可变
	2.定义char[10]，varchar[10],存进'abcd',char的长度为abcd+6个空格；varchar长度为4
	3.char的存取速度比varchar要快，varchar比char省空间
	4.(1)char对ASCII占用1个字节，汉字占用两个字节；
	  (2)varchar对ASCII和汉字占用2个字节，两者的存储数据都非unicode的字符数据
```

#### Mysql-事务



#### Mysql-原理

网页https://www.jianshu.com/p/57ecc074af32