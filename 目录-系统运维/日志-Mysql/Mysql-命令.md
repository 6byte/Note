## Mysql-系统设置

#### 启动关闭

```mysql
启动
service mysqld start

关闭
service mysqld stop

重启
service mysqld restart

状态
service mysqld status
```

#### 配置

```mysql
#查看配置
cat /etc/my.cnf

#修改配置
vi /etc/my.cnf
```

日志配置

```MYSQL
# 在[mysqld] 中輸入

二进制日志
#指定日志文件前缀为sqlbin,生成的文件是sqlbin.0001...
log_bin = sqlbin
#配置二进制日志格式
binlog_format = STATEMENT || ROW ||MIXED

查询日志，记录所有信息
#在data目录下查看文件
general_log = 1
general_log_file = query_log.log

慢查询日志，默认关闭
#慢查询日志开启
slow_query_log = 1
slow_query_log_file = slow_query.log
#指定时间限制
long_query_time = 2

```

#### 密码修改

```mysql
# 该方法针对	Mysql  5.7	以上版本有效

use mysql;
 update user set authentication_string=passworD("密码") where user='用户名';

```

#### 用户管理

##### 创建管理

```MYSQL
#创建用户，并设置密码
create user '自定义用户名'@'%' identified with mysql_native_password by '你要给用户指定的密码';
flush privileges;
```

##### 分配权限

```MYSQL
#如果碰到其他错误，可以先刷新权限
#旧版本
grant all privileges on *.* to '自定义用户名'@'IP地址' with grant option;

#新版本
ALTER USER '用户名'@'IP地址' IDENTIFIED WITH mysql_native_password BY '密码';

flush privileges;
```



#### 开启远程连接

```
远程LINUX主机操作
1.use mysql;
2.update user set host="%" where user="root";
2.刷新权限：FLUSH PRIVILEGES;

Windows远程连接
mysql -h 114.114.114.114 -P 3306 -u root -p123456789
mysql -h 主机地址 -P 端口号 -u 用户名 -p 密码
```

#### 故障排除

##### 字符编码

```java
1.故障
Character set 'utf-8' is not a compiled character set and is not specified
//-->解决方案
default-character-set=utf8

```

##### 时间错误

```
set global time_zone='+8:00';
```

##### 其他错误

```MYSQL
#Job for mysqld.service failed because the control process exited with error code.
把/etc/my.cnf中的	datadir=/var/lib/mysql注视掉

#mysqld: error while loading shared libraries: libnuma.so.1
yum -y install numactl
```



#### 备份复制



##### 导入Mysql文件

```

```

#### 集群搭建

<https://www.cnblogs.com/eleven24/p/7350000.html>：快速搭建

<https://www.cnblogs.com/clsn/p/8150036.html>:很全面

##### Master配置

```mysql
#[mysqld]

#开启二进制日志，必须
log-bin=mysql-bin
#保证主库唯一
server-id=2
#指定不需要同步的数据库
binlog-ignore-db=information_schema
binlog-ignore-db=cluster
binlog-ignore-db=mysql
#指定需要同步的数据库
binlog-do-db=test


```

##### 授权

```mysql
1.在主库添加账号，用于完成主从复制
#创建用户，并设置密码
create user '自定义用户名'@'%' identified with mysql_native_password by '你要给用户指定的密码';

#分配权限，注意，新旧版本分配权限的命令是不同的
	grant replication slave on *.* to "用户名"@"ip地址" identified by "密码"
	FLUSH PRIVILEGES;

2.查看状态
show master status;
信息说明
	File:指定从哪个文件中进行同步
	Position：从文件中的第x个位置
	
    #旧版本
    grant FILE on *.* to 'client'@'118.190.245.157' identified by '00000000';

    #新版本
    ALTER USER '用户名'@'IP地址' IDENTIFIED WITH mysql_native_password BY '密码';

```

##### Slave配置

配置：

```mysql

[mysqld]
log-bin=mysql-bin
#保证主库唯一
server-id=2

配置远程master主机：
登录mysql后执行下面语句
  CHANGE MASTER TO
  MASTER_HOST='主库中设置的IP地址';
  MASTER_USER='主库中设置的用户名';		
  MASTER_PASSWORD='主库中设置的密码';
  MASTER_PORT=3306;
  #在主库使用命令show master status;获取下面的信息
  MASTER_LOG_FILE='mysql-bin.000012';
  MASTER_LOG_POS=120;
```

命令：

```MYSQL
start  slave;  # 启动从库复制
stop slave		#停止协议
SET GLOBAL SQL_SLAVE_SKIP_COUNTER=1; #最好设置
--和主机中配置的账号密码保持一致
```

#### 配置表

```mysql
#配置客户端
[client]
port = 3306
socket = /var/lib/mysql/mysql.sock
default-character-set=utf8

#配置本地数据库
[mysqld]
#配置数据存放位置
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
#配置错误日志存放位置
log-error=/var/log/mysqld.log
#配置默认编码格式
character-set-server=utf8

#配置读写分离
server-id = 3
#指定二进制文件
log-bin = mysqlbin

pid-file=/var/run/mysqld/mysqld.pid
symbolic-links=0
```

