## Vue-组件开发

#### 组件注册

定义组件(btn.vue)

```vue
<template>
	<div>
		<button>你好</button>
	</div>
</template>

<script>
	export default {name:'mbutton'}
</script>
```

注册组件(main.js)

```js
import btn from '@/components/btn.vue'
//btn.name对应上面的name
Vue.component(btn.name,MButton)
#Vue.component("组件名称","组件数据");
```

使用组件(App.vue)

```vue

<template>
	<div>
		<div>
			组件测试
            <!-- 对应上面的name:mbutton -->
			<mbutton></mbutton>
		</div>
	</div>
</template>
```

#### 参数支持

传值

```VUE
<!-- 父组件向子组件传值 -->
<template>
    <!-- key是type，子组件必须用type接收 -->
    <mbutton type="color">子组件</mbutton>
</template>
```



接收

```vue
<template>
<!-- 接收props中type的值 -->
<button :class='[`${type}`]'>
    <!-- 如果父组件向子组件添加信息，必须使用slot接收 -->
    <span><slot></slot></span>
    </button>
</template>


<script>
    export default {
        //接收 type="color"，可以通过this.type查看
        props: {
            type:{
                type:String,
                default:'color'
            },
            //接收布尔类型的参数
            plain:{
                type:Boolean,
                default:false
            }
        },
    }
</script>

```

