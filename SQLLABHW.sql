-- 2.1
SELECT * FROM Employee;
SELECT * FROM Employee where LASTNAME = 'King';
SELECT * FROM Employee where FIRSTNAME = 'Andrew' and REPORTSTO IS NULL;

-- 2.2
SELECT * FROM ALBUM
ORDER BY TITLE DESC;
SELECT * FROM CUSTOMER
ORDER BY CITY;

-- 2.3
INSERT INTO GENRE VALUES(28, 'Genre1');
INSERT INTO GENRE VALUES(65, 'Genre2');
INSERT INTO EMPLOYEE VALUES(44, 'Gomez', 'Bessie', 'Ms.', NULL , TO_DATE('1963-12-30','YYYY-MM-DD'), TO_DATE('1988-08-29','YYYY-MM-DD'), '932 Prince Court', 
'Attleboro', 'MA', 'USA', '02703', '202-555-0148', '202-555-0102', 'oraquce-6429@yopmail.com');
INSERT INTO EMPLOYEE VALUES(47, 'Hall', 'Joe', 'Mr.', 44, TO_DATE('1970-07-07','YYYY-MM-DD'), TO_DATE('1986-09-17','YYYY-MM-DD'), '9903 Crescent St.', 
'Nampa', 'ID', 'USA', '83651', '202-555-0158', '202-555-0179', 'hanoddabiff-2035@yopmail.com');
INSERT INTO CUSTOMER VALUES(67, 'Huff', 'Carrie', 'Volsonline', '8852A South Rockaway Lane', 'Perkasie', 'PA', 'USA', '18944', '202-555-0171', 
'202-555-0135', 'muttuddahulle-0736@yopmail.com', NULL);
INSERT INTO CUSTOMER VALUES(96, 'Rose', 'Jessie', 'Fintexon', '7664 East Magnolia Lane', 'Ridgewood', 'NJ', 'USA', '07450', '202-555-0108', 
'429-682-0038', 'effopylle-9224@yopmail.com', NULL);


-- 2.4
UPDATE Customer
SET LASTNAME = 'Walter', FIRSTNAME = 'Robert'
WHERE LASTNAME = 'Mitchell' AND FIRSTNAME = 'Aaron';

UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedance Clearwater Revival';

-- 2.5
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

-- 2.6
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

-- 2.7
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'WALTER';

-- 3.1
Create or replace Function get_time
    return varchar2
    Is
        l_time    varchar2(10) := to_char( sysdate, 'HH24:MI:SS' );
        l_message Varchar2(30) := 'The Current Time is : ' || l_time;
    Begin
        dbms_output.put_line( l_message );
        return l_message;
End;
/

Create or replace Function get_mediatype_length (mediaid number) 
    return number
    Is
        length_mediatype number;
        mediatype number;
    Begin
        SELECT LENGTH(NAME)
        INTO mediatype
        FROM MEDIATYPE
        WHERE mediatypeid = mediaid;
    RETURN length_mediatype;
End;
/

-- 3.2
Create or replace Function average_total_invoices
    return number
    Is
        average_invoices number;
    Begin
        SELECT AVG(TOTAL)
        INTO average_invoices
        FROM INVOICE;
    RETURN average_invoices;
End;
/

Create or replace Function most_expensive_track 
    return number
    Is
        length_mediatype number;
        mediatype number;
    Begin
        SELECT TRACK.TRACKID, TRACK.NAME, TRACK.UNITPRICE
        INTO mediatype
        FROM TRACK
        JOIN (SELECT TRACKID, MAX(UNITPRICE) as MaxPrice
            From TRACK
            GROUP BY TRACKID
            )
            ON TRACK.UNITPRICE = TRAK.UNITPRICE
        ORDER BY track.albumid;
    RETURN length_mediatype;
End;
/

-- 3.3
Create or replace Function avg_price_invoiceline
    return number
    Is
        avg_price number;
    Begin
        SELECT AVG(UNITPRICE)
        INTO avg_price
        FROM INVOICELINE;
    RETURN avg_price;
End;
/

-- 3.4
CREATE OR REPLACE FUNCTION employees_born_after_1968
RETURN SYS_REFCURSOR
IS 
    employees_born_after_68 SYS_REFCURSOR;
BEGIN
    OPEN employees_born_after_68 FOR
    SELECT FIRSTNAME, LASTNAME 
    FROM EMPLOYEE 
    WHERE BIRTHDATE > TO_DATE('1968-1-1 00:00:00','yyyy-mm-dd hh24:mi:ss');
    RETURN employees_born_after_68;
END;
/

-- 4.1
CREATE PROCEDURE first_and_last_names AS
    first_last SYS_REFCURSOR;
    BEGIN
        OPEN first_last FOR
        SELECT FIRSTNAME, LASTNAME
        FROM EMPLOYEE;
        RETURN first_last;
END;
/

-- 4.2
CREATE OR REPLACE PROCEDURE update_employee_info(EMPLOYEEID IN NUMBER, CURSOR_ OUT SYS_REFCURSOR_)
AS
BEGIN
    UPDATE EMPLOYEE
    SET ADDRESS = EMPLOYEE_ADD
    WHERE EMPLOYEEID = EMPLOYEE_ID;
    END;
/

CREATE OR REPLACE PROCEDURE get_manager_of_employee(EMP_ID IN NUMBER, CURSOR_ OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN CURSOR_ FOR
    SELECT REPORTSTO
    FROM EMPLOYEE
    WHERE EMPLOYEEID = E_ID;
END;
/

-- 4.3
CREATE OR REPLACE PROCEDURE get_name_and_company_of_customer(cust_first_name IN VARCHAR2, company_name OUT VARCHAR2, customer_first OUT VARCHAR2)
IS
BEGIN
    SELECT FIRSTNAME, COMPANY INTO customer_first, company_name FROM CUSTOMER WHERE FIRSTNAME = customer_first_name;
END get_name_and_company_of_customer;
/

-- 5.0
DELETE FROM invoice
WHERE invoiceid = 1;

INSERT INTO CUSTOMER VALUES(87, "Ihab", "Baghdadi", "Revature", "AddressExample", "Tampa", "FL", "USA", "38727", "8283282446", "0201060224", "ihabbaghdadi@live.com", 78);

-- 6.1
CREATE [OR REPLACE] TRIGGER after_insert_trigger
AFTER INSERT OR UPDATE ON employee
FOR EACH ROW
BEGIN
    UPDATE employee
    SET hiredate = TO_DATE('1995-08-12', 'YYYY-MM-DD')
        WHERE employeeid = 2;
END;
/

CREATE [OR REPLACE] TRIGGER after_insert_trigger_album
AFTER INSERT OR UPDATE ON ALBUM
FOR EACH ROW
BEGIN
    UPDATE ALBUM
    SET TITLE = "The Beatles"
        WHERE ALBUMID = 3;
END;
/

CREATE [OR REPLACE] TRIGGER after_insert_trigger_customer
AFTER INSERT OR UPDATE ON CUSTOMER
FOR EACH ROW
BEGIN
    UPDATE CUSTOMER
    SET COMPANY = "Revature';
        WHERE CUSTOMERID = 3;
END;
/

-- 7.1
SELECT INVOICEID FROM INVOICE;
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID
FROM CUSTOMER
INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- 7.2
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER
JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.INVOICEID;

-- 7.3
SELECT ARTIST.NAME, ARTIST.TITLE
FROM ARTIST
RIGHT JOIN ALBUM ON ARTIST.ARTISTID = ALBUM.ARTISTID;

-- 7.4
SELECT * FROM ARTIST CROSS JOIN ALBUM
ORDER BY ARTIST.NAME ASC;

-- 7.5

SELECT E1.FISTNAME, E2.REPORTSTO
FROM EMPLOYEE E1 JOIN EMPLOYEE E2
ON (E1.EMPLOYEE.ID = E2.REPORTSTO);

