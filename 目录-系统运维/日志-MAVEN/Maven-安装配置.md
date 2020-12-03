## Maven-Setting配置

### Linux安装Maven

##### 下载

```
wget https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz
```

##### 解压

```sql
--	解压到/usr/local中
  tar -xzvf apache-maven-3.5.4-bin.tar.gz -C /usr/local
  
--	改名
cd /usr/local/
mv  apache-maven-3.5.4 maven
```

##### 添加环境

```
--	设置环境变量
vim /etc/profile
--	添加
export MAVEN_HOME=/usr/local/maven
export PATH=${MAVEN_HOME}/bin:${PATH}

--	使配置生效
source /etc/profile
. /etc/profile

```

配置仓库

```xml
cd /usr/local/maven/conf
在setting中添加
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```

初始化

```
mvn help:system
```



### Windows-Maven

参考<https://www.cnblogs.com/eagle6688/p/7838224.html>

#### 下载

```
https://mirror.bit.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip
```

#### 环境搭建

```
--	新建系统环境变量
MAVEN_HOME
--	赋值maven根目录路径
D:\program\apache-maven-3.6.3
--  追加path环境变量
%MAVEN_HOME%\bin\;

--	测试
mvn
```

#### 配置

描述

```
setting.xml在maven根目录下
通过搜素标签的方式复制粘贴
```



##### 配置--setting.xml

```xml
--	mirrors，复制粘贴
<mirrors>
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
    </mirror>
  </mirrors>
```

##### 配置--本地路径

```
--	在 maven 安装目录找setting文件
--	在 setting 搜 localRepository
--  将地址替换成当前maven所在的根目录
```



##### 配置--pom.xml

```xml
--	搜 repositories，复制粘贴
<repositories>  
        <repository>  
            <id>alimaven</id>  
            <name>aliyun maven</name>  
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
            <releases>  
                <enabled>true</enabled>  
            </releases>  
            <snapshots>  
                <enabled>false</enabled>  
            </snapshots>  
        </repository>  
</repositories>  
```

