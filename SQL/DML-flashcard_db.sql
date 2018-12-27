--DML: Data Manipulation Language (CRUD, Only on Table Records/Rows)

----------------------------FLASHCARD Fake Data
--CREATE
INSERT INTO flashcard VALUES(1, 'What is Java?', 'Awesome');
INSERT INTO flashcard VALUES(2, 'What is C#', 'Lame');
INSERT INTO flashcard(fc_id,fc_question) VALUES(3, 'What is Java??');
COMMIT;
FC_ID
FC_QUESTION
FC_ANSWER

---READ: Uses the SELECT keyword to return a **ResultSet**
--* means return all columns
SELECT * FROM flashcard;
SELECT fc_question FROM flashcard;
SELECT fc_question, fc_answer FROM flashcard;
--Alias using the AS Keyword for the ResultSet Column Name, NOT a permanent change
SELECT fc_question AS "Question", 
       fc_answer AS "Answer" 
FROM flashcard;


--UPDATE
UPDATE flashcard 
SET fc_question = 'What is Java!';
--WHERE fc_id = 1;


--DELETE
DELETE flashcard; 
--WHERE fc_id = 2;


--------------------------------------FC_USER Fake Data
INSERT INTO fc_user VALUES(1, 'Batman', '123');
INSERT INTO fc_user VALUES(2, 'Superman', '321');
INSERT INTO fc_user VALUES(3, 'Chris Pratt', '555');
INSERT INTO fc_user VALUES(4, 'Bobbert', '444');
SELECT * FROM fc_user;
WHERE user_id = 1;

--Referencial integrity: Foreign Key must point to a valid primary key value!
--                 Primary Key & Foreign key must match!
--Can a Foreign Key null? yes they can be null
DELETE FROM fc_user
WHERE user_id = 3; --Trying to delete Chris?

--------------------------------------USER_FLASHCARD Fake Data
SELECT * FROM user_flashcard;
--                          user_id, fc_id
INSERT INTO user_flashcard VALUES(1, 1);
INSERT INTO user_flashcard VALUES(1, 2); --Batman, What is C#
INSERT INTO user_flashcard VALUES(1, 3);
INSERT INTO user_flashcard VALUES(null, null);
INSERT INTO user_flashcard VALUES(2, 1); --Superman, What is Java?
INSERT INTO user_flashcard VALUES(3, 3); --Chris Pratt, What is Java??
INSERT INTO user_flashcard VALUES(5, 1); --? , What is Java?
                                --5 is not found in fc_user so it is 
                                -- violating Referencial integrity 
                                -- if this record was inserted then
                                -- it would be considered an **orphan record**

--Joins: Connect 2 or more tables to create a more detailed ResultSet
--Show a ResultSet with a User_id and their FlashCard e.g. Batman, What is C#, answer
-- Do not show user_pw and other useless data
SELECT user_name, fc_question, fc_answer
FROM flashcard a 
INNER JOIN user_flashcard b ON a.fc_id = b.fc_id
INNER JOIN fc_user c ON b.user_id = c.user_id;

COMMIT;

