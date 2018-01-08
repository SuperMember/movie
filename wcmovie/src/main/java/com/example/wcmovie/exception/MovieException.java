package com.example.wcmovie.exception;

import com.example.wcmovie.enums.ResultEnum;

public class MovieException extends RuntimeException {
    private Integer code;

    public MovieException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MovieException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
