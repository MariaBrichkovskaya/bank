package com.bank.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${properties.test}")
    private String test;

    @RequestMapping("/test")
    public String getTest() {
        return test;
    }
}
