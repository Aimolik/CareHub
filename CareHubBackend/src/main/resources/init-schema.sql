
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
