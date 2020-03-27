## ES6对象遍历



```JS

let object = {
    name: 'wang',
    age: 18
}

for (let item in object) {
    #	item相当于key
    #	Object[item]相当于遍历key所对应的value
    console.log(item)  			//  'name','age'
    console.log(object[item]) 	//	'wang',18
}
```

#### Object.key

```JS
#只遍历key
Object.keys(object) 	#	'name','age'
```

#### Object.values

```JS
#只遍历values
Object.values(object)	#	'wang',18
```

#### Object.entries

```js
#遍历值和对象
console.log(Object.entries(object)) //['name', 'wang'],['age', 18]
```

### Iterator

```js
定义:iterator是接口，为不同的数据结构提供统一的访问机制
```

#### 工作原理

1. ### 创建一个指针对象，指向数据结构的起始位置。

2. ### 第一次调用next方法，指针自动指向数据结构的第一个成员

3. ### 接下来不断调用next方法，指针会一直往后移动，直到指向最后一个成员

4. ### 每调用next方法返回的是一个包含value和done的对象，{value: 当前成员的值,done: 布尔值}

   - #### value表示当前成员的值，done对应的布尔值表示当前的数据的结构是否遍历结束。

   - #### 当遍历结束的时候返回的value值是undefined，done值为false

#### 具备接口的对象

```
Array
arguments
set容器
map容器
String
```

#### 特性

```
for..of..
具备接口的元素可用for of遍历
```



-  创建一个指针对象，指向数据结构的起始位置。
-  第一次调用next方法，指针自动指向数据结构的第一个成员
- 不断调用next方法，指针会往后移动，直到指向最后的成员
- ·每次调用next方法返回一个包含value和done的对象，{value: 当前成员的值,done: 布尔值}
- 