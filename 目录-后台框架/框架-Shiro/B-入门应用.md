## 基础应用

### ShiroConfig

```JAVA
//需要单独导入这个包，否则报错
import org.apache.shiro.mgt.SecurityManager;

@Component  //必须配置该注解
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

     ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
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

### Realm

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

### 加密

```JAVA
(1):请求
@RequestMapping("/register")
 public void register(User user) {
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

