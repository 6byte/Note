## Git-基本操作

#### Git&Hub-基本命令

**命令大全**		https://www.jianshu.com/p/93318220cdce

#### Git&Hub-核心操作

```js
#初始化仓库
1.新建文件夹
2.进入文件夹，运行命令 git init

#向仓库中添加文件
add		:	git add index.html
commit  : 	git commit -m "提交描述"
```



#### Git&Hub-管理远程仓库

##### 克隆操作

```js
定义:下载远程仓库的项目
1.从远程仓库获取地址 github.com/yourAddress.com
2.git clone github.com/yourAddress.com
```

##### 发布到远程仓库

```
push:	将本地内容提交到远程仓库
1.严格按照 执行流程 操作==>add commit push
```

##### 操作远程仓库

```GIT
$ git remote
# 列出已经存在的远程仓库

# 列出	远程仓库的详细信息
$ git remote -v
$ git remote --verbose

# 添加	远程仓库
$ git remote add <远程仓库的别名> <远程仓库的URL地址>

# 修改	远程仓库UR地址
$ git remote set-url <远程仓库的别名> <新的远程仓库URL地址>

# 修改	远程仓库的别名
$ git remote rename <原远程仓库的别名> <新的别名>

# 删除	远程仓库
$ git remote remove <远程仓库的别名>
$ git remote rm <URL.git>

```

#### 提交全新项目

```
git init
git add .
git commit -m "info"
git remote add origin https://github.com/Yooven/game.git
git push -u origin master
```

#### 创建远程仓库

```
git remote add origin 远程仓库名称
git remote add origin https://github.com/6byte/game.git

```

#### 关联远程仓库

```
git remote set-url --add origin https://gitee.com/jinsx/oa.git
```

