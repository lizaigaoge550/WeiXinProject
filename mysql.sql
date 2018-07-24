-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: myDB
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `username` varchar(1024) NOT NULL,
  `password` varchar(1024) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_hunting`
--

DROP TABLE IF EXISTS `job_hunting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_hunting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wxId` varchar(1024) DEFAULT NULL,
  `title` varchar(1024) NOT NULL,
  `category` int(11) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `text` varchar(2048) DEFAULT NULL,
  `image` varchar(1024) DEFAULT NULL,
  `top` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `viewNumber` int(11) DEFAULT NULL,
  `comments` json DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `miniText` varchar(1024) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_hunting`
--

LOCK TABLES `job_hunting` WRITE;
/*!40000 ALTER TABLE `job_hunting` DISABLE KEYS */;
INSERT INTO `job_hunting` VALUES (1,'wx_id0','t1',0,'123','dfefdsfe','http://localhost:8080/FirstPro/imageSave/1.ipg',0,1,'1000-01-01 00:00:00',0,'{}',-7,'dfe',NULL,NULL,NULL),(2,'wx_id1','t1',0,'123','dfefdsfe','http://localhost:8080/FirstPro/imageSave/1.ipg',0,1,'1000-01-01 00:00:00',0,'{\"wx_id0\": \"fdfefdfd\"}',-1,'dfe',NULL,NULL,NULL),(3,'yuyu','8klk',0,'6778686','hjhjhj','18ae5b11-476e-442e-bf55-3872bba04d18.jpg',0,0,'2018-07-24 23:19:43',0,'{}',1000,'hjhjhj','gaogj',1,1);
/*!40000 ALTER TABLE `job_hunting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-24 23:32:06
