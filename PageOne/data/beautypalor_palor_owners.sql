-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: beautypalor
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `palor_owners`
--

DROP TABLE IF EXISTS `palor_owners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `palor_owners` (
  `palar_ownersID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `firstaname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `personal_telephoneN0` int NOT NULL,
  `home_telephone` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`palar_ownersID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `palor_owners`
--

LOCK TABLES `palor_owners` WRITE;
/*!40000 ALTER TABLE `palor_owners` DISABLE KEYS */;
INSERT INTO `palor_owners` VALUES (1,'Mr','AAA','BBB',7654,8765,'AAA','AAAA','123'),(2,'Mr','AAA','BBB',87878,98765,'afafg','yyyyy','123'),(3,'Mr','AA','BB',8654345,987654,'kalmunai','abc@gmail.com','123'),(4,'Mr','AAA','BBB',87654,8765,'aaaaa','gahaga','1234'),(5,'Miss','Nuha','Fareed',767676415,415,'kalmunai','abcd@gmail.com','4444'),(6,'Miss','Nuha','Fareed',767676415,415,'kalmunai','abcd@gmail.com','0000'),(7,'Mr','yusra','Naseer',767676415,801,'kalmunai','aaaa@gmail.com','1234'),(8,'Mr','vidrarshana','wichramasingha',771135400,84,'colombo','vidhy@gmail.com','3333'),(9,'Mr','sdn','fds',864256784,65,'kandy','abcd@gmail.com','9999'),(10,'Mr','sd','fhg',897654324,415,'kalmunai','abcd@gmail.com','6666'),(11,'Mr','ka','tu',113546789,451,'kandy','abcd@gmail.com','1234'),(12,'Mr','nuha','fareed',876543568,56,'kalmunai','abcg@gmail.com','1234'),(13,'Mr','Aadithya','Rathnayaka',767676415,45,'kandy','abcd@gmail.com','1234');
/*!40000 ALTER TABLE `palor_owners` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-17 17:23:31
