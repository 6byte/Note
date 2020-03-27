## Mysql-系统设置

#### 密码修改

```mysql
 use mysql;
 update user set authentication_string=passworD("00000000") where user='root';
# 该方法针对	Mysql  5.7	以上版本有效
```

#### 开启远程连接

```
1.use mysql;
2.update user set host="%" where user="root";
2.刷新权限：FLUSH PRIVILEGES;

CMD远程连接
mysql -h yooven.xyz -P 3306 -u root -p00000000
mysql -h 主机地址 -P 端口号 -u 用户名 -p 密码
```

#### 故障排除

##### 字符编码

```java
1.故障
Character set 'utf-8' is not a compiled character set and is not specified
//-->1解决方案
将mysql安装目录下的my.ini文件的default-character-set=utf-8更改为default-character-set=utf8即可，简单来说就是将"utf-8"更改为"utf8"即可。
    

```

##### 时间错误

```
set global time_zone='+8:00';
```



#### 备份还原

##### 导入Mysql文件

```

```
