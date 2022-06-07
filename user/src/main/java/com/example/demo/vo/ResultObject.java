package com.example.demo.vo;

import java.io.Serializable;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 17:01
 * \* Description:
 * \
 */
public class ResultObject<T> implements Serializable {

    private static final long serialVersionUID = -2403923814218768698L;

    public T result;

    public Integer code;

    public String msg;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}