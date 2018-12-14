--DDL: Data Definition Language

/*
    OracleDB Datatypes:
    NUMBER
    VARCHAR2
    DATE
    TIMESTAMP
    BLOB: Binary Large Object like images, movies etc.
    CLOB: Character Large Object like txt, doc, csv etc.
    
    Note: no boolean in OracleDB
*/
DROP TABLE flashcard;
DROP TABLE fc_user;
DROP TABLE user_flashcard; 
--FlashCard Table without Constraints
CREATE TABLE flashcard(
fc_id INT,
fc_question VARCHAR2(4000),
fc_answer VARCHAR2(4000)
);

/*
OracleDB Constraints
    Datatypes
    Not Null
    UNIQUE
    Primary Key: Add to a column to uniquely identify the record (also Not Null)
        And also for Relations to other tables' Foreign Keys
    Foreign Key: a column that represents the primary key of another table
    Default (if value is not applied, then use x like 18)
    Checked (see if value meets criteria like BETWEEN 18 and 125)
    
Candidate Key: Any column(s) that COULD be the Primary Key (not a keyword)
Composite Key: A Primary Key made up of 2 or more columns  (not a keyword)
*/

--Table w/ Constraints
CREATE TABLE flashcard(
 fc_id INT,
 fc_question VARCHAR2(4000) UNIQUE NOT NULL,
 fc_answer VARCHAR2(4000),
 PRIMARY KEY(fc_id)
);


--User Table
CREATE TABLE fc_user(
 user_id INT,
 user_name VARCHAR2(40) UNIQUE NOT NULL,
 user_pw VARCHAR2(40) NOT NULL,
 PRIMARY KEY(user_id)
);


--A Join/Junction Table
CREATE TABLE user_flashcard (
 user_id INT,
 fc_id INT,  
 PRIMARY KEY(user_id, fc_id),
 FOREIGN KEY(user_id) REFERENCES fc_user(user_id),
 FOREIGN KEY(fc_id) REFERENCES flashcard(fc_id)
);

TRUNCATE TABLE flashcard;

