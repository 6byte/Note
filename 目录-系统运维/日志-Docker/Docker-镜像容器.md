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

##### 运行

```js
启动
    docker run [参数]	image
      --	参数说明
            --name = "Name"		 	 #容器名字
            -d					    #后台方式运行
            -it						#能对容器进行一系列操作

            -p						#随机端口
            -p 主机端口:容器端口	   #指定主机端口、容器的端口
            -p ip:主机端口:容器端口	   #指定IP，主机端口，容器端口

    --	运行
        docker run -itd --name 指定容器的名字 指定镜像 指定命令行
        docker run -itd --name mysql-01 mysql /bin/bash
同步
	docker -v 主机目录:容器目录
```

##### 配置容器

```
--	进入容器
docker attach 容器名	#开启一个新的终端
docker exec	容器名		#打开之前已经开启的终端

--	从容器中启动复制文件到主机(非常重要)
docker cp 容器id:容器内路径 主机路径
```



##### 其他命令

```mysql
--	查看正在运行的容器
docker ps -a

--	删除容器
docker rm -f	#能删除所有状态的容器
docker rm		#不能删除正在运行的容器

--	启动容器
docker start mysql	#只能启动已经停止的容器

--	停止容器
docker stop mysql	#停止正在运行的容器

--	查看日志
docker -ft --tail 
```
