## Webpack-文件说明

#### 详解-根配置

概览

```
webpack.config.js由五部分组成
entry output mode plugin module
mode
	1.参数：production,development,none
	2.作用：指定编译模式
	3.mode:'development',
entry
	1.指定需要编译文件的路径
    2.可以指定多个文件

output
	1.配置打包后的文件
    
plugin
	1.插件配置
    
module
	1.配置解析条件
```



#### 详解-entry

概览

```
三种解析方案:字符串,数组,对象
字符串:打包单个文件
数组：仅用于热更新
对象：打包多个文件
```

对象

```js
entry: {
  key1 : './index.js',
  key2 : './app.js'
},
#将index.js和app.js打包成key1.js,key2.js
```

字符串

```js
entry:'./index.js'
#仅打包index.js
```

组合

```js
entry: {
  index:[key1 : './index.js',key2 : './app.js'],
  key:'./value.js'
},
index:将app.js打包到index.js中
key:单独打包
```

#### 详解-module

```js
module:{
    rules:[
        {
        //匹配文件
            test:/\.css$/,
            //多个loader用use,单个loader用loader
            use:['style-loader','css-loader']
        },
        {
            test:'/\.js$/',
            //排除模块
            exclude:/node_modules/,
            //仅检查src下的文件
            include:resolve(_dirname,'src'),
            loader:'eslint-loader'
        }，
        {
        //以下配置仅会生效一个
        	oneOf:[]
        }
    ]
}
```

