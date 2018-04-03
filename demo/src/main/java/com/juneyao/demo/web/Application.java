package com.juneyao.demo.web;

import com.soento.core.support.WebApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

@MapperScan(basePackages = {"com.juneyao"})
public class Application extends WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
