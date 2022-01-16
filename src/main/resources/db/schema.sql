-- ----------------------------
-- Table structure for wework_corplist
-- ----------------------------
CREATE TABLE IF NOT EXISTS `wework_corplist` (
    `id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
    `corpid` VARCHAR ( 18 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业id',
    `secret` VARCHAR ( 43 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '聊天内容存档的Secret',
    `corpname` VARCHAR ( 100 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
    `prikey` VARCHAR ( 2048 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密文的私有密钥',
    `limits` INT ( 11 ) NOT NULL DEFAULT 100 COMMENT '一次拉取的消息条数，最大值1000条',
    `timeout` INT ( 11 ) NOT NULL DEFAULT 5 COMMENT '超时时间(秒)',
    `status` TINYINT ( 1 ) NOT NULL DEFAULT 1 COMMENT '0:用户无效 1:用户有效',
    `update` TINYINT ( 1 ) NOT NULL DEFAULT 0 COMMENT '0:无更新 1:有更新',
    PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE INDEX `corpid` ( `corpid` ) USING BTREE
    ) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for wework_admin
-- ----------------------------
CREATE TABLE IF NOT EXISTS `wework_admin` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `username` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '',
    `password` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '',
    `state` tinyint(1) DEFAULT '1',
    `created_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET = utf8;