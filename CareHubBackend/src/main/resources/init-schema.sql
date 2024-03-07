
DROP TABLE IF EXISTS guardians CASCADE;

CREATE TABLE guardians (
    guardian_id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    phone varchar(20) NOT NULL,
    address varchar(255) NOT NULL
);

DROP TABLE IF EXISTS staffs CASCADE;

CREATE TABLE staffs (
    staff_id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    contact_info varchar(255) NOT NULL,
    position varchar(50) NOT NULL
);

DROP TABLE IF EXISTS children CASCADE;

CREATE TABLE children (
    child_id SERIAL PRIMARY KEY,
    guardian_id SERIAL REFERENCES guardians(guardian_id),
    name varchar(255) NOT NULL,
    date_of_birth date NOT NULL,
    address varchar(255) NOT NULL,
    medical_information varchar(255) NOT NULL
);