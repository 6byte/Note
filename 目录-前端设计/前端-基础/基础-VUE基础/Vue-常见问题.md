## Vue-常见问题

##### 加载assets图片

```js
使用require
data:function(){
	imgsrc: require('@/assets/card.png')
}

不能使用一些变量，如
data:function(){
	imgsrc: require('@/assets/'+this.weapon[0]+'card.png')
}
```

##### 导航路由无限追加

```js
现象：http://192.168.0.54:8080/func/index/download/index
原因:	to没有加"/"
错误	<b-link to="home">跳转</b-link>
正确	<b-link to="/home">跳转</b-link>
```

##### VUE使用图标

```
 <link rel="shortcut icon" href="favicon.ico">
 一定要加上shortcut
 图标名:favicon
 放在public/index.html同级即可
```

