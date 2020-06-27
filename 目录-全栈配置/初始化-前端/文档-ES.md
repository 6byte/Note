#### ES5-应用组件



###### 页面基础事件

禁止拖拽图片

```JS
function imgdragstart() {
	return false;
}
for (i in document.images) document.images[i].ondragstart = imgdragstart;
```

禁用文本选择

```js
document.addEventListener('selectstart',function(e){
    e.preventDefault()
})
```

阻止右键菜单

```JS
document.addEventListener('contextmenu',function(e){
    e.preventDefault()
})

window.oncontextmenu=function(e){
    //取消默认的浏览器自带右键 很重要！！
    e.preventDefault();
}
```

获取鼠标坐标

```JS
document.addEventListener('mousemove',function(e){
/*
	e.screenX 	: 鼠标相对屏幕左上角的坐标
	e.pageX		:鼠标相对于文档
*/
			console.log(e.screenX,e.screenY);
})
```

##### 滚动效果

滚动条到底部

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

获取滚动事件

````js
 function scroll() {
        if(window.pageYOffset != null) // ie9+ 和其他浏览器
        {
            return {
                left: window.pageXOffset,
                top: window.pageYOffset
            }
        }
        else if(document.compatMode == "CSS1Compat") // 声明的了 DTD
          // 检测是不是怪异模式的浏览器 -- 就是没有 声明<!DOCTYPE html>
        {
            return {
                left: document.documentElement.scrollLeft,
                top: document.documentElement.scrollTop
            }
        }
        return { // 剩下的肯定是怪异模式的
            left: document.body.scrollLeft,
            top: document.body.scrollTop
        }
    }
````



滚动特效

```JS
$("select").click(function() {
    $("html, body").animate({
      scrollTop: $($(this).attr("href")).offset().top + "px"
    }, {
      duration: 500,
      easing: "swing"
    });
    return false;
  });
  
$('select').click(function() {
    $('html,body').animate({ scrollTop: 			  $("#history").offset().top - 100 }, 200)
});
```

鼠标滚轮

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

滚动监听

```JS
/*
	功能:
		处理兼容性问题
		获取在Y轴上滚动的距离
	返回值:
		(int)scrollTop:滚动条距离顶部的距离
*/
getScrollTop() {

    var scrollTop = 0,
        bodyScrollTop = 0,
        documentScrollTop = 0;
    if (document.body) {
        bodyScrollTop = document.body.scrollTop;
    }
    if (document.documentElement) {
        documentScrollTop = document.documentElement.scrollTop;
    }
    scrollTop = bodyScrollTop - documentScrollTop > 0 ?
        bodyScrollTop :
    documentScrollTop;
    return scrollTop;
}

 /*
	功能:
		处理兼容性问题
		获取浏览器窗口当前高度
	返回值:
		(int)scrollTop:滚动条距离顶部的距离
*/
    getHeight() {
        var scrollHeight = 0,
            bodyScrollHeight = 0,
            documentScrollHeight = 0;

        if (document.body) {
            bodyScrollHeight = document.body.scrollHeight;
        }

        if (document.documentElement) {
            documentScrollHeight = document.documentElement.scrollHeight;
        }

        Height =
            bodyScrollHeight - documentScrollHeight > 0 ?
            bodyScrollHeight :
        documentScrollHeight;

        return Height;
    }

        //浏览器视口的高度

getWindowHeight() {

   var windowHeight = 0;
   if (document.compatMode == "CSS1Compat") {
      windowHeight = document.documentElement.clientHeight;
   } else {
      windowHeight = document.body.clientHeight;
   }
	return windowHeight;
   }

```



###### 获取中文时间

```js
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
	],},
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
	]}]
```

###### 数组操作

Filter应用

```JS
描述:删除对象中id = 2的一项
var goods = [
	{id:1,name:'books',price:12.2},
	{id:2,name:'pens',price:2.2},
	{id:3,name:'mouse',price:42.2},
	{id:4,name:'keyboard',price:62.2},]

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
var newArr = goods.filter((i)=>{return id == i.id})
```

###### 前端分页

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
  <div><table class="table table-hover">
        //标题部分
      <thead><tr>
            <th class="number">序号</th>
            <th>题目</th>
            <th class="del">删除</th>
        </tr></thead>
      <tbody>
<tr v-for="(item,index) in page_arrs" :key="index">
            <th>{{index+1}}</th>
            <td>{{item.name}}</td><td><a>删除</a>
</td></tr></tbody>
</table>
/*
	功能:点击翻页
*/

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

<style scoped lang='less'></style>
```

原生JS分页

```JS
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





###### 处理非法字符

正则

```JS
var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥&*（）——|{}【】‘；：”“'。，、？]")
```

判断

```JS
/**
	功能：
		功能1:判断是否含有非法字符串
		功能2:删除所有非法字符
	参数:
		参数1:str:需要处理的字符串
		
	返回值:
    	result.str:过滤后的字符串
		result.status:是否含有非法字符
*/
function strFilter(str) {
    let result = {
        str:'',
        status:false
    }
    var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]")
    var rs = "";
    for (var i = 0; i < str.length; i++) {
        if(str.substr(i, 1).match(pattern) != null){
            result.status = true
        }
        rs = rs + str.substr(i, 1).replace(pattern, '');
    }
    result.str=rs
    return result;
}
```

##### 文字处理

###### 高亮文字

```JS
/**
函数描述
	功能:高亮关键字
	参数:
		参数一(keyword):需要高亮的关键字
		参数二(selector):输入选择器，如#id
			ex:highLight(..,"#select",...),#select下的内容会被高亮
		参数三(style):一个css的class，暂时不支持style
	返回值:Null
*/
function highLight(keyword,selector,style) {
    const content = document.getElementsByTagName(selector)[0];
    const text = content.innerHTML;
    const string = text.split(keyword);
    content.innerHTML = string.join(`<span class="${style}">` + keyword + '</span>');
}
```

###### 字符转换

转Unicode

```JS
/**
	功能:把中文转换成Unicode
	参数一：需要转换的字符串
	返回值:
		result.value:
			描述:成功返回的值，失败为空
			类型:字符串
		result.status:
			描述:转换状态,true--成功,false--失败
			类型:Boolean
*/
function toUnicode(string) {
    let result = {
        value:null,
        status:false
    }
    var str = '';
    if (string == ''){
        result.status = false
        return result;
    }
    else{
        for (var i = 0; i < datastringlength; i++) {
            str += "\\u" + parseInt(string[i].charCodeAt(0), 10).toString(16);
        }
        result.value = str
        result.status = true
    }
    return result;
}
```

###### 转中文

```JS
/**
	功能:把Unicode转换成中文
	参数一：需要转换的Unicode
	返回值:
		result.value:
			描述:成功返回的值，失败为空
			类型:字符串
		result.status:
			描述:转换状态,true--成功,false--失败
			类型:Boolean
*/
function toChinese(string) {
    let result = {
        value:null,
        status:false
    }
    string = string.split("\\u");
    var str = '';
    if (string == ''){
        result.status = false
        return result
    };
    else{
        for (var i = 0; i < string.length; i++) {
            str += String.fromCharCode(parseInt(string[i], 16).toString(10));
        }
        result.value = str
        result.status = true
        return result;
    }
}
```

###### 中文转二进制

```JS
//功能:将中文转换成二进制
//返回:二进制0101
function getCharBinary(str) {
    var i = 0;
    var result = "";
    while (true) {
        var temp = str.charCodeAt(i++);
        if (temp) {
            result += temp.toString(2);
        } 
        else {
            break;
        }
    }
    return result;
}
```



##### BOM事件

###### 监听窗口改变

```JS
window.onresize = function(){}
```

###### 倒计时

```JS
/*
	功能:实现倒计时,返回从year到现在的时间差
	参数:
		(number)year:从未来的某一年开始倒计时
				要求:比现在的年份要大
	返回值:
		str:倒计时的时分秒
*/
function counter(year) {
    var date = new Date();
    var year = date.getFullYear();
    var date2 = new Date(year, 11, 30, 23, 59, 59);
    /*转换成秒*/
    var time = (date2 - date) / 1000;
    var day = Math.floor(time / (24 * 60 * 60))
    var hour = Math.floor(time % (24 * 60 * 60) / (60 * 60))
    var minute = Math.floor(time % (24 * 60 * 60) % (60 * 60) / 60);
    var second = Math.floor(time % (24 * 60 * 60) % (60 * 60) % 60);
    var str = 2021 + "年还剩" + day + "天" + hour + "时" + minute + "分" + second + "秒";
    return str
}
window.setInterval(counter, 1000);
```

###### 点击复制

思路

```
1.将textarea获取input的样式去掉
2.通过execCommand()获取input中的值
```

实现

```HTML
<head>
	<style type="text/css">
        /*都是必须的的*/
	   .wrapper {position: relative;}
	   textarea {position: absolute;top: 0;left: 0;opacity: 0;}
	</style>
</head>
<body>
    <div class="wrapper">
       <p id="text">我是需要复制的文本内容</p>
       <textarea id="input">这是幕后黑手</textarea>
       <button onclick="copyText()">copy<tton>
    </div>
</body>
<script type="text/javascript">
/* 
   函数名:copyText
	 功能:实现复制
	 逻辑:
	 	1.获取p标签中的内容
	 	2.获取输入框中的内容
	 	3.将P标签的内容传给输入框
	 	4.选中文本
	 	5.复制
	 解释:execCommand()命令只能复制输入框中的内容
*/
	function copyText() {
        var text = document.getElementById("text").innerHTML;
        var input = document.getElementById("input");
        input.value = text;
        input.select();//选中文本
        document.execCommand("copy");
    }
</script>
```

###### 全屏特效

```JS
/*
	功能:全屏兼容性调用方案
	参数:
		ele:传入的参数值
	注意:全屏必须通过用户点击才能调用
*/
function full(ele) {
    if (ele.requestFullscreen) {
        ele.requestFullscreen();
    } else if (ele.mozRequestFullScreen) {
        ele.mozRequestFullScreen();
    } else if (ele.webkitRequestFullscreen) {
        ele.webkitRequestFullscreen();
    } else if (ele.msRequestFullscreen) {
        ele.msRequestFullscreen();
    }
}

 //退出全屏
function exitfullscreen(ele) {
    if(ele.exitFullscreen) {
        ele.exitFullscreen();
    } else if(ele.mozCancelFullScreen) {
        ele.mozCancelFullScreen();
    } else if(ele.webkitCancelFullScreen) {
        ele.webkitCancelFullScreen();
    } else if(ele.msExitFullscreen) {
        ele.msExitFullscreen();
    }
}

```

###### 页面属性

<https://blog.csdn.net/jarniyy/article/details/80423813>

##### 遍历对象

###### map遍历

```JS
Object.entries(data).map(([k, v])=>{
   console.log(${k},${v})
})
```

