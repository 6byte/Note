## Docker安装nginx

安装指导网页		https://segmentfault.com/a/1190000015092063

#### 启动运行

网页详细说明		https://blog.csdn.net/qierkang/article/details/92657302	

```js
#思路:开启docker，进入到docker里面将配置文件复制出来，通过-v命令将主机文件和Docker文件相关联，修改主机文件即可实现反向代理

==>#普通启动
docker run --name nginx-test -p 8080:8080 -d nginx
-p 8080:80		 端口进行映射，将本地 8080 端口映射到容器内部的 80 端口。
-p 本地端口:容器内部端口
缺点：每次重启docker都需要更改配置文件
-v：将主机文件夹和Docker文件夹相关联，格式：-v <主机目录>:<容器目录>

==>#完整启动
1.将镜像<文件>中的配置文件复制出来
docker cp nginx:/etc/nginx/nginx.conf ~/nginx/nginx.conf 
2.启动容器<进程>
docker run --name=nginx -v ~/nginx/nginx.conf:/etc/nginx/nginx.conf -v ~/nginx/conf.d:/etc/nginx/conf.d -v ~/nginx/resource:~/resource -p 8000:80 -d nginx

```

#### 项目结构

```
日志位置：/var/log/nginx/
配置文件位置：/etc/nginx/
项目位置：/usr/share/nginx/html
```

#### 配置文件详解

```
#增加虚拟主机
server { 
		#监听	www.ready.com	的80端口，客户访问时触发
		Listen 80; 
		#客户访问的网页地址，如果设置了proxy_pass，会将该请求递交给proxy_pass后面设置的网址
        server_name www.ready.com; 
        location  / { 
       
        #将匹配的location以http协议反代至主机2的8080端口
        proxy_pass http://10.10.10.130:8080; 
        }
}

########### 每个指令必须有分号结束。#################
#user administrator administrators;  #配置用户或者组，默认为nobody nobody。
#worker_processes 2;  #允许生成的进程数，默认为1
#pid /nginx/pid/nginx.pid;   #指定nginx进程运行文件存放地址
error_log log/error.log debug;  #制定日志路径，级别。这个设置可以放入全局块，http块，server块，级别以此为：debug|info|notice|warn|error|crit|alert|emerg
events {
    accept_mutex on;   #设置网路连接序列化，防止惊群现象发生，默认为on
    multi_accept on;  #设置一个进程是否同时接受多个网络连接，默认为off
    #use epoll;      #事件驱动模型，select|poll|kqueue|epoll|resig|/dev/poll|eventport
    worker_connections  1024;    #最大连接数，默认为512
}
http {
    include       mime.types;   #文件扩展名与文件类型映射表
    default_type  application/octet-stream; #默认文件类型，默认为text/plain
    #access_log off; #取消服务日志    
    log_format myFormat '$remote_addr–$remote_user [$time_local] $request $status $body_bytes_sent $http_referer $http_user_agent $http_x_forwarded_for'; #自定义格式
    access_log log/access.log myFormat;  #combined为日志格式的默认值
    sendfile on;   #允许sendfile方式传输文件，默认为off，可以在http块，server块，location块。
    sendfile_max_chunk 100k;  #每个进程每次调用传输数量不能大于设定的值，默认为0，即不设上限。
    keepalive_timeout 65;  #连接超时时间，默认为75s，可以在http，server，location块。

    upstream mysvr {   
      server 127.0.0.1:7878;
      server 192.168.10.121:3333 backup;  #热备
    }
    error_page 404 https://www.baidu.com; #错误页
    server {
        keepalive_requests 120; #单连接请求上限次数。
        listen       4545;   #监听端口
        server_name  127.0.0.1;   #监听地址       
        location  ~*^.+$ {       #请求的url过滤，正则匹配，~为区分大小写，~*为不区分大小写。
           #root path;  #根目录
           #index vv.txt;  #设置默认页
           proxy_pass  http://mysvr;  #请求转向mysvr 定义的服务器列表
           deny 127.0.0.1;  #拒绝的ip
           allow 172.18.5.54; #允许的ip           
        } 
    }
}
```

