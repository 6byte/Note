# CSS-重要布局



### 布局 -flex布局

```

```



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

#### 媒体功能

| 值                      | 描述                                                         |
| :---------------------- | :----------------------------------------------------------- |
| aspect-ratio            | 定义输出设备中的页面可见区域宽度与高度的比率                 |
| color                   | 定义输出设备每一组彩色原件的个数。如果不是彩色设备，则值等于0 |
| color-index             | 定义在输出设备的彩色查询表中的条目数。如果没有使用彩色查询表，则值等于0 |
| device-aspect-ratio     | 定义输出设备的屏幕可见宽度与高度的比率。                     |
| device-height           | 定义输出设备的屏幕可见高度。                                 |
| device-width            | 定义输出设备的屏幕可见宽度。                                 |
| grid                    | 用来查询输出设备是否使用栅格或点阵。                         |
| height                  | 定义输出设备中的页面可见区域高度。                           |
| max-color               | 定义输出设备每一组彩色原件的最大个数。                       |
| max-color-index         | 定义在输出设备的彩色查询表中的最大条目数。                   |
| max-device-aspect-ratio | 定义输出设备的屏幕可见宽度与高度的最大比率。                 |
| max-device-height       | 定义输出设备的屏幕可见的最大高度。                           |
| max-device-width        | 定义输出设备的屏幕最大可见宽度。                             |
| max-height              | 定义输出设备中的页面最大可见区域高度。                       |
| max-monochrome          | 定义在一个单色框架缓冲区中每像素包含的最大单色原件个数。     |
| max-resolution          | 定义设备的最大分辨率。                                       |
| max-width               | 定义输出设备中的页面最大可见区域宽度。                       |
| min-color-index         | 定义在输出设备的彩色查询表中的最小条目数。                   |
| min-device-aspect-ratio | 定义输出设备的屏幕可见宽度与高度的最小比率。                 |
| min-device-width        | 定义输出设备的屏幕最小可见宽度。                             |
| min-device-height       | 定义输出设备的屏幕的最小可见高度。                           |
| min-height              | 定义输出设备中的页面最小可见区域高度。                       |
| min-resolution          | 定义设备的最小分辨率。                                       |
| **min-width**           | **定义输出设备中的页面最小可见区域宽度**。                   |
| monochrome              | 定义在一个单色框架缓冲区中每像素包含的单色原件个数。如果不是单色设备，则值等于0 |
| orientation             | 定义输出设备中的页面可见区域高度是否大于或等于宽度。         |
| resolution              | 定义设备的分辨率。如：96dpi, 300dpi, 118dpcm                 |
| width                   | 定义输出设备中的页面可见区域宽度。                           |

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

```
原理：
	1.基于父容器，配合子元素实现变化效果
	2.通过媒体查询父容器大小，对子元素进行排列
	0
```



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

##### 列嵌套

```html
1.列嵌套要加row

<div class="row">
    <div class="col-8">
        <div class="row">
            <div class="col-8">1</div>
            <div class="col-2">1</div>
        </div>
    </div>
    <div class="col-2">1</div>
</div>
```











