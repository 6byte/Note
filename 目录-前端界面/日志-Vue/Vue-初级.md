## Vue-整理中...



概览

```
学习指南
data,mounted,methods

什么是实例:
	let vm = new Vue()
	vm就是Vue()的实例
```

### data

```
类型：函数

详细：
	1.Vue使用 递归 将data 的属性转换为 getter/setter
	2.data中的数据只能是对象
	3.实例创建之后，可以通过 vm.$data.属性 访问属性，相当于vm.$data.a。
	4.以 _ 或 $ 开头的属性 不会 被 Vue 实例代理，因为它们可能和 Vue 内置的属性、API 方法冲突。你可以使用例如 vm.$data._property 的方式访问这些属性
	5.当一个组件被定义，data 必须声明为返回一个初始数据对象的函数，因为组件可能被用来创建多个实例。如果 data 仍然是一个纯粹的对象，则所有的实例将共享引用同一个数据对象！通过提供 data 函数，每次创建一个新实例后，我们能够调用 data 函数，从而返回初始数据的一个全新副本数据对象。

如果需要，可以通过将 vm.$data 传入 JSON.parse(JSON.stringify(...)) 得到深拷贝的原始数据对象。
```

### mounted

```
类型：函数
详细：
	1.mounted 不会保证所有的子组件也都一起被挂载。
	2.如果你希望等到整个视图都渲染完毕，可以在 mounted 内部使用 vm.$nextTick：
```

### methods

```
类型：对象

详细：
	1.可以通过 VM 实例访问这些方法，或者在指令表达式中使用。
	2.methods中定义的方法不能使用箭头函数，会将this指向windows,Vue将无法访问，报错undefined
```

### computed

```
类型：{ [key: string]: Function | { get: Function, set: Function } }

详细：
	1.所有 getter 和 setter 的 this 将绑定到Vue对象中。
	2.在计算属性中使用箭头函数，this 不会指向这个组件的实例，可以把实例作为函数的第一个参数来访问。
	3.计算属性的结果会被缓存，除非依赖的响应式属性变化才会重新计算。
```

### watch

````
类型：{ [key: string]: string | Function | Object | Array }

详细：
	1.键是表达式，值是函数。
	2.值也可以是方法名。
	3.Vue实例化时调用 $watch()，遍历 watch 对象的每一个属性。
````

### props

```
类型：Array<string> | Object

详细：
	1.props 可以是数组、对象，用于接收来自父组件的数据。
	2.props 可以是简单的数组、对象，对象允许配置高级选项，如类型检测、自定义验证和设置默认值。



```

### v-html

```
1.最好少用
2.在单文件组件里，scoped 的样式不会应用在 v-html 内部
3.禁止使用在用户提交的内容中
```

### v-on

```

修饰符：
.stop - 调用 event.stopPropagation()。
.prevent - 调用 event.preventDefault()。
.capture - 添加事件侦听器时使用 capture 模式。
.self - 只当事件是从侦听器绑定的元素本身触发时才触发回调。
.{keyCode | keyAlias} - 只当事件是从特定键触发时才触发回调。
.native - 监听组件根元素的原生事件。
.once - 只触发一次回调。
.left - (2.2.0) 只当点击鼠标左键时触发。
.right - (2.2.0) 只当点击鼠标右键时触发。
.middle - (2.2.0) 只当点击鼠标中键时触发。
.passive - (2.3.0) 以 { passive: true } 模式添加侦听器

用法：
	1.用在普通元素上，只能监听原生 DOM 事件。
	2.用在自定义元素组件上时，能监听子组件触发的自定义事件。
	3.可以访问 $event：v-on:click="handle('ok', $event)"

```

### v-bind

```
修饰符：
	-.prop - 作为一个 DOM property 绑定而不是作为 attribute 绑定。
	-.camel - 将 kebab-case 特性名转换为 camelCase. 
	-.sync 会扩展成一个更新父组件绑定值的 v-on 侦听器。

```

### 实例属性

```
vm.$data	:访问组件中所有data的值
vm.$el		:输出绑定的那段html
vm.$root	:有父组件代表父组件，没有父组件就是自己
vm.$refs	:可以获取绑定的ref
vm.$watch	:返回一个取消观察函数，来停止回调
vm.$emit	：调用方法
```

### 生命周期

```
beforeCreate()
	-创建前的状态，实现加载页面

created()
	-Init (初始化) injections (依赖注入) & reactivity (开始响应)，通过ajax请求数据

beforeMount():
	-组件实例将要挂载到挂载点,开发中很少使用

mounted()
	-组件模板已经渲染到指定的el，页面显示，可以操作动态数据，dom
beforeUpdate():可以拿到Vue实例化改变前的状态。

updated()
	-关于update的钩子函数，都是页面动态渲染有关，每当数据变化是，变会页面更新，都会触发这些钩子，而且触发频率很高，极少操作

beforeDestroy():消亡前的状态。

destroyed():实例化或组件被摧毁消亡。
```

