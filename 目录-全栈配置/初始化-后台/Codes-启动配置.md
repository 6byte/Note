

## 配置

## 单体-配置

### 配置-Application

```
#设置数据源
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/market?characterEncoding=utf8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=00000000

#阿里巴巴连接池类型
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
#日志设置
logging.file.path=log
#自定义日志格式配置
logging.pattern.file=   %d{yyy MMM dd HH}  -----=*=----- %F : %L  -----=*=----- %m  %n
#为避免冲突重定义端口
server.port=8080
#关闭启动图案
spring.main.banner-mode=off
```

### 配置-Yml

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

### 配置-邮件

```
spring.mail.default-encoding=UTF-8
spring.mail.host=smtp.xxx.com
#发送者的邮箱密码
spring.mail.password=xxxx
#端口
spring.mail.port=25
#协议
spring.mail.protocol=smtp
#发送者的邮箱账号
spring.mail.username=xxxx@qq.com
```

### 配置-Mybatis

```
mybatis.configuration.map-underscore-to-camel-case=true
#使用xml文件必配
mybatis.mapper-locations=classpath:generator//*.xml
```

### 配置-Mybatis-Plus

```
#MybatisPlus默认日志配置
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

#配置逻辑删除
#已经删除的值
mybatis-plus.global-config.db-config.logic-delete-value=0

#没有删除的值
mybatis-plus.global-config.db-config.logic-not-delete-value=1
```

### 配置-Redis

```
spring.redis.port=6379
spring.redis.host=127.0.0.1
spring.redis.database=0
spring.redis.password=
```



## 分布式-配置-YML

### Nacos

注意

```
1.配置文件名一定是Bootstrap.yml,不是application.yml
2.服务地址一定要和NACOS的保持一致
```

生产者

```YML
#提供服务，让其他服务调用
spring:
  application:
    name: demo1
  cloud:
    nacos:
      server-addr: 172.27.35.1:8848
      discovery:
        group: TEST_GROUP
        namespace: 2677f493-d64f-434c-9c89-e21665bd5db0

  main:
    banner-mode: off
server:
  port: 8085
```



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

