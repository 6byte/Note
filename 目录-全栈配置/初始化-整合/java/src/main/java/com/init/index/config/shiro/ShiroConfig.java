package com.init.index.config.shiro;

//需要单独导入这个包，否则报错

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;


import java.util.LinkedHashMap;
import java.util.Map;

//必须配置该注解,默认关闭
//@Component
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> prevent = new LinkedHashMap<>();

        //常用拦截:anon,authc,authcBasic,perms,port,rest,roles,ssl,user

        /*      开启拦截        */
        prevent.put("/" , "anon");                              //首页
        prevent.put("/public/**" , "anon");                     //公共页面
        prevent.put("/error/**" , "anon");                       //错误页面
        prevent.put("/login/logout" , "logout");                //用户退出
        prevent.put("/**" , "authc");                           //一定要写在最下面，避免出现不可预知的问题

        /*   返回拦截页面   */
        shiroFilterFactoryBean.setLoginUrl("/");                //登录拦截返回页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/");         //授权拦截返回页面
        shiroFilterFactoryBean.setSuccessUrl("/");              //登录成功需要访问的页面
        shiroFilterFactoryBean.setFilterChainDefinitionMap(prevent);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "defaultWebSecurityManager")
    //创建DefaultWebSecurityManager
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realmConfig") Shiro_Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //创建Realm==此处需要修改，改成自定义的Realm
    @Bean(name = "realmConfig")
    public Shiro_Realm getUserRealm() {
        return new Shiro_Realm();
    }

    /**
        功能:开启注解拦截必须配置,如@RequiresRoles,@RequiresPermissions
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
