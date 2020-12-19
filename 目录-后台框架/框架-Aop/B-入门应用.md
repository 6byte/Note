### Aop-使用

<https://www.jianshu.com/p/5fc2a78b7766>详解

<https://blog.csdn.net/weixin_33755649/article/details/91389367>：shiro使AOP失效解决方案

### 入门案例

```JS
注意:
	必须具备几个条件
        1.目标类,@Component
        2.增强类,@Aspect注解的类
        3.配置类,开启@EnableAspectJAutoProxy，@Configuration
	所有类都必须放在Spring容器中
	绝对不能手动创建对象
```

##### 目标类

```java
@Component
public class Math {
 public int add(int a , int b){return a+b;}
}
```

##### 增强类

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



##### 配置类

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

##### 测试类

```JAVA
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
context.register(Config.class);
context.refresh();

Math bean = context.getBean(Math.class);
int add = bean.add(1 , 2);

```

