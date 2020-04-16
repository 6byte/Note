## 防火墙&端口

### 防火墙

```
开启防火墙
systemctl start firewalld

开启80端口
firewall-cmd --zone=public --add-port=80/tcp --permanent
开启8080端口
firewall-cmd --zone=public --add-port=8080/tcp --permanent


重启防火墙命令
firewall-cmd --reload    
service firewalld restart

开启防火墙
systemctl start firewalld.service

开机自启动
systemctl enable firewalld.service
关闭开机自启动
systemctl disable firewalld.service
```



### 查看端口列表

```
firewall-cmd --permanent --list-port
```

### 端口信息

```
第一种方式：根据端口号
lsof -i:端口号
lsof -i:8080

第二种方式：根据端口号
netstat -tunlp|grep 8080

第三种方式：根据程序名字
ps -ef |grep nginx

查看所有端口
netstat -nat  只显示部分
netstat -apn  可以查看系统进程
netstat -ntlp 查看TCP端口
```

### 查看进程

#### 命令->PS

```js
说明:ps命令用于报告当前系统的进程状态。可以搭配kill指令随时中断、删除不必要的程序
使用:		ps aux
#参数
a：显示当前终端下的所有进程信息，包括其他用户的进程。
u：使用以用户为主的格式输出进程信息。
x：显示当前用户在所有终端下的进程。

```

#### 命令->TOP

```
说明:动态监视进程
使用:		top
```

### 网络

```
/etc/init.d/network restart		刷新网络DNS缓存
```

