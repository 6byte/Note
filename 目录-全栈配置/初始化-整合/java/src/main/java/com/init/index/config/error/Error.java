package com.init.index.config.error;

import com.init.index.global.attribute.result.ResultInfo;
import com.init.index.global.attribute.result.eStatusSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 功能:统一异常处理
 * 注意:这类异常一般由于程序设计不稳定造成，不建议把具体信息返回到客户端，可存放数据库做排错处理
 * */
@RestControllerAdvice
@Slf4j
public class Error {
    @ExceptionHandler(value = {Exception.class})
    public Object handleOtherExceptions(final Exception ex) {
        return new ResultInfo(eStatusSystem.ERROR_DATABASE.getStatus() , eStatusSystem.ERROR_DATABASE.getMsg());
    }
}