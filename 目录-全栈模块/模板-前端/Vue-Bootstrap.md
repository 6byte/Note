## Vue-Bootstrap



## B-Vue-全局属性

```vue
颜色:
		primary, secondary, 
		success, danger, 
		warning, info, 
		light ,dark.
禁用:		disable
size	 sm
```

### B-Vue-栅格系统

#### 概览

```css
理解设备宽度
cols 	<自动>移动设备 占2格
sm		平板			占2格
md		小屏电脑	   占4格
lg/xl	电脑			占12格		
<b-col cols=2 sm=2  md =4 lg=8 xl=12>这是一段文字</b-col>

重要属性:
no-gutters		：去除row的margin和pa dding
```

#### 结构&示例

```VUE
简单示例
<b-container fluid>
  <b-row>
    <b-col>1 of 3</b-col>
    <b-col>2 of 3</b-col>
    <b-col>3 of 3</b-col>
  </b-row>
</b-container>

理解:每个<b-col>占1/3网格，由四个<b-col>，则每个<b-col>占1/4网格
```

#### 设备尺寸对比

|              | **特小**（xs） `<576px` | **小**（sm） `≥576px` | **中**（md） `≥768px` | **大**（lg） `≥992px` | **特大**（xl） `≥1200px` |
| :----------: | :---------------------: | :-------------------: | :-------------------: | :-------------------: | :----------------------: |
| 最大容器宽度 |       无（自动）        |        540像素        |         720px         |         960px         |          1140px          |
|     支柱     |       `cols="*"`        |       `sm="*"`        |       `md="*"`        |       `lg="*"`        |         `xl="*"`         |
|    偏移量    |      `offset="*"`       |    `offset-sm="*"`    |    `offset-md="*"`    |    `offset-lg="*"`    |     `offset-xl="*"`      |
|     订购     |       `order="*"`       |    `order-sm="*"`     |    `order-md="*"`     |    `order-lg="*"`     |      `order-xl="*"`      |



#### 高级栅格

##### 自定义宽度栅格

```vue
作用:自定义栅格占用个数
注意:移动端不会换行显示
<b-container >
    <b-row class="text-center">
        <b-col>占2个网格</b-col>
        <b-col cols="8">占8个网格</b-col>
        <b-col>占2个网格</b-col>
    </b-row>
</b-container>
```

##### 栅格折行

```vue
在不同设备下选择是否换行
	sm:	设备>576px生效
    sm:当设备>576px独占一行
    sm="8"

<!-- 栅格系统 -->
<b-container>
    <b-row>
<!-- 
    当设备>576px，col=8
    当设备>576px，col=12,始终占一行
-->
        <b-col sm="8">col-sm-8</b-col>
        <b-col sm="4">col-sm-4</b-col>
    </b-row>

    <b-row>
       <!-- sm:当设备>576px独占一行-->
        <b-col sm>col-sm</b-col>
        <b-col sm>col-sm</b-col>
        <b-col sm>col-sm</b-col>
    </b-row>
</b-container>
```

##### 对齐

```vue
使用:在row添加align-h属性

中心对齐
<b-row align-h="center">
    <b-col cols="4">在最中间</b-col>
    <b-col cols="4">在最中间</b-col>
</b-row>

两端对齐
 <b-row align-h="between">
    <b-col cols="4">靠最左</b-col>
    <b-col cols="4">靠最右</b-col>
  </b-row>
```

##### 排序

```
使用:用order-x进行排序,x是一个数值，相当于权重，数字越大，排序越靠前
<b-row class="mb-3">
		    <b-col>First in DOM, no order applied</b-col>
		    <b-col order="5">Second in DOM, with a larger order</b-col>
		    <b-col order="1">Third in DOM, with an order of 1</b-col>
		  </b-row
```

##### 偏移

```VUE
使用通过offset属性控制偏移
注意:如果使用了md,sm，offset最好对应上

<b-container fluid class="bv-example-row">
  <b-row>
    <b-col md="4">md="4"</b-col>
    <b-col md="4" offset-md="4">md="4" offset-md="4"</b-col>
  </b-row>

  <b-row>
    <b-col md="3" offset-md="3">md="3" offset-md="3"</b-col>
    <b-col md="3" offset-md="3">md="3" offset-md="3"</b-col>
  </b-row>

  <b-row>
    <b-col md="6" offset-md="3">md="6" offset-md="3"</b-col>
  </b-row>
</b-container>
```

##### 嵌套

```VUE
<b-container fluid class="bv-example-row">
<b-row>
   <b-col cols="9">
            外层
     <b-row>
        <b-col cols="8" sm="6"></b-col>
     <b-col cols="4" sm="6">Level 2: cols="4" sm="6"</b-col>
   </b-row>
   </b-col>
</b-row>
</b-container>
```

##### 定制行

```VUE
使用:通过在row上面添加cols控制行数

<b-container>
    <!--添加cols=2,将会被分成2行显示-->
  <b-row cols="2">
    <b-col>Column</b-col>
    <b-col>Column</b-col>
    <b-col>Column</b-col>
    <b-col>Column</b-col>
  </b-row>
</b-container>
```

## B-Vue-导航

#### 重要属性

```vue
1.注意:
	0.通过<b-collapse>和<b-navbar-nav>实现响应式导航
	1.<b-navbar-nav>必须在父容器<b-collapse>内
	3.父容器<b-collapse id="nav-collapse" is-nav>里面的值不可更改
2.重要组件
    <b-navbar-nav> 子组件：
       <b-nav-item> 			用于链接（和路由器链接）操作项
       <b-nav-text> 			用于添加垂直居中的文本字符串。
       <b-nav-item-dropdown> 	导航栏下拉菜单
       <b-nav-form> 			用于向导航栏添加简单表单。
3.重要属性
<b-navbar>
	1.variant primary,success,info,warning,danger,dark,light	主题颜色	
    2.typelight,dark文字配色
    3.sticky	BOOLEAN		窗口滚动，导航位置保持在顶部
    4.fixed		BOOLEAN		一直在顶部，有滚动条出现会隐藏
           
<b-navbar-nav>
     justified		BOOLEAN		元素居中，块状元素居中，文字不居中
     small			BOOLEAN			小型导航  
     class<向右排列必加>	"ml-auto"		实现导航元素靠右排列
    
<b-navbar-brand>
	active		BOOLEAN				是否激活
    disabled	BOOLEAN				是否禁用
    to			自定义字符			 串路由跳转
    
</b-nav-item-dropdown>
   right<向右排列必加>  BOOLEAN	  与上面的class配和使用    


```

#### 示例

```VUE
靠右排列
<b-navbar-nav class="ml-auto">
</b-navbar-nav>

基础结构
<!-- 外部导航开始，进行颜色选择 -->
<b-navbar toggleable="lg" type="dark" variant="dark">
    <!-- 商标 -->
    <b-navbar-brand href="#">商标</b-navbar-brand>

    <!-- 下拉开关，三横 -->
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <!-- 配合上面的开关工作 -->
    <b-collapse id="nav-collapse" is-nav>
        <!-- 文本导航开始 -->
        <b-navbar-nav>
            <b-nav-item href="#">导航1</b-nav-item>
        </b-navbar-nav>
        <!-- 文本导航结束 -->
    </b-collapse>
</b-navbar>

完整代码
<div>
    <!-- 外部导航开始，进行颜色选择 -->
    <b-navbar toggleable="lg" type="dark" variant="dark">
        <!-- 商标 -->
        <b-navbar-brand href="#">商标</b-navbar-brand>

        <!-- 下拉开关，三横 -->
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <!-- 配合上面的开关工作 -->
        <b-collapse id="nav-collapse" is-nav>
            <!-- 文本导航开始 -->
            <b-navbar-nav>
                <b-nav-item href="#">导航1</b-nav-item>
                <b-nav-item href="#" disabled>禁用导航</b-nav-item>
            </b-navbar-nav>
            <!-- 文本导航结束 -->

<!-- ml-auto:靠右排列 -->
            <b-navbar-nav class="ml-auto">
                <!-- 列表导航开始 -->
                <b-nav-form>
                    <b-form-input size="sm" placeholder="搜索"></b-form-input>
                </b-nav-form>
                <!-- 列表导航结束 -->

                <!-- 下拉列表开始，right:靠右排列  -->
                <b-nav-item-dropdown text="下拉列表" right>
                    <b-dropdown-item href="#">下拉表1</b-dropdown-item>
                    <b-dropdown-item href="#">下拉表2</b-dropdown-item>
                </b-nav-item-dropdown>
                <!-- 下拉列表结束 -->
            </b-navbar-nav>

        </b-collapse>
    </b-navbar>
</div>



```



## B-Vue-表单属性

#### 输入框

```VUE
重要属性
<b-form-input>
readonly 	boolean		只读
trim		boolean		去除空格
size		sm,lg				控制尺寸大小
status	null,false,true		控制输入框颜色    
    
1.普通表单
    <b-form-input trim placeholder="自定义" size="lg">
    </b-form-input>



2.带下拉单的表单
<b-form-input list="my-list-id"></b-form-input>
<datalist id="my-list-id">
    <option>Manual Option</option>
    <option v-for="size in sizes">{{ size }}</option>
</datalist>
<script>
export default{
data() {return {sizes: ['Small','Medium','Large','Extra Large']}}}
</script>

3.文件表单
    <template>
  <div>
    <b-form-file
      v-model="file"
      placeholder="选择文件"
    >
	</b-form-file>
	<!-- 按照该格式写 -->
	<div class="mt-3">文件内容:{{ file ? file.name : '' }}</div>
  </div>
</template>

<script>
  export default {
    data() {return {file: null,}}}
</script>
```

#### 并行输入框

```VUE
第一种方式，在<b-input-group>中添加
        1.<b-form-input>是输入框
    	2.使用<b-input-group-append>标签进行添加
<b-input-group>
    
<b-form-input size="sm"></b-form-input>
    <b-input-group-append>
        <!--添加按钮-->
        <b-button variant="outline-success">
            发送
        </b-button>
    </b-input-group-append>
</b-input-group>
    
第二种方式在<b-input-group>通过代码添加
    1.prepend：在表单前面添加
    2.append：	在input后面追加
    <b-input-group size="lg" prepend="$" append=".00">
        <b-form-input></b-form-input>
    </b-input-group>
```





#### 选择框

```vue
<template>
<div>
    <!-- 表单 -->
    <b-form-select v-model="selected" :options="options"></b-form-select>
    <div>选中的值:{{ selected }}</div>
    </div>
</template>

<script>
export default {
  data() {
    return {
        selected: null,
        options: [
            // value是选中的值，text是提示文本,可以为null
            { value: 'apple', text: '苹果' },
            // 可以返回对象,也可以禁用对象
            { value: { C: '3PO' }, 
             text: '水蜜桃',disabled: true  },
        ]}}}
</script>
```

#### 验证框

```VUE
重要属性
<b-form-group>
description  自定义提示信息	下面的提示信息，small标签
label-size	 sm ,lg				标签大小
    
    
普通框
<div>
  <div>
    <b-form-group
      id="fieldset-horizontal"
      label-cols-sm="4"
      label-cols-lg="3"
      description="你好啊"
      label="Enter your name"
      label-for="input-horizontal"
    >
      <b-form-input id="input-horizontal"></b-form-input>
    </b-form-group>
  </div>
</div>

验证框
<template>
  <div>
    <b-form-group
      description="樱花绽开了"
      label="输入你的名字"
      :invalid-feedback="invalidFeedback"
      :valid-feedback="validFeedback"
      :state="state"
    >
      <b-form-input  v-model="name" :state="state" trim></b-form-input>
    </b-form-group>
  </div>
</template>

<script>
  export default {
    computed: {
	// 对输入框进行判断
      state() {
        return this.name.length >= 4 ? true : false
      },
      invalidFeedback() {
        if (this.name.length > 6) {
          return ''
        } else if (this.name.length > 0) {
          return '至少输入6个字符'
        } else {
          return '输入名字'
        }
      },
      validFeedback() {
        return this.state === true ? '正确' : ''
      }
    },
    data() {
      return {
        name: ''
      }
    }
  }
</script>
```



## B-Vue-图片

#### 响应式图片

```HTML
两种模式
    fluid
    fluid-grow
重要属性
<b-img
   width:图片宽度
       >
    
自适应：
<div>
  <b-img src="https://picsum.photos/1024/400/?image=41" fluid >
      响应式图片
    </b-img>
</div>

强制拉伸：可能模糊
<b-img src="https://picsum.photos/300/150/?image=41" fluid-grow alt="Fluid-grow image"></b-img>

```

#### 外框图

```vue
 使用 thumbnail属性
 <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Image 2"></b-img>
```

#### 圆角图

```VUE
需要配合属性
<b-img v-bind="mainProps" rounded alt="Rounded image"></b-img>
<scrpit>
 data() {
    return {
		mainProps: {  
			blankColor: '#777',//无图片显示
			width: 75, //宽
			height: 75, //高
			class: 'm1' ,绑定样式
			src:'https://picsum.photos/1024/400/?image=41'
    		//图片地址
			//black:true	无图片
}}}

rounded属性
    'top': 上边两个圆角
    'right': 左边两个圆角
    'bottom': 底部两个圆角
    'left': 左边两个圆角
    'circle':圆形图片
</scrpit>
```

#### 图片对齐

```vue
左右对齐
使用:right,left属性对齐
<div class="clearfix">
	<b-img left src="https://picsum.photos/125/125/?image=58" >
    </b-img>
    
	<b-img right src="https://picsum.photos/125/125/?image=58">
    </b-img>
</div>
		
```

#### 图片懒加载

```vue
 
<b-img-lazy v-bind="mainProps" :src="mainProps.src" ></b-img-lazy>

<scrpit>
return {
    mainProps: {
        center: true,//图片水平居中
        fluidGrow: true,//放大到原始宽度
        blank: true,
        blankColor: '#bbb',
        //width: 600,设置fluidGrow可以不设置宽高
        //height: 400,
        class: 'my-5',
        src:'https://picsum.photos/640/400/?image=80'
}}
</scrpit>

```

## B-Vue-卡片

#### 普通卡片

```VUE
<div>
  <b-card
    title="卡片标题"
    img-src="https://picsum.photos/600/300/?image=25"
    img-top
    tag="article"
    style=""
    class=""
  >
      
    <b-card-text>
      卡片内容
    </b-card-text>

    <b-button href="#" variant="primary">跳转</b-button>
  </b-card>
</div>
```

#### 文字卡片

```VUE
<div>
<!-- 
注意两个属性
	title:卡片标题 
	sub-title:子标题
-->
  <b-card title="卡片标题" sub-title="副标题">
    <b-card-text>
     文字内容
    </b-card-text>
    <a href="#" class="card-link">卡片链接1</a>
    <b-link href="#" class="card-link">卡片链接2</b-link>
  </b-card>
</div>
```

#### 标题尾注

```vue
<b-card header="头标题" footer="尾注"  title="标题">
	<b-card-text>
    文字内容
    </b-card-text>
	<b-button href="#" variant="primary">
    跳转
    </b-button>
</b-card>
```



#### 卡片带图

```vue
卡片大小:img-height,img-width
卡片位置:主要由img-*控制
	img-left，img-start:		左右排列排在左边
	 img-right,img-end:		 左右排列排在右边
			img-bottom:		在文字下面
				img-top     在文字上面
	
水平排列
<div>
    <b-card no-body >
        <b-row no-gutters>
            <b-col cols>
			<b-card-img 
            src="https://picsum.photos/400/400/?image=20" 
                   
             >				
             </b-card-img>
            </b-col>
            <b-col cols>

                <b-card-body title="水平排列卡片">
                    <b-card-text>
                        文字内容
                    </b-card-text>
                </b-card-body>
            </b-col>
        </b-row>
    </b-card>
</div>

```

#### 卡片颜色

```vue
主题颜色
<!-- 
bg-variant="dark"			主题黑色
text-variant="white"		文本白色
header="Primary"			头部颜色
header-bg-variant="primary"	头部背景颜色
 align="center"				文字居中对齐
bg-variant="primary"		背景颜色
border-variant="primary"	边框颜色
footer-bg-variant="success"	脚注背景
footer-border-variant="dark"脚注边线颜色
-->

<b-card
        border-variant="primary"
        border-variant="primary"
        header="头部标题"
        header-bg-variant="white"
        header-text-variant="white"
        align="center"
        bg-variant="primary"
        footer-bg-variant="success"
    	footer-border-variant="dark"
>
<b-card-text>文本</b-card-text></b-card-text>
</b-card>
```

#### 卡片组

```VUE

注意卡片的位置

<div>
    <b-card-group>
        <b-card title="标题" 
                img-src="https://placekitten.com/g/300/450" 
                img-top 
                >
            <b-card-text>
                文字
            </b-card-text>
            <template 
                      v-slot:footer>
			<small class="text-muted">
    			脚注
             </small>
            </template>
        </b-card>

        <b-card title="标题" 
                img-src="https://placekitten.com/g/300/450" 
                img-top 
                >
            <b-card-text>
                文字
            </b-card-text>
     <template 
                 v-slot:footer>
                <small class="text-muted">
                    脚注
                </small>
     </template>
        </b-card>
    </b-card-group>
</div>
```

## B-Vue-选项卡

#### 基本选项卡

```vue
重要属性
<b-tab>:
	title：选项卡导航按钮
	active:激活样式
	vertical					：垂直导航
	end							:导航按钮靠右
	nav-wrapper-class="w-50"	：导航按钮和内容各占一半
	active-tab-class			：激活按钮的样式<字符串>

<b-tabs>：
    pills		：药膏导航
	fill  		:导航铺满父容器，内容自适应
    Justified	：导航铺满父容器，不伸缩
    align 		:导航条居中，内容伸缩
    
<div>
    <b-tabs>
        <b-tab title="选项卡1" active>文字内容</b-tab>
        <b-tab title="禁用 " disabled>文字内容</b-tab>
        <!-- 嵌套图片等其他元素 -->
        <b-tab title="选项卡二" >
        	<b-card-img 
              bottom 
              src="https://picsum.photos/600/200/?image=21">
            </b-card-img>
        </b-tab>
    </b-tabs>
</div>
```

#### 高级选项卡

```vue

1.标题样式-加载中

<div>
   <b-card no-body>
         <b-tabs pills card vertical end >
           <b-tab active>
                    
             <!-- 带加载动画的标题，可以直接当普通tab使用 -->       
<template v-slot:title><b-spinner type="grow" small>
</b-spinner>加载标题</template><p class="p-3">加载内容</p>
                </b-tab>

<b-tab title="标题1" ><b-card-text>内容1</b-card-text></b-tab>
<b-tab title="标题2"><b-card-text>内容2</b-card-text></b-tab>

     </b-tabs>
   </b-card>
</div>
    
    
 2.懒加载选项卡:可以响应键盘按钮
<b-tabs lazy>
    <b-tab title="标题1"><b-alert show>
        内容
        </b-alert>
    </b-tab>
    <b-tab title="标题2">
        <b-alert show> 
            内容
        </b-alert></b-tab>
</b-tabs>
	

    
```

#### 监听选项卡

```VUE
注意：
	按钮和文本内容并没有强联系
	

<template>
<div>
    <b-card no-body>
        <b-nav pills card-header slot="header" v-b-scrollspy:nav-scroller>
            <!--  
1.<b-nav-item >的href属性与下面的元素id属性对应 
2.实质是a标签对应id进行跳转
-->
            <b-nav-item href="#title1" @click="scrollIntoView">标题一</b-nav-item>
            <b-nav-item href="#title2" @click="scrollIntoView">标题二</b-nav-item>
            <b-nav-item-dropdown text="下拉标题" right-alignment>
                <b-dropdown-item href="#dtitle1" @click="scrollIntoView">下拉菜单1</b-dropdown-item>		  <!-- 分割线 -->
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item href="#dtitle2" @click="scrollIntoView">下拉菜单2</b-dropdown-item>

    </b-nav-item-dropdown>
            <b-nav-item href="#title3" @click="scrollIntoView">标题三</b-nav-item>
    </b-nav>
        <!-- 
通过设置style属性对样式进行改变 ！！！需要设置高度
-->
        <b-card-body
                     id="nav-scroller"
                     ref="content"
                     style="position:relative; height:300px; overflow-y:scroll;" >
            <p>{{ text }}</p>
            <h4 id="title1">标题一</h4>
            <p >{{ text }}</p>
            <h4 id="title2">标题二</h4>
            <p>{{ text }}</p>
            <h4 id="dtitle1">子标题一</h4>
            <p >{{ text }}</p>
            <h4 id="dtitle2">子标题二</h4>

            <p >{{ text }}</p>
            <h4 id="title3">标题三</h4>
            <p>{{ text }}</p>
    </b-card-body>
    </b-card>
    </div>
</template>

<script>
    export default {
        methods: {
            // 核心逻辑，一般不用改
            scrollIntoView(evt) {
                evt.preventDefault()
                const href = evt.target.getAttribute('href')
                const el = href ? document.querySelector(href) : null
                if (el) {this.$refs.content.scrollTop = el.offsetTop}}},
        data() {
            return {
                text: `
在这里添加文字内容
`
            }}}
</script>
```

#### 垂直监听选项卡

```VUE


<template>
	<b-container>
		<b-row>
			<b-col>
				<b-list-group v-b-scrollspy:listgroup-ex>
					<b-list-group-item href="#list-item-1">Item 1</b-list-group-item>
					<b-list-group-item href="#list-item-2">Item2</b-list-group-item>
				</b-list-group>
			</b-col>

			<b-col>
				<div id="listgroup-ex" style="position:relative; overflow-y:auto; height:300px">
					<h4 id="list-item-1">Item 1</h4>
					<p>{{ text }}</p>
					<h4 id="list-item-2">Item 2</h4>
					<p>{{ text }}</p>
	
				</div>
			</b-col>
		</b-row>
	</b-container>
</template>

<script>
	export default {
		data() {
			return {
				text: `文本内容`
			}}}
</script>

```





## B-Vue-进阶工具

#### SPINNER

```
重要属性
type	grow/default	圈/默认旋转

<b-spinner label="Spinning"></b-spinner>
```

#### 徽标

```
<b-badge>New</b-badge>
```

#### 模态框

```VUE
重要属性
<b-modal>
size		sm,lg, xl		控制大小
scrollable  boolean			设置滚动条
centered	boolean			模态框屏幕垂直居中
hide-backdrop	boolean		网页不会变黑
    
    

<div>
    <!-- 启动模态框的按钮 -->
    <b-button v-b-modal.my-modal>启动模态框</b-button>
 	<!-- 标题，Id要和按钮保持一致 -->
    <b-modal id="my-modal" title="模态框标题">
    <!-- 内容 -->
        <p >内容</p>
    </b-modal>
</div>

2.带关闭的模态框
使用 @click="$bvModal.toggle('你的ID')"关闭模态框
$bvModal有三个函数show(),hide(),toggle()

<div>
    <b-button  @click="$bvModal.show('my-modal')">打开模态框</b-button>
    <!-- 模态框主题，用id标注  hide-footer:隐藏脚注-->
    <b-modal id="my-modal"  hide-footer>
        <template v-slot:modal-title>
<!-- 在这里输入标题 -->
		标题
        </template>
        <div class="d-block text-center">
            <h3>文字内容</h3>
        </div>
        <!-- 模态框中的关闭按钮 -->
        <b-button block @click="$bvModal.hide('my-modal')">关闭</b-button>
    </b-modal>
	</div>
```

#### 表格

````VUE
可能只能渲染规则表

<template>
  <div class="text-center">
    <b-table striped hover :items="items"></b-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
		  items: [
          { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
          { age: 21, first_name: 'Larsen', last_name: 'Shaw' },]}}}
</script>
````

#### 隐藏元素

```vue
使用：v-show="show"自定义命令对元素进行控制
v-show:
	为true显示内容
	为false隐藏内容
	
<template>
	<div>
		<b-button @click="show = !show">
            点击隐藏显示
    	</b-button>
        <div v-show="show">元素</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				show: true
			}},}
</script>
```

#### 多媒体

```VUE
重要属性
right-align:图片靠右排列


<div>
    <b-card><b-media><template v-slot:aside>
<b-img 
       blank blank-color="#ccc" 
       width="64" 
       alt="placeholder"
       ></b-img></template>
            <h5>标题</h5><p>文字内容</p>
        
          <!-- 内容嵌套 -->   
<div>
<b-media right-align vertical-align="center">
   <template v-slot:aside>
<b-img blank 
       blank-color="#ccc" 
       width="80" 
       alt="placeholder">
       </b-img>
   </template>
    <h5>主题</h5><p>文本内容</p></b-media></div>
            <!-- 内容嵌套 -->
        </b-media>
    </b-card>
</div>
```

#### 轮播图

````vue
重要属性

<b-carousel>指针属性
interval	数字		轮播图切换间隔
controls	BOOLEAN		是否显示控件
fade		BOOLEAN		淡入淡出效果
value 		数字			当前的轮播图索引
img-height	数字			轮播图高度
img-width	数字			轮播图宽度
    
<b-carousel-slide>轮播属性
caption		自定义字符串		标题名字
text		自定义字符串		图片上显示的文字
img-src		自定义字符串		图片URL
    
<!--
		注意配置<b-carousel>的属性
-->
<div>
<!--
		指针标签
		图像大小不符合要求，需要更改图片高度
-->
    <b-carousel 
                id="carousel-no-animation" 
                o-animation indicators 
                img-height="480">
        
<!-- 轮播图标签，一个标签=一张轮播图-->
        
<template>
	
	
	
	<div>
		<!-- 轮播图开始 -->
		<div>
			<b-carousel id="carousel-no-animation" fade indicators  >
				<!-- 使用for循环迭代-->
				 <div v-for="v in Carousel">
					 <b-carousel-slide :caption="v.caption" :text="v.text" :img-src="v.url"></b-carousel-slide>
				 </div>
			</b-carousel>
		</div>
		<!-- 轮播图结束 -->
	</div>
	
</template>

<script>
	export default {
		data:function(){
			return {
				Carousel:[
						{
							text:'轮播图显示文本',
												url:'https://picsum.photos/1024/480/?image=10',
							caption:'标题文本'
						},
						{
							text:'轮播图显示文本',
							url:'https://picsum.photos/1024/480/?image=10',
							caption:'标题文本'
						},
					]}}}
</script>

````

#### 分页

```vue
重要属性

<b-pagination>
pills 	 BOOLEAN	切换按钮样式
align	 right,left,fill	对齐，fill弹性居中


<template>
	<div>
	<!-- total-rows:总共行数-->
		<b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage"></b-pagination>
		<!-- 显示当前页码 -->
		<p>Current Page: {{ currentPage }}</p>
		<!-- 用表格显示数据 -->
		<!-- 
	 items：		数据源
	per-page：		每页显示数据
	current-page：	当前显示页码
	small:			表单大小
 		-->
		<b-table id="my-table" :items="items" :per-page="perPage" :current-page="currentPage" small></b-table>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				perPage: 3,
				currentPage: 1,
				items: [{
						id: 1,
						first_name: 'Fred',
						last_name: 'Flintstone'
					},
					{
						id: 2,
						first_name: 'Wilma',
						last_name: 'Flintstone'
					},]}},
		computed: {
			rows() {
				return this.items.length}}}
</script>

```

#### 提示信息

```VUE
侧边弹框：
<b-button>
variant		颜色		颜色


简单通知
    <div>
        <!-- 定义触发源 -->
        <b-button @click="$bvToast.show('my-toast')">Show toast</b-button>
        <!-- 定义触发元素 -->
        <b-toast id="my-toast" variant="warning" solid>
            <template v-slot:toast-title>
<div class="d-flex flex-grow-1 align-items-baseline">

    <b-img blank blank-color="#ffaaff" class="mr-2" width="12" height="12"></b-img>
    <strong class="mr-auto">Notice!</strong>
    <small class="text-muted mr-2">42 seconds ago</small>
                </div>
            </template>
            <p> 通知信息</p>

        </b-toast>
    </div>
    
引用数据
<template>
	<div class="home">
		<HelloWorld />
		<div>
			<!-- 触发按钮,对应methods中的方法 -->
			<b-button @click="makeToast()">触发</b-button>
		</div>
	</div>
</template>

<script>
export default {
data() {
	return {toastCount: 0}},
		methods: {
			makeToast(append = false) {
				// 进行运算
				this.toastCount++
				this.$bvToast.toast(`提示信息+${this.toastCount}`, {
					title: '标题',
					// 自动隐藏时间
					autoHideDelay: 5000,
					appendToast: append
				})}}}
</script>
```

