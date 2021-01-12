### Vuepress 基本配置

下载配置目录文件 http://link

#### 配置package.json

```json
// 添加内容
  "scripts": {
    "dev": "vuepress dev docs",
    "build": "vuepress build docs"
  }
```

#### 配置主页

>位置:  	/docs/README.md

```
---
home: true
heroImage: /favicon.ico
actionText: 导航按钮 →
actionLink: /导航按钮对应的连接/
features:
- title: 标题
  details: 描述信息
- title: 第二标题
  details: 可以理解为栅栏结构
- title: 第三标题
  details: 描述信息
footer: 脚注信息
---
```

#### 配置Config

##### 上方导航配置

>/docs/.vuepress/.config.js

```js
module.exports = {
  themeConfig: {
    // 添加导航栏
    nav: [
        //   { text: "显示的标题", link: "/对应文件的路径" },
      {text:"vuepress",link:"/vuepress/vuepress"},
      {
        text: "显示的标题",
        // 下拉列表
        items: [{ 
            text: "子标题1",
            link: "子标题对应的连接",//该链接=本地 | 网络
          },],},
    ],
  },
};
```

#### 配置SideBar

```JS
module.exports = {
  themeConfig: {
    sidebar: {
        /**
        	vuepress : 对应文件夹
        	test 	 : 对应 vuepress/test.md
        	""		 : 对应 vuepress/README.md
        */
        '/vuepress/': [
          'test', 
   ],}},
};
```

