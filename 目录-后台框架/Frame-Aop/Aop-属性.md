### AOP-属性

#### 注解

##### Pointcut

```java
概念:统一方法切入点
注意:绝对不能写错，否则启动不了
// 指定的方法
@Pointcut("execution(* execution(..))")
public void Method() {}
```

匹配方式

```
execution,within,@annotation,bean
```

- execution：匹配具体某个方法

- within：匹配所在类或所在包下面的方法

- @annotation:方法上具有特点的注解
- bean:根据bean的名字匹配

##### JoinPoint

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

#### 表达式

##### execution

- 匹配具体方法

- ```
  execution([方法的访问控制修饰符 | 方法的返回值] 包名.类名/接口名.方法名(参数))
  ```

- 方法的控制修饰符如public可以省略

- 包名和类名必须有



##### 通配符

概览

```
可以匹配:返回值，包名，类名，方法名，参数
execution(返回值 包名.类名.方法名(参数))
```

匹配方法

- ```JAVA
  匹配具体方法
  execution(* *.*.Clazz.Method())//匹配单个包,每个星号代表一个包名
  --匹配  public com.java.Clazz Method()
      	public com.js.Clazz Method()
  ```

- ```JAVA
  匹配任意包下类中的所有方法
  execution(* *..Clazz.Method())//匹配任意包下类中的所有method
  ---匹配  public com.java.oracle.Clazz Method()
      	public com.java.mybatis.Clazz Method()
  ```

- ```java
  匹配包含某个字符的方法
  execution(* *.*.Clazz.*Method())
  --匹配   public com.java.Clazz javaMethod()
      	public com.java.Clazz cMethod()
      	public com.java.Clazz jsMethod()
  ```

匹配参数

- ```JAVA
  匹配有多个参数的方法
  execution(* *..Clazz.Method(..))
  ```

- ```JAVA
  匹配有单个参数的方法
  execution(* *..Clazz.Method(*))
  ```

- ```java
  匹配有多个参数的方法
  execution(* *..Clazz.Method(*.*))
  ```

  

