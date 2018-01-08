package com.example.wcmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WcmovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcmovieApplication.class, args);
    }
}
