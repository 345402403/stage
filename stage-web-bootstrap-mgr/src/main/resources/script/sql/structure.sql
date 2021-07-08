-- 用户表
DROP TABLE IF EXISTS user_info;
CREATE TABLE
    user_info
    (
        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
        `user_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户名',
        `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '密码',
        `nick_name` VARCHAR(50) COMMENT '昵称',
        `portrait` VARCHAR(100) DEFAULT '' COMMENT '头像',
        `mobile_phone` VARCHAR(100) DEFAULT '' COMMENT '手机号',
        `wechat` VARCHAR(100) DEFAULT '' COMMENT '微信',
        `email` VARCHAR(100) DEFAULT '' COMMENT '邮箱',
        `is_enabled` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '是否启用',
        `create_user` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        `update_user` VARCHAR(50) DEFAULT NULL COMMENT '更新人',
        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON
    UPDATE
        CURRENT_TIMESTAMP COMMENT '更新时间',
        `is_delete` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '删除标识：0未删除；1已删除',
        `version` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '版本号',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;