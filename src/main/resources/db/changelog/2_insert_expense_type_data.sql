--liquibase formatted sql

--changeset kempegowdafs:2
INSERT INTO expense_type (name, description, status, created_by, updated_by, created_date, updated_date, registration_required) VALUES
('Electricity', 'Electricity Bills', 'Active', 'system', 'system', '2023-01-01 00:00:00', '2023-01-01 00:00:00', false);

--changeset kempegowdafs:3
INSERT INTO expense_type (name, description, status, created_by, updated_by, created_date, updated_date, registration_required) VALUES
('Water', 'BWSSB Bills', 'Active', 'system', 'system', '2023-01-01 00:00:00', '2023-01-01 00:00:00', false);

--changeset kempegowdafs:4
INSERT INTO expense_type (name, description, status, created_by, updated_by, created_date, updated_date, registration_required) VALUES
('Dairy Products', 'Milk, Curd, Ghee, Butter, Panneer etc', 'Active', 'system', 'system', '2023-01-01 00:00:00', '2023-01-01 00:00:00', false);