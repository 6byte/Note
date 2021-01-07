## Java-Linux

### 安装JAVA

参照网页		

```js
#卸载自带的JAVA
查询系统自带的JDk
rpm -qa | grep jdk 
将列出来的jdk删除
rpm -e --nodeps java-1.8.0-openjdk-devel-1.8.0.242.b08-0.el7_7.x86_64

#安装Java,两个必须一起安装
	yum install -y java-1.8.0-openjdk.x86_64
	yum install -y java-1.8.0-openjdk-devel.x86_64

#验证
java -version
//验证配置
echo $JAVA_HOME

```



### Linux配置环境变量

```js
#配置路径:		 vim /etc/profile

在末尾添加	jdk的路径和jre的路径

export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk 
export JRE_HOME=/usr/lib/jvm/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH

#使配置生效 		. /etc/profile 
```

### 环境配置详解

```
//配置各路径,/root/java/jdk/是解压的目录路径

 export JAVA_HOME=/root/java/jdk/jdk版本编号

 export JRE_HOME=/root/java/jdk/jdk版本编号

 export CLASSPATH=.:/root/java/jdk/jdk版本编号/lib:$CLASSPATH

 export PATH=/root/java/jdk/jdk版本编号/bin:$PATH
```



### JAR包

解压jar包

```
解压到当前目录
jar xvf hello.jar
```

