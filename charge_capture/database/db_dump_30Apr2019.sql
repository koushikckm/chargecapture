-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 192.168.1.186    Database: chargecapturenew
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.14-MariaDB-1:10.3.14+maria~bionic

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
-- Table structure for table `attendingdoctor`
--

DROP TABLE IF EXISTS `attendingdoctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendingdoctor` (
  `provider_id` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`provider_id`),
  UNIQUE KEY `provider_id_UNIQUE` (`provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendingdoctor`
--

LOCK TABLES `attendingdoctor` WRITE;
/*!40000 ALTER TABLE `attendingdoctor` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendingdoctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chargecaptureuser`
--

DROP TABLE IF EXISTS `chargecaptureuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chargecaptureuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `modified_by` varchar(100) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `account_locked` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chargecaptureuser`
--

LOCK TABLES `chargecaptureuser` WRITE;
/*!40000 ALTER TABLE `chargecaptureuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `chargecaptureuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpdcodes`
--

DROP TABLE IF EXISTS `cpdcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cpdcodes` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpdcode` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `cpdcode_UNIQUE` (`cpdcode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpdcodes`
--

LOCK TABLES `cpdcodes` WRITE;
/*!40000 ALTER TABLE `cpdcodes` DISABLE KEYS */;
INSERT INTO `cpdcodes` VALUES (1,'CPT-10040','Incision and Drainage Procedures on the Skin'),(2,'CPT-50593','Under Lithotripsy and Ablation Procedures on the Kidney'),(3,'CPT-52601','REMOVAL OF PROSTATE (TURP)');
/*!40000 ALTER TABLE `cpdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ethnicitydetail`
--

DROP TABLE IF EXISTS `ethnicitydetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ethnicitydetail` (
  `ethnicity_id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ethnicity_id`),
  UNIQUE KEY `ethnicity_id_UNIQUE` (`ethnicity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ethnicitydetail`
--

LOCK TABLES `ethnicitydetail` WRITE;
/*!40000 ALTER TABLE `ethnicitydetail` DISABLE KEYS */;
INSERT INTO `ethnicitydetail` VALUES (0,'ethinicity');
/*!40000 ALTER TABLE `ethnicitydetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility`
--

DROP TABLE IF EXISTS `facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility` (
  `facility_id` int(11) NOT NULL,
  `facility_group_id` int(11) NOT NULL,
  `clia_number` varchar(45) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `pos_id` int(11) DEFAULT NULL,
  `phone1` varchar(15) DEFAULT NULL,
  `phone2` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `tax_id` varchar(45) DEFAULT NULL,
  `provider_rep_id` int(11) DEFAULT NULL,
  `is_deleted` int(1) DEFAULT NULL,
  `facility_npi` varchar(45) DEFAULT NULL,
  `group_npi` varchar(45) DEFAULT NULL,
  `is_emergency` int(1) DEFAULT NULL,
  PRIMARY KEY (`facility_id`),
  UNIQUE KEY `facility_id_UNIQUE` (`facility_id`),
  KEY `fk_address_id1_idx` (`address_id`),
  KEY `fk_address_id2_idx` (`address_id`),
  KEY `fk_facility_group_id1_idx` (`facility_group_id`),
  CONSTRAINT `fk_address_id2` FOREIGN KEY (`address_id`) REFERENCES `facilityaddress` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_facility_group_id1` FOREIGN KEY (`facility_group_id`) REFERENCES `facilitygroup` (`facility_group_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility`
--

LOCK TABLES `facility` WRITE;
/*!40000 ALTER TABLE `facility` DISABLE KEYS */;
INSERT INTO `facility` VALUES (0,0,'clia','name',1,0,'phone1','phone2','fax','tax_id',0,0,'npi','npi',0);
/*!40000 ALTER TABLE `facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilityaddress`
--

DROP TABLE IF EXISTS `facilityaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facilityaddress` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `address_street1` varchar(45) DEFAULT NULL,
  `address_street2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zipcode` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilityaddress`
--

LOCK TABLES `facilityaddress` WRITE;
/*!40000 ALTER TABLE `facilityaddress` DISABLE KEYS */;
INSERT INTO `facilityaddress` VALUES (1,'address_street1','address_street2','city','st','Zipcode');
/*!40000 ALTER TABLE `facilityaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilitygroup`
--

DROP TABLE IF EXISTS `facilitygroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facilitygroup` (
  `facility_group_id` int(11) NOT NULL,
  `facility_group_guid` int(11) DEFAULT NULL,
  `facility_group_name` varchar(100) DEFAULT NULL,
  `facility_group_prefix` varchar(50) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  `npi` varchar(45) DEFAULT NULL,
  `tax_id` varchar(45) DEFAULT NULL,
  `clia_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`facility_group_id`),
  UNIQUE KEY `facility_group_id_UNIQUE` (`facility_group_id`),
  UNIQUE KEY `facility_group_guid_UNIQUE` (`facility_group_guid`),
  KEY `fk_address_id1_idx` (`address_id`),
  CONSTRAINT `fk_address_id1` FOREIGN KEY (`address_id`) REFERENCES `facilityaddress` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilitygroup`
--

LOCK TABLES `facilitygroup` WRITE;
/*!40000 ALTER TABLE `facilitygroup` DISABLE KEYS */;
INSERT INTO `facilitygroup` VALUES (0,0,'group','g',1,0,'npi','tax','clia');
/*!40000 ALTER TABLE `facilitygroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icdcodes`
--

DROP TABLE IF EXISTS `icdcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icdcodes` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `icdcode` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `icdcode_UNIQUE` (`icdcode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icdcodes`
--

LOCK TABLES `icdcodes` WRITE;
/*!40000 ALTER TABLE `icdcodes` DISABLE KEYS */;
INSERT INTO `icdcodes` VALUES (1,'ICD-280.0','Iron deficiency anemia secondary to blood loss (chronic)'),(2,'ICD-411.1','SYNDROME, INTERMEDIATE CORONARY');
/*!40000 ALTER TABLE `icdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insuranceplan`
--

DROP TABLE IF EXISTS `insuranceplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insuranceplan` (
  `plan_id` int(11) NOT NULL,
  `plan_name` varchar(45) DEFAULT NULL,
  `company_address_line1` varchar(45) DEFAULT NULL,
  `company_address_line2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `plan_group_number` varchar(15) DEFAULT NULL,
  `effective_date` datetime DEFAULT NULL,
  `termination_date` datetime DEFAULT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insuranceplan`
--

LOCK TABLES `insuranceplan` WRITE;
/*!40000 ALTER TABLE `insuranceplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `insuranceplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maritalstatusdetail`
--

DROP TABLE IF EXISTS `maritalstatusdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maritalstatusdetail` (
  `marital_status_code` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`marital_status_code`),
  UNIQUE KEY `marital_status_code_UNIQUE` (`marital_status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maritalstatusdetail`
--

LOCK TABLES `maritalstatusdetail` WRITE;
/*!40000 ALTER TABLE `maritalstatusdetail` DISABLE KEYS */;
INSERT INTO `maritalstatusdetail` VALUES (0,'UnMarried');
/*!40000 ALTER TABLE `maritalstatusdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientattendingdoctormapping`
--

DROP TABLE IF EXISTS `patientattendingdoctormapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientattendingdoctormapping` (
  `patient_id` varchar(8) NOT NULL,
  `provider_id` int(11) NOT NULL,
  KEY `fk_patient_id_idx` (`patient_id`),
  KEY `fk_provider_id_1_idx` (`provider_id`),
  CONSTRAINT `fk_patient_id1` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_id_1` FOREIGN KEY (`provider_id`) REFERENCES `attendingdoctor` (`provider_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientattendingdoctormapping`
--

LOCK TABLES `patientattendingdoctormapping` WRITE;
/*!40000 ALTER TABLE `patientattendingdoctormapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientattendingdoctormapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientdetail`
--

DROP TABLE IF EXISTS `patientdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientdetail` (
  `patient_id` varchar(8) NOT NULL,
  `facility_id` int(11) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `name_suffix` varchar(5) DEFAULT NULL,
  `date_of_birth` varchar(45) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `race_id` int(11) DEFAULT NULL,
  `address_line_1` varchar(45) DEFAULT NULL,
  `address_line_2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `home_phone` varchar(15) DEFAULT NULL,
  `mobile_phone` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `work_phone` varchar(15) DEFAULT NULL,
  `primary_language` varchar(15) DEFAULT NULL,
  `martial_status_code` int(11) DEFAULT NULL,
  `chart_number` varchar(45) DEFAULT NULL,
  `ssn` varchar(15) DEFAULT NULL,
  `ethnicity_id` int(11) DEFAULT NULL,
  `is_processed` int(1) DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `patient_id_UNIQUE` (`patient_id`),
  KEY `fk_race_id_idx` (`race_id`),
  KEY `fk_marital_status_code_idx` (`martial_status_code`),
  KEY ` fk_ethnicity_id_idx` (`ethnicity_id`),
  KEY `fk_facility_id_idx` (`facility_id`),
  CONSTRAINT ` fk_ethnicity_id` FOREIGN KEY (`ethnicity_id`) REFERENCES `ethnicitydetail` (`ethnicity_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_facility_id` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`facility_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_marital_status_code` FOREIGN KEY (`martial_status_code`) REFERENCES `maritalstatusdetail` (`marital_status_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_id` FOREIGN KEY (`race_id`) REFERENCES `racedetail` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientdetail`
--

LOCK TABLES `patientdetail` WRITE;
/*!40000 ALTER TABLE `patientdetail` DISABLE KEYS */;
INSERT INTO `patientdetail` VALUES ('1762',NULL,'DeRossi','Marissa','','','1972-03-04 00:00:00','F',NULL,'1 NEW Street','1 NEW Street','Charleston','SC','29414','(843)730-2811','(123)456-7899','NEW@email.com','(123)456-7899','',NULL,NULL,'',NULL,0),('1763',NULL,'DeRossi','Jordan','','','1972-03-04 00:00:00','F',NULL,'1 OLD STreet','1 OLD STreet','Charleston','SC','29414','(843)730-1111','','OLD@email.com','','',NULL,NULL,'',NULL,0),('18',NULL,'Parker','Peggy','','','1991-06-03 00:00:00','F',NULL,'222 Atlantic Boulevard','222 Atlantic Boulevard','Atlantic City','NJ','00255','(212)555-1113','','giridhar.gavvala@healtasyst.com','','',NULL,NULL,'522-46-5321',NULL,0),('197',NULL,'Martin','Gary','','','1957-03-30 00:00:00','M',NULL,'8041 N MacArthur Blvd','8041 N MacArthur Blvd','Carrollton','IL','60215','(972)386-9688','(972)247-5492','Test.k@healthasyst.com','(972)247-5492','',NULL,NULL,'357-87-4020',NULL,0),('807',NULL,'P','GOUTHAMI','','','1996-06-06 00:00:00','F',NULL,'5TH CROSS,2ND MAIN church','5TH CROSS,2ND MAIN church','IOW','','78787897','(990)086-4484','','gouthamip@healthasyst.com','','',NULL,NULL,'578-97-8978',NULL,0);
/*!40000 ALTER TABLE `patientdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientguarantor`
--

DROP TABLE IF EXISTS `patientguarantor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientguarantor` (
  `patient_id` varchar(8) NOT NULL,
  `guarantor_id` varchar(7) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `suffix` varchar(5) DEFAULT NULL,
  `address_line1` varchar(45) DEFAULT NULL,
  `address_line2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `home_phone` varchar(15) DEFAULT NULL,
  `work_phone` varchar(15) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `ssn` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`patient_id`,`guarantor_id`),
  CONSTRAINT `fk_patient_id3` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientguarantor`
--

LOCK TABLES `patientguarantor` WRITE;
/*!40000 ALTER TABLE `patientguarantor` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientguarantor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientinsuranceplanmapping`
--

DROP TABLE IF EXISTS `patientinsuranceplanmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientinsuranceplanmapping` (
  `patient_id` varchar(8) NOT NULL,
  `plan_id` int(11) NOT NULL,
  KEY `fk_patient_id4_idx` (`patient_id`),
  KEY `fk_plan_id_idx` (`plan_id`),
  CONSTRAINT `fk_patient_id4` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_plan_id` FOREIGN KEY (`plan_id`) REFERENCES `insuranceplan` (`plan_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientinsuranceplanmapping`
--

LOCK TABLES `patientinsuranceplanmapping` WRITE;
/*!40000 ALTER TABLE `patientinsuranceplanmapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientinsuranceplanmapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientreferringprovidermapping`
--

DROP TABLE IF EXISTS `patientreferringprovidermapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientreferringprovidermapping` (
  `patient_id` varchar(8) NOT NULL,
  `provider_id` int(11) NOT NULL,
  KEY `fk_patient_id2_idx` (`patient_id`),
  KEY `fk_provider_id2_idx` (`provider_id`),
  CONSTRAINT `fk_patient_id2` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_id2` FOREIGN KEY (`provider_id`) REFERENCES `referringprovider` (`provider_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientreferringprovidermapping`
--

LOCK TABLES `patientreferringprovidermapping` WRITE;
/*!40000 ALTER TABLE `patientreferringprovidermapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientreferringprovidermapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientservicecpdcodes`
--

DROP TABLE IF EXISTS `patientservicecpdcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientservicecpdcodes` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `cpdcode` varchar(45) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_service_id2_idx` (`service_id`),
  KEY `fk_cpdcode_idx` (`cpdcode`),
  CONSTRAINT `fk_cpdcode` FOREIGN KEY (`cpdcode`) REFERENCES `cpdcodes` (`cpdcode`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_id2` FOREIGN KEY (`service_id`) REFERENCES `patientservicedetail` (`service_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicecpdcodes`
--

LOCK TABLES `patientservicecpdcodes` WRITE;
/*!40000 ALTER TABLE `patientservicecpdcodes` DISABLE KEYS */;
INSERT INTO `patientservicecpdcodes` VALUES (1,1,'CPT-50593'),(2,125,'CPT-10040'),(3,125,'CPT-52601');
/*!40000 ALTER TABLE `patientservicecpdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientservicedetail`
--

DROP TABLE IF EXISTS `patientservicedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientservicedetail` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(8) NOT NULL,
  `serviced_provider_id` int(11) DEFAULT NULL,
  `date_of_service` varchar(45) NOT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `charges` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`service_id`),
  KEY `fk_patient_id6_idx` (`patient_id`),
  KEY `fk_serviced_provider_id_idx` (`serviced_provider_id`),
  CONSTRAINT `fk_patient_id6` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_serviced_provider_id` FOREIGN KEY (`serviced_provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicedetail`
--

LOCK TABLES `patientservicedetail` WRITE;
/*!40000 ALTER TABLE `patientservicedetail` DISABLE KEYS */;
INSERT INTO `patientservicedetail` VALUES (1,'197',0,'2019-04-30','Diagnosis done',10),(125,'18',NULL,'2019-01-21','Service',30),(127,'197',0,'2018-01-01','test',20);
/*!40000 ALTER TABLE `patientservicedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientserviceicdcodes`
--

DROP TABLE IF EXISTS `patientserviceicdcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientserviceicdcodes` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `icdcode` varchar(45) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_service_id1_idx` (`service_id`),
  KEY `fk_icd_code_idx` (`icdcode`),
  CONSTRAINT `fk_icd_code` FOREIGN KEY (`icdcode`) REFERENCES `icdcodes` (`icdcode`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_id1` FOREIGN KEY (`service_id`) REFERENCES `patientservicedetail` (`service_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientserviceicdcodes`
--

LOCK TABLES `patientserviceicdcodes` WRITE;
/*!40000 ALTER TABLE `patientserviceicdcodes` DISABLE KEYS */;
INSERT INTO `patientserviceicdcodes` VALUES (1,1,'ICD-280.0'),(2,1,'ICD-411.1'),(3,125,'ICD-280.0'),(4,125,'ICD-411.1');
/*!40000 ALTER TABLE `patientserviceicdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientsubscriber`
--

DROP TABLE IF EXISTS `patientsubscriber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientsubscriber` (
  `subscriber_id` varchar(45) NOT NULL,
  `patient_id` varchar(8) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `relationship_to_insured` varchar(15) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `address_line1` varchar(45) DEFAULT NULL,
  `address_line2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `policy_number` varchar(45) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `ssn` varchar(15) DEFAULT NULL,
  `home_phone` varchar(15) DEFAULT NULL,
  `work_phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`subscriber_id`),
  KEY `fk_patient_id5_idx` (`patient_id`),
  CONSTRAINT `fk_patient_id5` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientsubscriber`
--

LOCK TABLES `patientsubscriber` WRITE;
/*!40000 ALTER TABLE `patientsubscriber` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientsubscriber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `provider_id` int(11) NOT NULL,
  `facility_group_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `suffix` varchar(5) DEFAULT NULL,
  `assgn_id` int(11) DEFAULT NULL,
  `tax_id` int(11) DEFAULT NULL,
  `prov_tax_id` varchar(45) DEFAULT NULL,
  `prov_sig_id` int(11) DEFAULT NULL,
  `prov_sig_date` datetime DEFAULT NULL,
  `spec_id` int(11) DEFAULT NULL,
  `prov_upin` varchar(45) DEFAULT NULL,
  `cred_id` int(11) DEFAULT NULL,
  `is_deleted` int(1) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_by` varchar(45) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`provider_id`),
  KEY `fk_facility_group_id2_idx` (`facility_group_id`),
  CONSTRAINT `fk_facility_group_id2` FOREIGN KEY (`facility_group_id`) REFERENCES `facilitygroup` (`facility_group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (0,0,0,NULL,NULL,NULL,'0',0,0,'0',2015,'0000-00-00 00:00:00',0,'0',0,0,'0','2019-02-02 00:00:00','0','2019-02-02 00:00:00');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racedetail`
--

DROP TABLE IF EXISTS `racedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `racedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1234568 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racedetail`
--

LOCK TABLES `racedetail` WRITE;
/*!40000 ALTER TABLE `racedetail` DISABLE KEYS */;
INSERT INTO `racedetail` VALUES (1234567,'race','racedetail');
/*!40000 ALTER TABLE `racedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referringprovider`
--

DROP TABLE IF EXISTS `referringprovider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referringprovider` (
  `provider_id` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`provider_id`),
  UNIQUE KEY `provider_id_UNIQUE` (`provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referringprovider`
--

LOCK TABLES `referringprovider` WRITE;
/*!40000 ALTER TABLE `referringprovider` DISABLE KEYS */;
/*!40000 ALTER TABLE `referringprovider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'chargecapturenew'
--

--
-- Dumping routines for database 'chargecapturenew'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-30 17:17:39
