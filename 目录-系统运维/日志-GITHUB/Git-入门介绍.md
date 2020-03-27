## Git-安装







#### Linux使用Git

##### 下载

```
yum install -y git
```

##### 配置

```js
（1）设置用户名和email。
    git config --global user.name "Your Name"
    git config --global user.email "youremail@domain.com"
（2）创建SSH key
	ssh-keygen -t rsa -C "xxx@qq.com"

	#SSH地址是		/root/.ssh/id_rsa.pub
    
（3）将SSH密钥填入	
	https://github.com/settings/keys 

（4）配置账号密码
	在/.git/config中添加
	[user]
        name = Yooven
        email = 444891953@qq.com
（5）完成上述步骤后，在执行push命令时，系统会要求使用密码再登录一次
```



