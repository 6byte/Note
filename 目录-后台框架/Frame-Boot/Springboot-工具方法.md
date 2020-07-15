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

##### Tool-发送Email

https://www.cnblogs.com/heqiyoujing/p/9477490.html

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

##### Tool-文件传输

文件上传

```JAVA
@RequestMapping("/upload")
@ResponseBody
public String upload(MultipartFile MultipartFile) {
        if(StringUtils.isEmpty(MultipartFile)) {
                return "上传失败，请选择文件";
        }
        //指定文件另存为的名字
        String fileName = file.getOriginalFilename();
        //指定文件路径
        String filePath = "F:\\Temp";
        File dest = new File(filePath + fileName);
        try {
                MultipartFile.transferTo(dest);
                return filePath + fileName;
        }
        catch(IOException e) {
        }
        return "失败";
}
```

前端

```js
HTML
//别想了,直接用form
<form action="" method="post" enctype="multipart/form-data">
    //与后端MultipartFile的名字对应
    <input type="file" name="multipartFile" class="custom-file-input" id="user-file">
    <label class="custom-file-label" for="user-file">选择文件</label>

    <div class="input-group-append">
        <button class="btn btn-outline-secondary " type="button" id="upload">上传</button>
    </div>
</form>

JS
$.ajax({
    method: 'POST',
    url: '/user/userFile',
    cache: false,
    data: new FormData($('#uploadForm')[0]),
    processData: false,
    contentType: false,
    success: function(data) {},
    error: function() {}
})
```





文件下载

```java
@RequestMapping("/guest/download")
public String downloadFile(HttpServletRequest request,
                           HttpServletResponse response) throws UnsupportedEncodingException {
    
    // 获取指定目录下的第一个文件
    File scFileDir = new File("F:\\temp\\download");
    File TrxFiles[] = scFileDir.listFiles();
    String fileName = TrxFiles[0].getName(); //下载的文件名
    // 如果文件名不为空，则进行下载
    if (fileName != null) {
        //设置文件路径
        String realPath = "F:\\temp\\download\\";
        File file = new File(realPath, fileName);
        
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the song successfully!");
            }
            catch (Exception e) {
                System.out.println("Download the song failed!");
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    return null;
}
```

##### Tool-动态更新属性

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

