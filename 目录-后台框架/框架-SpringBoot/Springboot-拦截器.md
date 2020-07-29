### Springboot-拦截器

#### 概览

```
1.继承HandlerInterceptor可实现拦截
2.有三个方法需要实现
	preHandle：在调用Controller之前
	postHandle:调用Controller之后
	afterCompletion:DispatcherServlet进行视图的渲染之后
```



#### 拦截器

```JAVA
public class WebInterceptor implements HandlerInterceptor {

    /*
     *  功能:任何流量都会通过该方法
     	返回值:
     		true：放行
     		false：不允许通过
     * */
    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) {
        return true;
    }
}

```

#### 注册

```JAVA
@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/**");
    }
}
```

