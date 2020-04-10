#### Java-服务器

```
1.需要理解类的组合
2.accept是阻塞的
3.将accept放在线程中，可以一直循环阻塞监听
```

Processing类

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

Main类

```JAVA
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

