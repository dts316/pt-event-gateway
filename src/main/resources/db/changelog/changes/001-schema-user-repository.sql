-- liquibase formatted sql

-- changeset shuh1:001.1
DROP TABLE IF EXISTS USER_DETAILS;

CREATE TABLE IF NOT EXISTS USER_DETAILS (
    id VARCHAR(255) AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NULL,
    last_name VARCHAR(255) NULL,
    email VARCHAR(255) NULL
    );

INSERT INTO USER_DETAILS (first_name, last_name, email) VALUES
  ('Lokesh', 'Gupta', 'abc@gmail.com'),
  ('Deja', 'Vu', 'xyz@email.com'),
  ('Caption', 'America', 'cap@marvel.com');