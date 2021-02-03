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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jspCommunity` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `jspCommunity`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `memberNum` int(10) unsigned NOT NULL,
  `boardNum` int(10) unsigned NOT NULL,
  `title` char(100) NOT NULL,
  `body` longtext NOT NULL,
  `views` int(10) unsigned NOT NULL DEFAULT 0,
  `hitsCount` int(10) unsigned NOT NULL DEFAULT 0,
  `like` int(10) NOT NULL,
  `unLike` int(10) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`num`,`regDate`,`updateDate`,`memberNum`,`boardNum`,`title`,`body`,`views`,`hitsCount`,`like`,`unLike`) values 
(5,'2021-01-16 17:46:48','2021-01-16 17:46:48',1,1,'가나다라','가나다라',0,0,0,0),
(8,'2021-01-16 23:41:54','2021-02-02 22:42:06',1,1,'1212','aSFAFAFS12',0,0,0,0);

/*Table structure for table `attr` */

DROP TABLE IF EXISTS `attr`;

CREATE TABLE `attr` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `relTypeCode` char(20) NOT NULL,
  `relId` int(10) unsigned NOT NULL,
  `typeCode` char(30) NOT NULL,
  `type2Code` char(30) NOT NULL,
  `value` text NOT NULL,
  `expireDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `relTypeCode` (`relTypeCode`,`relId`,`typeCode`,`type2Code`),
  KEY `relTypeCode_2` (`relTypeCode`,`typeCode`,`type2Code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `attr` */

insert  into `attr`(`id`,`regDate`,`updateDate`,`relTypeCode`,`relId`,`typeCode`,`type2Code`,`value`,`expireDate`) values 
(1,'2021-02-02 19:58:58','2021-02-02 23:08:08','member',1,'extra','isUsingTempPassword','0',NULL);

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `boardNum` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `code` char(10) NOT NULL,
  `name` char(10) NOT NULL,
  PRIMARY KEY (`boardNum`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `board` */

insert  into `board`(`boardNum`,`regDate`,`updateDate`,`code`,`name`) values 
(1,'2021-01-07 12:30:06','2021-01-07 12:30:06','notice','공지사항'),
(2,'2021-01-07 12:31:07','2021-01-07 12:31:07','guestBook','방명록'),
(3,'2021-01-07 12:31:07','2021-01-07 12:31:07','Free','자유게시판');

/*Table structure for table `like` */

DROP TABLE IF EXISTS `like`;

CREATE TABLE `like` (
  `likeNum` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `relTypeCode` char(20) NOT NULL,
  `relId` int(10) unsigned NOT NULL,
  `memberNum` int(10) unsigned NOT NULL,
  `point` int(10) DEFAULT NULL,
  PRIMARY KEY (`likeNum`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `like` */

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `memberNum` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `name` char(50) NOT NULL,
  `nickname` char(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `loginId` char(50) NOT NULL,
  `loginPw` varchar(200) NOT NULL,
  `authLevel` tinyint(2) unsigned NOT NULL DEFAULT 2 COMMENT '등록자 코멘트: 0= 탈퇴/1=정지회원/2=일반/3=인증된회원/4관리자',
  `phNum` char(30) NOT NULL,
  PRIMARY KEY (`memberNum`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `member` */

insert  into `member`(`memberNum`,`regDate`,`updateDate`,`name`,`nickname`,`email`,`loginId`,`loginPw`,`authLevel`,`phNum`) values 
(1,'2021-01-25 12:44:00','2021-02-02 11:00:00','방혜성','baobab612','banggu1997@gmail.com','baobab612','8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414',2,'010-8370-0420');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
