package com.gd.gateway.internal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/rest-template")
    public String test() {
       return restTemplate.getForObject("http://service-provider/echo", String.class);
    }
}
