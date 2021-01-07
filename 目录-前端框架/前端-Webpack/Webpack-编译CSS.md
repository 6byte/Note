### Webpack-CSS打包

#### 概览

```
通过配置module解析css

```

#### 入门

```JS
module: {rules:[{
    //test的值千万别加引号
            test:/\.css$/,
    //useloader执行顺序： 从右->左，从下->上
            use:[
                'style-loader',
                //将CSS编译成commjs模块加载到JS中
                //内容:样式字符串
                'css-loader'
            ]
}]}

```

完整配置

```JS
const {resolve} = require('path')
module.exports = {
    entry: resolve(__dirname, 'index.js'),
    output: {
        path: resolve(__dirname, './dist'),
        filename: 'my.js'
    },

    module: {
        rules:[
            {
                test:'/\.css$/',
                use:[
                    'style-loader',
                    //将CSS编译成commjs模块加载到JS中
                    //内容:样式字符串
                    'css-loader'
                ]
            }
        ]
    },
}
```

#### 解析less

```
说明:通过多个test正则匹配不同类型的文件
 module: {rules:[{
                test:/\.less$/,
                use:[
                //注意顺序,less->css->style
                    'style-loader',
                    'css-loader',
                    'less-loader',
                ]
            }
        ]
    },
```

