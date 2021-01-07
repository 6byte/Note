# 进阶-注解

## 注解-概念

```
1.对元素进行标记，配合反射，实现特定的功能
```

### 元注解

#### **@Retention** 

```
作用:标注注解存活时间
取值
RetentionPolicy.SOURCE 
注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。

RetentionPolicy.CLASS 
注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。

RetentionPolicy.RUNTIME 
注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
```

#### @Documented

```
是否能够将注解中的元素包含到 Javadoc 中去。
```

#### @Target

```
指定了注解运用的位置
取值如下
ElementType.ANNOTATION_TYPE 	可以给一个注解进行注解
ElementType.CONSTRUCTOR 		可以给构造方法进行注解
ElementType.FIELD 				可以给属性进行注解
ElementType.LOCAL_VARIABLE 		可以给局部变量进行注解
ElementType.METHOD 				可以给方法进行注解
ElementType.PACKAGE 			可以给一个包进行注解
ElementType.PARAMETER 			可以给一个方法内的参数进行注解
ElementType.TYPE 				可以给一个类型进行注解，比如类、接口、枚举
```

#### @Inherited

```
Inherited A类拥有该注解，B继承A后，也将拥有该注解
```

#### @Repeatable

```java
一个注解可以注解多次
@Schedule(dayOfMonth="last")
@Schedule(dayOfWeek="Wed", hour=24)
public class RepetableAnnotation{}
```

### 注解的属性

#### 概念

```
1.注解只有成员变量，没有方法
2.以函数形式表现属性，int param()相当于int param;
3.用default设定默认值
```

#### 支持的数据类型

```
基本类型	int,float,boolean,byte,double,char,long,short
JDK类	  String，Class，enum，Annotation
```

## 注解-使用

```JAVA
基本使用
    //标识该类只能定义在普通成员变量<FIELD>上
@Target(value = ElementType.FIELD)
public @interface test {
    //成员方法以函数体现
    //用default设定默认值
    //返回值是成员类型
    int count() default 10;
}
```
