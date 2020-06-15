## Nginx-入门到进阶



### 入门篇	Nginx安装

查看网页:https://blog.csdn.net/t8116189520/article/details/81909574

```mysql
1.安装依赖包

#一键安装上面四个依赖
    yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel
    
2.下载并解压安装包

#创建一个文件夹
        cd /usr/local && mkdir nginx &&cd nginx

#下载tar包
        nohup wget http://nginx.org/download/nginx-1.13.7.tar.gz &
        
#解压
        tar -xvf nginx-1.13.7.tar.gz

3.安装nginx

#进入nginx目录
        cd /usr/local/nginx
安装
        	./configure && make && make install

	#编译
    make && make install



4.配置nginx.conf

# 打开配置文件
	vi /usr/local/nginx/conf/nginx.conf

5.启动
	/usr/local/nginx/sbin/nginx
	
	7.查看是否启动
ps -ef | grep nginx
```

### 入门篇	常用命令

```
1、启动
/usr/local/nginx/sbin/nginx
cd /usr/local/nginx/sbin ./nginx 

2、启动，载入当前配置
/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf

3、重新加载配置文件
/usr/local/nginx/sbin/nginx -s reload 
注意不是重启，是重新加载配置文件，可以当重启使用。

4、停止
		1./usr/local/nginx/sbin/nginx -s stop   
		2./usr/local/nginx/sbin/nginx -s quit  
注:stop和quit的区别在于
quit：Nginx在退出前完成已经接受的连接请求
Stop ：快速关闭，不再处理任何请求。

5.测试nginx配置文件是否正确
/usr/local/nginx/sbin/nginx -t -c /usr/local/nginx/conf/nginx.conf

6.重新打开日志文件

/usr/local/nginx/sbin/nginx -s reopen

7.查看是否启动
ps -ef | grep nginx
```



### 入门篇	配置详解

结构

```
1、全局块：配置影响nginx全局的指令。一般有运行nginx服务器的用户组，nginx进程pid存放路径，日志存放路径，配置文件引入，允许生成worker process数等。
2、events块：配置影响nginx服务器或与用户的网络连接。有每个进程的最大连接数，选取哪种事件驱动模型处理连接请求，是否允许同时接受多个网路连接，开启多个网络连接序列化等。
3、http块：可以嵌套多个server，配置代理，缓存，日志定义等绝大多数功能和第三方模块的配置。如文件引入，mime-type定义，日志自定义，是否使用sendfile传输文件，连接超时时间，单连接请求数等。
4、server块：配置虚拟主机的相关参数，一个http中可以有多个server。
5、location块：配置请求的路由，以及各种页面的处理情况。
```

配置结构

```
...              #全局块

events {         #events块
}
http      #http块
{
    ...   #http全局块
    server        #server块
    { 
        ...       #server全局块
        location [PATTERN]   #location块
        {
            ...
        }
        location [PATTERN] 
        {
            ...
        }
    }
    server
    {
      ...
    }
    ...     #http全局块
}
```



详细配置

```mysql
#每个指令必须有分号结束。
#配置用户或者组，默认为nobody nobody。
	#user administrator administrators;  

#允许生成的进程数，默认为1
	#worker_processes 2;  
	
#指定nginx进程运行文件存放地址
	#pid /nginx/pid/nginx.pid;  
	
 #制定日志路径，级别。这个设置可以放入全局块，http块，server块，级别以此为：debug|info|notice|warn|error|crit|alert|emerg
error_log log/error.log debug; 

events {
#设置网路连接序列化，防止惊群现象发生，默认为on
    accept_mutex on;  
#设置一个进程是否同时接受多个网络连接，默认为off    
    multi_accept on;  
    
    #use epoll;      #事件驱动模型，select|poll|kqueue|epoll|resig|/dev/poll|eventport
    worker_connections  1024;    #最大连接数，默认为512
}
http {
    include       mime.types;   #文件扩展名与文件类型映射表
    default_type  application/octet-stream; #默认文件类型，默认为text/plain
    #access_log off; #取消服务日志  
    #如$remote_addr...这些配置，在下面详细解释
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

参数详细解释
1.$remote_addr 与 $http_x_forwarded_for 用以记录客户端的ip地址；
2.$remote_user ：用来记录客户端用户名称；
3.$time_local ： 用来记录访问时间与时区；
4.$request ： 用来记录请求的url与http协议；
5.$status ： 用来记录请求状态；成功是200；
6.$body_bytes_s ent ：记录发送给客户端文件主体内容大小；
7.$http_referer ：用来记录从那个页面链接访问过来的；
8.$http_user_agent ：记录客户端浏览器的相关信息；
```

