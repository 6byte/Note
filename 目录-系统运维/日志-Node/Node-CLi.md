### 项目构造

#### 简单入门

安装依赖

```JS
创建并进入目录 test
//初始化
npm init -y
//下载命令编辑
npm install commander
//下载文件模块
npm install download-git-repo
```



编码

```js
1.创建文件index.js，并添加下面的代码

2.修改package.json,在"license"同级目录添加命令
 "bin": {
    "test": "index.js"
  }
解释:
    test:在cmd中执行的命令
    index.js:执行同级目录下index.js文件

5.将命令添加至全局
npm link
```

#### 参数设计

##### 使用

```js
创建index.js,并粘贴下列代码
#!/usr/bin/env node
/**
 * 流程
 *      1.获取用户输入命令
 *      2.根据用户命令执行不同操作
 * 
 */

const { program } = require('commander');
const download = require('download-git-repo')

const { url } = { url: 'http://github.com:6byte/Prescriptive-Linguistics#master'}
program
    .command('add')
    .action(function () {
        /* 
            download(github项目地址，保存路径+名称，)
            url格式:http://github.com:github名字/仓库名#分支
        */
        download(url, 'index', function (err) {
            console.log(err ? '失败' : '成功')
        })
    });
//解析
program.parse(process.argv);
```

##### 发包

```
1.修改
修改package.json中的name为发布到npm上的包名，别人通过该名下载
2.登录
npm login  
3.发布
npm publish
```

