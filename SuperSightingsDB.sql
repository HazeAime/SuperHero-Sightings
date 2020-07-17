DROP DATABASE IF EXISTS SuperSightingsDB;
CREATE DATABASE SuperSightingsDB;

USE SuperSightingsDB;

create table supers(
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `description` varchar(255),
    `power` varchar(30) NOT NULL
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
    latitude INT NOT NULL,
    longitude INT NOT NULL
);

create table sightings(
	id INT PRIMARY KEY AUTO_INCREMENT,
    locationId INT NOT NULL,
    superId INT NOT NULL,
    `date` date NOT NULL,
    
    FOREIGN KEY (locationId) REFERENCES locations(id),
    FOREIGN KEY (superId) REFERENCES supers(id)
);
