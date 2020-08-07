### 插件配置

#### 数据监控

概览

```
访问
http://127.0.0.1:8090/druid/index.html
```

属性配置

```
user.druid.username = admin
user.druid.passwd = 123456
```



Spring配置

```JAVA
@Configuration
public class DruidConfiguration {
    /**
     * 配置监控服务器
     */
    @Value("${user.druid.username}")
    String DruidAdmin;

    @Value("${user.druid.passwd}")
    String DruidPasswd;

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
        servletRegistrationBean.addInitParameter("allow", "192.168.25.125,127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
        servletRegistrationBean.addInitParameter("deny", "192.168.25.123");
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", DruidAdmin);
        servletRegistrationBean.addInitParameter("loginPassword", DruidPasswd);
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 配置服务过滤器
     */
    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }
}
```

