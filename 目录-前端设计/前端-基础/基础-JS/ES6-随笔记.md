## ES-小技巧

#### 你应该知道的

优雅去掉小数点

```
console.log(~~ 1.5) //结果1
console.log(1.5 | 0)//结果1
```

用Map代替switch

```JS
function condition(){
    console.log('执行');
}
const map = new Map([
    [0,condition],
    [1,condition],
    [2,condition],
    [3,condition],
])
map.get(0)()&&map.get(0)()
```



#### 左值

```js

true && 10	//返回10
false && 10 //返回false

true || 10	//返回true
false || 10 //返回10

&& 两边同时都有值，并且都为真，一个为false就返回false

```

#### 好用的函数

```
Math.trunc(0.001) 去除小数部分 返回整数
Math.sign(0||1||-1)
```

实现

```JS
var leader = id.offsetLeft //获取当前位置
var step = 10  		//  步长
leader += step 		//  当前的位置加10
id.style.left = leader + 'px'
```



