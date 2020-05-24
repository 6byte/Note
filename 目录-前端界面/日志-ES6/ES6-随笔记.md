## ES-小技巧

#### 左值

```js

true && 10	//返回10
false && 10 //返回false

true || 10	//返回true
false || 10 //返回10

&& 两边同时都有值，并且都为真，一个为false就返回false

```

#### call

<https://blog.csdn.net/kiven_wolf/article/details/79272370>

概念

```
1.函数中的this总是指向它的调用者
2.apply和call用于执行函数
3.方法A和对象B,A借助Call调用B的内容
```

使用

```js
函数A.call(对象B,值..)
函数A调用对象B中的内容

function A() {
	console.log(this.value)//指向B
}
var B = {
	value: "B",
};
A.call(B);//b
A调用B中的food
```



#### 好用的函数

```
Math.trunc(0.001) 去除小数部分 返回整数
Math.sign(0||1||-1)
```

#### proxy

概念

```
作用
	1.拦截，过滤对象
语法
	let p = new Proxy(target, handler);
		target ：目标对象，任意对象，甚至另一个代理
		handler: 处理目标对象的方法
```

使用

```JS
let test = {
    name: "小红"
};
test = new Proxy(test, {
    get(target, key) {
		/*
			target：test自身
			key：键
			return:如果不返回值,将获取不到任何对象
        */
        return target[key];
    },
    set(target,key,value){
		/*
			target：test自身
			key：键
			value:被设置的值，不设置，
				  test.name = "小黑"语句将不起作用
        */
        target[key] = value
    }
});
test.name = "小黑"
console.log(test.name);
```

