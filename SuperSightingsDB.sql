DROP DATABASE IF EXISTS SuperSightingsDB;
CREATE DATABASE SuperSightingsDB;

USE SuperSightingsDB;

create table `power` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255)
);

create table `super` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `description` varchar(255)
);

create table superPower (
	superId INT NOT NULL,
    powerId INT NOT NULL,
    
    FOREIGN KEY (superId) REFERENCES `super`(id),
    FOREIGN KEY (powerId) REFERENCES `power`(id)
);

create table `organization` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    orgName varchar(80),
    orgDescription varchar(255),
    address varchar(100),
    phone varchar(20)
);

create table superOrganization (
    superId INT NOT NULL,
    orgId INT NOT NULL,
    
    FOREIGN KEY (superId) REFERENCES `super`(id),
    FOREIGN KEY (orgId) REFERENCES `organization`(id)
);

create table location (
	id INT PRIMARY KEY AUTO_INCREMENT,
    locationName varchar(50),
	`description` varchar(255),
    address varchar(100),
    latitude numeric(7, 5) NOT NULL,
    longitude numeric(8, 5) NOT NULL
);

create table sighting (
	id INT PRIMARY KEY AUTO_INCREMENT,
    locationId INT NOT NULL,
    superId INT NOT NULL,
    `date` date NOT NULL,
    
    FOREIGN KEY (locationId) REFERENCES location(id),
    FOREIGN KEY (superId) REFERENCES `super`(id)
);
