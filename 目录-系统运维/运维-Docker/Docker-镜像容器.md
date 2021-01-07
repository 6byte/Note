### Docker-基本命令

#### 镜像命令

```js
docker images	查看docker中安装了哪些镜像

--	查找镜像
docker search 镜像名称	#例如	docker search mysql

--	添加镜像
docker pull 镜像名称	#例如	docker pull mysql
docker pull mysql:5.7	#指定版本下载,需要先搜索

--	删除软件
docker rmi -f 镜像ID 					 #指定名字或ID删除软件
docker rmi -f 镜像ID 镜像ID 			#删除多个软件
docker rmi -f $(docker images -aq)		#删除所有软件
```

#### 容器命令

```sql
--	进入容器
    docker attach 容器名	#开启一个新的终端
    docker exec	容器名		#打开之前已经开启的终端

--	文件复制
    docker cp 容器id:容器内路径 主机路径

--  重启策略
    docker run --restart=	\
        always|no|on-failure|on-failure:3|always|unless-stopped
	
--	删除容器
    docker rm -f	#能删除所有状态的容器
    docker rm		#不能删除正在运行的容器

--	启动容器
	docker start mysql	#只能启动已经停止的容器
	
--	停止容器
    docker stop mysql	#停止正在运行的容器
    
--	查看正在运行的容器
    docker ps -a
```

#### 卷命令

```sql
--	查看卷
	docker volume ls
	
--	删除卷
	docker volume rm mysql001 (上面查到的卷名)
```

