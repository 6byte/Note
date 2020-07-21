### Mysql-存储过程

概览

```
--特点
1.是一组SQL语句的集合，可以编程

--区别
存储函数:有返回值的过程
存储过程:没有返回值的函数
```

#### 过程-使用

概览

```
总共分三步:创建，调用，删除
```



**语法**

```mysql
--#--创建
create procedure 存储过程名字(存储过程参数)
begin
	--SQL 语句
end;

--#--调用
call 存储过程名字(存储过程参数)

--#--删除
drop procedure 存储过程名
```

**示例**

```MYSQL
--#--创建

#除了 begin 中语句，其他都不用加分号
#将 ; 改成 @，避免回车结束输入
delimiter @ 
create pro()
begin
select 'Hello';
end@

--#--调用
call pro()
```

**查看状态**

```mysql
#查看某个数据库中，所有存储过程的信息
select 名字 from mysql.proc where db = "数据库名"
select * from mysql.proc where db = "iota"\G

#查看存储过程的状态信息
show procedure status;

#查询某个存储过程的定义
show create procedure 表名.存储过程名 \G
```

#### 过程-语法

###### **语法**-变量

```MYSQL
--#--	变量声明
DECLARE 变量名.. 类型 [默认值]

--#--	变量赋值,两种方式
set 变量名 = 10;
select count(0) into 变量名 from A ;
#将A表中的记录数作为值赋给变量

```

###### 语法-条件

**IF**

```mysql
--#--	if的基本使用
if 条件 
	then 要执行的SQL语句;
	[elseif 条件 then 要执行的SQL语句;] #可以省略
	[elseif 条件 then 要执行的SQL语句;]
end if;
```

**CASE**

```mysql
方式一：
case 
	when 条件 
	then SQL 语句
	when 条件 and 条件
	then SQL 语句
	else SQL 语句
end
```

###### 语法-循环

**WHILE**

```mysql
# 满足条件继续循环
while 条件 
	SQL语句;
end while
```

**REPEAT**

```mysql
repeat
 SQL语句
 until 条件  #不能有分号
end repeat
```

**LOOP**

```mysql
#通常配合其他语句结束循环
```



###### 语法-传参

概览

```mysql
create procedure 存储过程名字([in|out|inout] 参数名 参数类型，..)
in：输入参数，默认
out:返回值
inout:既可以作为输入参数也可以作为返回值
```

**输入参数**

```MYSQL
#定义
create procedure name(in flag int);
#调用
call name(100);
```

**输出参数**

```MYSQL 
#定义，定义flag作为输出参数
CREATE PROCEDURE pro( out flag int)
BEGIN
#只要给flag赋值，他是自动返回的
	set flag = 100;
END

#接收,用@flag参数接收到上面的返回值，并输出
call pro(@flag);
select @flag;
 -- @:是会话变量，全局可用,关闭数据库后释放
 -- @@:是系统变量
```

