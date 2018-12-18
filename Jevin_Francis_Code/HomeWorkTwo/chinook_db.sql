--Jevin Francis

-- SELECT 

--Task – Select all records from the Employee table.
SELECT * 
FROM EMPLOYEE;
--Task – Select all records from the Employee table where last name is King.
SELECT * 
FROM EMPLOYEE 
WHERE lastname = 'King';
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL
SELECT *
FROM EMPLOYEE
WHERE firstname = 'Andrew' AND reportsto IS NULL;

--ORDER BY

--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM album
ORDER BY title DESC;
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT firstname
FROM customer
ORDER BY city ASC;

--ISERT INTO

--Task – Insert two new records into Genre table
INSERT INTO genre VALUES (37, 'My Genere 2');
INSERT INTO genre VALUES (26, 'My Genere 1');
INSERT INTO genre VALUES (27, 'My Genere 2');
SELECT * FROM genre;

--Task – Insert two new records into Employee table
INSERT INTO employee (employeeid, lastname, firstname) VALUES (9,'Francis','Jevin');
INSERT INTO employee (employeeid, lastname, firstname) VALUES (10,'Tucker','Tom');
SELECT * FROM employee;
    
--Task – Insert two new records into Customer table
INSERT INTO customer VALUES (100, 'Jevin', 'Francis', 'No Companey', 'Address', 'City', 'State', 'country', 00000, 000000000, 0000000000, 'qq@mail.com', 1);
INSERT INTO customer VALUES (101, 'Jevin', 'Francis', 'No Companey', 'Address', 'City', 'State', 'country', 00000, 000000000, 0000000000, 'qq@mail.com', 1);
SELECT * FROM customer;

--UPDATE

--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--LIKE

--Task – Select all invoices with a billing address like “T%”
SELECT *
FROM invoice
WHERE billingaddress LIKE 'T%';

--BETWEEN

--Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM employee
WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

--DELETE

--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints
--       that rely on this, find out how to resolve them).
SELECT * FROM customer 
WHERE firstname = 'Robert' AND lastname = 'Walter';

SELECT * FROM invoice
WHERE customerid = (SELECT customerid FROM customer 
                    WHERE firstname = 'Robert' AND lastname = 'Walter');
                    
SELECT * FROM invoiceline
WHERE invoiceid IN (SELECT invoiceid FROM invoice
                    WHERE customerid = (SELECT customerid FROM customer 
                                        WHERE firstname = 'Robert' AND lastname = 'Walter'));

DELETE FROM invoiceline
WHERE invoiceid IN (SELECT invoiceid FROM invoice
                    WHERE customerid = (SELECT customerid FROM customer 
                                        WHERE firstname = 'Robert' AND lastname = 'Walter'));
DELETE FROM invoice
WHERE customerid = (SELECT customerid FROM customer 
                    WHERE firstname = 'Robert' AND lastname = 'Walter');

DELETE FROM customer 
WHERE firstname = 'Robert' AND lastname = 'Walter';
                                   

--System Defined Functions
--Task –Create a function that returns the current time.
CREATE OR REPLACE FUNCTION currentTime
RETURN VARCHAR2
IS
   timeNow VARCHAR2(20);
BEGIN
    SELECT to_char( CURRENT_TIMESTAMP, 'HH12:MI:SS' ) INTO timeNow FROM dual;
    RETURN timeNow;
END;
/

DECLARE
    timeNow VARCHAR2(20);
BEGIN
    timeNow := currentTime();
    DBMS_OUTPUT.PUT_LINE('Current Time Is: ' || timeNow);
END;
/

SELECT currentTime() FROM dual;
SELECT to_char( SYSTIMESTAMP AT TIME ZONE 'EST', 'HH12:MI:SS' ) FROM dual;
SELECT sys FROM dual;


--Task –create a function that returns the length of a mediatype from the mediatype table

SELECT name FROM mediatype WHERE mediatypeid = 1;  -- NOT SURE WHAT THE QUESTIONS IS ASKING

--ASSUMING ITS ASKING FOR THE LENGTH OF THE NAME
--Creating Function
CREATE OR REPLACE FUNCTION findLength (x IN INT)
RETURN INT
IS
    mediaTypeLength INT;
BEGIN
    SELECT LENGTH(name) INTO mediatypelength FROM mediatype WHERE mediatypeid = x;
    RETURN mediatypelength;
END;
/

--Testing Function
DECLARE
    mediaTypeId INT;
    nameIS VARCHAR2(50);
BEGIN
    mediaTypeId:= 1; 
    DBMS_OUTPUT.PUT_LINE('Length Is : ' || findlength(mediaTypeId));
END;
/

-- System Defined Aggregate Functions

-- Task –Create a function that returns the average total of all invoices
SELECT AVG(total) FROM invoice;

--Creating Function
CREATE OR REPLACE FUNCTION averageTotal
RETURN NUMBER
IS
   avgTotal NUMBER(8,2);
BEGIN
    SELECT AVG(total)INTO avgTotal FROM invoice;
    RETURN avgTotal;
END;
/

--Running Function
DECLARE
    avgTotal NUMBER(8,2);
BEGIN
    avgTotal := averageTotal();
    DBMS_OUTPUT.PUT_LINE('The Average Total of all Invoice is : ' || avgTotal);
END;
/
-- Task –Create a function that returns the most expensive track

SELECT name, unitprice
FROM track 
WHERE unitprice = (SELECT MAX(unitprice)
                    FROM track);

--Creating Function
CREATE OR REPLACE FUNCTION expensiveTrack
RETURN NUMBER
IS
    expensiveyes NUMBER(5,2);
BEGIN
    SELECT MAX(unitprice) INTO expensiveyes  FROM track;
    RETURN expensiveyes;
END;
/

--Running the Function
DECLARE
    expensiveTrackPrice NUMBER(5,2);
BEGIN 
    expensiveTrackPrice := expensivetrack();
    DBMS_OUTPUT.PUT_LINE('Most Expensive Track is: '|| expensivetrack );
END;
/


--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION findAverageInvoiceline
RETURN NUMBER
IS
    invoiceAverage NUMBER;
BEGIN
    SELECT (ROUND(AVG(unitprice), 2)) INTO invoiceaverage FROM invoiceline;
    RETURN invoiceAverage;
END;
/

-- Run the Function
DECLARE
    invAvg NUMBER;
BEGIN
    invAvg := findAverageInvoiceline();
     DBMS_OUTPUT.PUT_LINE('Average of all Invoiceline is: '|| invAvg );
END;
/

-- User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968
--Select Statement for the result
SELECT lastname, firstname, birthdate
FROM employee
WHERE TO_CHAR(birthdate, 'YYYY') > 1968;
----Create Function

CREATE OR REPLACE FUNCTION getEmployeBornAfter(bornYear IN NUMBER)
RETURN SYS_REFCURSOR
IS
    cur SYS_REFCURSOR;
BEGIN
    OPEN cur FOR SELECT lastname, firstname, birthdate
    FROM employee
    WHERE TO_CHAR(birthdate, 'YYYY') > bornYear;
    RETURN cur;
END;
/


--
SELECT lastname, firstname, birthdate
FROM employee
WHERE TO_CHAR(birthdate, 'YYYY') > 1969;
----Create Function
CREATE OR REPLACE FUNCTION bornAfter( birthyear IN NUMBER)
RETURN empTableList PIPELINED
IS
    CUSROR borncur;
BEGIN

     borncur :=  (SELECT lastname, firstname, birthdate
                      FROM employee
                      WHERE TO_CHAR(birthdate, 'YYYY') > bornAfter);
    
    RETURN  orncur;
    
END;
/



--4.0 Stored Procedures
--In this section you will be creating and executing stored procedures. You will be creating various types
--of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
--Select
SELECT firstname, lastname
FROM employee;

--Creating the Procedure
CREATE OR REPLACE PROCEDURE getallemployee
IS
    CURSOR cur IS 
    SELECT firstname, lastname 
    FROM employee;
    
    efname VARCHAR2(50);
    elname VARCHAR2(50);
BEGIN
    OPEN cur;
    LOOP
    FETCH cur INTO efname, elname;
    EXIT WHEN cur%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(efname ||' ' || elname);
    END LOOP;
    CLOSE cur;
END getallemployee;
/

--Running the Stored Procedure
BEGIN
   getallemployee();
END;
/

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE updateState(
    eid IN INT,
    estate IN VARCHAR2)
IS
BEGIN
    UPDATE employee
    SET state = estate
    WHERE employeeid = eid;
END updateState;
/

--Run the procedure
BEGIN
    updateState(7,'FL');
    DBMS_OUTPUT.PUT_LINE('Updated State to : FL');
END;
/

--Task – Create a stored procedure that returns the managers of an employee.
--SELECT
SELECT firstname, lastname
    FROM employee
    WHERE employeeid = (SELECT reportsto
                        FROM employee
                        WHERE firstname = 'Jane'
                        AND lastname = 'Peacock');

--Create Stored Procedure to get manager
CREATE OR REPLACE PROCEDURE getManagerName(
efname IN VARCHAR2,
elname IN VARCHAR2,
mfname OUT VARCHAR2,
mlname OUT VARCHAR2)
IS
BEGIN
    SELECT firstname, lastname
    INTO mfname, mlname
    FROM employee
    WHERE employeeid = (SELECT reportsto
                        FROM employee
                        WHERE firstname = efname
                        AND lastname = elname);
END getManagerName;
/

--Run the procedure to get manager name
DECLARE
    mfname VARCHAR2(50);
    mlname VARCHAR2(50);
BEGIN
    getManagerName('Nancy','Edwards', mfname, mlname);
    DBMS_OUTPUT.PUT_LINE('Manager: ' || mfname ||' ' || mlname);   
END;
/
--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
--Select
SELECT firstname, lastname, company
FROM customer
WHERE customerid = 10;

--Create Procedures
CREATE OR REPLACE PROCEDURE getNameCompany(
    cid IN INT,
    cfname OUT VARCHAR2,
    clname OUT VARCHAR2,
    ccompany OUT VARCHAR2)
IS
BEGIN
    SELECT firstname, lastname, company
    INTO cfname, clname,ccompany
    FROM customer
    WHERE customerid = cid;
END getNameCompany;
/

--Running the Procedure
DECLARE
    cid  INT;
    cfname  VARCHAR2(50);
    clname  VARCHAR2(50);
    ccompany  VARCHAR2(50);
BEGIN
    cid := 10;
    getnamecompany(cid,cfname,clname,ccompany);
    DBMS_OUTPUT.PUT_LINE('Name: ' || cfname ||' ' || clname || ' Company: '|| ccompany); 
END;
/
--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored
--procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that
--rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE deleteInvoice (iid IN INT)
IS
BEGIN
    DELETE FROM invoiceline
        WHERE invoiceid IN (SELECT invoiceid FROM invoice
                            WHERE invoiceid = (iid));
    DELEtE FROM invoice WHERE invoiceid = (iid);
END deleteInvoice;
/

--Test the Procedure
DECLARE
    iid  INT;
BEGIN
    iid := 220;
    deleteInvoice(iid);
END;
/

--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer
--table
CREATE OR REPLACE PROCEDURE insertIntoCustomer(
    cid IN INT,
    cfname IN VARCHAR2,
    clname IN VARCHAR2,
    ccompany IN VARCHAR2,
    caddress IN VARCHAR2,
    cCity IN VARCHAR2,
    cState IN VARCHAR2,
    cCountry IN VARCHAR2,
    cPostalCode IN VARCHAR2,
    cPhone IN VARCHAR2,
    cFax IN VARCHAR2,
    cEmail IN VARCHAR2,
    cSupportRepID IN NUMBER
    )
IS
BEGIN

    INSERT INTO customer VALUES (cid, cfname, clname, ccompany, 
                                caddress, cCity, cState, cCountry, 
                                cPostalCode, cPhone, cFax, cEmail, cSupportRepID);

END insertIntoCustomer;
/

--Test Procedure
BEGIN
    insertIntoCustomer(60, 'Jevin', 'Francis',
                        'Revature', '28th dr', 'Coral',
                        'FL','USA','33000', '5463353343', '','me@mail.com', 5);
END;
/

--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are
--executed on a table.
--3
--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the
--table.
CREATE OR REPLACE TRIGGER afterInsertTrigger
AFTER INSERT ON employee
FOR EACH ROW
BEGIN
DBMS_OUTPUT.PUT_LINE('Triggerd the after Insert');
END;
/
--Testing Trigger
INSERT INTO employee (employeeID, firstname, lastname)VALUES (15, 'Jevin', 'Francis');
DELETE FROM employee WHERE employeeID = 15;


--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER afterUpdateTrigger
AFTER UPDATE ON album
FOR EACH ROW
BEGIN
DBMS_OUTPUT.PUT_LINE('Triggerd the after Update');
END;
/
--Testing Update
UPDATE album SET title = 'nothing to see here' WHERE albumid = 1;

--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the
--table.
CREATE OR REPLACE TRIGGER afterDeleteTrigger
AFTER DELETE ON customer
FOR EACH ROW
BEGIN
DBMS_OUTPUT.PUT_LINE('Triggerd the after Delete');
END;
/
--Testing
DELETE FROM invoiceline
WHERE invoiceid IN (SELECT invoiceid FROM invoice
                    WHERE customerid = (SELECT customerid FROM customer 
                                        WHERE firstname = 'Robert' AND lastname = 'Walter'));
DELETE FROM invoice
WHERE customerid = (SELECT customerid FROM customer 
                    WHERE firstname = 'Robert' AND lastname = 'Walter');

DELETE FROM customer 
WHERE firstname = 'Robert' AND lastname = 'Walter';

--7.0 JOINS
--In this section you will be working with combing various tables through the use of joins. You will work
--with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and
--the invoiceId.
SELECT invoiceid, firstname, lastname 
FROM customer 
INNER JOIN invoice
ON customer.customerid = invoice.customerid
ORDER BY invoiceid ASC;

--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId,
--firstname, lastname, invoiceId, and total.\
SELECT invoiceid, customer.customerid, firstname, lastname, invoice.total
FROM customer
FULL OUTER JOIN invoice
ON customer.customerid = invoice.customerid
ORDER BY invoice.invoiceid;


-- Task – Create a right join that joins album and artist specifying artist name and title.
SELECT album.title, artist.name AS Artist
FROM album
RIGHT JOIN artist
ON artist.artistid = album.artistid
ORDER BY album.title;

--Task – Create a cross join that 
--joins album and artist and sorts by artist name in ascending order.
SELECT artist.name AS Artist, album.title
FROM album
CROSS JOIN artist
ORDER BY Artist ASC;

--Task – Perform a self-join on the employee table, joining on the reportsto column
SELECT *
FROM employee A, Employee B
WHERE A.reportsto = B.reportsto;