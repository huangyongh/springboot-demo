package com.hyh.fastdfs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(){
        return "Spring boot!";
    }
}
