--liquibase formatted sql

--changeset kempegowdafs:5
CREATE TABLE expense_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL);

--changeset kempegowdafs:6
CREATE TABLE expense_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

--changeset kempegowdafs:7
CREATE TABLE user_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES expense_users(id),
    FOREIGN KEY (role_id) REFERENCES expense_roles(id)
);