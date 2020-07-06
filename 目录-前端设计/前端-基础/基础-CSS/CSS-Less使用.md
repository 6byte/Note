## CSS-Less

#### 注释

```less
#第一种注释方式
//第一种注释方式
#第二种注释方式
/*第二种注释方式*/
```

#### 变量

```less
#申明
	用@来申明一个变量:@pink:pink
#使用
    1.作为普通属性值来使用
       	@color:pink;//记得在webpack中,末尾一定要加;
		color:@color;
		
```

#### 嵌套规则

```less
#使用 & 符号标注层级关系
.inner {
    position: absolute;
    &:hover {
        color: pink;
    }
}
/*编译后的结果是*/
.inner {
    position: absolute;
}
.inner:hover{
        color: pink;
}
如果不加 & 符号，hover和.inner之间有空格，:hover不能生效
```

#### Less**混合**

**种类:	普通混合，无参混合，带参混合**

**作用:	用于解决代码重复**



###### 普通混合

```less
#普通混合，
ex:
.font{
    font-size:18px
    color:red;
}
.my-box{
    .font;
}
.my-table{
    .font
}
/*特性:会将.font编译到文件中，.font()不会编译到文件中*/
```

###### 无参混合

```LESS
ex:
.font(){
    font-size:18px
    color:red;
}
.my-box{
    .font;
}
.my-table{
    .font
}
/*特性:将.font()中的代码，在.my-box中展开*/
```

###### 带参混合

```less
/*使用:	可以自定义变量，适用不同类*/
//!!!注意，形参和实参一定要匹配
ex:
//第一步:定义
.div-box(@width,@height,@color:'yellow'){
    //默认值:@color:'yellow'
	width:@width;
    height:@height;
    color:@color;
}
//第二步:使用
.div{
    .div-box(100px,100px,'red')
}
.tr{
    .div-box(200px,200px,'blue')
}
```

