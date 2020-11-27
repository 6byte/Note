## Git-入门介绍

### 总览

```js
git作用:将本地项目同步到Git服务器上
git和github区别:
	1.git是GitHub的同步工具
	2.GitHub是网页,GitHub上的代码都是通过git这个工具提交上来的
掌握以下基本概念:仓库，同步，关注，事务卡片
#掌握工作流程:工作区->暂存区->仓库->远程仓库
#使用:掌握Git仓库的增删改查
#熟练使用管理远程仓库
```

#### Linux安装Git

```
yum -y install git
```

#### 仓库配置

```js
#设置用户名
git config --global user.name "your name"
#设置邮箱
git config --global user.email "your email address" 

#初始化仓库
1.新建文件夹
2.进入文件夹，运行命令 git init

#向仓库中添加文件
add		:	git add index.html
commit  : 	git commit -m "提交描述"
```

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
	在/root/.gitconfig中添加
	[user]
        name = Yooven
        email = 444891953@qq.com
（5）完成上述步骤后，在执行push命令时，系统会要求使用密码再登录一次

(6)配置免密PUSH
git config  credential.helper store
```



