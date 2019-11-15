package com.atguigu.scw.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test01")
    public String test01(){
        return "helloThymeleaf";
    }
}
