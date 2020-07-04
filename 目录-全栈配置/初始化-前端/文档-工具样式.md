## CSS-组件复用

### 全屏背景

```css
思路:使用一个置底的绝对定位div实现全屏背景
.bgimg{
    position: fixed;
    z-index: -100;
    background-image: url(img/bgimg.jpg);
    filter: blur(1px);
    width: 100%;
    height: 100%;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
}

线性渐变背景

 background: linear-gradient(
    145deg,
    #0e4174 40%,
    #177abb 0,
    #177abb 80%,
    white 0
  );
```

### 文字换行

参照网页		https://blog.csdn.net/weixin_45761317/article/details/103092793

概览

```
换行种类:多行换行,单行换行,不换行,自动换行,强制换行
```

```css
多行换行：
效果:会显示多行，超出部分不显示
.wrap {
    overflow: hidden;
    display: -webkit-box;  
    -webkit-box-orient: vertical;   
    -webkit-line-clamp: 3;   
    word-break: break-all;   
}

单行换行：
效果：只会显示一行，超出部分显示省略号
.wrap {
    overflow:hidden;
    text-overflow:ellipsis;
    white-space:nowrap;
} 

不换行
.wrap {
  white-space:nowrap;
}

自动换行
.wrap {
  word-wrap: break-word;
  word-break: normal;
}

强制换行
.wrap {
  word-break:break-all;
}

```

### 小技巧

```
使用硬件加速
 transform: translateZ(0);
 
 
/* 每个元素中的回车都会被识别 */
 
 body {
  	white-space: pre-line;
}

修改光标颜色
input{
    color:  #fff;
    caret-color: red;
}

修改placeholder字体

input::-webkit-input-placeholder {
    color: #ff55ff;
}

input:-moz-placeholder {
    color: #ff55ff;
}

input::-moz-placeholder {
    color: #ffaaff;
}

input:-ms-input-placeholder {
    color: #5555ff;
}
```

### 三角形

```
向上的三角形
原理:border是一个梯形结构，
	把宽度去掉，只剩下border，
	

.wrap { 
  border-color: transparent transparent green transparent; 
  border-style: solid; 
  border-width: 0px 300px 300px 300px; 
  height: 0px; 
  width: 0px; 
}
```

### 禁止选中文本

```CSS
 body{-webkit-touch-callout: none; /* iOS Safari */
-webkit-user-select: none; /* Chrome/Safari/Opera */
-khtml-user-select: none; /* Konqueror */
-moz-user-select: none; /* Firefox */
-ms-user-select: none; /* Internet Explorer/Edge */
user-select: none; /* Non-prefixed version, currently
not supported by any browser */}
```

### 鼠标样式

```
1.必须是ico文件
2.小于28px  *  28px
body{
	cursor: url('img/mouse.ico'), default;//需要严格按照这个样式
}

```

### 自定义滚动条

```CSS
/* 自定义滚动条 */
#message::-webkit-scrollbar {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: #535353;
}
#message::-webkit-scrollbar {
    /*滚动条整体样式*/
    width: 10px;
    /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
}
#message::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: #535353;
}
#message::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
    background: #EDEDED;
}

```

### 移动端禁止双击放大

```CSS
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
```

### 常用内容结构

```
电梯导航、手风琴、横向手风琴、
```

### 底部导航栏<完美版>

```html
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title></title>
		<style type="text/css">
			.acv,
			.acv ul,
			body,
			html {
				width: 100%;
				height: 100%;
				position: fixed;
				margin: 0;
				padding: 0;
			}

			.acv ul {
				display: flex;
				flex-direction: column;
				list-style: none;
			}

			.acv ul li:nth-of-type(1) {
				background-color: red;
				flex: 1;
			}

			.acv ul li:nth-of-type(2) {
				flex: 8;
				overflow: scroll;
			}

			.acv ul li:nth-of-type(3) {
				background-color: blue;
				flex: 1;
				max-height: 60px;
			}
		</style>
	</head>
	<body>
		<div class="acv">
			<ul>
				<li>
					顶部导航
				</li>
				<li>
					内容
				</li>
				<li>
					底部导航
				</li>
			</ul>
		</div>
	</body>
</html>

```

### 隐藏滚动条

```
.select::-webkit-scrollbar {display:none}
```

### 居中对齐

全屏居中

```css
.center{
    position: absolute;
    top: calc(50% - 50px);
    left: calc(50% - 50px);
    min-width: 100px;
}
```

文字垂直居中

```css
子元素垂直居中对齐
.center {
    display: flex;
    align-items: center;
    justify-content: center;
}

```

