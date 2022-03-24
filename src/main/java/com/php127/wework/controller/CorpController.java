package com.php127.wework.controller;


import com.php127.wework.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@RequestMapping(value="/corp")
public class CorpController extends BaseController {

    @PostMapping(  "/list")
    public Object getList(Map<String, String> map){

        return Response.success(this);
    }
}
