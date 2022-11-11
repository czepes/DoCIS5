-- SQL script to create and fill in tables used in the project

DROP TABLE IF EXISTS televisions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE televisions (
    id SERIAL PRIMARY KEY,
    model text NOT NULL,
    producer text NOT NULL,
    production_country text,
    width integer NOT NULL,
    height integer NOT NULL,
    CONSTRAINT positive_width_and_height CHECK (width > 0 AND height > 0)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username varchar(12) NOT NULL UNIQUE,
    password text NOT NULL
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id integer REFERENCES tvs.users(id),
    role_id integer REFERENCES tvs.roles(id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO televisions (model, producer, production_country, width, height) VALUES
	('43PFS5505/60', 'Philips', NULL, 1920, 1080),
	('28TN515S-PZ', 'LG', 'Russia', 1366, 768),
	('MI TV P1 43', 'Xiaomi', 'Russia', 3840, 2160),
	('OLED65B2RLA', 'LG', 'Russia', 3840, 2160),
	('UE32T5300AUXCE', 'Samsung', NULL, 1920, 1080);

INSERT INTO roles (name) VALUES
    ('ROLE_ADMIN'), ('ROLE_USER');
