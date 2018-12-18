--2.1 SELECT
--Task – Select all records from the Employee table.
SELECT * FROM employee;
--Task – Select all records from the Employee table where last name is King.
SELECT * FROM employee where lastname = 'King';
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM employee where firstname = 'Andrew' AND reportsto IS NULL;

--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM album ORDER BY title DESC;
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT firstname FROM customer ORDER BY city ASC;

--2.3 INSERT INTO
--Task – Insert two new records into Genre table
INSERT INTO genre(genreid, name) VALUES (26, 'Horror');
INSERT INTO genre(genreid, name) VALUES (27, 'Action');
--Task – Insert two new records into Employee table
INSERT INTO employee(employeeid, lastname, firstname, title, reportsto, birthdate, hiredate, address, city, state, country,
postalcode, phone, fax , email) 
VALUES (9, 'Kurosaki', 'Shun', 'IT Support', 1, '23-Jun-1994', '12-AUG-2015', 'address', 'Lethbridge', 'AB', 'Canada', 'T2G 3K7', 
'+1 (403) 326-9328', '+1 (403) 456-9828', 'shun@gmail.com');
INSERT INTO employee(employeeid, lastname, firstname, title, reportsto, birthdate, hiredate, address, city, state, country,
postalcode, phone, fax , email) 
VALUES (10, 'Yuki', 'Haou', 'IT Support', 1, '10-MAR-92', '19-JUL-2016', 'somewhere AVE', 'Lethbridge', 'AB', 'Canada', 
'T1G 8L7', '+1 (403) 456-9828', '+1 (403) 326-3892', 'haou@gmail.com');
--Task – Insert two new records into Customer table
INSERT INTO customer(customerid, firstname, lastname, address, city, state, country, postalcode, phone, email, supportrepid) 
VALUES (60, 'Shun', 'Kurosaki', 'address', 'Lethbridge', 'AB', 'Canada', 'T2G 3K7', '+1 (403) 326-9328', 'shun@gmail.com', 3);
INSERT INTO customer(customerid, firstname, lastname, address, city, state, country, postalcode, phone, email, supportrepid) 
VALUES (61, 'Haou', 'Yuki', 'somewhere AVE', 'Lethbridge', 'AB', 'Canada', 'T1G 8L7', '+1 (403) 456-9828', 'haou@gmail.com', 5);

--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE customer SET firstname = 'Robert', lastname = 'Walter' WHERE firstname = 'Aaron' AND lastname = 'Mitchell';   
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE artist SET name = 'CCR' WHERE name = 'Creedence Clearwater Revival';

--2.5 LIKE
--Task – Select all invoices with a billing address like “T%”
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;
--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter 
--(There may be constraints that rely on this, find out how to resolve them).
DELETE FROM invoiceline 
WHERE EXISTS (SELECT invoiceid 
              FROM invoice WHERE invoice.customerid = 32 AND invoice.invoiceid = invoiceline.invoiceid);
DELETE FROM invoice WHERE customerid = 32;
DELETE FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter';

--3.1 System Defined Functions
--Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION get_time_current
RETURN TIMESTAMP
IS
    c_time TIMESTAMP;
BEGIN
    c_time := LOCALTIMESTAMP();
    RETURN c_time;
END;
/

DECLARE
    current_time TIMESTAMP;
BEGIN
    -- := is for assignment operator in SQL
    current_time := get_time_current();
    -- || is the concat
    DBMS_OUTPUT.PUT_LINE('Current time is: ' || current_time);
END;
/

--Task – create a function that returns the length of a mediatype from the mediatype table
CREATE OR REPLACE FUNCTION get_length_mediatype
RETURN NUMBER
IS
    c_length NUMBER;
BEGIN
    SELECT LENGTH(mediatype.name) INTO c_length FROM mediatype 
    WHERE mediatypeid = 2;
    RETURN c_length;
END;
/

DECLARE
    t_length NUMBER;
BEGIN
    -- := is for assignment operator in SQL
    t_length := get_length_mediatype();
    -- || is the concat
    DBMS_OUTPUT.PUT_LINE('Length of media name: ' || t_length);
END;
/

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION get_invoices_avg
RETURN NUMBER
IS
    total_avg NUMBER;
BEGIN
    SELECT AVG(invoice.total) INTO total_avg FROM invoice;
    RETURN total_avg;
END;
/

DECLARE
    t_avg NUMBER;
BEGIN
    -- := is for assignment operator in SQL
    t_avg := get_invoices_avg();
    -- || is the concat
    DBMS_OUTPUT.PUT_LINE('Invoice total average is: ' || t_avg);
END;
/

--Task – Create a function that returns the most expensive track
--SELECT name, unitprice FROM track WHERE unitprice = (SELECT MAX(unitprice) FROM track);
CREATE OR REPLACE FUNCTION get_expensive_track
RETURN NUMBER
IS
    expensive_track NUMBER;
BEGIN
    SELECT MAX(unitprice) INTO expensive_track FROM track;
    RETURN expensive_track;
END;
/

DECLARE
    expen_track NUMBER;
BEGIN
    -- := is for assignment operator in SQL
    expen_track := get_expensive_track();
    -- || is the concat
    DBMS_OUTPUT.PUT_LINE('Most expensive track price: ' || expen_track);
END;
/
--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
SELECT * FROM invoiceline;
CREATE OR REPLACE FUNCTION get_invoiceline_avg
RETURN NUMBER
IS
    price_avg NUMBER;
BEGIN
    SELECT AVG(unitprice) INTO price_avg FROM invoiceline;
    RETURN price_avg;
END;
/

DECLARE
    p_avg NUMBER;
BEGIN
    -- := is for assignment operator in SQL
    p_avg := get_invoiceline_avg();
    -- || is the concat
    DBMS_OUTPUT.PUT_LINE('Invoiceline price average: ' || p_avg);
END;
/

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
SELECT * FROM employee WHERE birthdate > '31-DEC-1968';
CREATE OR REPLACE FUNCTION get_birthdates
RETURN SYS_REFCURSOR
IS
    employ_bd SYS_REFCURSOR;
    
BEGIN
    OPEN employ_bd FOR
    SELECT firstname, lastname, birthdate FROM employee WHERE birthdate > '31-DEC-1968';
    RETURN employ_bd;
END;
/

DECLARE
    employ SYS_REFCURSOR;
    fname VARCHAR2(50);
    lname VARCHAR2(50);
    bdate DATE;
BEGIN
    -- := is for assignment operator in SQL
    employ := get_birthdates();
    LOOP
    FETCH employ INTO fname, lname, bdate;
    EXIT WHEN employ%notfound;
    -- || is the concat
    DBMS_OUTPUT.PUT_LINE('Employee born after 1968: ' || lname || ' ' || fname || ' ' || bdate);
    END LOOP;
END;
/

--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE all_employees(employ_cur OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN employ_cur FOR 
    SELECT firstname, lastname FROM employee;
    
END;
/

DECLARE
    employ SYS_REFCURSOR;
    fname VARCHAR2(50);
    lname VARCHAR2(50);

BEGIN
    -- := is for assignment operator in SQL
    all_employees(employ);
    LOOP
    FETCH employ INTO fname, lname;
    EXIT WHEN employ%notfound;
    -- || is the concat
    DBMS_OUTPUT.PUT_LINE('All employees: ' || lname || ' ' || fname);
    END LOOP;
END;
/

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
SELECT * FROM employee;
CREATE OR REPLACE PROCEDURE update_employee(e_employid IN employee.employeeid%TYPE,
e_employemail IN employee.email%TYPE)
IS 
BEGIN
    UPDATE employee SET email = e_employemail WHERE employeeid = e_employid;
    COMMIT;
END;
/

BEGIN
    update_employee(9, 'kurosaki@gmail.com');
END;
/

--Task – Create a stored procedure that returns the managers of an employee.
SELECT * FROM employee;
SELECT e.lastname AS Employee,
       m.lastname AS Manager
  FROM employee e, employee m 
  WHERE e.employeeid = 3 AND m.employeeid = e.reportsto;

CREATE OR REPLACE PROCEDURE manager_of_employee(e_id IN NUMBER)
IS
    e_lastn VARCHAR2(50);
    e_firstn VARCHAR2(50);
    m_lastn VARCHAR2(50);
    m_firstn VARCHAR2(50);
BEGIN
    SELECT e.lastname AS ELastN, e.firstname AS EFirstN,
        m.lastname AS MLastN, m.firstname AS MFirstN INTO e_lastn, e_firstn, m_lastn, m_firstn
    FROM employee e, employee m 
    WHERE e.employeeid = e_id AND m.employeeid = e.reportsto;
    DBMS_OUTPUT.PUT_LINE(m_firstn || ' ' || m_lastn || ' is the manager of ' || e_firstn || ' ' || e_lastn);
END;
/

BEGIN
    manager_of_employee(7);
END;
/

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
SELECT * FROM customer;
CREATE OR REPLACE PROCEDURE cus_name_company(c_id IN NUMBER, c_fname OUT VARCHAR2, c_lname OUT VARCHAR2, c_company OUT VARCHAR2)
IS

BEGIN
    SELECT firstname, lastname, company INTO c_fname, c_lname, c_company FROM customer
    WHERE customerid = c_id;
END;
/

DECLARE 
    fname VARCHAR2(50);
    lname VARCHAR2(50);
    companyn VARCHAR2(50);
BEGIN
    cus_name_company(9, fname, lname, companyn);
    DBMS_OUTPUT.PUT_LINE('Customer: ' || fname || ' ' || lname || ' Company: ' || companyn);
END;
/

--5.0 Transactions
--Task – Create a transaction that given a invoiceId will delete that invoice 
--(There may be constraints that rely on this, find out how to resolve them).
SELECT * FROM invoiceline;
SELECT * FROM invoice;
SELECT * FROM customer WHERE firstname = 'Daan';
CREATE OR REPLACE PROCEDURE delete_invoice(inv_id IN NUMBER)
IS
BEGIN
    DELETE FROM invoiceline 
    WHERE EXISTS (SELECT invoiceid 
              FROM invoice WHERE invoiceline.invoiceid = inv_id);
    DELETE FROM invoice WHERE invoiceid = inv_id;
    COMMIT;
END;
/

BEGIN
    delete_invoice(343);
END;
/
--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE insert_customer(c_id IN customer.customerid%TYPE, c_fname IN customer.firstname%TYPE,
	   c_lname IN customer.lastname%TYPE, c_company IN customer.company%TYPE, c_address IN customer.address%TYPE,
       c_city IN customer.city%TYPE, c_state IN customer.state%TYPE, c_country IN customer.country%TYPE,
       c_postalcode IN customer.postalcode%TYPE, c_phone IN customer.phone%TYPE, c_fax IN customer.fax%TYPE,
       c_email IN customer.email%TYPE, c_supportrepid IN customer.supportrepid%TYPE)
IS
BEGIN

  INSERT INTO customer(customerid, firstname, lastname, company, address, city, state, country, 
  postalcode, phone, fax, email, supportrepid) 
  VALUES (c_id, c_fname, c_lname, c_company, c_address, c_city, c_state, c_country, 
        c_postalcode, c_phone, c_fax, c_email, c_supportrepid);

  COMMIT;

END;
/

BEGIN
   insert_customer(63,'Kai','Sky','The Corp','somewhere', 'Orlando', 'FL', 'USA', '32801', '+1 (904) 349-2983',
   '(null)', 'Sky@gmail.com', 2);
END;
/

--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER employee_trigger
AFTER INSERT ON employee FOR EACH ROW

BEGIN
    DBMS_OUTPUT.PUT_LINE('In employee trigger after insert');
END;
/

INSERT INTO employee(employeeid, lastname, firstname, title, reportsto, birthdate, hiredate, address, city, state, country,
postalcode, phone, fax , email) 
VALUES (11, 'Newon', 'Jinx', 'IT Support', 6, '01-JAN-92', '19-JUL-2016', 'somewhere AVE', 'Lethbridge', 'AB', 'Canada', 
'T1G 8L7', '+1 (403) 456-6253', '+1 (403) 326-3892', 'JNewon@gmail.com');

--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER album_trigger
AFTER UPDATE ON album FOR EACH ROW

BEGIN
    DBMS_OUTPUT.PUT_LINE('In the update album trigger after insert');
END;
/
SELECT * FROM album;
UPDATE album SET title = 'Hybrid Theory' WHERE artistid = 275;

--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER customer_trigger
AFTER DELETE ON customer FOR EACH ROW

BEGIN
    DBMS_OUTPUT.PUT_LINE('In the customer trigger after delete');
END;
/

DELETE FROM invoiceline 
WHERE EXISTS (SELECT invoiceid 
              FROM invoice WHERE invoice.customerid = 61 AND invoice.invoiceid = invoiceline.invoiceid);
DELETE FROM invoice WHERE customerid = 61;
DELETE FROM customer WHERE customerid = 61;

--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT customer.firstname, customer.lastname, invoice.invoiceid FROM customer INNER JOIN invoice 
ON customer.customerid = invoice.customerid;

--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, 
--lastname, invoiceId, and total.
SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total 
FROM customer FULL OUTER JOIN invoice ON customer.customerid = invoice.customerid;

--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT artist.name, album.title
FROM album RIGHT OUTER JOIN artist ON artist.artistid = album.artistid;

--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * FROM album CROSS JOIN artist ORDER BY artist.name ASC;

--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT * FROM employee e, employee m WHERE e.reportsto = m.reportsto;

--9.0 Administration
--Task – Create a .bak file for the Chinook database.

/*
    Through my RDS AWS console I created a snapshot 
    to save the current version of my database. 
*/
COMMIT;