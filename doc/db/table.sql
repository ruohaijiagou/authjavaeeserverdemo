DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
`id` bigint(64) NOT NULL AUTO_INCREMENT,
`phone` varchar(30) NOT NULL DEFAULT '',
`password` varchar(30) NOT NULL DEFAULT '',
`wechat_mini_openid` varchar(200) NOT NULL DEFAULT '',
`wechat_mini_session_key` varchar(200) NOT NULL DEFAULT '',
PRIMARY KEY (`id`),
KEY `idx_phone` (`phone`),
KEY `idx_miniopenid` (`wechat_mini_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
`id` int(64) NOT NULL AUTO_INCREMENT,
`user_id` int(64) NOT NULL,
`nick_name` varchar(255) NOT NULL DEFAULT '',
`phone` varchar(30) NOT NULL DEFAULT '',
`birthday` timestamp NULL DEFAULT NULL,
`avatar_url` varchar(100) NOT NULL DEFAULT '',
`country` varchar(60) NOT NULL DEFAULT '',
`province` varchar(60) NOT NULL DEFAULT '',
`city` varchar(60) NOT NULL DEFAULT '',
`language` varchar(20) NOT NULL DEFAULT '',
`gender` tinyint NOT NULL DEFAULT 0,
PRIMARY KEY (`id`),
KEY `idx_userid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


