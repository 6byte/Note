## Vue-MvvM

概览

```
核心实现:defineProperties()方法
关键实现:脏值检测，发布订阅
```



#### defineProperty

概览

```js
作用
	1.为对象添加属性
    2.返回修改属性后的对象
    3.限制修改对象属性
定义
	Object.defineProperty(a,"key",props)
```

porps的值

```
configurable	是否能被删除和修改
enumerable	 	是否能被遍历
value			赋给属性的值
writable		是否能被覆盖
get				获取属性值,在获取值的时候触发
set				设置属性值,在设置值的时候触发
```

使用

```

```



