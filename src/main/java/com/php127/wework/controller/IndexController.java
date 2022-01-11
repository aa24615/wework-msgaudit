package com.php127.wework.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class IndexController {

    @RequestMapping("/")
    String index() {
        return "<h1>Hello World!</h1>";
    }
}
