## VueX-基本使用

### VueX-概念

```
1.Vue组件传值不方便，Vuex很好的解决了该问题
2.数据处理都放在Vuex中
3.vuex是单向数据
4.vuex的内容全局共享

```

#### 快速使用

```js
定义-state
state: {
	view:100
}
```



#### 了解

```
state: 				中心数据
mutations: 			修改中心数据
actions: 			异步数据处理
modules: 			模块化
getters：			计算
```

### VueX-使用

#### state

```js
特点:<作用域:当前模块>
1.只读不可写
2.作用域:仅在当前模块有效

--	定义

给state赋值

	state: {
		number : 100
	}


```





#### mutations

```js
特点:<作用域:全局>
作用:修改仓库的数据

-- store.js：
	state: {number : 100},
	mutations: {
        //param是从vue中传过来的
		mut:(state,param)=>{
			state.number = 1.52
            state.number = param
		}
	},

-- xx.vue

change:function(id){
    //this.$store.commit('mutations中的函数',从vue传过来的参数)
    this.$store.commit('mut',10)
    
}

```





#### getters

```js
作用:对仓库数据经行计算，不能更改修改源数据
特点:<作用域:全局>
1.getter是一个对象，需要自定义函数来处理逻辑关系
使用步骤：
定义
1.在getters中自定义逻辑处理函数
2.函数需要有返回值，以供外部调用
export default new Vuex.Store({
	state: {
		number : 100,
	},
	//getter可以返回一个计算的值
	getters:{
		calc:state=>{
			return state.number*100
		}
	},
})

调用
$store.getters.calc
```

#### actions

```js
作用:处理异步请求，如setInterver,Axios
特点:<作用域:全局>
1.使用dispatch调用

使用步骤
1.在actions自定义异步处理函数
state: {
		number : '',
},
mutations: {
	setData:(state,param)=>{
		state.number = param.goods
}},
actions: {
	getData:function(store){
		axios.get('..').then((res) => {
        //将获得的参数返回给setData函数处理
        //可以考虑返回一个对象
         store.commit('setData',{goods:res.data})   
        });}}
    
2.使用普通方法调用
 methods:{
	  change:function(id){
		//this.$store.dispatch('action方法名',值)
		 this.$store.dispatch('getData')
	  }
  }      
梳理逻辑
从下往上执行，顺序
1.普通方法->actions->mutations->state(数据中心)
```

#### modules

```
特点:<作用域:全局>
作用:将state，mutations，actions，modules，getters集成到一个模块，精简代码结构
```

