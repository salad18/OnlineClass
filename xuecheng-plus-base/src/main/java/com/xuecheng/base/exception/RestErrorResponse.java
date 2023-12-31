package com.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @Author: SS
 * @DATE: 2023/12/31 15:35
 * @Decription: 和前端约定的返回异常信息
 * @Version 1.0
 **/
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}