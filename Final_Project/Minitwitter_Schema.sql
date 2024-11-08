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
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `follow` (
  `followedUserID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `followedUserName` varchar(45) NOT NULL,
  `followDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`followedUserID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (19,'wkgnszoq12','wkgnszoq','2018-12-06 23:34:21'),(20,'wkgnszoq','test1234','2018-12-06 23:38:04'),(23,'test1234','jkoo5','2018-12-12 16:57:01'),(24,'gimochi','jkoo5','2018-12-12 16:57:03'),(25,'salt123','jkoo5','2018-12-12 16:57:20');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hashtag` (
  `hashtagID` int(11) NOT NULL AUTO_INCREMENT,
  `hashtag` varchar(45) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`hashtagID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (9,'#abcccc',1),(10,'#kooolaid',1);
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tweethashtag`
--

DROP TABLE IF EXISTS `tweethashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tweethashtag` (
  `twittID` int(11) NOT NULL,
  `hashtagID` int(11) NOT NULL,
  KEY `hashtagID_idx` (`hashtagID`),
  KEY `twitID_idx` (`twittID`),
  CONSTRAINT `hashtagID` FOREIGN KEY (`hashtagID`) REFERENCES `hashtag` (`hashtagid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `twitID` FOREIGN KEY (`twittID`) REFERENCES `tweets` (`twittid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweethashtag`
--

LOCK TABLES `tweethashtag` WRITE;
/*!40000 ALTER TABLE `tweethashtag` DISABLE KEYS */;
INSERT INTO `tweethashtag` VALUES (53,9),(54,10);
/*!40000 ALTER TABLE `tweethashtag` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `birthdate` varchar(45) NOT NULL,
  `password` varchar(65) NOT NULL,
  `questionNo` int(11) NOT NULL,
  `answer` varchar(45) NOT NULL,
  `lastlogin` datetime DEFAULT CURRENT_TIMESTAMP,
  `salt` varchar(65) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (32,'test koo','test1234','test@gmail.com','2018-11-06','Test1',1,'Test','2018-12-12 16:42:42',''),(33,'Gimochi Koo','gimochi','gimochi@gmail.com','2018-12-10','021c635a682ec5bb3d10b0c16789dcf950a3f4dd2b44fed924a2a85532c7830a',1,'Aa','2018-12-12 16:42:42',''),(34,'Jahoon Koo','gimochi123','gimochi123@gmail.com','2018-11-28','1422c1d0b1a679694f9413fb70325dd0461c5bb736e91fdc54b344437591682e',2,'A','2018-12-12 16:42:42',''),(35,'Jahoon ROo','salt123','salt@gmail.com','2018-12-03','e5429956513421bf2cc49915289c9468d3f002ab237cd89bbad54ef60f110405',2,'A','2018-12-12 16:42:42','EBUZTKBoBgxTM2FtlH556YGIQi/JZzKszfoKAWt9LFA='),(36,'Test Koo','Aa1234','a@gmail.com','2018-12-10','3bda73bb53126f8339bb70d507da3248aee242d4d7d6e5c845587c7faaaec0a6',1,'A','2018-12-12 16:42:42','g8GKzKqM546w5IqEH/bmrLRhwm59X+6GasiMwaEtUdY='),(37,'Jahoon Koo','wkgnszoq12','wkgnszoq@gmail.com','2018-12-04','674111881f980e607a38f868093c4e9b9ef8475ab68bb76728902dab5ca29a07',1,'Gecko','2018-12-12 18:42:49','4wobtPnTXB4HlDMjW74cjTBClNRg1kg1rrv4adTmemo='),(38,'Test Kim','tk123','tt@gmail.com','2018-12-12','6684c5afbde67f2b6af7ef4842a1f0d278805d2b0612029b8d1558a3503da6b9',1,'Cook','2018-12-12 16:42:42','d7wL/vfYOqj41rojdj0XnfrZ0eh01qKu7v7uu9Nre/A='),(39,'A A','test01','test01@gmail.com','2018-12-11','79c862b8da8c9d262d44e69a43ad8d47223574d22fa923b96f33c0ccfbee22f5',1,'gecko','2018-12-12 16:42:42','/L5RErUOYP4D1Lpdx8Mw7Pwity5/Xs0iq3w2wLTemnM='),(40,'B B','test02','test02@gmail.com','2018-12-04','ee3fd00bafa1f081707e1e68fe218adc4fbaed53bd749015bddff072ec3ece0a',2,'Car','2018-12-12 16:42:42','CHVoVQNld5o/zb5S8VXKkCWnJpgHI4sZoi4lZLQ5eKE='),(41,'SSi bal','sibal','sibal@gmail.com','2018-12-23','eb3fbebbb304cb73b76325871ae63f6a80a1533404b5cedbcdcf9d5cad0d3820',1,'cat','2018-12-12 16:42:42','uneFHjXpLnrmGOiyM+5HGQVUFXRPiUzzR8gW8JkEMHU='),(42,'Kimchi Koo','kimchi123','kimchi@gmail.com','2018-12-03','7da2a373c757038b350f652b804f7026940a0316f366659cea99d6a2cdea42b2',2,'car','2018-12-12 16:42:42','nFLPnnyikdHBQ1DsQA2MOmyyOI4/WzovXQKUArxgusg='),(43,'Jahoon Koo','jkoo5','wkgnszoq54321@gmail.com','2018-12-23','996936e9b78f6850c02eff824e61855aa3068eb00119542404fe28c29cb7d32e',2,'Car','2018-12-12 18:42:30','HnZNUjTAjzsuawHOaDTdHzKIZgGVSUpqFblhD3zGP4c='),(44,'dasd koo','abc123','abc@gmail.com','2018-12-04','98181f78d1cd1702caf897c09692009d991df2a47808359ae75848aefa13bad3',1,'cat','2018-12-12 18:31:26','oC6m34L+Du2ek+RKe63WhzZVyWw6lFsm76UynkAcVRs=');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usermentions`
--

DROP TABLE IF EXISTS `usermentions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usermentions` (
  `userMentionID` int(11) NOT NULL AUTO_INCREMENT,
  `mentionUser` varchar(45) NOT NULL,
  `tweet` longtext NOT NULL,
  `postDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userMentionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermentions`
--

LOCK TABLES `usermentions` WRITE;
/*!40000 ALTER TABLE `usermentions` DISABLE KEYS */;
/*!40000 ALTER TABLE `usermentions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-13  2:20:48
