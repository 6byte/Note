## ES6-高级特性

### 模块化

```
功能单一，职责明确
```

##### 导入导出

```js
#导出
let A = 123
function func(){..}
export default{
	A,func
}

#导入
import  module from "./module/chain.js"
export default {
    mounted:()=>{
        console.log(module.A,module.func);
    }
}
```

##### 单文件导入导出

```JS
#导出
export A = 123
export B = "value"

#导入
import * as module from "./module/ES6.js"
console.log(module.A)
```

