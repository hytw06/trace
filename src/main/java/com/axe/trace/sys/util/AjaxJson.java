package com.axe.trace.sys.util;

import java.util.LinkedHashMap;

/**
 * 后端返回数据封装类
 * */
public class AjaxJson {

    private boolean success = true;// 是否成功
    private String errorCode = "-1";//错误代码
    private String msg = "操作成功";// 提示信息
    private LinkedHashMap<String, Object> body = new LinkedHashMap<>();//封装json的map

    public void put(String key, Object value) {
        body.put(key, value);
    }

    public void remove(String key) {
        body.remove(key);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LinkedHashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(LinkedHashMap<String, Object> body) {
        this.body = body;
    }

}
