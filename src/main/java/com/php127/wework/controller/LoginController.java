package com.php127.wework.controller;

import com.php127.wework.DB;
import com.php127.wework.Response;
import com.php127.wework.bean.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@EnableAutoConfiguration

public class LoginController extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value="login",method = RequestMethod.POST)
    public Object loginPost(Map<String,String> map){

        System.out.println(request.getScheme());

        Admin admin = new Admin();

        admin.setId(1);
        admin.setUsername("2222");
        admin.setPassword("2222");

        return Response.success(admin);
    }

}
