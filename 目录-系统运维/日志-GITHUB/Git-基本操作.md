## Git-基本操作



#### Git&Hub-基本命令

**命令大全**		https://www.jianshu.com/p/93318220cdce

#### 初始化用户名

```
$ git config --global user.name "YOURNAME"
$ git config --global user.email "YOUREMAIL@XX.COM"
```

#### 添加到暂存区

```
git add *  //将工作区所有修改添加到暂存区
git add .  //将工作区所有修改添加到暂存区
git add filename //将指定文件添加到暂存区

git status  //列出变更文件
```

#### 添加到本地仓库

```
git commit -m '备注信息'
```

#### 添加到远程仓库

```

git remote add origin https://github.com/XXX.git
git push -u origin master


注意:以https 提交代码每次都要输入账号密码

解决方案
git remote rm origin   //移除
git remote add origin git@github.com:gitname/xx.git       //添加
```

#### 放弃暂存区修改

```
git checkout -- filename  //放弃暂存区修改
git rm --cached filename  //放弃add
git reset HEAD filename   //放弃add

git stash     //暂时放弃未提交的修改
git stash pop  //恢复
```

#### 分支操作

```
git branch     //所有本地分支
git branch -r  //所有远程分支
git branch -a  //所有远程分支和本地分支
```



#### 操作远程仓库

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

