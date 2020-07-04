### Nginx-杂记

系统模块

https://blog.csdn.net/L835311324/article/details/82560275

内置变量

https://www.cnblogs.com/pyng/p/10451295.html

概览

```
1.可以配置多个server
2.server匹配网址|IP地址
3.localhost:匹配路径
4.http:匹配web协议
5.常用内置变量
	--$http_x_forwarded_for //Http请求携带的信息
	--$http_user_agent	//客户端访问的设备
```

#### 最简配置

```
pid        logs/nginx.pid;
events {}
http {
    server {
    listen       9090;
    server_name  134.175.79.33;
    location /static { 
    root html/;
    index index.html; #跳转首页
}}}
```

#### 配置

##### Server

```
listen，server_name，location，root，index
```

##### Localtion

```
location /static {
	root /html;
}
本地访问路径:/html/static
网络映射路径:http://1.1.1.1/static
```



#### 日志配置

##### 访问日志

概览

```mysql
1.配置格式,去内置变量中查找添加
log_format main '$remote_addr---$request_time';
#该日志名为main，作为下面路径的参数

2.配置日志路径
access_log /root/log/access.log main;
```

举例

```MYSQL
http {
    log_format main '$remote_addr---$request_time';
    access_log /root/log/access.log main;
}
#记录了远程IP和响应时间
```

##### 状态日志

概览

```
1.开启
location / { 
	stub_status on;#默认关闭
}
```

#### 功能开启

##### 下载站点

```
location / {
	#文件映射,默认关闭
    autoindex on;
    
    #默认on，显示文件大小，单位：bytes。
    #off，显示文件估计大小，单位：kB||MB||GB
    autoindex_exact_size off;
    
    #默认off，文件时间为GMT时间。
    #on，显示服务器文件时间
    autoindex_localtime on;
}
```

##### 请求限制

概览

```
概览:分两种方式
1.连接频率限制:limit_conn_module
2.请求频率限制:limit_req_module
```

入门使用

```
http {    
    #$binary_remote_addr:以二进制传输，用户IP作为标识
	#zone=one:10m :生成一个大小为10M，名字为one的内存区域
	#rate=1r/s	:限制客户端每秒1次访问，还可以有比如30r/m的。
    limit_req_zone $binary_remote_addr zone=one:10m rate=1r/s;

    server {
    location /{
    #zone=one ：与上面的zone对应
	#burst=5：设置值为5的缓冲区
	#nodelay：
		#如果设置，超频访问且缓冲区也满了，返回503
		#如果没有设置，则所有请求会等待排队。
    	limit_req zone=one burst=5 nodelay;
    	#limit_conn zone=one burst=5 nodelay;
	}
}
```

##### 访问控制

```
1.基于IP的访问控制:http_access_module
2.基于用户的登录认证:http_auth_basic_module
3.返回:http,server,localtion
4.返回403页面
5.局限性，通过代理可以
```

使用

```
http {    
    server {
    location /{
    #拒绝恶意IP
		deny 1.1.1.1;
		allow all;
		
	#仅允许管理员
		allow 1.1.1.1;
		deny all;
	}
}
```

##### 用户认证

使用

```
location / {
	#auth_basic :提示语
    auth_basic "dont come in";
    auth_basic_user_file conf/htpasswd;
}
```

生成密码

```
yum install httd-tools

htpasswd -c /etc/nginx/auth_conf admin 
#在目录 /etc/nginx/auth_conf下生成 
admin:$apr1$vGfgrEsl$yLX0mfr2S1U3g/62PLdzK1
```

##### 静态资源

```
tcp_nopush : 传输效率高，实时性低
tcp_nodelay :传输效率低，实时性高
```

##### 浏览器缓存

```
 expires 10d;
 location /(img|js|css|static)/\.(jpg|png)$ {
     expires 10d;
 }
```

##### 跨域访问

```
add_header Address-Control-Allow-Origin 允许访问的域名;
add_header Address-Control-Allow-Method Get,POST,PUT;
```

##### 防盗链

参数说明

```JS
valid_referers配置说明：
    none : 允许没有http_refer的请求；
    blocked : 允许没有http://开头的请求；
    server_names : 指定域名或IP的请求访问；
删除none和 blocked,则不允许通过文件直链进行访问和下载，只能在限定的域名中引用和下载。
```



文件类型

```JS
# 需要防盗的后缀
location ~* \.(jpg|jpeg|png|gif|bmp|swf|rar|zip|doc|xls|pdf|gz|bz2|mp3|mp4|flv)$

    valid_referers none blocked 192.168.0.1 *.google.com;

    if ($invalid_referer) {
        # return 403;
        rewrite ^/ https://site.com/403.jpg;
    }
    root  /html;
}
```

