-- DML: Data Manipulation Language (CRUD, Only on table records/rows)


--------------------------FLASH CARD FAKE DATA
--CREATE
INSERT INTO flashcard VALUES(1,'What is Java?','Awesome');
INSERT INTO flashcard VALUES(2,'What is C#?','Lame!');
INSERT INTO flashcard (fc_id, fc_question) VALUES(3,'What is Java??');
FC_ID
FC_QUESTION
FC_ANSWER

--READ uses the SELECT keyword to return a **Result Set**
-- * means return all columns
SELECT * FROM flashcard;
SELECT fc_question FROM flashcard;

--ALIAS using the AS Keyword for the ResultSet Column Name, NOT a permanent change
SELECT fc_question AS "Question", fc_answer AS "Answer" FROM flashcard;

--UPDATE
UPDATE flashcard
SET fc_question = 'What is Java!'
WHERE fc_id = 1;

--DELETE
DELETE flashcard
WHERE fc_id =2;


---------------------FC_USER FAKE DATA
INSERT INTO fc_user Values(1, 'Batman', '123');
INSERT INTO fc_user Values(2, 'Superman', '321');
INSERT INTO fc_user Values(3, 'Chris', '123');
SELECT * FROM fc_user;


---------------------USER_FLASHCARD FAKE DATA
--                              user_id, fc_id
INSERT INTO user_flashcard VALUES(1,1);
INSERT INTO user_flashcard VALUES(2,2);
INSERT INTO user_flashcard VALUES(3,2);
SELECT * FROM user_flashcard;

--Joins: Connect 2 or more tables to create a more detailed ResultSet
--Show a ResultSet with a User_id and their FlashCard e.g. Batman, What is C#
--Do not show user_pw and other useless data
SELECT user_name, fc_question, fc_answer    
FROM fc_user
INNER JOIN user_flashcard ON fc_user.user_id = user_flashcard.user_id
INNER JOIN flashcard ON flashcard.fc_id = user_flashcard.user_id;

