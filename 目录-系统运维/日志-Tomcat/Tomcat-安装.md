## Tomcat

### Tomcat安装



```js
#Tomcat下载
//后台运行
nohup wget https://downloads.apache.org/tomcat/tomcat-8/v8.5.51/bin/apache-tomcat-8.5.51.tar.gz &

#Tomcat解压

tar -zxvf 刚刚下载的文件名

#分配权限
sudo chmod a+rwx -R logs 

#启动，在bin目录下运行
./startup.sh

#结束
./shutdown.sh

#解压war包

jar -xvf hello.war
//一般可以自动解压，有时候不行，需要自行解压
打包命令
jar -cvfM0 hello.war ./
x 解包
c 打包
v 显示过程信息
0 这个是阿拉伯数字，只打包不压缩的意思
```

#### Tomcat 配置详解

参照网页		https://blog.csdn.net/skp127/article/details/52026150

##### 概览

```
重点掌握		 Server,Connector,Host标签
工作模式3种		独立Servlet，进程内servlet，进程外servlet

```



##### serve.xml

```XML
<!-- 启动Server  
     在端口8005处等待关闭命令  
     如果接受到"SHUTDOWN"字符串则关闭服务器  
-->  
<Server port="8005" shutdown="SHUTDOWN" debug="0">  
  <Listener className="org.apache.catalina.mbeans.ServerLifecycleListener" debug="0"/>  
  <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" debug="0"/>  
  <!-- Tomcat的Standalone Service  
       Service是一组Connector的集合  
       它们共用一个Engine来处理所有Connector收到的请求  
       -->  
  <Service name="Tomcat-Standalone">  
    <!-- Coyote HTTP/1.1 Connector  
         className : 该Connector的实现类是org.apache.coyote.tomcat4.CoyoteConnector  
         port : 在端口号8080处侦听来自客户browser的HTTP1.1请求  
         minProcessors : 该Connector先创建5个线程等待客户请求，每个请求由一个线程负责  
         maxProcessors : 当现有的线程不够服务客户请求时，若线程总数不足75个，则创建新线程来处理请求  
         acceptCount : 当现有线程已经达到最大数75时，为客户请求排队  
                       当队列中请求数超过100时，后来的请求返回Connection refused错误  
         redirectport : 当客户请求是https时，把该请求转发到端口8443去  
         其它属性略  
         -->
    <Connector className="org.apache.coyote.tomcat4.CoyoteConnector"   
               port="8080"   
               minProcessors="5" maxProcessors="75" acceptCount="100"   
               enableLookups="true"   
               redirectPort="8443"   
               debug="0"   
               connectionTimeout="20000"   
               useURIValidationHack="false"   
               disableUploadTimeout="true" />  
    <!-- Engine用来处理Connector收到的Http请求  
         它将匹配请求和自己的虚拟主机，并把请求转交给对应的Host来处理  
         默认虚拟主机是localhost  
         -->  
    <Engine name="Standalone" defaultHost="localhost" debug="0">  
      <Logger className="org.apache.catalina.logger.FileLogger" .../>  
      <!-- 虚拟主机localhost  
           appBase : 该虚拟主机的根目录是webapps/  
           它将匹配请求和自己的Context的路径，并把请求转交给对应的Context来处理  
           -->  
      <Host name="localhost" debug="0" appBase="webapps" unpackWARs="true" autoDeploy="true">  
        <!-- Context，对应于一个Web App  
             path : 该Context的路径名是""，故该Context是该Host的默认Context  
             docBase : 该Context的根目录是webapps/mycontext/  
             -->  
  
        <Context path="" docBase="mycontext" debug="0"/>  
        <!-- 另外一个Context，路径名是/wsota -->  
  
        <Context path="/wsota" docBase="wsotaProject" debug="0"/>  
      </Host>  
        
    </Engine>  
  
  </Service>  
  
</Server>  
  
  
<!----------------------------------------------------------------------------------------------->  
```

