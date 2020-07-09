### JS数组处理

#### 数组概览

```js
查找	some find findIndex lastIndexOf
增加	fill，concat，push，unshift
遍历	every，foreach
其他  reverse，sort
删除	slice，splice,shift,pop
```



#### 数组转换

```
let arr = ["Apple","Bannana"]
1.判断是否为数组		Array.isArray(obj)
2.数组转字符串		 arr.toString()
3.字符串转数组		
	1.拆成单个字符	let newarr = Array.from(arr)
	2.按分隔符拆分	let newarr = arr.split(',')
```

#### 数值查找

##### find

```js
/* 
	功能:
		1.查找数组中符合条件的值
		2.如果有多个,返回最近的
	参数:回调函数作为判断条件
	返回值:
		Object:符合条件的值
*/
let boolean =  = [10,3,5,15,100,1].find(function(elem, index){    
    return elem>=15;
});

```

##### findIndexOf

```js
/* 
	功能:返回符合条件的值的索引
	参数:回调函数作为判断条件
	返回值:
		int:该数的索引
*/
var num = [10,3,5,15,100,1]
let index = findIndex(function(e){    // 15
    return e>=15;
});
```

#### 数组添加

##### fill

```JS
/*
    作用：初始化数组
    参数说明
        value：填充值。
        start：填充起始位置，可以省略
        end：填充结束位置，可以省略，实际结束位置是end-1
     返回:填充值后的数组
*/
const arr = []
arr.fill(7)
```

##### reverse

```js
快速实现倒序
var arr = [1,2,3,4,5]
arr.reverse()
```

##### concat

```JS
var arr = [1,2,3]
var arr1=[4,5,6]
arr.concat(arr1,[1,2])
```



#### 数组排序

```
sort排序:
返回负数:从小到大排序
返回正数:从大到小排序
```

#### 数组遍历

foreach

```JS
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





#### 数组删除

```JS
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
let arr = [0,0,0,0]
arr.slice(0,-1)
```



