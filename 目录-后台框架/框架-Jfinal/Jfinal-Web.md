### Jfinal查询手册

#### 1.跨域请求

新建类:CrossOrgin

```java
public class CrossOrigin implements Interceptor {
	public void intercept(Invocation inv) {
inv.getController().getResponse().addHeader("Access-Control-Allow-Origin", "*");
		inv.invoke();
	}
}

```

注册到拦截器

```JAVA
public class TestConfig extends JFinalConfig {
	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new CrossOrigin());
	}
    //...省略其他方法	
}
```

