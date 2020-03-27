## ES6-高级特性

### ES6-异步操作

```JS
#Promise使用
//1.定义一个返回Promise对象的函数
function getData(url, data) {
//使用res,rej两个参数接受返回的数据
    return new Promise((res, rej) => {
        $.ajax({
            url: host,
            data: data,
            //成功，用then处理返回的数据
            success: (data) => {res(data)},
            //错误，用catch处理错误结果
            error: (err) => {rej(err)}
        })
    })
}
var msg = {
    key: 'free',
    appid: 0,
    msg: 'hello'
}
getData(host, msg).then((data) => {
    console.log(data);
})
```

ES-5回调函数

```JS
#回调函数
1.定义原函数,并将要处理的值给回调函数
function main(callback){
    var data=10
    //这个callback== result
    callback(data)
    }
2.定义回调函数
function result(data){
    ...//进行数据处理
}
3.调用
main(result)
#封装Ajax请求
/**
		* [getData	封装Ajax]
		* @param {类型} url 		[目标地址]
		* @param {类型} data 		[将要传送的数据]
		* @param {类型} ret 		[接受到返回的数据]
		* @return {返回类型}		 [返回值]
* */
//参数callback接受的必须是一个函数
function getData(url,data,callback){
    $.ajax({
        url:host,
        data:msg,
        dataType:'json',
        success: (data) => {
            //回调函数处理数据
            callback(data)
        }
    })
}

    //需要发送的数据
var msg={
    key:'free',
    appid:0,
    msg:'hello'
}

//定义一个回调函数，用于处理接受到的参数
function ret(data){
    //对数据进行处理
    console.log(data);
}
    
    /*
    	@PARAM 第一个参数  	空值默认主机地址
    	@PARAM 第二个参数	需要发送的数据
    	@PARAM 第二个参数	回调函数处理返回结果
    */
getData(null,'你好',ret)


```

### 模块化

```
功能单一，职责明确
```

##### 导入导出

```js
#导出
let A = 123
function func(){..}
export default{
	A,func
}

#导入
import  module from "./module/chain.js"
export default {
    mounted:()=>{
        console.log(module.A,module.func);
    }
}
```

##### 单文件导入导出

```JS
#导出
export A = 123
export B = "value"

#导入
import * as module from "./module/ES6.js"
console.log(module.A)
```

