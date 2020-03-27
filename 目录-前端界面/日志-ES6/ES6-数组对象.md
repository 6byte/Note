### ES6-迭代函数

##### some()

```js
#some方法查找数组中是否有满足条件的元素
返回:		Boolean
var arr = [1, 2, 3, 4]
var result = arr.some(function(value) {
    return value == 9
})
console.log(result);
```

##### filter()

```js
#检查数组中符合条件的所有元素
返回:		一个符合条件的数组!不产生新的数组

var arr = [1, 2, 3, 4]
var result = arr.filter(function(value,index){
    return value > 2
})
console.log(result);
```

##### find()

```js
#查找符合条件的数
返回:		一个符合条件元素
var num = [10,3,5,15,100,1].find(function(elem, index){    
    return elem>=15;
});
console.log(num)     // 15
```

##### findIndex() 

```JS
#查找数组中第一个大于等于15的元素的位置
#返回索引
var num = [10,3,5,15,100,1].findIndex(function(e, i){    // 15
    return elem>=15;
});
```

##### lastIndexOf()

```js
 #方法返回指定元素,从数组的后面向前查找
 arr.lastIndexOf(searchElement[, fromIndex = arr.length - 1])
To be Continue
```

##### every()

```JS
#检查数组中每一项
返回:		Boolean
arr.every( function( item, index, array ){ 
	console.log( 'item=' + item + ',index='+index+',array='+array ); 
    return item > 3; 
```

##### fill(value, start, end)

```js
#作用：初始化数组
返回:		填充值后的数组
#参数说明
value：填充值。
start：填充起始位置，可以省略。
end：填充结束位置，可以省略，实际结束位置是end-1。

#举例
const arr1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
arr1.fill(7)
```

##### reverse

```js
#数组反转，对对象有用
返回:		一个反转后的数组
var arr = [1,2,3,4,5]
arr.reverse()
```

##### sort

```js
#对数组进行排序，可能使用了二分查找算法
返回：返回一个排序后的数组
 var arr = [1,2,3,4,5,6,7]
  console.log(arr.sort(function(i,e){
	  console.log(e,i);
	  return e>i
  }));
```

##### concat

```js
#连接数组，concat是可变长度数组，可以传多个参数
返回：		一个连接后的数组
  var arr = [1,2,3]
  var arr1=[4,5,6]
 console.log(arr.concat(arr1,[1,2]));
```

##### slice

```js

返回:		选定元素的新数组

函数原型：slice(n,m)

    仅有一个参数	删除前n+1个元素
    有n和m,删除n到m个元素
	当n为负数，反向删除元素
！该数超过数组最大长度，返回所有元素

    
情形一:单个参数

var arr = [1,2,3]
console.log(arr.slice(1));
从第二位开始截取数组，输出[2,3]数组

情形二:两个个参数
  var arr = [1,2,3,4]
  console.log(arr.slice(1,2));	
```

##### splice

```JS
#从数组中同时添加和删除项目，然后返回被删除的项目。
返回:被截取的的元素
特点:原数组有添加删除效果
函数原型:array.splice(index,howmany,item1,.....,itemX)
index:				删除的起始下标
howmany:			删除多少个，为0会全部删除
item1...:			需要添加的元素
var arr = ["apple","banana","pear","peach"]
arr.splice(1,2,"car")
//删除"banana","pear",并添加"car"
```

##### foreach

```js
#定义
array.foreach((数组当前项的值，数组当前的索引，数组对象本身)=>{})
array.foreach((e,i,array)=>{})
数组有多少个元素，就会被执行多少次

使用：
	1.可以对数组和对象使用
    2.为数组执行一次回调函数
```





##### 数组增删改查

```
push			#向数组末尾添加元素
pop				#从尾部删除元素
unshift			#向数组开头添加元素
shift			#向数组开头删除元素
```

### 对象遍历

##### for in

```

```




