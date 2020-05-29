## Vue-路由

### 概念

```
理解:路由就是通过路径访问组件
知识点:路由守卫，路由参数
```

### 路由传参

query传参

```JS
注意:
	1.可以传json
	2.会暴露在路径中
    3.传值内容寿县
传递
this.$router.push({path: '/path',query: {id: id}})


接收
this.$route.query.id ##是route接受参数不是router
```

params



跳转方式

````js

推荐:-->命名的路由
this.$router.push({ name: 'user', params: { userId: 123 }})

根据名字name跳转
this.$router.push('home')

根据path跳转
this.$router.push({ path: 'home' })


````

