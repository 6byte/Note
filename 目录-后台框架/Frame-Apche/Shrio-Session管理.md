## Shrio-Session管理

**参考网页**:	<https://blog.csdn.net/qq_34021712/article/details/80418112>

### Session-操作

```java
1.获取Session
Subject subject = SecurityUtils.getSubject();
Session session = subject.getSession();

2.使用方法
返回值				方法名					描述
Object	getAttribute(Object key)	根据key标识返回绑定到session的对象
Collection	getAttributeKeys()	获取在session中存储的所有的key
String	getHost()	获取当前主机ip地址，如果未知，返回null
Serializable	getId()					获取session的唯一id
Date	getLastAccessTime()				获取最后的访问时间
Date	getStartTimestamp()				获取session的启动时间
long	getTimeout()			  		获取session失效时间，单位毫秒
void	setTimeout(long maxTime)		设置session的失效时间
Object	removeAttribute(Object k)		通过key移除session中绑定的对象
void	setAttribute(Object k, Object v)	设置session会话属性
void	stop()							销毁会话
void	touch()							更新会话最后访问时间
```

### Session-监听

```
通过继承SessionListenerAdapter，覆盖里面的方法
public class ShiroSessionListener extends SessionListenerAdapter {

//当用户登录，Session被创建时触发
@Override
public void onStart(Session session) { }
//当用户退出，Session被停用触发
@Override
public void onStop(Session session) { }
@Override
//当Session失效触发
public void onExpiration(Session session) { }
}
```

### Session-记住我



### Session-验证码