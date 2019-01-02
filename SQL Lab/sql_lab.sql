--Author: Steven Jean-Paul
--SQL lab

--2.1 SELECT
/*Task*/ SELECT * FROM Employee;
/*Task*/ SELECT * FROM Employee WHERE lastname = 'King';
/*Task*/ SELECT * FROM Employee WHERE firstname = 'Andrew' AND reportsto IS NULL;

--2.2 ORDER BY 
/*Task*/ SELECT * FROM Album ORDER BY title DESC;
/*Task*/ SELECT firstname FROM customer ORDER BY City ASC;

--2.3 INSERT INTO
/*Task*/ INSERT INTO Genre VALUES(26, 'Downtempo');
/*Task*/ INSERT INTO Genre VALUES(27, 'Triphop');

/*Task*/ INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
VALUES (9, 'Tidus', 'Fullwave', 'Battle Staff', 2, TO_DATE('1945-2-6 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2014-8-5 00:00:00','yyyy-mm-dd hh24:mi:ss'), '64 1 ST E', 'Blitzballe', 'DC', 'Antartica', 'B8T 1Y6', '+1 (223) 457-4851', '+1 (223) 661-8073', 'tidusblitz@finalfantasyt.net');

/*Task*/ INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) 
VALUES (60, 'Jack', 'Sparrow', '20 Pirate Bay Lane', 'Aruba', 'FL', 'SA', '82461', '+1 (441) 323-7584', 'jsparr@xmail.com', 4);

--2.4 UPDATE
/*Task*/ UPDATE CUSTOMER SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';
/*Task*/UPDATE ARTIST SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--2.5 LIKE
/*Task*/ SELECT * FROM Invoice WHERE BillingAddress LIKE 'T%';

--2.6 BETWEEN
/*Task*/ SELECT * FROM Invoice WHERE Total BETWEEN 15 AND 50;
/*Task*/ SELECT * FROM Employee WHERE HireDate BETWEEN (TO_DATE('2003-6-1 00:00:00','yyyy-mm-dd hh24:mi:ss')) AND
(TO_DATE('2004-3-4 00:00:00','yyyy-mm-dd hh24:mi:ss'));

--2.7 DELETE 
--SELECT CustomerID FROM Customer WHERE FirstName = 'Robert' AND LastName = 'Walter';
--SELECT InvoiceID FROM Invoice WHERE customerid = 32;
--SELECT * FROM Invoiceline WHERE invoiceid = 245;
/*Task*/ DELETE FROM Invoiceline WHERE
    Invoiceid IN 
        (SELECT Invoiceid FROM Invoice WHERE CustomerID = 
            (SELECT CustomerId FROM Customer WHERE firstname = 'Robert' AND lastname = 'Walter'));

--3.1 SYSTEM DEFINED FUNCTIONS
/*Task*/ CREATE OR REPLACE FUNCTION getthyme
RETURN VARCHAR2
IS 
    thyme varchar2(200);
    BEGIN
        SELECT TO_CHAR (CURRENT_TIMESTAMP, 'HH12:MI:SS') INTO thyme FROM dual;
        RETURN thyme;
    END; --this compiles the fx for later use
/
DECLARE
t varchar2(200);
BEGIN
    t := getthyme();
    DBMS_OUTPUT.PUT_LINE('Time is: ' || t);
END;
/

/*Task*/ CREATE OR REPLACE FUNCTION getmlen
RETURN VARCHAR2
IS 
    m VARCHAR2(100);
    BEGIN
        SELECT milliseconds INTO m FROM track WHERE trackid < 2;
        RETURN m;
    END; --this compiles the fx for later use
/
DECLARE
m VARCHAR2(100);
BEGIN
    m := getmlen();
    DBMS_OUTPUT.PUT_LINE('Media length is: ' || (m/1000) || ' milliseconds.');
END;
/

--3.2 SYSTEM DEFINED AGGREGATE FUNCTIONS
/*Task*/ CREATE OR REPLACE FUNCTION getAvgInvoices
RETURN VARCHAR2
IS 
    avginv INTEGER;
    BEGIN
        SELECT  AVG(Invoiceid) INTO avginv FROM Invoice;
        RETURN avginv;
    END; --this compiles the fx for later use
/
DECLARE
a INTEGER;
BEGIN
    a := getAvgInvoices();
    DBMS_OUTPUT.PUT_LINE('Total invoices are: ' || a);
END;

/*Task*/ CREATE OR REPLACE FUNCTION getMostExpsensiveTrck
RETURN VARCHAR2
IS 
    metrack VARCHAR2(100);
  
    BEGIN
        SELECT  MAX(unitprice), name INTO metrack FROM track;
        RETURN metrack;
    END; --this compiles the fx for later use
/
DECLARE
mextrck INTEGER;
BEGIN
    mextrck := getMostExpsensiveTrck();
    DBMS_OUTPUT.PUT_LINE('The most expensive track is: ' || mextrck);
END;

--3.3
--CREATE FUNCTION avgPriceInvlineItems()
/*Task*/
CREATE OR REPLACE FUNCTION avgPriceInvlineItems(@InvoiceLineId INT)
IS
RETURN INT
	BEGIN(
		DECLARE
		--@InvoiceLineId := SELECT AVG(unitprice) FROM InvoiceLine
		@InvoiceLineId := SELECT AVG(unitprice) FROM InvoiceLine
		WHERE InvoiceLineId = @InvoiceLineId
		GROUP BY InvoiceLineId
		RETURN InvoiceLineId
	)END

SELECT avgPriceInvlineItems(InvoiceLineId) AS avgPrice

--practice functions
--CREATE OR REPLACE FUNCTION practiceFx
--RETURN NUMBER
--IS 
    --BEGIN
        --RETURN 1;
    --END; 
/   
DECLARE
--t varchar2(200);
BEGIN
    --t := getthyme();
    DBMS_OUTPUT.PUT_LINE('Time is: ' || practiceFx());
END;
/
SELECT * FROM Invoice;
--practice function



/*3.4*/
--user defined table value functions  - all employees born > 1968
/*Task*/
CREATE OR REPLACE FUNCTION getEmpDob()
RETURN INT
AS
	BEGIN(
		dob int;
		SELECT BirthDate INTO dob FROM Employee
		WHERE BirthDate > 1968; 
		RETURN dob
	)END;
/
DECLARE
empDob INTEGER;
BEGIN
    empDob := getEmpDob();
    DBMS_OUTPUT.PUT_LINE('Output: ' || empDob);
END;


--4.1 BASIC STORED PROCEDURE
/*Task*/ 
CREATE OR REPLACE PROCEDURE basicSP(selector OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN selector FOR 
            SELECT firstname, lastname FROM Employee;
    END basicSP;
/
DECLARE
    miniCurse SYS_REFCURSOR;
    fn EMPLOYEE.firstname%TYPE;
    ln EMPLOYEE.lastname%TYPE;
BEGIN
    basicSP(selector => miniCurse);
    LOOP
        FETCH miniCurse INTO fn, ln;
        EXIT WHEN miniCurse%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Output is: ' || fn || '---' || ln);
    END LOOP;
    CLOSE miniCurse;
END;
/

--4.2 STORED PROCEDURE INPUT PARAMETERS
/*Task*/
CREATE OR REPLACE PROCEDURE updateEmpName (@EmployeeId NUMBER, @LastName VARCHAR2(20)) AS 
BEGIN
UPDATE EMPLOYEE SET LastName = @LastName WHERE EmployeeId = @EmployeeId
END updateEmpName;
/
CREATE OR REPLACE PROCEDURE updateEmployees (@EmployeeId NUMBER, @LastName VARCHAR2(20), @FirstName VARCHAR2(20), @Title VARCHAR2(30), @ReportsTo NUMBER, @Birthdate DATE, @Hiredate DATE, @Address VARCHAR2(70), @City VARCHAR2(40), @State VARCHAR2(40), @Country VARCHAR2(40), @Postalcode VARCHAR2(10), @Phone VARCHAR2(24), @Fax VARCHAR2(24), @Email VARCHAR2(60))
AS
UPDATE EMPLOYEE
SET LastName = @LastName, FirstName = @FirstName, Title = @Title, ReportsTo = @ReportsTo, Birthdate = @Birthdate, Hiredate = @Hiredate, Address = @Address, City = @City, State = @State, Country = @Country, Postalcode = @Postalcode, Phone = @Phone, Fax = @Fax, Email = @Email
WHERE EmployeeId = @EmployeeId
GO;
--- In need of recoding.
CREATE OR REPLACE PROCEDURE getnum (empid IN NUMBER) 
AS
BEGIN
    empn IN NUMBER;
    SELECT EmployeeId INTO empn FROM EMPLOYEE WHERE EmployeeId = empid;
	dbms_output.put_line(empn);
END;
/
EXEC getnum(9);
/

/*Task*/ 
CREATE OR REPLACE PROCEDURE getMngr(selector OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN selector FOR 
            SELECT reportsto FROM EMPLOYEE;
    END getMngr;
/
DECLARE
    cusC SYS_REFCURSOR;
    mn EMPLOYEE.reportsto%TYPE;
BEGIN
    getMngr(selector => cusC);
    LOOP
        FETCH cusC INTO mn;
        EXIT WHEN cusC%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Manager is: ' || mn || '.');
    END LOOP;
    CLOSE cusC;
END;
/
--4.3 Create stored procedure that returns the name & co of a customer
/*Task*/ 
CREATE OR REPLACE PROCEDURE custoInfo(selector OUT SYS_REFCURSOR)
    AS
    BEGIN
        OPEN selector FOR 
            SELECT firstname, company FROM CUSTOMER;
    END custoInfo;
/
DECLARE
    cusC SYS_REFCURSOR;
    fn CUSTOMER.firstname%TYPE;
    co CUSTOMER.company%TYPE;
BEGIN
    custoInfo(selector => cusC);
    LOOP
        FETCH cusC INTO fn, co;
        EXIT WHEN cusC%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Output is: ' || fn || ' is with company ' || co);
    END LOOP;
    CLOSE cusC;
END;
/

5.0
6.0
6.1
--7.1 INNER 
SELECT firstname, lastname, invoiceId FROM CUSTOMER 
INNER JOIN INVOICE ON CUSTOMER.customerid = INVOICE.customerid;

--7.2 OUTER
/*Task*/ SELECT CUSTOMER.customerid, CUSTOMER.firstname, CUSTOMER.lastname, INVOICE.invoiceid, total FROM CUSTOMER 
FULL OUTER JOIN INVOICE ON CUSTOMER.customerid = INVOICE.customerid;

--7.3 RIGHT
/*Task*/ SELECT ALBUM.title, ALBUM.name, FROM ALBUM 
RIGHT JOIN ARTIST ON ALBUM.artistid = ARTIST.artistid;

--7.4 CROSS
/*Task*/ SELECT name, title FROM ALBUM
JOIN ARTIST ON ALBUM.artistid = ARTIST.artistid;

--7.5 SELF
/*Task*/ SELECT A.EmployeeId AS Employee, B.Reportsto AS Manager
FROM Employee A, Employee B
WHERE A.Reportsto <> B.EmployeeId
ORDER BY A.EmployeeId; 

9.0 ADMINISTRATION