--Fake Data for Actor
ACT_ID
ACT_NAME
ACT_FILM
ACT_BIRTH_DATE
ACT_ADDRESS
ACT_YEARS_IN_MEDIA
ACT_TIME_SIGNED_CONTRACT

INSERT INTO actor VALUES(1, 'Adam Sandler', 'Big Daddy 3', TO_DATE('19960725','YYYYMMDD'),'12345 lane rd', 125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(2, 'Joe', 'Movie A', TO_DATE('19960725','YYYYMMDD'),'12345 lane rd', 125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(3, 'Tom', 'Movie A', TO_DATE('19960725','YYYYMMDD'),'12345 lane rd', 125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(4, 'Jerry', 'Movie B', TO_DATE('19960725','YYYYMMDD'),'12345 lane rd', 125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(5, 'Jerry', 'Movie B', TO_DATE('19960725','YYYYMMDD'),'12345 lane rd', 125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(6, 'Jerry', 'Movie B', TO_DATE('19960725','YYYYMMDD'),'12345 lane rd', 125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(7, 'Joe', 'Movie A', TO_DATE('19960725','YYYYMMDD'),'12345 lane rd', 125, '20-AUG-16 07.17.54.098000000 PM');

SELECT * FROM actor;
SELECT act_name, act_film FROM actor;

SELECT * FROM actor
WHERE act_film = 'Movie A';

--Change Tom & Jerry years in media to 150
UPDATE actor
SET act_years_in_media = 150
WHERE act_name = 'Tom'
OR
act_name = 'Jerry';

--See Actors that have been in media more than 130 years
SELECT * FROM actor
WHERE act_years_in_media > 130;

--Aggregates
--SQL aggregate functions return a single value, calculated from values in a column.
/*
AVG() - Returns the average value
COUNT() - Returns the number of rows
MAX() - Returns the largest value
MIN() - Returns the smallest value
SUM() - Returns the sum
*/

SELECT COUNT(act_name) FROM actor;
SELECT COUNT(act_address) FROM actor;
SELECT AVG(act_years_in_media) FROM actor;

--SUM the years in acting of Actors with the same name
SELECT act_name, SUM(act_years_in_media) FROM actor
GROUP BY act_name;

--Same as above BUT descending order years 
SELECT act_name, SUM(act_years_in_media) AS "Years" FROM actor
GROUP BY act_name
ORDER BY "Years" desc;

--SQL scalar functions return a single value, based on the input value per record.
-- Input and Output PER record (NOT output for multiple records like Aggregate Functions)
/*
UPPER() - Converts a field to upper case
LOWER() - Converts a field to lower case
SUBSTR() - Extract characters from a text field
LENGTH() - Returns the length of a text field
ROUND() - Rounds a numeric field to the number of decimals specified
*/

SELECT UPPER(act_name) FROM actor;
SELECT LOWER(act_name), LENGTH(act_address) FROM actor;


----NOW USING movie_type Table, FAKE DATA
INSERT INTO movie_type VALUES(1, 'Comedy');
INSERT INTO movie_type VALUES(2, 'Horror');
INSERT INTO movie_type VALUES(3, 'Drama');
INSERT INTO movie_type VALUES(4, 'Action');
INSERT INTO movie_type VALUES(5, 'Thriller');

SELECT * FROM movie_type;

--Show Actors, movie, movie_type (not the number)
SELECT act_name, act_film, movie_category
FROM actor
INNER JOIN movie_type
ON movie_type = movie_id;


--------------FUNCTION getMax ID
CREATE OR REPLACE FUNCTION get_max_id
RETURN NUMBER
IS
    --section for variable declaration
    max_id NUMBER;
BEGIN
    SELECT MAX(act_id) INTO max_id FROM actor;
    RETURN max_id;
END;
/

------Test getMax ID using an Anonymous Function: a function with no name (can only use one)
DECLARE
    max_number NUMBER;
BEGIN
    -- := is the assignment operator in SQL unlike = in most EVERY OTHER LANGUAGE...cool
    max_number := get_max_id();
    -- || is the concat 
    DBMS_OUTPUT.PUT_LINE('max number is: ' || max_number);
END;
/


--Return the max either x or y
CREATE OR REPLACE FUNCTION findMax(x IN NUMBER, y IN NUMBER)







