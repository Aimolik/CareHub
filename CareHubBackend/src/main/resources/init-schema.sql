
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

DROP TABLE IF EXISTS attendances CASCADE;

DROP TYPE IF EXISTS attendance_status;
CREATE TYPE attendance_status AS ENUM ('CHECKED_IN', 'CHECKED_OUT');

CREATE TABLE attendances (
    attendance_id SERIAL PRIMARY KEY,
    child_id SERIAL REFERENCES children(child_id),
    checked_in_time timestamp NOT NULL,
    checked_out_time timestamp,
    attendance_status attendance_status NOT NULL
);

DROP TABLE IF EXISTS vehicles CASCADE;

CREATE TABLE vehicles (
    vehicle_id SERIAL PRIMARY KEY,
    guardian_id SERIAL REFERENCES guardians(guardian_id),
    vehicle_type varchar(255) NOT NULL,
    license_plate varchar(255) NOT NULL,
    color varchar(255) NOT NULL
);