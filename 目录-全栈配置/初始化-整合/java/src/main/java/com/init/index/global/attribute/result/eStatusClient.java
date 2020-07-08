package com.init.index.global.attribute.result;

/*
 * 功能:返回给普通用户的信息
 *
 * */
public enum eStatusClient {
    //    通用反馈

    //    成功反馈

    SUCCESS_SUBMIT(1 , "你成功提交了信息"),
    SUCCESS_SELECT(2 , "我们为你查询到了一些数据"),
    SUCCESS_UPDATE(3 , "我们为你查询到了一些数据"),
    SUCCESS_DELETE(4 , "你成功删除了信息"),


    //    错误提示
    ERROR_SUBMIT(-1 , "不小心出了一些状况,如事件紧急,请联系管理员"),
    ERROR_SELECT(-2 , "似乎没有找出来什么东西"),
    ERROR_UPDATE(-3 , "一次失败的修改,是哪里出错了?"),
    ERROR_DELETE(-4 , "这条数据似乎不想离开这个世界");


    private Integer STATUS;
    private String MESSAGE;

    eStatusClient(Integer status , String message) {
        STATUS = status;
        MESSAGE = message;
    }


    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }
}
