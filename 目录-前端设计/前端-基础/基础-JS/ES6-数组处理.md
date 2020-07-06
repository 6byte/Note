### JS数组处理

##### 数值转换

```
let arr = ["Apple","Bannana"]
1.判断是否为数组		Array.isArray(obj)
2.数组转字符串		 arr.toString()
3.字符串转数组		
	1.拆成单个字符	let newarr = Array.from(arr)
	2.按分隔符拆分	let newarr = arr.split(',')
```

##### 数值查找

```
1.查找字符串，如果有多个会返回最相近的那个
let result = arr.find(e=>{
    return e == "str"
})
2.查找对象

```

##### 数组排序

```
sort排序:
返回负数:从小到大排序
返回正数:从大到小排序
```









