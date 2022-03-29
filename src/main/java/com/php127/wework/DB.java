/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */

package com.php127.wework;


import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class DB {

    public static DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //jdbc:mysql://地址:端口/数据库名称
        dataSource.setUrl("jdbc:mysql://mysql:3306/test?useSSL=false&characterEncoding=utf-8&autoReconnect=true");
        //用户名
        dataSource.setUsername("root");
        //密码
        dataSource.setPassword("root");
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate() {

        JdbcTemplate jdbcTemplate = null;

        jdbcTemplate = new JdbcTemplate(getDataSource());

        return jdbcTemplate;
    }
}
