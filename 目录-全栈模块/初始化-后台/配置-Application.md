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



