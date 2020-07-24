DROP DATABASE IF EXISTS SuperSightingsTestDB;
CREATE DATABASE SuperSightingsTestDB;

USE SuperSightingsDB;

create table `power` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) unique
    
);

create table `super` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL unique,
    `description` varchar(255)
);

create table superPower (
	superId INT NOT NULL,
    powerId INT NOT NULL,
    
    FOREIGN KEY (superId) REFERENCES `super`(id) ON DELETE CASCADE,
    FOREIGN KEY (powerId) REFERENCES `power`(id) ON DELETE CASCADE
);

create table `organization` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    orgName varchar(80) unique NOT NULL,
    orgDescription varchar(255),
    address varchar(100),
    phone varchar(20)
);

create table superOrganization (
    superId INT NOT NULL,
    orgId INT NOT NULL,
    
    FOREIGN KEY (superId) REFERENCES `super`(id) ON DELETE CASCADE,
    FOREIGN KEY (orgId) REFERENCES `organization`(id) ON DELETE CASCADE
);

create table location (
	id INT PRIMARY KEY AUTO_INCREMENT,
    locationName varchar(50) unique NOT NULL,
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
    
    FOREIGN KEY (locationId) REFERENCES location(id) on delete cascade,
    FOREIGN KEY (superId) REFERENCES `super`(id) on delete cascade
);

insert into location(locationName, `description`, address, latitude, longitude) values
	('The Daily Planet', "A large building in the bustling streets of downtown Metropolis with a large globe on top. Metropolis' number 1 news source.",
    '1234 Metropolis way', 10.5, 80.1234),
    
    ('Wayne Tower', "Wayne Enterprises is large, growing multinational company. The conglomerate is owned and chaired by Bruce Wayne, the son of Thomas and Martha Wayne. 
    Wayne Enterprises is a green company based out of Gotham City and headquartered in Wayne Tower.", '9876 Wayne Tower Way', 90, 90),
    
    ('Bikini Bottom', "An undersea city located in the Pacific Ocean, beneath Bikini Atoll in the Marshall Islands.", '5678 Rock Bottom Heights', 24, 25);

insert into `super`(`name`, `description`) values
	('Superman', 'Faster than a speeding bullet, the savior from Krypton knows no limits!'),
    
    ('Batman', 'The dark knight of Gotham. A Highly skilled martial artist with a genius level IQ, and lots and lots of tactical toys. Not the hero Gotham needs, but the one they deserve.'),
        
    ('ManRay', 'Half-man, half...RAYY.'),
    
    ('Iron-Man', 'Half-man, half...IRON.');
    
insert into `power` (`name`) values
	('Heat Vision'),
    ('Super Speed'),
    ('Lasers'),
    ('Underwater Breathing'),
    ('Super Strength'),
    ('Flight'),
    ('Invulnerability'),
    ('Money');

insert into sighting (locationId, superId, `date`) values 
	(1, 1, '2020-07-04'),
    (3, 3, '2020-07-10'),
    (2, 2, '2020-07-16');
    
insert into `organization` (orgName, orgDescription, address, phone) values
	('E.V.I.L.', 'EVERY VILLAIN IS LEMONS', '1000 Bikini Bottom Pass', '(555)999-1000'),
    
    ('The Justice League of America', 'A league of crime fighting superheroes. Acting as a strike force and a leading line of 
    defense, The Justice League remains vigilant in efforts to protect humanity.', '404 Missing Avenue', '(999)404-9999');
    
insert into superOrganization () values
	(3, 1),
    (1, 2), (2, 2);
    
insert into superPower(superId, powerId) values
	(1, 1), (1, 2), (1, 5), (1, 6), (1, 7),
    (2, 8),
    (3, 3), (3, 5);
