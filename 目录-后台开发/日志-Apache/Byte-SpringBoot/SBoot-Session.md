## Spring-Boot Session

### 概览

```
定义:Session本质是一个随机字符串，用于比对用户当前状态
产生过程:
    1.浏览器第一次发送请求
    2.服务生成一个随机字符串，由cookies携带发送给浏览器
    3.浏览器再次提交请求，会将Session提交给服务器
    4.服务器校验Session，并做出处理
```

### 代码生成

```JAVA
public void createSession(HttpServletRequest request){
	HttpSession sessoin=request.getSession();//这就是session的创建
    //给session添加属性属性name： username,属性 value：TOM
	session.setAttribute("username","TOM");
	System.out.println(session.geiId);
}
```

### Session管理

```
Session操作类:
	1.是一个自定义类
	2.对Session进行增加删除
	3.将所有Session加入一个Map中，统一管理
Session监听器类:
	1.继承了HttpSessionListener类
	2.覆盖两个方法
		<1>sessionCreated:Session创建触发
		<2>sessionDestroyed:Session销毁触发
```

##### Session操作

```JAVA
public class MySessionContext {
    private static HashMap mymap = new HashMap();
 
    public static synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }
 
    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }
 
    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
            return null;
        return (HttpSession) mymap.get(session_id);
    }
 
}

```



##### Session监听器

```JAVA
@Component
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session正在創建");
        MySessionContext.AddSession(httpSessionEvent.getSession());
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("session注銷中");
        MySessionContext.DelSession(session);
    }
}

```

