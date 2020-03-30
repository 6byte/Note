## Vue-基础语法

### Vue-基本指令

#### v:bind

说明:

```js
v:bind所绑定的属性，所对应的值是一个可以计算的表达式！
1.如果值没有带引号，当变量处理，有引号，当字符串处理
2.如果是变量，会去data中寻找数据，找不到就报错
3.渲染对象,键会被渲染成字符串，值会去data中搜索
EX:
#JS代码
data: function() {
    return {
        active:false,
        outer:'outer',
    }
}

#有	引号，会直接将'outer'当作字符串
<div :class="'outer'">
   示例文字
</div>


#没有	引号，将outer当作变量
<div :class="{outer:active}">
	示例文字
</div>

#渲染后:
<div class="outer"> v:bind </div>

```

#### v-if

```vue
作用:控制元素显示和隐藏
使用:接收一个Boolean值，控制元素显示或者隐藏

<div v-if=flag>A</div>
<div v-if=!flag>B</div>
//逻辑:A显示，B隐藏，A隐藏，B显示，相当于一个开关
```

#### v-on

```html
作用:给元素绑定一个事件
<div @click.prevent="func()"></div>
```

##### 事件修饰符

```
.stop 			阻止冒泡
.prevent 		阻止默认事件
.capture 		添加事件侦听器时使用事件捕获模式
.self 			只当事件在该元素本身（比如不是子元素）触发时触发回调
.once 			事件只触发一次
.passive		默认执行
```

##### 键盘修饰符

```
.enter			确定
.tab			TAB
.delete 		(捕获“删除”和“退格”键)
.esc			取消
.space			空格
.up				箭头上
.down			箭头下
.left			箭头左
.right			箭头右
```

##### 鼠标修饰符

```
.left			鼠标左键
.right			鼠标右键
.middle			鼠标中键
```

自定义修饰符

```
Vue.config.keyCodes.名称 = 按键值
```

##### 表单修饰符

```
.lazy		当input框失去焦点时触发
.number		自动将用户输入的值转换成数值类型
.trim		过滤首页空格
```





### Vue-基本函数

#### watch

```js
类型:对象
定义:		当监听的属性发生变化时，执行函数
使用:
export default {
 data: function() {
     return {value:''}
 },
 watch:{
     value:function(newValue,oldValue){...}}
     /*
			value:		watch监听的属性是data中的属性
			newValue:	改变后的值
			oldValue:	改变前的值
      */
 }
//频繁使用watch发送ajax请求会增加服务器压力
```

#### computed

```js
类型:对象
定义:返回计算后的属性，相当于一个特殊的属性

data: function() {
    return { value:''}
},
computed:{
    newValue:function(){
        return Number(this.value)+10
    }
}
```

#### mounted

```
类型:函数
#最重要的一个生命周期函数

```



### Vue-样式控制

#### 样式控制-Class

```js

说明:
1.预定义好CSS样式
2.通过v-bind属性控制Html元素与CSS样式绑定
3.一般用Boolean值控制元素显示隐藏

template:
//单个flag样式
<div :class="flag">1213</div>

script:
return {
    //仅控制单个样式
    flag:'style'
    //控制多个样式
    flag:{style:true,style1:false}
    
}

style:
.style{color: red;}
.style1{font-size:18px}
```

#### 样式控制-Style

```JS
方式一说明:
1.给变量color 赋值 'color:red'
2.将style变成表达式，再把color渲染给style
3.渲染结果：style="color:red;"
template:
<div :style="color">sytle</div>

script:
return {
    color:'color:red'
}

方式二说明
1.style绑定一个对象，对象的键会被渲染成字符串，值绑定color属性
2.渲染结果：style="color:red;"

template：
<div :style="{color:color}">sytle</div>

script：
return {
	color:'red'
}
```

### Vue-资源

参照网页	[https://cli.vuejs.org/zh/guide/html-and-static-assets.html#%E5%A4%84%E7%90%86%E9%9D%99%E6%80%81%E8%B5%84%E6%BA%90](https://cli.vuejs.org/zh/guide/html-and-static-assets.html#处理静态资源)

#### 加载资源

```js
1.文件夹说明
  1.public
	<1>该目录下的文件是不会被wabpack处理,它们会被直接复制到dist目录
	<2>必须使用绝对路径来引用文件
  2.assets
  	<1>文件会经过 webpack 打包，重新编译
	<2>动态绑定中，assets路径的图片会加载失败
	<3>通过require可以避免上面的错误
例如:
data (){
	return {
        asetUrl: require('../assets/logo.png'),
        sticUrl: '/static/logo.png'
    }
 
资源URL转化
 1.普通资源引入,如果以./开头，会被转换成require模块引入
	例如:url(./image.png) 被转化为 require('./image.png')
 2.转换规则
 	URL是绝对路径，如/images/foo.png，它不变
    URL以.开头，它会被解析成require模块导入
    
```





## Vue-进阶用法

### 组合-计算属性&For

```vue
方式
1.v-for最好绑定使用computed计算后的属性
2.computed需要返回一个计算后的对象，或者数组
3.使用filter时，注意函数this指向

<template>
	<div>
		<!-- V-for与Computed组合的关键 -->
		<div :style="{color:color}" v-for="index in stat">{{index.name+'-'+index.sex}}</div>
		
		<label for="man">男</label>
		<input type="radio" v-model="type" value="boy" name="man">
		<label for="man">女</label>
		<input type="radio" v-model="type" value="girl"  name="man">
		<label for="man">所有</label>
		<input type="radio" v-model="type"  value="all" name="man">
	</div>
</template>

<script>
export default {
	data: function() {
	return {
        user: [{name: '小明',sex: 'girl'},
               {name: '小白',sex: 'boy'},],
// 每次单击单选按钮，v-model可以动态获取值，并传给type
				type:'all',}
		},
computed:{
//返回一个重新计算后的对象给V-for
	stat:function(){
		if(this.type == 'all'){
			return this.user
}
	    if(this.type == 'boy' || this.type == 'girl'){
			return this.user.filter((v)=>{
// 将符合条件的元素返回，并且组成一个新的对象
			return v.sex == this.type
})}}}}
</script>
```



