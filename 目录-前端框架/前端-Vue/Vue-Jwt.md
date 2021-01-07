## Jwt详解

>构成:header,playload,signature

### Header

```
承载两部分信息
	声明类型：JWT
	声明加密算法：HMAC SHA256
	
格式:
    { 'typ': 'JWT','alg': 'HS256' }

对上面格式进行Base64加密:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
```

### Playload

>这部分内容可自定义添加

```
内容说明
    iss: 生成Jwt的人
    sub: 所面向的用户
    aud: 接收jwt的人	(谁登陆就签给谁)
    exp: jwt的过期时间
    nbf: 定义在什么时间之前，该jwt都是不可用的.
    iat: jwt的签发时间
    jti: 回避重放攻击,对应(id)
格式
	{"sub": "1234567890","name": "John Doe","admin": true}
	
对其base64加密：eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9
```

### Signature

```
header (base64后)
payload (base64后)
secret:唯一秘钥，保存在服务端

用上面三部分,结合header中提供的加密算法进行加密，生成字符串:TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```

### 生成

```
token = Header(base64加密).PlayLoad(base64加密).Signature
```

