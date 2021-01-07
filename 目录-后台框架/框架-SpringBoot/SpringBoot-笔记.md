## Spring Boot笔记

#### 接收数组参数

Bean

```java
public class Bean{
   private String[] list; // 用户ID列表
}
```

controller

```java
public class Controller{
    @PostMapping("test")
	public void addOrderItem(@RequestBody Bean param) {
}}
```

前端

```JS
uni.request({
    url: 'http://127.0.0.1:4000/test', //仅为示例，并非真实接口地址。
    data: {},//传给后端的参数
    dataType: 'json',
    method:'POST',//请求方法与后台对应
    header: {'authToken':getApp().globalData.token//带上token},
    success: (res) => {}//成功后返回
});
```



