### Mybatis-方法工具

##### 执行任意SQL

```JAVA
@Select("${sql}")
public List<Map<String , Object>> exec(@Param("sql") String sql);
```



执行LIKE

```
 select * from message where name like '%${name} %' 
```



##### 拦截Sql

```JAVA
@Autowired
private SqlSessionFactory sqlSessionFactory;

String sql = sqlSessionFactory
    .getConfiguration()
    .getMappedStatement("com.example.Mapper.selectList")
    .getBoundSql(users)
    .getSql();

//getMappedStatement(MAPPER方法全名)
//getBoundSql(参数)
```

##### 分页插件

使用

```JAVA
@RequestMapping("/list")
public PageInfo<DiscussPost> index(Integer page , Integer size) {
    //配置PagerHelper的分页
    PageHelper.startPage(page,size);
    //获取List结果
    List<DiscussPost> list = discussMapper.selectList();
    //注意此处:会返回一个List列表，除了结果以外附带下面信息
    return new PageInfo<>(list);
}
```

配置

```JAVA
@Configuration
public class BeanConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum","true");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
```

