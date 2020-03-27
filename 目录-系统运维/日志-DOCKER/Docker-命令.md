## Docker-常用

命令大全		https://www.runoob.com/docker/docker-command-manual.html

#### 基本信息

```
容器和镜像的关系
容器:		进程
镜像:	    文件

安装VIM，两步即可
apt-get update
apt-get install vim
```

#### 启动服务

```

重启
service docker restart  

遇到下面故障,可以重启docker
Cannot connect to the Docker daemon ...
```

#### 容器信息

```
-->查看容器信息
docker container ls -a

-->重启策略，当服务器重启后，docker容器会被清空，指定策略，可以恢复状态
no，默认策略，在容器退出时不重启容器
on-failure，在容器非正常退出时（退出状态非0），才会重启容器
on-failure:3，在容器非正常退出时重启容器，最多重启3次
always，在容器退出时总是重启容器
unless-stopped，在容器退出时总是重启容器，但是不考虑在Docker守护进程启动时就已经停止了的容器

示例：docker run -p 8092:80 --name apicloud -v /home/lifehaier:/var/www/html -d --restart=on-failure:3 hub.docker.terminus.io:5000/aabb:20180315

```

#### 文件复制

```
1、从容器里面拷文件到宿主机
docker cp 容器名：要拷贝的文件在容器里面的路径       要拷贝到宿主机的相应路径 
示例：docker cp testtomcat：/usr/local/tomcat/webapps/test/js/test.js /home/app
　　
2、从宿主机拷文件到容器里面
docker cp 要拷贝的文件路径 	容器名：要拷贝到容器里面对应的路径
示例：docker cp /opt/test.js testtomcat :/usr/local/tomcat/webapps/test/js

注意:docker cp /opt/test.js testtomcat:(这里没有空格)/usr/local/tomcat/webapps/test/js
```

