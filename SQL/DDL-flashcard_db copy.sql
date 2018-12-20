--DDL: Data Definition Language
/*
OracleDB Datatypes:
NUMBER
VARCHAR2
DATE
TIMESTAMP
BLOB : Binary Large Object ex. images, movies etc.
CLOB : Character Large Object ex. txt, doc, csv etc.

Note: no boolean in or OracleDB
*/

DROP TABLE flashcard;
DROP TABLE fc_user;
DROP TABLE user_flashcard;


/*
OracleDB Contraints
Datatypes
Not Null
UNIQUE
Primary Key: add to a column to uniquely identify the record (also not null) and allows for relations to other tables' foriegn keys
Forgien Key: a cloumn key that represents the primary key of another table
Default (if value is not applied, then use x variable ex. 18)
Checked (see if value meets criteria like Between 18 and 125)
Composite Key: A primary Key made up of 2 or more columns
Canidate key: Any column(s) that COULD be the Primary Key (not a keyword)
Composite Key: 
*/

--Table without constraints
CREATE TABLE flashcard(
fc_id INT,
fc_question VARCHAR2(4000),
fc_answer VARCHAR2(4000)
);

--TABLE with constraints
CREATE TABLE flashcard(
fc_id INT,
fc_question VARCHAR2(4000) UNIQUE NOT NULL,
fc_answer VARCHAR2(4000),
PRIMARY KEY (fc_id)
);


--User Table
CREATE TABLE fc_user(
user_id INT,
user_name VARCHAR2(40) UNIQUE NOT NULL,
user_pw VARCHAR2(40) NOT NULL,
PRIMARY KEY(user_id)
);

--A Join/Junction Table
CREATE TABLE user_flashcard(
user_id INT,
fc_id INT,
PRIMARY KEY(user_id, fc_id),
FOREIGN KEY (user_id) REFERENCES fc_user(user_id),
FOREIGN KEY (fc_id) REFERENCES flashcard(fc_id)
);

TRUNCATE TABLE flashcard;