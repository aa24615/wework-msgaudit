package com.php127.wework.controller;

import com.php127.wework.DB;
import com.php127.wework.Response;
import com.php127.wework.data.Admin;
import com.php127.wework.utils.MD5Util
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class LoginController extends BaseController {


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object loginPost(Map<String, String> map) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String sql = String.format("SELECT * FROM wework_admin where username='%s'", username);

        try {
            Map<String, Object> data = DB.getJdbcTemplate().queryForMap(sql);

            password = MD5Util.md5(MD5Util.md5(password));
            if (!data.get("password").toString().equals(password)) {
                return Response.error("密码错误");
            }

            Admin admin = new Admin();
            admin.setId(new Integer(data.get("id").toString()));
            admin.setUsername(username);

            return Response.success(admin);

        } catch (Exception exception) {
            return Response.error("用户名错误");
        }


    }

}
