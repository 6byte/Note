## Maven-依赖

### Maven-Aop

```vue
<!-- 整合Springboot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

<!-- Spring aop核心依赖 -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.8.7</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>5.2.3.RELEASE</version>
</dependency>
```

### Maven-SpringCloud



```vue
<!--排除eureka依赖XML的依赖,不再返回XML-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>
        spring-cloud-starter-netflix-eureka-client
    </artifactId>

    <exclusions>
        <exclusion>
        <artifactId>jackson-dataformat-xml</artifactId>
            <groupId>
            	com.fasterxml.jackson.dataformat
            </groupId>
        </exclusion>
    </exclusions>
</dependency>
```



### Maven-Httpclient

```vue
<!-- 用于发起Http请求 -->
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.5</version>
</dependency>
```

### Maven-Shiro

```vue
<!-- Shiro缓存控制 -->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-ehcache</artifactId>
    <version>1.3.2</version>
</dependency>

<!-- Shiro核心包 -->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-core</artifactId>
    <version>1.3.2</version>
</dependency>

<!--利用Spring Aop实现注解权限开发 -->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.2.3</version>
</dependency>

<!-- 
Shiro不是一个单纯的Web权限框架,使用shiro-web依赖获取更多技术支持 
-->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-web</artifactId>
    <version>1.2.2</version>
</dependency>
```

### Maven-validation

```XML
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.0.7.Final</version>
</dependency>
```



### Maven-Mybatis

```vue
<!-- mybatis依赖开始 -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.0</version>
</dependency>
<!-- mybatis依赖 -->

<!-- @MapperScan才能正常使用 -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.2</version>
</dependency>
<!-- 必须，否则无法启动 -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.1.1</version>
</dependency>
<!-- end -->
```

### Maven-WebSocket

```vue
<!-- 长连接通信依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
<!-- websocket依赖 -->

<!--
使用websocket时打包会报错，使用该插件跳过测试可打包通过
-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
    <skipTests>true</skipTests>
    </configuration>
</plugin>

```



### Maven-Jsoup

```
<!-- 爬虫框架 -->
<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.12.1</version>
</dependency>

```

### Maven-MarkDown

```
<!--markdown 转 html-->
<dependency>
    <groupId>com.vladsch.flexmark</groupId>
    <artifactId>flexmark</artifactId>
    <version>0.26.4</version>
</dependency>
<dependency>
    <groupId>com.vladsch.flexmark</groupId>
    <artifactId>flexmark-util</artifactId>
    <version>0.26.4</version>
</dependency>
<!--表格渲染插件-->
<dependency>
    <groupId>com.vladsch.flexmark</groupId>
    <artifactId>flexmark-ext-tables</artifactId>
    <version>0.26.4</version>
</dependency>
```

### Maven-Mysql

```
<!-- Mysql jar包 -->
<dependency>
    <groupId>mysql</groupId>
    	<artifactId>mysql-connector-java</artifactId>
    <version>8.0.19</version>
</dependency>
<!-- end -->
```

### Maven-Mybatis-Plus

```vue
<!-- Mybatis-Plus jar包 -->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.2</version>
</dependency>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.1.tmp</version>
</dependency>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.3.1.tmp</version>
</dependency>

```

### Maven-AliYun

```
<!-- 阿里云存储 jar包 -->
<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>3.8.0</version>
</dependency>
```

### Maven-JWT

<https://www.jianshu.com/p/fe67b4bb6f2c>

```
<!-- jwt jar包 -->
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.2.0</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.7.0</version>
</dependency>
```

### Maven-Jedis

```vue
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.6.0</version> 
</dependency>
```

### Maven-Cglib

```vue
<!-- cglib jar包 -->
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.2.12</version>
</dependency>
<!-- END -->
```

### Maven-PageHelper

```vue
<!-- 分页插件 -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.10</version>
</dependency>
<!-- END -->
```

### Maven-Email

<https://www.cnblogs.com/chenjiahao9527/p/12432217.html>

```vue
<!-- 发送邮件 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
<!-- END -->
```

### Maven-Druid

```vue
<!-- 德鲁伊 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.8</version>
</dependency>
```

### Maven-FastJson

```VUE
<dependency>
    <groupId>com.alibaba</groupId>
    	<artifactId>fastjson</artifactId>
    <version>1.2.58</version>
</dependency>
```

### Maven-HashID

```VUE
<!-- ID加密 -->
<dependency>
    <groupId>org.hashids</groupId>
    <artifactId>hashids</artifactId>
    <version>1.0.3</version>
</dependency>
```

