/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */
package com.php127.wework;

import com.php127.wework.controller.IndexController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
public class Application {

    /**
     * 主方法
     */
    public static void main(String[] args) {
        SpringApplication.run(IndexController.class, args);
    }
}