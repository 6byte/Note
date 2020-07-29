## Aop-概览

#### 概念

```
作用
	在java启动后时，将代码添加到指定的方法中，并执行
实现方式
	Spring Aop动态织入
	Aspectj静态织入
关键字
	切入点，通知，切面，织入
	关系:切面 = 切入点 + 通知
```

#### 应用范围

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



#### 通知

概览

```
指定什么时候执行切入
```

常用注解

```
@Before				前置通知，在目标方法执行前调用
@After				后置通知，在目标方法执行后调用
@Around				环绕通知，它将覆盖原有方法，允许通过反射调用原有方法
@AfterReturning		返回通知，在目标方法执行并正常返回后调用
@AfterThrowing		异常通知，当连接点方法异常时调用
```

#### 表达式

概览

```
作用:匹配目标方法
```

使用

```
execution(<修饰符>?<返回类型><方法名>(<参数>)<异常>?)
```

