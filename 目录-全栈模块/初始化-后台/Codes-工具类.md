## Codes-Tools

### Tool-Springboot

##### Tool-异常处理

<https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html>

```JAVA
@ControllerAdvice
public class ErrorConfig {
        //标注错误类型
    @ExceptionHandler(value = AuthorizationException.class)
     public String methods(){
         //捕获到异常，返回到页面unAuthor.html
        return "/guest/unAuthor.html";
    }
    //黑名单
    @ExceptionHandler(value=DisabledAccountException.class)
    public String DisabledAccountException() {
        //捕获到异常，返回到页面unAuthor.html
        return "限定登录";
    }
}
```



##### Tool-Session监听

继承

```java
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

```JAVA
public class MyListener implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
    }
    @Override
    public void valueUnbound(HttpSessionBindingEvent event){
    }
}
```

##### Tool-获取远程IP

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



##### Tool-WebSocket

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

##### Tool-Schedule

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

##### Tool-发送EMAIL

配置

```
spring.mail.default-encoding=UTF-8
spring.mail.host=smtp.qq.com
#发送者的邮箱密码
spring.mail.password=XXXXXX
#端口
spring.mail.port=25
#协议
spring.mail.protocol=smtp
#发送者的邮箱账号
spring.mail.username=XXXXXX@qq.com
```

工具类

```JAVA
@Autowired
JavaMailSender jsm;
@Autowired
SimpleMailMessage message;

@Value("${spring.mail.username}")
private String username;


/*
    *   功能:给目标发送邮件
    *   参数:
    *       targetEmail:目标邮箱
    *       content：内容
    *       title:标题
    *   返回值:
    *       是否成功
    * */
@RequestMapping("/sendEmail")
public boolean sendEmail(String targetEmail,String content,String title) {
    //建立邮箱消息
    //发送者
    try {
        message.setFrom(username);
        //接收者
        message.setTo(targetEmail);
        //发送标题
        message.setSubject(title);
        //发送内容
        message.setText(content);
        jsm.send(message);
    }
    catch (MailException e) {
        return false;
    }
    return true;
}
```

### Tool-生成数

##### Tool-随机数

概览:

```
三种创建方式：
第一种：new Random()
第二种：Math.random()
第三种：currentTimeMillis()
```

返回区间数

```java
/*
    * 功能:生成(min,max)之间的随机数
* */
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





##### Tool-生成ID

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



##### Tool-生成JWT

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

### Tool-对象存储

```JAVA
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





### Tool-Jedis

```JAVA
@Component
public class RedisUtil {
    //Redis服务器IP
    private String ADDR = "127.0.0.1";

    //Redis的端口号
    private int PORT = 6379;

    //访问密码
    private String AUTH = null;

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值8。
    private int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private int MAX_WAIT = 10000;

    private int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private boolean TEST_ON_BORROW = true;

    private JedisPool pool = null;

    /**
     * 初始化Redis连接池
     */ {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            Integer port = new Integer(PORT);
            pool = new JedisPool(config , ADDR , port , TIMEOUT , AUTH);

            try {
                pool.getResource();
            }
            catch (JedisConnectionException e) {
                System.out.println("redis没有设置密码");
                pool = new JedisPool(config , ADDR , port , TIMEOUT);
                //System.out.println("再次获取资源："+pool.getResource());
                e.printStackTrace();
            }
            //需要认证
            // pool = new JedisPool(config, ADDR, PORT, TIMEOUT,AUTH);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 返还到连接池
     *
     * @param pool
     * @param jedis
     */
    public void returnResource(JedisPool pool , Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

    /**
     * <p>通过key获取储存在redis中的value</p>
     * <p>并释放连接</p>
     *
     * @param key
     * @return 成功返回value 失败返回null
     */
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return value;
    }

    /**
     * <p>向redis存入key和value,并释放连接资源</p>
     * <p>如果key已经存在 则覆盖</p>
     *
     * @param key
     * @param value
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key , String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return "0";
        }
        finally {
            returnResource(pool , jedis);
        }
    }


    /**
     * <p>删除指定的key,也可以传入一个包含key的数组</p>
     *
     * @param keys 一个key  也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.del(keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return 0L;
        }
        finally {
            returnResource(pool , jedis);
        }
    }

    /**
     * <p>通过key向指定的value值追加值</p>
     *
     * @param key
     * @param str
     * @return 成功返回 添加后value的长度 失败 返回 添加的 value 的长度  异常返回0L
     */
    public Long append(String key , String str) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.append(key , str);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return 0L;
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>判断key是否存在</p>
     *
     * @param key
     * @return true OR false
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.exists(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        }
        finally {
            returnResource(pool , jedis);
        }
    }

    /**
     * <p>设置key value,如果key已经存在则返回0,nx==> not exist</p>
     *
     * @param key
     * @param value
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    public Long setnx(String key , String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.setnx(key , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return 0L;
        }
        finally {
            returnResource(pool , jedis);
        }
    }

    /**
     * <p>设置key value并制定这个键值的有效期</p>
     *
     * @param key
     * @param value
     * @param seconds 单位:秒
     * @return 成功返回OK 失败和异常返回null
     */
    public String setex(String key , String value , int seconds) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.setex(key , seconds , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }


    /**
     * <p>通过key 和offset 从指定的位置开始将原先value替换</p>
     * <p>下标从0开始,offset表示从offset下标开始替换</p>
     * <p>如果替换的字符串长度过小则会这样</p>
     * <p>example:</p>
     * <p>value : bigsea@zto.cn</p>
     * <p>str : abc </p>
     * <P>从下标7开始替换  则结果为</p>
     * <p>RES : bigsea.abc.cn</p>
     *
     * @param key
     * @param str
     * @param offset 下标位置
     * @return 返回替换后  value 的长度
     */
    public Long setrange(String key , String str , int offset) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.setrange(key , offset , str);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return 0L;
        }
        finally {
            returnResource(pool , jedis);
        }
    }


    /**
     * <p>通过批量的key获取批量的value</p>
     *
     * @param keys string数组 也可以是一个key
     * @return 成功返回value的集合 , 失败返回null的集合 ,异常返回空
     */
    public List<String> mget(String... keys) {
        Jedis jedis = null;
        List<String> values = null;
        try {
            jedis = pool.getResource();
            values = jedis.mget(keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return values;
    }

    /**
     * <p>批量的设置key:value,可以一个</p>
     * <p>example:</p>
     * <p>  obj.mset(new String[]{"key2","value1","key2","value2"})</p>
     *
     * @param keysvalues
     * @return 成功返回OK 失败 异常 返回 null
     */
    public String mset(String... keysvalues) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.mset(keysvalues);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚</p>
     * <p>example:</p>
     * <p>  obj.msetnx(new String[]{"key2","value1","key2","value2"})</p>
     *
     * @param keysvalues
     * @return 成功返回1 失败返回0
     */
    public Long msetnx(String... keysvalues) {
        Jedis jedis = null;
        Long res = 0L;
        try {
            jedis = pool.getResource();
            res = jedis.msetnx(keysvalues);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>设置key的值,并返回一个旧值</p>
     *
     * @param key
     * @param value
     * @return 旧值 如果key不存在 则返回null
     */
    public String getset(String key , String value) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.getSet(key , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过下标 和key 获取指定下标位置的 value</p>
     *
     * @param key
     * @param startOffset 开始位置 从0 开始 负数表示从右边开始截取
     * @param endOffset
     * @return 如果没有返回null
     */
    public String getrange(String key , int startOffset , int endOffset) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.getrange(key , startOffset , endOffset);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1</p>
     *
     * @param key
     * @return 加值后的结果
     */
    public Long incr(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.incr(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key给指定的value加值,如果key不存在,则这是value为该值</p>
     *
     * @param key
     * @param integer
     * @return
     */
    public Long incrBy(String key , Long integer) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.incrBy(key , integer);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>对key的值做减减操作,如果key不存在,则设置key为-1</p>
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.decr(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>减去指定的值</p>
     *
     * @param key
     * @param integer
     * @return
     */
    public Long decrBy(String key , Long integer) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.decrBy(key , integer);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取value值的长度</p>
     *
     * @param key
     * @return 失败返回null
     */
    public Long serlen(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.strlen(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key给field设置指定的值,如果key不存在,则先创建</p>
     *
     * @param key
     * @param field 字段
     * @param value
     * @return 如果存在返回0 异常返回null
     */
    public Long hset(String key , String field , String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hset(key , field , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0</p>
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hsetnx(String key , String field , String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hsetnx(key , field , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key同时设置 hash的多个field</p>
     *
     * @param key
     * @param hash
     * @return 返回OK 异常返回null
     */
    public String hmset(String key , Map<String, String> hash) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hmset(key , hash);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key 和 field 获取指定的 value</p>
     *
     * @param key
     * @param field
     * @return 没有返回null
     */
    public String hget(String key , String field) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hget(key , field);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key 和 fields 获取指定的value 如果没有对应的value则返回null</p>
     *
     * @param key
     * @param fields 可以使 一个String 也可以是 String数组
     * @return
     */
    public List<String> hmget(String key , String... fields) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hmget(key , fields);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key给指定的field的value加上给定的值</p>
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrby(String key , String field , Long value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hincrBy(key , field , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key和field判断是否有指定的value存在</p>
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(String key , String field) {
        Jedis jedis = null;
        Boolean res = false;
        try {
            jedis = pool.getResource();
            res = jedis.hexists(key , field);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回field的数量</p>
     *
     * @param key
     * @return
     */
    public Long hlen(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hlen(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;

    }

    /**
     * <p>通过key 删除指定的 field </p>
     *
     * @param key
     * @param fields 可以是 一个 field 也可以是 一个数组
     * @return
     */
    public Long hdel(String key , String... fields) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hdel(key , fields);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回所有的field</p>
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hkeys(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回所有和key有关的value</p>
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hvals(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取所有的field和value</p>
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetall(String key) {
        Jedis jedis = null;
        Map<String, String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.hgetAll(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key向list头部添加字符串</p>
     *
     * @param key
     * @param strs 可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public Long lpush(String key , String... strs) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lpush(key , strs);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key向list尾部添加字符串</p>
     *
     * @param key
     * @param strs 可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public Long rpush(String key , String... strs) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.rpush(key , strs);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key在list指定的位置之前或者之后 添加字符串元素</p>
     *
     * @param key
     * @param where LIST_POSITION枚举类型
     * @param pivot list里面的value
     * @param value 添加的value
     * @return
     */
    public Long linsert(String key , BinaryClient.LIST_POSITION where ,
                        String pivot , String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.linsert(key , where , pivot , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key设置list指定下标位置的value</p>
     * <p>如果下标超过list里面value的个数则报错</p>
     *
     * @param key
     * @param index 从0开始
     * @param value
     * @return 成功返回OK
     */
    public String lset(String key , Long index , String value) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lset(key , index , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key从对应的list中删除指定的count个 和 value相同的元素</p>
     *
     * @param key
     * @param count 当count为0时删除全部
     * @param value
     * @return 返回被删除的个数
     */
    public Long lrem(String key , long count , String value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lrem(key , count , value);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key保留list中从strat下标开始到end下标结束的value值</p>
     *
     * @param key
     * @param start
     * @param end
     * @return 成功返回OK
     */
    public String ltrim(String key , long start , long end) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.ltrim(key , start , end);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key从list的头部删除一个value,并返回该value</p>
     *
     * @param key
     * @return
     */
    public String lpop(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lpop(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key从list尾部删除一个value,并返回该元素</p>
     *
     * @param key
     * @return
     */
    public String rpop(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.rpop(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key从一个list的尾部删除一个value并添加到另一个list的头部,并返回该value</p>
     * <p>如果第一个list为空或者不存在则返回null</p>
     *
     * @param srckey
     * @param dstkey
     * @return
     */
    public String rpoplpush(String srckey , String dstkey) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.rpoplpush(srckey , dstkey);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取list中指定下标位置的value</p>
     *
     * @param key
     * @param index
     * @return 如果没有返回null
     */
    public String lindex(String key , long index) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lindex(key , index);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回list的长度</p>
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.llen(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取list指定下标位置的value</p>
     * <p>如果start 为 0 end 为 -1 则返回全部的list中的value</p>
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key , long start , long end) {
        Jedis jedis = null;
        List<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.lrange(key , start , end);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key向指定的set中添加value</p>
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public Long sadd(String key , String... members) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sadd(key , members);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key删除set中对应的value值</p>
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 删除的个数
     */
    public Long srem(String key , String... members) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.srem(key , members);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key随机删除一个set中的value并返回该值</p>
     *
     * @param key
     * @return
     */
    public String spop(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.spop(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取set中的差集</p>
     * <p>以第一个set为标准</p>
     *
     * @param keys 可以使一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public Set<String> sdiff(String... keys) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sdiff(keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取set中的差集并存入到另一个key中</p>
     * <p>以第一个set为标准</p>
     *
     * @param dstkey 差集存入的key
     * @param keys   可以使一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public Long sdiffstore(String dstkey , String... keys) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sdiffstore(dstkey , keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取指定set中的交集</p>
     *
     * @param keys 可以使一个string 也可以是一个string数组
     * @return
     */
    public Set<String> sinter(String... keys) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sinter(keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取指定set中的交集 并将结果存入新的set中</p>
     *
     * @param dstkey
     * @param keys   可以使一个string 也可以是一个string数组
     * @return
     */
    public Long sinterstore(String dstkey , String... keys) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sinterstore(dstkey , keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回所有set的并集</p>
     *
     * @param keys 可以使一个string 也可以是一个string数组
     * @return
     */
    public Set<String> sunion(String... keys) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sunion(keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回所有set的并集,并存入到新的set中</p>
     *
     * @param dstkey
     * @param keys   可以使一个string 也可以是一个string数组
     * @return
     */
    public Long sunionstore(String dstkey , String... keys) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sunionstore(dstkey , keys);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key将set中的value移除并添加到第二个set中</p>
     *
     * @param srckey 需要移除的
     * @param dstkey 添加的
     * @param member set中的value
     * @return
     */
    public Long smove(String srckey , String dstkey , String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.smove(srckey , dstkey , member);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取set中value的个数</p>
     *
     * @param key
     * @return
     */
    public Long scard(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.scard(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key判断value是否是set中的元素</p>
     *
     * @param key
     * @param member
     * @return
     */
    public Boolean sismember(String key , String member) {
        Jedis jedis = null;
        Boolean res = null;
        try {
            jedis = pool.getResource();
            res = jedis.sismember(key , member);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取set中随机的value,不删除元素</p>
     *
     * @param key
     * @return
     */
    public String srandmember(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.srandmember(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取set中所有的value</p>
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.smembers(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key向zset中添加value,score,其中score就是用来排序的</p>
     * <p>如果该value已经存在则根据score更新元素</p>
     *
     * @param key
     * @param scoreMembers
     * @return
     */
    public Long zadd(String key , Map<String, Double> scoreMembers) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zadd(key , scoreMembers);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key向zset中添加value,score,其中score就是用来排序的</p>
     * <p>如果该value已经存在则根据score更新元素</p>
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Long zadd(String key , double score , String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zadd(key , score , member);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key删除在zset中指定的value</p>
     *
     * @param key
     * @param members 可以使一个string 也可以是一个string数组
     * @return
     */
    public Long zrem(String key , String... members) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrem(key , members);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key增加该zset中value的score的值</p>
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Double zincrby(String key , double score , String member) {
        Jedis jedis = null;
        Double res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zincrby(key , score , member);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回zset中value的排名</p>
     * <p>下标从小到大排序</p>
     *
     * @param key
     * @param member
     * @return
     */
    public Long zrank(String key , String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrank(key , member);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回zset中value的排名</p>
     * <p>下标从大到小排序</p>
     *
     * @param key
     * @param member
     * @return
     */
    public Long zrevrank(String key , String member) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrevrank(key , member);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key将获取score从start到end中zset的value</p>
     * <p>socre从大到小排序</p>
     * <p>当start为0 end为-1时返回全部</p>
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key , long start , long end) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrevrange(key , start , end);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回指定score内zset中的value</p>
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public Set<String> zrangebyscore(String key , String max , String min) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrevrangeByScore(key , max , min);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回指定score内zset中的value</p>
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public Set<String> zrangeByScore(String key , double max , double min) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zrevrangeByScore(key , max , min);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>返回指定区间内zset中value的数量</p>
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zcount(String key , String min , String max) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zcount(key , min , max);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key返回zset中的value个数</p>
     *
     * @param key
     * @return
     */
    public Long zcard(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zcard(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key获取zset中value的score值</p>
     *
     * @param key
     * @param member
     * @return
     */
    public Double zscore(String key , String member) {
        Jedis jedis = null;
        Double res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zscore(key , member);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key删除给定区间内的元素</p>
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremrangeByRank(String key , long start , long end) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zremrangeByRank(key , start , end);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key删除指定score内的元素</p>
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremrangeByScore(String key , double start , double end) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = pool.getResource();
            res = jedis.zremrangeByScore(key , start , end);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>返回满足pattern表达式的所有key</p>
     * <p>keys(*)</p>
     * <p>返回所有的key</p>
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            res = jedis.keys(pattern);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

    /**
     * <p>通过key判断值得类型</p>
     *
     * @param key
     * @return
     */
    public String type(String key) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.type(key);
        }
        catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }
        finally {
            returnResource(pool , jedis);
        }
        return res;
    }

}

```

