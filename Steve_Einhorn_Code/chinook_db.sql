-- Steven Einhorn

-- 2.1 SELECT

-- a) Select all records from the Employeetable.
        SELECT * FROM EMPLOYEE;
        
-- b) Select all records from the Employee table where last name is King.
        SELECT * FROM EMPLOYEE
         WHERE LASTNAME = 'King';
         
-- c) Select all records from the Employee table where first name is Andrewand REPORTSTOis NULL.
        SELECT * FROM EMPLOYEE
         WHERE FIRSTNAME = 'Andrew'
           AND REPORTSTO is Null;

-- 2.1 ORDER BY

-- a) Select all albumsin Albumtable and sort result set in descending orderby title.
        SELECT * FROM ALBUM
         ORDER BY TITLE DESC;
         
-- b) Select first name from Customerand sort result set in ascending orderbycity
        SELECT FIRSTNAME FROM CUSTOMER
         ORDER BY CITY;
         
-- 2.3 INSERT INTO

-- a) Insert two new records into Genre table
        INSERT INTO GENRE (GENREID, NAME)
        VALUES (26, 'Folk');
        INSERT INTO GENRE (GENREID, NAME)
        VALUES (27, 'Ethnic');
        
-- b) Insert two new records into Employee table 
        INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, BIRTHDATE)
        VALUES (9, 'Einhorn', 'Steven', '24-NOV-60');
        INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, BIRTHDATE)
        VALUES (10, 'John', 'Elton', '11-MAY-52');
        
-- c) Insert two new records into Customer table
        INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
        VALUES (60, 'Mitch', 'Trubisky', 'mtrubisky@gmail.com');
        INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL)
        VALUES (61, 'Matt', 'Nagy', 'mnagy@gmail.com');
        
-- 2.4 UPDATE

-- a) Update Aaron Mitchell in Customer table to Robert Walter
        UPDATE CUSTOMER
           SET FIRSTNAME = 'Robert',
               LASTNAME  = 'Walter'
         WHERE FIRSTNAME = 'Aaron'
           AND LASTNAME  = 'Mitchell';
        
-- b) Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR” 
        UPDATE ARTIST
           SET NAME = 'CCR'
         WHERE NAME = 'Creedence Clearwater Revival';
         
-- 2.5 LIKE

-- a) Select all invoices with a billing address like “T%”
        SELECT * FROM INVOICE
         WHERE BILLINGADDRESS LIKE 'T%';
         
-- 2.6 BETWEEN

-- a) Select all invoices that have a total between 15 and 50
        SELECT * FROM INVOICE
         WHERE TOTAL BETWEEN 15 AND 50;
         
-- b) Select all employees hired between 1st of June 2003 and 1st of March 2004 
        SELECT * FROM EMPLOYEE
         WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-2004';
         
-- 2.7 DELETE

-- a) Delete a record in Customer table where the name is Robert Walter
--    (There may be constraints that rely on this, find out how to resolve them)

        SELECT CUSTOMERID FROM CUSTOMER
         WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
         
        SELECT INVOICEID FROM INVOICE
         WHERE CUSTOMERID = ( SELECT CUSTOMERID FROM CUSTOMER
                               WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');
        SELECT * FROM INVOICELINE
         WHERE INVOICEID IN ( SELECT INVOICEID FROM INVOICE
                               WHERE CUSTOMERID = ( SELECT CUSTOMERID FROM CUSTOMER
                                                     WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'))
        ;
        DELETE FROM INVOICELINE
         WHERE INVOICEID IN ( SELECT INVOICEID FROM INVOICE
                               WHERE CUSTOMERID = ( SELECT CUSTOMERID FROM CUSTOMER
                                                     WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'))
        ;
        DELETE FROM INVOICE
         WHERE CUSTOMERID = ( SELECT CUSTOMERID FROM CUSTOMER
                               WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter')
        ;
        DELETE FROM CUSTOMER
         WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
         ;
         
-- 3.0 SQL Functions

-- a) System Defined Functions

---- i) Create a function that returns the current time.

        CREATE OR REPLACE FUNCTION get_current_time
        RETURN VARCHAR
        IS
            current_time VARCHAR(20);
        BEGIN
            SELECT TO_CHAR(LOCALTIMESTAMP, 'HH24:MI:SS') INTO current_time FROM dual;
            RETURN current_time;
        END;
        /
        
        DECLARE
            current_time VARCHAR(20);
        BEGIN
            current_time := get_current_time();
            DBMS_OUTPUT.PUT_LINE('current time is: ' || current_time);
        END;
        
---- ii) Create a function that returns the length of a mediatype from the mediatype table

        CREATE OR REPLACE FUNCTION get_mediatype_length(id IN NUMBER)
        RETURN NUMBER
        IS
            mediatype_length NUMBER;
        BEGIN
            SELECT LENGTH(NAME) INTO mediatype_length 
              FROM MEDIATYPE
             WHERE MEDIATYPEID = id;
            RETURN mediatype_length;
        END;
        /
        
        DECLARE
            media_type_id INT;
            the_length INT;
        BEGIN
            media_type_id := 2;
            the_length := get_mediatype_length(media_type_id);
            DBMS_OUTPUT.PUT_LINE('The length of media type id ' || media_type_id || ' is ' || the_length);
        END;
        
        select * from mediatype;
        
-- b) System Defined Aggregate Functions  

---- i) Create a function that returns the average total of all invoices

        CREATE OR REPLACE FUNCTION get_average_total_of_invoices
        RETURN NUMBER
        IS
            avg_tot NUMBER(10,2);
        BEGIN
            SELECT AVG(TOTAL) INTO avg_tot 
              FROM INVOICE;
            RETURN avg_tot;
        END;
        /
        
        DECLARE
            average_total NUMBER;
        BEGIN
            average_total := get_average_total_of_invoices();
            DBMS_OUTPUT.PUT_LINE('The average TOTAL of Invoices is '|| average_total);
        END;
        
---- ii) Create a function that returns the most expensive track 

        CREATE OR REPLACE FUNCTION get_most_expensive_track
        RETURN NUMBER
        IS
            u_price NUMBER(10,2);
        BEGIN
            SELECT MAX(UNITPRICE) INTO u_price 
              FROM TRACK;
            RETURN u_price;
        END;
        /
        
        DECLARE
            unit_price NUMBER;
        BEGIN
            unit_price := get_most_expensive_track;
            DBMS_OUTPUT.PUT_LINE('The most expensive track is: ' || unit_price);
        END;
        
-- b) User Defined Scalar Functions

---- i) Create a function that returns the average price of invoiceline items in the invoiceline table

        CREATE OR REPLACE FUNCTION get_avg_price_of_invoices
        RETURN NUMBER
        IS
            avg_price NUMBER;
        BEGIN
            SELECT ROUND(AVG(UNITPRICE), 2) INTO avg_price 
              FROM INVOICELINE;
            RETURN avg_price;
        END;
        /
        
        DECLARE
            average_price NUMBER;
        BEGIN
            average_price := get_avg_price_of_invoices;
            DBMS_OUTPUT.PUT_LINE('The average price of invoices is: ' || average_price);
        END;
  
-- c) User Defined Table Valued Functions
        
---- i) Create a function that returns all employees who are born after 1968.

-- 4.0 Stored Procedures

-- a) 4.1 Basic Stored Procedure - Create a stored procedure that selects the first and last names of all the employees

        CREATE OR REPLACE PROCEDURE get_emp_rs (p_recordset OUT SYS_REFCURSOR) 
        AS
        BEGIN 
            OPEN p_recordset FOR
                SELECT LASTNAME, FIRSTNAME
                  FROM EMPLOYEE;
        END get_emp_rs;
        /
        DECLARE
            l_cursor  SYS_REFCURSOR;
            l_fname   EMPLOYEE.FIRSTNAME%TYPE;
            l_lname   EMPLOYEE.LASTNAME%TYPE;
        BEGIN
            get_emp_rs (p_recordset => l_cursor);
        LOOP 
            FETCH l_cursor
             INTO l_fname, l_lname;
             EXIT WHEN l_cursor%NOTFOUND;
             DBMS_OUTPUT.PUT_LINE(l_fname || ' | ' || l_lname);
        END LOOP;
        CLOSE l_cursor;
        END;

-- b) Stored Procedure Input Parameters

---- i) Create a stored procedure that updates the personal information of an employee.

        CREATE OR REPLACE PROCEDURE updateEMPLOYEE(
            p_empid IN EMPLOYEE.EMPLOYEEID%TYPE,
            p_city  IN EMPLOYEE.CITY%TYPE,
            p_state IN EMPLOYEE.STATE%TYPE)
        IS
        BEGIN
            UPDATE EMPLOYEE 
               SET CITY = p_city,
                   STATE = p_state
             WHERE EMPLOYEEID = p_empid;
            COMMIT;
        END;
        /
        BEGIN
            updateEMPLOYEE(8,'Chicago', 'Illinois');
        END;
        
---- ii) Create a stored procedure that returns the managers of an employee.

        CREATE OR REPLACE PROCEDURE get_mgr_rs(
            p_empid IN NUMBER, o_mgrid OUT VARCHAR2, o_mgrLname OUT VARCHAR2, o_mgrFname OUT VARCHAR2) 
        AS
        BEGIN 
            SELECT a.REPORTSTO, b.FIRSTNAME, b.LASTNAME 
              INTO o_mgrid, o_mgrFname, o_mgrLname 
              FROM EMPLOYEE a, EMPLOYEE b
             WHERE a.EMPLOYEEID = p_empid 
               AND a.REPORTSTO = b.EMPLOYEEID ;
        END get_mgr_rs;
        /
        DECLARE
            m_id      EMPLOYEE.EMPLOYEEID%TYPE;
            m_fname   EMPLOYEE.FIRSTNAME%TYPE;
            m_lname   EMPLOYEE.LASTNAME%TYPE;
        BEGIN
            get_mgr_rs (8, m_id, m_fname, m_lname);
            DBMS_OUTPUT.PUT_LINE(m_fname || ' | ' || m_lname);
        END;
        select * from employee;
        
-- 5.0 Transactions

-- a) Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, 
--    find out how to resolve them).

        CREATE OR REPLACE PROCEDURE del_invoice (inv_id IN INVOICE.INVOICEID%TYPE) 
        AS
        BEGIN 
            DELETE FROM INVOICELINE
             WHERE INVOICEID = inv_id;
            DELETE FROM INVOICE
             WHERE INVOICEID = inv_id;
            COMMIT;
        END del_invoice;
        /
        EXECUTE DEL_INVOICE(2);
        select * from invoiceline where invoiceid = 2;
        select * from invoice where invoiceid = 2;

-- b) Create a transaction nested within a stored procedure that inserts a new record in the Customer table

        CREATE OR REPLACE PROCEDURE ins_customer (
            cust_id IN CUSTOMER.CUSTOMERID%TYPE,
            first_nm IN CUSTOMER.FIRSTNAME%TYPE,
            last_nm IN CUSTOMER.LASTNAME%TYPE,
            email IN CUSTOMER.EMAIL%TYPE) 
        AS
        BEGIN 
            INSERT INTO CUSTOMER (CUSTOMERID, LASTNAME, FIRSTNAME, EMAIL) 
                VALUES (cust_id, last_nm, first_nm, email);
            COMMIT;
        END ins_customer;
        /
        EXECUTE ins_customer(62, 'Mitch', 'Trubisky', '123@abc.com');
        select * from customer;
        
-- 6.0 Triggers

-- a) 6.1 AFTER/FOR

---- i) Create an after insert trigger on the employee table fired after a new record is inserted into the table.

        CREATE OR REPLACE TRIGGER trg_after_insert_employee
        AFTER INSERT
           ON EMPLOYEE
          FOR EACH ROW 
        DECLARE
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Trigger for After Insert of EMPLOYEE reached.');
        END;
        /
        INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME) 
        VALUES (10, 'Trubisky','Mitch');
        SELECT * FROM EMPLOYEE;
        
        
---- ii) Create an after update trigger on the album table that fires after a row is inserted in the table

        CREATE OR REPLACE TRIGGER trg_after_update_album
        AFTER UPDATE
           ON ALBUM
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Trigger for After Update of ALBUM reached.');
        END;
        /
        UPDATE ALBUM
           SET TITLE = 'Lies And Legends'
         WHERE ALBUMID = 4;
        select * from album;
        
---- iii} Create an after delete trigger on the customer table that fires after a row is deleted from the table.

        CREATE OR REPLACE TRIGGER trg_after_delete_customer
        AFTER DELETE
           ON CUSTOMER
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Trigger for After Delete of CUSTOMER reached.');
        END;
        /
        DELETE FROM CUSTOMER
         WHERE CUSTOMERID = 60;
        select * from customer;

-- 7.0 JOINS

-- a) 7.1 INNER - Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.

        SELECT CUSTOMER.LASTNAME, CUSTOMER.FIRSTNAME, INVOICE.INVOICEID
          FROM CUSTOMER
         INNER JOIN INVOICE
            ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
         ORDER BY CUSTOMER.LASTNAME, CUSTOMER.FIRSTNAME;
         
-- b) 7.2 OUTER - Create an outer join that joins the customer and invoice table, 
--                specifying the CustomerId, firstname, lastname, invoiceId, and total.

        SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME,
               INVOICE.INVOICEID, INVOICE.TOTAL
          FROM CUSTOMER
          FULL OUTER JOIN INVOICE
            ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
         ORDER BY CUSTOMER.LASTNAME, CUSTOMER.FIRSTNAME;
         
-- c) 7.3 RIGHT - Create a right join that joins album and artist specifying artist name and title.

        SELECT ARTIST.NAME, ALBUM.TITLE
          FROM ALBUM
         RIGHT JOIN ARTIST
            ON ARTIST.ARTISTID = ALBUM.ARTISTID;

-- d) 7.4 CROSS - Create a cross join that joins album and artist and sorts by artist name in ascending order.

        SELECT ARTIST.NAME, ALBUM.TITLE
          FROM ALBUM
         CROSS JOIN ARTIST
         ORDER BY ARTIST.NAME;
         
-- e) 7.5 SELF - Perform a self-join on the employee table, joining on the reportsto column.

        