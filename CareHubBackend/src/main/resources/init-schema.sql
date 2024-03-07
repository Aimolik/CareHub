
DROP TABLE IF EXISTS guardians CASCADE;

CREATE TABLE guardians (
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar NOT NULL,
    phone varchar(20) NOT NULL,
    address varchar(255) NOT NULL
);