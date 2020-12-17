## CSS-选择器

#### 概览

```
所有选择器:ID选择器，类选择器，标签选择器，群组选择器，包含选择器，属性选择器，兄弟选择器，伪类选择器，表单选择器,特殊选择器
```

#### 详解

##### 包含选择器

```
格式	：	div p{color:blue}
说明	:	 仅对div中的p标签生效
```

##### 属性选择器

```
概念	:	对具有某属性的标签进行样式控制
格式	:	input[attr]{color:blue}
		[attr]{color:blue}
```

选择器

>*=, |=, ^=, $=, *= 的区别
>[attr~=key] 		:	key必须是独立的单词
>[attribute*=key]	:	key出现即可
>[attribute^=key] 	：   必须以key开头

#### 表单选择器

```
概览：表单选择器是属性选择器的一种
```



#### 特殊选择器

>:root
>	匹配所有元素
>	优先级比*低

>:target	:	匹配a标签中href指向的id
>
>运行代码
>
>```CSS
><style>
>    div:target { background: red; }
></style>
><body>
>		<a href="#id">指向内容一</a><br>
>		<div id="id">内容</div>
></body>
>```
>
>