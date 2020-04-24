#### ES5-应用组件

###### 禁止拖拽图片

```JS
function imgdragstart() {
	return false;
}
for (i in document.images) document.images[i].ondragstart = imgdragstart;

```



###### 禁用文本选择

```js
document.addEventListener('selectstart',function(e){
    e.preventDefault()
})
```

###### 阻止右键菜单

```JS
document.addEventListener('contextmenu',function(e){
    e.preventDefault()
})
```

###### 获取鼠标坐标

```JS
document.addEventListener('mousemove',function(e){
/*
	e.screenX 	: 鼠标相对屏幕左上角的坐标
	e.pageX		:鼠标相对于文档
*/
			console.log(e.screenX,e.screenY);
})
```

###### 复制粘贴

```js
--#html代码
//将要复制的内容必须加一个id属性
<span id="target">将要复制的内容</span>
//绑定的事件上，需要有	data-clipboard-target="#target"属性
<button class="btn" data-clipboard-target="#target" id="copy_btn">点击复制</button>  

--#JS代码
 $(document).ready(function(){
     //获取对象
        var clipboard = new Clipboard('#copy_btn');  
   
        clipboard.on('success', function(e) { 
            //清除选中状态
            e.clearSelection();       
        });    
    });
```



###### 获取时间

```
function writeCurrentDate() {
	var now = new Date();
	var year = now.getFullYear(); //得到年份
	var month = now.getMonth(); //得到月份
	var date = now.getDate(); //得到日期
	var day = now.getDay(); //得到周几
	var hour = now.getHours(); //得到小时
	var minu = now.getMinutes(); //得到分钟
	var sec = now.getSeconds(); //得到秒
	var MS = now.getMilliseconds(); //获取毫秒
	var week;
	month = month + 1;
	if (month < 10) month = "0" + month;
	if (date < 10) date = "0" + date;
	if (hour < 10) hour = "0" + hour;
	if (minu < 10) minu = "0" + minu;
	if (sec < 10) sec = "0" + sec;
	if (MS < 100) MS = "0" + MS;
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;

	//设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
	// var timer = setTimeout("writeCurrentDate()", 1000);
	return time;
}
```



标准注释

```JS
/**
* [函数名	作用]
* @param {类型} 参数名 		[作用]
* @param {类型} 参数名 		[作用]
* @return {返回类型}		 [作用]
* */
```

###### 全屏页面

进入全屏

```js
//控制全屏
function enterfullscreen() { //进入全屏
    $("#fullscreen").html("退出全屏");
    var docElm = document.documentElement;
    //W3C
    if(docElm.requestFullscreen) {
        docElm.requestFullscreen();
    }
    //FireFox
    else if(docElm.mozRequestFullScreen) {
        docElm.mozRequestFullScreen();
    }
    //Chrome等
    else if(docElm.webkitRequestFullScreen) {
        docElm.webkitRequestFullScreen();
    }
    //IE11
    else if(elem.msRequestFullscreen) {
        elem.msRequestFullscreen();
    }
}
```

退出全屏

````JS
function exitfullscreen() { //退出全屏
    $("#fullscreen").html("切换全屏");
    if(document.exitFullscreen) {
    document.exitFullscreen();
    } else if(document.mozCancelFullScreen) {
    document.mozCancelFullScreen();
    } else if(document.webkitCancelFullScreen) {
    document.webkitCancelFullScreen();
    } else if(document.msExitFullscreen) {
    document.msExitFullscreen();
    }
}

````

###### 操作Cookies

读取

```js
var username=document.cookie.split(";")[0].split("=")[1];
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
    	return unescape(arr[2]);
    else
    	return null;
}
```

写入

```JS
//写cookies
function setCookie(name,value)
{
    
    //设置过期时间
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    //写入，escape进行编码，unescape解码，
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
```

清空

```JS
function clearCookie(){
    var keys=document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
    for (var i = keys.length; i--;)
    document.cookie=keys[i]+'=0;expires=' + new Date( 0).toUTCString()
    }
} 
```

###### 节流防抖

防抖

```js
概念:
	1.防抖（debounce）在触发事件n秒内函数只能执行一次
	2.有两种实现方式
	
非立即执行：触发事件后延迟N秒执行

/**
 * @desc 函数防抖
 * @param func 函数
 * @param wait 延迟执行毫秒数
 * @param immediate true 表立即执行，false 表非立即执行
 */
function debounce(func,wait,immediate) {
    let timeout;

    return function () {
        let context = this;
        let args = arguments;

        if (timeout) clearTimeout(timeout);
        if (immediate) {
            var callNow = !timeout;
            timeout = setTimeout(() => {
                timeout = null;
            }, wait)
            if (callNow) func.apply(context, args)
        }
        else {
            timeout = setTimeout(function(){
                func.apply(context, args)
            }, wait);
        }
    }
}
```



节流

```js
概念:n 秒中只执行一次函数
有两种实现方式:间戳版和定时器版
区别：
	时间戳版：开始的时候触发函数，
    定时器版：的函数触发是在时间段内结束的时候。
/**
 * @desc 函数节流
 * @param func 函数
	 * @param wait 延迟执行毫秒数
 * @param type 1 表时间戳版，2 表定时器版
 */
function throttle(func, wait ,type) {
    if(type===1){
        let previous = 0;
    }else if(type===2){
        let timeout;
    }
    return function() {
        let context = this;
        let args = arguments;
        if(type===1){
            let now = Date.now();

            if (now - previous > wait) {
                func.apply(context, args);
                previous = now;
            }
        }else if(type===2){
            if (!timeout) {
                timeout = setTimeout(() => {
                    timeout = null;
                    func.apply(context, args)
                }, wait)
            }
        }
    }
}
```

###### 滚动条

```JS
window.onscroll = function() {
				//变量scrollTop是滚动条滚动时，距离顶部的距离
				var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				//变量windowHeight是可视区的高度
				var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
				//变量scrollHeight是滚动条的总高度
				var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
				//滚动条到底部的条件
				if (scrollTop + windowHeight == scrollHeight) {
					//写后台加载数据的函数
					this.show=!this.show
					alert('滚动到底部')
					console.log("距顶部" + scrollTop + "可视区高度" + windowHeight + "滚动条总高度" + scrollHeight);
				}
			}
```

###### 随机数生成

```js
生成m到n之间的随机数
function randH(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

var num = Math.floor(Math.random() * 10 + 1);
```



###### Animate特效

```CSS
var	animateClass = [{
    //animate滑入样式
	animateIn: [
		'bounce', 'flash', 'rubberBand', 'shake', 'wobble', 'tada',
		'bounceIn', 'bounceInDown', 'bounceInLeft', 'bounceInRight',
		'bounceInUp',
		'fadeIn', 'fadeInDown', 'fadeInDownBig',
		'fadeInLeft', 'fadeInLeftBig', 'fadeInRight', 'fadeInRightBig',
		'flip', 'flipInX', 'flipInY', 'lightSpeedIn',
		 'rotateIn', 'rotateInDownLeft', 'rotateInDownRight',
		'rotateInUpLeft', 'rotateInUpRight',
		'slideInDown', 'slideInLeft', 'slideInRight', 'rollIn',
		
	],
},
{
    //animate滑出样式
	animateOut: [
		'bounceOut', 'bounceOutLeft', 'bounceOutRight',
		'fadeOut', 'fadeOutDown', 'fadeOutDownBig', 'fadeOutLeft',
		'fadeOutLeftBig', 'fadeOutRight', 'fadeOutRightBig', 'flipOutX',
		'slideOutLeft', 'slideOutRight', 'slideOutUp',
		'rotateOutDownRight', 'rotateOutUpLeft', 'rotateOutUpRight',
		'rotateOut', 'rotateOutDownLeft', 'bounceOutUp', 'rollOut',
		'lightSpeedOut','flipOutY', 'hinge' ,'flipOutY','lightSpeedOut',
		'slideOutRight'
	]
}
		
		
	]
```

###### 数组操作

Filter应用

```JS
描述:删除对象中id = 2的一项
var goods = [
	{id:1,name:'books',price:12.2},
	{id:2,name:'pens',price:2.2},
	{id:3,name:'mouse',price:42.2},
	{id:4,name:'keyboard',price:62.2},
]

var newArr = goods.filter((i)=>{
	return id != i.id
})

描述:找到对象中id = 2的一项
var goods = [
	{id:1,name:'books',price:12.2},
	{id:2,name:'pens',price:2.2},
	{id:3,name:'mouse',price:42.2},
	{id:4,name:'keyboard',price:62.2},
]

var newArr = goods.filter((i)=>{
	return id == i.id
})
```

###### VUE分页

思路

```js
1.定义页号currentPage , 页大小pagesize

2.data中用一个数组存储服务器上的数据

3.用计算属性返回数组page_arrs，让页面展示数据

4.用slice()方法进行分割page_arrs；<核心>

5.通过按钮改变currentPage属性，完成分页逻辑
```





实现

```JS
<template>
  <div>
    <table class="table table-hover">
        //标题部分
      <thead>
        <tr>
            <th class="number">序号</th>
            <th>题目</th>
            <th class="del">删除</th>
        </tr>
      </thead>
      <tbody>
<tr v-for="(item,index) in page_arrs" :key="index">
            <th>{{index+1}}</th>
            <td>{{item.name}}</td>
<td>
            <a>删除</a>
</td>
</tr>
</tbody>
    </table>

<div>
      <button @click="primaryPage">首页</button>
      <button @click="prePage">上页</button>
//总数
      <button >{{current_page}}/{{Math.ceil(arrs.length/pagesize)}}</button>
      <button  @click="nextPage">下页</button>
      <button  @click="lastPage">尾页</button>
    </div></div>
</template>

<script>
export default {
  data() {
    return {
      arrs: [
        { name: "Otto", id: 1 },
        { name: "Jacob", id: 2 },
        { name: "Larry", id: 3 },
        { name: "Tim", id: 4 },
        { name: "Tom", id: 5 },
      ],
      currentPage: 1, //当前页号
      pagesize: 10 //每页大小
    };
  },
  computed: {
    page_arrs() {
      let { currentPage, pagesize } = this;
      // 返回
      return this.arrs.slice(
        (currentPage - 1) * pagesize,
        currentPage * pagesize
      );
    },
    current_page() {
      return this.currentPage;
    }
  },
  methods: {
    primaryPage() {
      this.currentPage = 1;
    },
    prePage() {
      if (this.currentPage == 1) {
        return;
      }
      this.currentPage = this.currentPage - 1;
    },
    nextPage() {
      if (this.currentPage == Math.ceil(this.arrs.length / this.pagesize)) {
        return;
      }
      this.currentPage = this.currentPage + 1;
    },
    lastPage() {
      this.currentPage = Math.ceil(this.arrs.length / this.pagesize);
    }
  }
};
</script>

<style scoped lang='less'>
</style>
```

###### 全屏滚动

判断鼠标滚轮

```JS
$(document).on("mousewheel DOMMouseScroll", function (e) {
        var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
                (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox
        if (delta > 0) {
            // 向上滚
            console.log("wheelup");

        } else if (delta < 0) {
            // 向下滚
             console.log("wheeldown");
        }
    });
```



