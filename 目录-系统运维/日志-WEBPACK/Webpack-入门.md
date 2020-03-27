## Webpack-入门

### 快速入门

安装

```
#	全局安装
cnpm install webpack webpack-cli –g

#	创建进入一个目录
cnpm install webpack

#	本地安装
在项目目录中安装webpack，其中，--save-dev是本地安装
cnpm install webpack webpack-cli --save-dev

#	检查
webpack -v

#初始化npm
npm init -y
```

编译第一个项目

```
1.	创建  src、public  文件夹
2.	创建  webpack.config.js文件  #打包配置文件
3.	
```

webpack.config说明

```js
mode
	1.参数：production,development,none
	2.作用：指定编译模式
	3.mode:'development',
entry
	1.指定需要编译文件的路径
    2.可以指定多个文件
    3.使用
        entry:{
        #index是打包后的文件名
            index:'./src/app.js',
        },
output
	1.配置打包后的文件
    2.使用
        output:{
        // 指定打包的文件名，是上面entry的index
        filename:'[name].js',
        //指定路径
        path:path.resolve(__dirname,'dist')
    }

```



package文件说明

```
name 				- 包名.
version 			- 包的版本号
description 		- 包的描述。
homepage 			- 包的官网URL。
author 				- 包的作者，是npmjs.org的账户名
contributors 		- 包的其他贡献者。
dependencies / devDependencies - 生产/开发环境依赖包列表
repository 			- 代码在git，svn的地址，Repo地址。
main 				- main 字段指定了程序的主入口文件
keywords 			- 关键字
```



