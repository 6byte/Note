## Nginx-配置文件详解

参照网页https://blog.csdn.net/WanJiaBaoBao/article/details/83349622

### 概览

### 配置文件

```
#文件结构
main块设置的指令将影响其他所有设置；
server块的指令主要用于指定主机和端口；
upstream指令主要用于负载均衡，设置一系列的后端服务器；
location块用于匹配网页位置

文件关系
server继承main，location继承server，upstream既不继承也不被继承

1、全局块：
	1.nginx服务器的用户组
	2.nginx进程pid存放路径
	3.日志存放路径
	4.配置文件引入
	5.允许生成worker process数等。

2、events块：
	1.配置影响nginx服务器或与用户的网络连接
	2.进程的最大连接数
	3.选取事件驱动模型处理连接请求
	4.是否允许同时接受多个网路连接
	5.开启多个网络连接序列化
	
3、http块：
	1.嵌套多个server，配置代理，缓存，日志定义等绝大多数功能和第三方模块的配置
	
4、server块：配置虚拟主机的相关参数

5、location块：对路径中的参数经行处理


#负载均衡
    动静分离
    反向代理
    负载均衡
    六种负载均衡方式:轮询，权重，ipHash,least_conn，第三方策略
    除了轮询和权重，其他都是通过算法实现的

```

#### 全局模块

```MYSQL
#指定运行用户
	user nobody nobody;
	
#指定开启的进程数。每个进程耗费10M~12M内存。
#建议cpu数量=worker_processes
	worker_processes 2;
	
#日志输出。级别有#debug、info、notice、warn、error、crit
	error_log logs/error.log notice;
	
#指定进程pid的存储文件位置。
	pid logs/nginx.pid;
#设置为默认值即可
worker_rlimit_nofile 65535;

#设定Nginx的工作模式及连接数上限：
	events{
	
	#指定Nginx的工作模式。工作模式有select、poll、epoll、rtsig和/dev/poll。首选epoll工作模式。
use epoll;

#定义每个进程的最大连接数，默认是1024。最大客户端连接数由worker_processes和worker_connections决定，即Max_client=worker_processes*worker_connections。
worker_connections 65536;
}
```

#### Http模块

```mysql

#HTTP模块主要是对服务器进行配置
include       mime.types; #文件扩展名与文件类型映射表

default_type  application/octet-stream; #默认文件类型

sendfile on;  #开启高效文件传输模式，sendfile指令指定nginx是否调用sendfile函数来 输出文件，对于普通应用设为 on，如果用来进行下载等应用磁盘IO重负载应用，可设置 为off，以平衡磁盘与网络I/O处理速度，降低系统的负载。注意:如果图片显示不正常 把这个改成off。

autoindex on; #开启目录列表访问，合适下载服务器，默认关闭。
tcp_nopush on; #防止网络阻塞
tcp_nodelay on; #防止网络阻塞
keepalive_timeout 120; #长连接超时时间，单位是秒
gzip on; #开启gzip压缩输出


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

#### 负载均衡配置

```MYSQL

upstream cszhi.com{
    ip_hash;
    server 192.168.8.11:80;
    server 192.168.8.12:80 down;
    server 192.168.8.13:8009 max_fails=3 fail_timeout=20s;
    server 192.168.8.146:8080;
}

#轮询（默认）：每个请求按时间顺序逐一分配到不同的后端服务器，如果后端某台服务器宕机，故障系统被自动剔除，使用户访问不受影响；

#权重：指定权值，权值越大，分配到的访问机率越高

#ip_hash：将用户和一个服务器绑定,解决了session共享问题；

#fair：可以依据页面大小、时间长短进行负载均衡。需要fair，必须先下载

参数
down：表示当前的server暂时不参与负载均衡；

backup：预留的备份机器。当其他所有的非backup机器出现故障或者忙的时候，才会请求backup机器，因此这台机器的压力最轻；

max_fails：允许请求最大失败的次数，默认为1，超过最大次数时，返回
自定义错误；

fail_timeout：在经历了max_fails次失败后，暂停服务的时间。max_fails可以和fail_timeout一起使用。
```

#### server虚拟主机配置

```MYSQL
server是对Nginx主机进行配置

#server标志定义虚拟主机开始
server{

#listen指定NGINX主机监听和运行的端口
listen 80;

#server_name用来指定IP地址或者域名
#多个域名之间用空格分开
#监听改域名
server_name yooven.com;

#index设定首页地址
index index.html index.htm index.php;

#root用于指定nginx的网页根目录
root /wwwroot/www.cszhi.com

#指定编码格式
charset gb2312;

#指定日志存放路径
access_log logs/www.ixdba.net.access.log main;
```

#### location URL匹配配置

```MYSQL
#location支持正则表达式,条件判断匹配。

$静态资源配置
location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)$ {

#匹配成功的用户将访问root对应的路径
root /wwwroot/www/name;
#设定静态文件的过期时间
expires 30d;
}

$动态代理配置

location / {
index index.php;
proxy_pass http://localhost:8080;
}
```

## 完整文件解析

```MYSQL
#全局模块配置开始

#指定运行用户
#user  nobody;


#指定开启的进程数。每个进程耗费10M~12M内存。
#建议cpu数量=worker_processes
worker_processes  1;


#日志输出。级别有#debug、info、notice、warn、error、crit
#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;


#指定进程pid的存储文件位置
#pid        logs/nginx.pid;


#设定Nginx的工作模式及连接数上限：
events {


#定义每个进程的最大连接数，默认是1024。最大客户端连接数由worker_processes和worker_connections决定，即#Max_client=worker_processes*worker_connections。
    worker_connections  1024;


#指定Nginx的工作模式。工作模式有select、poll、epoll、rtsig和/dev/poll。首选epoll工作模式。
   use epoll;


}
#全局模块配置结束





#Http模块开始
#HTTP模块主要是对服务器进行配置

http {

#文件扩展名与文件类型映射表
    include       mime.types;


#默认文件类型
    default_type  application/octet-stream;



    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;


#开启高效文件传输模式
	#1.普通应用设为 on，
	#2.对IO要求较高，可设置 为off。注意:如果图片显示不正常 把这个改成off。
    sendfile        on;


#防止网络阻塞
    #tcp_nopush     on;


#长连接超时时间，单位是秒
    keepalive_timeout  7;


#开启gzip压缩输出
    #gzip  on;
#Http模块结束



#server虚拟主机配置开始


    server {


        #listen指定NGINX主机监听和运行的端口，该端口的流量会被转发
        listen       80;


        #server_name用来指定IP地址或者域名
        #监听的域名
        server_name  localhost;


        #指定编码格式
        #charset koi8-r;


        #指定日志存放路径
        #access_log  logs/host.access.log  main;
#server虚拟主机配置结束



#location URL
#对路径中的参数进行匹配和响应
        location / {


#匹配成功的用户将访问root对应的路径
            root   /web/Source;


#配置路径下的首页
            index  index.html index.htm;
        }


#设定静态文件的过期时间
expires 30d;


#异常访问html
        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}

```





### 目录配置

```
配置文件	作用
nginx.conf	nginx的基本配置文件
mime.types	MIME类型关联的扩展文件	
fastcgi.conf	与fastcgi相关的配置文件
proxy.conf	与proxy相关的配置
sites.conf	配置nginx提供的网站，包括虚拟主机


nginx.conf
main配置段：全局配置段。其中main配置段中可能包含event配置段；
event{}：定义event模型工作特性；
http{}：定义http协议相关配置。
```

### 默认文件





### 功能组件

#### 开启目录浏览功能

```

location / {   
        root /data/www/file                     //指定实际目录绝对路径；   
        autoindex on;                            //开启目录浏览功能；   
        autoindex_exact_size off;            //关闭详细文件大小统计，让文件大小显示MB，GB单位，默认为b；   
        autoindex_localtime on;              //开启以服务器本地时区显示文件修改日期！   
}
```

## 允许跨域请求

```
add_header 'Access-Control-Allow-Origin' '*';
add_header 'Access-Control-Allow-Credentials' 'true';
```



