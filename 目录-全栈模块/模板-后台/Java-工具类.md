## Spring-Boot 工具组件

### Tool-WebSocket

概览

```
创建WebSocket
1.配置类		<必须创建>
2.逻辑处理类，主要有4个方法，一个属性
	四个方法
        1. @OnOpen	          		连接建立成功调用的方法
        2. @OnMessage      		 	接收客户端发送过来的消息
        3. @OnError             	发生错误调用
        4. @OnClose              	接关闭调用的方法
    一个属性
    	Session:与某个客户端的连接会话，需要通过它来给客户端发送数据
    	
注意：WebSocket是一个单例类@AutoWired不能自动注入，需要额外配置
```

配置类

```JAVA
@Component
public class WebSocketConfig {
    @Bean
	public ServerEndpointExporter serverEndpointExporter() {       return new ServerEndpointExporter();}}
```

逻辑类

浏览器访问

````

````

单例注入

```java
@Autowired
private static ClassA classa;
@Autowired
public void getService(ApplicationContext context){
    classa= context.getBean(ClassA.class);
}
```

### Tool-Error

异常有三种处理方式，参见网页：<https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html>

概览

```
作用:处理捕获到的异常信息
使用:
	1.主要通过@ControllerAdvice/@RestControllerAdvice实现
	2.当捕获到异常，会被提交到这个类，并返回数据
	3.在方法上标注@ExceptionHandler确定错误类型
```

代码

```JAVA
@ControllerAdvice
public class ErrorConfig {
    //标注错误类型
@ExceptionHandler(value = AuthorizationException.class)
 public String methods(){
     //捕获到异常，返回到页面unAuthor.html
    return "/guest/unAuthor.html";
}}
```



### Tool-Schedule

概念

```java
作用:定时执行方法，处理对应逻辑
注意:
	1.使用一定要添加对应注解
    2.学会指定时间格式
```

代码

```java
@Configuration
@EnableScheduling//最好加在main入口处，让全局可用
public class ErrorConfig {
    @Scheduled(fixedRate = 2000)
    @Scheduled(cron="0/5 * * * * ?")
    public void methods() {
        System.out.println("每2000执行一次");
    }
}
```

时间格式

```
@Scheduled的CRON参数详解
cron="秒 分 时 周日 月份 星期 年份"
不会就用*代替
To be Continue
```



### Tool-监听器

```
作用:对Session的创建，销毁等事件进行控制
使用:
.
```

### Tool-随机数

概览:

```
三种创建方式：
第一种：new Random()
第二种：Math.random()
第三种：currentTimeMillis()
```

返回区间数

```java
#返回一个介于min和max之间的正整数
public static long getRandom( int max, int min ) {
        return Math.abs( ( long ) ( Math.random( ) * ( max - min ) + min ) );
}
```

第二种方式

```JAVA
#返回一个不会重复的数
    
public static int getRandomNumber(){
        Random random = new Random( System.currentTimeMillis( ));
        return  Math.abs(random.nextInt());
}
```

Tool-获取远程IP

```JAVA
/**
 * 获得用户远程地址
 */
public static String getRemoteAddr(HttpServletRequest request){

        
        
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                        //根据网卡取本机配置的IP
                        InetAddress inet=null;
                        try {
                                inet = InetAddress.getLocalHost();
                        } catch ( UnknownHostException e) {
                                e.printStackTrace();
                        }
                        ipAddress= inet.getHostAddress();
                }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
                if(ipAddress.indexOf(",")>0){
                        ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
                }
        }
        return ipAddress;
}
```

