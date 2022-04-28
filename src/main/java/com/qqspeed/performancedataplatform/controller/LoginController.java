package com.qqspeed.performancedataplatform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/test")
    public String Test(){
        return "Hello World";
    }
}
