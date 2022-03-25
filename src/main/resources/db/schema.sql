-- ----------------------------
-- Table structure for wework_corplist
-- ----------------------------
CREATE TABLE IF NOT EXISTS `wework_corplist` (
    `id` INT ( 11 ) NOT NULL AUTO_INCREMENT COMMENT '主健',
    `corpid` VARCHAR ( 18 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业id',
    `secret` VARCHAR ( 43 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '聊天内容存档的Secret',
    `corpname` VARCHAR ( 100 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
    `limits` INT ( 11 ) NOT NULL DEFAULT 100 COMMENT '一次拉取的消息条数，最大值1000条',
    `timeout` INT ( 11 ) NOT NULL DEFAULT 5 COMMENT '超时时间(秒)',
    `status` TINYINT ( 1 ) NOT NULL DEFAULT 1 COMMENT '0:关闭 1:开启',
    `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE INDEX `corpid` ( `corpid` ) USING BTREE
    ) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for wework_admin
-- ----------------------------
CREATE TABLE IF NOT EXISTS `wework_admin` (
    `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主健',
    `username` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '用户名',
    `password` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '密码',
    `state` tinyint(1) DEFAULT '1' COMMENT '1 开启 0 禁用',
    `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET = utf8;