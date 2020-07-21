## Mybatis-高级查询



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

## Mybatis-查询

#### 模糊查询

```MYSQL
@Select("  select * from message where name like '%${name} %'  ")
@Select("  select * from message where name like concat('%', #{name}, '%')  ")
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

### Mybatis-拦截器

#### 概览

```
1.只能拦截Executor、ParameterHandler、StatementHandler、ResultSetHandler四个接口
	--Executor
        Executor是Mybatis的核心接口。
        update()执行增删改，query(执行查询)
        
    --ParameterHandler
        用来设置参数
        
    --StatementHandler
        负责处理Mybatis与JDBC之间Statement的交互
        
    --ResultSetHandler
        处理结果
2.Mybatis拦截器的使用，分两步：
   -- 自定义拦截器类	
   -- 注册拦截器类
```







