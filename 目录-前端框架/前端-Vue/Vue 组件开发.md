### Vue 组件开发

#### 新建组件

>位置:
>
>​	--	src/components/btn/BBtn.vue

```vue

<template>
  <div>
    组件
  </div>
</template>

<script>
export default {
  name:'b-btn'
  // 很重要,以<b-btn />方式引入
};
</script>

<style>
/* 不新建css样式，将不会产生css文件 */
.color{
  color:red;
}
</style>
```

#### 新建文件

>位置:src/component/index.js

```js

import bbtn from "./btn/BBtn.vue";
import _Vue from "vue";
const components = [bbtn];

const install = function(Vue) {
  // 判断是否安装
  if (install.installed) return;
  // 遍历注册全局组件
  components.map((component) => Vue.component(component.name, component));
};

if (typeof window !== "undefined" && window.Vue) {
  install(window.Vue);
}
//第一个参数是用于安装所有组件，第二个参数是用于单个组件引入
export default {isntall,bbtn};

```

#### 修改Package.json

```JSON
{
  "name": "project", // 使用 npm install [project]
  "version": "0.1",
  "private": false,	//必须选择 false ,否则不能发布
  "scripts": {
    "serve": "vue-cli-service serve",
    "build": "vue-cli-service build",
     "package": "vue-cli-service build --target lib ./src/components/index.js --dest com --name com_01"
      /**
      	package:自定义命令
      	--target [打包方式] 打包入口
      	--dest [输出目录]
      	--name [根目录名]
      */
  }
}
```

#### 发布NPM

```
登陆npm
在src根目录下运行 npm pulish
注意包名不与其他人重复
```

