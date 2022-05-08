package com.qqspeed.performancedataplatform.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Object data;
    private Integer code;
    private String msg;


    public Result( Integer code,Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result( Integer code,Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result() {
    }
}
