package com.example.wcmovie.handler;

import com.example.wcmovie.exception.MovieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MovieExceptionHandler {
    @ExceptionHandler(value = MovieException.class)
    @ResponseBody
    public Map<String, Object> handlerException(MovieException e) {
        Map<String,Object> map=new HashMap<>();
        map.put("code","400");
        map.put("message",e.getMessage());
        return map;
    }
}
