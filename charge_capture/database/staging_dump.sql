-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 192.168.1.186    Database: chargecapture_new
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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL,
  `patient_id` varchar(45) NOT NULL,
  `provider_id` int(11) NOT NULL,
  `appointment_type` varchar(45) DEFAULT NULL,
  `appointment_type_desc` varchar(100) DEFAULT NULL,
  `appointmant_length` int(11) DEFAULT NULL,
  `appointment_start_datetime` datetime NOT NULL,
  `appointment_end_datetime` datetime NOT NULL,
  `placer_user` varchar(45) DEFAULT NULL,
  `appointment_status` varchar(45) DEFAULT NULL,
  `appointment_reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `fk_app_patient_id_idx` (`patient_id`),
  KEY `fk_app_provider_id_idx` (`provider_id`),
  CONSTRAINT `fk_app_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_app_provider_id` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
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
-- Table structure for table `cptcodes`
--

DROP TABLE IF EXISTS `cptcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cptcodes` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `cptcode` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `cpdcode_UNIQUE` (`cptcode`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cptcodes`
--

LOCK TABLES `cptcodes` WRITE;
/*!40000 ALTER TABLE `cptcodes` DISABLE KEYS */;
INSERT INTO `cptcodes` VALUES (1,'CPT-10040','Incision and Drainage Procedures on the Skin'),(2,'CPT-50593','Under Lithotripsy and Ablation Procedures on the Kidney'),(3,'CPT-52601','REMOVAL OF PROSTATE (TURP)'),(4,'CPT-92014','ophthalmological services'),(5,'CPT-92134','Retina'),(6,'CPT-67028','Intravitreal injection of a pharmacological agent'),(7,'CPT-19240','REMOVAL OF BREAST, MODIFIED RADICAL'),(8,'CPT-27447','TOTAL KNEE REPLACEMENT'),(9,'CPT-29882','KNEE ARTHROSCOPY/MENISCUS REPAIR'),(10,'CPT-30520','REPAIR OF NASAL SEPTUM'),(11,'CPT-42145','REVISION OF PALATE, PHARYNX/UVULA, (UPPP)'),(12,'CPT-42820','REMOVE TONSILS & ADENOIDS, UNDER 12'),(13,'CPT-42821','REMOVE TONSILS & ADENOIDS, AGE 12+'),(14,'CPT-42826','REMOVAL OF TONSILS, AGE 12+'),(15,'CPT-42835','REMOVAL OF ADENOIDS, UNDER AGE 12'),(16,'CPT-44950','REMOVAL OF APPENDIX'),(17,'CPT-46255','REMOVAL OF HEMORRHOIDS, SIMPLE'),(18,'CPT-47600','REMOVE GALLBLADDER'),(19,'CPT-49505','REPAIR INGUINAL HERNIA, AGE 5+'),(20,'CPT-92982','CORONARY ARTERY DILATION, BALLOON');
/*!40000 ALTER TABLE `cptcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cptgroup`
--

DROP TABLE IF EXISTS `cptgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cptgroup` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(100) NOT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `groupname_UNIQUE` (`groupname`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cptgroup`
--

LOCK TABLES `cptgroup` WRITE;
/*!40000 ALTER TABLE `cptgroup` DISABLE KEYS */;
INSERT INTO `cptgroup` VALUES (1,'cpd group 1'),(2,'cpd group 2'),(3,'cpd group 3'),(4,'cpd group 4');
/*!40000 ALTER TABLE `cptgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cptgroupcodemapping`
--

DROP TABLE IF EXISTS `cptgroupcodemapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cptgroupcodemapping` (
  `record_id` int(19) NOT NULL AUTO_INCREMENT,
  `cpt_group_record_id` int(11) NOT NULL,
  `cptcode` varchar(45) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_cpt_group_record_id_idx` (`cpt_group_record_id`),
  KEY `fk_cptcode1_idx` (`cptcode`),
  CONSTRAINT `fk_cpt_group_record_id` FOREIGN KEY (`cpt_group_record_id`) REFERENCES `cptgroup` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cptcode1` FOREIGN KEY (`cptcode`) REFERENCES `cptcodes` (`cptcode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cptgroupcodemapping`
--

LOCK TABLES `cptgroupcodemapping` WRITE;
/*!40000 ALTER TABLE `cptgroupcodemapping` DISABLE KEYS */;
INSERT INTO `cptgroupcodemapping` VALUES (1,1,'CPT-10040'),(2,1,'CPT-52601'),(3,2,'CPT-92014'),(4,2,'CPT-19240'),(5,2,'CPT-52601'),(6,3,'CPT-27447');
/*!40000 ALTER TABLE `cptgroupcodemapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cptmodifiers`
--

DROP TABLE IF EXISTS `cptmodifiers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cptmodifiers` (
  `modifier_code` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`modifier_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cptmodifiers`
--

LOCK TABLES `cptmodifiers` WRITE;
/*!40000 ALTER TABLE `cptmodifiers` DISABLE KEYS */;
INSERT INTO `cptmodifiers` VALUES ('WP','Designated Doctor Exam'),('WX','Administered by nurse'),('XE','Separate Encounter: A service that is distinct because it occurred during a separate encounter.'),('XP','Separate Practitioner: A service that is distinct because it was performed by a different practitioner.'),('XS','Separate Structure: A service that is distinct because it was performed on a separate organ/structure.'),('XU','Unusual Non-Overlapping Service: The use of a service that is distinct because it does not overlap usual components of the main service.'),('ZA','Novartis/Sandoz'),('ZB','Pfizer/Hospira'),('ZD','Local Modifier used for global diagnostic test'),('ZK','Primary Surgeon');
/*!40000 ALTER TABLE `cptmodifiers` ENABLE KEYS */;
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
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
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
INSERT INTO `facility` VALUES (3,1,NULL,'River Oaks',1,0,'(214) 662-2000',' (214) 662-2189',NULL,NULL,0,0,NULL,NULL,0,NULL,NULL,NULL,NULL),(5,1,NULL,'Western Medical Hospital',3,0,NULL,NULL,NULL,NULL,0,0,NULL,NULL,0,NULL,NULL,NULL,NULL),(13,2,NULL,'Loma Linda Multispecialty Group',2,0,'(818) 555-1239','(818) 555-1233',NULL,NULL,0,0,NULL,NULL,0,NULL,NULL,NULL,NULL),(1111,3,NULL,'ABC Hospital',5,0,'5555555555','6666666666',NULL,NULL,0,0,NULL,NULL,0,NULL,NULL,NULL,NULL),(2222,4,NULL,'DEF Hospital',6,0,NULL,NULL,NULL,NULL,0,0,NULL,NULL,0,NULL,NULL,NULL,NULL),(6990,2,NULL,'TESTRETINA ',4,0,'(281) 495-2222',NULL,NULL,NULL,0,0,NULL,NULL,0,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilityaddress`
--

LOCK TABLES `facilityaddress` WRITE;
/*!40000 ALTER TABLE `facilityaddress` DISABLE KEYS */;
INSERT INTO `facilityaddress` VALUES (1,'3790 W. First St','','Dallas','TX','75248'),(2,'451 Esther Williams Way','','Atlanta','GA','30325'),(3,'344 W 59th Ave W','','Carrollton','TX','75006'),(4,'13300 HARGRAVE ROAD','SUITE 480','HOUSTON','TX','77070-7374'),(5,'abc street',NULL,'abc','NY','99501'),(6,'def street',NULL,'def','NY','99501');
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
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
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
INSERT INTO `facilitygroup` VALUES (1,1,'Facility Group 1',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,2,'Facility Group 2',NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,3,'ABC Hospital',NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,4,'DEF Hospital',NULL,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `icdcodes` VALUES (1,'ICD-280.0','Iron deficiency anemia secondary to blood loss (chronic)'),(2,'ICD-411.1','Syndrome, Intermediate coronary'),(7,'ICD-413.0','Angina Decubitus'),(8,'ICD-413.1','Angina, Prinzmetal'),(9,'ICD-413.9','Angina pectoris nec/nos'),(10,'ICD-272.2','ICD-280.0'),(11,'ICD-272.4','Hyperlipidemia nec/nos'),(12,'ICD-396.0','Stenosis, Mitral and Aortic valves'),(13,'ICD-396.3','Insufficiency, Mitral/Aortic valves'),(14,'ICD-397.0','Disease, Tricuspid valve'),(15,'ICD-397.1','Disease, Rheumatic pulmonary valve'),(16,'ICD-401.0','Hypertension, Malignant essential'),(17,'ICD-401.1','Hypertension, Benign essential'),(18,'ICD-401.9','Hypertension, Essential nos'),(19,'ICD-402.90','Disease, Hypertensive heart nos'),(20,'ICD-403.90','Disease, Hypertensive renal nos'),(21,'ICD-405.19','Hypertension, Secondary, MLG nec'),(22,'ICD-411.0','Syndrome, Postmyocardial infarctio'),(23,'ICD-426.89','Disorder, Conduction nec'),(25,'ICD-H35.341','Macular cyst, Hole, or Pseudohole, Right eye'),(27,'ICD-H35.3231','Exudative age-related macular degeneration, Bilateral, with Active choroidal neovascularization'),(28,'ICD-H353211','Exudative age-related macular degeneration, right eye, with active choroidal neovascularization'),(29,'ICD-H353221','Exudative age-related macular degeneration, left eye, with active choroidal neovascularization');
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
-- Table structure for table `organisation`
--

DROP TABLE IF EXISTS `organisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organisation` (
  `organisation_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address_line_1` varchar(45) DEFAULT NULL,
  `address_line_2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`organisation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organisation`
--

LOCK TABLES `organisation` WRITE;
/*!40000 ALTER TABLE `organisation` DISABLE KEYS */;
INSERT INTO `organisation` VALUES (111,'Practice Admin','aaa','qqqq','dsd','NY','99501','2121212121',NULL,NULL,NULL,NULL),(222,'GE','das','fsd','sdfsd','MI','63141','1212121212',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `organisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organisationfacilitygroupmapping`
--

DROP TABLE IF EXISTS `organisationfacilitygroupmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organisationfacilitygroupmapping` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `organisation_id` int(11) NOT NULL,
  `facility_group_id` int(11) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_org_organisation_id_idx` (`organisation_id`),
  KEY `fk_org_facility_group_id_idx` (`facility_group_id`),
  CONSTRAINT `fk_org_facility_group_id` FOREIGN KEY (`facility_group_id`) REFERENCES `facilitygroup` (`facility_group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_org_organisation_id` FOREIGN KEY (`organisation_id`) REFERENCES `organisation` (`organisation_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organisationfacilitygroupmapping`
--

LOCK TABLES `organisationfacilitygroupmapping` WRITE;
/*!40000 ALTER TABLE `organisationfacilitygroupmapping` DISABLE KEYS */;
INSERT INTO `organisationfacilitygroupmapping` VALUES (1,111,1),(2,111,2),(3,222,3),(4,222,4);
/*!40000 ALTER TABLE `organisationfacilitygroupmapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientdetail`
--

DROP TABLE IF EXISTS `patientdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientdetail` (
  `patient_id` varchar(45) NOT NULL,
  `facility_id` int(11) DEFAULT NULL,
  `facility_group_id` int(11) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `name_suffix` varchar(5) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
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
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT current_timestamp(),
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `patient_id_UNIQUE` (`patient_id`),
  KEY `fk_race_id_idx` (`race_id`),
  KEY `fk_marital_status_code_idx` (`martial_status_code`),
  KEY ` fk_ethnicity_id_idx` (`ethnicity_id`),
  KEY `fk_facility_id_idx` (`facility_id`),
  KEY `fk_patient_facility_group_id_idx` (`facility_group_id`),
  CONSTRAINT ` fk_ethnicity_id` FOREIGN KEY (`ethnicity_id`) REFERENCES `ethnicitydetail` (`ethnicity_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_facility_id` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`facility_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_marital_status_code` FOREIGN KEY (`martial_status_code`) REFERENCES `maritalstatusdetail` (`marital_status_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_patient_facility_group_id` FOREIGN KEY (`facility_group_id`) REFERENCES `facilitygroup` (`facility_group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_id` FOREIGN KEY (`race_id`) REFERENCES `racedetail` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientdetail`
--

LOCK TABLES `patientdetail` WRITE;
/*!40000 ALTER TABLE `patientdetail` DISABLE KEYS */;
INSERT INTO `patientdetail` VALUES ('1751',3,NULL,'Mike','Hussey','','','2000-10-18 00:00:00','M',NULL,'1201','1201','Dunwoody','AL','23212','123-456-7890','987-654-32','','123-213-2131','',NULL,'787875465789','222-22-2221',NULL,0,NULL,'2019-05-03 15:06:53',NULL,'2019-05-21 11:26:11'),('1762',3,NULL,'Marissa','DeRossi','','','1972-03-21 00:00:00','F',NULL,'1 NEW Street','1 NEW Street','Charleston','SC','29414','843-730-2811','123-456-1212','NEW@email.com','123-456-7899','',NULL,NULL,NULL,NULL,1,NULL,'2019-05-03 15:06:53',NULL,'2019-05-20 14:37:03'),('1764',3,NULL,'Deepika','Aiyappa','','','1989-05-17 00:00:00','F',NULL,'St.Louis','St.Louis','Missouri','MO','63141','785-114-2456','324-323-8787','deepika.aiyappa@healthasyst.com','888-888-8888','',NULL,NULL,'748-55-5555',NULL,0,NULL,'2019-05-03 15:06:53',NULL,'2019-05-21 11:26:11'),('18',3,NULL,'Peggy','Parker','','','1991-06-03 00:00:00','F',NULL,'222 Atlantic Boulevard1','222 Atlantic Boulevard1','Atlantic City','NJ','00255','212-555-1113','212-111-7856','Peggy.parker@mail.com','212-545-7856','',NULL,NULL,'522-46-5321',NULL,0,NULL,'2019-05-03 15:06:53',NULL,'2019-05-20 14:37:03'),('1867020919471890',3,NULL,'krishnareddy','divya',NULL,NULL,'2000-01-01 00:00:00',NULL,NULL,'lane 1156','lane 2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-16 16:15:21',NULL,NULL),('1869319307724170',3,NULL,'SMART','TIMMY',NULL,NULL,'2000-01-02 00:00:00','M',NULL,'123 Main St.123 Main St.',NULL,'AnnArbor','CT','11111',NULL,NULL,NULL,NULL,NULL,NULL,'222222222222',NULL,NULL,NULL,NULL,'2019-05-16 15:14:49',NULL,'2019-05-20 14:37:03'),('197',5,NULL,'Martin','Gary','','','1967-03-30 00:00:00','M',NULL,'8041 N MacArthur Blvd','8041 N MacArthur Blvd','Carrollton','IL','60215','(972)386-9688','(972)247-5492','Test.k@healthasyst.com','(972)247-5492','',NULL,NULL,'357-87-4020',NULL,1,NULL,'2019-05-03 15:06:53',NULL,'2019-05-15 19:40:05'),('4340612',6990,NULL,'MARTIN','MORRISON','M','','1984-10-10 00:00:00','m',NULL,'101 ROAD','101 ROAD','HOUSTON','TX','77074-1010','','','','','English',NULL,NULL,'101010101',NULL,0,NULL,'2019-05-06 13:03:24',NULL,'2019-05-06 13:18:24'),('4341081',6990,NULL,'MARTIN','BLACK','','','1950-10-10 00:00:00','m',NULL,'999 STREET','999 STREET','ST.CHARLES','MO','77070-3212','1234567890','98765432','','','English',NULL,NULL,'333121211',NULL,0,NULL,'2019-05-03 19:43:54',NULL,'2019-05-13 11:30:00'),('822',3,NULL,'damodar','prabu','','','2000-01-01 00:00:00','M',NULL,'lane 1','lane 1','','','','','','','','',NULL,NULL,'',NULL,0,NULL,'2019-05-09 11:35:16',NULL,'2019-05-16 15:14:50');
/*!40000 ALTER TABLE `patientdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientservicecptcodes`
--

DROP TABLE IF EXISTS `patientservicecptcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientservicecptcodes` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `cptcode` varchar(45) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_service_id2_idx` (`service_id`),
  KEY `fk_cptcode_idx` (`cptcode`),
  CONSTRAINT `fk_cptcode` FOREIGN KEY (`cptcode`) REFERENCES `cptcodes` (`cptcode`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_id2` FOREIGN KEY (`service_id`) REFERENCES `patientservicedetail` (`service_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicecptcodes`
--

LOCK TABLES `patientservicecptcodes` WRITE;
/*!40000 ALTER TABLE `patientservicecptcodes` DISABLE KEYS */;
INSERT INTO `patientservicecptcodes` VALUES (1,1,'CPT-50593'),(4,140,'CPT-27447'),(9,145,'CPT-19240'),(10,145,'CPT-42145'),(17,154,'CPT-29882'),(18,154,'CPT-42821'),(27,182,'CPT-67028'),(31,189,'CPT-92982'),(32,190,'CPT-92982'),(33,190,'CPT-67028'),(34,200,'CPT-29882'),(35,200,'CPT-92982'),(36,202,'CPT-42821');
/*!40000 ALTER TABLE `patientservicecptcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientservicecptmodifiermapping`
--

DROP TABLE IF EXISTS `patientservicecptmodifiermapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientservicecptmodifiermapping` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_cpt_record_id` int(11) NOT NULL,
  `modifier_code` varchar(45) NOT NULL,
  PRIMARY KEY (`service_cpt_record_id`,`modifier_code`),
  UNIQUE KEY `record_id_UNIQUE` (`record_id`),
  KEY `fk_modifier_code_idx` (`modifier_code`),
  KEY `fk_patient_service_cpt_record_id_idx` (`service_cpt_record_id`),
  CONSTRAINT `fk_modifier_code` FOREIGN KEY (`modifier_code`) REFERENCES `cptmodifiers` (`modifier_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_patient_service_cpt_record_id` FOREIGN KEY (`service_cpt_record_id`) REFERENCES `patientservicecptcodes` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicecptmodifiermapping`
--

LOCK TABLES `patientservicecptmodifiermapping` WRITE;
/*!40000 ALTER TABLE `patientservicecptmodifiermapping` DISABLE KEYS */;
INSERT INTO `patientservicecptmodifiermapping` VALUES (9,4,'WP'),(10,9,'WX'),(11,9,'XE'),(12,10,'XP'),(13,4,'WX'),(14,4,'XE'),(15,4,'XP');
/*!40000 ALTER TABLE `patientservicecptmodifiermapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientservicedetail`
--

DROP TABLE IF EXISTS `patientservicedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientservicedetail` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(45) NOT NULL,
  `provider_id` int(11) DEFAULT NULL,
  `date_of_service` datetime NOT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `charges` int(11) NOT NULL DEFAULT 0,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `fk_patient_id6_idx` (`patient_id`),
  KEY `fk_provider_id_idx` (`provider_id`),
  CONSTRAINT `fk_patient_id6` FOREIGN KEY (`patient_id`) REFERENCES `patientdetail` (`patient_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_id` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientservicedetail`
--

LOCK TABLES `patientservicedetail` WRITE;
/*!40000 ALTER TABLE `patientservicedetail` DISABLE KEYS */;
INSERT INTO `patientservicedetail` VALUES (1,'197',1,'2019-05-03 00:00:00','Diagnosis done',10,'Processed'),(140,'1764',1,'2019-05-04 00:00:00','service 1',500,'Processed'),(145,'1751',1,'2019-02-11 00:00:00','service 1',500,'Processed'),(154,'1762',1,'2019-05-03 00:00:00',NULL,500,'Processed'),(179,'1764',NULL,'2019-05-06 00:00:00','Scanning recorded (longer comment for testing)',20,'Processed'),(180,'1762',1,'2019-05-06 00:00:00',NULL,0,'Processed'),(182,'1762',1,'2019-05-04 00:00:00',NULL,0,'PendingReview'),(184,'1751',1,'2019-05-05 00:00:00',NULL,0,'PendingReview'),(185,'197',1,'2019-05-06 00:00:00',NULL,0,'Processed'),(186,'1762',1,'2019-05-03 00:00:00',NULL,0,'PendingReview'),(189,'18',1,'2019-05-06 00:00:00','Accute chest pain',0,'Processed'),(190,'4340612',97674,'2019-05-07 00:00:00','Chest Pain',20,'Processed'),(191,'1751',1,'2019-05-08 00:00:00',NULL,0,'PendingReview'),(196,'1751',1,'2019-02-11 00:00:00','service 1',500,'PendingReview'),(197,'1751',1,'2019-05-10 00:00:00',NULL,0,'PendingReview'),(198,'1751',1,'2019-02-11 00:00:00','service 1',500,'PendingReview'),(199,'1751',1,'2019-02-11 00:00:00','service 1',500,'PendingReview'),(200,'1869319307724170',1,'2019-05-17 00:00:00',NULL,0,'PendingReview'),(201,'1867020919471890',1,'2019-05-17 00:00:00',NULL,0,'PendingReview'),(202,'197',1,'2019-05-17 00:00:00',NULL,0,'PendingReview'),(203,'1751',1,'2019-02-11 00:00:00','service 1',500,'PendingReview'),(205,'1751',1,'2019-02-11 00:00:00','service 1',500,'PendingReview');
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientserviceicdcodes`
--

LOCK TABLES `patientserviceicdcodes` WRITE;
/*!40000 ALTER TABLE `patientserviceicdcodes` DISABLE KEYS */;
INSERT INTO `patientserviceicdcodes` VALUES (1,1,'ICD-280.0'),(2,1,'ICD-411.1'),(14,140,'ICD-272.2'),(15,140,'ICD-411.1'),(22,145,'ICD-413.1'),(28,154,'ICD-272.2'),(29,154,'ICD-397.0'),(39,180,'ICD-411.1'),(40,182,'ICD-426.89'),(47,189,'ICD-401.1'),(48,189,'ICD-401.9'),(49,189,'ICD-426.89'),(51,190,'ICD-H35.341'),(52,190,'ICD-H35.3231'),(53,200,'ICD-411.1'),(54,200,'ICD-401.1');
/*!40000 ALTER TABLE `patientserviceicdcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `provider_id` int(11) NOT NULL,
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
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (1,0,'Richard','Chase',NULL,'Dr',0,0,'0',2015,'0000-00-00 00:00:00',0,'0',0,0,'0','2019-02-02 00:00:00','0','2019-02-02 00:00:00'),(97674,NULL,'TESTGEORGE','JOHNTEST',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `providerfacilitymapping`
--

DROP TABLE IF EXISTS `providerfacilitymapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `providerfacilitymapping` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) NOT NULL,
  `facility_group_id` int(11) NOT NULL,
  `facility_id` int(11) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_prfc_provider_id_idx` (`provider_id`),
  KEY `fk_prfc_facility_group_id_idx` (`facility_group_id`),
  KEY `fk_prfc_facility_id_idx` (`facility_id`),
  CONSTRAINT `fk_prfc_facility_group_id` FOREIGN KEY (`facility_group_id`) REFERENCES `facilitygroup` (`facility_group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_prfc_facility_id` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`facility_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_prfc_provider_id` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `providerfacilitymapping`
--

LOCK TABLES `providerfacilitymapping` WRITE;
/*!40000 ALTER TABLE `providerfacilitymapping` DISABLE KEYS */;
INSERT INTO `providerfacilitymapping` VALUES (1,1,1,3),(2,1,2,13),(3,1,2,6990);
/*!40000 ALTER TABLE `providerfacilitymapping` ENABLE KEYS */;
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
-- Dumping events for database 'chargecapture_new'
--

--
-- Dumping routines for database 'chargecapture_new'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-21 11:35:51
