/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
 */

/*
-- create schema
CREATE SCHEMA [schema_name];

-- use schema
USE [schema_name];

-- Create user 
create user 'USERNAME'@'IPADDRESS' identified by 'PASSWORD';

-- Grant privileges to user
grant all privileges on *.* to 'USERNAME'@'IPADDRESS' with grant option;
 */

 /*
 * Structure
 */
CREATE TABLE `bookmark` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `add_date` datetime DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `epoch_time` bigint(20) DEFAULT NULL,
  `filename` varchar(2000) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `path` varchar(600) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `protocol` varchar(255) DEFAULT NULL,
  `query` varchar(2000) DEFAULT NULL,
  `ref` varchar(2000) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `text` varchar(2000) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL,
  `website_category` varchar(255) DEFAULT NULL
);

CREATE TABLE `bookmark_live_chrome` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bookmark_bar_id` varchar(255) DEFAULT NULL,
  `date_added` varchar(255) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
);

CREATE TABLE `execution_summary` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_id` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` varchar(255) DEFAULT NULL,
  `url_count` int(11) DEFAULT NULL
);

CREATE TABLE `host_classification` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `host` varchar(255) DEFAULT NULL,
  `website_category` varchar(255) DEFAULT NULL
);

CREATE TABLE `application_settings` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `settings_Key` varchar(255) DEFAULT NULL,
  `settings_Value` varchar(255) DEFAULT NULL
);