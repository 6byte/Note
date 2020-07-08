package com.init.index.global.attribute.result;

/*
 * 功能:返回给后台管理的信息
 * */
public enum eStatusSystem {
    //    成功信息
    SUCCESS_SELECT(10 , "查询成功"),
    SUCCESS_UPDATE(20 , "修改成功"),
    SUCCESS_DELETE(30 , "删除成功"),
    SUCCESS_INSERT(40 , "添加成功"),


    //    错误信息
    ERROR_SELECT(-10 , "查询失败"),
    ERROR_UPDATE(-20 , "修改失败"),
    ERROR_DELETE(-30 , "删除失败"),
    ERROR_INSERT(-40 , "添加失败"),


    //    原因
    ERROR_SERVER(-10 , "服务器错误"),
    ERROR_DATABASE(-10 , "服务器错误"),
    ERROR_CLIENT(-20 , "客户端异常");

    private Integer status;
    private String msg;

    eStatusSystem(int status , String msg) {
        this.status = status;
        this.msg = msg;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
