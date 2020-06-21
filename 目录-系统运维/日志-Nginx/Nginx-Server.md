### Nginx-Server

概览

```
1.可以有多个server
2.可以将server拆分
3.web模块:随机主页，替换模块，文件读取，文件压缩，页面缓存，防盗链
```

#### Server

##### 开启负载均衡

```
upstream stream{
    #ip_hash;
    server 127.0.0.1:10001 weight=4;
    server 127.0.0.1:10000 weight=1;
    server 192.168.0.102:8080 backup;
}  

server {
    listen       9090;
    server_name  localhost;
location / {
    proxy_pass http://stream;  
}
}
```



拆分server

```
http {
   include /etc/nginx/conf.d/*.conf
   # etc文件夹下，所有以conf结尾的文件
}
```

Location



#### 负载均衡

