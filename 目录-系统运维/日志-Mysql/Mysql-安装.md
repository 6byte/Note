## Mysql-入门

网页:	https://www.jianshu.com/p/fe476f511485

### 安装步骤

```mysql
0.	一定要配置Mysql扩展源，安装出错，找下面的故障解决

rpm -ivh http://repo.mysql.com/yum/mysql-5.7-community/el/7/x86_64/mysql57-community-release-el7-10.noarch.rpm

1.	下载
wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.15-1.el7.x86_64.rpm-bundle.tar

2.	解压，切换目录，统一管理软件
    mkdir /usr/local/mysql 
解压
    tar -xvf mysql-8.0.15-1.el7.x86_64.rpm-bundle.tar -C /usr/local/mysql/
    
3.	安装,一定要在解压后的MySQL中安装
cd /usr/local/mysql

rpm -ivh mysql-community-common-8.0.15-1.el7.x86_64.rpm --nodeps --force && rpm -ivh mysql-community-libs-8.0.15-1.el7.x86_64.rpm --nodeps --force && rpm -ivh mysql-community-client-8.0.15-1.el7.x86_64.rpm --nodeps --force && rpm -ivh mysql-community-server-8.0.15-1.el7.x86_64.rpm --nodeps --force


4.初始化数据库
mysqld --initialize;
chown mysql:mysql /var/lib/mysql -R;
systemctl start mysqld.service;
systemctl enable mysqld;

5.登录修改密码
#查看密码
	cat /var/log/mysqld.log | grep password
	
#登录修改密码
	mysql -u root -p "上面查到的密码"
	
#修改密码,要加引号
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '你自己的密码'; 


```

#### 创建远程用户

```
创建远程用户
#创建用户，并设置密码
create user '自定义用户名'@'%' identified with mysql_native_password by '你要给用户指定的密码';

#分配权限
grant all privileges on *.* to '自定义用户名'@'%' with grant option;

#刷新权限
flush privileges;
```



### 配置端口

```js
#编辑文件，并在[mysqld]添加端口号
vim /etc/my.cnf

port=4000

#重启
systemctl restart mysqld

#指定端口启动
mysql -uroot -p -h127.0.0.1 -P4000

#查看端口
show global variables like 'port'; 命令查看端口号,
```

### 修改密码

```js
#1.编辑配置文件
	vim /etc/my.cnf
#2.在 [mysqld] 下 加上
	skip-grant-tables
#3.重启MySQL
	systemctl restart mysqld
4.免密进入mysql并且use一个数据库
mysql>		set global validate_password_policy=0;
mysql> 		set global validate_password_mixed_case_count=0;
mysql> 		set global validate_password_number_count=3;
mysql> 		set global validate_password_special_char_count=0;
mysql> 		set global validate_password_length=3;
mysql> 		SET PASSWORD FOR 'root'@'localhost' = PASSWORD('YourPassword');



2.跳过密码
/etc/my.cnf
#skip-grant-tables
```

### 重启数据库

````
systemctl restart mysqld
````

#### 备用方案

参照网页		https://www.jianshu.com/p/35ec7fda9671

```
一、配置Mysql扩展源

rpm -ivh http://repo.mysql.com/yum/mysql-5.7-community/el/7/x86_64/mysql57-community-release-el7-10.noarch.rpm

二、yum安装mysql
yum install mysql-community-server -y

三、启动Mysql，并加入开机自启
systemctl start mysqld
systemctl enable mysqld

四、使用Mysq初始密码登录数据库
mysql -uroot -p$(awk '/temporary password/{print $NF}' /var/log/mysqld.log)

五、修改数据库密码
set global validate_password_policy=0;
set global validate_password_length=1;
set password for root@localhost = password('Your Password');
```

#### 卸载

```
  #执行yum卸载
  yum remove mysql mysql-server mysql-libs compat-mysql51
  #通过find命令找到路径，逐个卸载
  find / -name mysql
```

