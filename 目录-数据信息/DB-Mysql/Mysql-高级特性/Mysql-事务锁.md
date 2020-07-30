### Mysql-事务锁

#### Mysql-事务

```
ACID:原子性，隔离性，一致性，持久性
开启事务:
	begin;
	start transaction;
结束事务:
	rollback;
	commit;
	客户端断开连接，结束事务并自动回滚
事务并发带来的问题
	脏读:读到别的事务没有提交的数据
	不可重复读:读到别的事务提交的数据
	幻读:
隔离级别
	Read Uncommitted (未提交读)
	Read Committed (已提交读) 解决脏读
	Repeatable Read (可重复读) 解决脏读、不可重复读
	Serializable (串行化)：解决所有问题
解决方案
	1.加锁
	2.快照,MVCC
```

#### Mysql-锁

```
一、锁类型
    表级锁（锁定整个表）
    页级锁（锁定一页）
    行级锁（锁定一行）
    共享锁（S锁，MyISAM 叫做读锁）
    排他锁（X锁，MyISAM 叫做写锁）
    抽象锁 (悲观锁,乐观锁,不真实存在)
二、
```

##### 排他锁

```mysql
定义:排他锁又名写锁,不能与其他锁并存，仅有获取到该锁的事务才能对数据进行读取修改
使用:
	自动：delete / update /insert 默认加上X锁
	#for update加排他锁
	手动: select * from student where id = 1 for update;
```

##### 共享锁

```

```

##### 意向锁

```

```

##### 排他锁

```

```

