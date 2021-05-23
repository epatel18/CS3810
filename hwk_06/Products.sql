CREATE DATABASE IF NOT EXISTS hw6;
USE hw6;

-- consider table Products considered as:
CREATE TABLE Products (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descr VARCHAR(30) NOT NULL,
    price FLOAT NOT NULL
);

-- modify table Products by adding column 'cond' to describe the condition of a product, which can only be 'new' or 'used'. make sure 'cond' defaults to 'new' if not stated otherwise.
ALTER TABLE Products
ADD cond VARCHAR(30) DEFAULT 'new';

-- create a trigger to check if the informed value for 'cond' is 'new' or 'used', and have the tigger set the value to 'new' if something else.
DELIMITER |
CREATE TRIGGER condition_default
    BEFORE INSERT
    ON Products
    FOR EACH ROW
        BEGIN
            IF NEW.cond <> 'used' AND NEW.cond <> 'new' THEN
                SET NEW.cond = 'new';
            END IF;
        END;
|
DELIMITER ;

-- test trigger if working with inserts to produce expected rows.
INSERT INTO Products (descr, price, cond) VALUES ('Ninja Sword', 250, 'new');
INSERT INTO Products (descr, price, cond) VALUES ('Dummy', 50, 'new');
INSERT INTO Products (descr, price, cond) VALUES ('Fake Blood', 5, 'used');
INSERT INTO Products (descr, price, cond) VALUES ('Rubber Ducky', 1, 'used');
INSERT INTO Products (descr, price, cond) VALUES ('Bathtub Soap', 3, 'used once');
INSERT INTO Products (descr, price, cond) VALUES ('Brazilian Coffee', 5, 'new');
INSERT INTO Products (descr, price, cond) VALUES ('Running Shoes', 50, 'fair');