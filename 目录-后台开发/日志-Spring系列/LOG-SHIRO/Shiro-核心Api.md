## Shiro-核心Api

### 概览

简单结构

```js
Subject					用户主体
SecurityManager			安全管理器
Reaml					数据管理
```

细分架构

```
subject：			对用户进行操作的对象，可以是真实用户，也可以是程序
securityManager：			核心管理器
authenticator：				认证器
authorizer：					授权器
sessionManager：				Session管理
SessionDao：					管理session数据
cache Manager：				缓存管理器
realm：						与数据库匹配操作
cryptography：				密码管理
```



执行流程

```JS
#1.通过UsernamePasswordToken获得账号密码
UsernamePasswordToken token = new UsernamePasswordToken(..);

#2.subject.login(token);将信息传递给AuthenticationInfo
subject.login(token);

```



### 详解-Subject

##### 理解

```JAVA
理解:一个 Shiro Subject 实例代表了当前用户的安全状态和操作。
如下
authentication(login)				登录检查
authorization(access control)		权限检查
session access						session控制
logout  							登出

获取方式
1.通过SecurityUtils获取Subject对象
Subject subject = SecurityUtils.getSubject();
2.自定义一个Subject
Subject mySubject = new Subject.Builder().buildSubject();
```

##### 获得Session

```java

//获取Session
Session session = subject.getSession();

可以实现:验证码，记住我，Session监听等操作
Session操作详见 	Shrio-Session管理
```

##### 获取信息

```java
1.控制登录
Subject subject = SecurityUtils.getSubject();
2.得到当前用户名
String currentUser = subject.getPrincipal().toString();

```

##### 检验权限

```
3.校验当前用户的权限和角色
//是否是拥有角色
boolean isRole = subject.hasRole( "admin" );
//是否拥有权限
boolean isPer = subject.isPermitted("xiaoming:run");
```

##### 登出

```JAVA
//核心代码
subject.logout();

@RequestMapping("/user/logout")
public String logout(HttpSession session) {
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return "index.html";
}

需要与拦截器一起使用
prevent.put("/login/logout","logout");
```



### Api-Reaml

##### 简单实现

```
1.自定义Reaml必须继承	AuthorizingRealm
2.重写两个保护方法	
doGetAuthorizationInfo 
doGetAuthenticationInfo
3.关注AuthorizingRealm类
```

```JAVA
public class UserRealm extends AuthorizingRealm {
@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //参照下面的doGetAuthorizationInfo解析
}

@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //参照下面的doGetAuthenticationInfo解析
    }
}
```



##### 身份检验

```
类名:doGetAuthenticationInfo
```

```java
@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    //获取用户名
    String username = (String) token.getPrincipal();
    User user = userDao.findByName(username);
    if(StringUtils.isEmpty(user)) {
        return null;
    }
    String password = user.getPassword();
    
//    盐值是ByteSource类型，
    ByteSource salt = ByteSource.Util.bytes(user.getSalt());
    /*
    第一个参数：返回给shiro的参数，可以用来授权
    第二个参数：密码，
    第三个参数: 盐值
    第四个参数：reaml的名字，在配置多个reaml时有用
    */
    return new SimpleAuthenticationInfo(username, password, salt,"");
}
```





### 权限使用

```
类名:doGetAuthorizationInfo
实质:权限是对url访问控制
```

```JAVA
@Override
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        
}
```

##### 权限注解

###### **权限概览**

```
RequiresAuthentication:当前Subject必须在当前session中已经过认证。

RequiresGuest:无需任何权限

RequiresPermissions:必须具备一定的权限

RequiresRoles:必须拥有某个角色，否则则方法不会执行还会抛出AuthorizationException异常。

RequiresUser:表示当前Subject已经身份验证或者通过记住我登录的

```

###### 开启注解

```java
###############手动添加包################
import org.apache.shiro.mgt.SecurityManager;
###############不然报错################
//在shiroConfig中添加两个Bean
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
 * @param securityManager
 * @return
 */
@Bean
public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
}

```



**权限使用**





##### 密码检验

```
类名:setCredentialsMatcher
```

###### 配置加密

```JAVA
@解析:对用户的密码进行校验
@使用:用户登录时使用

--->代码
@Override
public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//HashedCredentialsMatcher是shiro提供的解析盐的实现类
    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
    //采用的算法是md5
    matcher.setHashAlgorithmName("md5");
    //设置加密次数
    matcher.setHashIterations(1);
    super.setCredentialsMatcher(matcher);
}

```

###### 设置加密

```java
@解析:密码以MD5的方式存入数据库中
@使用:用户注册时使用该方法

public void setPassword(String name,User user) {
    //生成盐值
    String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
//从数据库获取密码
    String password = user.getPassword();
//对密码进行加密
//Md5Hash(密码，盐值，加密次数),加密次数需要和配置加密保持一致
    String ciphertext = new Md5Hash(password, salt, 3).toString();
    //将盐值添加到数据库
    user.setSalt(String.valueOf(salt));
    //将加密后的密码更新
    user.setPassword(ciphertext);
    //更新后，将加密的密码存储到数据库
    userDao.insertByName(name);
}
```

### ShiroConfig配置



