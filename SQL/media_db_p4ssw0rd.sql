--DDL: DATA

CREATE TABLE actor(
act_id INT,
act_name VARCHAR2(50),
act_film VARCHAR2(50),
act_birth_date DATE,
act_address VARCHAR(100),
act_years_in_media NUMBER(8,2),
act_time_signed_contract TIMESTAMP,
PRIMARY KEY(act_id)
);



ALTER TABLE actor ADD movie_type INT;
ALTER TABLE actor ADD FOREIGN KEY(movie_type) REFERENCES movie_id;

ALTER TABLE actor DROP COLUMN movie_type;

CREATE TABLE movie_type(
movie_id INT,
movie_category VARCHAR2(40),
PRIMARY KEY(movie_id)
);

DROP TABLE movie_table;