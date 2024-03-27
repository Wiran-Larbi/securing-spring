create database easybank;
use easybank;

CREATE TABLE `users` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `authorities` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(45) NOT NULL,
    `authority` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT IGNORE INTO `users` VALUES (NULL,'admin','admin',1);
INSERT IGNORE INTO `authorities` VALUES (NULL,'admin','write');

CREATE TABLE `agent` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `email` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `role` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `agent`  (email, password, role)
VALUES ('agent1@mail.com', '4321', 'admin');