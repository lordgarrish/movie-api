DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
    id IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    type VARCHAR NOT NULL,
    genre VARCHAR NOT NULL,
    release_date DATE NOT NULL
);