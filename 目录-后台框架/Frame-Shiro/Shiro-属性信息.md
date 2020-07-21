## Shiro-属性信息

### Shiro-异常总览

```java
IncorrectCredentialsException             不正确的凭证
ExpiredCredentialsException               凭证过期
ConcurrentAccessException     并发访问异常（多个用户同时登录时抛出）
UnknownAccountException         未知的账号
ExcessiveAttemptsException      认证次数超过限制
DisabledAccountException        禁用的账号
LockedAccountException          账号被锁定
pportedTokenException           使用了不支持的Token
UnauthorizedException:     		不允许资源访问
UnauthenticatedException:		未认证就授权
```

### Shiro-过滤器

```sql
-- 概览
    认证过滤器:anon，authcBasic，auchc，user
    授权过滤器:perms，roles，ssl，rest，port
```

|       名称        |       解释       |
| :---------------: | :--------------: |
|       anon        |     完全开放     |
|       authc       |    登录可访问    |
|    authcBasic     |   http基本验证   |
|      logout       |       退出       |
| noSessionCreation |  不创建session   |
|       perms       |   指定权限访问   |
|       port        |   指定端口访问   |
|       rest        | 指定请求方式访问 |
|       roles       |   指定角色访问   |

