--2.0 SQL Queries
--2.1 SELECT
--Select all records from the Employee table.
SELECT * FROM employee;
--Select all records from the Employee table where last name is King.
SELECT * FROM employee WHERE lastname = 'King';
--Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM employee WHERE firstname = 'Andrew' AND reportsto IS NULL;

--2.2 ORDER BY
--Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM album ORDER BY title DESC;
--Select first name from Customer and sort result set in ascending order by city
SELECT firstname FROM customer ORDER BY city ASC;

--2.3 INSERT INTO
--Insert two new records into Genre table
INSERT INTO genre VALUES(26,'K-Pop');
INSERT INTO genre VALUES(27,'Italian Rap');

--Insert two new records into Employee table
INSERT INTO employee(employeeid, lastname, firstname) VALUES(11,'Boodwa','Karan');
INSERT INTO employee(employeeid, lastname, firstname) VALUES(12,'Wick','John');

--Insert two new records into Customer table
INSERT INTO customer(customerid, firstname, lastname, email) VALUES(60, 'Ken', 'Thompson', 'kthompson@unix.com');
INSERT INTO customer(customerid, firstname, lastname, email) VALUES(60, 'Dennis', 'Ritchie', 'dritch@unix.com');

--2.4 UPDATE
--Update Aaron Mitchell in Customer table to Robert Walter
UPDATE customer SET firstname='Robert', lastname='Walter' WHERE firstname='Aaron' AND lastname='Mitchell';

--Update name of artist in the Artist table "Creedence Clearwater Revival" to "CCR"
UPDATE artist SET name='CCR' WHERE name='Creedence Clearwater Revival';

--2.5 LIKE
-- Select all invoices with a billing address like “T%”
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

--2.6 BETWEEN
--Select all invoices that have a total between 15 and 50
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;

--Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

--2.7 DELETE
-- Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).

-- Need to delete records that rely on the customerid BEFORE deleting the customer record itself
-- Need to delete invoice items before (foreign key reference to customerid)
-- Need to delete invoiceline items FIRST (foreign key reference to invoiceid)
DELETE FROM invoiceline WHERE invoiceid IN (
    SELECT invoiceid  FROM invoice WHERE customerid = (
        SELECT customerid FROM customer WHERE firstname='Robert' AND lastname='Walter')
);
    
DELETE FROM invoice WHERE customerid = (
    SELECT customerid FROM customer WHERE firstname='Robert' AND lastname='Walter'
);

DELETE FROM customer WHERE firstname='Robert' AND lastname='Walter';

-- Seeing if record actually deleted (should return 0 rows) :
SELECT * FROM customer WHERE firstname='Robert' AND lastname='Walter';

--3.0 SQL Functions
--3.1 System Defined Functions
--Create a function that returns the current time.
create or replace FUNCTION get_date
RETURN DATE
IS today_date DATE;
BEGIN
    SELECT current_date INTO today_date FROM DUAL;
    RETURN(today_date);
END;
/

--create a function that returns the length of a mediatype from the mediatype table
create or replace FUNCTION get_length(mediaid IN NUMBER)
RETURN NUMBER
IS len NUMBER;
BEGIN
    SELECT LENGTH(name) INTO len FROM mediatype WHERE mediatypeid=mediaid;
    RETURN(len);
END;
/

--3.2 System Defined Aggregate Functions
--Create a function that returns the average total of all invoices
create or replace FUNCTION avg_invoice
RETURN NUMBER
IS average NUMBER(10,2);
BEGIN
    SELECT AVG(total) INTO average FROM invoice;
    RETURN(average);
END;
/

--Create a function that returns the most expensive track
create or replace FUNCTION max_track
RETURN VARCHAR2
IS most_exp VARCHAR2(200);
BEGIN

    SELECT name
    INTO most_exp
    FROM track WHERE (unitprice =( SELECT MAX(unitprice) FROM track) AND ROWNUM <=1);

    RETURN(most_exp);
END;
/

--3.3 User Defined Scalar Functions
--Create a function that returns the average price of invoiceline items in the invoiceline table
create or replace FUNCTION avg_price_invoiceline
RETURN NUMBER
IS avg_price NUMBER(10,2);
BEGIN
    SELECT AVG(unitprice) INTO avg_price FROM invoiceline;
    RETURN(avg_price);
END;
/

--3.4 User Defined Table Valued Functions
--Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION born_after_1968
RETURN SYS_REFCURSOR
IS o_ref SYS_REFCURSOR;
BEGIN
    OPEN o_ref FOR SELECT firstname, lastname, birthdate FROM employee WHERE birthdate >='01-JAN-69';
    RETURN o_ref;
END;
/

DECLARE
  result_cursor  SYS_REFCURSOR;
  firstname employee.firstname%type;
  lastname employee.lastname%type;
  birthdate employee.birthdate%type;
BEGIN 
result_cursor := born_after_1968;
LOOP
    FETCH result_cursor INTO firstname, lastname, birthdate;
    EXIT WHEN result_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(lastname || ', ' || firstname || ' ' || birthdate);
    
END LOOP;
CLOSE result_cursor;
END;
/



--4.0 Stored Procedures

--4.1 Basic Stored Procedure
--Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE get_employee_names(empl_cursor OUT SYS_REFCURSOR)
AS
BEGIN
OPEN empl_cursor FOR SELECT firstname, lastname FROM employee;
END;
/

DECLARE
  result_cursor  SYS_REFCURSOR;
  firstname employee.firstname%type;
  lastname employee.lastname%type;
BEGIN 
get_employee_names(empl_cursor=>result_cursor);
LOOP
    FETCH result_cursor INTO firstname, lastname;
    EXIT WHEN result_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(lastname || ', ' || firstname);
    
END LOOP;
CLOSE result_cursor;
END;
/

--4.2 Stored Procedure Input Parameters
--Create a stored procedure that updates the personal information of an employee.

-- Allows them to update their firstname (similar procedure can be made for any attribute)
CREATE OR REPLACE PROCEDURE update_empl_info(emplid IN employee.employeeid%type, newFirstname IN employee.lastname%type)
AS
BEGIN
    UPDATE employee SET employee.firstname=newFirstname WHERE employee.employeeid = emplid;
END;
/

-- Testing above procedure
EXECUTE update_empl_info(11, 'Kevin');


--Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE get_manager(emplid IN employee.employeeid%type, managerFName OUT employee.firstname%type, managerLName OUT employee.lastname%type)
AS 
BEGIN
SELECT firstname, lastname INTO managerFName, managerLName FROM employee WHERE employee.employeeid=(
    SELECT reportsto FROM employee WHERE employeeid=emplid
);
END;
/

-- Testing above procedure
DECLARE
  firstname employee.firstname%type;
  lastname employee.lastname%type;
BEGIN 
get_manager(6, managerFName=>firstname, managerLName=>lastname);
DBMS_OUTPUT.PUT_LINE(lastname || ', ' || firstname); -- Michael reports to Andrew
END;
/

--4.3 Stored Procedure Output Parameters
--Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE get_name_company(custid IN customer.customerid%type, 
                                   custfname OUT customer.firstname%type, 
                                   custlname OUT customer.lastname%type,
                                    custcomp OUT customer.company%type)
AS
BEGIN
SELECT firstname, lastname, company INTO custfname, custlname, custcomp FROM customer WHERE customerid=custid;
END;
/

-- Testing above procedure
DECLARE
  firstname customer.firstname%type;
  lastname customer.lastname%type;
  company customer.company%type;
BEGIN 
get_name_company(16, custfname=>firstname, custlname=>lastname, custcomp=>company);
DBMS_OUTPUT.PUT_LINE(lastname || ', ' || firstname || ' Company: ' || company ); 
END;
/

--5.0 Transactions

--Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE delete_invoice(id IN invoice.invoiceid%type)
AS
BEGIN
    DELETE FROM invoiceline WHERE invoiceid=id;
    DELETE FROM invoice WHERE invoiceid = id;
    COMMIT; -- commit makes this a transaction (makes changes above PERMANENT!)
END;
/
-- ~Testing~:
EXECUTE delete_invoice(1);

--Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE create_customer(id IN customer.customerid%type, first IN customer.firstname%type, last IN customer.lastname%type, email IN customer.email%type)
AS
BEGIN
    INSERT INTO customer(customerid, firstname, lastname, email) VALUES(id, first, last, email);
    COMMIT; -- commit makes this a transaction (makes changes above PERMANENT!)
END;
/
-- TESTING:
EXECUTE create_customer(62, 'John','Denver','jdenver@countryroads.home');


--6.0 Triggers

--6.1 AFTER/FOR
--Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER employee_trigger
AFTER INSERT ON employee
BEGIN
    DBMS_OUTPUT.PUT_LINE('EMPLOYEE INSERTED');
END;
/

-- Testing above trigger:
INSERT INTO employee(employeeid, lastname, firstname) VALUES(15, 'John', 'Cena');

--Create an after update trigger on the album table that fires after a row is inserted in the table
-- After update or after insert??? typo?
CREATE OR REPLACE TRIGGER album_trigger
AFTER UPDATE ON album
BEGIN
    DBMS_OUTPUT.PUT_LINE('ALBUM UPDATED');
END;
/

-- Testing above trigger:
INSERT INTO album VALUES(348, 'Flash Gordon', 51);

--Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER album_trigger
AFTER DELETE ON customer
BEGIN
    DBMS_OUTPUT.PUT_LINE('CUSTOMER DELETED');
END;
/


-- Not very creative but hey, it does a thing after the appropriate thing :D

--7.0 JOINS
--7.1 INNER
--Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT firstname, lastname , invoiceId FROM customer
INNER JOIN invoice on customer.customerid = invoice.customerid;

--7.2 OUTER
--Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT customer.customerid, firstname, lastname, invoiceid, total FROM customer
FULL OUTER JOIN invoice ON customer.customerid = invoice.customerid;

--7.3 RIGHT
--Create a right join that joins album and artist specifying artist name and title.
SELECT name, title FROM album
RIGHT JOIN artist ON album.artistid = album.artistid;

--7.4 CROSS
--Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * from album CROSS JOIN artist ORDER BY name ASC;

--7.5 SELF
--Perform a self-join on the employee table, joining on the reportsto column.
SELECT empl.firstname, empl.lastname, empl2.firstname AS reportsto_firstname, empl2.lastname AS reportsto_lastname FROM employee empl JOIN employee empl2 ON empl.reportsto=empl2.employeeid;

--9.0 Administration
--Create a .bak file for the Chinook database.
-- Through Oracle SQL Developer: 
-- Tools>Database Export then append the database backup file with .bak ;)
