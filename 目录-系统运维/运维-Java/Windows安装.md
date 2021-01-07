## Windows安装

<https://www.cnblogs.com/ssrs-wanghao/articles/8994856.html>

配置环境

```
注意:jdk默认就行，别瞎装浪费时间
-->都在系统变量下进行
-->作用:解决javac问题
1.新建变量
	变量		JAVA_HOME
	值		 C:\Program Files\Java\jdk1.8.0_181
2.新建变量
	变量		 classpath
	值		  .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar
3.给path添加变量,注意变量以分号隔开
	添加值		%JAVA_HOME%\bin;
```

