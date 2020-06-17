##### Mysql-触发器

概念

```
1.与表有关的数据库对象
2.在insert,update,delete之前或之后执行自定义SQL语句
作用：日志记录，数据校验
```

类型

```
INSERT:NEW表示将要或者已经新增的数据
UPDATE:OLD表示修改之前的数据，NEW表示将要或已经修改后的数据
DELETE:OLD表示将要或已经删除的数据
```

创建触发器

```mysql
create triggle 触发器名字;
before|after insert|update|delete;--选择操作
on 表名;
for each row  --行级触发器
begin
	SQL语句;
end;
```

