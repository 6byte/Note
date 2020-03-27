## Linux-用户管理

### Linux-增删改查

添加用户

```mysql
1、添加用户
useradd 参数 用户名
参数
-c 		comment 添加注释。
-d 		指定目录，一般在/home目录下
-s 		Shell文件 指定用户的登录Shell。 
ex:
    useradd -d /home/lucy  lucy
    # useradd –d /usr/lucy -m lucy
    # 网上的创建方式，在centos不能运行
    
2.创建密码
    语法		passwd 用户名
    使用		passwd lucy
    
3.查看是否成功
    定义			id 用户名
    ex			 id lucy

```

权限分配

```
将目录分配给用户
chown -R 用户名:用户名 目录
chown -R lucy:lucy ./local

```

Root权限管理

```
1.修改计算机名称
sudo vi /etc/hostname
reboot	#重启生效

```





### Linux-多用户管理

```JS
#强制用户下线
1.先查看当前在线用户
w	OR	who

//结果
USER     TTY      FROM             LOGIN@   IDLE   JCPU   PCPU WHAT
root     pts/1    43.250.201.10    14:57    1:58m  0.02s  0.02s -bash
root     pts/2    119.39.248.42    17:30    1.00s  0.03s  0.00s w
2.强制下线
pkill -kill -t pts/0	//强制 pts/0 下线
```

