## CSS-BFC

#### 概览

```
BFC	:	让元素之间不相互影响
		指独立的渲染区域
```

#### 形成条件

```
1、float:right|left,除了none 
2、position:absolute，fixed
3、display:inline-block，table-cell，table-caption 
4、overflow: hidden，auto，scroll, 除了 visible
```

#### 特性

```
1.内部的Box由上往下排列
2.垂直方向上的距离由margin决定
3.bfc的区域不会与float的元素区域重叠。
4.计算bfc的高度时，浮动元素也参与计算
5.bfc就是页面上的一个独立容器，容器里面的子元素不会影响外面元素。
```

