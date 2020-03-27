## Shiro-组件代码

### 组件-登录处理

```JAVA
//进行密码检查后才校验用户状态，否则可能泄露信息
@GetMapping("/login")
public void index(User host) {
    //获取登录主体
    Subject subject = SecurityUtils.getSubject();
//        开启验证
    UsernamePasswordToken token = new UsernamePasswordToken(host.getUsername(), host.getPassword());
    try {
        /*
         * 无异常    =      成功
         * 有异常    =      失败
         * */
        subject.login(token);
        User user = userDao.findByName(user1.getUsername());
        if(!StringUtils.isEmpty(user)){
            //是否为黑名单
            System.out.println(user.getSalt());
            if(user.getBlack().equals(variable.isDisabled)){
                System.out.println("黑名单用户");
                throw new  DisabledAccountException();
            }
            //是否锁定
            if(user.getStatus().equals(variable.isLocked)){
                System.out.println("账号锁定");
                throw new  LockedAccountException();
            }

        }
    }
    catch(LockedAccountException e) {
//        账号被锁定
    }
    catch(UnknownAccountException e) {
        //未知账号异常
    }
    catch(ExpiredCredentialsException e) {
        //凭证过期
    }
    catch(ConcurrentAccessException e) {
//        多个用户同时登录时抛出
    }
    catch(ExcessiveAttemptsException e) {
//        认证次数超过限制
    }
    catch(DisabledAccountException e) {
//        黑名单账号登录
    }
    catch(UnauthorizedException e) {
//        不允许资源访问
    }
    catch(UnauthenticatedException e) {
//     未认证就授权
    }
    catch(IncorrectCredentialsException e) {
        // 密码错误
    }
}
```

### 组件-注册账号

```JAVA
@RequestMapping("/insert")
 public void insert(User user) {
     //对应下面的方法
    setPassword(user);
}

public void setPassword(User user) {
    //生成盐值
    String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
    //从浏览器获取密码
    String password = user.getPassword();
    //对密码进行加密
    String ciphertext = new Md5Hash(password, salt, 3).toString();
    //类型转换
    ByteSource byteSalt = ByteSource.Util.bytes(ciphertext);
    //将加密后的盐值和密码插入数据库
    user.setSalt(String.valueOf(salt));
    user.setPassword(ciphertext);
    //在Dao中有对应的SQL语句
    userDao.insertOne(user);
}
}
```





### 组件-身份校验

```java
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

### 组件-Shiro配置

```JAVA

@Component	//必须配置该注解
public class ShiroConfig {

//创建ShiroFilterFactoryBean
@Bean(name="shiroFilterFactoryBean")
public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){

ShiroFilterFactoryBean 
shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    //设置安全管理器
shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
    
//添加shiro内置过滤器
/**
         anon:表示可以匿名使用。
         authc:表示需要认证(登录)才能使用，没有参数
         perms：多个参数必须加上引号,并且参数用逗号分割，例如perms["user:add:*,user:modify:*"]
*/
    Map<String, String> prevent = new LinkedHashMap<>();
    prevent.put("/","anon");
    prevent.put("/login/logout","logout");//退出
    prevent.put("/**","authc");//一定要写在最下面，避免出现不可预知的问题
    
shiroFilterFactoryBean.setLoginUrl("/");//登录拦截返回页面
shiroFilterFactoryBean.setUnauthorizedUrl("/");//授权拦截返回页面
shiroFilterFactoryBean.setSuccessUrl("/");  //登录成功需要访问的页面
shiroFilterFactoryBean.setFilterChainDefinitionMap(prevent);
    return shiroFilterFactoryBean;
}
@Bean(name="defaultWebSecurityManager")
//创建DefaultWebSecurityManager
public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realmConfig")UserRealm realm){
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    defaultWebSecurityManager.setRealm(realm);
    return defaultWebSecurityManager;
}

//创建Realm
@Bean(name="realmConfig")
public UserRealm getUserRealm(){
    return new UserRealm();
}

/**
 *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类
 * 需要配置以下两个Bean
 */
@Bean
public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
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

/**
     配置Md5加密管理
     如果在自定义的Reaml中已经配置了，可以不使用该Bean
*/
      @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

```

