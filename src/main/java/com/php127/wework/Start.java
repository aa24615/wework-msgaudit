/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */


package com.php127.wework;

import com.php127.wework.message.Threads;

import java.util.List;
import java.util.Map;
import java.io.*;


public class Start {


    public static void run() {


        System.out.println("===============init===============");

        //创建企业表
        createCorpListTable();


        List<Map<String, Object>> list = DB.getJdbcTemplate().queryForList("SELECT * FROM corplist where status=1");

        if (list.size() == 0) {
            System.out.println("没有企业可拉取");
            return;
        }

        for (Map<String, Object> map : list) {


            String corpid = String.valueOf(map.get("corpid"));
            String secret = String.valueOf(map.get("secret"));
            String prikey = String.valueOf(map.get("prikey"));


            //创建聊天记录表
            createMessageTable(corpid);

            //创建附件目录
            createMsgFileDir(corpid);

            //开启线程
            //开启线程
            Threads thread = new Threads(corpid, secret, prikey);
            thread.start();
        }

    }

    private static void createCorpListTable() {

        String sql = "select count(*) from information_schema.tables where table_name = 'corplist'";
        int count = DB.getJdbcTemplate().queryForObject(sql, Integer.class);
        System.out.println("是否创建企业表:" + count);

        if (count == 0) {

            sql = "CREATE TABLE `corplist`  (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `corpid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业id',\n" +
                    "  `secret` varchar(43) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '聊天内容存档的Secret',\n" +
                    "  `corpname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',\n" +
                    "  `prikey` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密文的私有密钥',\n" +
                    "  `limits` int(11) NOT NULL DEFAULT 100 COMMENT '一次拉取的消息条数，最大值1000条',\n" +
                    "  `timeout` int(11) NOT NULL DEFAULT 5 COMMENT '超时时间(秒)',\n" +
                    "  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0:用户无效 1:用户有效',\n" +
                    "  `update` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0:无更新 1:有更新',\n" +
                    "  PRIMARY KEY (`id`) USING BTREE,\n" +
                    "  UNIQUE INDEX `corpid`(`corpid`) USING BTREE\n" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;";

            DB.getJdbcTemplate().update(sql);
        }
    }

    /**
     * 创建聊天记录表.
     *
     * @return void
     * @author 读心印 <aa24615@qq.com>
     */
    private static void createMessageTable(String corpid) {

        String sql = "select count(*) from information_schema.tables where table_name = '" + "message_" + corpid + "' ";
        int count = DB.getJdbcTemplate().queryForObject(sql, Integer.class);
        System.out.println("是否创建聊天记录表[" + corpid + "]:" + count);

        if (count == 0) {

            sql = "CREATE TABLE `message_" + corpid + "` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `msgid` varchar(64) NOT NULL COMMENT '消息ID',\n" +
                    "  `publickey_ver` tinyint(4) DEFAULT '1' COMMENT '密钥版本',\n" +
                    "  `seq` bigint(21) DEFAULT 0 COMMENT '消息序号，最大值为2^64 -1',\n" +
                    "  `action` varchar(16) DEFAULT '' COMMENT '消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型',\n" +
                    "  `msgfrom` varchar(64) DEFAULT '' COMMENT '消息发送方id。同一企业内容为userid，非相同企业和机器人消息均为external_userid',\n" +
                    "  `tolist` text COMMENT '消息接收方ID列表，多个接收ID以逗号分隔',\n" +
                    "  `msgtype` varchar(32) DEFAULT '' COMMENT '消息类型',\n" +
                    "  `msgtime` bigint(13) DEFAULT 0 COMMENT '消息发送时间戳，utc时间，ms单位',\n" +
                    "  `text` varchar(4000) DEFAULT '' COMMENT '文本消息',\n" +
                    "  `sdkfileid` varchar(2000) DEFAULT '' COMMENT '附件ID',\n" +
                    "  `msgdata` text COMMENT '消息数据 json格式',\n" +
                    "  `status` int(11) DEFAULT '1' COMMENT '1：未加载媒体;\\r\\n2：正在加载媒体；\\r\\n3：媒体加载完成；\\r\\n4：媒体加载失败',\n" +
                    "  `media_code` int(11) DEFAULT 0 COMMENT '媒体错误码',\n" +
                    "  `media_path` varchar(1024) DEFAULT '' COMMENT '媒体文件路径',\n" +
                    "  `roomid` varchar(32) DEFAULT '' COMMENT '群聊消息的群id。如果是单聊则为空',\n" +
                    "  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                    "  PRIMARY KEY (`id`) USING BTREE,\n" +
                    "  KEY `msgid` (`msgid`) USING BTREE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;";

            DB.getJdbcTemplate().update(sql);
        }
    }

    private static void createMsgFileDir(String corpid) {

        //创建根目录
        File file = new File("./msgfile/");
        if (!file.exists()) {
            file.mkdir();
        }

        //创建企业目录
        File msgfile = new File("./msgfile/" + corpid + "/");
        if (!msgfile.exists()) {
            msgfile.mkdir();
        }
    }
}
