#### Jquery选择器

- 选择某一个子元素

```
$('ul').eq(0).css('background','purple')
```

- 选择最近父元素

```
$('#id').parent().css('background','purple')
```

- 选择一个子元素

```js
$('#abc').find('li').css('background','purple')
```

- 所有同级元素

```js
$('#id').siblings().css('background','purple')
```

- 获取当前索引

```js
$(this).index()
```

#### Jquery效果

- 隐藏元素

```
$('#id').hide([speed,[easing],[fn]])
```

- 显示元素

```
$('#id').show([speed<int>],[swing,linear,fast],function(){})
```

- 获取索引

```
$('#id').index()
//获取当前索引，从0开始
```

- 鼠标经过

```
$('button').hover(function(){//鼠标进入执行},function(){//鼠标滑出执行})
```

- 阻止动画

```
$('#id').stop().slideToggle()//阻止之前一切动画效果，防止冲突
```

- 改变透明度

```js
$('#abc').fadeTo(1000,1)//必须填写两个参数，（时间，透明度）	
```

#### JQuery修改CSS

- 给CSS赋值  ==css==

```
$("#id").css('background','pink')//属性一定要加双引号
```

- 添加样式  ==addClass==

```css
$('#id').addClass("style")
```

- 删除类  ==removeClass==

```
$('#id').removeClass("style")
```

- 切换类  ==toggleClass==

```
$('#id').toggleClass("style")
```



#### Jquery增删改查

- 更改属性  ==attr==

```
("#id").attr("href","http://www.qq.com.cn/");
```

- 属性操作

```
(selector).prop(property)//返回属性的值
(selector).prop(property,value)//设置属性和值
(selector).prop({property:value, property:value})//设置多个属性和值
```

- 遍历对象

```
var city={}
$.each(city, (i, ele) => {
	console.log('i是索引'+'ele是对象');
})
```

- 内部添加

```
$('div').append("<li>Heloise</li>"//从内部最后开始添加
$('div').prepend("<li>Heloise</li>")//从内部最前面开始添加
```

- 外部添加

```
$('div').after("<li>after</li>")
$('div').before("<li>after</li>")
```

- 删除元素

```
$('li').remove()//删除所有li
$('div').html("")//删除所有子节点,一定要双引号
$('div').
```

- 绑定多个事件

```javascript
$('#id').on({
	click:function(){}},
	mouseenter:function(){}
})

```

- 自动触发

```
$('#id').click()//第一种方式
$('button').triggerHandler('click')//不会触发默认行为
```

#### JQuery监听滚动条

```js
$('#message').scrollTop($('#message')[0].scrollHeight);
//把滚动条定位到最底部
//$('#message')[0]是转换原生JS对象，从而获取属性
```

#### JQuery位置

<https://blog.csdn.net/justlpf/article/details/81281365>

```js
注意:一定要在页面完全加载后再获取高度，否则一直为0

offset相对浏览器顶部距离的偏移，绝对位置
拥有两个属性Top,Left
$('demo').offset({top:10,left:10}) 

$(window).scrollTop();//获取垂直滚动条距离文档头部的高度

$(document).scrollLeft() //获取水平滚动条的距离

$(document).height()  //获取整个页面的高度

$(window).height()  //获取当前,也就是你浏览器所能看到的页面的那部分的高度,这个大小在你缩放浏览器窗口大小,会改变,与document是不一样的


```

#### JQuery兼容滚动条

```JS
var scrollPos;//滚动条位置
if (typeof window.pageYOffset != 'undefined') {
    scrollPos = window.pageYOffset;
} else if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
    scrollPos = document.documentElement.scrollTop;
} else if (typeof document.body != 'undefined') {
    scrollPos = document.body.scrollTop;
}
```





#### 源码分析

```js
//给jquery原型添加方法
var SP = $.fn.方法名		//模板
var SP = $.fn.switchPage  //样例

//合并对象，将后面的两个对象合并到第一个对象中
var opts = $.extend(对象合并, 对象1, 对象2);

var settings = { validate: false, limit: 5, name: "foo" };
var options = { validate: true, name: "bar" };
jQuery.extend(settings, options);
//输出
{validate: true, limit: 5, name: "foo", username: "bar"}
```

























