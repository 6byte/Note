## Shiro-核心Api

### 概览

简单结构

```js
Subject					用户主体
SecurityManager			安全管理器
Reaml					数据管理
```

整体架构

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

##### 概览

```JAVA
理解:
	--	Shiro Subject代表了当前用户
	--	可以进行的操作:登录检查，权限检查，session控制，登出

获取Subject
    --	Subject subject = SecurityUtils.getSubject();
    --	Subject mySubject = new Subject.Builder().buildSubject();
```

##### 操作

```java
通过获取Subject能进行的操作

--	获取当前用户Session
	Session session = subject.getSession();

--	获取当前用户信息
	String currentUser = subject.getPrincipal().toString();

--	获取权限信息
	boolean isRole = subject.hasRole( "admin" );
    boolean isPer = subject.isPermitted("admin:add");

--	退出
	--   逻辑退出
    @RequestMapping("/user/logout")
        public void logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
    --	拦截器退出
    prevent.put("/logout","logout");
```



### Api-Reaml

##### 简单实现

```java
1.自定义Reaml必须继承	AuthorizingRealm
2.重写两个保护方法	
	doGetAuthenticationInfo();doGetAuthorizationInfo() ; 
```



##### 身份检验

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



##### 权限校验

```java
@Override
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
}
```



##### 权限注解

###### **权限概览**

```
@RequiresAuthentication:当前Subject必须在当前session中已经过认证。
@RequiresGuest:无需任何权限
@RequiresPermissions:必须具备一定的权限
@RequiresRoles:必须拥有某个角色，否则方法不执行
@RequiresUser:用户通过身份验证或通过记住我登录的

```

#### 安全

###### 配置加密

```JAVA
public class Shiro_Realm extends AuthorizingRealm {
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
    //HashedCredentialsMatcher是shiro提供的解析盐的实现类
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //采用的算法是md5
        matcher.setHashAlgorithmName("md5");
        //设置加密次数
        matcher.setHashIterations(3);
        super.setCredentialsMatcher(matcher);
    }
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





