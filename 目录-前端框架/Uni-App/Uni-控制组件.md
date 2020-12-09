### Uni-App 常用样式

#### scroll-view

```
必须保证区域正常滚动
下面示例是最低运行属性
```

##### 纵向滚动

```vue
<template>
	<scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y"
	 @scrolltoupper="upper" @scrolltolower="lower" @scroll="scroll">
		<view class="scroll-view-item uni-bg-red">A</view>
		<view class="scroll-view-item uni-bg-green">B</view>
		<view class="scroll-view-item uni-bg-blue">C</view>
	</scroll-view>
</template>
<script>
	export default {
		methods: {
			upper: function(e) {
				console.log('滚动到顶部触发');
			},
			lower: function(e) {
				console.log('滚动到底部触发');
			},
			scroll: function(e) {
				console.log('滚动就触发');
			},
		}
	}
</script>
<style>
	.scroll-Y {
		/* 必须设定高度，否则无法滚动 */
		height: 300rpx;
	}
	.scroll-view-item {
		height: 300rpx;
	}
</style>

```

##### 横向滚动

```vue
<template>
	<scroll-view class="scroll-view_H" scroll-x="true"
	 scroll-left="120">
	 <!-- scroll-left="120" 将滚动条定位到120的位置 -->
		<view id="demo1" class="scroll-view-item_H uni-bg-red">A</view>
		<view id="demo2" class="scroll-view-item_H uni-bg-green">B</view>
		<view id="demo3" class="scroll-view-item_H uni-bg-blue">C</view>
	</scroll-view>
</template>
<script>
	export default {
		methods: {
			upper: function(e) {
				console.log(e)
			},
			lower: function(e) {
				console.log(e)
			},
			scroll: function(e) {},
		}
	}
</script>
<style>
	.scroll-view_H {
		/* div之间有默认文本，选择不换行，保证三个div在同一行 */
		white-space: nowrap;
	}
	.scroll-view-item_H {
		/* 确保元素在同一行 */
		display: inline-block;
		width: 100%;
		line-height: 300rpx;
	}
</style>

```



#### swiper

```VUE
<template>
	<view>
		<swiper circular indicator-dots autoplay interval=2000>
			<swiper-item>
				<view class="swiper-item uni-bg-red">A</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item ">B</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item uni-bg-blue">C</view>
			</swiper-item>
		</swiper>
	</view>
</template>
<style>
	.swiper-item {
		line-height: 300rpx;
	}
</style>

```

