#### Codes-框架工具

##### Codes-Shiro

###### Shiro-登录处理

```java
@RestController
public class Login {
    //进行密码检查后才校验用户状态，否则可能泄露信息
    @RequestMapping("/login")
    public void index(User user) {
        //获取登录主体
        Subject subject = SecurityUtils.getSubject();
//        开启验证
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername() , user.getPassword());
         /** 无异常    =      成功
             有异常    =      失败
         **/
        try {
            subject.login(token);
       //如果不为空，用户存在，进行逻辑处理
            if (!StringUtils.isEmpty(user)) {}
        }
        // 账号被锁定
        catch (LockedAccountException e) {}
         //未知账号异常
        catch (UnknownAccountException e) {}
        //凭证过期
        catch (ExpiredCredentialsException e) {}
        //多个用户同时登录
        catch (ConcurrentAccessException e) {}
        //认证次数超过限制
        catch (ExcessiveAttemptsException e) {}
        //黑名单账号登录
        catch (DisabledAccountException e) {}
        //不允许资源访问
        catch (UnauthorizedException e) {}
        //未认证就授权
        catch (UnauthenticatedException e) {}
        // 密码错误
        catch (IncorrectCredentialsException e) {}
    }
}

```

###### Shiro-账号注册

```java
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
    //对密码进行加密
    String ciphertext = new Md5Hash(password, salt, 3).toString();
    //类型转换
    ByteSource byteSalt = ByteSource.Util.bytes(ciphertext);
    //将加密后的盐值和密码插入数据库
    user.setSalt(String.valueOf(salt));
    user.setPassword(ciphertext);
    //在Dao中有对应的SQL语句
    Dao层.insertOne(user);
}
}
```

###### Shiro-身份校验

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

###### Shiro-全局Config

```JAVA
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

###### Shiro-Realm

```JAVA
需要继承AuthorizingRealm
public class Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
```

##### Codes-ALiYun

###### ALi-基础信息

```java
//注意检查此处信息
    static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    static String accessKeyId = "LTAI4FpXGmp1TvygZhqdYx8g";
    static String accessKeySecret = "B4IXsT2SescVGMeroCwbzBIvnQwm7i";
    static String bucketName = "sixbyte";
    static OSS ossClient = getOssClient();

    public static OSS getOssClient() {
        return new OSSClientBuilder().build(endpoint , accessKeyId , accessKeySecret);
    }
```

###### ALi-上传本地文件

```java
/*
   上传文本文件
   filePath：仅需要本地文件路径
* */
public static void uploadFile(String filePath) {
    PutObjectRequest putObjectRequest = null;
    try {

        File file = new File(filePath);
        if (!StringUtils.isEmpty(null) ) {
            String name = file.getName();
            //            new PutObjectRequest("bucket名字 , 在云端文件的名字 , 文件对象);
            putObjectRequest = new PutObjectRequest(bucketName , name , file);
            ossClient.putObject(putObjectRequest);
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }

}
```

###### ALi-网络路径

```JAVA
    /*上传网络文件到云端
     	url:一个网络路径
     	fileName:OSS存储文件名
     * */
    public static void uploadNetFile(String url,String fileName) {
// 上传网络流。
        InputStream inputStream = null;
        try {
            if (!StringUtils.isEmpty(url)) {
                inputStream = new URL(url).openStream();
                ossClient.putObject(bucketName , fileName , inputStream);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
```

