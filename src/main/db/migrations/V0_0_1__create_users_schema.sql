/*
 * Version: O.O.1
 * Description: Initialize the dataset
 */


/*
 * Structure
 */

CREATE TABLE IF NOT EXISTS `users` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `permission` int NOT NULL DEFAULT 0,
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `projects` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `content` VARCHAR(255) NOT NULL,
    `difficulty` int NOT NULL DEFAULT 0,
    `score` int NOT NULL DEFAULT 0,
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `languages` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

/*
 * Data
 */