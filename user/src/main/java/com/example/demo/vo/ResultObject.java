package com.example.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import java.io.Serializable;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 17:01
 * \* Description:
 * \
 */
@ToString
@ApiModel(value="ResultObject 대상", description="결과 대상")
public class ResultObject<T> implements Serializable {

    private static final long serialVersionUID = -2403923814218768698L;

    @ApiModelProperty(value = "결과 데이터")
    public T result;

    @ApiModelProperty(value = "상태 코드")
    public Integer code;

    @ApiModelProperty(value = "메세지")
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