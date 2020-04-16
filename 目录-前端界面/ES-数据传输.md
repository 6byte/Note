## ES-数据传输

### Vue-Axios

### ES-JSONP

### JWT

**概叙**

```
全称:JSON WEB TOKEN
作用:	权限认证/前后端分离/微信小程序/App开发
本质：JWT是一个生成安全Token密文的工具
过程：由服务器产生一个Token，客户端通过解析Token获取用户的信息
```

#### JWT-TOKEN

```
产生过程:
1.访问/登录服务器时，服务器生成一个token(随机字符串)
2.该字符串会返回给浏览器，并存储
3.再次访问服务器时，会携带该Token，服务器根据这个Token判断用户登录状态
4.用户每次发起请求，服务器都需要检查token
5.服务器可以在Redis或数据库中存储

```

#### JWT-结构

```
一段JWT:
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ

```





```
结构
由三部分组成：Header，Payload，Signature

解析
1.Header
承载两部分信息：token类型，加密算法。
支持的算法有：MD5、SHA、HAMC。

2.Payload
分三部分
	(1).标准中注册的声明
		iss: jwt签发者
        sub: 面向的用户(jwt所面向的用户)
        aud: 接收jwt的一方
        exp: 过期时间戳(jwt的过期时间，这个过期时间必须要大于签发时间)
        nbf: 定义在什么时间之前，该jwt都是不可用的.
        iat: jwt的签发时间
        jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
	(2).公共的声明
		公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.
	(3).私有的声明
		私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。

3.Signature
	1.将第1,2部分密文拼接生成字符串str1
	2.将str1再进行hs256加密+加盐生成str2
	3.将str2加密后，再做base64url加密
```



#### JWT-解析

```
从服务器获取Token
解析开始
1.对Token进行切割
2.对第二段进行base64url解密，并获取payload信息，检测Token是否超时
3.把第1，2部分进行HS256加密+加盐，再上传回服务器进行比对，获取验证结果

```

使用

