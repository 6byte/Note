## Java-正则字符串

#### 匹配数量

| 正则  |    解释    |  REG  |  EXM   |
| :---: | :--------: | :---: | :----: |
|   *   | 任意个字符 |  a*   | aa,aaa |
|   a   |  至少一个  |  a+   |  a,aa  |
|   ?   |   0或1个   |  a?   | a,null |
|  {n}  |  匹配n个   | a{2}  |   aa   |
| {n,m} | 匹配n到m个 | {1,2} |  a,aa  |
|  \s   | 仅匹配空格 |       |        |

其他规则

| 正则  |          解释          |
| :---: | :--------------------: |
|   ^   |        匹配开头        |
|   $   |        匹配结尾        |
| [...] | 匹配括号内任意一个内容 |
| [a-f] |  匹配a-f之间任意一个   |
| A\|B  |        匹配A或B        |

```java
[a-f0-9]{6}:匹配任意十六进制数
//a-f是一组，0-9是一组，匹配两组任意一个
//[组一 | 组二 | 组三]
[^a-f]:不在a-f之间的数

```



#### 分组匹配

##### Pattern

```JAVA
Pattern pattern =Pattern.compile("正则表达式");
Matcher matcher = pattern.matcher("待匹配字符串");

//matcher.matches()：是否匹配上
if(matcher.matches()){
    // group(0):原始字符串
    // group(1):匹配到的  第一条  字符串
    // group(2):匹配到的  第二条  字符串
    String group = matcher.group(1);
}
```



#### 正则对象

概览

```js
必要条件
-目标字符串，正则表达式，Pattern添加正则，Matcher添加目标字符串
步骤
    -定义一个目标字符串和一个正则表达式
    --使用Pattern获取Matcher对象
    ---使用Matcher匹配字符串
流程:
	Pattern+正则表达式 -> Matcher+目标字符串 -> 使用Matcher对象
```

代码

```java
//       目标字符串
String string = "Happiness  is  a way , station between too much and too little.";
//       正则表达式
String reg = "[\\s\\,]+";
//        Pattern添加正则
Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
//        Matcher添加目标字符串
Matcher matcher = pattern.matcher(string);
```

##### Matcher

|  方法名   |             作用             |
| :-------: | :--------------------------: |
| lookAt()  |      与字符串的前缀匹配      |
| find（）  |   查找是否有匹配的子字符串   |
| start（） | 返回字符串的第一个字符的索引 |
|  end（）  |  返回字符串最后一个的索引+1  |
|           |                              |

##### 提取指定字符串

```
通过start(),end()提取字符串位置,再截取
```

