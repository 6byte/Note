## Jquery应用组件

#### 阻止默认事件

```
e.preventDefault()
```

#### 判断滚轮上下移动

```js
$(document).on("mousewheel DOMMouseScroll", function (e) {
        var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
                (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox
        if (delta > 0) {
            // 向上滚
            console.log("wheelup");

        } else if (delta < 0) {
            // 向下滚
             console.log("wheeldown");
        }
    });
```



#### 右键菜单

```
window.oncontextmenu=function(e){
    //取消默认的浏览器自带右键 很重要！！
    e.preventDefault();
}
```

