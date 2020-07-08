### Redis-管理

#### 过期管理

|      策略       |                           说明                            |
| :-------------: | :-------------------------------------------------------: |
|  volatile-lru   |             为已添加超时的数据，用LRU算法删除             |
|   allkeys-lru   |                   用LRU算法删除所有key                    |
| volatile-random |                 随机删除设定了超时的数据                  |
| allkeys-random  |                   用LRU算法随机删除key                    |
|  volatile-ttl   | 对设定了超时的数据中，根据TTL机制排序，删除将要过期的数据 |
|   noeviction    |               (默认)内存数据不会删除，报错                |

#### 快照管理

RDB

#### 配置管理

##### 日志配置

```MYSQL
#日志配置
    #级别:debug,verbose,notice,warning 默认为notice
    loglevel notice
    #指定redis日志目录,默认为stdout
    #logfile ~/redislog.log
	logfile stdout
```

数据库配置

```MYSQL
#数据库配置
    #设定redis所允许的最大数据库的个数,默认为16个.
    databases 16

```

##### 备份配置

```MYSQL
#备份配置
	#save 时间(s) 操作数(n)：在s秒中发生至少n个操作触发备份
    #	snapshot最终将生成新的dump.rdb文件
    #	save ""用来禁用snapshot功能
    #	如下表示12小时内至少一个key变更,触发snapshot
save 43200 1
```

##### 安全配置

```MYSQL
#配置密码
requirepass passwd

#和主机器密码保持一致,实现主从复制
masterauth <master-password>
```

##### 其他配置

```MYSQL
#将redis是否以后台进程的方式运行,默认"no"
#如果"daemonize yes",将把进程id写入文件.
#请注意:  需要具有写入此目录的权限.
daemonize no


#pidfile ~/redis.pid
#指令操作:./redis.server --daemonize yes --pidfile ~/redis.pid
pidfile /var/run/redis.pid



##如果snapshot过程中出现错误,即数据持久化失败,是否终止所有的客户端write请求.
##这个选项很让人为难,"yes"表示终止,一旦snapshot故障,那么此server为只读服务;

##如果为"no",那么此次snapshot将失败,但下一次snapshot不会受到影响,不过如果出现故障,数据只能恢复到"最近一个成功点".
stop-writes-on-bgsave-error yes

##是否启用rdb文件压缩手段,默认为yes.

##压缩可能需要额外的cpu开支,不过这能够有效的减小rdb文件的大小,有利于存储/备份/传输/数据恢复.
rdbcompression yes

##是否对rdb文件使用CRC64校验和,默认为"yes",那么每个rdb文件内容的末尾都会追加CRC校验和.
##对于其他第三方校验工具,可以很方便的检测文件的完整性
rdbchecksum yes

##指定rdb文件的名称
dbfilename dump.rdb

##指定rdb/AOF文件的目录位置
dir ./

# 将当前server做为slave,并为其指定master信息.
# slaveof <masterip> <masterport>

 



##如果当前server是slave,那么当slave与master失去通讯时,是否继续为客户端提供服务,"yes"表示继续,"no"表示终止.
##在"yes"情况下,slave继续向客户端提供只读服务,有可能此时的数据已经过期.
##在"no"情况下,任何向此server发送的数据请求服务(包括客户端和此server的slave)都将被告知"error"
slave-serve-stale-data yes

##slave是否为"只读",强烈建议为"yes"
slave-read-only yes

##slave向指定的master发送ping消息的时间间隔(秒),默认为10
# repl-ping-slave-period 10

##slave与master通讯中,最大空闲时间,默认60秒.超时将导致连接关闭.
# repl-timeout 60

##slave与master的连接,是否禁用TCP nodelay选项.
##"yes"表示禁用,那么socket通讯中数据将会以packet方式发送(packet大小受到socket buffer限制),
##       可以提高socket通讯的效率(tcp交互次数),但是小数据将会被buffer,不会被立即发送,对于接受者可能存在延迟.
##"no"表示开启tcp nodelay选项,任何数据都会被立即发送,及时性较好,但是效率较低
##建议为"no"
repl-disable-tcp-nodelay no

##适用Sentinel模块(unstable,M-S集群管理和监控),需要额外的配置文件支持
##slave的权重值,默认100.当master失效后,Sentinel将会从slave列表中找到权重值最低(>0)的slave,并提升为master
##如果权重值为0,表示此slave为"观察者",不参与master选举
slave-priority 100

##重命名指令,对于一些与"server"控制有关的指令,可能不希望远程客户端(非管理员用户)链接随意使用,
##那么就可以把这些指令重命名为"难以阅读"的其他字符串.
##例如"slaveof"   "CONFIG"   "BGREWRITEAOF"   "BGREWRITE"   "FLUSHALL"等指令需要被限制访问

##配置项格式: rename-command <command> <newCommand>
# rename-command CONFIG 3ed984507a5dcd722aeade310065ce5d    (方式:MD5('CONFIG^!'))

##所允许的客户端连接数,默认为10000.
##此值不可能被设置成过大,因为每个socket连接都会以"文件描述符"的方式被系统打开,它受到系统"文件打开个数"的限制
##如果超过此值,server将会拒绝连接.
# maxclients 10000

##redis-cache所能使用的最大内存(bytes),默认为0,表示"无限制",最终由OS物理内存大小决定(如果物理内存不足,有可能会使用swap)
##如果此值设置过小(比如32字节),将直接导致server无法使用.
##此值尽量不要超过机器的物理内存尺寸,从性能和实施的角度考虑,可以为物理内存3/4.
##此配置需要和"maxmemory-policy"配合使用,当redis中内存数据达到maxmemory时,触发"清除策略".
##如果使用"清除策略"后,仍无法得到足够的内存来存储新的数据,那么write操作的客户端将会收到"error OOM.."信息,此时server只读.
##在"内存不足"时,任何write操作(比如set,lpush等)都会触发"清除策略"的执行.
##在实际环境中,建议redis的所有物理机器的硬件配置保持一致(内存一致),同时确保master/slave中"maxmemory""policy"配置一致
# maxmemory <bytes>

##"内存不足"时,数据清除策略,默认为"volatile-lru"
## volatile-lru    ->对"过期集合"中的数据采取LRU(近期最少使用)算法.如果对key使用"expire"指令指定了过期时间,那么此key将会被添加到"过期集合"中.

##每个Redis对象，都保留一个“最后访问时间”的属性，可以用来判断此对象空闲的时间，那么LRU算法就可以根据此属性来进行判断。
## 将已经过期/LRU的数据优先移除.如果"过期集合"中全部移除仍不能满足内存需求,将OOM.
## allkeys-lru ->对所有的数据,采用LRU算法
## volatile-random ->对"过期集合"中的数据采取"随即选取"算法,并移除选中的K-V,直到"内存足够"为止.
## 如果如果"过期集合"中全部移除全部移除仍不能满足,将OOM
## allkeys-random ->对所有的数据,采取"随即选取"算法,并移除选中的K-V,直到"内存足够"为止.
## volatile-ttl ->对"过期集合"中的数据采取TTL算法(最小存活时间),移除即将过期的数据.
## noeviction ->不做任何干扰操作,直接返回OOM异常.
###
##如果数据的过期不会对"应用系统"带来异常,且系统中write操作比较密集,建议采取"allkeys-lru"
# maxmemory-policy volatile-lru


##是否开启aof功能,"yes"表示开启,在开启情况下,aof文件同步功能才生效,默认为"no"
##对master机器,建议使用AOF,对于slave,建议关闭(采用snapshot),
appendonly no


##aof中文件同步机制
## always ->任何一个aof记录都立即进行文件同步(磁盘写入),安全性最高;如果write请求比较密集,将会造成较高的磁盘IO开支和响应延迟
## everysec ->每秒同步一次,性能和安全性都较高的策略,也是默认值
## no ->不直接同步,让文件同步交给OS控制,OS将会根据文件流通道中buffer情况/空闲情况进行择机写入磁盘.安全性和效率与OS设定有关.
appendfsync everysec

##在aof rewrite期间,是否对aof新记录的append暂缓使用文件同步策略,主要考虑磁盘IO开支和请求阻塞时间.
##默认为no,表示"不暂缓",新的aof记录仍然会被立即同步
##
no-appendfsync-on-rewrite no

##aof rewrite触发时机,最小文件尺寸
auto-aof-rewrite-min-size 64mb


##aof每次rewrite之后,都会记住当前aof文件的大小,当文件增长到一定比例后,继续进行aof rewrite
auto-aof-rewrite-percentage 100

##aof rewrite过程中,是否采取增量"文件同步"策略,默认为"yes",而且必须为yes.
##rewrite过程中,每32M数据进行一次文件同步,这样可以减少"aof大文件"写入对磁盘的操作次数.
aof-rewrite-incremental-fsync yes

##lua脚本运行的最大时间
lua-time-limit 5000

##"慢操作日志"记录,单位:微秒(百万分之一秒,1000 * 1000),如果操作时间超过此值,将会把command信息"记录"起来.(内存,非文件)
##其中"操作时间"不包括网络IO开支,只包括请求达到server后进行"内存实施"的时间."0"表示记录全部操作.
slowlog-log-slower-than 10000
##"慢操作日志"保留的最大条数,"记录"将会被队列化,如果超过了此长度,旧记录将会被移除.
##可以通过"SLOWLOG <subcommand> args"查看慢记录的信息(SLOWLOG get 10,SLOWLOG reset)
##通过"SLOWLOG get num"指令可以查看最近num条慢速记录,其中包括"记录"操作的时间/指令/K-V等信息
slowlog-max-len 128

##通过"TYPE key"指令查看key的数据类型
##通过"OBJECT encoding key"查看key的编码类型
##hash类型的数据结构在编码上可以使用ziplist和hashtable

##ziplist的特点就是文件存储(以及内存存储)所需的空间较小,在内容较小时,性能和hashtable几乎一样.因此redis对hash类型默认采取ziplist.

##如果hash中条目的条目个数或者value长度达到阀值,将会被重构为hashtable.
##ziplist中允许存储的最大条目个数,建议为128
hash-max-ziplist-entries 512
##ziplist中允许条目value值最大字节数,建议为1024
hash-max-ziplist-value 64

##对于list类型,将会采取ziplist,linkedlist两种编码类型.
##同hash.
list-max-ziplist-entries 512
list-max-ziplist-value 64


##zset为有序集合,有2中编码类型:ziplist,skiplist

##因为"排序"将会消耗额外的性能,当zset中数据较多时,将会被重构为skiplist.
##同hash.
zset-max-ziplist-entries 128
zset-max-ziplist-value 64

##intset中允许保存的最大条目个数,如果达到阀值,intset将会被重构为hashtable
set-max-intset-entries 512


##是否开启顶层数据结构的rehash功能,如果内存允许,请开启.
##rehash能够很大程度上提高K-V存取的效率.
activerehashing yes

##客户端buffer控制

##在客户端与server进行的交互中,每个连接都会与一个buffer关联,此buffer用来队列化亟待被client接受的响应信息.
##如果client不能及时的消费响应信息,那么buffer将会被不断积压而给server带来内存压力.如果buffer中积压的数据达到阀值,将会
##导致连接被关闭,buffer被移除."
##buffer控制类型包括:
## normal -> 普通连接
## slave ->与slave之间的连接
## pubsub ->pub/sub类型连接,此类型的连接,往往会产生此种问题;因为pub端会密集的发布消息,但是sub端可能消费不足.
##指令格式:client-output-buffer-limit <class> <hard> <soft> <seconds>",其中hard表示buffer最大值,一旦达到阀值将立即关闭连接;
##soft表示"容忍值",它和seconds配合,如果buffer值超过soft且持续时间达到了seconds,也将立即关闭连接,如果超过了soft但是在seconds之后
##buffer数据小于了soft,连接将会被保留.
# 其中hard和soft都设置为0,则表示禁用buffer控制.通常hard值大于soft.
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit slave 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60

##Redis server执行后台任务的频率,默认为10,此值越大表示redis对"间歇性task"的执行次数越频繁(次数/秒)
##"间歇性task"包括"过期集合"检测、关闭"空闲超时"的连接等,此值必须大于0且小于500.(参见redis.h源码)

##此值过小就意味着更多的cpu周期消耗,后台task被轮询的次数更频繁

##此值过大意味着"内存敏感"性较差.
##建议保持默认值
hz 10

##include指令用来载入额外的配置文件模板,也可以在redis server启动时,手动指定需要include的配置文件.
# include /path/to/local.conf
# include /path/to/other.conf
```

