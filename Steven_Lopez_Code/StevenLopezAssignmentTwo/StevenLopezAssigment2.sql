--2.1 SELECT Task 
--Select all records from the Employee table
SELECT * FROM employee;

--Task Select all records from the Employee table where last name is King.
SELECT * FROM employee WHERE lastname='King';

--Task Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM employee WHERE firstname='Andrew' AND reportsto IS null;

--2.2 ORDER BY
--Task –Select all albumsin Album table and sort result set in descending orderby title.
SELECT * FROM album ORDER BY title desc;

--Task–Select first name from Customer and sort result set in ascending order by city 
SELECT firstname FROM customer ORDER BY city;

--2.3 INSERT INTO
--Task – Insert two new records into Genre table
INSERT INTO genre (genreid,genre.name) VALUES(26,'Country');
INSERT INTO genre VALUES(27,'Disco');

--Task –Insert two new records into Employee table
INSERT INTO employee (employeeid,firstname,lastname) 
    VALUES (9,'Lopez','Steven');
INSERT INTO employee (employeeid,firstname,lastname) 
    VALUES (10,'Lopez','Josue');

--Task –Insert two new records into Customer table
INSERT INTO customer (customerid,firstname,lastname,email) 
    VALUES (61,'John','Cena','john.cena@example.com');
INSERT INTO customer (customerid,firstname,lastname,email) 
    VALUES (60,'Tony','Stark','no.avengerhere@example.com');
    

--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE customer SET firstname='Robert',lastname='Walter' WHERE firstname='Aaron' AND lastname='Mitchell';

--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE artist SET name='CCR' WHERE name='Creedence Clearwater Revival';


--2.5 LIKE
--Task – Select all invoices with a billing address like “T%” 2.6 BETWEEN
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

--Task – Select all invoices that have a total between 15 and 52.6 BETWEEN
SELECT * FROM invoice WHERE total BETWEEN 15 AND 52.7;

--Task – Select all invoices that have a totalbetween 15 and 50
SELECT * FROM invoice WHERE total BETWEEN 15 AND 51;

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee WHERE hiredate BETWEEN '1-JUN-03' AND '1-MAR-04';

--2.7 DELETE
--Task –Delete a record in Customer table where the name is Robert Walter(There may be constraints that rely on this, 
--find out how to resolve them).
----------
--To delete Robert Walter, first delete the invoiceline who have parents in invoice, 
--who have parents referencing the Rober Walter record in customer
DELETE FROM invoiceline WHERE invoiceid IN (SELECT invoiceid  FROM invoice WHERE customerid = (SELECT customerid FROM customer WHERE firstname='Robert' AND lastname='Walter'));
DELETE FROM invoice WHERE customerid = (SELECT customerid FROM customer WHERE firstname='Robert' AND lastname='Walter');
DELETE FROM customer WHERE firstname='Robert' AND lastname='Walter';

----------
--Then delete the invoices who reference Rober in the customer table.
SELECT * FROM invoiceline WHERE invoiceid IN (SELECT invoiceid  FROM invoice WHERE customerid = (SELECT customerid FROM customer WHERE firstname='Robert' AND lastname='Walter'));
SELECT * FROM invoice WHERE customerid = (SELECT customerid FROM customer WHERE firstname='Robert' AND lastname='Walter');

-----------
--Finally, delete from the customers table.
SELECT * FROM customer WHERE firstname='Robert' AND lastname='Walter';
------------

--3.0 SQL Functions In this section you will be using the Oracle system functions, as well as your own functions, to perform 
--various actions against the database
--3.1 System Defined Functions
--Task –Create a function that returns the current time.

--Functions that runs the system function CURRENT_TIMESTAMP
CREATE OR REPLACE FUNCTION find_today
    RETURN TIMESTAMP
    IS get_time TIMESTAMP;
    BEGIN
        SELECT CURRENT_TIMESTAMP INTO get_time FROM DUAL;
        RETURN(get_time);
    END;
/

DECLARE 
    ntime TIMESTAMP;
    BEGIN
        ntime := find_now;
        DBMS_OUTPUT.PUT_LINE('The current time is ' || TO_CHAR (ntime, 'HH12:MI:SS AM'));
    END;
/


--Task create a function that returns the length of a mediatype from the mediatype table

--Another function that runs a system function, but this time the scalar function Length is used.
CREATE OR REPLACE FUNCTION get_mediatype_length(mt_id IN NUMBER)
    RETURN NUMBER
    IS len NUMBER;
    BEGIN
        SELECT LENGTH(name) INTO len FROM mediatype WHERE mediatypeid=mt_id;
        RETURN(len);
    END;
/

DECLARE 
    len NUMBER;
    mt_id NUMBER;
    BEGIN
        mt_id := 1;
        len := get_mediatype_length(mt_id);
        DBMS_OUTPUT.PUT_LINE('The media''s length is ' || len);
    END;
/

SELECT get_mediatype_length(1) AS "Name Length" FROM DUAL;
SELECT name, get_mediatype_length(mediatypeid)  AS "Name Length" FROM mediatype;


--3.2 System Defined Aggregate Functions
--Task– Create a function that returns the average total of all invoices


--In this function, the AVG() function is used on the total column of invoices.
CREATE FUNCTION get_invoice_totalavg
    RETURN NUMBER
    IS avg_total NUMBER(10,2);
    BEGIN
        SELECT AVG(total) INTO avg_total FROM invoice;
        RETURN(avg_total);
    END;
/
--Declare statement used to execute the total_avg function
DECLARE 
    total_avg NUMBER;
    BEGIN
        total_avg := get_invoice_totalavg;
        DBMS_OUTPUT.PUT_LINE('The total average of invoices is ' || total_avg);
    END;
/
 SELECT get_invoice_totalavg() FROM DUAL;   


--Task –Create a function that returns the most expensive track

--the most expensive track is found, and then the value is compared to every track in the table,
--from the return only one record.
create or replace FUNCTION get_max_unitprice
    RETURN VARCHAR2
    IS most_exp VARCHAR2(60);
    BEGIN
         SELECT track.name INTO most_exp FROM track WHERE unitprice = (SELECT MAX(unitprice) FROM track) AND ROWNUM <= 1;
        RETURN(most_exp);
    END;
/

DECLARE
    most_exp VARCHAR(50);
    BEGIN
    most_exp := get_max_unitprice;
    DBMS_OUTPUT.PUT_LINE('The most expensive track is "' || most_exp || '"');
    END;
/

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table

--the same as the previous one, I guess the user defined function wasn't needed on the previous one.
CREATE FUNCTION get_avg_price
    RETURN NUMBER
    IS max_price NUMBER(10,2);
    BEGIN
        SELECT AVG(unitprice) INTO max_price FROM track;
        RETURN(max_price);
    END;
/
--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.

--First we define an object, which will act as a record(row) of a temp table.
create or replace TYPE emp_obj AS OBJECT
       (employeeid  NUMBER,
        lastname    VARCHAR2(20),
        firstname   VARCHAR2(20),
        birthdate   DATE
        );
/
--Then we create a table and with the object previously defined as a row template.
create or replace TYPE emp_result AS TABLE OF emp_obj;
/

--then inside the function we do the query and put the resultSet into the table created above, and that's it, we're done.
create or replace FUNCTION get_emp_bday
    RETURN emp_result AS emp emp_result;
    BEGIN 
        SELECT emp_obj(e.employeeid, e.firstname, e.lastname, e.birthdate)
        BULK COLLECT INTO emp
        FROM (SELECT employeeid, firstname,lastname, birthdate FROM employee
        WHERE birthdate > '1/Jan/1969') e;
        RETURN emp;
    END get_emp_bday;
/
DECLARE 
    BEGIN
        FOR emp IN (select firstname, lastname, birthdate FROM TABLE(get_emp_bday)) LOOP
            DBMS_OUTPUT.PUT_LINE('The employees born after 1969 were ' || emp.firstname || ' ' || emp.lastname || ' born at ' || emp.birthdate);
        END LOOP;
    END;
/
SELECT * FROM employee WHERE birthdate > '31-DEC-1968';
SELECT * FROM TABLE(get_emp_bday);

--4.0 Stored Procedures,
--In this section you will be creating and executing stored procedures. You will be creating various types 
--of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure


--Task – Create a stored procedure that selects the first and last names of all the employees.

--we use a query to the list and a for loop to go through it
create or replace PROCEDURE get_emp_name
AS
    BEGIN
    DBMS_OUTPUT.PUT_LINE('List of all employees');
    FOR emp IN (SELECT firstname, lastname FROM employee)
    LOOP
        DBMS_OUTPUT.PUT_LINE(emp.firstname || '  ' || emp.lastname);
    END LOOP;
    END;

execute get_emp_name;
/

--another way is to use a cursor.
create or replace PROCEDURE get_emp_full_name
AS
BEGIN
    DECLARE 
    e_id employee.employeeid%type; 
    e_fname employee.firstname%type; 
    e_lname employee.lastname%type; 
    CURSOR e_employee IS 
        SELECT employeeid, firstname, lastname FROM employee; 
    BEGIN 
        OPEN e_employee; 
        LOOP 
        FETCH e_employee INTO e_id, e_fname, e_lname; 
            EXIT WHEN e_employee%notfound; 
        dbms_output.put_line(e_id || ' ' || e_fname || ' ' || e_lname); 
        END LOOP; 
    CLOSE e_employee; 
    END;
END;
/

execute get_emp_full_name;

--4.2 Stored Procedure Input Parameters
--Task –Create a stored procedure that updates the personal information of an employee.

-- Declare a function with the parameters to hold the neccessary information to update the table.
create or replace PROCEDURE set_emp_byid(
e_id INTEGER,
e_fname VARCHAR2,
e_lname VARCHAR2,
e_title VARCHAR2,
e_phone VARCHAR2,
e_email VARCHAR2
)
AS
    BEGIN
    UPDATE employee 
    SET firstname=e_fname, lastname=e_lname, title=e_title, phone=e_phone, email=e_email
    WHERE employeeid = e_id;
    END;
 /   
execute set_emp_byid(10,'John','Cena','Meme Star','555-5555','john.cena@gmail.com');
/

--Task –Create a stored procedure that returns the managers of an employee.

CREATE OR REPLACE PROCEDURE get_managers(
e_id INTEGER,
e_manager OUT VARCHAR2,
e_fname OUT VARCHAR2,
e_lname OUT VARCHAR2
)
IS
BEGIN
    SELECT e_id, firstname, lastname INTO e_manager, e_fname, e_lname FROM employee WHERE employeeid = (SELECT reportsto FROM employee WHERE e_id=employeeid);
END;

DECLARE
    e_search INTEGER;
    e_id INTEGER;
    e_fname VARCHAR2(50);
    e_lname VARCHAR(50);
    BEGIN
        e_search :=3;
        get_managers(e_search, e_id, e_fname, e_lname);
        DBMS_OUTPUT.PUT_LINE('Manager ' || e_fname|| ' ' || e_lname);
    END;

--4.3 Stored Procedure Output Parameters
--Task –Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE get_cust_namecompany_byid(
    search_id INTEGER,
    c_fname OUT VARCHAR2,
    c_lastname OUT VARCHAR2,
    c_company OUT VARCHAR2)
IS
    BEGIN
    SELECT firstname, lastname, company INTO c_fname, c_lastname, c_company FROM customer WHERE customerid=search_id;
    END;
/

DECLARE
    search_id INTEGER;
    c_fname VARCHAR2(50);
    c_lastname VARCHAR2(50);
    c_company VARCHAR2(50);
    BEGIN
    search_id := 4;
    get_cust_namecompany_byid(search_id, c_fname, c_lastname, c_company);
    DBMS_OUTPUT.PUT_LINE('Customer ' || search_id || ', ' || c_fname || ' ' || c_lastname || ' works for ' || c_company);
    END;
/


--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task –Create a transaction that given a invoice Id will delete that invoice (There may be constraints that rely on this, 
--find out how to resolve them).

--Procedure that takes an integer as a parameter and delete the invoice with it and 
--also deletes the invoices that references to maintain referential integrety.
CREATE OR REPLACE PROCEDURE delete_invoice
(
invoice_id INTEGER
)
AS
BEGIN
    DELETE FROM invoiceline WHERE invoiceid = invoice_id;
    DELETE FROM invoice WHERE invoiceid = invoice_id;
    COMMIT;--Commit to actually perform a transaction
END;
/

DECLARE
    BEGIN
        delete_invoice(2);
    END;
--just checking if it works.
    SELECT *  FROM invoiceline WHERE invoiceid = 2;
    SELECT * FROM invoice WHERE invoiceid = 2;
    
--Task –Create a transaction nested within a stored procedure that inserts a new record in the Customer table.
CREATE OR REPLACE PROCEDURE insert_customer
(
c_id INTEGER,
c_fname VARCHAR2,
c_lname VARCHAR2,
c_email VARCHAR2
)
AS
BEGIN
    INSERT INTO customer (customerid, firstname, lastname, email) VALUES (c_id, c_fname, c_lname, c_email);
    COMMIT;--Commit to actually perform a transaction
END;
/

BEGIN
    insert_customer(62, 'Mike', 'Tyson','mike.tyson@example.com');
END;

--6.0 Triggers In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Task -Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE TRIGGER after_emp_insert
AFTER INSERT
ON employee
BEGIN
    DBMS_OUTPUT.PUT_LINE('COngratz you inserted a new row, why dont you insert another one?!!');
END;

INSERT INTO employee (employeeid,firstname,lastname) 
    VALUES (11,'John','Wick');
    
--Task –Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE TRIGGER after_alb_update
AFTER UPDATE
ON album
BEGIN
    DBMS_OUTPUT.PUT_LINE('COngratz you updated a row, seriously you should stop!!');
END;

UPDATE album SET title='DONE' WHERE albumid='3';


--Task –Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE TRIGGER after_cust_delete
AFTER DELETE
ON customer
BEGIN
    DBMS_OUTPUT.PUT_LINE('...');
END;
/
SELECT * FROM customer WHERE customerid='1';
DELETE FROM customer WHERE customerid='60';


--7.0 JOINS 
--In this section you will be working with combing various tables through the use of joins. You will work 
--with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT firstname, lastname, invoiceid 
    FROM customer c INNER JOIN invoice i 
    ON c.customerid = i.customerid;

--7.2 OUTER 
--Task –Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT c.customerid, firstname, lastname, invoiceid, total 
    FROM customer c FULL OUTER JOIN invoice i 
    ON c.customerid = i.customerid;

--7.3 RIGHT
--Task –Create a right join that joins album and artist specifying artist name and title.
SELECT ar.name, title FROM album al RIGHT JOIN artist ar
    ON ar.artistid = al.artistid;

--7.4 CROSS
--Task –Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * FROM album CROSS JOIN artist ORDER BY (name) asc;

--7.5 SELF
--Task –Perform a self-join on the employee table, joining on the reportsto column.
SELECT e1.employeeid, e1.reportsto, e1.firstname AS Name_emp, e2.firstname AS Reports_to FROM employee e1 INNER JOIN employee e2 ON e2.employeeid=e1.reportsto;

--9.0 Administration
--In this section you will be creating backup files of your database. After you create the backup file you will also restore the database.
--Research or try random things then communicate with batchmates, do not ask trainer.
--Task – Create a .bak file for the Chinook database.

--***********************************
--Tools -> Database Export -> Select Connection -> next(many times) -> finish
--
