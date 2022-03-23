/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */


package com.php127.wework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class IndexController {

    @RequestMapping("/")
    public String index(Map<String,String> map){
        return "index";
    }

    @RequestMapping("/index.html")
    public String _index(Map<String,String> map){
        return "index";
    }


    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/*.html")
    public String empty(Map<String,String> map){

        map.put("name",request.getServletPath());
        return "empty";
    }
}