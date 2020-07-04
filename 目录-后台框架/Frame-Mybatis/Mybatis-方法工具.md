### Mybatis-方法工具

##### 执行任意SQL

```JAVA
@Select("${sql}")
    public List<Map<String , Object>> exec(@Param("sql") String sql);
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

