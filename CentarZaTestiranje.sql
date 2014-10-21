CREATE DATABASE  IF NOT EXISTS `testiranje_1.1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testiranje_1.1`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: testiranje_1.1
-- ------------------------------------------------------
-- Server version	5.6.12

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
-- Table structure for table `odgovor`
--

DROP TABLE IF EXISTS `odgovor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `odgovor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pitanje_id` int(11) NOT NULL,
  `tekst` varchar(45) DEFAULT NULL,
  `tacan` int(11) DEFAULT NULL,
  `bodovi` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_odgovor_pitanje1_idx` (`pitanje_id`),
  CONSTRAINT `fk_odgovor_pitanje1` FOREIGN KEY (`pitanje_id`) REFERENCES `pitanje` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `odgovor`
--

LOCK TABLES `odgovor` WRITE;
/*!40000 ALTER TABLE `odgovor` DISABLE KEYS */;
INSERT INTO `odgovor` VALUES (1,1,'Superman',1,2),(2,1,'Batman',1,2),(3,1,'Spiderman',0,-2),(4,1,'Aquaman',0,-2),(5,1,'Thor',1,2),(6,1,'Flash',0,-2),(7,2,'Bruce Wayne',1,10),(8,2,'Klark Kent',0,-10),(9,2,'Bruce Banner',0,-10),(10,2,'Tony Stark',1,10),(11,3,'Bruce Banner',0,-5),(12,3,'Tony Stark',0,-5),(13,3,'Peter Parker',1,5),(14,3,'Klark Kent',1,5),(15,3,'Bruce Wayne',1,5),(16,3,'Steve Rogers',0,-5),(17,4,'Steve Rogers',0,-5),(18,4,'Klark Kent',1,5),(19,4,'Kal-El',1,5),(20,4,'Jor-El',0,-5),(21,5,'Tacan na prvo',1,5),(22,5,'Netacan na prvo',0,-5),(23,6,'Tacan na drugo',1,5),(24,6,'Netacan na drugo',0,-5),(25,7,'1',NULL,1),(26,7,'2',NULL,1),(27,8,'12',NULL,1),(28,9,'23',NULL,0),(29,10,'1',NULL,1),(30,10,'1',NULL,1),(31,10,'1',NULL,1),(32,11,'2',NULL,2),(33,11,'2',NULL,2),(34,11,'2',NULL,2),(35,12,'1',NULL,1),(36,12,'2',NULL,4),(37,12,'3',NULL,4);
/*!40000 ALTER TABLE `odgovor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pitanje`
--

DROP TABLE IF EXISTS `pitanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pitanje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `tekst` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pitanje_test_idx` (`test_id`),
  CONSTRAINT `fk_pitanje_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pitanje`
--

LOCK TABLES `pitanje` WRITE;
/*!40000 ALTER TABLE `pitanje` DISABLE KEYS */;
INSERT INTO `pitanje` VALUES (1,1,'Ko nosi plast?'),(2,1,'Ko nema de facto supermoci?'),(3,1,'Ko je siroce?'),(4,1,'Sta je sve Supermenovo ime?'),(5,11,'Danasnji test prvo pitanje'),(6,11,'Danasnji test drugo pitanje'),(7,20,'prvo'),(8,20,'drugo'),(9,22,'1'),(10,23,'1'),(11,23,'2'),(12,24,'1');
/*!40000 ALTER TABLE `pitanje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `polaganje`
--

DROP TABLE IF EXISTS `polaganje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `polaganje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  `uplaceno` int(11) DEFAULT NULL,
  `ostvareno_bodova` int(11) DEFAULT NULL,
  `polagano` int(11) DEFAULT '0',
  `sifra` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_polaganje_user1_idx` (`user_id`),
  KEY `fk_polaganje_test1_idx` (`test_id`),
  CONSTRAINT `fk_polaganje_test1` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_polaganje_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `polaganje`
--

LOCK TABLES `polaganje` WRITE;
/*!40000 ALTER TABLE `polaganje` DISABLE KEYS */;
INSERT INTO `polaganje` VALUES (1,3,1,1,2,1,NULL),(2,4,1,1,15,1,NULL),(3,5,1,1,25,1,NULL),(4,3,2,1,12,1,NULL),(5,4,2,1,25,1,'123'),(9,3,10,1,NULL,0,'4310216583807754'),(10,3,10,1,NULL,NULL,'4310216583807754'),(11,3,10,1,NULL,NULL,'4310216583807754'),(12,4,10,1,NULL,0,'C46I3ALRJ1'),(13,4,11,1,NULL,1,'SIFRATESTA'),(14,4,24,1,NULL,1,'KXSH8EN4XX');
/*!40000 ALTER TABLE `polaganje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  `datum_polaganja` datetime DEFAULT NULL,
  `rok_za_prijavu` datetime DEFAULT NULL,
  `najavljen` int(11) DEFAULT NULL,
  `trajanje` time DEFAULT NULL,
  `max_broj_kandidata` int(11) DEFAULT NULL,
  `potrebno_bodova` int(11) DEFAULT NULL,
  `sifra_testa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'Prepoznavanje superheroja','2014-01-01 13:00:00','2013-12-31 22:00:00',1,'00:30:00',100,25,'superheroj'),(2,'Vrste slatkisa','2014-07-10 15:00:00','2014-07-09 22:00:00',1,'00:30:00',50,25,'candy'),(3,'Test',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'Test',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'izKonstruktora','2014-07-03 21:03:30','2014-07-03 21:03:30',1,NULL,10,10,'10'),(6,'Test sa custom datumima','2014-10-10 22:00:00','2014-10-10 15:00:00',0,NULL,0,0,''),(7,'Test sa custom datumima','2014-10-10 22:00:00','2014-10-10 15:00:00',0,NULL,0,0,''),(8,'Datumi','2014-10-10 22:00:00','2014-10-10 15:00:00',0,NULL,0,0,''),(9,'GMT test','2014-10-10 20:00:00','2014-10-10 13:00:00',0,NULL,0,0,''),(10,'GMT test bez trajanja testa','2014-10-10 18:00:00','2014-10-10 16:25:00',1,NULL,10,10,'sifra'),(11,'PrimeFaces DateTime picker','2014-08-24 12:00:00','2014-08-20 08:00:00',1,NULL,11,5,'asdasd'),(13,'Demo pitanja i odgovori','2014-07-30 00:00:00','2014-07-14 00:00:00',1,NULL,123,12,'asd1234'),(14,'Demo pitanja i odgovori','2014-07-30 00:00:00','2014-07-14 00:00:00',1,NULL,123,12,'asd1234'),(15,'Brisanje pitanja cascade','2014-07-30 00:00:00','2014-07-16 00:00:00',1,NULL,12,12,'1254ss'),(17,'test','2014-07-23 00:00:00','2014-07-25 00:00:00',1,NULL,12,12,'asd'),(18,'asd','2014-07-08 00:00:00','2014-07-08 00:00:00',0,NULL,0,0,''),(19,'asd','2014-07-15 00:00:00','2014-07-09 00:00:00',0,NULL,0,0,''),(20,'7.9.2014. naslov','2014-09-10 00:00:00','2014-09-09 00:00:00',0,NULL,5,10,''),(21,'7.9.2014. naslov1','2014-09-10 00:00:00','2014-09-09 00:00:00',1,NULL,23,23,''),(22,'7.9.2014. naslov2','2014-09-24 00:00:00','2014-09-22 00:00:00',1,NULL,23,23,''),(23,'8.9.','2014-09-10 00:00:00','2014-09-09 00:00:00',1,NULL,0,15,NULL),(24,'demo','2014-09-09 12:20:00','2014-09-09 12:27:00',1,NULL,0,5,NULL);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uneseni_odgovor`
--

DROP TABLE IF EXISTS `uneseni_odgovor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uneseni_odgovor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `polaganje_id` int(11) NOT NULL,
  `odgovor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_uneseni_odgovor_polaganje1_idx` (`polaganje_id`),
  KEY `fk_uneseni_odgovor_odgovor1_idx` (`odgovor_id`),
  CONSTRAINT `fk_uneseni_odgovor_odgovor1` FOREIGN KEY (`odgovor_id`) REFERENCES `odgovor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_uneseni_odgovor_polaganje1` FOREIGN KEY (`polaganje_id`) REFERENCES `polaganje` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uneseni_odgovor`
--

LOCK TABLES `uneseni_odgovor` WRITE;
/*!40000 ALTER TABLE `uneseni_odgovor` DISABLE KEYS */;
INSERT INTO `uneseni_odgovor` VALUES (1,1,1),(2,1,2),(3,1,4),(4,1,7),(5,1,8),(6,1,17),(7,1,18),(20,13,21),(21,14,35),(22,14,36);
/*!40000 ALTER TABLE `uneseni_odgovor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `privilegija` int(11) DEFAULT NULL,
  `aktivan` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin','admin',1,1),(2,'tester','tester','tester','tester',2,1),(3,'kandidat','kandidat','kandidat','kandidat',3,1),(4,'kand','kand','kand','kand',3,1),(5,'radnik','radnik','radnik','radnik',3,1),(6,'Milos','Jankovic','milos','milos',3,0),(7,'Marko','Jankovic','marko','marko',3,0),(8,'Tester1','Tester1','tester1','tester1',2,0),(9,'t2','t2','t2','t2',2,0),(10,'t33','t3','t3','t3',2,0),(11,'UserBeanSession','UBS','ubs','ubs',3,0),(12,'8.9.testerime','8.9.testerprezime','89','89',2,0),(13,'asdasda','adasd','asd','asd',2,1);
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

-- Dump completed on 2014-10-21  8:48:56
