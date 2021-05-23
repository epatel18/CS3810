-- occupations database
-- created at: <02/22/2021>
-- author: <Eesha Patel>

CREATE DATABASE occupations;

USE occupations;

DROP TABLE IF EXISTS Occupations;

-- TODO: create table Occupations
CREATE TABLE Occupations (
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    code        VARCHAR(20),
    occupation  VARCHAR(100),
    jobFamily   VARCHAR(80) );

-- TODO: populate table Occupations
LOAD DATA INFILE '/Users/eesha/documents/CS3810/Occupations/occupations.csv' INTO TABLE Occupations FIELDS TERMINATED BY ','
ENCLOSED BY '"' IGNORE 1 ROWS (code, occupation, jobFamily);

-- TODO: a) the total number of occupations (expect 1016).
SELECT COUNT(*) FROM Occupations;

-- TODO: b) a list of all job families in alphabetical order (expect 23).
SELECT DISTINCT jobFamily FROM Occupations ORDER BY jobFamily DESC;

-- TODO: c) the total number of job families (expect 23)
SELECT COUNT(DISTINCT jobFamily) FROM Occupations;

-- TODO: d) the total number of occupations per job family in alphabetical order of job family.
SELECT jobFamily, COUNT(*) AS 'Total Number of Occupations' FROM  Occupations GROUP BY jobFamily ORDER BY jobFamily ASC;

-- TODO: e) the number of occupations in the "Computer and Mathematical" job family (expect 38)
SELECT COUNT(occupation) FROM Occupations WHERE jobFamily LIKE "Computer and Mathematical";

-- TODO: f) an alphabetical list of occupations in the "Computer and Mathematical" job family.
SELECT occupation FROM Occupations WHERE jobFamily LIKE "Computer and Mathematical" ORDER BY occupation ASC;

-- TODO: g) an alphabetical list of occupations in the "Computer and Mathematical" job family that begins with the word "Database"
SELECT occupation FROM Occupations WHERE jobFamily LIKE "Computer and Mathematical" AND occupation LIKE "Databse%";