CREATE DATABASE IF NOT EXISTS IDEATION_STORM;

USE IDEATION_STORM;

CREATE TABLE users(
    id int NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO users(username, email) VALUES ("Bob", "bob@example.com");
INSERT INTO users(username, email) VALUES ("John", "john@example.com");
