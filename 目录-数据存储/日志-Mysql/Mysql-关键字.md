# Mysql-关键字

关键字大全	https://blog.csdn.net/weixin_43201975/article/details/88953903

## Mysql-表级关键字

### add

概览

```
添加字段,添加索引
```

详解

```MYSQL
#作用:增加表的字段
命令格式：
	alter table 表名 add 字段 类型 其他;
ex:
	alter table TbName add passtest int(4) default '0'
	
#添加索引
命令格式：
	alter table 表名 add index 索引名 (字段名1[，字段名2 …]);	
ex:
	alter table employee add index emp_name (name);

```

## 条件关键字

```MYSQL
#ALL:	从查询语句的返回结果中进行筛选，选择所有返回的结果

```

