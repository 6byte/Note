## Nginx-入门到进阶

### 入门篇-常用命令

```js
--	启动
    /usr/local/nginx/sbin/nginx
    # cd /usr/local/nginx/sbin 
    # ./nginx 

--	配置文件启动
    /usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf

--	重启
    /usr/local/nginx/sbin/nginx -s reload 
    注意不是重启，是重新加载配置文件，可以当重启使用。

--	停止
	/usr/local/nginx/sbin/nginx -s stop   
	/usr/local/nginx/sbin/nginx -s quit  
    --	说明
        quit：处理完成所有请求再关闭
        Stop ：快速关闭，不再处理任何请求。

--	测试配置文件是否正确
	/usr/local/nginx/sbin/nginx -t -c /usr/local/nginx/conf/nginx.conf

--	重新打开日志文件
    /usr/local/nginx/sbin/nginx -s reopen

7.查看是否启动
    ps -ef | grep nginx
```







### 入门篇--安装

查看网页:https://blog.csdn.net/t8116189520/article/details/81909574

```mysql
1.安装依赖包

#安装依赖
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






