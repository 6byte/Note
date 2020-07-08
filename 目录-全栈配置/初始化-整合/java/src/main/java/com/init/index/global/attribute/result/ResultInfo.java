package com.init.index.global.attribute.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/*
* 功能:返回无分页的信息
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResultInfo<T> {
    private String sys_msg;
    private Integer sys_status;
    private T data;

    //    不需要返回数据
    public ResultInfo(Integer status, String msg ) {
        this.sys_msg = msg;
        this.sys_status = status;
    }
    public ResultInfo(Integer status, String msg, T data ) {
        this.sys_msg = msg;
        this.sys_status = status;
        this.data = data;
    }
}
