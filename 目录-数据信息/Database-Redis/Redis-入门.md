## Redis

#### 概念

```
五种基本数据类型
三种特殊数据类型
作用:数据库，缓存，消息中间件，分布式锁，哨兵，事务，驱动事件，主从复制，索引半径查询
```

#### 通用命令

|      命令       |     解释     |     举例      |
| :-------------: | :----------: | :-----------: |
| expire key time | 设置过期时间 | expire key 10 |
|     ttl key     | 查看过期时间 |    ttl key    |
|       del       |    删除键    |    del key    |
|      type       |  查看键类型  |   type key    |
|     exists      | 查看键值存在 |  exists key   |

##### String

概览

```
操作:键值自增，批量增删，设置过期时间
业务:网页访问量，分布式锁，缓存
特点:
```



|          命令           |       解释       |        举例        |
| :---------------------: | :--------------: | :----------------: |
|        incr key         |      自增1       |      incr key      |
|        decr key         |      自减1       |      decr key      |
|     incrby key step     |       步减       |   incrby key 10    |
| setex key seconds value | 设置过期时间的键 | setex key 10 value |
|     setnx key value     | 仅添加不存在的值 |       setnx        |
|       mset key..        |    设置多个值    |     mset key..     |
|                         |                  |                    |

##### Redis-List

概览

```
作用:模拟栈队列
特点:先进后出
```



|         命令          |          解释           |        举例         |
| :-------------------: | :---------------------: | :-----------------: |
| lpush/rpush key value |         添加值          | lpush/rpush k value |
| lrange key start end  |        查看范围         |    lrange k 0 2     |
|  lpop/rpop key value  |         移除值          |     lpop/rpop k     |
|   lindex key index    |      获取某一个值       |     lindex k 0      |
|       llen key        |        获取长度         |       llen k        |
|   lrem key n value    | 移除key中的n个value的值 |     lrem k 1 v      |
|                       |                         |                     |

##### Redis-Set

```
作用:生成随机值,相互关注
特点:唯一
```

|     命令     |     解释     |    举例    |
| :----------: | :----------: | :--------: |
|   spop key   |  随机删除值  |   spop k   |
| smembers key | 查看所有元素 | smembers k |
|   sadd key   |   添加元素   |   sadd k   |

##### Redis-Hash

```
作用
```

|         命令         |    解释    |    举例    |
| :------------------: | :--------: | :--------: |
| hset key field value |   存储值   | hset k f v |
|       hget key       |            |            |
|     hgetall key      | 获取所有值 | hgetall k  |

