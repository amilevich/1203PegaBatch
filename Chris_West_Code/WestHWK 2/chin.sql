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
SELECT * FROM genre;

INSERT INTO genre
VALUES(26,'');

INSERT INTO genre
VALUES(27,'');

--INSERT INTO employee 
--VALUES();

INSERT INTO Employee VALUES (9, 'East', 'North', '', '' ,'' ,'' , '', '', '', '', '', '', '', '');
INSERT INTO Employee VALUES (10, 'West', 'South', '', '' ,'' ,'' , '', '', '', '', '', '', '', '');

INSERT INTO Customer VALUES (60, 'East', 'North', '', '' ,'' ,'' , '', '', '', '4325671098', 'getsomething@gmail.com', 4);
INSERT INTO Customer VALUES (61, 'West', 'South', '', '' ,'' ,'' , '', '', '', '3421245900', 'something@gmail.com', 3);

/*
2.4 UPDATE
Task: Update Aaron Mitchell in Customer table to Robert Walter
Task: Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
*/

UPDATE CUSTOMER c
SET c.FirstName = 'Robert', c.LastName = 'Walter'
Where c.FirstName = 'Aaron' AND c.LastName = 'Mitchell'; 

UPDATE Artist art
SET art.Name = 'CCR'
WHERE art.Name = 'Creedence Clearwater Revival';

/*
2.5 LIKE
Task: Select all invoices with a billing address like “T%”
*/

SELECT * FROM INVOICE
WHERE BillingAddress LIKE 'T%';

/*
2.6 BETWEEN
Task: Select all invoices that have a total between 15 and 50
Task: Select all employees hired between 1st of June 2003 and 1st of March 2004
*/

SELECT * FROM invoice 
WHERE total BETWEEN 15 AND 50;

SELECT * FROM employee
where HireDate between TO_DATE('2003-6-01 00:00:00','yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-3-1 00:00:00','yyyy-mm-dd hh24:mi:ss');

/*
2.7 DELETE
Task: Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
*/
SELECT Customer.customerid, Inovoice.customerid FROM ((Customer where
FirstName = 'Robert' AND LastName = 'Walter')
INNER JOIN Inovice ON Inovoice.customerid = Customer.customerid);
--SELECT customerid FROM customer cus
--WHERE FirstName = 'Robert' AND LastName = 'Walter'
--    SELECT customerid FROM Invoice inv where cus.customerid = inv.customerid;