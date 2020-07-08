package com.init.index.config.plugin;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class Plugin {
    //    功能:Mybatis分页配置
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum" , "true");
        p.setProperty("rowBoundsWithCount" , "true");
        p.setProperty("reasonable" , "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
