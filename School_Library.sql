SET NAMES 'utf8';
SET SESSION collation_connection = 'utf8_general_ci';

CREATE DATABASE school_library DEFAULT CHARACTER SET 'utf8';

USE school_library;

CREATE TABLE students(
id_student INTEGER AUTO_INCREMENT,
student_name VARCHAR(20) NOT NULL,
date_of_bith DATE NOT NULL,
number_of_books INTEGER NOT NULL
PRIMARY KEY (id_student)
);

CREATE TABLE literature(
id_item INTEGER AUTO_INCREMENT,
item_type VARCHAR(10) NOT NULL,
item_name VARCHAR(20) NOT NULL,
aouthor VARCHAR(20) NOT NULL,
PRIMARY KEY(id_item)
);