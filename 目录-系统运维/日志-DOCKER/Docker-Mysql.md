## Docker安装Mysql

### 下载

```
docker pull mysql
```

### 启动

```
docker run -itd --name 自定义名字 -p 端口:端口 -e MYSQL_ROOT_PASSWORD=密码 mysql
docker run -itd --name mysql -p 9901:9901 -e MYSQL_ROOT_PASSWORD=90909090 mysql
```

### 登录

查看镜像

```
docker ps -a
获取镜像id:		243c32535da7
```

进入容器

```
docker exec -it 243c32535da7 /bin/bash
进入容器启动MySQL

```

登录

```MYSQL
mysql -h localhost -P9901 -uroot -p
```

