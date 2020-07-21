## Webpack-入门

#### 快速入门

安装

```js
#	全局安装

cnpm install babel-loader babel babel-core css-loader style-loader  url-loader file-loader less-loader less html-webpack-plugin css-loader style-loader less webpack-plugin --save-dev 

#	初始化
cnpm init -y
cnpm install webpack webpack-cli –g

#	创建进入一个目录
cnpm install webpack

#	检查
webpack -v

```

#### 快速入手

###### 第一步-概览

```js
新建 
	index.html 
	index.js 

#非常重要
	webpack.config.js 	
```

###### 第二步-编译

```js
修改:webpack.config.js 
// webpack.config.js
const path = require('path')
module.exports = {
    // 入口：表示要使用webpack打包哪个文件
    entry: path.join(__dirname, 'index.js'),
    // 出口：输出文件的相关配置
    output: {
        // 指定打包好的文件，输出到哪个目录中去
        path: path.join(__dirname, './dist'),
        // 指定输出的文件名称
        filename: 'bundle.js'
    }
}
```

###### 第三步-执行

```
任意目录执行
webpack
```

###### package

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

#### 编译ES6

###### 第一步-安装

```
cnpm install babel-loader babel babel-core css-loader style-loader  url-loader file-loader less-loader less  --save-dev 


#	根据需要配置
cnpm install babel-polyfill --save
cnpm install @babel/runtime --save
```

###### 第二步-配置

```js

const path = require('path')
module.exports = {
    // 入口：表示要使用webpack打包哪个文件
    entry: path.join(__dirname, 'index.js'),
    // 出口：输出文件的相关配置
    output: {
        // 指定打包好的文件，输出到哪个目录中去
        path: path.join(__dirname, './dist'),
        // 指定输出的文件名称
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                // 正则检查所有的js文件
                test: /\.js$/,
                // 使用的babel-loader转换规则
                use: {
                    loader: 'babel-loader',
                    options: {}
                },
                // 排除不被应用编译
                exclude: '/node_modules/'
            }
        ]
    }
}
```





