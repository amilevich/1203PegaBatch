--Fake data for actor

INSERT INTO actor VALUES(1,'Adam Sandler', 'Big Daddy 3',TO_DATE('19960725','YYYYMMDD'), '12345 lane rd',125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(2,'Joe', 'Movie A',TO_DATE('19960725','YYYYMMDD'), '12345 lane rd',125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(3,'Tom', 'Movie B',TO_DATE('19960725','YYYYMMDD'), '12345 lane rd',125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(4,'Jerry', 'Movie B',TO_DATE('19960725','YYYYMMDD'), '12345 lane rd',125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(5,'Jerry', 'Movie B',TO_DATE('19960725','YYYYMMDD'), '12345 lane rd',125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(6,'Jerry', 'Movie B',TO_DATE('19960725','YYYYMMDD'), '12345 lane rd',125, '20-AUG-16 07.17.54.098000000 PM');
INSERT INTO actor VALUES(7,'Joe', 'Movie A',TO_DATE('19960725','YYYYMMDD'), '12345 lane rd',125, '20-AUG-16 07.17.54.098000000 PM');


SELECT * FROM actor;

SELECT act_name,act_film FROM actor;

SELECT * FROM actor WHERE act_film = 'Movie B';


--Change Tom & Jerry years in media to 150
UPDATE actor
SET act_years_in_media = 150
WHERE act_name = 'Tom'
OR
act_name = 'Jerry';

--See Actors that have been in media more than 130 years
SELECT * FROM actor
WHERE act_years_in_media > 130;


--AGREGATES
--SQL aggregatte functions return a single value, calculated from values in a column.

/*
AVG() - Returns the average value
Count() - Returns the number of rows
Max() - Returns the largest value
Max() - Returns the smallest value
Sum() -returns the sum
*/

SELECT COUNT(act_name) FROM actor;
SELECT COUNT(act_address) FROM actor;
SELECT AVG(act_years_in_media) FROM actor;

--SUM the years in acting of actor with the same name
SELECT act_name, SUM(act_years_in_media) FROM actor
GROUP BY act_name;

--Same as above but descending order
SELECT act_name, SUM(act_years_in_media) AS "YEARS" FROM actor
GROUP BY act_name
ORDER BY "YEARS" DESC;

--SQL scalar functions return a single value, based on the input value.
-- Input and Output PER record (NOT output for multiple records like Aggregate Functions)
/*
UPPER() - Converts a field to upper case.
LOWER() - Converts a field to lower case.
SUBSTR() - Extracts characters from a text field.
LENGTH() - Returns the length of a text field.
ROUND() - Rounds a numeric field
*/


--NOW USING movie_types, FAKE DATA
INSERT INTO movie_type VALUES(1,'Comedy');
INSERT INTO movie_type VALUES(2,'Horror');
INSERT INTO movie_type VALUES(3,'Drama');
INSERT INTO movie_type VALUES(4,'Action');
INSERT INTO movie_type VALUES(5,'Thriller');

SELECT * FROM movie_type;

--SHOW actors, movie, movie type
SELECT act_name, act_film, movie_category 
FROM actor
INNER JOIN movie_type
ON actor.movie_type = movie_type.movie_id;




---------------------------------------FUNCTION getMaxID
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


--TEST getMaxId using an Anonymous Function: a function with no name (can only use one)
DECLARE
max_number NUMBER;
BEGIN 
--    := is the assignment operator
max_number := get_max_id();
--  || is the concat
DBMS_OUTPUT.PUT_LINE('max number is: ' || max_number);
END;
/

------------Make a function called findMax
CREATE OR REPLACE FUNCTION findMax(x IN NUMBER, y IN NUMBER)
RETURN NUMBER 
IS 
max_num NUMBER;
BEGIN
    IF x > y THEN max_num := x;
    ELSE max_num := y;
    END IF;
    RETURN max_num;
END;
/

--Anonymous Function to test findmax()
DECLARE
first_number INT;
second_number INT;
max_number INT;
BEGIN

first_number := 25;
second_number := 48;
max_number := findMax(first_number,second_number);
DBMS_OUTPUT.PUT_LINE('Max of 25 and 48 is ' || max_number);
END;
/