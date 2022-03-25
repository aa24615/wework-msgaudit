package com.php127.wework.controller;


import com.php127.wework.DB;
import com.php127.wework.Response;
import com.php127.wework.service.CorpService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.server.Activation$ActivationSystemImpl_Stub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/corp")
public class CorpController extends BaseController {

    @PostMapping("/list")
    public Object getList() {

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        if (page == null) {
            page = "1";
        }

        if (limit == null) {
            limit = "10";
        }

        CorpService corp = new CorpService();

        return Response.success(corp.getList(page,limit));
    }

    @PostMapping("/create")
    public Object create() {
        String corpid = request.getParameter("corpid");
        String secret = request.getParameter("secret");
        String corpname = request.getParameter("corpname");
        String limits = request.getParameter("limits");
        String timeout = request.getParameter("timeout");

        CorpService corp = new CorpService();

        boolean res = corp.create(corpid,secret,corpname,limits,timeout);

        if(res){
            return Response.success();
        }

        return Response.error(corp.getErrorMessage());
    }

    @PostMapping("/update")
    public Object update() {

        String corpid = request.getParameter("corpid");
        String secret = request.getParameter("secret");
        String corpname = request.getParameter("corpname");
        String limits = request.getParameter("limits");
        String timeout = request.getParameter("timeout");

        String sql_count = String.format("SELECT count(*) FROM wework_corplist where corpid='%s'",corpid);
        if(DB.getJdbcTemplate().queryForObject(sql_count,Integer.class)==0){
            return Response.error("企业不存在");
        }


        return Response.success(this);
    }

    @PostMapping("/delete")
    public Object delete() {

        return Response.success(this);
    }
}
