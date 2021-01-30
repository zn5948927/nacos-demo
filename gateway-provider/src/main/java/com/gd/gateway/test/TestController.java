package com.gd.gateway.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/echo")
    public String echo() {
        return "hello world";
    }

    @GetMapping("/payment/get/{id}")
    public String payment(@PathVariable("id") Long id) {
        return "7011";
    }

    @GetMapping("/user/get/{id}")
    public String user(@PathVariable("id") Long id) {
        return "7011-11";
    }
}
