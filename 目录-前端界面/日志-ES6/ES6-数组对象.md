### ES6-迭代函数

##### some()

```js
/* 
    功能:查找数组中是否含有某个值
    参数:回调函数作为判断条件
    返回值:
        true:该值存在
        false:该值不存在
*/
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
/* 
	功能:查找数组中符合条件的值
	参数:回调函数作为判断条件
	返回值:
		Object:符合条件的值
*/
返回:		一个符合条件元素
var num = [10,3,5,15,100,1].find(function(elem, index){    
    return elem>=15;
});
console.log(num)     // 15
```

##### findIndex() 

```JS
/* 
	功能:查找数组中第一个大于等于15的元素的位置
	参数:回调函数作为判断条件
	返回值:
		int:该数的索引
*/
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
/* 
	模型:slice(n,m)
	功能:
		1.删除元素
	参数:
		(int)n:数组下标
		(int)m:数组下标
		说明:截取从n到m的数
	返回值:
		object[]：截取后的数组
	注意
		1.参数m可以是负数
		2.n>m,不能等于
*/

```

##### splice

```JS
/* 
	模型:函数原型:array.splice(index,howmany,item1...)
	功能:
		1.删除元素
		2.获得一个新数组
		3.添加元素
		4.替换元素
	参数:
		(int)index:			删除的起始下标
		(int)num:			删除多少个，为0会全部删除
		(string)item1:		需要添加的元素
	返回值:
		object[]：			一个新的数组
*/
var arr = ["apple","banana","pear","peach"]
arr.splice(1,2,"car")
//删除"banana","pear",并添加"car"
```

##### foreach

```js
/* 
	模型:array.foreach((e,i,arr)=>{})
	功能:
		1.迭代数组
	参数:
		(int)e:				数组当前项的值
		(int)i:				数组当前的索引
		(arr[])arr:			数组对象本身
	返回值:Null
*/
```





##### 数组增删改查

```
push			#向数组末尾添加元素
pop				#从尾部删除元素
unshift			#向数组开头添加元素
shift			#向数组开头删除元素
```

##### 对象遍历

```js
for (let item in object) {}

for (let o of obj) {}


遍历Key
Object.keys(object) 	

遍历values
Object.values(object)	

遍历值和对象
console.log(Object.entries(object))
```



##### Iterator

```js
定义:iterator是接口，为不同的数据结构提供统一的访问机制
```

##### 工作原理

```
1.  创建一个指针对象，指向数据结构的起始位置。

2.  第一次调用next方法，指针自动指向数据结构的第一个成员

3.  接下来不断调用next方法，指针会一直往后移动，直到指向最后一个成员

4.  每调用next方法返回的是一个包含value和done的对象，{value: 当前成员的值,done: 布尔值}

   - value表示当前成员的值，done对应的布尔值表示当前的数据的结构是否遍历结束。

   - 当遍历结束的时候返回的value值是undefined，done值为false
```



具备接口的对象

```
Array
arguments
set容器
map容器
String
```

##### for..of

```
1.具备接口的元素可用for of遍历
2.创建一个指针对象，指向数据结构的起始位置。
3.第一次调用next方法，指针自动指向数据结构的第一个成员
4.不断调用next方法，指针会往后移动，直到指向最后的成员
5.每次调用next方法返回一个包含value和done的对象，{value: 当前成员的值,done: 布尔值}
```




