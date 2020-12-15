## 基础应用

### 加密

```
MD5Hash()方法：MD5Hash("原始值")
```



### ShiroConfig

```JAVA

//  需要单独导入这个包，否则报错

import org.apache.shiro.mgt.SecurityManager;

//  必须配置该注解,默认关闭
@Component
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        //	anon,authc,authcBasic,perms,port,rest,roles,ssl,user
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 注意过滤器配置顺序 不能颠倒
        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/public/**", "anon");
        filterChainDefinitionMap.put("/**", "anon");
        // 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "realmConfig")
    public ShiroRealm getUserRealm() {
        return new ShiroRealm();
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    // 配置 RedisManager ,用于与底层Redis交互
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        return redisManager;
    }

    // 配置SessionDao ，用于控制 RedisManager
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置 回话管理器 ，用于存储 token状态
     */
    @Bean
    public DefaultSessionManager sessionManager() {
        CustomSessionManager manager = new CustomSessionManager();
        manager.setSessionDAO(redisSessionDAO());
        return manager;
    }

    // 开启缓存
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    // 配置核心安全管理器:将自定义管理器注册到安全管理器
    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm) {
        // 一定要注意是 : DefaultWebSecurityManager
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);

        securityManager.setSessionManager(sessionManager());
        // 将自定义的Redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类
     * 需要配置以下两个Bean
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}

```

### MyRealm

```JAVA
//需要继承AuthorizingRealm
public class Realm extends AuthorizingRealm {
    
//配置加密，一旦使用MD5校验，就一定得配置该类
@Override
public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//HashedCredentialsMatcher是shiro提供的解析盐的实现类
    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
    //采用的算法是md5
    matcher.setHashAlgorithmName("md5");
    //设置加密次数，和注册的要一致
    matcher.setHashIterations(3);
    super.setCredentialsMatcher(matcher);
}
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //授权
        Set<String> set = new HashSet();
        set.add("user:add");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(set);
        return info;
}

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
```

### 密码验证

````JAVA
AuthenticationInfo 类

@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    String username = (String) token.getPrincipal();
    User user = userDao.findByName(username);
    /*判断用户名是否存在*/
    if(StringUtils.isEmpty(user)) {
        return null;
    }
    String password = user.getPassword();
    
//    盐值是ByteSource类型，
    ByteSource salt = ByteSource.Util.bytes(user.getSalt());
    /*
    第一个参数：返回给shiro的参数，
    第二个参数：密码，
    第三个参数: 盐值
    第四个参数：reaml的名字，在配置多个reaml时有用
    */
    return new SimpleAuthenticationInfo(username, password, salt,"");
}
````

### 用户注册

```JAVA

public void setPassword(User user) {
        //生成盐值
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        对密码进行加密,要和配置中的一致
        String passwd = new Md5Hash(user.getPassword(), salt, 3).toString();
        //将加密后的盐值和密码插入数据库
        user.setSalt(String.valueOf(byteSalt));
        user.setPassword(ciphertext);
        //放进数据库
        Dao层.insertOne(user);
}
```

### 权限认证

````JAVA
@RequiresPermissions("user:add")
@RequestMapping("/add")
public String add() {
    return"返回越权操作值";
}
````

## 前后端分离

### CustomSessionManager

>SessionId 默认放在Cookis中，如要获取，需要重写 DefaultWebSessionManager

```JAVA
@Component
public class CustomSessionManager extends DefaultWebSessionManager {
    // 
	final private static  String AUTHORIZATION = "AuthToken";

	final private static  String REFERENCED_SESSION_ID_SOURCE = "header";

	@Override
	protected Serializable getSessionId( ServletRequest request,  ServletResponse response) {
		// 返回session Id
		 String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		System.out.println("id：" + id);
		if (StringUtils.isEmpty(id)) {
			// 如果没有携带id参数则按照父类的方式在cookie进行获取
			System.out.println("super：" + super.getSessionId(request, response));
			return super.getSessionId(request, response);
		} else {
		
            //指定ID获取来源 ,从 "header" 中获取
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            //指定 ID获取的内容 上面获取的id
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            //SessionId是否需要验证
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return id;
		}
	}
}
```

### Bean 配置

```JAVA
//继承 AuthCachePrincipal 就能存在Redis中
@Data
public class Users implements Serializable ,AuthCachePrincipal {
    ...//自定义属性
	@Override
	public String getAuthCacheKey() {
		// TODO Auto-generated method stub
		return null;
	}
}

```



### 登陆验证

```JAVA
@PostMapping(value = "/login")
public Object login(User user){      
    Subject subject = SecurityUtils.getSubject();
    try {
        UsernamePasswordToken token 
            = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
        //登陆成功后，生成Token
        String newToken = JwtUtils.generateJwtToken(user.getUsername());
        //将信息放在请求头中，并，用于在自定义的SessionManager中检测
        response.setHeader("AuthToken", newToken);

        return ResponseEntity.ok().build();
    } catch (AuthenticationException e) { 
        // 如果校验失败，shiro会抛出异常，返回客户端失败

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
```



### 