## Spring-概念

https://www.jianshu.com/p/1dec08d290c1

### Bean

回顾

```JAVA
@Bean(initMethod = "common"):配置定义初始化方法
@Resource:非Spring注解
@Scope("prototype"):定义多例
```





#### 作用域

```
概览:singleton,prototype,request,session,global-session
```

|          singleton           |      prototype      |        global-session         |          session          |             request              |
| :--------------------------: | :-----------------: | :---------------------------: | :-----------------------: | :------------------------------: |
| 加载配置文件时创建单实例对象 |   调用时返回对象    | 所有的Session共享一个Bean实例 | 不同Session使用不同的实例 | 每次HTTP请求都会创建一个新的Bean |
|        仅创建一个对象        |  每次返回新的对象   |                               |                           |                                  |
|         @Scope(默认)         | @Scope("prototype") |                               |                           |                                  |



#### 生命周期

概览

```
1.初始化 实例化 属性赋值 初始化 销毁 
2.创建对象->接口初始化对象->配置初始化对象->方法调用
```





##### 创建阶段



创建者

|        BeanFactory        |          ApplicationContext           |
| :-----------------------: | :-----------------------------------: |
|  对Spring内部开发者使用   | 是BeanFactory的子接口，面向所有人使用 |
| 调用Bean的时候加载,懒加载 |               即时加载                |
|                           |                                       |

```java
1.ApplicationContext主要实现子类
ClassPathXmlApplicationContext
FileSystemXmlApplicationContext

2.获取
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spring.class);
--此时singleton创建对象，prototype不创建对象
```

##### 初始化对象

```java
--如何初始化:	通过用户调用
--调用方式:		 目标方法继承 InitializingBean()
--调用顺序:		 先执行接口,在执行接口,约定大于配置
```

第一种方式

```java
----Apple中一定要有 common()这个方法
@Bean(initMethod = "common")
public Apple method() {
	return new Apple();
}
```



第二种方式

```java
Bean类通过继承 InitializingBean()
public class Apple implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
    }
}
```



### 自动装配

### 属性注入

