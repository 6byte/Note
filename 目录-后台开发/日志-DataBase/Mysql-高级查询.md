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

#### 权限查询

```MYSQL
SELECT
	u.*,
	r.*,
	p.* 
FROM
	USER AS u
	LEFT JOIN user_role AS ur ON u.userId = ur.userId
	LEFT JOIN role AS r ON r.role_id = ur.roleId
	LEFT JOIN role_perm AS rp ON rp.role_id = r.role_id
	LEFT JOIN permission AS p ON p.permissionId = rp.permission_id 
WHERE
	u.mainId = 1
	
--一旦用了别名，就要一用到底，不能停
```

