-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: twitterdb
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tweets`
--

DROP TABLE IF EXISTS `tweets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tweets` (
  `twittID` int(11) NOT NULL AUTO_INCREMENT,
  `tweet` longtext NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `postDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`twittID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweets`
--

LOCK TABLES `tweets` WRITE;
/*!40000 ALTER TABLE `tweets` DISABLE KEYS */;
INSERT INTO `tweets` VALUES (3,' gfadasd #gimochji','koo@gmail.com','2018-12-06 22:28:20'),(4,' dasfsadasdas','koo@gmail.com','2018-12-06 22:28:28'),(5,' gasdafasdgc safasfasd','koo@gmail.com','2018-12-06 22:28:34'),(20,' arfsafdasdas @fasd','koo@gmail.com','2018-12-06 23:04:05'),(21,' fasd #gimochi','koo@gmail.com','2018-12-06 23:04:16'),(27,' fasdasfas','koo@gmail.com','2018-12-06 23:13:46'),(28,' fasdasfas','koo@gmail.com','2018-12-06 23:15:05'),(29,' fasdasfas','koo@gmail.com','2018-12-06 23:18:15'),(30,' @gimiochi','test@gmail.com','2018-12-06 23:37:57'),(31,' @gimiochi','test@gmail.com','2018-12-06 23:38:04'),(32,'#abc','test02@gmail.com','2018-12-12 15:13:19'),(51,' #abc dddd','wkgnszoq@gmail.com','2018-12-12 18:40:17'),(52,' #suckkk','wkgnszoq@gmail.com','2018-12-12 18:40:38'),(53,'#abcccc','wkgnszoq54321@gmail.com','2018-12-12 18:42:02'),(54,' #kooolaid kooliaddd','wkgnszoq54321@gmail.com','2018-12-12 18:42:12'),(55,' @wkgnszoq12 koooooldadd','wkgnszoq54321@gmail.com','2018-12-12 18:42:27');
/*!40000 ALTER TABLE `tweets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-13  2:19:29
