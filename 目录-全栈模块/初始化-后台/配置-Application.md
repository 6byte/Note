## 配置-Application

### 常规配置

```java

#设置数据源
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=00000000

#日志设置
logging.file.path=Log/log4.log

#为避免冲突重定义端口
server.port=10000

#关闭启动图案
spring.main.banner-mode=off

```

### 分布式

```


#设置数据源
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/test?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=00000000

#日志设置
logging.file.path=cherrioLog/log4.log
logging.level.com.cherrio.cherrio_controller = info

#为避免冲突重定义端口
server.port=10000


#关闭启动图案
spring.main.banner-mode=off

server.port=8087

#设置该服务注册中心的hostname
eureka.instance.hostname=Eureka
#禁止注册自己
eureka.client.register-with-eureka=false
#不检索其他服务
eureka.client.fetch-registry=false
#指定服务中心的位置
eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
```



