package com.php127.wework.service;

import com.php127.wework.dao.AdminDao;
import com.php127.wework.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService extends BaseService {

    @Autowired
    private AdminDao adminDao;

    public boolean login(String username, String password){
        Admin admin = adminDao.findByUsername(username);

        if(admin.getId()>0){
            return true;
        }

        return false;
    }
}
