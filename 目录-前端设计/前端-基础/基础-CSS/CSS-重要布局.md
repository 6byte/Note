# CSS-重要布局





### 布局-REM适配布局

#### rem基础

```
1.rem(root em)是一个相对单位，类似于em,em是父元素字体大小
2.rem以html字体为基准
3.通过修改html字体，实现控制页面整体效果，前提是所有元素必须使用REM单位。
```

#### 媒体查询

```css
基础语法
@media mediatype and|not|only (media feature) {
    CSS-Code;
}
@media 媒体类型 关键字 (媒体功能) {
    CSS-代码;
}
使用技巧
1.一般按照从小到大，或者从大到小的顺序进行布置
```

#### 媒体类型

| 值     | 描述                                         |
| :----- | :------------------------------------------- |
| all    | 用于所有设备                                 |
| screen | 用于电脑屏幕，平板电脑，智能手机等。媒体功能 |

#### 关键字

```
and:对多个媒体设备生效
not:排除某个设备
only:仅仅对某个设备有效
```

#### 示例

```css
max-width:当设备宽度小于xxx生效
min-width:当设备宽度大于xxx生效

当设备宽度小于800px生效
@media screen and (max-width : 800px){
	.outer{
        ...
	}
}

当设备宽度大于800px生效
@media screen and (min-width : 800px){
	.outer{
		...
	}
}

当宽度大于300小于500生效
@media screen and (min-width : 300px) and (max-width:500px){
	.outer{
		...
	}
}
```

### 布局-流式布局



### 布局-响应式布局





#### 设备划分

| 设备划分 |     尺寸区间<宽度>     |
| :------: | :--------------------: |
| 超小屏幕 |   screen     <768px    |
| 小屏设备 | 768px<  screen  <992px |
| 中等屏幕 | 992px < screen <1200px |
|  大屏幕  |     screen>1200px      |

#### 屏幕设置

| 屏幕设置 |  尺寸  |  前缀   |
| :------: | :----: | :-----: |
| 超小屏幕 |  100%  | col-xs- |
| 小屏设备 | 750px  | col-sm- |
| 中等屏幕 | 970px  | col-md- |
|  大屏幕  | 1170px | col-lg- |



#### 媒体查询

```JS
#如果文档宽度小于 300 像素则修改背景颜色
@media screen and (max-width: 300px) {
    body {
        background-color:lightblue;
    }
}
```

#### 栅格详解

```
1.col总数 <	12:留空
2.col总数	==	12:铺满
3.col总数 >	12:下移
```















