#### Java-线程池

##### 共四种线程池

|          名字           |       特点       |
| :---------------------: | :--------------: |
|   newCachedThreadPool   |  速度快，轻负载  |
|   newFixedThreadPool    | 大小固定，重负载 |
| newSingleThreadExecutor |   单例顺序执行   |
| newScheduledThreadPool  |     延时周期     |

创建

```java
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

ScheduledExecutorService  service = Executors.newScheduledThreadPool(5);//5是线程数
Class clazz = new Class();
Thread thread = new Thread(clazz);
service.schedule(thread, 2, TimeUnit.SECONDS);//2是延时2s执行
```

