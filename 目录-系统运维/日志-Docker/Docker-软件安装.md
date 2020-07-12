## Docker-软件安装

#### 安装Nginx

##### 安装

```js
--	下载
docker pull nginx

--	运行
docker run --name nginx-name -p 8080:80 -d nginx
	--	命令解释
        -p 8080:80	#	将主机8080端口映射到容器中的80端口
        
--	测试
wget localhost:8080
```

##### 配置

```
--	复制配置
    docker cp 容器路径 主机绝对路径
    docker cp mysql:/etc/mysql/mysql.conf.d/mysqld.cnf /root/mysqld.cnf

```



#### 安装Mysql

概览

```
--	容器mysql配置目录
/etc/mysql

--	容器数据目录
datadir = /var/lib/mysql
```



##### 简单安装

```mysql
--	下载
	docker pull mysql

--	简单安装
    docker run -d \
    --name mysql -p 3306:3306\
    -e MYSQL_ROOT_PASSWORD=passwd\
    mysql

--	复制 容器配置文件 到主机中
	# docker cp 容器名:容器中的配置绝对路径  主机的绝对路径
	docker cp mysql-01:/etc/mysql/my.cnf  /root/conf/mysql.cnf
	
--	复制 主机配置文件 到容器中	
	docker cp  /root/conf/mysql.cnf  mysql-01:/etc/mysql/my.cnf
```

##### 同步安装

```mysql

--	添加配置安装
    docker run -d \
    --name mysql -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=passwd \
    -v /home/mysqls/mysql001/conf:/etc/mysql/conf.d \
    -v /home/mysqls/mysql001/data/:/var/lib/mysql \
    mysql
 
    docker run -d \
    --name mysql -p 3306:3306\
    -e MYSQL_ROOT_PASSWORD=passwd \
    mysql
	--参数解释
		-v:	主机目录:容器目录
	--注意事项
		使用-v主机文件类型一定要和容器文件类型对应
		
--	进入容器
docker exec -it mysql /bin/bash
```

#### 安装TOMCAT

概览

```js
--	容器中	TOMCAT	路径
        /usr/local/tomcat/webapps
        /usr/local/tomcat/bin
        /usr/local/tomcat/conf
--	主机对应路径
        /home/tomcats/tomcat-001/webapps
        /home/tomcats/tomcat-001/conf
        /home/tomcats/tomcat-001/bin
```

安装命令

```js
--	基本安装
       docker run -d \
           -v /root/tomcat/webapp/:/usr/local/tomcat/webapps \
		  --name tomcat \
           -p 8080:8080 tomcat
          

--	集群安装
	docker run -d \
    	--name tomcat-001 \
        -v /home/tomcats/tomcat-001/webapps:/usr/local/tomcat/webapps \
        -v /home/tomcats/tomcat-001/conf:/usr/local/tomcat/conf \
        -v /home/tomcats/tomcat-001/bin:/usr/local/tomcat/bin \
        -p 8080:8080 tomcat
```

