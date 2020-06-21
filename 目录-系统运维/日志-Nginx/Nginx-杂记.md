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
    index a.html; #跳转首页
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

概览

```
autoindex
    1.能在location ，server，http中使用
    2.默认关闭
    3.打开:autoindex on;
参数
	autoindex_exact_size off;
         默认on，显示文件大小，单位：bytes。
         off，显示文件估计大小，单位：kB||MB||GB
     autoindex_localtime on;
     	 默认off，文件时间为GMT时间。
          on，显示服务器文件时间
```

使用

```
location / {
    autoindex on;
    autoindex_exact_size off;
    autoindex_localtime on;
}
```

##### 请求限制

概览

```
概览:分两种方式
1.连接频率限制:limit_conn_module
2.请求频率限制:limit_req_module
用于流量拦截
```

入门使用

```
http {
#一定要在全局使用
limit_req_zone $binary_remote_addr zone=one:10m rate=1r/s;
    server {
    listen       9090;
    server_name  134.175.79.33;

    location / {
    limit_req zone=one burst=5  nodelay;
}}}
```

