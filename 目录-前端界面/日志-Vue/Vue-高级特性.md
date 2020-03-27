## Vue-高级特性

### 路由

#### 重要概念

```
理解:路由就是通过路径访问组件
知识点:路由守卫，路由参数
```

#### 路由传数

##### PATH传参

```js

query传参: 
	1.简单易用
传递
this.$router.push({
        path: '/describe',
        query: {id: id}})

接收
this.$route.query.id

路径传参
	1.需要配置路由
    2.可以使用正则对路径进行设置
getDescribe(id) {
      // 直接调用$router.push 实现携带参数的跳转
      this.$router.push({
        path: `/describe/${id}`
      })
},
路由配置
     {
     path: '/describe/:id',
     component: Describe
   }

获取
this.$route.params.id
```

##### 编程跳转

```
作用:权限控制
```



##### 参数限制

```JS
作用:防止参数传递错误，跳转到不应该显示的组件上
1.使用正则表达式限制参数
2.符合规则显示组件，不符合不显示
{
    path: '/about/:id(\\d{2})',
    name: 'about',
    component: () => import('../views/About.vue')
}


```

##### 默认参数

```js
作用:如果路径中没有传递需要的参数，则传递一个默认的参数
实现:
1.在路径后添加	?号
 path: '/about/:id?'
2.在初始化页面时给定一个默认值
To be Continue...
```

##### 路由别名

```js
作用:改变访问路由的方式，从路径访问到名字访问
1.定义名字
  {
    path: '/about',
    name: 'about',//定义名字
    component:...
  }
 2.访问
<router-link 
:to="{name:content,params:{id:12}}">跳转</router-link>

//v-bind绑定to之后，可能经过Vue的处理再渲染
//params是一个对象
```

##### 嵌套路由

```js
作用:路由中再嵌套路由
嵌套路由实现:
1.父子路由都是数组，可以定义多个路径和组件

const routes = [{
path: '/about',name: 'about',
//子路由,嵌套路由定义
children:[{
            //为了区别,上层路径最好与父路由保持一致
path:'/about/child',
component: () => import( '../views/Children.vue')}],
    //此处省略父路由代码
    ....
  }
]
```

404页面

```js
作用:处理不存在的资源地址
{
    path: '*',//匹配任何路径
    component: () => import('../views/Error.vue')
},
```

##### 路由函数

```
this.$router：路径跳转
1.基本使用

// 字符串
      this.$router.push('home')
// 对象
      this.$router.push({ path: 'home' })
// 命名的路由
      this.$router.push({ name: 'user', params: { userId: 123 }})
// 带查询参数，变成 /register?plan=123
      this.$router.push({ path: 'register', query: { plan: '123' }})
      
2、this.$router.replace()
   跳转到指定的url，不会向history添加记录
    
3、this.$router.go(n)
   n可为正数可为负数。正数返回上一个页面
   
   
2.this.$route：路径信息
1.$route.path
      对应当前路由的路径，总是解析为相对路径，如 "/foo/bar"。
2.$route.params
      一个 key/value 对象，包含了 动态片段 和 全匹配片段，
      如果没有路由参数，就是一个空对象。
**3.$route.query**
      一个 key/value 对象，表示 URL 查询参数。
      例如，对于路径 /foo?user=1，则有 $route.query.user == 1，
      如果没有查询参数，则是个空对象。
**4.$route.hash**
      当前路由的 hash 值 (不带 #) ，如果没有 hash 值，则为空字符串。锚点
**5.$route.fullPath**
      完成解析后的 URL，包含查询参数和 hash 的完整路径。
**6.$route.matched**
      数组，包含当前匹配的路径中所包含的所有片段所对应的配置参数对象。
**7.$route.name    当前路径名字**
**8.$route.meta  路由元信息


router.beforeEach((to, from, next) => {
  // to 和 from 都是 路由信息对象
})
watch: {
  $route(to, from) {
     // to 和 from 都是 路由信息对象
  }
```

##### 路由守卫

##### 路由懒加载

##### 跨域请求



### 组件开发



