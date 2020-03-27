## Electron-入门

### 安装启动

```
cnpm install --save-dev electron
```

第一个应用

初始化

```
选择一个目录初始化
npm init
```

创建main.js

```js
const { app, BrowserWindow } = require('electron')

function createWindow () {   
  // 创建浏览器窗口
  let win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      nodeIntegration: true
    }
  })

  // 加载index.html文件
  win.loadFile('index.html')
}

app.whenReady().then(createWindow)

```

创建index.html

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Hello World!</title>

    <meta http-equiv="Content-Security-Policy" content="script-src 'self' 'unsafe-inline';" />
  </head>
  <body>
    <h1>Hello World!</h1>
    
    Hello World!
    
  </body>
</html>

```

启动

```
在当前目录下
npm start
```

### 入门介绍

```
Electron分渲染进程和主进程
	1.	渲染进程:渲染WEB界面包括html,js,css
	2.	主进程：main.js,只有一个主进程
```

