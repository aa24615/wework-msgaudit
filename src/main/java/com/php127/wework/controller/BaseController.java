package com.php127.wework.controller;

import com.php127.wework.utils.CrossOriginResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@CrossOriginResponse
public class BaseController {

    @Autowired
    protected HttpServletRequest request;
}
