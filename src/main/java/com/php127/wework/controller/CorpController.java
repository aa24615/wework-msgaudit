package com.php127.wework.controller;


import com.php127.wework.DB;
import com.php127.wework.Response;
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
        if (page == null) {
            page = "1";
        }
        int page1 = new Integer(page);
        int n1 = page1 * 10 - 10;
        int n2 = page1 * 10;

        String sql = String.format("SELECT * FROM wework_corplist LIMIT %s,%s", n1, n2);
        String sql2 = String.format("SELECT count(*) FROM wework_corplist ");


        List<Map<String, Object>> list = DB.getJdbcTemplate().queryForList(sql);
        int count = DB.getJdbcTemplate().queryForObject(sql2, Integer.class);

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("count", count);
        return Response.success(data);
    }

    @PostMapping("/create")
    public Object create() {
        String corpid = request.getParameter("corpid");
        String secret = request.getParameter("secret");
        String corpname = request.getParameter("corpname");
        String limits = request.getParameter("limits");
        String timeout = request.getParameter("timeout");

        String sql = "INSERT INTO `wework_corplist`" +
                "(`corpid`, `secret`, `corpname`, `limits`, `timeout`)" +
                " VALUES (?,?,?,?,?)";


        int res =  DB.getJdbcTemplate().update(sql, corpid, secret, corpname, limits, timeout);
        if(res>0){
            return Response.success();
        }

        return Response.error();
    }

    @PostMapping("/update")
    public Object update() {

        return Response.success(this);
    }

    @PostMapping("/delete")
    public Object delete() {

        return Response.success(this);
    }
}
