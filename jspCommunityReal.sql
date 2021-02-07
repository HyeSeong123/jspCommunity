/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.17-MariaDB : Database - jspCommunity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP DATABASE IF EXISTS `jspCommunityReal`
CREATE DATABASE jspCommunityReal
USE jspCommunityReal

/*Table structure for table `article` */

CREATE TABLE `article` (
  `num` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `regDate` DATETIME NOT NULL,
  `updateDate` DATETIME NOT NULL,
  `memberNum` INT(10) UNSIGNED NOT NULL,
  `boardNum` INT(10) UNSIGNED NOT NULL,
  `title` CHAR(100) NOT NULL,
  `body` LONGTEXT NOT NULL,
  `views` INT(10) UNSIGNED NOT NULL DEFAULT 0,
  `hitsCount` INT(10) UNSIGNED NOT NULL DEFAULT 0,
  `like` INT(10) NOT NULL,
  `unLike` INT(10) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=INNODB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

INSERT  INTO `article`(`num`,`regDate`,`updateDate`,`memberNum`,`boardNum`,`title`,`body`,`views`,`hitsCount`,`like`,`unLike`) VALUES 
(5,'2021-01-16 17:46:48','2021-01-16 17:46:48',1,1,'가나다라','가나다라',0,0,0,0),
(8,'2021-01-16 23:41:54','2021-02-02 22:42:06',1,1,'1212','aSFAFAFS12',0,0,0,0);

/*Table structure for table `attr` */

CREATE TABLE `attr` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `regDate` DATETIME NOT NULL,
  `updateDate` DATETIME NOT NULL,
  `relTypeCode` CHAR(20) NOT NULL,
  `relId` INT(10) UNSIGNED NOT NULL,
  `typeCode` CHAR(30) NOT NULL,
  `type2Code` CHAR(30) NOT NULL,
  `value` TEXT NOT NULL,
  `expireDate` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `relTypeCode` (`relTypeCode`,`relId`,`typeCode`,`type2Code`),
  KEY `relTypeCode_2` (`relTypeCode`,`typeCode`,`type2Code`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `attr` */

INSERT  INTO `attr`(`id`,`regDate`,`updateDate`,`relTypeCode`,`relId`,`typeCode`,`type2Code`,`value`,`expireDate`) VALUES 
(1,'2021-02-02 19:58:58','2021-02-02 23:08:08','member',1,'extra','isUsingTempPassword','0',NULL);

/*Table structure for table `board` */

CREATE TABLE `board` (
  `boardNum` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `regDate` DATETIME NOT NULL,
  `updateDate` DATETIME NOT NULL,
  `code` CHAR(10) NOT NULL,
  `name` CHAR(10) NOT NULL,
  PRIMARY KEY (`boardNum`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `board` */

INSERT  INTO `board`(`boardNum`,`regDate`,`updateDate`,`code`,`name`) VALUES 
(1,'2021-01-07 12:30:06','2021-01-07 12:30:06','notice','공지사항'),
(2,'2021-01-07 12:31:07','2021-01-07 12:31:07','guestBook','방명록'),
(3,'2021-01-07 12:31:07','2021-01-07 12:31:07','Free','자유게시판');

/*Table structure for table `like` */

CREATE TABLE `like` (
  `likeNum` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `regDate` DATETIME NOT NULL,
  `updateDate` DATETIME NOT NULL,
  `relTypeCode` CHAR(20) NOT NULL,
  `relId` INT(10) UNSIGNED NOT NULL,
  `memberNum` INT(10) UNSIGNED NOT NULL,
  `point` INT(10) DEFAULT NULL,
  PRIMARY KEY (`likeNum`)
) ENGINE=INNODB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `like` */

/*Table structure for table `member` */

CREATE TABLE `member` (
  `memberNum` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `regDate` DATETIME NOT NULL,
  `updateDate` DATETIME NOT NULL,
  `name` CHAR(50) NOT NULL,
  `nickname` CHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `loginId` CHAR(50) NOT NULL,
  `loginPw` VARCHAR(200) NOT NULL,
  `authLevel` TINYINT(2) UNSIGNED NOT NULL DEFAULT 2 COMMENT '등록자 코멘트: 0= 탈퇴/1=정지회원/2=일반/3=인증된회원/4관리자',
  `phNum` CHAR(30) NOT NULL,
  PRIMARY KEY (`memberNum`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `member` */

INSERT  INTO `member`(`memberNum`,`regDate`,`updateDate`,`name`,`nickname`,`email`,`loginId`,`loginPw`,`authLevel`,`phNum`) VALUES 
(1,'2021-01-25 12:44:00','2021-02-02 11:00:00','방혜성','baobab612','banggu1997@gmail.com','baobab612','8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414',2,'010-8370-0420');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
