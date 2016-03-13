/*
SQLyog Ultimate v9.62 
MySQL - 5.6.28-0ubuntu0.14.04.1 : Database - SocialNetwork
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`SocialNetwork` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `SocialNetwork`;

/*Table structure for table `UserData` */

DROP TABLE IF EXISTS `UserData`;

CREATE TABLE `UserData` (
  `fbid` bigint(20) unsigned NOT NULL COMMENT 'User FacebookId',
  `firstname` varchar(10) DEFAULT NULL,
  `middlename` varchar(10) DEFAULT NULL,
  `lastname` varchar(10) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `location` varchar(5) DEFAULT NULL,
  `timezone` double DEFAULT NULL,
  `updatedtime` varchar(30) DEFAULT NULL,
  `link` varchar(100) DEFAULT NULL,
  `verified` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`fbid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `UserData` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
