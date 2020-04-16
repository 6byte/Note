## Codes-Tools

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

### Tool-获取远程IP

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

### Tool-文件获取

概览

```
对应关系
文件列表	=>	分类列表
文件名		 =>	 内容

需要获取
目录下所有文件夹
该文件下所有内容
```

### Tool-对象存储

```java
public class Tools {

    String endpoint = "https://6byte.oss-cn-chengdu.aliyuncs.com/";
    String accessKeyId = "LTAI4FpXGmp1TvygZhqdYx8g";
    String accessKeySecret = "B4IXsT2SescVGMeroCwbzBIvnQwm7i";

    OSS ossClient = getOssClient();

    public OSS getOssClient() {
        return ossClient;
    }

    public Tools(String endpoint , String accessKeyId , String accessKeySecret) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }
//可以直接调用getOssClient()获取对象
}
```

### Tool-生成UUID

```JAVA
public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-" , "");
    }
```

### Tool-生成JWT

```java
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
    //过期时间
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    //私钥
    private static final String TOKEN_SECRET = "privateKey";

    /**
     * 生成签名，15分钟过期
     *
     * @param **username**
     * @param **password**
     * @return
     */
    public static String sign(Long userId) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type" , "Jwt");
            header.put("alg" , "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId" , userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param **token**
     * @return
     */
    public static Long verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Long userId = jwt.getClaim("userId").asLong();
            return userId;
        }
        catch (Exception e) {
            return 0L;
        }
    }

    public static void main(String[] args) {
        JwtUtil util = new JwtUtil();
        System.out.println(util.sign(456415l));
    }
}
```

### Tool-手动注入

配置

```JS
@Component
public class SpringConfig implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        SpringConfig.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }

    public static <T> T getBean(String name , ApplicationContext applicationContext) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        Map<String, T> beanMaps = context.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz , ApplicationContext applicationContext) {
        Map<String, T> beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }
}
```

使用

```java

public class A implements Runnable {
    private AMapper aMapper;
    @Override
    public void run() {
//需要在线程中执行
        this.AMapper= SpringConfig.getBean(aMapper.class);
    }
}
```





