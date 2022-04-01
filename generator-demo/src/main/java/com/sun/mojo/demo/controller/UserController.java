package com.sun.mojo.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户平台
 */
@RestController
@RequestMapping("/demo")
public class UserController {
    /**
     * 欢迎使用
     * @param name 名称
     * @return
     */
    @GetMapping("/hello")
    public String hello(String name) {
        return "hello : " + name;
    }
}
