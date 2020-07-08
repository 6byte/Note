### Redis-安装

<https://www.jianshu.com/p/546ff3b8151d>

常用命令

```
--	启动
redis-server /usr/local/redis/etc/redis.conf
/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf 

--	杀死　
pkill redis 
```



#### 安装

```mysql
wget http://download.redis.io/releases/redis-4.0.8.tar.gz
tar xzvf redis-4.0.8.tar.gz
cd redis-4.0.8
make
cd src
make install PREFIX=/usr/local/redis

#将conf配置文件移动到安装目录下
#conf文件在redis-4.0.8中
mkdir /usr/local/redis/etc
mv redis.conf /usr/local/redis/etc


```

#### 自启动

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
将daemonize no 改成daemonize yes
```



#### 卸载

```
rm -rf /usr/local/redis //删除安装目录
rm -rf /usr/bin/redis-* //删除所有redis相关命令脚本
rm -rf /root/download/redis-4.0.4 //删除redis解压文件夹
```

