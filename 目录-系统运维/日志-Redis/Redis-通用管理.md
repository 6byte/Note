### Redis-通用管理

#### 管理-密码

```
命令行
设置密码
config set reqiurepass "passwd"

获取密码
config get requirepass

登录
auth "passwd"
```

#### 管理-连接

```

# 指定端口
    port 6379
# 指定地址
    bind 127.0.0.1
# 指定连接空闲时间.
        # 如果超时将会关闭连接
        # 如果为0,表示永不超时.
    timeout 0
# 指定TCP连接是否为长连接,将增加开支
  	#默认为0，禁用。非0值表示开启;
  	#client端socket也可以通过配置keepalive选项,开启"长连接"
    tcp-keepalive 0

```

