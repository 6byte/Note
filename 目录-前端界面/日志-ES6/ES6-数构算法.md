## ES6-数构算法

网页参照:	<https://www.jianshu.com/p/16fc1ea4fc73>

### 总览

```
1.主要的数据结构有
Set,WaekSet,Map,WeakMap

2.区别
<1>Set：无序，唯一无重复，
<2>WeakSet:
		(1)成员只能是对象
		(2)垃圾回收机制会优先回收该对象所占用的内存
		(3)功能与Set一样
<3>Map:键值对，采用数组+链表的方式进行存储
<4>WeakMap:
		(1)垃圾回收机制会优先回收该对象所占用的内存
		(2)与Map功能一样
```

### ES6-数构

#### Map



```js
增
set()
var map = new Map()
map.set("value","value")

查
get():返回一个值
var val = map.get("value")
console.log(val);//value

has:返回Boolean值
var val = map.has("value")
console.log(val);//true

删
delete():
map.delete("value")
```

#### Set

```js
增
add():返回一个Set对象
set.add("value")

查
has():返回一个Boolean值

改
通过forEach修改
set.forEach(e=>{
    if(e == "as"){
    console.log("true");
    }

    else{
    console.log('-');
    }
})
```



### ES6-算法

#### 数组推移

```js
$ 描述:类似懒加载实现，每次点击按钮加载一部分图片

html
<button type="button">点击加载</button>
<div>
    <img>
</div>


	<script type="text/javascript">
//起点位置
let start = 0
//每次点击需要加载数据的数量
let length = 5
//记录鼠标当前已经偏移了的位置
let d = start*length
function doSome(e) {
    
	for (i = start * length; i < length * (start + 1) && d< e.length; i++) {
		a = host + e[d++].slice(5)
		$('div').append(`<img src="${a}">`)
	}
}
</script>
```

