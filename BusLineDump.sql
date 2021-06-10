-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: cityBusline
-- ------------------------------------------------------
-- Server version	5.7.32

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
-- Table structure for table `bus_line`
--

DROP TABLE IF EXISTS `bus_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bus_line` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `line_num` int(20) NOT NULL,
  `line_price` int(20) NOT NULL,
  `begin_time` varchar(30) NOT NULL,
  `end_time` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `line_num` (`line_num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_line`
--

LOCK TABLES `bus_line` WRITE;
/*!40000 ALTER TABLE `bus_line` DISABLE KEYS */;
INSERT INTO `bus_line` VALUES (8,1,2,'06:30','22:30'),(9,2,2,'07:00','20:30'),(10,3,2,'07:30','20:00');
/*!40000 ALTER TABLE `bus_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  `time` varchar(30) NOT NULL,
  `post_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'<p>11004689767@qq.com的第一条评论</p>','2021-05-14 13:03',6,2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_point`
--

DROP TABLE IF EXISTS `line_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line_point` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `line_id` int(20) NOT NULL,
  `point_id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `line_id` (`line_id`),
  KEY `point_id` (`point_id`),
  CONSTRAINT `line_point_ibfk_1` FOREIGN KEY (`line_id`) REFERENCES `bus_line` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `line_point_ibfk_2` FOREIGN KEY (`point_id`) REFERENCES `point` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_point`
--

LOCK TABLES `line_point` WRITE;
/*!40000 ALTER TABLE `line_point` DISABLE KEYS */;
INSERT INTO `line_point` VALUES (7,8,21),(8,8,22),(9,8,23),(10,8,24),(11,8,25),(12,8,26),(13,9,27),(14,9,28),(15,9,29),(16,9,30),(17,10,31),(18,10,32),(19,10,33),(20,10,34);
/*!40000 ALTER TABLE `line_point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_station`
--

DROP TABLE IF EXISTS `line_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line_station` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `line_id` int(20) NOT NULL,
  `station_id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `line_id` (`line_id`),
  KEY `station_id` (`station_id`),
  CONSTRAINT `line_station_ibfk_1` FOREIGN KEY (`line_id`) REFERENCES `bus_line` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `line_station_ibfk_2` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_station`
--

LOCK TABLES `line_station` WRITE;
/*!40000 ALTER TABLE `line_station` DISABLE KEYS */;
INSERT INTO `line_station` VALUES (22,8,20),(23,8,21),(24,8,22),(25,8,23),(26,8,24),(27,8,25),(28,8,26),(29,9,27),(30,9,28),(31,9,29),(32,9,24),(33,9,30),(34,9,31),(35,9,32),(36,10,33),(37,10,34),(38,10,35),(39,10,36),(40,10,25),(41,10,37),(42,10,38);
/*!40000 ALTER TABLE `line_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'jack1024','123');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  `time` varchar(30) NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) NOT NULL,
  `content` varchar(20000) NOT NULL,
  `time` varchar(30) NOT NULL,
  `type` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (2,'第一篇通知','<p>第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知第一篇通知</p>','2021-05-12 12:36',2),(3,'第二篇通知','<p>第二篇通知第二篇通知第二篇通知第二篇通知第二<strong>篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知</strong>第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知</p>\r\n<p>第二篇通知第二篇通知第二篇通知第<em>二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通</em>知第二篇通知第二篇通知第二篇通知第二篇通知第二篇通知</p>','2021-05-14 08:51',2),(4,'第三篇通知','<p>第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第<strong>三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三</strong>篇通知</p>\r\n<p><em>第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇</em>通知第三篇通知第三篇通知第三篇通知第三篇通知第三篇通知</p>','2021-05-14 08:52',3),(7,'第六篇通知','<p>第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知<sup>第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知</sup>第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知</p>\r\n<p><span style=\"background-color: #f1c40f;\">第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知第六篇通知</span></p>','2021-05-14 08:54',2),(8,'第四篇通知','<p><span style=\"text-decoration: underline;\">第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知</span></p>\r\n<p><span style=\"text-decoration: line-through;\">第四篇通知第四篇通知第四篇通知第四篇通知第四<span style=\"color: #f8cac6; text-decoration: line-through;\">篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知第四篇通知</span>第四篇通知</span></p>','2021-05-14 08:55',1),(9,'第五篇通知','<p>第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇<span style=\"font-size: 18pt;\">通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第</span>五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知</p>\r\n<p><span style=\"background-color: #169179;\">第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知第五篇通知</span></p>','2021-05-14 08:55',1),(10,'第七篇通知','<p style=\"text-align: center;\">第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知</p>\r\n<p style=\"text-align: right;\">第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知第七篇通知</p>','2021-05-14 08:56',4),(11,'第八篇通知','<p>第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知</p>\r\n<p>第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知第八篇通知</p>','2021-05-14 08:56',3),(12,'第九篇通知','<p>第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知</p>\r\n<p>第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知第九篇通知</p>','2021-05-14 08:57',2),(13,'第十篇通知','<p>第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知</p>\r\n<p>第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知第十篇通知</p>','2021-05-14 08:57',1),(14,'第十一篇贴子','<p>第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子</p>\r\n<p>第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子第十一篇贴子</p>','2021-05-14 08:57',4);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point`
--

DROP TABLE IF EXISTS `point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `point` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `x` int(20) NOT NULL,
  `y` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point`
--

LOCK TABLES `point` WRITE;
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
INSERT INTO `point` VALUES (21,0,0),(22,5,0),(23,5,11),(24,15,11),(25,15,19),(26,19,19),(27,0,3),(28,13,3),(29,13,19),(30,0,19),(31,0,5),(32,18,5),(33,18,15),(34,0,15);
/*!40000 ALTER TABLE `point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) NOT NULL,
  `content` varchar(20000) NOT NULL,
  `time` varchar(30) NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'第一篇贴子','<p>第一篇贴子第<em>一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇</em>贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子</p>\r\n<p>第一篇贴子第一<strong>篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一</strong>篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子</p>\r\n<p>第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子第一篇贴子</p>','2021-05-11 21:02',2),(2,'第二篇贴子','<p><strong>第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二</strong>篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子</p>\r\n<p>第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子第二篇贴子</p>','2021-05-11 21:46',2),(3,'第三篇贴子','<p>第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子第三篇贴子</p>','2021-05-12 10:19',2),(4,'第一篇贴子（gmail用户）','<p>第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇<strong>贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmai</strong>l用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）</p>\r\n<p>第一篇贴子（gmail用户）第一篇贴子（gmail用户）<em>第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）第一篇贴子</em>（gmail用户）第一篇贴子（gmail用户）第一篇贴子（gmail用户）</p>','2021-05-14 08:36',4),(5,'第二篇贴子（gmail用户）','<p>第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二<strong>篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子</strong>（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）</p>\r\n<p>第二篇贴子（gmail用户）第二篇贴子（<em>gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用</em>户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）第二篇贴子（gmail用户）</p>','2021-05-14 08:36',4),(6,'第三篇贴子（gmail用户）','<p>第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第<strong>三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三</strong>第三篇贴子（gmail用户）篇贴子（gmail用户）第三篇贴子（gmail用户）</p>\r\n<p>第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（g<em>mail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）第三篇贴子（gmail用户）</em></p>','2021-05-14 08:37',4);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `x` int(20) NOT NULL,
  `y` int(20) NOT NULL,
  `station_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `station_name` (`station_name`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (20,0,0,'西山公园'),(21,5,1,'人民医院'),(22,5,5,'大剧院'),(23,5,11,'体育馆'),(24,13,11,'立交桥'),(25,15,15,'无路口'),(26,19,19,'南站'),(27,0,3,'北站'),(28,8,3,'诗城小学'),(29,13,5,'广电大楼'),(30,13,17,'石油小区'),(31,7,19,'城南旅馆'),(32,0,19,'农场'),(33,0,5,'中国银行'),(34,8,5,'农贸市场'),(35,18,5,'纪念碑'),(36,18,10,'东站'),(37,7,15,'政府大楼'),(38,0,15,'西站');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `post_num` int(20) NOT NULL,
  `is_active` int(20) NOT NULL,
  `nick_name` varchar(30) NOT NULL,
  `active_code` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `nick_name` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'1004689767@qq.com','123',4,1,'jackyang','5b6abfaea1c949b18c1767c7db0a14f4'),(4,'yangfanjack1024@gmail.com','123',3,1,'yangfanjack1024','f15d2d6833cc4f45823bf20b28ee5a17');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-20 22:45:26
