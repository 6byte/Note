## Shrio-Session管理

**参考网页**:	<https://blog.csdn.net/qq_34021712/article/details/80418112>

### Session-操作



#### Session-方法

获取

```JAVA
--	获取Session
Subject subject = SecurityUtils.getSubject();
Session session = subject.getSession();
```

使用

```sql
--	常用方法
    getHost()						--	获取当前主机ip地址
    getLastAccessTime()				 --	 获取最后的访问时间
    getStartTimestamp()				  --	 获取用户登录时间
    getTimeout()					 --	 获取session失效时间，单位毫秒
    setTimeout(time)		  		  --  设置session的失效时间
    stop()							 -- 下线

-- key操作
    getAttribute(key)			-- 设置key
    getAttributeKeys()			--	获取所有key
    removeAttribute(key)		--	通过key移除session
```



### Session-监听

```java
通过继承SessionListenerAdapter，覆盖里面的方法
public class ShiroSessionListener extends SessionListenerAdapter {
    @Override
    public void onStart(Session session) {
        //当用户登录、Session被创建时触发
    }
    @Override
    public void onStop(Session session) { 
         //当用户退出、Session被停用触发
    }
    @Override
    public void onExpiration(Session session) {
         //当Session失效触发
    }
}
```

### Session-记住我

<https://blog.csdn.net/zltAlma/article/details/93209521>

#### 使用

```JAVA
/**
     * （新增方法）
     * cookie对象;会话Cookie模板 ,默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid或rememberMe，自定义
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：

        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    
    /**
     * （新增方法）
     * cookie管理对象;记住我功能,rememberMe管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
    
    /**
     * （新增方法）
     * FormAuthenticationFilter 过滤器 过滤记住我
     * @return
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter(){
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        //对应前端的checkbox的name = rememberMe
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }


    /**
     * （修改方法）
     * @Description:配置核心安全事务管理器
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setRememberMeManager(rememberMeManager());//（增加改行）配置记住我
        return securityManager;
    }

   /**（修改方法）
   	* @Description:Filter工厂，设置对应的过滤条件和跳转条件
   */
   @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
       ……
 		LinkedHashMap<String, String> map= new LinkedHashMap<>();
        //map.put("/**", "authc");  //（注释掉该行） 需要认证才能进行访问
        map.put("/itemPage/**", "authc"); //（该行只是用来测试）/itemPage/**，必须通过认证才能访问
        map.put("/**", "user"); //（增加该行） user表示配置记住我或认证通过可以访问的地址
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
```



### Session-验证码