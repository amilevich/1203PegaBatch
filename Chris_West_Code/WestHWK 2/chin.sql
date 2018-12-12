/*
2.1 SELECT
Task: Select all records from the Employee table.
Task: Select all records from the Employee table where last name is King.
Task: Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.

*/


SELECT * FROM employee;

SELECT * FROM employee
    where lastname = 'King';

SELECT * FROM employee
    where firstname = 'Andrew' 
        AND reportsto = NULL;

/*
2.2 ORDER BY
Task: Select all albums in Album table and sort result set in descending order by title.
Task: Select first name from Customer and sort result set in ascending order by city
*/

SELECT * FROM album
    ORDER BY title DESC;
    
SELECT firstname FROM customer
    ORDER BY city ASC;
    
/*
2.3 INSERT INTO
Task: Insert two new records into Genre table
Task: Insert two new records into Employee table
Task: Insert two new records into Customer table
*/

INSERT INTO genre;
    
