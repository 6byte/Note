## 插件

### Mysql-Mybatis

#### 快速使用

```JAVA
@Repository
public interface Mapper extends BaseMapper<User> {}
//仅仅需要继承BaseMapper
！！一定要填写泛型
```

#### 分页

配置

```Java
@Bean
public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
    return paginationInterceptor;
}
```

使用

```java
解释:
Page page = new Page(pageNum,pageSize);
Page page = new Page(1,100);
/*
  	参数一:当前页
  	参数二:页面大小
  	解释,从第1条开始,返回后面的100条数据
 */

@RequestMapping("/index")
public List<User> getList(Integer start , Integer pageSize) {
    Page page = new Page(0,2);
    Page result = mapper.selectPage(page , null);
    return result.getRecords();
	可以考虑所有结果,更方便前端分页显示
//    return mapper.selectPage(page , null);
    
}



```

#### 删除

|           方法名            |      解释       |
| :-------------------------: | :-------------: |
|   deleteById(Integer  ID)   |   根据ID删除    |
| deleteBatchIds(HashSet set) |  传入一个集合   |
|  deleteByMap(HashMap map)   | 根据HASHMAP删除 |
|                             |                 |

示例

```java
//批量删除
HashSet<Integer> map = new HashSet<>();
    map.add(1);
    map.add(2);
mapper.deleteBatchIds(map);

/*
	根据map条件删除
	删除username = name的所有值
	key类型必须为String
*/
HashMap<String, Object> hashMap = new HashMap<>();
hashMap.put("username", "name");
mapper.deleteByMap(hashMap);
```

逻辑删除

配置

```
#配置逻辑删除
#已经删除的值
mybatis-plus.global-config.db-config.logic-delete-value=0

#没有删除的值
mybatis-plus.global-config.db-config.logic-not-delete-value=1
```

使用

```java
//和普通删除一样
@RequestMapping("/index")
public void delete(Integer id ) {
mapper.deleteById(id);
}
```



#### 查询

##### selectByMap

概念

```
通过Map查询
1.key:对应数据库中的字段
2.value:对应数据库的值
返回:list<Bean>
```

举例

```JAVA
//构造HASHMAP
HashMap<String, Object> hashMap = new HashMap<>();
//查询数据库中username = jhon 的列表
hashMap.put("username", "jhon");
List<User> users = mapper.selectByMap(hashMap);
//输出
users.forEach(System.out::println);
```

#### 条件构造器

单个查询

```java
整体概叙:
	QueryWrapper查询条件中的结果
	selectOne(wrapper)对该结果进行筛选
QueryWrapper<User> wrapper = new QueryWrapper<>();
//查询字段为username ,值为name的所有值
wrapper.eq("username", "name");
//上面条件中仅返回一个结果
mapper.selectOne(wrapper);
```

常用

```

```

