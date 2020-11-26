## Jfinal-数据库操作

##### Db+Record

```
使用通用模型，不需要Bean
```



```java
查询单个
Record res = Db.findById("user", 1);
//此处返回的是一个通用model对象，可直接通过res.get()获取值
查询所有
List<Record> findAll = Db.findAll("user");

增加
Record user = new Record().set(数据库字段, 值)；
Boolean isSave = Db.save("数据库表名",user);

删除
Boolean isDelete = Db.deleteById("数据库表名",1);

更改

```

##### 分页

```SQL
dao.paginate(起始页, 每页数量, sql语句, 参数, 参数);
//使用?占位
```

