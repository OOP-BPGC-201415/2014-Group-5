---- Structure for database `nirmaanam` ----
CREATE DATABASE IF NOT EXISTS nirmaanam;
USE nirmaanam;

CREATE TABLE Activity
(
id INT PRIMARY KEY AUTO_INCREMENT,
name text,
description text,
report text,
head int,
event int,
time int
);

CREATE TABLE Activity_assignees(
	activity INT,
	volunteer INT,
	availability INT
);



CREATE TABLE Discussion
(
id INT PRIMARY KEY AUTO_INCREMENT,
title text,
event INT
);

CREATE TABLE Discussion_posts(
	id INT PRIMARY KEY AUTO_INCREMENT,
	discussion INT,
	post text,
	poster INT
);

CREATE TABLE Event
(
id INT PRIMARY KEY AUTO_INCREMENT,
name text,
description text,
vertical INT,
head INT
);

CREATE TABLE EventUpdate
(
id INT PRIMARY KEY AUTO_INCREMENT,
message text,
event int,
timeStamp BIGINT
);

CREATE TABLE Meeting
(
id INT PRIMARY KEY AUTO_INCREMENT,
time BIGINT,
location text,
purpose text
);


CREATE TABLE Meeting_attendees(
	meeting INT,
	volunteer INT
);


CREATE TABLE Notice
(
id INT PRIMARY KEY AUTO_INCREMENT,
message text,
event int
);

CREATE TABLE TimeTable
(
volunteer INT,
day INT,
starttime INT
);

CREATE TABLE Vertical
(
id INT PRIMARY KEY AUTO_INCREMENT,
name text,
description text,
head int
);

CREATE TABLE Volunteer
(
id INT PRIMARY KEY AUTO_INCREMENT,
name text,
bitsID VARCHAR(12),
year int,
vertical int,
email VARCHAR(100),
password CHAR(32)
);

-- Default Values --
INSERT INTO vertical(id,name,description,head) VALUES(1,"General Body", "The whole organization", 1);
INSERT INTO volunteer(id,name,bitsID,year,vertical,email,password) VALUES(1,"admin", "XXXXXXXXXXXX",2012, 1, "admin@nirmaan.org", MD5("n!rm@@n"));
