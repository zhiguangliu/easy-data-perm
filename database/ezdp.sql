-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: easy_dp
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dp_base_property_define`
--

DROP TABLE IF EXISTS `dp_base_property_define`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_base_property_define` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sub_system_code` varchar(100) DEFAULT NULL,
  `property_code` varchar(100) NOT NULL,
  `property_name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`property_code`),
  KEY `AK_KEY_2` (`id`),
  KEY `FK_REFERENCE_12` (`sub_system_code`),
  CONSTRAINT `FK_REFERENCE_12` FOREIGN KEY (`sub_system_code`) REFERENCES `dp_sub_system` (`sub_system_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_base_property_define`
--

LOCK TABLES `dp_base_property_define` WRITE;
/*!40000 ALTER TABLE `dp_base_property_define` DISABLE KEYS */;
INSERT INTO `dp_base_property_define` VALUES (1,'SELLER_SYSTEM','AREA_CODE','区域',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_base_property_define` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_base_property_value`
--

DROP TABLE IF EXISTS `dp_base_property_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_base_property_value` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sub_system_code` varchar(100) DEFAULT NULL,
  `property_code` varchar(100) NOT NULL,
  `value_code` varchar(100) DEFAULT NULL,
  `value_name` varchar(100) DEFAULT NULL,
  `is_deleted` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`,`property_code`),
  KEY `FK_REFERENCE_5` (`property_code`),
  CONSTRAINT `FK_REFERENCE_5` FOREIGN KEY (`property_code`) REFERENCES `dp_base_property_define` (`property_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_base_property_value`
--

LOCK TABLES `dp_base_property_value` WRITE;
/*!40000 ALTER TABLE `dp_base_property_value` DISABLE KEYS */;
INSERT INTO `dp_base_property_value` VALUES (1,'SELLER_SYSTEM','AREA_CODE','京','北京',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'SELLER_SYSTEM','AREA_CODE','津','天津',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'SELLER_SYSTEM','AREA_CODE','冀','河北',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'SELLER_SYSTEM','AREA_CODE','陕','陕西',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'SELLER_SYSTEM','AREA_CODE','黑','黑龙江',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'SELLER_SYSTEM','AREA_CODE','沪','上海',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'SELLER_SYSTEM','AREA_CODE','藏','西藏',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'SELLER_SYSTEM','AREA_CODE','川','四川',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'SELLER_SYSTEM','AREA_CODE','琼','海南',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'SELLER_SYSTEM','AREA_CODE','蒙','蒙古',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'SELLER_SYSTEM','AREA_CODE','晋','山西',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'SELLER_SYSTEM','AREA_CODE','鲁','山东',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'SELLER_SYSTEM','AREA_CODE','甘','甘肃',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'SELLER_SYSTEM','AREA_CODE','青','青海',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'SELLER_SYSTEM','AREA_CODE','宁','宁夏',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'SELLER_SYSTEM','AREA_CODE','新','新疆',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'SELLER_SYSTEM','AREA_CODE','吉','吉林',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'SELLER_SYSTEM','AREA_CODE','辽','辽宁',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_base_property_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_permission`
--

DROP TABLE IF EXISTS `dp_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `metadata_id` int DEFAULT NULL,
  `permission_name` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_2` (`metadata_id`),
  CONSTRAINT `FK_REFERENCE_2` FOREIGN KEY (`metadata_id`) REFERENCES `dp_permission_metadata` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_permission`
--

LOCK TABLES `dp_permission` WRITE;
/*!40000 ALTER TABLE `dp_permission` DISABLE KEYS */;
INSERT INTO `dp_permission` VALUES (1,1,'查询客户-所有数据','OK',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'查询客户-按区域属性控制','OK',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,2,'分页查询客户-按区域属性控制','OK',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,2,'分页查询客户-所有数据','OK',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,1,'查询客户-维护人为当前用户','OK',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,2,'分页查询客户-维护人为当前用户','OK',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_permission_item`
--

DROP TABLE IF EXISTS `dp_permission_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_permission_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_id` int DEFAULT NULL,
  `item_metadata_id` int DEFAULT NULL,
  `relation` varchar(20) DEFAULT NULL,
  `value_type` varchar(20) DEFAULT NULL,
  `field_value` varchar(2000) DEFAULT NULL,
  `apply_method` varchar(20) DEFAULT NULL,
  `target_table_name` varchar(100) DEFAULT NULL,
  `field_name` varchar(100) DEFAULT NULL,
  `fleld_type` varchar(20) DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_11` (`permission_id`),
  KEY `FK_REFERENCE_3` (`item_metadata_id`),
  CONSTRAINT `FK_REFERENCE_11` FOREIGN KEY (`permission_id`) REFERENCES `dp_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_REFERENCE_3` FOREIGN KEY (`item_metadata_id`) REFERENCES `dp_permission_item_metadata` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_permission_item`
--

LOCK TABLES `dp_permission_item` WRITE;
/*!40000 ALTER TABLE `dp_permission_item` DISABLE KEYS */;
INSERT INTO `dp_permission_item` VALUES (1,1,1,'EQUAL','ALL_VALUE','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,2,'EQUAL','ALL_VALUE','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,2,1,'EQUAL','PROPERTY','AREA_CODE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,2,2,'NOT_CONTROL','CONSTANT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,3,3,'EQUAL','PROPERTY','AREA_CODE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,3,4,'NOT_CONTROL','CONSTANT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,4,3,'EQUAL','ALL_VALUE','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,4,4,'EQUAL','ALL_VALUE','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,5,1,'NOT_CONTROL','CONSTANT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,5,2,'EQUAL','USER_ID','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,6,3,'NOT_CONTROL','CONSTANT',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,6,4,'EQUAL','USER_ID','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_permission_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_permission_item_metadata`
--

DROP TABLE IF EXISTS `dp_permission_item_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_permission_item_metadata` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_metadata_id` int DEFAULT NULL,
  `target_table_name` varchar(100) DEFAULT NULL,
  `field_name` varchar(100) DEFAULT NULL,
  `field_desc` varchar(500) DEFAULT NULL,
  `field_type` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_1` (`permission_metadata_id`),
  CONSTRAINT `FK_REFERENCE_1` FOREIGN KEY (`permission_metadata_id`) REFERENCES `dp_permission_metadata` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_permission_item_metadata`
--

LOCK TABLES `dp_permission_item_metadata` WRITE;
/*!40000 ALTER TABLE `dp_permission_item_metadata` DISABLE KEYS */;
INSERT INTO `dp_permission_item_metadata` VALUES (1,1,'customer','area_code','地区编码','STRING',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'customer','maintainer_id','维系人ID','NUMBER',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,2,'customer','area_code','区域编码','STRING',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,2,'customer','maintainer_id','维系人ID','NUMBER',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_permission_item_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_permission_metadata`
--

DROP TABLE IF EXISTS `dp_permission_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_permission_metadata` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sub_system_code` varchar(100) NOT NULL,
  `operation_name` varchar(200) DEFAULT NULL,
  `operation_identifier` varchar(400) DEFAULT NULL,
  `class_name` varchar(500) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `matching_mode` varchar(20) DEFAULT NULL,
  `apply_method` varchar(20) DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_4` (`sub_system_code`),
  CONSTRAINT `FK_REFERENCE_4` FOREIGN KEY (`sub_system_code`) REFERENCES `dp_sub_system` (`sub_system_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_permission_metadata`
--

LOCK TABLES `dp_permission_metadata` WRITE;
/*!40000 ALTER TABLE `dp_permission_metadata` DISABLE KEYS */;
INSERT INTO `dp_permission_metadata` VALUES (1,'SELLER_SYSTEM','查询客户','cn.zhgliu.ezdpdemo.customer.mapper.CustomerMapper.selectList',NULL,NULL,'STRICT','EMBED',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'SELLER_SYSTEM','分页查询客户','cn.zhgliu.ezdpdemo.customer.mapper.CustomerMapper.selectPage',NULL,NULL,'LENIENT','EMBED',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_permission_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_role`
--

DROP TABLE IF EXISTS `dp_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `p_id` int DEFAULT NULL,
  `sub_system_code` varchar(100) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `memo` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_13` (`sub_system_code`),
  CONSTRAINT `FK_REFERENCE_13` FOREIGN KEY (`sub_system_code`) REFERENCES `dp_sub_system` (`sub_system_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_role`
--

LOCK TABLES `dp_role` WRITE;
/*!40000 ALTER TABLE `dp_role` DISABLE KEYS */;
INSERT INTO `dp_role` VALUES (1,NULL,'SELLER_SYSTEM',NULL,'华北地区销售经理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,'SELLER_SYSTEM',NULL,'东北地区销售经理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,'SELLER_SYSTEM',NULL,'西北地区销售经理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,'SELLER_SYSTEM',NULL,'销售总监',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,'SELLER_SYSTEM',NULL,'普通销售',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_role_permission_relation`
--

DROP TABLE IF EXISTS `dp_role_permission_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_role_permission_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dp__id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `permission_id` int DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_8` (`role_id`),
  KEY `FK_REFERENCE_9` (`dp__id`),
  CONSTRAINT `FK_REFERENCE_8` FOREIGN KEY (`role_id`) REFERENCES `dp_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_REFERENCE_9` FOREIGN KEY (`dp__id`) REFERENCES `dp_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_role_permission_relation`
--

LOCK TABLES `dp_role_permission_relation` WRITE;
/*!40000 ALTER TABLE `dp_role_permission_relation` DISABLE KEYS */;
INSERT INTO `dp_role_permission_relation` VALUES (1,NULL,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,1,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,6,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,6,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,2,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,2,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,4,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,4,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,7,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_role_permission_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_role_property_relation`
--

DROP TABLE IF EXISTS `dp_role_property_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_role_property_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `property_code` varchar(100) DEFAULT NULL,
  `sub_system_code` varchar(100) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `property_value_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_10` (`property_value_id`,`property_code`),
  KEY `FK_REFERENCE_6` (`role_id`),
  CONSTRAINT `FK_REFERENCE_10` FOREIGN KEY (`property_value_id`, `property_code`) REFERENCES `dp_base_property_value` (`id`, `property_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_REFERENCE_6` FOREIGN KEY (`role_id`) REFERENCES `dp_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_role_property_relation`
--

LOCK TABLES `dp_role_property_relation` WRITE;
/*!40000 ALTER TABLE `dp_role_property_relation` DISABLE KEYS */;
INSERT INTO `dp_role_property_relation` VALUES (1,NULL,'SELLER_SYSTEM',1,1,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,'SELLER_SYSTEM',1,2,NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,'SELLER_SYSTEM',2,5,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,'SELLER_SYSTEM',4,4,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,'SELLER_SYSTEM',2,17,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,'SELLER_SYSTEM',2,18,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,'SELLER_SYSTEM',1,3,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,'SELLER_SYSTEM',1,10,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,'SELLER_SYSTEM',1,11,NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,'SELLER_SYSTEM',1,12,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,'SELLER_SYSTEM',4,13,NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,'SELLER_SYSTEM',4,14,NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,'SELLER_SYSTEM',4,15,NULL,NULL,NULL,NULL,NULL,NULL),(14,NULL,'SELLER_SYSTEM',4,16,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_role_property_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_role_user`
--

DROP TABLE IF EXISTS `dp_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_role_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sub_system_code` varchar(100) DEFAULT NULL COMMENT '分系统编码',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户主键',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `mobile_phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `role_id` int DEFAULT NULL COMMENT '角色主键',
  `memo` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `FK_REFERENCE_14` (`sub_system_code`),
  CONSTRAINT `FK_REFERENCE_14` FOREIGN KEY (`sub_system_code`) REFERENCES `dp_sub_system` (`sub_system_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_role_user`
--

LOCK TABLES `dp_role_user` WRITE;
/*!40000 ALTER TABLE `dp_role_user` DISABLE KEYS */;
INSERT INTO `dp_role_user` VALUES (59,'SELLER_SYSTEM','2',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,'SELLER_SYSTEM','7',NULL,NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(62,'SELLER_SYSTEM','6',NULL,NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,'SELLER_SYSTEM','5',NULL,NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,'SELLER_SYSTEM','1',NULL,NULL,NULL,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,'SELLER_SYSTEM','3',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,'SELLER_SYSTEM','4',NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,'SELLER_SYSTEM','8',NULL,NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,'SELLER_SYSTEM','9',NULL,NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,'SELLER_SYSTEM','10',NULL,NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dp_sub_system`
--

DROP TABLE IF EXISTS `dp_sub_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dp_sub_system` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sub_system_code` varchar(100) NOT NULL,
  `sub_system_name` varchar(100) DEFAULT NULL,
  `default_matching_mode` varchar(20) DEFAULT NULL,
  `user_info_provider` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(100) DEFAULT NULL COMMENT '创建人主键',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` varchar(100) DEFAULT NULL COMMENT '修改人主键',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`sub_system_code`),
  UNIQUE KEY `AK_KEY_2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dp_sub_system`
--

LOCK TABLES `dp_sub_system` WRITE;
/*!40000 ALTER TABLE `dp_sub_system` DISABLE KEYS */;
INSERT INTO `dp_sub_system` VALUES (1,'SELLER_SYSTEM','销售系统','STRICT','',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dp_sub_system` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-22  0:43:15
