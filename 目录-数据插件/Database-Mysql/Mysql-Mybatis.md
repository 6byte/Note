## Mysql-高级查询



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

## Mysql-Mybatis

#### 模糊查询

```MYSQL
select *from student where name like "%"#{name}"%"
一定要用双引号
```

#### 多表查询

```MYSQL
@Select(value = "select 字段 from 表1,表2,表.. ")
@Results({
    //从表1查询数据
    //property的属性可以自定义他对应集合Map的名字{'atrribute':{....}}
@Result(property = "Atrribute",column = "id",many = @Many(select = "..dao.包全名.方法名",fetchType = FetchType.EAGER)),
    //从表2查询数据
@Result(property = "zheshimiddle",column = "id",many = @Many(select = "shiro.demo.dao.user_dao.table2",fetchType = FetchType.EAGER))
})
```

#### 一对多查询

```MYSQL
@Select(value = "select *from person where name =#{name}")
@Results({
             //column = "keyid"		->>对应外键
@Result(property = "子表Bean",column = "keyid",
        many = @Many(select = "包全名.方法名",fetchType= FetchType.EAGER))})

     public UserBean selectUser(String name);

```

