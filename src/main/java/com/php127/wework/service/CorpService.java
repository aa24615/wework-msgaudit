package com.php127.wework.service;

import com.php127.wework.DB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorpService {

    public Object getList(String page, String limit){

        int limitInt = new Integer(limit);
        int pageInt = new Integer(page);

        int n1 = pageInt * limitInt - limitInt;
        int n2 = pageInt * limitInt;

        String sql = String.format("SELECT * FROM wework_corplist LIMIT %s,%s", n1, n2);
        String sql2 = String.format("SELECT count(*) FROM wework_corplist ");


        List<Map<String, Object>> list = DB.getJdbcTemplate().queryForList(sql);
        int count = DB.getJdbcTemplate().queryForObject(sql2, Integer.class);

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("count", count);

        return data;
    }

    public boolean create(){


    }
}
