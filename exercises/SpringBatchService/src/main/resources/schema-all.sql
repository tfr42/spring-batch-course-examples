DROP TABLE guests IF EXISTS;

CREATE TABLE guests  (
    guest_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);