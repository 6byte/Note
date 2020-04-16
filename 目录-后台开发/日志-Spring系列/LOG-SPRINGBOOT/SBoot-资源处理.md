## SpringBoot-资源

#### 获取默认静态资源路径

```java
//获取resources的路径
 String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

//获取static路径
 String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"\\static";
```

#### 静态资源放行

```JAVA
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

#### 获取浏览器访问参数

**原网页：<https://blog.csdn.net/sinat_32829963/article/details/79690058>**

获取所有参数

```
Enumeration enu=request.getParameterNames();  
while(enu.hasMoreElements()){  
	String paraName=(String)enu.nextElement();  
	System.out.println(paraName+": "+request.getParameter(paraName));  
}
```

获取全路径

```
getRequestURL()
String requestUrl = request.getScheme() //当前链接使用的协议
    request.getServerName()//服务器地址
    request.getServerPort() //端口号
    request.getContextPath() //应用名称，如果应用名称为
  	request.getServletPath() //请求的相对url
    request.getQueryString() //请求参数
    request.getRequestURI()	//获得/url/url
```

