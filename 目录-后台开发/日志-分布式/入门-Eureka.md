## 起步-SpringCloud

##### 概览

```

核心组件	    					功能

一个分布式系统由多个服务器组成

Eureka		服务治理	服务提供者，服务消费者，注册中心
Ribbon		负载均衡    
Feign 		服务通信
Zuul		服务网关
Hystrix		服务容错
Config		服务配置
Actuarot 	服务监控
Zipkin		服务跟踪

服务注册:每个服务器启动时，都会将自己的信息存储在注册中心
服务发现:用户从注册中心获取后台的网络信息，通过该信息调用服务
服务提供者:			多个服务器
服务消费者:			用户从浏览器访问
服务注册:			 将服务所在的主机，端口版本号，通信协议登记到主机上
服务发现:			 服务发现由eureka实现
服务调用:			 服务调用由ribbon调用


```

##### 本质

```
eureka本质是一个Web项目
```

### 开启Eureka之旅

```
流程：配置服务端->配置客户端
```

网页	https://www.cnblogs.com/idoljames/p/11482670.html

#### 配置服务端

##### 启动类

```JAVA
//开启注解
@SpringBootApplication
@EnableEurekaServer
public class EuerkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EuerkaApplication.class, args);
    }
}

```

##### MAVEN依赖

```
<dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.SR2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

##### YML配置

```
server:
  port: 8761
spring:
  application:
    name: servers-register
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  server:
    wait-time-in-ms-when-sync-empty: 0
    enable-self-preservation: false
```



#### 配置客户端

##### MAVEN配置

```
<dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.SR2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

##### 启动类

```JAVA
@EnableDiscoveryClient
@SpringBootApplication
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

}
```

##### YML配置

```
server:
  port: 8081
eureka:
  client:
    service-url:
      defaultZone: 'http://localhost:8761/eureka/'
spring:
  application:
    name: user-server
```



```

服务发现由Eureka提供，服务调用由Ribbon实现
将服务提供者注册到Eureka中

```

#### 步骤

```
1.开启服务中心
2.将服务提供者注册到Eureka中
3.
```

