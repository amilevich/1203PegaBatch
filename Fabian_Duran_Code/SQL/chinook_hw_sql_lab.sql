-------------------------2.1 SELECT
--Task - Select all records from the Employee Table
SELECT * FROM employee;

--Task - Select all records from the Employee table where last name is King.
SELECT * FROM employee
WHERE lastname = 'King';

--Task - Select all records from the Employee table where first name is Andrew and REPORTSTO is Null
SELECT * FROM employee
WHERE firstname = 'Andrew' AND reportsto is null;


-------------------------2.2 ORDER BY
--Task - Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM album
ORDER BY title DESC;

--Task - Select first name from Customer and sort result set in ascending order by city
SELECT firstname FROM customer
ORDER BY city ASC;


-------------------------2.3 INSERT INTO
--Task - Insert two new records into Genre table
INSERT INTO genre (genreid, name) VALUES (26, 'Slapstick');
INSERT INTO genre (genreid, name) VALUES (27, 'Horror');

--Task - Insert two new records into Employee table
INSERT INTO employee (employeeid, lastname, firstname, email) 
VALUES (9,'Thorpe', 'Burton', 'burton@chinookcorp.com');

INSERT INTO employee (employeeid, lastname, firstname, email) 
VALUES (10,'Smith', 'Colleen', 'colleen@chinookcorp.com');

--Task - Insert two new records into Customer table
INSERT INTO customer (customerid, firstname, lastname, email) 
VALUES (60,'Smith', 'Colleen', 'colleen@gmail.com');

INSERT INTO customer (customerid, firstname, lastname, email) 
VALUES (61,'Thorpe', 'Burton', 'burton@gmail.com');


-------------------------2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE customer SET firstname = 'Robert', lastname = 'Walter' 
WHERE customerid = 32;
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE artist set name = 'CCR' WHERE artistid=76;


-------------------------2.5 LIKE
--Task – Select all invoices with a billing address like “T%”
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';


-------------------------2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT * FROM invoice WHERE total BETWEEN 15.00 AND 50.00;

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' and '01-MAR-04';

-------------------------2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
DELETE FROM invoiceline
WHERE invoiceid = 116 OR invoiceid = 245 OR invoiceid = 268
OR invoiceid = 61 OR invoiceid = 342 OR invoiceid = 50 OR invoiceid =290;

DELETE FROM invoice
WHERE customerid = 32;

DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';

-------------------------3.1 System Defined Functions
--Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION get_current_time
RETURN TIMESTAMP
IS
    current_time TIMESTAMP;
BEGIN
    current_time := CURRENT_TIMESTAMP;
    RETURN current_time;
END;
/
DECLARE
    time_ TIMESTAMP;
BEGIN
    time_ := get_current_time();
    DBMS_OUTPUT.PUT_LINE('The time is: ' || time_);
END;
/

--Task – create a function that returns the length of a mediatype from the mediatype table
CREATE OR REPLACE FUNCTION get_length
    (mediatype_id IN NUMBER)
RETURN NUMBER
IS
    m_length NUMBER;
BEGIN
    SELECT LENGTH(name) into m_length FROM mediatype WHERE mediatypeid = mediatype_id;
    RETURN m_length;
END;
/
DECLARE
    media_length NUMBER;
BEGIN
    media_length := get_length(1);
    DBMS_OUTPUT.PUT_LINE('The mediatype length of ID 1 is: ' || media_length);
END;
/
-------------------------3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION get_avg_invoice
RETURN NUMBER
IS
    avg_in NUMBER;
BEGIN
    SELECT AVG (total)INTO avg_in FROM invoice;
    RETURN avg_in;
END;
/
DECLARE
    invoice_avg NUMBER;
BEGIN
    invoice_avg := get_avg_invoice();
    DBMS_OUTPUT.PUT_LINE('The average of all totals is: ' || invoice_avg);
END;
/

--Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION get_most_expen
RETURN NUMBER
IS
    max_expen NUMBER;
BEGIN
    SELECT MAX (unitprice)INTO max_expen FROM track;
    RETURN max_expen;
END;
/

DECLARE
    max_expen NUMBER;
BEGIN
    max_expen := get_most_expen();
    DBMS_OUTPUT.PUT_LINE('The most expensive track is: ' || max_expen);
END;
/
-------------------------3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table

CREATE OR REPLACE FUNCTION find_avg_of_invoiceline_items
RETURN NUMBER
    IS find_avg NUMBER;
BEGIN
    SELECT AVG(unitprice) INTO find_avg FROM invoiceline;
    RETURN find_avg;
END;
/
DECLARE
    avg_total NUMBER;
BEGIN
    avg_total := find_avg_of_invoiceline_items();
    DBMS_OUTPUT.PUT_LINE('The average is '||avg_total);
END;
/

-------------------------3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION emp_bd_after(@b_date DATE)--was able to get this to work properly...
RETURNS TABLE
AS RETURN
    SELECT firstname, lastname FROM employee
    WHERE b_date >= '01-JAN-68';


CREATE OR REPLACE FUNCTION bd_after68
RETURN SYS_REFCURSOR
    IS emp  SYS_REFCURSOR;
BEGIN
    OPEN emp FOR
    SELECT * FROM employee WHERE birthdate >= '01-JAN-68';
END;
/
DECLARE
    e sys_refcursor;
    r employee%ROW TYPE;
BEGIN
    e := bd_after68;
    LOOP
        FETCH e INTO r;
        EXIT WHEN e%notfound;
        DBMS_output.put_line(r.fisrtname||r.lastname||', '||r.birthdate);
    END LOOP;
    CLOSE e;
END;
/

-------------------------4.0 Stored Procedures
--In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
-------------------------4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE emp_names
    (EMP OUT SYS_REFCURSOR) AS
BEGIN
    OPEN EMP FOR
    SELECT firstname, lastname FROM employee;
END;
/
DECLARE
    cursor_e SYS_REFCURSOR;
    name_f employee.firstname%TYPE;
    name_l employee.lastname%TYPE;
BEGIN
    emp_names(cursor_e);
    LOOP
        FETCH cursor_e INTO name_f, name_l;
        EXIT WHEN cursor_e%notfound;
        DBMS_output.put_line('First Name: '||name_f||'   Last Name: '||name_l);
    END LOOP;
    CLOSE cursor_e;
END;
/
-------------------------4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE update_emp
(emp_id in NUMBER, fname IN VARCHAR2, lname IN VARCHAR2, e_mail IN VARCHAR2) AS
BEGIN
    UPDATE employee SET firstname = fname, lastname = lname, email = e_mail
        WHERE employeeid = emp_id;  
    COMMIT;
END;
/

BEGIN
    update_emp(8, 'Bruce', 'Wayne', 'batman@chinook.com');
END;
/
--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE get_emp_manager 
(emp_id IN NUMBER, man_id OUT NUMBER, man_fname OUT VARCHAR2, man_lname OUT VARCHAR2) AS
BEGIN
    SELECT a.reportsto, b.firstname, b.lastname INTO man_id, man_fname, man_lname FROM employee a, employee b
    WHERE a.employeeid = emp_id AND a.reportsto = b.employeeid ;  
    --SELECT a.employeeid, a.lastname, a.firstname, a.reportsto, b.firstname, b.lastname FROM employee a, employee b WHERE a.reportsto = b.employeeid;
end;
/

DECLARE
    manager_id NUMBER;
    manager_fname VARCHAR2(20);
    manager_lname VARCHAR2(20);
BEGIN
    get_emp_manager (8, manager_id, manager_fname, manager_lname);
    DBMS_output.put_line('The Manager for Employee ID 8 is : ' || manager_id|| ', '||manager_fname||' '||manager_lname);
end;
/

-------------------------4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE cust_name_and_co
(cust_id IN NUMBER, fname OUT VARCHAR2, lname OUT VARCHAR2, cname OUT VARCHAR2) AS
BEGIN
    SELECT firstname, lastname, company INTO fname, lname, cname
    FROM customer WHERE customerid = cust_id;
END;
/

DECLARE
    first_name VARCHAR2(20);
    last_name VARCHAR2(20);
    co_name VARCHAR2(50);
BEGIN
    cust_name_and_co (10, first_name, last_name, co_name);
    DBMS_output.put_line('Here is the name and company of customer Id 10: '||first_name||' '||last_name||', '||co_name);
END;
/
    
-------------------------5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE delete_invoice
(invoice_id IN NUMBER) AS
BEGIN
    --SAVEPOINT; --do this here to not lose data
   
    EXECUTE IMMEDIATE 'ALTER TABLE invoiceline DROP CONSTRAINT FK_invoicelineinvoiceid';
   
    EXECUTE IMMEDIATE 'ALTER TABLE invoiceline ADD CONSTRAINT FK_invoicelineinvoiceid FOREIGN KEY (invoiceid) REFERENCES invoice (invoiceid) ON DELETE CASCADE'; 

    DELETE invoice WHERE invoiceid = invoice_id;
       
    --ROLLBACK    get data back
    COMMIT;
END;
/
--to test
BEGIN
    delete_invoice(100);
END;
/

--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE create_customer
(fname IN VARCHAR2, lname IN VARCHAR2) AS--all the other fields could be added in as well this is mostly demonstration purposes
BEGIN
--savepoint here
--simply adding the other fields in the input paramaters in the parentheses and into parentheses after 'VALUES' would work
    INSERT INTO customer (customerid, firstname, lastname)
    VALUES (1, 'fname', 'lname');     
--rollback here...
    COMMIT;
END;
/
--Test
BEGIN
    create_new_customer(9, 'Clark', 'Kent');
END;
/

-------------------------6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
-------------------------6.1 AFTER/FOR
-- Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER trig_after_new_emp
AFTER INSERT ON employee
BEGIN
    DBMS_OUTPUT.PUT_LINE('Employee record inserted');
END;
/
----Task – Create an after update trigger on the album table that fires after a row is inserted in the table.
CREATE OR REPLACE TRIGGER trig_after_new_row
AFTER UPDATE ON album
BEGIN
    DBMS_OUTPUT.PUT_LINE('Album record updated');
END;
/
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER trig_after_row_delete
AFTER DELETE ON customer
BEGIN
    DBMS_OUTPUT.PUT_LINE('Customer record deleted.');
END;
/
-------------------------7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT firstname, lastname, invoiceid FROM customer
INNER JOIN invoice ON customer.customerid=invoice.customerid;

-------------------------7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT invoice.customerid, firstname, lastname, invoiceid, total FROM customer
FULL OUTER JOIN invoice ON customer.customerid=invoice.customerid;

-------------------------7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT name, title FROM album
RIGHT JOIN artist on album.artistid = artist.artistid;

-------------------------7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * FROM artist CROSS JOIN album ORDER BY name ASC;

-------------------------7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT a.employeeid, a.lastname, a.firstname, a.reportsto, b.firstname, b.lastname FROM employee a, employee b WHERE a.reportsto = b.employeeid;
-------------------------9.0 Administration
--In this section you will be creating backup files of your database. After you create the backup file you will also restore the database. Research or try random things then communicate with batchmates, do not ask trainer.
--Task – Create a .bak file for the Chinook database.

--I was able to create a backup of the database, it wasn't a .bak
--or at least there isn't a way for me to identify the backup as .bak
--I went to Amazon web services website, accessed the RDBS that was made for this
--training course. I created an additional 'snapshot' under backup & maintenance
--this snapshot is a backup copy of the database that has the option to restore the database
--back to that snapshot instance