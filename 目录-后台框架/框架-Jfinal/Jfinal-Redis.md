## Jfinal-Redis

#### 基本使用

```JAVA
RedisPlugin 详解
RedisPlugin redis=new RedisPlugin("自定义cache", "地址(默认为127.0.0.1)","连接密码");

配置
@Override
public void configPlugin(Plugins me) {
　　RedisPlugin redis=new RedisPlugin("bbs", "","");
　　me.add(redis);
}
使用
public void index() {
    // 获取名称为bbs的Redis Cache对象
    Cache bbsCache = Redis.use("bbs");
    bbsCache.set("key", "value");
}
```

#### 

```

```

