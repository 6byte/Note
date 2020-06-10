## JS-DOM

### style.width/height

```js
作用:		获取元素宽高
注意
1.仅对行内样式生效
2.获取的值带是带单位的字符串,100px
```

### offsetWidth/offsetHeight

```js
作用:		获取元素宽高
注意
1.只读属性，不能设置
2.获取的值是	数字
3.值
包括:		border
不包括:	margin,padding
```

### offsetParent

```
距离自身最近的父级
该父级必须有定位
```

### offsetLeft

```
1.获取在父容器的距离
2.从自身boder到父盒子padding左侧的距离
3.只读属性，返回数值
```

### style.left

```
1.不加定位无效
2.获取带单位的字符串
```

### scrollHeight/scrollWidth

```

```

### scrollTop/scrollLeft

```

```

