## Webpack-编译HTML

#### 配置

```js
const htmlPlugin = require('html-webpack-plugin')
plugins:[
//功能：默认创建一个空的HTML，自动引入打包输出的所有资源
//需要:需要有结构的html
	new htmlPlugin({
	//复制index.html
	//功能:找到index.html，并将资源复制进去
	//不指定，则新建一个空白的html，并将资源引入
        template:'index.html'
	})
]
```

#### 完整配置

```js
const htmlPlugin = require('html-webpack-plugin')
const { resolve } = require('path')

module.exports = {
    entry: resolve(__dirname, 'index.js'),
    output: {
        filename: 'our.js',
        path: resolve(__dirname, './dist')
    },
    module: {
        rules: [{
            test: /\.css$/,
            use: [
                'style-loader',
                'css-loader'
            ]
        },
        {
            test: /\.less$/,
            use: [
                'style-loader',
                'css-loader',
                'less-loader',
            ]
        }
        ]
    },
    plugins:[
        new htmlPlugin({
            template:'index.html'
        })
    ],
    mode:'development'
}
```

