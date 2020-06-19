### Aop-使用

<https://www.jianshu.com/p/5fc2a78b7766>详解

<https://blog.csdn.net/weixin_33755649/article/details/91389367>：shiro使AOP失效解决方案

##### 使用场景

```
Authentication 权限
Caching 缓存
Context passing 内容传递
Error handling 错误处理
Lazy loading　懒加载
Debugging　　调试
logging, tracing, profiling and monitoring　记录跟踪　优化　校准
Performance optimization　性能优化
Persistence　　持久化
Resource pooling　资源池
Synchronization　同步
Transactions 事务
```

### 入门案例

```
必须的类
Target	：	需要被增强的类		@Component
Aspect	:	 增强类		   @Aspect
Config	:	 配置类			@Configuration，@EnableAspectJAutoProxy

```

##### 类:Target

```java
@Component
public class Math {
 public int add(int a , int b){return a+b;}
}
```

##### 类:AspectJ

```JAVA
@Component
@Aspect
public class AspectJ {
    此处需要修改
    @Pointcut("execution(* test.Math*.*(..))")
    public void pointCut() {}

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after aspect executed");
    }
}
```



##### 类:Config

```JAVA
//将aspectj配置类和目标类同时注入SPRING
@EnableAspectJAutoProxy
@Configuration
public class Config {
    @Bean
    public AspectJ getSimpleAspect(){
        return new AspectJ();
    }
    @Bean
    public Math getPersonService(){
        return new Math();
    }
}
```

##### 类:Test

```JAVA
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
context.register(Config.class);
context.refresh();

Math bean = context.getBean(Math.class);
int add = bean.add(1 , 2);

```

使用注意

```
1.必须添加的注解@EnableAspectJAutoProxy,@Configuration,@Aspect
2.不能手动创建对象,需要从IOC容器中获取目标对象，再调用
```

