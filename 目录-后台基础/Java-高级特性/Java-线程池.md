#### Java-线程池

##### 共四种线程池

|          名字           |       特点       |
| :---------------------: | :--------------: |
|   newCachedThreadPool   |  速度快，轻负载  |
|   newFixedThreadPool    | 大小固定，重负载 |
| newSingleThreadExecutor |   单例顺序执行   |
| newScheduledThreadPool  |     延时周期     |

创建

```
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
```

