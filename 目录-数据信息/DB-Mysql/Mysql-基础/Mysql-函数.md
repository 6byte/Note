### MYSQL-函数

<https://www.jb51.net/article/157703.htm>

#### 概览

- 共九个

- 数学函数,聚合函数,字符串函数,日期和时间函数,加密函数,控制流函数,格式化函数,类型转化函数,系统信息函数

##### 聚合函数

- AVG(col)`	返回指定列的平均值
- `MIN(col)`         返回指定列的最小值
- `MAX(col)`         返回指定列的最大值
- `SUM(col)`         返回指定列的所有值之和
- `GROUP_CONCAT(col) `返回由属于一组的列值连接组合而成的结果
- `COUNT(col)`         返回指定列中非NULL值的个数





#### 日期函数

DAYOFWEEK

- 作用:根据日期返回星期的索引,1:星期天，2:星期一 …7:星期六

  - ```MYSQL
    select DAYOFWEEK(日期); 
    select DAYOFWEEK('2020-06-17'); --4
    ```

- `CURDATE()` 返回当前的日期
- `CURTIME()` 返回当前的时间
- HOUR(time)返回time的小时值(0~23)

#### 控制流程函数

概览:共四个

- IF(expr1,expr2,expr3)   相当于三目运算符

- `IFNULL(参数1,参数2) `     arg1是空，返回参数2，否则返回参数1

- `NULLIF(参数1,参数2) `如果参数1=参数2返回NULL；否则返回参数1

- CASE-WHEN-THEN

  - ```MYSQL
    --适合查找类型较少的数据
    SELECT
    CASE				  -----开始	
      WHEN				  -----当
    	列名 = '值' 	    -----条件
    	THEN			  -----返回
    		'返回值' 	    -----返回值
       WHEN 			  -----可以多级循环
       	 '列名' = '值' 
       	 THEN
    		'1' 		
    	END 				-----结束标志
    FROM 表名					---查询表
    ```

    

#### 数学函数

- RAND():返回０到１内的随机值,可以通过提供一个参数(种子)使RAND()随机数生成器生成一个指定的值。

#### 类型转化函数



#### 系统信息函数

- `DATABASE()`   返回当前数据库名
- `BENCHMARK(count,expr)`  将表达式expr重复运行count次
- `CONNECTION_ID() `  返回当前客户的连接ID
- `FOUND_ROWS()`   返回最后一个SELECT查询进行检索的总行数
- `USER()或SYSTEM_USER() ` 返回当前登陆用户名
- `VERSION()  ` 返回MySQL服务器的版本