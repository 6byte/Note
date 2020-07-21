### Vue权限

概览

```
路由守卫,组件守卫,全局守卫，路由独享守卫
组件守卫:beforeRouteEnter,
```

#### route和router

```java
route包含的对象信息
{name,meta,path,hash,query,params,fullPath,mathed}
用途:获取路由参数

router包含的对象信息
app,apps,options,beforeHooks,resolveHooks,afterHooks,matcher,mode
用途:跳转路由
```





#### watch响应路由

```js
 watch: {
    '$route' (to, from) {
      // 对路由变化作出响应...
    }
```

#### 路由抓取

````JS
捕获404
/*
	1.将这个放在路由表的最后面
	2.会匹配所有路径
	3.匹配优先级，从上自下
*/
{
  path: '*',
  component:()=>{import('../Error.vue')}
}

捕获特定路径
{
  // 会匹配以 `/user-` 开头的任意路径
  path: '/user-*'
}
````

#### 路由跳转

```
1.路由跳转两种方式,编程式，声明式，重点掌握编程式
2.路由传递参数
3.路由接受参数

注意:如果	
```

##### 跳转传递参数

```js
带参跳转
router.push({ 目标, params: {参数}})
router.push({ name: 'user', params: { userId: '123' }})
```

