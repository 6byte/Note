### Mysql-CNF详解

<https://blog.csdn.net/vic_qxz/article/details/82223589>

#### 常用配置

```
#允许通过 TAB 键提示
[mysql]
auto-rehash
#需要带上提示键
prompt=[\h][\u]@\d\r:\m:\s>
```



#### 位置属性

```MYSQL
#	默认位置
/etc/my.cnf
basedir = /usr/local/mysql 		    #指定Mysql安装的绝对路径；
datadir = /data/mysql_db_data	    #指定Mysql数据存放的绝对路径；
socket = /tmp/mysql.sock		    #套接字文件
tmpdir =  /usr/local/mysql/tmp/		#保存

```

#### 连接配置

```MYSQL


#	置Mysql的最大连接数，按实际需要配置
max-connections = 320

# 设置超时时间，超时切断
wait_timeout = 9000

#	MySQL监控到客户端访问，但没有成功，如果超过10次，阻断后续访问
max_connect_errors = 10

#指定了Mysql开放的端口
port = 3306
```

#### 内存配置

```MYSQL
#指定 MySQL 查询缓冲区的大小
query_cache_size = 64M 

#	用于缓存空闲的线程
thread_cache_size = 256

#	用于指定索引缓冲区的大小
key_buffer_size = 16M

#	指定用户可创建内存表的大小
max_heap_table_size = 16M

#	值越大，group by和order by的性能越高
sort_buffer_size = 16M：

#	设置服务器读缓冲区的大小
ead_buffer_size = 16M

#	服务器重建索引时允许建立的最大临时文件的大小
myisam_sort_buffer_size = 64M

#设置临时内部堆积表（Heap）的大小
tmp_table_size = 64M

#	设置一次消息传输的最大值
max_allowd_packet = 128M
```



内容详解

```MYSQL
# 	mysql中plugin插件所在的路径
plugin_dir = /usr/local/mysql/lib/plugin 
#	文本文件,指定错误日志路径，
log-error = /data/mysql_db/data/error.log

#	设置服务器降低写操作的优先级
low_priority_updates = 1

#	设置基于key查询允许的最大查找次数
max_seeks_for_key = 100

```

#### 日志配置

```mysql

#错误日志路径
log-error =  /usr/local/mysql/log/mysql.err

#设置日志名称
log-basename=master
```

##### 慢查询日志

```MYSQL
#慢查询日志
log_slow_queries
slow_query_log_file =  /usr/local/mysql/log/slow.log

#设定慢查询的阀值，超出次设定值的SQL即被记录到慢查询日志，缺省值为10s.
long-query-time = 1

#没有使用索引，记录到慢查询日志
log-queries-not-using-indexes 

#记录查找多余1000次而引发的慢查询
min_examined_row_limit=1000　　　　

#记录由Slave所产生的慢查询
log-slow-slave-statements 
```

##### 二进制日志

```MYSQL
#打开二进制日志功能,主从复制必要
log-bin = mysql-bin 

#二进制的索引文件名
log-bin-index = mysql-bin.index 

#表示slave将复制事件写进自己的二进制日志
log_slave_updates = 1

#如果二进制日志的内容超出给定值，日志就会发生滚动
#不能将该变量大于1GB或小于4096字节
#默认值是1GB
#如果你正使用大的事务，二进制日志还会超过max_binlog_size
max_binlog_size = 1G 

查看mysql
show variables like 'datadir';
```

#### MYISAM

```MYSQL
#*** MyISAM 相关选项 ***#
key_buffer_size = 256M 
#指定用于索引的缓冲区大小，增加它可得到更好的索引处理性能。如果是以InnoDB引擎为主的DB，专用于MyISAM引擎的 key_buffer_size 可以设置较小，8MB 已足够  如果是以MyISAM引擎为主，可设置较大，但不能超过4G. 在这里，强烈建议不使用MyISAM引擎，默认都是用InnoDB引擎.注意：该参数值设置的过大反而会是服务器整体效率降低！

sort_buffer_size = 2M 
#查询排序时所能使用的缓冲区大小。排序缓冲被用来处理类似 ORDER BY 以及 GROUP BY 队列所引起的排序.一个用来替代的基于磁盘的合并分类会被使用.查看 “Sort_merge_passes” 状态变量. 在排序发生时由每个线程分配 注意：该参数对应的分配内存是每连接独占！如果有 100 个连接，那么实际分配的总共排序缓冲区大小为 100 × 6 ＝600MB,所以,对于内存在 4GB 左右的服务器推荐设置为 6-8M。 

read_buffer_size = 2M 
#读查询操作所能使用的缓冲区大小。和 sort_buffer_size 一样，该参数对应的分配内存也是每连接独享！用来做 MyISAM 表全表扫描的缓冲大小.当全表扫描需要时,在对应线程中分配.

join_buffer_size = 8M 
#联合查询操作所能使用的缓冲区大小，和 sort_buffer_size 一样，该参数对应的分配内存也是每连接独享!此缓冲被使用来优化全联合(full JOINs 不带索引的联合).类似的联合在极大多数情况下有非常糟糕的性能表现, 但是将此值设大能够减轻性能影响.通过 “Select_full_join”状态变量查看全联合的数量， 当全联合发生时,在每个线程中分配。

read_rnd_buffer_size = 8M 
#MyISAM 以索引扫描(Random Scan)方式扫描数据的 buffer大小 

bulk_insert_buffer_size = 64M 
#MyISAM 使用特殊的类似树的 cache 来使得突发插入(这些插入是,INSERT … SELECT, INSERT … VALUES (…), (…), …, 以及 LOAD DATAINFILE) 更快. 此变量限制每个进程中缓冲树的字节数.设置为 0 会关闭此优化.为了最优化不要将此值设置大于 “key_buffer_size”.当突发插入被检测到时此缓冲将被分配MyISAM 用在块插入优化中的树缓冲区的大小。注释：这是一个 per thread 的限制 （ bulk 大量）.此缓冲当 MySQL 需要在 REPAIR, OPTIMIZE, ALTER 以及 LOAD DATA INFILE到一个空表中引起重建索引时被分配.这在每个线程中被分配.所以在设置大值时需要小心.

myisam_sort_buffer_size = 64M 
#MyISAM 设置恢复表之时使用的缓冲区的尺寸,当在REPAIR TABLE 或用 CREATE INDEX 创建索引或 ALTER TABLE 过程中排序 MyISAM 索引分配的缓冲区

myisam_max_sort_file_size = 10G
#mysql重建索引时允许使用的临时文件最大大小

myisam_repair_threads = 1 
#如果该值大于 1，在 Repair by sorting 过程中并行创建MyISAM 表索引(每个索引在自己的线程内).如果一个表拥有超过一个索引, MyISAM 可以通过并行排序使用超过一个线程去修复他们.这对于拥有多个 CPU 以及大量内存情况的用户,是一个很好的选择.

myisam_recover = 64K
#允许的 GROUP_CONCAT()函数结果的最大长度
transaction_isolation = REPEATABLE-READ # 设定默认的事务隔离级别.可用的级别如下:READ-UNCOMMITTED, READ-COMMITTED, REPEATABLE-READ,SERIALIZABLE
1.READ UNCOMMITTED-读未提交 2.READ COMMITTE-读已提交 3.REPEATABLE READ -可重复读 4.SERIALIZABLE -串行
```

#### INNODB

```MYSQL
skip-innodb 
# 如果你的 MySQL 服务包含 InnoDB 支持但是并不打算使用的话,使用此选项会节省内存以及磁盘空间,并且加速某些部分

# InnoDB为独立表空间模式，每个数据库的每个表都会生成一个数据空间
innodb_file_per_table = 1 

独立表空间优点：
    1．每个表	都有自已独立的表空间。
    2．每个表	的数据和索引都会存在自已的表空间中。
    3．可以实现 单表 在不同的数据库中移动。
    4．空间可以回收（除drop table操作处，表空不能自已回收）
缺点：
	1.单表增加过大，如超过100G
结论：
	共享表空间在Insert操作上少有优势。
	其它都没独立表空间表现好。
	当启用独立表空间时，请合理调整：innodb_open_files

#启用InnoDB的status file，便于管理员查看以及监控等
innodb_status_file = 1 

# 限制Innodb能打开的表的数据，如果库里的表特别多的情况，请增加这个。这个值默认是300
innodb_open_files = 2048 


innodb_additional_mem_pool_size = 100M 
#设置InnoDB存储引擎用来存放数据字典信息以及一些内部数据结构的内存空间大小，所以当我们一个MySQL Instance中的数据库对象非常多的时候，是需要适当调整该参数的大小以确保所有数据都能存放在内存中提高访问效率的。 

innodb_buffer_pool_size = 2G 
#包括数据页、索引页、插入缓存、锁信息、自适应哈希所以、数据字典信息.InnoDB 使用一个缓冲池来保存索引和原始数据, 不像 MyISAM.这里你设置越大,你在存取表里面数据时所需要的磁盘 I/O 越少.在一个独立使用的数据库服务器上,你可以设置这个变量到服务器物理内存大小的 80%,不要设置过大,否则,由于物理内存的竞争可能导致操作系统的换页颠簸.注意在 32 位系统上你每个进程可能被限制在 2-3.5G 用户层面内存限制,所以不要设置的太高.

innodb_write_io_threads = 4
innodb_read_io_threads = 4
# innodb使用后台线程处理数据页上的读写 I/O(输入输出)请求,根据你的 CPU 核数来更改,默认是4
# 注:这两个参数不支持动态改变,需要把该参数加入到my.cnf里，修改完后重启MySQL服务,允许值的范围从 1-64

innodb_data_home_dir =  /usr/local/mysql/var/ 
#设置此选项如果你希望 InnoDB 表空间文件被保存在其他分区.默认保存在 MySQL 的 datadir 中.

innodb_data_file_path = ibdata1:500M;ibdata2:2210M:autoextend
#InnoDB将数据保存在一个或者多个数据文件中成为表空间.如果你只有单个逻辑驱动保存你的数据,一个单个的自增文件就足够好了.其他情况下.每个设备一个文件一般都是个好的选择.你也可以配置 InnoDB 来使用裸盘分区 – 请参考手册来获取更多相关内容

innodb_file_io_threads = 4 
#用来同步 IO 操作的 IO 线程的数量. 此值在 Unix 下被硬编码为 4,但是在 Windows 磁盘 I/O 可能在一个大数值下表现的更好.

innodb_thread_concurrency = 16
#在 InnoDb 核心内的允许线程数量,InnoDB 试着在 InnoDB 内保持操作系统线程的数量少于或等于这个参数给出的限制,最优值依赖于应用程序,硬件以及操作系统的调度方式.过高的值可能导致线程的互斥颠簸.默认设置为 0,表示不限制并发数，这里推荐设置为0，更好去发挥CPU多核处理能力，提高并发量

innodb_flush_log_at_trx_commit = 1 
#如果设置为 1 ,InnoDB 会在每次提交后刷新(fsync)事务日志到磁盘上,这提供了完整的 ACID 行为.如果你愿意对事务安全折衷, 并且你正在运行一个小的食物, 你可以设置此值到 0 或者 2 来减少由事务日志引起的磁盘 I/O
0 代表日志只大约每秒写入日志文件并且日志文件刷新到磁盘.
2 代表日志写入日志文件在每次提交后,但是日志文件只有大约每秒才会刷新到磁盘上.

innodb_log_buffer_size = 8M 
#用来缓冲日志数据的缓冲区的大小.当此值快满时, InnoDB 将必须刷新数据到磁盘上.由于基本上每秒都会刷新一次,所以没有必要将此值设置的太大(甚至对于长事务而言)

innodb_log_file_size = 500M 
#事物日志大小.在日志组中每个日志文件的大小，你应该设置日志文件总合大小到你缓冲池大小的5%~100%，来避免在日志文件覆写上不必要的缓冲池刷新行为.不论如何, 请注意一个大的日志文件大小会增加恢复进程所需要的时间.

innodb_log_files_in_group = 2 
#在日志组中的文件总数.通常来说 2~3 是比较好的.

innodb_log_group_home_dir =  /usr/local/mysql/var/
# InnoDB 的日志文件所在位置. 默认是 MySQL 的 datadir.你可以将其指定到一个独立的硬盘上或者一个 RAID1 卷上来提高其性能innodb_max_dirty_pages_pct = 90 #innodb 主线程刷新缓存池中的数据，使脏数据比例小于 90%,这是一个软限制,不被保证绝对执行.

innodb_lock_wait_timeout = 50 
#InnoDB 事务在被回滚之前可以等待一个锁定的超时秒数。InnoDB 在它自己的 锁定表中自动检测事务死锁并且回滚事务。 InnoDB 用 LOCK TABLES 语句注意到锁定设置。默认值是 50 秒

innodb_flush_method = O_DSYNC 
# InnoDB 用来刷新日志的方法.表空间总是使用双重写入刷新方法.默认值是 “fdatasync”, 另一个是 “O_DSYNC”.

innodb_force_recovery=1
# 如果你发现 InnoDB 表空间损坏, 设置此值为一个非零值可能帮助你导出你的表.从1 开始并且增加此值知道你能够成功的导出表.

innodb_fast_shutdown 
# 加速 InnoDB 的关闭. 这会阻止 InnoDB 在关闭时做全清除以及插入缓冲合并.这可能极大增加关机时间, 但是取而代之的是 InnoDB 可能在下次启动时做这些操作.

```

