### Spring-属性

#### 属性注入

```
@Autowired @Qualifirer @Resource @Value
```

|   注解名    |           说明           |
| :---------: | :----------------------: |
| @Autowired  | 根据属性类型进行自动装配 |
| @Qualifirer |    根据属性名进行注入    |
|  @Resource  |  可以根据类型或名称注入  |
|   @Value    |     注入配置文件属性     |

注意

```
1.@Qualifirer要和@Autowired一起使用，@Autowired通过类型注入，类型可以有很多个，此时需要通过@Qualifirer区别
2.@Resource不是Spring的，功能一样
```

