## Mysql-高级查询

#### 查询-Case条件

```
适合查找类型较少的数据
```

格式

```mysql

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

范例

```MYSQL
SELECT
CASE
	WHEN
		s_sex = '女' THEN
			'2' 
			WHEN s_sex = '男' THEN
			'1' 
		END 
	FROM
	student
```

