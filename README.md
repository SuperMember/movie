# movie
豆瓣电影展示
微信客户端(小程序+springboot)
数据库语句
CREATE TABLE `movie_comment` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`movieid` VARCHAR(50) NOT NULL DEFAULT '0' COLLATE 'utf8_unicode_ci',
	`moviecontent` VARCHAR(255) NOT NULL DEFAULT '0' COLLATE 'utf8_unicode_ci',
	`username` VARCHAR(255) NOT NULL DEFAULT '0' COLLATE 'utf8_unicode_ci',
	`created` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	`moviename` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=21
;
