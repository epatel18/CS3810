-- Database Project 01 - Flowers Database
-- created at: 3/7/2021
-- author(s): Eesha Patel, Selamawit Abdo

-- database creation and use
CREATE DATABASE flowers ;
USE flowers;
DROP TABLE IF EXISTS Zones;
DROP TABLE IF EXISTS Deliveries;
DROP TABLE IF EXISTS FlowersInfo;

-- tables creation satisfying all of the requirements

-- create a table for Zones
CREATE TABLE Zones(
    id INT NOT NULL AUTO_INCREMENT,
    lowerTemp INT  NOT NULL,
    higherTemp INT NOT NULL,
    PRIMARY KEY (id)
 );

-- create a table for Deliveries
CREATE TABLE Deliveries(
    id INT NOT NULL AUTO_INCREMENT,
    categ VARCHAR(10),
    delSize DECIMAL(5,3),
    PRIMARY KEY (id)
 );

-- create a table for FlowersInfo
CREATE TABLE FlowersInfo(
    id INT NOT NULL AUTO_INCREMENT,
    comName VARCHAR(30),
    latName VARCHAR(35),
    cZone INT,
    hZone Int,
    deliver Int,
    sunNeeds VARCHAR(5),
    PRIMARY KEY (id),
    FOREIGN KEY (cZone) REFERENCES Zones (id),
	FOREIGN KEY (hZone) REFERENCES Zones (id),
	FOREIGN KEY (deliver) REFERENCES Deliveries (id)
 );

-- update FlowersInfo Table so that the ID starts from 101
alter table FlowersInfo auto_increment=101;

-- tables population for Zones
INSERT INTO Zones VALUES
  ( 2, -50, -40 ),
  ( 3, -40, -30 ),
  ( 4, -30, -20),
  ( 5, -20, -10 ),
  ( 6, -10, 0 ),
  ( 7, 0, 10),
  ( 8, 10, 20 ),
  ( 9, 20, 30 ),
  ( 10, 30, 40);

-- tables population for Deliveries
INSERT INTO Deliveries (categ , delSize) VALUES
('pot', 1.500 ),
('pot', 2.250 ),
('pot', 2.625 ),
('pot', 4.250 ),
('plant', null ),
('bulb', null ),
('hedge', 18.000 ),
('shrub', 24.000 ),
('tree', 36.000);


-- tables population for FlowersInfo
INSERT INTO FlowersInfo (comName ,latName ,cZone ,hZone, deliver, sunNeeds )  VALUES
('Lady Fern', 'Atbyrium filix-femina' , 2 , 9, 5, 'SH'),
('Pink Caladiums', 'C.x bortulanum' , 10, 10, 6, 'PToSH'),
('Lily-of-the-Valley', 'Convallaria majalis' , 2 , 8, 5, 'PToSH'),
('Purple Liatris', 'Liatris spicata' , 3 , 9, 6, 'StoP'),
('Black Eyed Susan', 'Rudbeckia fulgida var. specios' , 4 , 10, 2, 'StoP'),
('Nikko Blue Hydrangea', 'Hydrangea macrophylla' , 5 , 9, 4, 'StoSH'),
('Variegated Weigela', 'W. florida Variegata' , 4 , 9, 8, 'StoP'),
('Lombardy Poplar', 'Populus nigra Italica' , 3 , 9, 9, 'S'),
('Purple Leaf Plum Hedge', 'Prunus x cistena' , 2 , 8, 7, 'S'),
('Thorndale Ivy', 'Hedera belix Thorndale' , 3, 9, 1, 'StoSH');


-- a) the total number of zones.
SELECT COUNT(*) As'total number of zones' FROM Zones;

-- b) the number of flowers per cool zone.
SELECT cZone AS 'Cool Zone', COUNT(cZone) AS 'Number of flowers'
FROM FlowersInfo
GROUP BY cZone
ORDER BY cZone;

-- c) common names of the plants that have delivery sizes less than 5.
SELECT comName FROM FlowersInfo
WHERE deliver < 5;

-- d) common names of the plants that require full sun (i.e., sun needs contains ‘S’).
SELECT comName, sunNeeds FROM FlowersInfo
WHERE sunNeeds LIKE 'S%' AND sunNeeds != 'SH';

-- e) all delivery category names order alphabetically (without repetition).
SELECT DISTINCT categ
FROM Deliveries
ORDER BY categ ASC ;

-- f) the exact output (see instructions)
SELECT comName as 'Name', lowerTemp as 'Cool Zone (low)', higherTemp as 'Cool Zone (high)', categ as 'Delivery Category'
FROM FlowersInfo
INNER JOIN Deliveries ON Deliveries.id = FlowersInfo.deliver
LEFT JOIN Zones ON Zones.id = FlowersInfo.cZone
ORDER BY comName ASC;

-- g) plant names that have the same hot zone as “Pink Caladiums” (your solution MUST get the hot zone of “Pink Caladiums” in a variable).
SET @pink_caladium_zone := (SELECT hZone
                            FROM FlowersInfo
                            WHERE comName = 'Pink Caladiums');
SELECT comName AS 'Plant names with the same hot zone as "Pink Caladiums"'
FROM FlowersInfo
WHERE hZone = @pink_caladium_zone
AND NOT comName = 'Pink Caladiums';

-- h) the total number of plants, the minimum delivery size, the maximum delivery size, and the average size based on the plants that have delivery sizes (note that the average value should be rounded using two decimals).
SELECT count(DISTINCT categ) as 'Total', MIN(DISTINCT convert (delSize,float)) as 'Min',
MAX(DISTINCT convert(delSize,float)) as 'Max',
ROUND(SUM(delsize) / count(DISTINCT categ), 2) as 'Average'
FROM Deliveries
ORDER BY 1;

-- i) the Latin name of the plant that has the word ‘Eyed’ in its name (you must use LIKE in this query to get full credit).
SELECT latName FROM FlowersInfo
WHERE latName LIKE '%Eyed%';

-- j) the exact output (see instructions)
SELECT categ as 'Category', comName as 'Name'
FROM FlowersInfo
INNER JOIN Deliveries ON Deliveries.id = FlowersInfo.deliver
ORDER BY categ ASC ;