package com.init.index.config.web;

import com.init.index.config.plugin.SpringContextUtil;
import com.init.index.config.redis.RedisUtil;
import com.init.index.global.utils.common.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class WebInterceptor implements HandlerInterceptor {

    /*
     * 功能:截取用户请求，统计用户访问量
     * */
    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) {
        AbstractApplicationContext ac = (AbstractApplicationContext) SpringContextUtil.getApplicationContext();
        String remoteAddr = Tools.getRemoteAddr(request);
        RedisUtil jedis = ac.getBean(RedisUtil.class);
        jedis.sadd("pv" , remoteAddr);
        return true;
    }
}
