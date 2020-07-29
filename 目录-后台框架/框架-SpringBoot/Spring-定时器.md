### SpringBoot -定时器

https://www.jianshu.com/p/1defb0f22ed1

#### 入门使用

概览

```

```



##### 开启

```JAVA
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class , args);
    }
}
```

##### 使用

```java
@Scheduled(cron = "0 05 03 ? * *")
@RequestMapping("/test")
public void testTasks() {
    doSome();
}
```

#### 参数详解

##### 参数-CRON

```
语法:[秒] [分] [小时] [日] [月] [周] [年]
```

| 序号 | 说明 | 必填 |  允许填写的值  | 允许的通配符  |
| :--: | :--: | :--: | :------------: | :-----------: |
|  1   |  秒  |  是  |      0-59      |    , - * /    |
|  2   |  分  |  是  |      0-59      |    , - * /    |
|  3   |  时  |  是  |      0-23      |    , - * /    |
|  4   |  日  |  是  |      1-31      | , - * ? / L W |
|  5   |  月  |  是  | 1-12 / JAN-DEC |    , - * /    |
|  6   |  周  |  是  | 1-7 or SUN-SAT | , - * ? / L # |
|  7   |  年  |  否  |   1970-2099    |    , - * /    |

通配符说明

```
* 说明:总会触发
  举例:在分的字段上设置 *,表示每一分钟都会触发。

? 说明:不指定值
  举例:每月的10号触发一个操作，但不关心是周几， 0 0 0 10 * ?

- 说明:表示区间
  举例:在小时上设置 “10-12”,表示 10,11,12点都会触发

, 说明:表示指定多个值
  举例:在周字段上设置 “MON,WED,FRI” 表示周一，周三和周五触发
  
/ 说明:用于递增触发。
  举例:如在秒上面设置”5/15” 表示从5秒开始，每增15秒触发(5,20,35,50)
  
L 说明:表示最后的意思
  举例:设置在日字段上，表示当月的最后一天
  
W 说明:表示离指定日期的最近那个工作日(周一至周五)
  举例:在日字段上设置”15W”，表示离每月15号最近的那个工作日触发。

```

##### 参数-fixedDelay

```
概念:上一次执行完毕时间点之后多长时间再执行
@Scheduled(fixedDelay = 5000) 
```



##### 参数-fixedRate

```
@Scheduled(fixedRate = 5000) 
//上一次开始执行时间点之后5秒再执行
```

