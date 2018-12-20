--SQL LAB 
--David Hincapie



--2.1 SELECT
--Task – Select all records from the Employee table.
SELECT * FROM employee;

--Task – Select all records from the Employee table where last name is King.
SELECT * FROM employee WHERE lastname = 'King';

--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM employee WHERE firstname = 'Andrew' AND reportsto is null;



--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM album ORDER BY title DESC;

--Task – Select first name from Customer and sort result set in ascending order by city
SELECT * FROM customer ORDER BY city ASC;



--2.3 INSERT INTO
--Task – Insert two new records into Genre table
INSERT INTO genre VALUES (26, 'Punk');
INSERT INTO genre VALUES (27, 'Focus');

--Task – Insert two new records into Employee table
INSERT INTO employee VALUES (9, 'Beam','Jim',null,3,null,null,null,null,null,null,null,null,null,null);
INSERT INTO employee VALUES (10, 'Daniels','Jack',null,3,null,null,null,null,null,null,null,null,null,null);

--Task – Insert two new records into Customer table
INSERT INTO customer VALUES (60, 'John','Doe',null,null,null,null,null,null,null,null,'JohnDoe@gmail.com',3);
INSERT INTO customer VALUES (61, 'Jane','Doe',null,null,null,null,null,null,null,null,'JaneDoe@gmail.com',3);



--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE customer SET firstname = 'Robert', lastname = 'Walter'
WHERE  firstname = 'Aaron' AND lastname = 'Mitchell';

--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE artist SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';



--2.5 LIKE
--Task – Select all invoices with a billing address like “T%”
SELECT billingaddress FROM invoice WHERE billingaddress like 'T%';



--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';



--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
 DELETE FROM invoiceline WHERE invoiceid IN 
 (SELECT invoiceid FROM invoice WHERE customerid = 
 (SELECT customerid FROM customer WHERE firstname ='Robert' AND lastname = 'Walter'));
 DELETE FROM invoice WHERE customerid = (SELECT customerid FROM customer WHERE firstname ='Robert' AND lastname = 'Walter');
 DELETE FROM customer WHERE firstname ='Robert' AND lastname = 'Walter';



--3.0 SQL Functions
--In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
--3.1 System Defined Functions
--Task – Create a function that returns the current time.
ALTER SESSION SET nls_date_format = 'HH12:MI:SS';
create or replace function get_time
return DATE
IS cur_time DATE;
BEGIN
  SELECT CURRENT_TIMESTAMP
    INTO cur_time
    FROM dual;
  RETURN cur_time;
END;
/
SELECT get_time() FROM dual;

--Task – create a function that returns the length of a mediatype from the mediatype table
create or replace function get_media_length(id IN INT)
return INT
IS len INT;
begin
  select LENGTH(name)
    INTO len
    FROM mediatype
    WHERE mediatypeid = id;
  RETURN len;
END;
/
DECLARE
id INT;
namevar VARCHAR2(50);
BEGIN
id := get_media_length(2);
SELECT mediatype.name INTO namevar FROM mediatype WHERE mediatype.mediatypeid = 2;
DBMS_OUTPUT.PUT_LINE('The length of ' || '"' || namevar || '"' || ' is ' || id || '.');
END;
/


--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices
create or replace function get_invoice_avg
return NUMBER
IS average NUMBER(3,2);
BEGIN
  SELECT AVG(total)
    INTO average
    FROM invoice;
  RETURN average;
END;
/
SELECT get_invoice_avg() FROM DUAL;

--Task – Create a function that returns the most expensive track
CREATE OR REPLACE TYPE high_object AS OBJECT (
    name  VARCHAR(200),
    price   NUMBER(10,2)
);
/
CREATE OR REPLACE TYPE high_table AS TABLE OF high_object;
/
CREATE or replace function get_high_price 
return high_table
AS
v_high_table high_table;
highest_price NUMBER(10,2);
BEGIN
SELECT MAX(unitprice) INTO highest_price
FROM track;
SELECT high_object(A.name,A.unitprice)
BULK COLLECT INTO v_high_table
FROM
(SELECT name, unitprice
FROM track WHERE unitprice = highest_price) A;
RETURN v_high_table;

END;
/
SELECT * FROM TABLE(get_high_price);

DROP FUNCTION get_high_price;
DROP TYPE high_table;
DROP TYPE high_object;



--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE or replace function get_avg_price 
return NUMBER
IS
avg_price NUMBER(10,2);
BEGIN
SELECT AVG(unitprice) INTO avg_price
FROM invoiceline;
RETURN avg_price;
END;
/
SELECT get_avg_price() FROM DUAL;



--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE TYPE emp_object AS OBJECT (
    first_name  VARCHAR(20),
    last_name VARCHAR2(20),
    birth_date   DATE
);
/
CREATE OR REPLACE TYPE emp_table AS TABLE OF emp_object;
/
CREATE or replace function get_emp_born 
return emp_table
AS
v_emp_table emp_table;
BEGIN
SELECT emp_object(A.firstname,A.lastname,A.birthdate)
BULK COLLECT INTO v_emp_table
FROM
(SELECT firstname, lastname, birthdate
FROM employee WHERE birthdate >= ('01-Jan-1968')) A;
RETURN v_emp_table;
END;
/
SELECT * FROM TABLE(get_emp_born);



--4.0 Stored Procedures
--In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE pro_names AS
  CURSOR c_names IS
    SELECT firstname, lastname
      FROM employee;
  v_firstname         employee.firstname%TYPE;
  v_lastname          employee.lastname%TYPE;
BEGIN
OPEN c_names;
LOOP
FETCH c_names
INTO v_firstname, v_lastname;
EXIT WHEN c_names%NOTFOUND;
DBMS_OUTPUT.PUT_LINE(v_firstname || ' ' || v_lastname);
END LOOP;
CLOSE c_names;
END pro_names;
/

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE pro_update 
(v_id IN employee.employeeid%TYPE,
v_title IN employee.title%TYPE)
IS
BEGIN
UPDATE employee
SET title = v_title
WHERE employeeid = v_id;
DBMS_OUTPUT.PUT_LINE('Employee ID ' || v_id || 'title changed to ' || v_title);
END pro_update;

DECLARE
  V_ID NUMBER;
  V_TITLE VARCHAR2(30);
BEGIN
  V_ID := 10;
  V_TITLE := 'Beverage Manager';

  PRO_UPDATE(
    V_ID => V_ID,
    V_TITLE => V_TITLE
  );
--rollback; 
END;
/
SELECT * FROM employee;

--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE pro_manager (v_employeeid IN employee.employeeid%TYPE)
IS
v_empfirstname  employee.firstname%TYPE;
v_emplastname  employee.lastname%TYPE;
v_mgrfirstname  employee.firstname%TYPE;
v_mgrlastname  employee.lastname%TYPE;
BEGIN
SELECT mgr.firstname, mgr.lastname, emp.firstname, emp.lastname INTO v_mgrfirstname, v_mgrlastname, v_empfirstname, v_emplastname
FROM employee emp
INNER JOIN employee mgr
ON mgr.employeeid = emp.reportsto
WHERE emp.employeeid = v_employeeid;
DBMS_OUTPUT.PUT_LINE(v_mgrfirstname || ' ' || v_mgrlastname || ' IS THE MANAGER OF ' || v_empfirstname || ' ' || v_emplastname);
END pro_manager;
/

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE pro_customer
(v_customerid IN customer.customerid%TYPE,
v_firstname OUT customer.firstname%TYPE,
v_lastname OUT customer.lastname%TYPE,
v_company OUT customer.company%TYPE) IS
BEGIN
SELECT firstname, lastname, company INTO v_firstname, v_lastname, v_company FROM customer WHERE customerid = v_customerid;
DBMS_OUTPUT.PUT_LINE(v_firstname || ' ' || v_lastname || ' ' ||  v_company);
END pro_customer;
/


--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE pro_del_invoice
(v_invoiceid IN invoice.invoiceid%TYPE) IS
BEGIN
DELETE FROM invoiceline WHERE invoiceid = v_invoiceid;
DELETE FROM invoice WHERE invoiceid = v_invoiceid;
DBMS_OUTPUT.PUT_LINE('Record with invoice id: ' || v_invoiceid || ' has been deleted from invoice and invoiceline table.');
COMMIT;
END;
/

--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE pro_insert_cust
(
v_customerid IN customer.customerid%TYPE,
v_firstname IN customer.firstname%TYPE,
v_lastname IN customer.lastname%TYPE,
v_email IN customer.email%TYPE
) IS
BEGIN
INSERT INTO CUSTOMER (customerid, firstname, lastname, email) 
VALUES (v_customerid, v_firstname, v_lastname, v_email);
COMMIT;
DBMS_OUTPUT.PUT_LINE('Id: ' || v_customerid || ' First name: ' ||
v_firstname || ' Last name: ' || v_lastname || ' Email: ' || v_email || ' inserted into Customer table.');
END pro_insert_cust;
/



--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER trg_insert
AFTER INSERT ON employee
BEGIN
DBMS_OUTPUT.PUT_LINE('Trigger fired  after insert because new row inserted.');
END;
/

--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER trg_update
AFTER UPDATE ON album
BEGIN
DBMS_OUTPUT.PUT_LINE('Trigger fired after update because new row inserted.');
END;
/

--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER trg_delete
AFTER DELETE ON customer
BEGIN
DBMS_OUTPUT.PUT_LINE('Trigger fired after delete because row deleted.');
END;
/

--7.0 JOINS
--In this section you will be working with combing various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT customer.firstname, customer.lastname, invoice.invoiceid
FROM customer
INNER JOIN invoice
ON customer.customerid = invoice.customerid;


--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM customer
FULL OUTER JOIN invoice ON customer.customerid = invoice.customerid;

--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT artist.name, album.title
FROM album
RIGHT JOIN artist ON artist.artistid = album.artistid;

--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
FROM album
CROSS JOIN artist
ORDER BY artist.name ASC;

--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT a.firstname, a.lastname, b.firstname || ' ' || b.lastname AS "Manager"
FROM employee a, employee b
WHERE a.reportsto = b.employeeid;

--9.0 Administration
--In this section you will be creating backup files of your database. After you create the backup file you will also restore the database. Research or try random things then communicate with batchmates, do not ask trainer.
--Task – Create a .bak file for the Chinook database.





