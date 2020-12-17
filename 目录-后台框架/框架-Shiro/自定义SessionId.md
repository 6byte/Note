## Shiro自定义SessionId

新建类 CustomSessionIdGenerator

```java
public class CustomSessionIdGenerator implements SessionIdGenerator {
	@Override
	public Serializable generateId(Session session) {
		return "xdclass"+ UUID.randomUUID().toString().replace("-","");
	}
}
```

注册

```JAVA
// 在SHIRO核心配置中 添加
@Bean
public RedisSessionDAO redisSessionDAO() {
    RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    redisSessionDAO.setRedisManager(redisManager());
    redisSessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());
    return redisSessionDAO;
}
```

