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
  `views` int(10) unsigned NOT NULL,
  `hitsCount` int(10) unsigned NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`num`,`regDate`,`updateDate`,`memberNum`,`boardNum`,`title`,`body`,`views`,`hitsCount`) values 
(1,'2021-01-07 12:34:25','2021-01-07 12:34:25',1,1,'제목1','내용1',0,0),
(2,'2021-01-07 12:34:47','2021-01-07 12:34:47',1,1,'제목2','내용2',0,0),
(3,'2021-01-07 12:35:03','2021-01-07 12:35:03',1,1,'제목3','내용3',0,0),
(4,'2021-01-11 09:27:27','2021-01-11 09:27:29',1,2,'제목4','내용4',0,0);

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
  PRIMARY KEY (`memberNum`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `member` */

insert  into `member`(`memberNum`,`regDate`,`updateDate`,`name`,`nickname`,`email`,`loginId`,`loginPw`,`authLevel`) values 
(1,'2021-01-07 12:27:17','2021-01-07 12:27:17','홍길동','의적홍길동','banggu1997@gamil.com','baobab612','baobab0612',2),
(2,'2021-01-07 12:28:32','2021-01-07 12:28:32','홍길순','홍길동동생','banggu1997@gamil.com','hong','hong',2),
(3,'2021-01-08 20:37:49','2021-01-08 20:37:52','임꺽정','잭더리퍼','banggu1997@gmail.com','jack','쟤가걔야',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
