# CSS-重要属性

### CSS-你不知道的事

```
1.在的div中，换行会被渲染成空格
2.使用一些伪元素选择器如.outer div:nth-of-type(1) {**}会将第一个div内所有的子元素的样式统统渲染
3.行内元素如图片，默认值是本身大小
4.父级元素随子级元素增加而增加
5.li前面的小圆点，是ul上的样式
6.flex是一个组合属性
	设flex:1,相当于flex-grow:1;flex-shink:1;flex-basis:0%;
```

### Float

```html
float元素
1.与内容保持宽度
2.可以用margin和padding，不能用tblr四属性
3.下面跟行内元素，与行内元素并排，跟块级元素，下面的元素上移，通过清除浮动避免这一现象
	!!!在使用行内元素时，务必注意给受影响的元素清除浮动！
4.A，B两个div并列,有float属性,
	<1>  A+B的宽  <  浏览器宽度 ，同行显示
    <2>  A+B的宽  >	浏览器视宽， A后面可能留出很长一段空白，并且能被行内元素填充

5.清除浮动:一般给下面的元素清除浮动
        1.通过clear:both
        2.通过after
        	需要清除的元素::after{
        	content:''
        	clear:both
        	display:block
        }
```





### 元素属性

行内元素

```
行内元素无法设置width,height,内外边距的top/bottom
```



````
* 自定义标签<my-tag></my-tag>
* a – 锚点
* abbr – 缩写
* acronym – 首字
* big – 大字体
* em – 强调
* i – 斜体
* img – 图片
* input – 输入框
* label – 表格标签
* select – 项目选择
* small – 小字体文本
* span – 常用内联容器，定义文本内区块
* strong – 粗体强调
* sub – 下标
* sup – 上标
* textarea – 多行文本输入框
* u – 下划线

````

块级元素

```
* ul – 非排序列表
* li - 无序列表
* address – 地址
* blockquote – 块引用
* center – 举中对齐块
* dir – 目录列表
* div – 常用块级容易，也是css layout的主要标签
* dl – 定义列表
* fieldset – form控制组
* form – 交互表单
* h1 – 大标题
* h2 – 副标题
* h3 – 3级标题
* h4 – 4级标题
* h5 – 5级标题
* h6 – 6级标题
* hr – 水平分隔线
* isindex – input prompt
* menu – 菜单列表
* ol – 排序表单
* p – 段落
* pre – 格式化文本
* table – 表格

```

### BFC



### 弹性布局

```
flex-direction:		方向
	row	默认值，按行排列
	row-reverse	与 row 相同，但是以相反的顺序。
	column	灵活的项目将垂直显示，正如一个列一样。
	column-reverse	与 column 相同，但是以相反的顺序。	
	
flex-wrap:	
	说明:弹性布局需要指定min-width,此时需要换行，定义在父元素中
	nowrap	默认不换行
	wrap	换行
	wrap-reverse	以相反的方向换行
```







