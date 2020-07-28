### AOP-常用注解

#### 概览

```
Pointcut:切入点
execution:匹配式
```



#### 注解

##### Pointcut

```java
作用:统一方法切入点
注意:绝对不能写错，否则启动不了
```

##### JoinPoint

```
作用:获取目标方法的信息
```



```java
打印参数
 Object[] args = joinPoint.getArgs();
for (int i = 0; i < args.length; i++) {
    System.out.println("第" + (i+1) + "个参数为:" + args[i]);
}
```

|          方法名           |             功能             |
| :-----------------------: | :--------------------------: |
| Signature getSignature(); | 获取目标方法名,所属类的Class |
|    Object[] getArgs();    |         获取参数对象         |
|    Object getTarget();    |       获取被代理的对象       |
|     Object getThis();     |         获取代理对象         |
|     String getKind()      |        获取连接点类型        |

##### ProceedingJoinPoint

```
作用:对目标方法进行完全控制
```



#### 表达式

##### execution

基本使用

```
execution([方法的访问控制修饰符 | 方法的返回值] 包名.类名/接口名.方法名(参数))
```



##### 通配符

概览

```java
可以匹配:返回值，包名，类名，方法名，参数
execution(修饰符 返回值 包名.类名.方法名(参数))

..用法
execution(返回值 包名.类名.方法名(..))//表示多个参数
execution(返回值 com..类.方法名(参数))//表示com下的任何包

*用法
execution(修饰符 返回值 包名.*.方法名(参数))
```

匹配方法

```JAVA
匹配具体方法
execution(* *.*.Clazz.Method())//匹配单个包,每个星号代表一个包名
--能匹配  public com.java.Clazz Method()
    	 public com.js.Clazz Method()
    
匹配任意包下类中的所有方法
execution(* *..Clazz.Method())//匹配任意包下类中的所有method
---能匹配  public com.java.oracle.Clazz Method()
    	  public com.java.mybatis.Clazz Method()
    
匹配包含某个字符的方法
execution(* *.*.Clazz.*Method())
--能匹配   public com.java.Clazz javaMethod()
    	  public com.java.Clazz cMethod()
    	  public com.java.Clazz jsMethod()
```



匹配参数

```JAVA
匹配有多个参数的方法
execution(* *..Clazz.Method(..))
    
匹配有单个参数的方法
execution(* *..Clazz.Method(*))
    
匹配有多个参数的方法
execution(* *..Clazz.Method(*.*))
```





