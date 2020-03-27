## Maven-Setting配置

### Linux安装Maven

```
1.下载
wget https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz
2.解压到/usr/local中
  tar -xzvf apache-maven-3.5.4-bin.tar.gz -C /usr/local
3.进入到/usr/local改名
 cd /usr/local/
 mv  apache-maven-3.5.4 maven
 
4.设置环境变量
 vim /etc/profile
 
 
 export MAVEN_HOME=/usr/local/maven
 export PATH=${MAVEN_HOME}/bin:${PATH}
 
 
 source /etc/profile
 
 . /etc/profile
5.运行以下脚本查看版本
 mvn -version

6.更换仓库
cd /usr/local/maven/conf/

镜像地址
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>


 
7.初始化
mvn help:system

```
