## Git-多人开发

https://blog.csdn.net/wangliang888888/article/details/80536277

### 新项目

如果已经存在本地仓库

```css
git remote add origin https://github.com/6byte/test1.git

git branch -M main

git push -u origin main
```

不存在本地仓库

```CSS
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/6byte/test1.git
git push -u origin main
```



### 分支操作

```
#查看
git branch

#添加
git branch '分支名'

#切换
git checkout '分支名'

#删除本地分支
git branch -d '分支名'
#删除远程分支
git push origin --delete '分支名'

```

### 提交

```
#分支合并
git checkout '主分支名'
git merge '子分支名'
//子分支文件将合并到主分支目录下
```

