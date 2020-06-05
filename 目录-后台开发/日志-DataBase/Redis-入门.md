## Redis

### String-操作

增

```
set		set key value				
setex   setex key senconds value	添加key并设置时间
setnx   setnx key value				设置值如果没有被设置
```

删

```
del			del key			删除键
flushdb				 	   	清除当前数据库所有数据
flushall					清除所有数据
```

查

```
get		get key		查看key
type	type key	查看key的类型
keys 	key * 		查看所有key
exsits  exsits key	判断key是否存在
getrange key 0 2:获取key中0到2之间的字符
```

改

```
getset 		getset key				更新键值
append 		append key value		向key追加字符串value
move		move key 1				将key移动到1号数据库
incr 		incr key				数据自增1
decr		decr key				数据减1
incrby		incrby key 10			设置步长为10
```

其他命令

```
时间
ttl key:查看剩余时间
expire key 5s:为key设置过期时间5秒
```

### List操作

```
lpush	lpush key value		左添加
rpush	rpush key value		右添加
lpop	lpop  key value		左删除
rpush	rpush key value		右删除

LREM 	LREM KEY  数量 值		移除n个key
LLEN	获取list长度

```

