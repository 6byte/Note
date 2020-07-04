### 插件配置

#### Mybatis-分页

###### 配置

```java
@RequestMapping("/list")
public PageInfo<DiscussPost> index(Integer page , Integer size) {
    //配置PagerHelper的分页
    PageHelper.startPage(page,size);
    //获取List结果
    List<DiscussPost> list = discussMapper.selectList();
    //注意此处:会返回一个List列表，除了结果以外附带下面信息
    return new PageInfo<>(list);
}
```

###### 使用

```JAVA
@Configuration
public class BeanConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum","true");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
```

#### Aop-配置

注意事项

```java
@EnableAspectJAutoProxy 注解一定要开在配置Aspect的Bean上
一定要优先使用双点(..)匹配

可执行配置
execution(* com.example.test.center..*(..))
within( com.example.test..*)
```



###### 配置

```java
//开启注解
@EnableAspectJAutoProxy
@Configuration
public class BeanConfig {

    //逻辑处理Bean
    @Bean
    public AspectAop getAspectAop() {
        return new AspectAop();
    }
    //切入点Bean
    @Bean
    public SystemArchitecture getSystemArchitecture() {
        return new SystemArchitecture();
    }
}

```



###### 统一切入点

```JAVA
@Aspect
@Component
public class SystemArchitecture {
    // ".." 代表包及其子包
 @Pointcut("within(com.iota.mudule..*)")
    public void inWebLayer() {}
    @Pointcut("within(com.iota.mudule..*)")
    public void inServiceLayer() {}

    @Pointcut("within(com.iota.mudule..*)")
    public void inDataAccessLayer() {}

    @Pointcut("execution(* com.iota.mudule.*.*(..))")
    public void businessService() {}

    @Pointcut("execution(* com.iota.mudule.*.*(..))")
    public void dataAccessOperation() {}
}
```

###### 具体切入点

```JAVA
@Component
@Aspect
public class AspectAop {
    @Before("within( com.example.test..*)")
     public void before() {
        log.warn("执行");
     }
}
```

#### Shiro-配置

###### 拦截配置

```java
//需要单独导入这个包，否则报错

import org.apache.shiro.mgt.SecurityManager;
@Component  //必须配置该注解
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean
                shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> prevent = new LinkedHashMap<>();
        prevent.put("/" , "anon");
        prevent.put("/public/**" , "anon");
        prevent.put("/login/logout" , "logout");//退出
        prevent.put("/**" , "authc");//一定要写在最下面，避免出现不可预知的问题

        shiroFilterFactoryBean.setLoginUrl("/");//登录拦截返回页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/");//授权拦截返回页面
        shiroFilterFactoryBean.setSuccessUrl("/");  //登录成功需要访问的页面
        shiroFilterFactoryBean.setFilterChainDefinitionMap(prevent);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "defaultWebSecurityManager")
//创建DefaultWebSecurityManager
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realmConfig") Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //创建Realm==此处需要修改，改成自定义的Realm
    @Bean(name = "realmConfig")
    public Realm getUserRealm() {
        return new Realm();
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

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

```

###### Realm配置

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

###### 身份校验

```JAVA
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
```

###### 加密注册

```JAVA
(1):请求
@RequestMapping("/add")
 public void insert(User user) {
    setPassword(user);
}

(2):密码加密
public void setPassword(User user) {
        //生成盐值
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        //从浏览器获取密码
        String password = user.getPassword();
        对密码进行加密,要和配置中的一致
        String ciphertext = new Md5Hash(password, salt, 3).toString();
        //类型转换
        ByteSource byteSalt = ByteSource.Util.bytes(ciphertext);
        //将加密后的盐值和密码插入数据库
        user.setSalt(String.valueOf(salt));
        user.setPassword(ciphertext);
        //放进数据库
        Dao层.insertOne(user);
}
```

###### 权限认证

```JAVA
@RequiresPermissions("user:add")
@RequestMapping("/add")
public String add() {
    return"返回越权操作值";
}
```



###### 其他方法

获取Session Id

```JAVA
/**
     * 通过sessionid获取Session
     * @param sessionId
     * @return Session
     */
public static Session getSessionById(String sessionId){
    SessionKey sessionKey = new SessionKey() {
        @Override
        public Serializable getSessionId() {
            return sessionId;
        }
    };
    return SecurityUtils.getSecurityManager().getSession(sessionKey);
}
```

