## CSS-效果

#### CSS-边框阴影

```CSS
/*定义:box-shadow: 水平阴影 垂直阴影 模糊像素大小 spread 颜色 inset;*/
div{ box-shadow: 10px 10px 5px #888888;}
```

#### CSS-边框背景

```CSS
//直接用
#border { 
    border-image: url(border.png) 10 round;
}
```

![img](https://www.runoob.com/wp-content/uploads/2013/08/241f95cad1c8a786fa8afe6f6109c93d70cf502a.gif)

### 渐变

##### 线性渐变

```css
background:linear-gradient(方向,颜色 0%,颜色...);
background:linear-gradient(to right ,red 0%,yellow,blue);
方向属性
	to top     :	0
	to right   :	90
	to bottom  :	180默认
	to left    :	270
```

##### 径向渐变

```CSS
background: radial-gradient(位置，颜色...);
background: radial-gradient(at bottom,red,blue);

```



### Web字体和图标

```css
1.导入文件
2.先申明
@font-face {
    font-family: "free";
    src: url('./font/free.ttf');
    font-weight:weight
}
3.后使用
div {
    font-family: "free";
    font-size: 28px;
}

```

### 



