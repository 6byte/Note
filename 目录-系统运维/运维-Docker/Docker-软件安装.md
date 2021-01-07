## 	Docker-软件安装

#### 安装-Nginx

##### 默认路径

```
--	配置文件
/etc/nginx

--	HTML根目录
/usr/share/nginx/html
```

##### 基础安装

```js
--	下载
docker pull nginx

--	运行
docker run --name nginx-name -p 8080:80 -d nginx

--  注意:容器中nginx默认监听80端口
```

##### 集群安装

```SQL
--	命令
    docker run -d -it --name=nginx-1  \
    --restart=always \
     -p 8080:80 \
    -v nginx-html-1:/usr/share/nginx/html  \
    -v nginx-conf-1:/etc/nginx  \
    nginx
    
--	注意： 配置多个nginx 需要修改conf配置并重启

```

#### 安装-Mysql

概览

```sql
容器内默认Mysql路径
    --	配置目录
    /etc/mysql

    --	数据目录
    /var/lib/mysql
```



##### 简单安装

```SQL
--	下载
	docker pull mysql

--	简单安装

    docker run -d \
    --name mysql -p 3306:3306\
    -e MYSQL_ROOT_PASSWORD=passwd\
    mysql
--	没有映射DATA目录，容易丢失数据
```

##### 集群安装

```sql
--	添加配置安装
    docker run -d \
    --restart=always \
    --name mysql-1 -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=passwd \
    -v mysql-conf-1:/etc/mysql/conf.d \
    -v mysql-data-1:/var/lib/mysql \
    mysql \
 
--	进入容器
docker exec -it mysql /bin/bash

--	部署多个Mysql
	1.修改	conf文件
	2.修改	datadir默认路径
	3.进入容器,创建对应datadir路径
	3.修改	端口
```

#### 安装-TOMCAT

概览

```js
--	容器中	TOMCAT	路径
    /usr/local/tomcat webapps，conf，bin
```

##### 基本安装

```js
docker run -d \
    -v /root/tomcat/webapp/:/usr/local/tomcat/webapps \
    --name tomcat \
    -p 8080:8080 tomcat \
```

##### 集群安装

```sql
docker run -d \
--restart=always \
--name tomcat-1 \
    -v tomcat-1:/usr/local/tomcat/\
    -p 8080:8080 tomcat
```

