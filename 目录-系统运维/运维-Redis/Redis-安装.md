### Redis-安装

<https://www.jianshu.com/p/546ff3b8151d>

常用命令

```
--	启动
redis-server /usr/local/redis/etc/redis.conf
/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf 

--	杀死　
pkill redis 

--	目录
	/usr/local/redis/bin	客户端和服务端
```



#### 安装-Linux

```sql
--	下载
	wget http://download.redis.io/releases/redis-4.0.8.tar.gz
--	解压
	tar xzvf redis-4.0.8.tar.gz 
--	安装
    cd redis-4.0.8
    make	--此处有坑，解决方案在下方故障中
    cd src
    make install PREFIX=/usr/local/redis
--  单机版统一管理
    #移动配置文件	
    mkdir /usr/local/redis/etc
    cd redis根目录 #conf文件在根目录中
    cp redis.conf /usr/local/redis/etc

```

##### 自启动

开机启动

```
vi /etc/rc.local 
添加：
/usr/local/redis/bin/redis-server 
/usr/local/redis/etc/redis.conf

```

后台启动

```
vi /usr/local/redis/etc/redis.conf 
将 daemonize no 改成daemonize yes
```



##### 故障

```
错误提示:
	Error jemalloc/jemalloc.h: No such file or directory
解决方案
	make MALLOC=libc

错误提示
	 /bin/sh: cc: command not found
解决方案
	 yum -y install gcc gcc-c++ libstdc++-devel
```

##### 卸载

```
rm -rf /usr/local/redis //删除安装目录
rm -rf /usr/bin/redis-* //删除所有redis相关命令脚本
rm -rf /root/download/redis-4.0.4 //删除redis解压文件夹
```

#### Windows-安装

```
1.后台守护
redis-server --service-install redis.windows.conf --loglevel verbose

2.启动服务
redis-server --service-start

3. 停止服务
redis-server --service-stop  

4.添加到系统环境变量path
C:\Program Files\Redis
使用 redis-cli 直接启动客户端


```

