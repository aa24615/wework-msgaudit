package com.php127.wework.controller;

import com.php127.wework.Response;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/prikey")
public class PrikeyController extends BaseController{


    @PostMapping("/list")
    public Object getList(){

        String id = request.getParameter("id");



        return Response.success();
    }

}
