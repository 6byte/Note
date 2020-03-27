## Linux-用户管理



### 添加用户

```mysql
#1.创建用户
语法		useradd -cdG 用户名
使用		useradd Jhon
选项说明
-c 		comment 	 #指定一段注释性描述
-d 		目录 		 	#指定用户主目录，先创建目录
-G 		用户组			#指定组。

#2.为该用户创建密码
语法		passwd 用户名
使用		passwd Jhon

```

### 其他操作

```mysql
# 删除用户
语法		userdel 	-r 	用户名
使用		userdel 	-r 		sam
选项说明
	-r	把用户的主目录一起删除
```

