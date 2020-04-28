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

填写密钥
    
    
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

### Tool-LongID

```JAVA
public class ID
{

    private static long tmpID = 0;

    private static boolean tmpIDlocked = false;

    public static long getId()
    {
        long ltime = 0;
        while (true)
        {
            if(tmpIDlocked == false)
            {
                tmpIDlocked = true;
                //当前：（年、月、日、时、分、秒、毫秒）*10000
                ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date()).toString()) * 10000;
                if(tmpID < ltime)
                {
                    tmpID = ltime;
                }
                else
                {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime;
            }
        }
    }
}
```

### Tool-Session监听

继承

```JAVA
public class SessionListener implements HttpSessionListener {
    public static int online = 0;
//    Session销毁

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online++;
    }

    //Session创建
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
        online--;
    }

}

```

配置

```JAVA
@Configuration
public class MywebConfig implements WebMvcConfigurer {
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new SessionListener());
        return srb;
    }
}

```

其他

```
public class MyListener implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

    }
}

```

### Tool-Generater

```JAVA
 public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("6bye");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("00000000");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
     	==>修改
  		pc.setModuleName("Catalina");
     	==>修改
  		pc.setParent("com.github.alpha.Tools");
        //生成的类在com.github.alpha.Tools.Catalina下
        mpg.setPackageInfo(pc);

         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude("users");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }

```

### Tool-分页

配置

```JAVA
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}


```

使用

```JAVA
@RequestMapping("/index")
public List index(String uname) {
    //         和limit用法一样
    Page page = new Page(1 , 4);
    //        需要返回一个记录
    List records = usersMapper.selectPage(page , null).getRecords();
    return records;
}
```

### Tool-Wrapper

```

```

