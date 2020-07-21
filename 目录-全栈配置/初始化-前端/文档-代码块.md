### 组件-代码块

#### Bootstrap-初始化

```HTML
<!doctype html>
<html lang="en">
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		 crossorigin="anonymous">

		<title>Hello, world!</title>
	</head>
	<body>



		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		 crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		 crossorigin="anonymous">
		</script>
		<script type="text/javascript">
			//必须初始化，否则弹窗无法使用
			$(function() {
				$('[data-toggle="tooltip"]').tooltip()
			})
		</script>
	</body>
</html>

```



#### 全屏滚动

博客	<https://blog.csdn.net/Rita_jing/article/details/78236768>

HTML

```html
<div id="wrap">
    <div id="main">
        <div id="first" class="page"></div>
        <div id="second" class="page"></div>
        <div id="third" class="page"></div>
        <div id="fourth" class="page"></div>
    </div>	
</div>

```

CSS

```css
html,
body {
    margin: 0;
    padding: 0;
}
#wrap {
    width: 100%;
    overflow: hidden;
    background: #ccc;
}
#main {
    width: 100%;
    background: #ccc;
    position: relative;
}

.page {
    width: 100%;
    margin: 0;
}
#first {
    background: #E4E6CE;
}
#second {
    background: #6CE26C;
}
#third {
    background: #BF4938;
}
#fourth {
    background: #2932E1;
}
```

Javascript

```JS

var pages = document.getElementsByClassName("page");
var wrap = document.getElementById("wrap");
var len = document.documentElement.clientHeight;
var main = document.getElementById("main");
wrap.style.height = len + "px";
for(var i=0; i<pages.length; i++){
    pages[i].style.height = len + "px";
}
if(navigator.userAgent.toLowerCase().indexOf("firefox") != -1){
    document.addEventListener("DOMMouseScroll",scrollFun);
}else if(document.addEventListener){
    document.addEventListener("mousewheel",scrollFun,false);
}else if(document.attachEvent){
    document.attachEvent("onmousewheel",scrollFun);
}else{
    document.onmousewheel = scrollFun;
}
var startTime = 0;
var endTime = 0;
var now = 0;
function scrollFun(e){
    startTime = new Date().getTime();
    var event = e || window.event;
    var dir = event.detail || -event.wheelDelta;
    if(startTime - endTime > 1000){
        if(dir>0 && now > -3*len){
            now -= len; 
            main.style.top = now +"px";
            endTime = new Date().getTime();
        }else if(dir<0 && now < 0){
            now += len;
            main.style.top = now +"px";
            endTime = new Date().getTime();
        }
    }else{
        event.preventDefault();    
    }
}

```

#### 底部导航

HTML

```HTML
<div class="acv">
	<ul>
            <li>
                顶部导航
            </li>
            <li>
                内容
            </li>
            <li>
                底部导航
            </li>
	</ul>
</div>
```



CSS

```CSS
.acv,
.acv ul,
body,
html {
width: 100%;
height: 100%;
position: fixed;
margin: 0;
padding: 0;
}

.acv ul {
    display: flex;
    flex-direction: column;
    list-style: none;
}

.acv ul li:nth-of-type(1) {
    background-color: red;
    flex: 1;
}

.acv ul li:nth-of-type(2) {
    flex: 8;
    overflow: scroll;
}

.acv ul li:nth-of-type(3) {
    background-color: blue;
    flex: 1;
    max-height: 60px;
}
```

#### Vue解析markdown

```JS
<template>
    <div v-html="compiledMarkdown"></div>
</template>
 
<script>
    let marked = require('marked');
    let hljs = require('highlight.js');
    import 'highlight.js/styles/default.css';

    marked.setOptions({
    renderer: new marked.Renderer(),
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: false,
    smartLists: true,
    smartypants: false,
    highlight: function (code, lang) {
          if (lang && hljs.getLanguage(lang)) {    
            return hljs.highlight(lang, code, true).value;
          } else {
            return hljs.highlightAuto(code).value;
          }
      }
  });
 
 
   export default{
     name: 'test', 
    computed: {
        compiledMarkdown() {
        let detail = '当马孔多在《圣经》所载那种龙卷风的怒号中化作可怕的瓦砾与尘埃漩涡时，奥雷里亚诺为避免在熟知的事情上浪费时间又跳过十一页 `test` 预言他正在破解羊皮卷的最后一页，宛如他正在会言语的镜中照影。他再次跳读去寻索自己死亡的日期和情形，但没等看到最后一行便已明白自己不会再走出这房间，因为可以预料这座镜子之城——或蜃景之城——将在奥雷里亚诺·巴比伦全部译出羊皮卷之时被飓风抹去 `test`、`test` 两大应用系统，让开发者更好、更快、更方便开发移动应用。\n\n';
        return marked(detail || '', {
          sanitize: true
        });
      }
    }
   }
</script>

```

#### JS解析Markdown

```JS
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<title>Marked in the browser</title>
		<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
		<style>
			.md_source {
				display: none
			}
		</style>
	</head>
	<body>
		<pre class="md_source">
# Marked in the browser
	Rendered by **marked**.
		</pre>
		<div class="md_render">

		</div>
		<link href="http://cdn.bootcss.com/highlight.js/8.0/styles/monokai_sublime.min.css" rel="stylesheet">
		<script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>
		<script>hljs.initHighlightingOnLoad();</script>
		<script>
			$('.md_render').html(marked($('.md_source').html()));
		</script>
	</body>
</html>

```

