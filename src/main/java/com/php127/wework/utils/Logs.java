/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */

package com.php127.wework.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void debug(String text) {
        logger.debug(text);
    }

    public static void info(String text) {
        logger.info(text);
    }


    public static void error(String text) {
        logger.error(text);
    }

}
