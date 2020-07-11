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
--	先查看当前在线用户
w users who

--	强制下线
pkill -kill -t pts/0	//强制 pts/0 下线

--  查看登录失败的信息
lastb lastlog
```

