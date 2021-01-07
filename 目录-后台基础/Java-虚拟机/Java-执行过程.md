## Java执行过程

<https://blog.csdn.net/shenwansangz/article/details/82422588>

#### 概览

```
执行过程:
    源码编译机制	--	 将源码编译成CLASS文件
    类加载机制	 --	  将类的信息编译到内存中
    类执行机制	 --   执行class文件
描述:将java文件编译成class文件,加载到内存中执行
```

#### 源码编译

前端编译过程

>java源文件->词法分析->token流->语法分析->语法树->语义分析->注解语法树->CLASS

#### 类加载机制

```
过程
    自底向上检查类是否已经加载
    自顶向下加载加载类
类加载器
	Bootstrap ClassLoader
	Extension ClassLoader
	App ClassLoader
	Custom ClassLoader
```

#### 类执行机制

执行过程

```
过程:加载、验证、准备、解析、初始化、使用、卸载
说明
    JVM主要在程序第一次主动使用类的时候，才会去加载该类
    JVM不是一次性把所有类加载到内存中，并且只加载一次
```


