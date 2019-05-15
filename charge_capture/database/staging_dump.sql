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
INSERT INTO `attendingdoctor` VALUES (97674,'TESTGEORGE','JOHNTEST');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpdcodes`
--

LOCK TABLES `cpdcodes` WRITE;
/*!40000 ALTER TABLE `cpdcodes` DISABLE KEYS */;
INSERT INTO `cpdcodes` VALUES (1,'CPT-10040','Incision and Drainage Procedures on the Skin'),(2,'CPT-50593','Under Lithotripsy and Ablation Procedures on the Kidney'),(3,'CPT-52601','REMOVAL OF PROSTATE (TURP)'),(4,'CPT-92014','ophthalmological services'),(5,'CPT-92134','Retina'),(6,'CPT-67028','Intravitreal injection of a pharmacological agent'),(7,'CPT-19240','REMOVAL OF BREAST, MODIFIED RADICAL'),(8,'CPT-27447','TOTAL KNEE REPLACEMENT'),(9,'CPT-29882','KNEE ARTHROSCOPY/MENISCUS REPAIR'),(10,'CPT-30520','REPAIR OF NASAL SEPTUM'),(11,'CPT-42145','REVISION OF PALATE, PHARYNX/UVULA, (UPPP)'),(12,'CPT-42820','REMOVE TONSILS & ADENOIDS, UNDER 12'),(13,'CPT-42821','REMOVE TONSILS & ADENOIDS, AGE 12+'),(14,'CPT-42826','REMOVAL OF TONSILS, AGE 12+'),(15,'CPT-42835','REMOVAL OF ADENOIDS, UNDER AGE 12'),(16,'CPT-44950','REMOVAL OF APPENDIX'),(17,'CPT-46255','REMOVAL OF HEMORRHOIDS, SIMPLE'),(18,'CPT-47600','REMOVE GALLBLADDER'),(19,'CPT-49505','REPAIR INGUINAL HERNIA, AGE 5+'),(20,'CPT-92982','CORONARY ARTERY DILATION, BALLOON');
/*!40000 ALTER TABLE `cpdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpdgroup`
--

DROP TABLE IF EXISTS `cpdgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cpdgroup` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(100) NOT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `groupname_UNIQUE` (`groupname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpdgroup`
--

LOCK TABLES `cpdgroup` WRITE;
/*!40000 ALTER TABLE `cpdgroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `cpdgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpdgroupcodemapping`
--

DROP TABLE IF EXISTS `cpdgroupcodemapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cpdgroupcodemapping` (
  `record_id` int(19) NOT NULL AUTO_INCREMENT,
  `cpd_group_record_id` int(11) NOT NULL,
  `cpdcode` varchar(45) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_cpd_group_record_id_idx` (`cpd_group_record_id`),
  KEY `fk_cpdcode1_idx` (`cpdcode`),
  CONSTRAINT `fk_cpd_group_record_id` FOREIGN KEY (`cpd_group_record_id`) REFERENCES `cpdgroup` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cpdcode1` FOREIGN KEY (`cpdcode`) REFERENCES `cpdcodes` (`cpdcode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpdgroupcodemapping`
--

LOCK TABLES `cpdgroupcodemapping` WRITE;
/*!40000 ALTER TABLE `cpdgroupcodemapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `cpdgroupcodemapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpdmodifiers`
--

DROP TABLE IF EXISTS `cpdmodifiers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cpdmodifiers` (
  `modifier_code` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`modifier_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpdmodifiers`
--

LOCK TABLES `cpdmodifiers` WRITE;
/*!40000 ALTER TABLE `cpdmodifiers` DISABLE KEYS */;
INSERT INTO `cpdmodifiers` VALUES ('WP','Designated Doctor Exam'),('WX','ADMINISTERED BY NURSE'),('XE','Separate Encounter: A service that is distinct because it occurred during a separate encounter.'),('XP','Separate Practitioner: A service that is distinct because it was performed by a different practitioner.'),('XS','Separate Structure: A service that is distinct because it was performed on a separate organ/structure.'),('XU','Unusual Non-Overlapping Service: The use of a service that is distinct because it does not overlap usual components of the main service.'),('ZA','Novartis/Sandoz'),('ZB','Pfizer/Hospira'),('ZD','Local Modifier used for global diagnostic test'),('ZK','Primary Surgeon');
/*!40000 ALTER TABLE `cpdmodifiers` ENABLE KEYS */;
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
  `pos_id` int(11) DEFAULT 0,
  `phone1` varchar(15) DEFAULT NULL,
  `phone2` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `tax_id` varchar(45) DEFAULT NULL,
  `provider_rep_id` int(11) DEFAULT 0,
  `is_deleted` int(1) DEFAULT 0,
  `facility_npi` varchar(45) DEFAULT NULL,
  `group_npi` varchar(45) DEFAULT NULL,
  `is_emergency` int(1) DEFAULT 0,
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
INSERT INTO `facility` VALUES (3,0,NULL,'River Oaks',1,0,'(214) 662-2000',' (214) 662-2189',NULL,NULL,0,0,NULL,NULL,0),(5,0,NULL,'Western Medical Hospital',3,0,NULL,NULL,NULL,NULL,0,0,NULL,NULL,0),(13,0,NULL,'Loma Linda Multispecialty Group',2,0,'(818) 555-1239','(818) 555-1233',NULL,NULL,0,0,NULL,NULL,0),(6990,0,NULL,'TESTRETINA ',4,0,'(281) 495-2222',NULL,NULL,NULL,0,0,NULL,NULL,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilityaddress`
--

LOCK TABLES `facilityaddress` WRITE;
/*!40000 ALTER TABLE `facilityaddress` DISABLE KEYS */;
INSERT INTO `facilityaddress` VALUES (1,'3790 W. First St','','Dallas','TX','75248'),(2,'451 Esther Williams Way','','Atlanta','GA','30325'),(3,'344 W 59th Ave W','','Carrollton','TX','75006'),(4,'13300 HARGRAVE ROAD','SUITE 480','HOUSTON','TX','77070-7374');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icdcodes`
--

LOCK TABLES `icdcodes` WRITE;
/*!40000 ALTER TABLE `icdcodes` DISABLE KEYS */;
INSERT INTO `icdcodes` VALUES (1,'ICD-280.0','Iron deficiency anemia secondary to blood loss (chronic)'),(2,'ICD-411.1','SYNDROME, INTERMEDIATE CORONARY'),(7,'ICD-413.0','ANGINA DECUBITUS'),(8,'ICD-413.1','ANGINA, PRINZMETAL'),(9,'ICD-413.9','ANGINA PECTORIS NEC/NOS'),(10,'ICD-272.2','ICD-280.0'),(11,'ICD-272.4','HYPERLIPIDEMIA NEC/NOS'),(12,'ICD-396.0','STENOSIS, MITRAL AND AORTIC VALVES'),(13,'ICD-396.3','INSUFFICIENCY, MITRAL/AORTIC VALVES'),(14,'ICD-397.0','DISEASE, TRICUSPID VALVE'),(15,'ICD-397.1','DISEASE, RHEUMATIC PULMONARY VALVE'),(16,'ICD-401.0','HYPERTENSION, MALIGNANT ESSENTIAL'),(17,'ICD-401.1','HYPERTENSION, BENIGN ESSENTIAL'),(18,'ICD-401.9','HYPERTENSION, ESSENTIAL NOS'),(19,'ICD-402.90','DISEASE, HYPERTENSIVE HEART NOS'),(20,'ICD-403.90','DISEASE, HYPERTENSIVE RENAL NOS'),(21,'ICD-405.19','HYPERTENSION, SECONDARY, MLG NEC'),(22,'ICD-411.0','SYNDROME, POSTMYOCARDIAL INFARCTIO'),(23,'ICD-426.89','DISORDER, CONDUCTION NEC'),(25,'ICD-H35.341','Macular cyst, hole, or pseudohole, right eye'),(27,'ICD-H35.3231','Exudative age-related macular degeneration, bilateral, with active choroidal neovascularization'),(28,'ICD-H353211','Exudative age-related macular degeneration, right eye, with active choroidal neovascularization'),(29,'ICD-H353221','Exudative age-related macular degeneration, left eye, with active choroidal neovascularization');
/*!40000 ALTER TABLE `icdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icdgroup`
--

DROP TABLE IF EXISTS `icdgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icdgroup` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(100) NOT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `groupname_UNIQUE` (`groupname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icdgroup`
--

LOCK TABLES `icdgroup` WRITE;
/*!40000 ALTER TABLE `icdgroup` DISABLE KEYS */;
INSERT INTO `icdgroup` VALUES (1,'Abnormalities of Heart Rhythm'),(2,'Atrial Fibrillation and Flutter'),(3,'Cardiac Arrhythmias (Other)'),(4,'Chest Pain'),(5,'Heart Failure'),(6,'Hypertension'),(7,'Nonrheumatic Valve Disorders'),(8,'Selected Atherosclerosis, Ischemia, and Infarction'),(9,'Syncope and Collapse');
/*!40000 ALTER TABLE `icdgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icdgroupcodemapping`
--

DROP TABLE IF EXISTS `icdgroupcodemapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icdgroupcodemapping` (
  `record_id` int(19) NOT NULL AUTO_INCREMENT,
  `icd_group_record_id` int(11) NOT NULL,
  `icdcode` varchar(45) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_icdcode_idx` (`icdcode`),
  KEY `fk_icd_group_record_id_idx` (`icd_group_record_id`),
  CONSTRAINT `fk_icd_group_record_id` FOREIGN KEY (`icd_group_record_id`) REFERENCES `icdgroup` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_icdcode` FOREIGN KEY (`icdcode`) REFERENCES `icdcodes` (`icdcode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icdgroupcodemapping`
--

LOCK TABLES `icdgroupcodemapping` WRITE;
/*!40000 ALTER TABLE `icdgroupcodemapping` DISABLE KEYS */;
INSERT INTO `icdgroupcodemapping` VALUES (18,1,'ICD-280.0'),(19,1,'ICD-413.0'),(20,2,'ICD-413.9'),(21,3,'ICD-272.2'),(22,3,'ICD-413.0');
/*!40000 ALTER TABLE `icdgroupcodemapping` ENABLE KEYS */;
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
INSERT INTO `patientattendingdoctormapping` VALUES ('4340612',97674);
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
  `created_date` datetime DEFAULT current_timestamp(),
  `updated_date` datetime DEFAULT NULL ON UPDATE current_timestamp(),
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
INSERT INTO `patientdetail` VALUES ('1751',3,'Mike','Hussey','','','2000-10-10T00:00:00.000Z','M',NULL,'1201','1201','Dunwoody','AL','23212','1234567890','98765432','','(123)213-2131','',NULL,NULL,'',NULL,0,'2019-05-03 15:06:53','2019-05-13 18:19:21'),('1762',3,'Marissa','DeRossi','','','1972-03-04T00:00:00.000Z','F',NULL,'1 NEW Street','1 NEW Street','Charleston','SC','29414','(843)730-2811','(123)456-7899','NEW@email.com','(123)456-7899','',NULL,NULL,'',NULL,1,'2019-05-03 15:06:53','2019-05-13 18:19:05'),('1764',3,'Deepika','Aiyappa','','','1989-05-18T00:00:00.000Z','F',NULL,'St.Louis','St.Louis','Missouri','MO','63141','(785)114-2456','3333333333','deepika.aiyappa@healthasyst.com','888-888-8888','',NULL,NULL,'748-55-5555',NULL,0,'2019-05-03 15:06:53','2019-05-14 11:09:39'),('18',3,'Peggy','Parker','','','1991-06-03','F',NULL,'222 Atlantic Boulevard1','222 Atlantic Boulevard1','Atlantic City','NJ','00255','(212)555-1113','(212)545-7856','Peggy.parker@mail.com','(212)545-7856','',NULL,NULL,'522-46-5321',NULL,0,'2019-05-03 15:06:53','2019-05-13 16:58:29'),('197',5,'Martin','Gary','','','1957-03-30','M',NULL,'8041 N MacArthur Blvd','8041 N MacArthur Blvd','Carrollton','IL','60215','(972)386-9688','(972)247-5492','Test.k@healthasyst.com','(972)247-5492','',NULL,NULL,'357-87-4020',NULL,1,'2019-05-03 15:06:53',NULL),('4340612',6990,'MARTIN','MORRISON','M','','1984-10-10','m',NULL,'101 ROAD','101 ROAD','HOUSTON','TX','77074-1010','','','','','English',NULL,NULL,'101010101',NULL,0,'2019-05-06 13:03:24','2019-05-06 13:18:24'),('4341081',6990,'MARTIN','BLACK','','','1950-10-10','m',NULL,'999 STREET','999 STREET','ST.CHARLES','MO','77070-3212','1234567890','98765432','','','English',NULL,NULL,'333121211',NULL,0,'2019-05-03 19:43:54','2019-05-13 11:30:00'),('822',3,'damodar','prabu','','','2000-01-01','',NULL,'lane 1','lane 1','','','','','','','','',NULL,NULL,'',NULL,0,'2019-05-09 11:35:16','2019-05-09 11:35:22');
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
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(8) NOT NULL,
  `provider_id` int(11) NOT NULL,
  PRIMARY KEY (`record_id`),
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicecpdcodes`
--

LOCK TABLES `patientservicecpdcodes` WRITE;
/*!40000 ALTER TABLE `patientservicecpdcodes` DISABLE KEYS */;
INSERT INTO `patientservicecpdcodes` VALUES (1,1,'CPT-50593'),(4,140,'CPT-27447'),(9,145,'CPT-19240'),(10,145,'CPT-42145'),(17,154,'CPT-29882'),(18,154,'CPT-42821'),(27,182,'CPT-67028'),(31,189,'CPT-92982'),(32,190,'CPT-92982'),(33,190,'CPT-67028');
/*!40000 ALTER TABLE `patientservicecpdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientservicecpdmodifiermapping`
--

DROP TABLE IF EXISTS `patientservicecpdmodifiermapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientservicecpdmodifiermapping` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_cpd_record_id` int(11) NOT NULL,
  `modifier_code` varchar(45) NOT NULL,
  PRIMARY KEY (`service_cpd_record_id`,`modifier_code`),
  UNIQUE KEY `record_id_UNIQUE` (`record_id`),
  KEY `fk_modifier_code_idx` (`modifier_code`),
  KEY `fk_patient_service_cpd_record_id_idx` (`service_cpd_record_id`),
  CONSTRAINT `fk_modifier_code` FOREIGN KEY (`modifier_code`) REFERENCES `cpdmodifiers` (`modifier_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_patient_service_cpd_record_id` FOREIGN KEY (`service_cpd_record_id`) REFERENCES `patientservicecpdcodes` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicecpdmodifiermapping`
--

LOCK TABLES `patientservicecpdmodifiermapping` WRITE;
/*!40000 ALTER TABLE `patientservicecpdmodifiermapping` DISABLE KEYS */;
INSERT INTO `patientservicecpdmodifiermapping` VALUES (9,4,'WP'),(10,9,'WX'),(11,9,'XE'),(12,10,'XP');
/*!40000 ALTER TABLE `patientservicecpdmodifiermapping` ENABLE KEYS */;
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
  `provider_id` int(11) DEFAULT NULL,
  `date_of_service` varchar(45) NOT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `charges` int(11) NOT NULL DEFAULT 0,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `fk_patient_id6_idx` (`patient_id`),
  KEY `fk_provider_id_idx` (`provider_id`),
  CONSTRAINT `fk_patient_id6` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_id` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicedetail`
--

LOCK TABLES `patientservicedetail` WRITE;
/*!40000 ALTER TABLE `patientservicedetail` DISABLE KEYS */;
INSERT INTO `patientservicedetail` VALUES (1,'197',1,'2019-05-03','Diagnosis done',10,'Processed'),(140,'1764',1,'2019-05-04','service 1',500,'Processed'),(145,'1751',1,'2019-02-11','service 1',500,'Processed'),(154,'1762',1,'2019-05-03',NULL,500,'Processed'),(179,'1764',NULL,'2019-05-06','Scanning recorded',20,'Processed'),(180,'1762',1,'2019-05-06',NULL,0,'Processed'),(182,'1762',1,'2019-05-04',NULL,0,'PendingReview'),(184,'1751',1,'2019-05-05',NULL,0,'PendingReview'),(185,'197',1,'2019-05-06',NULL,0,'Processed'),(186,'1762',1,'2019-05-03',NULL,0,'PendingReview'),(189,'18',1,'2019-05-06','Accute chest pain',0,'Processed'),(190,'4340612',97674,'2019-05-07','Chest Pain',20,'Processed'),(191,'1751',1,'2019-05-08',NULL,0,'PendingReview'),(196,'1751',1,'2019-02-11','service 1',500,'PendingReview'),(197,'1751',1,'2019-05-10',NULL,0,'PendingReview');
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientserviceicdcodes`
--

LOCK TABLES `patientserviceicdcodes` WRITE;
/*!40000 ALTER TABLE `patientserviceicdcodes` DISABLE KEYS */;
INSERT INTO `patientserviceicdcodes` VALUES (1,1,'ICD-280.0'),(2,1,'ICD-411.1'),(14,140,'ICD-272.2'),(15,140,'ICD-411.1'),(22,145,'ICD-413.1'),(28,154,'ICD-272.2'),(29,154,'ICD-397.0'),(39,180,'ICD-411.1'),(40,182,'ICD-426.89'),(47,189,'ICD-401.1'),(48,189,'ICD-401.9'),(49,189,'ICD-426.89'),(51,190,'ICD-H35.341'),(52,190,'ICD-H35.3231');
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
INSERT INTO `provider` VALUES (1,0,0,'Richard','Chase',NULL,'Dr',0,0,'0',2015,'0000-00-00 00:00:00',0,'0',0,0,'0','2019-02-02 00:00:00','0','2019-02-02 00:00:00'),(97674,NULL,NULL,'TESTGEORGE','JOHNTEST',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
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

-- Dump completed on 2019-05-14 20:49:13
