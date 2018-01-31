SET NAMES 'utf8';
SET SESSION collation_connection = 'utf8_general_ci';

CREATE DATABASE school_library DEFAULT CHARACTER SET 'utf8';

USE school_library;

CREATE TABLE students(
id_student INTEGER AUTO_INCREMENT,
student_name VARCHAR(20) NOT NULL,
date_of_birth DATE NOT NULL,
number_of_books INTEGER NOT NULL,
PRIMARY KEY(id_student)
);

CREATE TABLE literature(
id_item INTEGER AUTO_INCREMENT,
item_type VARCHAR(10) NOT NULL,
item_name VARCHAR(20) NOT NULL,
author VARCHAR(20) NOT NULL,
available BOOLEAN NOT NULL,
PRIMARY KEY(id_item)
);

CREATE TABLE used_literature(
id_used INTEGER AUTO_INCREMENT,
id_student INTEGER NOT NULL,
id_item INTEGER NOT NULL,
PRIMARY KEY(id_used)
);