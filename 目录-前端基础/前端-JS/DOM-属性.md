## DOM-属性

### 文档属性

#### 焦点

```
document.activeElement	：	获取当前已聚焦的元素
```

#### 存储

cookies

```
document.cookie	:	获取所有的cookie
```

#### 获取引用

```
document.anchors
	作用：获取所有a标签
	注意：是一个集合
         a标签必须有name属性，否则获取不到
         
document.images:获取所有图片
```



#### 其他

```
document.doctype
    作用：	获取文档申明
    返回：	集合

document.title
	作用：	获取标题
	返回：	集合
```



文档状态

>获取状态:			document.readyState
>
>类型:					字符串
>
>状态说明
>
>- uninitialized - 还未开始载入
>- loading - 载入中
>- interactive - 已加载，文档与用户可以开始交互
>- complete - 载入完成



地址

```
document.baseURI
	作用:	获取完整地址
	类型:	字符串
	
document.domain
	作用:	仅获取域名
	类型:	字符串
	
document.scripts
	作用:	获取js标签
	类型:	集合
```



### 节点属性

#### 样式属性

```
e.style
	作用：读写样式属性
	返回	数组
		
className
	作用：	获取样式名称
	读写:	 可读写
```



>classList 
>
>- ​	获取元素的类名
>- ​    读写:只读
>- ​    说明:可通过add()和remove()方法修改样式
>
>代码
>
>```
>document.querySelector("tag").classList.add('red')
>document.querySelector("tag").classList.remove('red')
>```



#### 节点操作

```
e.childNodes
		作用：获取所有子节点
		返回：document数组
		
e.firstChild
        作用：获取第一个子节点
        返回：document对象	

e.lastChild
		作用：获取最后一个节点
		返回：document对象

e.previousSibling
		作用：获取上一个兄弟节点
		返回：document对象

e.nextSibling
		作用：获取下一个兄弟节点
		返回：document对象

e.parentNode
		作用：获取父节点
		返回：document对象
		
		
```

#### 属性获取

```
e.id
	作用：读写元素id
	返回：读写节点文本字符串

e.innerHTML
    作用：读写节点文本
	返回：字符串

e.nodeName
	作用:	获取节点名字
	返回:	字符串
		
e.nodeType
	作用:	获取节点类型
	返回:	字符串
		
e.tagName
	作用:	获取标签名字
	返回:	字符串
```

>e.nodeType 
>
>​	作用:返回节点类型
>
>​	值为1:代表元素节点
>
>​	值为2:代表文本节点，空格也是1
>
>​	值为3:代表注释节点

#### 滚动属性

```
e.scrollHeight
	作用:	返回整个元素的高度
	返回:	字符串
	
e.scrollWidth
	作用:	返回整个元素的宽度度
	返回:	字符串
	
e.scrollLeft
	作用:	返回元素左边与浏览器左边的距离
	返回:	字符串

e.scrollTop
	作用:	返回元素顶部与浏览器顶部的距离
	返回:	字符串
```

#### 偏移属性

```
e.offsetHeight
	作用:	返回元素的height+padding-top+padding-bottom
	返回:	字符串

e.offsetWidth
	作用:	返回元素的width+padding-left+padding-right
	返回:	字符串
	
e.offsetLeft
	作用:	返回元素的 left +padding-left+padding-right
	返回:	字符串

e.offsetTop
	作用:	返回元素的 top+padding-top+padding-bottom
	返回:	字符串

e.offsetParent
	作用:	返回父容器对象
	返回:	document对象
```



其他

```
e.accessKey
	作用:	设置快捷键
	读写:	可读写
	示例:	e.accessKey="g"
	说明:	给id为main的元素设定快捷键g，相当于点击操作

e.isContentEditable
	作用:	内容可编辑
	返回:	布尔
```

