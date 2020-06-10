## 单体-配置-YML

```
server:
  port: 8080
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/market?characterEncoding=utf8&serverTimezone=UTC
    data-username: root
    data-password: 00000000
  main:
    banner-mode: off
logging:
  file:
    path: Log/log4.log

```





## 单体-配置-Application

### 常规配置

```java
#设置数据源
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/market?characterEncoding=utf8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=00000000
#阿里巴巴连接池类型
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource

#日志设置
logging.file.path=log/log4.log
#为避免冲突重定义端口
server.port=8080
mybatis.configuration.map-underscore-to-camel-case=true
#使用xml文件必配
mybatis.mapper-locations=classpath:generator//*.xml
spring.main.banner-mode=off

#关闭启动图案
spring.main.banner-mode=off
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=127.0.0.1
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.jedis.pool.max-active=20
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.jedis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.jedis.pool.max-idle=10
# 连接池中的最小空闲连接
spring.redis.jedis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=1000
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



## 分布式-配置-YML

### 服务端

<https://blog.csdn.net/weixin_44217401/article/details/104266509>

```yml
server:
  port: 8088 # 端口
spring:
  application:
    name: eurekaserverdemo # 应用名称，会在Eureka中显示
eureka:
  client:
    register-with-eureka: false # 是否注册自己的信息到EurekaServer，默认是true
    fetch-registry: false # 是否拉取其它服务的信息，默认是true
    service-url: # EurekaServer的地址，现在是自己的地址，如果是集群，需要加上其它Server的地址。
      defaultZone: http://127.0.0.1:8088/eureka
```



### 服务提供者

```YML
server:
  port: 8081
spring:
  application:
    name: userservice # 应用名称，会在Eureka中显示
mybatis:
  type-aliases-package: com.geek.pojo
eureka:
  client:
    service-url:
      defaultzone: http://127.0.0.1:8088/eureka
  instance:
    prefer-ip-address: true # 当调用getHostname获取实例的hostname时，返回ip而不是host名称
    ip-address: 127.0.0.1 # 指定自己的ip信息，不指定的话会自己寻找
```

### 服务消费者

```YML
server:
  port: 8080
spring:
  application:
    name: userconsumer
eureka:
  client:
    service-url:
      defaultZone: http://127.0.0.1:8088/eureka
  instance:
    prefer-ip-address: true # 当其它服务获取地址时提供ip而不是hostname
    ip-address: 127.0.0.1 # 指定自己的ip信息，不指定的话会自己寻找
```

## JRebel破解

```
http://jrebel.yanjiayu.cn:9001/64245b6b-ef68-4bdc-aabf-7f8e85a540e4
```

