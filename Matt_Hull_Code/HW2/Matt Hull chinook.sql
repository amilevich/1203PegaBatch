--Homework 2

--2.1

SELECT * FROM employee;
SELECT * FROM employee WHERE lastname = 'King';
SELECT * FROM employee WHERE firstname = 'Andrew' AND reportsto IS null;

--2.2
SELECT album FROM album ORDER BY title DESC;
SELECT firstname FROM customer ORDER BY city ASC;

--2.3 
SELECT * FROM genre;
INSERT INTO genre
VALUES(26,'Trance');


SELECT * FROM genre;
INSERT INTO genre
VALUES(27,'Folk');

SELECT * FROM EMPLOYEE;


INSERT INTO employee(employeeid,lastname,firstname)
VALUES(9,'Sagat','Bob');

INSERT INTO employee(employeeid,lastname,firstname)
VALUES(10,'Baratheon','Robert');

SELECT * FROM CUSTOMER;

INSERT INTO customer(customerid,firstname,lastname,email)
VALUES(60,'Jamie','Lannister','jlan@goldenhands.com');

INSERT INTO customer(customerid,firstname,lastname,email)
VALUES(61,'Jaqen','Hagar','noone@blackandwhite.com');


--2.4
UPDATE customer SET
firstname = 'Robert',lastname = 'Walter' 
where firstname = 'Aaron' AND lastname = 'Mitchell';

select * from artist;
UPDATE artist
SET name = 'CCR'
where name = 'Creedence Clearwater Revival';

--2.5
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

--2.6
SELECT * FROM invoice WHERE total >= 15 and 10 <= 50;
SELECT * FROM employee where hiredate >= '01-JUN-2003' and hiredate <= '01-MAR-2004';

--2.7
SELECT * FROM CUSTOMER where lastname = 'Walter';
SELECT * FROM INVOICE WHERE CUSTOMERID = 32;
--DELETE FROM INVOICE WHERE CUSTOMERID = 32;


DELETE FROM INVOICELINE WHERE INVOICEID IN
(SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID IN
(SELECT CUSTOMERID FROM CUSTOMER WHERE firstname = 'Robert' AND lastname = 'Walter'));

DELETE FROM INVOICE WHERE CUSTOMERID in
(SELECT CUSTOMERID FROM CUSTOMER WHERE firstname ='Robert' AND lastname = 'Walter');

DELETE FROM CUSTOMER WHERE firstname = 'Robert' and lastname = 'Walter';

--3.0

--3.1
CREATE OR REPLACE FUNCTION get_now
RETURN VARCHAR2
IS
datetimee VARCHAR2(40);
BEGIN
SELECT TO_CHAR
    (SYSDATE, 'MM-DD-YYYY HH24:MI:SS') "NOW"
     INTO datetimee FROM DUAL;
RETURN datetimee;
END;
/

DECLARE 
    timee VARCHAR(40);
BEGIN
    timee := get_now();
    DBMS_OUTPUT.PUT_LINE('Now is: ' || timee);
END;
/
--SELECT TO_CHAR
  --  (SYSDATE, 'MM-DD-YYYY HH24:MI:SS') "NOW"
    -- FROM DUAL;

SELECT LENGTH(NAME) FROM MEDIATYPE ;
CREATE OR REPLACE FUNCTION get_length (id IN INT)
RETURN INT
IS
len INT;
BEGIN
SELECT LENGTH(NAME) INTO len FROM MEDIATYPE where mediatypeid = id;
RETURN LEN;
END;
/

DECLARE 
    leng INT;
BEGIN
    leng := get_length(1);
    DBMS_OUTPUT.PUT_LINE('lenth is: ' || leng);
END;
/
--3.2
SELECT AVG(TOTAL) FROM INVOICE;
CREATE OR REPLACE FUNCTION get_avg
RETURN NUMBER
IS
avgtotal NUMBER(10,2);
BEGIN
SELECT AVG(total) INTO avgtotal FROM INVOICE;
RETURN avgtotal;
END;
/

DECLARE 
    avger NUMBER;
BEGIN
    avger := get_avg();
    DBMS_OUTPUT.PUT_LINE('avg is: ' || avger);
END;
/
SELECT NAME FROM TRACK WHERE UNITPRICE = (SELECT MAX(UNITPRICE) FROM TRACK) AND ROWNUM <= 1;
CREATE OR REPLACE FUNCTION get_expensive_track
RETURN VARCHAR2
IS
track_name VARCHAR2(40);
BEGIN
SELECT NAME INTO track_name FROM TRACK where UNITPRICE = (SELECT MAX(UNITPRICE) FROM TRACK) AND ROWNUM <=1;
RETURN track_name;
END;
/

DECLARE 
    t_name VARCHAR2(40);
BEGIN
    t_name := get_expensive_track();
    DBMS_OUTPUT.PUT_LINE('The track is: ' || t_name);
END;
/

--3.3
SELECT AVG(UNITPRICE) FROM INVOICELINE;
CREATE OR REPLACE FUNCTION get_avg_price 
RETURN NUMBER
IS
avg_price NUMBER;
BEGIN
SELECT AVG(UNITPRICE) INTO avg_price FROM INVOICELINE;
RETURN avg_price;
END;
/

DECLARE 
    averg NUMBER;
BEGIN
    averg := get_avg_price();
    DBMS_OUTPUT.PUT_LINE('Average price is: ' || averg);
END;
/
--3.4
SELECT FIRSTNAME, BIRTHDATE FROM EMPLOYEE WHERE BIRTHDATE > '12-DEC-1968';

CREATE OR REPLACE TYPE EMPLOYEE_OBJ_TYPE IS OBJECT
(
EMPLOYEEID   NUMBER(9),
lastname VARCHAR(30)
)
/
CREATE OR REPLACE TYPE EMPLOYEE_TABTYPE AS TABLE OF EMPLOYEE_OBJ_TYPE
/
CREATE OR REPLACE FUNCTION FN_GET_YOUNG_EMPLOYEES
RETURN EMPLOYEE_TABTYPE
AS
employees EMPLOYEE_TabType;
BEGIN

SELECT EMPLOYEE_OBJ_TYPE(A.employeeid, A.lastname)
BULK COLLECT INTO employees
FROM
(SELECT employeeid, lastname
FROM employee
WHERE BIRTHDATE > '12-DEC-1968') A;
RETURN employees;

END;
/
SELECT * FROM TABLE(FN_GET_YOUNG_EMPLOYEES);


--4.0

--4.1
CREATE OR REPLACE PROCEDURE get_employee_cursor(
	   empl_cur OUT SYS_REFCURSOR)
AS
BEGIN

  OPEN empl_cur FOR
  SELECT firstname, lastname FROM employee;
 
END get_employee_cursor;
/

DECLARE
emp_cursor SYS_REFCURSOR;
emp_firstname employee.firstname%TYPE;
emp_lastname employee.lastname%TYPE;
BEGIN
get_employee_cursor(empl_cur => emp_cursor);
LOOP
FETCH emp_cursor
into emp_firstname, emp_lastname;
EXIT WHEN emp_cursor%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('Full name is : ' || emp_firstname || ' ' || emp_lastname);
END LOOP;
CLOSE emp_cursor;
END;
/

--4.2
CREATE OR REPLACE PROCEDURE edit_employees(
	   emp_lastname in employee.lastname%TYPE)
AS
BEGIN
UPDATE employee SET firstname = 'Roberto'
WHERE lastname = emp_lastname;

END edit_employees;
/
execute edit_employees('Sagat');

CREATE OR REPLACE PROCEDURE get_employee_manager(
	   emp_id in employee.employeeid%TYPE,man_lastname out employee.lastname%TYPE)
AS
BEGIN
SELECT lastname into man_lastname from employee where employeeid = (SELECT REPORTSTO FROM employee WHERE employeeid = emp_id);

END get_employee_manager;
/


DECLARE
emp_id employee.employeeid%TYPE;
emp_lastname employee.lastname%TYPE;
BEGIN
get_employee_manager(3,man_lastname => emp_lastname);
DBMS_OUTPUT.PUT_LINE('Manager name is : ' || emp_lastname);
END;
/


CREATE OR REPLACE PROCEDURE get_customer_information(
	   cus_id in customer.customerid%TYPE,
       cus_fname out customer.firstname%TYPE,
       cus_lname out customer.lastname%TYPE,
       cus_company out customer.company%TYPE)
AS
BEGIN
SELECT firstname,lastname,company into cus_fname, cus_lname, cus_company
from customer where customerid = cus_id;

END get_customer_information;
/


DECLARE
cust_id customer.customerid%TYPE;
cust_firstname customer.firstname%TYPE;
cust_lastname customer.lastname%TYPE;
cust_company customer.company%TYPE;
BEGIN
get_customer_information(1,cus_fname => cust_firstname, cus_lname => cust_lastname,cus_company=>cust_company);
DBMS_OUTPUT.PUT_LINE('Customer Info is : ' || cust_firstname || ' ' || cust_lastname || ' : ' || cust_company);
END;
/


--5.0

--5.1
CREATE OR REPLACE PROCEDURE insert_customer(
	   inv_id in invoice.invoiceid%TYPE)
AS
BEGIN
DELETE FROM invoiceline where invoiceid = inv_id;
DELETE FROM invoice where invoiceid = inv_id;
commit;
END delete_invoice;
/
execute delete_invoice(1);
select* from invoice where invoiceid = 1;

CREATE OR REPLACE PROCEDURE insert_customer(
	   cus_id in customer.customerid%TYPE,
       cus_fname in customer.firstname%TYPE,
       cus_lname in customer.lastname%TYPE,
       cus_email in customer.email%TYPE)
AS
BEGIN
INSERT INTO customer (customerid,firstname,lastname,email) VALUES(cus_id,cus_fname,cus_lname,cus_email);
commit;
END insert_customer;
/
execute insert_customer(3242,'Cercei','Lannister','clan@thegame.win');
select* from customer where customerid = 3242;


--6.0
CREATE OR REPLACE TRIGGER  employee_trigger
  after insert on employee
BEGIN
   DBMS_OUTPUT.PUT_LINE('SOMEONE DONE GONE BEEN HIRED.');
END;
/
INSERT INTO employee(employeeid,lastname,firstname)
VALUES(9000,'Sidious','Darth');

CREATE OR REPLACE TRIGGER  album_trigger
  after insert on album
BEGIN
   DBMS_OUTPUT.PUT_LINE('ERMAGAWD NEW ALBUM.');
END;
/

CREATE OR REPLACE TRIGGER  delete_trigger
  after delete on customer
BEGIN
   DBMS_OUTPUT.PUT_LINE('In this case, the customer is wrong');
END;
/
--7.0

--7.1
SELECT FIRSTNAME, LASTNAME, INVOICEID FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.2


SELECT CUSTOMER.CUSTOMERID, FIRSTNAME, LASTNAME, INVOICEID, TOTAL
FROM CUSTOMER FULL OUTER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.3 
SELECT NAME, TITLE FROM ALBUM RIGHT JOIN ARTIST ON ARTIST.ARTISTID = ALBUM.ARTISTID;

--7.4
SELECT * FROM ALBUM CROSS JOIN ARTIST ORDER BY NAME ASC;

--7.5
SELECT a.firstname, a.lastname, b.firstname Boss FROM EMPLOYEE a FULL OUTER JOIN EMPLOYEE b ON a.REPORTSTO=b.EMPLOYEEID;