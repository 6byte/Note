### ES6-事件属性

#### 滚动事件

滚动方法

```js
/*
	功能:定位
	x:水平
*/
window.scrollTo(X,Y);
```



判断滚动方向



封装方法

```JS
 /*
 	功能:
 		1.返回滚动条的原点的距离
 		2.处理兼容性问题
 	返回值:
 		(int)top:ju距离顶部距离
 		(int)left:距离左边距离
 */

function scroll() {
				if (window.pageYOffset != null) // ie9+ 和其他浏览器
				{
					return {
						top: window.pageYOffset,
						left: window.pageXOffset,
					}
				} else if (document.compatMode == "CSS1Compat") // 声明的了 DTD
				// 检测是不是怪异模式的浏览器 -- 就是没有 声明<!DOCTYPE html>
				{
					return {
						top: document.documentElement.scrollTop,
						left: document.documentElement.scrollLeft,
					}
				}
				return { // 剩下的肯定是怪异模式的

					left: document.body.scrollLeft,
				}
			}

```

#### 页面尺寸

```
window.innerHeight	:当前窗口的高度，根据调整发生改变
window.pageXOffset 	:水平方向滚动的距离
window.pageYOffset 	:垂直方向滚动的距离
```

