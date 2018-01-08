package com.example.wcmovie.enums;

import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-06-11 18:56
 */
public enum ResultEnum {

    FAIL(0, "获取失败"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
