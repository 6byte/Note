## Aop-概览

#### 注解应用

|   通知类型    |      应用      |
| :-----------: | :------------: |
|    before     |      校验      |
|     after     |     关闭流     |
| afterReturing | 无异常处理数据 |
|    around     |  执行任意操作  |
| afteThrowing  |    封装异常    |
|               |                |

#### 执行顺序

方法前运行

- before,around

方法后运行

- after
- afterReturning
- around

