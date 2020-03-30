## JAVA-通信

### TCP

```
简介
·面向连接，客户端和服务器端必须经过三次握手才能建立连接
·服务端一直等待客户端的响应
·IO是字节流对象
·套接字=IP地址+端口号+其他网络信息


```

**编程概要知识**

客户端

```java
理解SOCKET
·构造方法
	SOCKET(String host , int port)
        host:服务主机的名称/服务器的IP地址
        port:服务器的端口号
·成员方法
	OutputStream getOutputStream()：返回套接字的输出流
    InputStream getInputStream():返回套接字的输入流
实现步骤
    1.创建一个哭护短对象Socket，构造方法绑定服务器的IP地址和端口号
    2.使用OutputStream发送数据
    3.使用InputStream接收数据
    4.释放资源close()
注意
    ·服务器端没有IO流，必须使用客户端Socket提供的IO流输入输出
	·经过三次握手建立连接
    ·客户端创建Socket对象时，就会和服务器进行通信
    ·服务器必须先启动
```

服务器端

```java
理解ServeSocket
·构造方法
	ServerSocket( port );
		port：指定端口号，否则随机分配
·成员方法
	·Accept()
实现步骤
     1.创建服务端对象
     2.使用OutputStream给服务端发送数据
     3.使用InputStream从客户端接收数据
注意
	·服务器必须从多台客户端分辨出对应请求的客户端
    ·注意客户端发送的数据，和服务端接收顺序要一致
```

