--liquibase formatted sql

--changeset kempegowdafs:1
CREATE TABLE expense_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(50),
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    registration_required BOOLEAN
);