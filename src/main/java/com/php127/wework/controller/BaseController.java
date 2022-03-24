package com.php127.wework.controller;

import com.php127.wework.utils.CrossOriginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableAutoConfiguration
@CrossOriginResponse
public class BaseController {

    @Autowired
    protected HttpServletRequest request;
}
