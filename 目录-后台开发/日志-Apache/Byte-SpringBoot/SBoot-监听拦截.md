## Spring-boot 监听拦截

### Web-拦截器

概览

```
作用：
	1.对浏览器的访问进行控制
	2.对于静态资源进行控制
	3.对访问权限进行控制
注意
1.拦截器仅对Controller层起作用
2.配置拦截器后，要在WebMvcConfigurer类注册
3.定义拦截器=配置类+注册类
```



##### 拦截器-配置类

```java

@Component
public class MvcConfig implements HandlerInterceptor {
    @Override
    public boolean 
        preHandle(	HttpServletRequest request,           							HttpServletResponse response, 
        			Object handler) 
        			throws Exception {        
    				//在这里添加拦截规则        
    				response.sendRedirect("index.html");        				//false:拦截所有请求，true放行请求
    				return false;
    }
//To Be Continue

}
preHandle()、postHandle()与afterCompletion()
方法比较:
- preHandle	 在Controller方法之前调用
- postHandle   在Controller方法之后调用
- afterCompletion    在DispatcherServlet进行视图渲染之后执行，用于清理资源,处理异常


```

##### 拦截器-注册类

```
@Component
public class RegisteInterceptor implements WebMvcConfigurer 
{
	@Autowired
	MVcConfig config;
	@Override
	public void 
	addInterceptors(InterceptorRegistry registry) 
{
				registry.addInterceptor(config)
				.addPathPatterns("/**")
				.excludePathPatterns("/*.html","/*.css");
}}
```

### Web-监听器

```

```

