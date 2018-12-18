/*
2.1 SELECT
Task: Select all records from the Employee table.
Task: Select all records from the Employee table where last name is King.
Task: Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
*/

--SELECT * FROM employee;
--
--SELECT * FROM employee
--    where lastname = 'King';
--
--SELECT * FROM employee
--    where firstname = 'Andrew' 
--        AND reportsto = NULL

/*
2.2 ORDER BY
Task: Select all albums in Album table and sort result set in descending order by title.
Task: Select first name from Customer and sort result set in ascending order by city
*/

--SELECT * FROM album
--    ORDER BY title DESC;
--    
--SELECT firstname FROM customer
--    ORDER BY city ASC;
    
/*
2.3 INSERT INTO
Task: Insert two new records into Genre table
Task: Insert two new records into Employee table
Task: Insert two new records into Customer table
*/

--INSERT INTO genre
--VALUES(26,'');
--
--INSERT INTO genre
--VALUES(27,'');

--INSERT INTO employee 
--VALUES();

--INSERT INTO Employee VALUES (9, 'East', 'North', '', '' ,'' ,'' , '', '', '', '', '', '', '', '');
--INSERT INTO Employee VALUES (10, 'West', 'South', '', '' ,'' ,'' , '', '', '', '', '', '', '', '');
--
--INSERT INTO Customer VALUES (60, 'East', 'North', '', '' ,'' ,'' , '', '', '', '4325671098', 'getsomething@gmail.com', 4);
--INSERT INTO Customer VALUES (61, 'West', 'South', '', '' ,'' ,'' , '', '', '', '3421245900', 'something@gmail.com', 3);

/*
2.4 UPDATE
Task: Update Aaron Mitchell in Customer table to Robert Walter
Task: Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
*/

--UPDATE CUSTOMER c
--SET c.FirstName = 'Robert', c.LastName = 'Walter'
--Where c.FirstName = 'Aaron' AND c.LastName = 'Mitchell'; 
--
--UPDATE Artist art
--SET art.Name = 'CCR'
--WHERE art.Name = 'Creedence Clearwater Revival';

/*
2.5 LIKE
Task: Select all invoices with a billing address like “T%”
*/

--SELECT * FROM INVOICE
--WHERE BillingAddress LIKE 'T%';

/*
2.6 BETWEEN
Task: Select all invoices that have a total between 15 and 50
Task: Select all employees hired between 1st of June 2003 and 1st of March 2004
*/

--SELECT * FROM invoice 
--WHERE total BETWEEN 15 AND 50;
--
--SELECT * FROM employee
--where HireDate between TO_DATE('2003-6-01 00:00:00','yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-3-1 00:00:00','yyyy-mm-dd hh24:mi:ss');

/*
2.7 DELETE
Task: Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
*/
--DELETE FROM InvoiceLine WHERE InvoiceId IN 
--(SELECT InvoiceId FROM Invoice 
--WHERE CustomerId=(SELECT CustomerId FROM Customer where Customer.firstName = 'Robert' 
--AND Customer.lastName = 'Walter'));
--
--DELETE FROM Invoice WHERE CustomerId=(SELECT CustomerId FROM Customer where Customer.firstName = 'Robert' 
--AND Customer.lastName = 'Walter');
--
--DELETE FROM CUSTOMER where Customer.firstName = 'Robert' 
--AND Customer.lastName = 'Walter';
/*
3.1 System Defined Functions
Task: Create a function that returns the current time.
Task: create a function that returns the length of a mediatype from the mediatype table
*/

--SELECT to_char(sysdate, 'HH24:MI:SS')
--FROM DUAL;
--
--SELECT (LENGTH(name)) 
--FROM mediatype;

/*
3.2 System Defined Aggregate Functions
Task: Create a function that returns the average total of all invoices 
Task: Create a function that returns the most expensive track
*/
--SELECT AVG (total)
--FROM invoice;
--
--SELECT MAX(unitprice)
--FROM track;
/*
3.3 User Defined Scalar Functions
Task: Create a function that returns the average price of invoiceline items in the invoiceline table
*/
--create or replace function getAvg
--return number
--is num1 number;
--begin
--SELECT round(AVG(UnitPrice),2) INTO num1 FROM invoiceline;
--return num1;
--end;
--/
--
--DECLARE
--number1 NUMBER;
--
--BEGIN
--number1 := getAvg;
--DBMS_OUTPUT.PUT_LINE('avg: ' || number1);
--END;
--/

/*
3.4 User Defined Table Valued Functions
Task: Create a function that returns all employees who are born after 1968.
--*/
--create or replace function getAfter()
--return TABLE;
--is num1 TABLE;
--AS RETURN
--begin
--SELECT * INTO num1 FROM employee WHERE birthdate > 1968;
--return num1;
--end;
--/

--DECLARE
--number1 Table;
--
--BEGIN
--number1 := getAfter;
--DBMS_OUTPUT.PUT_LINE('avg: ' || number1);
--END;
--/
--SELECT getAfter() from dual;

/*
4.1 Basic Stored Procedure
Task: Create a stored procedure that selects the first and last names of all the employees.
*/
--create or replace procedure SELECTEMPlo
--AS
--
--SELECT firstName, lastName FROM EMPLOYEE;
--GO;
--
--EXECUTE SELECTEMPlo();


/*
4.2 Stored Procedure Input Parameters
Task: Create a stored procedure that updates the personal information of an employee. Task: Create a stored procedure that returns the managers of an employee.
*/



/*
4.3 Stored Procedure Output Parameters
Task: Create a stored procedure that returns the name and company of a customer.
*/



/*
5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
Task: Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
Task: Create a transaction nested within a stored procedure that inserts a new record in the Customer table

*/



/*
6.1 AFTER/FOR
Task: Create an after insert trigger on the employee table fired after a new record is inserted into the table.
Task: Create an after update trigger on the album table that fires after a row is inserted in the table
Task: Create an after delete trigger on the customer table that fires after a row is deleted from the table.
*/
--create or replace trigger afterEmpUpdate
--after update on Employee
--for each row
--Begin
--DBMS_OUTPUT.PUT_LINE('triggered occurred S');
--End;
--/
--create or replace trigger afterEmpInsert
--after insert on Employee
--for each row
--Begin
--DBMS_OUTPUT.PUT_LINE('triggered occurred I');
--End;
--/
--
--create or replace trigger afterEmpDelete
--after delete on Employee
--for each row
--Begin
--DBMS_OUTPUT.PUT_LINE('triggered occurred D');
--End;
--/

/*
7.1 INNER
Task: Create an inner join that joins customers and orders and 
specifies the name of the customer and the invoiceId.
*/
--SELECT cs1.FirstName, cs1.LastName, in1.invoiceId 
--FROM customer cs1 INNER JOIN invoice in1
--ON cs1.customerId = in1.customerId;



/*
7.2 OUTER
Task: Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
*/
--SELECT Customer.customerId, Customer.firstname, Customer.lastname, invoice.invoiceId, invoice.total
--FROM Customer
--FULL OUTER JOIN Invoice ON Customer.customerId = invoice.CustomerId;


/*
7.3 RIGHT
Task: Create a right join that joins album and artist specifying artist name and title.
*/
--SELECT Artist.name, Album.title
--FROM Album
--RIGHT JOIN Artist ON
--Album.ArtistId = Artist.ArtistId;



/*
7.4 CROSS
Task: Create a cross join that joins album and artist and sorts by artist name in ascending order.
*/
--SELECT *
--FROM album
--CROSS JOIN artist
--ORDER BY artist.name ASC;


/*
7.5 SELF
Task: Perform a self-join on the employee table, joining on the reportsto column.
*/
--SELECT a.employeeid AS "Employee_ID", a.lastname AS "Employee_LastName", a.firstname AS "Employee_FirstName", b.employeeid AS "Supervisor_ID", b.lastname AS "Supervisor_lastname", b.firstname AS "Supervisor_firstname"
--FROM employee a, employee b
--WHERE a.reportsto = b.employeeid;


/*
9.0 Administration
In this section you will be creating backup files of your database. After you create the backup file you will also restore the database. Research or try random things then communicate with batchmates, do not ask trainer.
Task: Create a .bak file for the Chinook database.
*/
--Used the export Wizard in SQL Developer
