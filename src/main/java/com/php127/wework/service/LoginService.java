package com.php127.wework.service;

import com.php127.wework.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService extends BaseService {

    @Autowired
    private AdminDao adminDao;

    public boolean login(String username, String password){

        adminDao.findByUsername(username);
    }
}
