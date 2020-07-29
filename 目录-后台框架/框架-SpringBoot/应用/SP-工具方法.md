### Tool-Springboot

##### Tool-资源处理

https://blog.csdn.net/sinat_32829963/article/details/79690058

获取默认静态资源路径

```java
ResourceUtils.getURL("classpath:").getPath()+"static/"
获取static目录
```



配置修改

```js
推荐使用,使用编码容易炸
#配置内部访问地址和外部图片访问地址 /img/**
spring.mvc.static-path-pattern=/**
spring.resources.static-locations=file:G:/market,classpath:/static/
```

编码修改

```java
@Configuration  //配置类注解
public class WebConfig extends WebMvcConfigurationSupport {

@Override
public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        /**
         把F:\temp作为静态资源路径
         1.配置本地路径                 addResourceLocations
                addResourceLocations("file:" +"F:\temp" )
         2.配置静态资源访问路径         addResourceHandler
                addResourceHandler("/static/**")
         3.访问F:\temp中的img.jpg
                localhost:80/static/img.jpg
         ！！！
         addResourceLocations的参数要么要加上“classpath”表示相对路径，要么用file+绝对路径
         *
         * */
        registry.addResourceHandler( "/static/**" ).addResourceLocations( "file:" + "G:\\网页资源\\高清动漫壁纸\\高清动漫壁纸\\" );
        super.addResourceHandlers( registry );
}
}

```



##### Tool-异常处理

<https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html>

```JAVA
@ControllerAdvice
public class ErrorConfig {
    @ExceptionHandler(value = {Exception.class})
    public Object handleOtherExceptions(final Exception ex) {
        if(ex instanceof NullPointException)
        return null;
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





##### Tools-获取所有请求信息

```JAVA
Enumeration em = request.getParameterNames();
 while (em.hasMoreElements()) {
    String name = (String) em.nextElement();
    String value = req.getParameter(name);
}
```

### Tool-Spring

##### Tools-手动注入

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

##### Tools-单例注入

```JAVA
@Autowired
private static ClassA classa;
@Autowired
public void getService(ApplicationContext context){
    classa= context.getBean(ClassA.class);
}
```

