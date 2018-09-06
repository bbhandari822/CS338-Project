
-- DROP TABLE cs338.USERDATA

CREATE database cs338;
use cs338;

CREATE TABLE cs338.USERDATA (
    id int PRIMARY KEY AUTO_INCREMENT,
    username varchar(255),
    password varchar(255),
    email varchar(255),
    phone VARCHAR(10)
);

INSERT INTO cs338.USERDATA(username,password,email,phone) VALUE ('bbhanda','password','bb822@drexel.edu',1111);