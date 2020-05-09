### Vuetify-教程

#### 常用属性

###### 参照

```vue
提示框：	    <v-alert>slot</v-alert>
头像：			<v-avatar>slot</v-avatar>
徽章:			 <v-badge></v-badge>
输入框:		<v-text-field></v-text-field>
卡片：			
			1.<v-card ></v-card>
			2.<v-card-text></v-card-text>
底部导航:
<v-bottom-navigation>
    <v-btn value="recent">
        <span>Recent</span>
        <v-icon>mdi-history</v-icon>
    </v-btn>
</v-bottom-navigation>
	
```



###### 文字

```js
背景颜色:颜色 亮度-值
		例如:red lighten-4
文字颜色:颜色--text text--亮度-值
		例如:red--text text--lighten-5//需要搭配使用
文字对齐:text-left,text-center,text-right
		支持响应式：text-sm-left
文字强调:text--primary,text--secondary,text--disabled
文字大小:
		.display-(4~1):很大
        .overline：很小
文字粗细
	    .font-weight-thin：很细
		.font-weight-bold：很粗
```

###### 边框

```
outlined:使背景透明并使用薄边框。
tail:删除组件的圆角边框。
elevation：去掉阴影
```



##### 

#### 栅格

```
fluid
```



#### 卡片

```
flat：移除边框阴影
disable:禁用
light/dark:主题颜色
loading：顶部进度条
shaped：设置月牙半角
```

#### 表单

###### 输入框

```js
1.进度条：loading
2.输入框下的提示信息
	hint="msg"
	messages="msg"
	error-messages="msg"
	error:错误提示
    hide-details：隐藏提示
3.特殊功能
	1.快速清除:clearable
     2.自动聚焦：autofocus
     3.进度条:loading
     4.进度条高度：loader-height=number
4.标签设置
	1.变大气：filled
    2.隐藏标签:single-line，
	3.限定输入:counter
5.添加图标
	1.输入框外添加：prepend-icon="mdi-home"
	2.输入框内添加：prepend-inner-icon="mdi-home"
	3.输入框后面追加:
6.卡片样式:
	1.边框圆角:rounded
	2.主题:dark|light
	3.去除padding :dense


```







#### 提示框

基本使用

```vue
<v-alert > 内容 </v-alert>
1.默认样式黑色
2.默认带图标
```

属性

```
dense：降低高度
dismissible：添加一个可以关闭的按钮
icon:添加图标
prominent：突出显示
```

#### 头像框

基本使用

```VUE
<v-avatar color="indigo" size="36">
    <span class="white--text headline">36</span>
</v-avatar>
```

属性

```
v-icon:添加图标
```

添加图片

```VUE
<v-avatar>
    <img
         src="https://cdn.vuetifyjs.com/images/john.jpg"
         alt="John"
         >
</v-avatar>
```

