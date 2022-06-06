package com.php127.wework.service;

import com.php127.wework.dao.AdminDao;
import com.php127.wework.entity.Admin;

public class LoginService extends BaseService {


    public boolean login(String username, String password){

        Admin admin = AdminDao.findByUsername(username);

        if(admin.getId()>0){


            return true;
        }

        return false;
    }
}
