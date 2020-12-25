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
///////////////
//	需要改动  //
///////////////
import bbtn from "./btn/BBtn.vue";
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
export default {install,bbtn};

```

#### 修改Package.json

```
注意:
	需要修改 "private" "package" "main"三个配置
	修改需要更改版本号，

```



```JSON
{
  "name": "project",					||	发布需要修改,不可与npm仓库中任何名字重复
  "version": "1.0.1",					||  修改时需要改动
  "private": false,						|| 	必须改为false
  "main": "dist/vtext.umd.min.js",		||	指定 使用者 import 得到的文件
  "scripts": {
    "serve": "vue-cli-service serve",
    "build": "vue-cli-service build",
    "pack": "vue-cli-service build --target lib src/com/index.js"
    // "自定义指令": "vue-cli-service build --target lib src/com/index.js"
  }
}

```

#### 发布NPM

```
登陆npm
在src根目录下运行 npm pulish
注意包名不与其他人重复
```

##### .npmignore

```
.*
*.md
*.yml
build/
node_modules/
src/
test/
gulpfile.js
```

