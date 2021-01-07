## 防火墙和端口

------

**`开启`**

```
#开启
systemctl start firewalld		
systemctl start firewalld.service

#开启80端口
firewall-cmd --zone=public --add-port=80/tcp --permanent 

#批量开启
firewall-cmd --zone=public --add-port=1000-5000/tcp --permanent
```

`重启`

```

重启防火墙
firewall-cmd --reload    
service firewalld restart

开机自启动
systemctl enable firewalld.service
```

`关闭`

```
关闭防火墙
service iptables stop
关闭开机自启动
systemctl disable firewalld.service
```



#### `端口列表`

```
firewall-cmd --permanent --list-port
```

#### 端口信息

`根据端口号`

```
lsof -i:端口号
lsof -i:8080

netstat -tunlp|grep 8080
```

`根据程序名字`

```
ps -ef |grep nginx
```

`查看所有端口`

```
netstat -nat  只显示部分
netstat -apn  可以查看系统进程
netstat -ntlp 查看TCP端口
```

------



## 查看进程

`TOP`

```
说明:动态监视进程,查看内存占用最高
使用:		top
```

## 网络

```
/etc/init.d/network restart		刷新网络DNS缓存
```

