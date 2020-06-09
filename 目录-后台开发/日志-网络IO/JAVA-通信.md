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
步骤
    1.客户端需要服务器的地址、端口
    2.通过创建Socket对象，发起链接
    3.socket对象会返回网络输入和输出流
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

### WEB服务器

概览

```
1.需要理解类的组合
2.accept是阻塞的
3.将accept放在线程中，可以一直循环阻塞监听
```

Processing

```JAVA
public class Processer implements Runnable {
    public Socket getSocket() {return socket;}
    private Socket socket;
    public Processer(Socket s) {this.socket = s;}
    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream() , true);
//            必须，否则出现404
            out.println("HTTP/1.0 200 OK");
//            必须，否则出现乱码
            out.println("Content-Type:text/html;charset=utf-8");
//            必须，否则解析不了
            out.println();
            out.println("<h1> 测试成功</h1>");
            out.close();
        }
        catch (Exception ex) {
        }
        finally {
            try {socket.close();}
            catch (Exception e) {e.printStackTrace();}
        }
    }
}
```

Main

```
public class Main {
    public static void main(String[] args) throws Exception {
//        开启端口
        ServerSocket server = new ServerSocket(8080);
//        进入循环
        while (true) {
//        打开socket
            Socket s = server.accept();
            Processer p = new Processer(s);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
```

