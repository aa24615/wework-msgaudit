package com.php127.wework.dao;

import com.php127.wework.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AdminDao extends CrudRepository<Admin, Integer> {

    List<Admin> findAll();

    Admin findByUsername(String username);

    Admin findFirstByUsername(String username);

}