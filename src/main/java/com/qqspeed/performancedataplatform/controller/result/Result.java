package com.qqspeed.performancedataplatform.controller.result;

import lombok.Data;

@Data
public class Result {
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

    public Result() {
    }
}
