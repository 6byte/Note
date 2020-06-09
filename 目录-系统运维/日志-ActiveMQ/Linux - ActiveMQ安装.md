## Linux - ActiveMQ安装

概览

```
JMS服务端口	61616
管理端口	8161

控制台管理服务
http://127.0.0.1:8161/admin/
admin/admin


```



### 安装

```mysql
解压
tar -zxvf active.5.5.tar.gz -C 目标路径

运行,进入bin目录
./activemq start

```

开启防火墙

```
firewall-cmd --zone=public --add-port=8161/tcp --permanent
firewall-cmd --reload   
```



### 运维



|        命令         |   说明   |
| :-----------------: | :------: |
| ./activemq   status | 查看状态 |
|  ./activemq start   |   启动   |
|   ./activemq stop   |   关闭   |
| ./activemq restart  |   重启   |
|                     |          |

