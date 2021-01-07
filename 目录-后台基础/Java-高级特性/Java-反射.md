# 进阶-反射编译

## 反射-概念

```
基本概念
1.反射是指在程序运行时，仍然可以对类的成员值进行访问修改
2.反射是对目标	xxx.class文件进行操作
3.反射可以获取:	成员变量，方法，构造函数，修饰符，包，父类，父接口，注解
4.可以通过反射创建对象
5.可以访问私有成员变量，破坏封装
6.xxx.class必须存在，如果不存在，可以即时编译

重要概念
1.反射可以将类拆解，再封装成其他对象
2.理解解耦
	1.当需要使用某个类的时候，需要先new一个对象，否则无法使用它的成员
	2.当项目体积增大，new会变得更加频繁
	3.每次new一个对象都会占用空间，会使得内存难以管控
	4.此时，对象中的成员使用和new关键字产生了强耦合关系
	5.不new一个对象，就无法使用对象中的方法，new出了方法会占用大量内存
	
通过反射解决
    反射是运行时调用，仅当需要一个类的时候才new一个对象
    不需要时，目标只是一个class文件,不占用内存

技术应用
JDBC,数组扩充，定时器，JSON和Java对象互转，tomcat中servlet的创建，spring IOC
```

### 反射-实现流程

```java
  
1.获取Class
Class<Lairu> clazz = Lairu.class;

2.获取默认构造方法
    //给属性设值，调用方法都需要用到
 Lairu lairu = clazz.newInstance();

3.获取Method,Filed
    //非常关键的一步
Method m = clazz.getMethod("publicMethod");

4.调用
Method publicMethod = clazz.getMethod("publicMethod", String.class);

```

### 反射-总结

```
使用:如果需要调用，修改对象，则需要传入对象的值
```

## 反射-获取信息

### 获取Class对象

```js
三种方式获取
==>	Class.forName

说明:基于JVM类加载机制的一个方法，将字节码加载进内存
ex:   
Class<?> clazz = Class.forName("test");

==>类名.class
每个被加载进方法区的类，都会有一个属性，记载了类的所有信息
Class clazz = test.class;
Class<?> clazz = testA.getClass();

==>类名.getClass()
该方法从Object继承而来,所以需要新建对象
 Test test = new Test();
Class<?> clazz = test.getClass();
```

### 获取信息

```
由Class 类提供	获取成员变量，注解，方法，注解等等属性
```

```
获取方法
getConstructors()					获取公共构造器 
getDeclaredConstructors()			获取所有构造器 
getMethods()						获取类公共类型的方法 
getDeclaredMethods()				获取类的所有方法 
getMethod(String name, Class[] param)获得类的特定公共类型方法

获取成员属性
getFields()							获取公共类型的所有属性 
getDeclaredFields()			  		获取所有属性
getDeclaredField(String name)		获取单个属性

获取类的属性
newInstance()						获取对象
getName()							获取类名包含包路径 
getSimpleName()						获取类名不包含包路径
getModifiers()						获取修饰符 
getInterfaces()						获取所实现的接口 
getDeclaredClasses()				获取内部类 
getDeclaringClass()					获取外部类 
```

## 反射-调用方法

### 操作属性和值

```java
目标&作用:修改Lairu类中属性password的值并输出
    
流程解析:
1.通过Class可以获得Filed
2.通过Filed可以对字段经行读写
3.Filed中有get,set等多种方法，还可以获取注解
4.getDeclaredField(String name)是Filed主要获取方式
    


//Lairu类代码
public class Lairu  {
	public String password = "123";
}
    
//main代码
public class Main {
public static void main(String[] args){
    //获取Class类
    Class<Lairu> clazz = Lairu.class;
    //获取Lairu对象，用于对属性进行读写
    Lairu lairu = new Lairu();
    //根据名字获取属性的Field对象
    Field field = clazz.getDeclaredField("password");
    
    //暴力反射，如果password为private，可以通过该步骤进行反射
    field.setAccessible(true);  
    
    field.set(lairu,"hey");
    //获取值的关键
    Object data = field.get(lairu);
    
    //输出:	public java.lang.String Lairu.password
    System.out.println(field);
    //输出:	hey
    System.out.println(data);
}
}

方法解释
1.field.set(目标对象，需要重新设置的值)
Field field = clazz.getDeclaredField("password");
ex:field.set(lairu,"hey");//给password重新赋值

2.field.get(目标对象) 
Field field = clazz.getDeclaredField("password");
Object data = field.get(lairu);//获取password的值
```

### 操作构造函数

```java
目标&作用:对Lairu中的属性和方法进行访问
流程解析:
	1.获取构造器有两种方式
    2.通过获取构造器对目标对象进行修改
说明:获取到构造器之后，与new出来的对象调用无异
Lairu类：
    public class Lairu {
//提供了getter和setter方法
private String sex;
public String getSex() {return sex;}
public void setSex(String sex) {this.sex = sex;}
}

    
main方法:
public static void main(String[] args){
    Class<Lairu> clazz = Lairu.class;
    Lairu lairu = new Lairu();
//  获取对象,该方式只能调用默认构造器
    Lairu lairu2 = clazz.newInstance();
//    通过获取到的构造器，对Lairu的属性进行修改
    lairu2.setSex("女");
    //获取信息
    System.out.println(lairu2.getSex());
    
第二种方式创建
// 根据参数，获取构造器，适合调用有参构造器
    Constructor<Lairu> constructor = clazz.getConstructor(String.class);
//    创建对象
    Lairu lairu1 = constructor.newInstance("张三");
}
```

### 操作方法

```java
作用&目标:调用Lairu中的方法，并传参

public static void main(String[] args{
    Class<Lairu> clazz = Lairu.class;
//  获取对象
    Lairu lairu = clazz.newInstance();
//    调用无参方法
    Method m = clazz.getMethod("publicMethod");
//    通过获取到的构造器，新建一个对象后，调用它的方法
   m.invoke(lairu);
    
//调用有参方法
    Method publicMethod = clazz.getMethod("publicMethod", String.class);
    public Method.invoke(lairu,"Hey");
}
}
方法说明
Method m = clazz.getMethod("publicMethod");
m.invoke(lairu,"Hey");
//invoke(对象，参数) 调用publicMethod方法，并传参Hey
	

```

## 反射获取注解信息

```java
目标：获取@test标注的值
lairu类
public class Lairu extends Object implements Serializable {
//属性
private String username;
@test(count="test")
public String password ;
}

public class Main {
public static void main(String[] args) {

    Class<Lairu> clazz = Lairu.class;
//    第一种方式获取对象
    Lairu lairu = clazz.newInstance();
    Field field = clazz.getDeclaredField("password");
    field.setAccessible(true);
    //非常关键的一步
    //field.getAnnotation(test.class)返回的是注解test的'实例'
    String count = field.getAnnotation(test.class).count();
    System.out.println(count);
}
}
```



