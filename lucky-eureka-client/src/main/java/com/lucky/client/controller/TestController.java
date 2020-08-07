package com.lucky.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${list}")
    private String port;

    @RequestMapping("/list")
    public String query(){
        return "端口:" + this.port;
    }
}
