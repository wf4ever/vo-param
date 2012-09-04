-- phpMyAdmin SQL Dump
-- version 3.3.7deb5build0.10.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2012 at 02:09 PM
-- Server version: 5.1.49
-- PHP Version: 5.3.3-1ubuntu9.5



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `PDLServer`
--

-- --------------------------------------------------------

--
-- Table structure for table `JobDetails`
--

CREATE TABLE IF NOT EXISTS "JobDetails" (
  "Id_Job" bigint(20) NOT NULL,
  "ParamName" varchar(100) NOT NULL,
  "ParamValue" varchar(300) NOT NULL,
  KEY "Id_Job_fk1" ("Id_Job")
);

--
-- Dumping data for table `JobDetails`
--


-- --------------------------------------------------------

--
-- Table structure for table `JobError`
--

CREATE TABLE IF NOT EXISTS "JobError" (
  "Id_Job" bigint(20) NOT NULL,
  "ErrorType" varchar(100) NOT NULL,
  "ErrorDetail" varchar(800) NOT NULL,
  KEY "Id_Job_fk3" ("Id_Job")
);

--
-- Dumping data for table `JobError`
--


-- --------------------------------------------------------

--
-- Table structure for table `JobPhase`
--

CREATE TABLE IF NOT EXISTS "JobPhase" (
  "Id_Phase" int(11) NOT NULL,
  "Phase_Name" varchar(100) NOT NULL,
  PRIMARY KEY ("Id_Phase")
);

--
-- Dumping data for table `JobPhase`
--


-- --------------------------------------------------------

--
-- Table structure for table `JobResults`
--

CREATE TABLE IF NOT EXISTS "JobResults" (
  "Id_Job" bigint(20) NOT NULL,
  "ResultName" varchar(300) NOT NULL,
  "ResultValue" varchar(300) NOT NULL,
  KEY "Id_Job_fk2" ("Id_Job")
);

--
-- Dumping data for table `JobResults`
--


-- --------------------------------------------------------

--
-- Table structure for table `Jobs`
--

CREATE TABLE IF NOT EXISTS "Jobs" (
  "Id_Job" bigint(20) NOT NULL,
  "Id_Service" bigint(20) NOT NULL,
  "Owner" bigint(20) NOT NULL,
  "Phase" int(11) NOT NULL,
  "UserDescription" varchar(600) NOT NULL,
  "CreationTime" varchar(50) NOT NULL,
  "LastCangeTime" varchar(50) NOT NULL,
  "Modifiable" varchar(1) NOT NULL,
  PRIMARY KEY ("Id_Job"),
  KEY "Id_Service_fk" ("Id_Service"),
  KEY "Id_Owner_fk" ("Owner"),
  KEY "Id_Phase_fk" ("Phase")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `Jobs`
--


-- --------------------------------------------------------

--
-- Table structure for table `PDLService`
--

CREATE TABLE IF NOT EXISTS "PDLService" (
  "Id_Service" bigint(20) NOT NULL,
  "ServiceName" varchar(300) NOT NULL,
  "PDLDescription" blob NOT NULL,
  PRIMARY KEY ("Id_Service")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `PDLService`
--


-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS "Users" (
  "Id_User" bigint(20) NOT NULL,
  "Email" varchar(300) NOT NULL,
  "Password" varchar(300) NOT NULL,
  PRIMARY KEY ("Id_User")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `Users`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `JobDetails`
--
ALTER TABLE `JobDetails`
  ADD CONSTRAINT "Id_Job_fk1" FOREIGN KEY ("Id_Job") REFERENCES "Jobs" ("Id_Job");

--
-- Constraints for table `JobError`
--
ALTER TABLE `JobError`
  ADD CONSTRAINT "Id_Job_fk3" FOREIGN KEY ("Id_Job") REFERENCES "Jobs" ("Id_Job");

--
-- Constraints for table `JobResults`
--
ALTER TABLE `JobResults`
  ADD CONSTRAINT "Id_Job_fk2" FOREIGN KEY ("Id_Job") REFERENCES "Jobs" ("Id_Job");

--
-- Constraints for table `Jobs`
--
ALTER TABLE `Jobs`
  ADD CONSTRAINT "Id_Service_fk" FOREIGN KEY ("Id_Service") REFERENCES "PDLService" ("Id_Service"),
  ADD CONSTRAINT "Id_Owner_fk" FOREIGN KEY ("Owner") REFERENCES "Users" ("Id_User"),
  ADD CONSTRAINT "Id_Phase_fk" FOREIGN KEY ("Phase") REFERENCES "JobPhase" ("Id_Phase");