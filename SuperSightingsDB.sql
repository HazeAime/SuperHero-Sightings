DROP DATABASE IF EXISTS SuperSightingsDB;
CREATE DATABASE SuperSightingsDB;

USE SuperSightingsDB;

create table `powers`(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255)
);

create table supers(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `description` varchar(255)
);

create table superPowers(
	superId INT NOT NULL,
    powerId INT NOT NULL,
    
    FOREIGN KEY (superId) REFERENCES supers(id),
    FOREIGN KEY (powerId) REFERENCES powers(id)
);

create table organizations(
	id INT PRIMARY KEY AUTO_INCREMENT,
    orgName varchar(80),
    orgDescription varchar(255),
    address varchar(100),
    phone varchar(20)
);

create table superOrganizations(
    superId INT NOT NULL,
    orgId INT NOT NULL,
    
    FOREIGN KEY (superId) REFERENCES supers(id),
    FOREIGN KEY (orgId) REFERENCES organizations(id)
);

create table locations(
	id INT PRIMARY KEY AUTO_INCREMENT,
    locationName varchar(50),
	`description` varchar(255),
    address varchar(100),
    latitude numeric(7, 5) NOT NULL,
    longitude numeric(8, 5) NOT NULL
);

create table sightings(
	id INT PRIMARY KEY AUTO_INCREMENT,
    locationId INT NOT NULL,
    superId INT NOT NULL,
    `date` date NOT NULL,
    
    FOREIGN KEY (locationId) REFERENCES locations(id),
    FOREIGN KEY (superId) REFERENCES supers(id)
);
