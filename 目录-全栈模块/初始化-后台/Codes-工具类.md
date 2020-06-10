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

### Tool-生成ID

生成UUID

```JAVA


public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-" , "");
    }
```

生成数字ID

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



### Tool-生成JWT

```java

@Data
public class Jwt {


    /*
     *   功能:设置认证token
     *   参数:
     *       map.id:         登录用户ID
     *       map.subject:    登录用户名
     *       map.author      用户权限信息
     * */
    public String createJwt(HashMap map,Long ttl,String key) {
        try {
//        设置失效时间
            long now = System.currentTimeMillis();
            Long exp = now + ttl;

            JwtBuilder builder = Jwts.builder()
                    .setId(map.get("id").toString())            //设置ID
                    .setSubject(map.get("subject").toString())  //设置用户名
                    .signWith(SignatureAlgorithm.HS256 , key)   // //设置签名
                    .claim("author" , map.get("author"))
                    .setExpiration(new Date(exp));              //设置失效时间
            String token = builder.compact();
            return token;                                       //返回token
        }
        catch (java.lang.Exception e) {
            System.out.println(e);
        }
        return null;
    }


    /*
     * 功能:解析token
     * 参数:客户端传来的token
     * 返回:claims或null	
     * */


    public Object parseJwt(String token,String key) {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims;

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

```java
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

### Tool-执行任意SQL

```java
@Select("${sql}")
    public List<Map<String , Object>> exec(@Param("sql") String sql);
```

### Tools-获取所有请求信息

```JAVA
Enumeration em = request.getParameterNames();
 while (em.hasMoreElements()) {
    String name = (String) em.nextElement();
    String value = req.getParameter(name);
}
```

### Tools-时间格式

```JAVA
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 String currentTime = sdf.format(new Date());
//结果是:2020-05-29
```

### Tools-手动注入

注入

```JAVA
 AbstractApplicationContext ac = (AbstractApplicationContext) SpringContextUtil.getApplicationContext();
    MIndex mIndex = ac.getBean(Mapper接口.class);
```

定义工具类

```JAVA
/*
	功能:
		在多线程中注入mapper
		在websocket中注入mapper
*/

@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
    
    功能:通过name,以及Clazz返回指定的Bean
    参数:
    	name:Bean的名字
    	clazz:Bean的类
     */
    public static <T> T getBean(String name , Class<T> clazz) {
        return getApplicationContext().getBean(name , clazz);
    }
}
```

### Tool-分页实现

配置

```JAVA
@Configuration
public class BeanConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum","true");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}


```

DAO层

```JAVA
//    按照常规写Mapper
@Select("select * from discuss_post ")
List<DiscussPost> selectDiscussPost();

```

Center

```JAVA
@RequestMapping("/postlist")
public PageInfo<DiscussPost> index(Integer page , Integer size) {
    //配置PagerHelper的分页
    PageHelper.startPage(page,size);
    //获取List结果
    List<DiscussPost> discussPosts = discussMapper.selectDiscussPost();
    //注意此处:会返回一个List列表，除了结果以外附带下面信息
    return new PageInfo<>(discussPosts);
}
```

结果

```
{
	"total": 1,
	"list": [{
		"id": 1,
		"userId": 0,
		"title": "标题啊",
		"content": "内容啊",
		"type": 0,
		"createTime": null,
		"status": 0,
		"score": 10.0,
		"commentCount": 0
	}],
	"pageNum": 0,
	"pageSize": 2,
	"size": 1,
	"startRow": 1,
	"endRow": 1,
	"pages": 1,
	"prePage": 0,
	"nextPage": 1,
	"isFirstPage": false,
	"isLastPage": false,
	"hasPreviousPage": false,
	"hasNextPage": true,
	"navigatePages": 8,
	"navigatepageNums": [1],
	"navigateFirstPage": 1,
	"navigateLastPage": 1
}


```

