#### 泛型

##### 泛型类

作用

```
1.往集合添加元素时，只能添加同一种数据类型
2.从集合取值时，无需进行类型转换
3.确保所有集合所有数据类型都是一样的
4.不能使用基本数据类型
5.如果不指定数据类型，默认Object
6.泛型子类继承泛型父类，两个泛型必须保持一致
	=或者子类包含父类
7.子类不是泛型，父类一定要具体泛型类型
```

定义

```
class ClassName <泛型标识，泛型标识...> {
    private 泛型标识 变量名
}
常用泛型标识:T,E,K,V
	=k,v一般是键值对
```

使用

```
定义:类名<具体的数据类型> 对象名 = new 类名();
例: ClassName <String> className = new ClassName();
```

代码

```JAVA
//给类T的泛型
public class Erfen<T> {
//将类的变量约束为T
    private T key;
    public static void main(String[] args) {
        //在new 对象时给出具体类型
        Erfen<String> stringErfen = new Erfen<>();
        //name的类型只能是String
        stringErfen.key = "45";
        System.out.println(stringErfen.key );
    }

}

```

多个泛型

```
public class <T,E> ClassName{
    private T name;
   	private E age;
}
```





##### 泛型接口

定义

```
interface 接口名称<泛型...>{
    泛型 方法名();
}
```

