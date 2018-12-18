------------------------------------Q#2.1.1 show all Employees
SELECT * FROM EMPLOYEE;
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

---------------------------------------------2.2.1 ORDER BY, 

SELECT * FROM ALBUM ORDER BY TITLE DESC;
SELECT FIRSTNAME, CITY FROM CUSTOMER ORDER BY CITY ASC;

-------------------------------------------------2.3 INSERT INTO

INSERT INTO Genre (GenreId, Name) VALUES (26, 'Anime');
INSERT INTO Genre (GenreId, Name) VALUES (27, 'Video Game');

INSERT INTO employee VALUES(9, 'Donald', 'Trump', 'President', 1, '14-June-46', '1-Jan-16', '1600 pennsylvania ave NW', 'DC',
'washington', 'United States', 20500, '1-111-111-1111', '1-222-222-2222', 'president@WhiteHouse.Gov');
INSERT INTO employee VALUES(10, 'Pence', 'Mike', 'VicePresident', 1, '14-June-46', '1-Jan-16', '1600 pennsylvania ave NW', 'DC',
'washington', 'United States', 20500, '1-111-111-1111', '1-222-222-2222', 'president@WhiteHouse.Gov');

INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) Values(60, 'Serious', 'Sam', 'SeriousSam3@Steam.com');
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) Values(61, 'Duke', 'Nukum', 'Forever@Steam.com');


--------------------------------------------2.4 UPDATE

UPDATE CUSTOMER
SET FIRSTNAME = 'Aaron' , LASTNAME = 'Mitchell'
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

------------------------------------------------2.5 LIKE

SELECT * FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

----------------------------------------------2.6 BETWEEN

SELECT * FROM INVOICE 
WHERE TOTAL 
BETWEEN 15 AND 50;

SELECT * FROM EMPLOYEE
WHERE hiredate
BETWEEN '1-Jun-03' AND '1-Mar-04';

------------------------------------------------------------------2.7 DELETE

UPDATE CUSTOMER
SET FIRSTNAME = 'Robert' , LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

DELETE From(
select * from invoiceline 
where invoiceid in
(select invoiceid from invoice
where customerid = (select customerid from customer
                    where FIRSTNAME = 'Robert' and LASTNAME = 'Walter')));
                    
DELETE From(
select * from invoice
where customerid = (select customerid from customer
                    where FIRSTNAME = 'Robert' and LASTNAME = 'Walter'));
    
DELETE FROM Customer WHERE FIRSTNAME = 'Robert' and LASTNAME = 'Walter';


------------------------------------------------------------7.1 INNER

select customer.firstname, invoice.invoiceid
from customer
inner join invoice on customer.customerid = invoice.invoiceid;
--OFFSET 4 ROWS FETCH NEXT 20 PERCENT ROWS ONLY;

-------------------------------------------------------------7.2 OUTER

select customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
from customer
full outer join invoice on customer.customerid = invoice.customerid;

-------------------------------------------------------------7.3 RIGHT 

select artist.name , album.title
from album
right join artist on album.artistid = artist.artistid;

---------------------------------------------------7.4 CROSS

select artist.name
from artist
cross join album
order by artist.name asc;

--------------------------------------------------7.5 get the managers

select emp.firstname, emp.lastname, emp.title, emp.employeeid, mang.reportsto
from employee emp, employee mang
where emp.employeeid = mang.reportsto;

---------------------------------------------------3.1.1 SYSTEM DEFINED FUNCTIONS, get current date

create or replace function get_date
return CHAR is

  a_format char(100) := 'dd-mon-yyyy hh24:mi:ss';

begin

  return TO_CHAR(sysdate, a_format);

end;
/

select get_date from dual; 


-----------------------------------------------3.1.2 get the media length

CREATE OR REPLACE FUNCTION media_length(x in NUMBER)
RETURN NUMBER
IS a_length NUMBER;
begin
    select length(name)
        into a_length
        from MEDIATYPE
        where MEDIATYPE.MEDIATYPEID = x;
    return a_length;
END;
/

DECLARE
    a_media NUMBER;
BEGIN
    --:= is the assignment operator in SQL unlike = in most EVERY OTHER LANGUAGE....cool
    a_media := media_length(1);
    --  || is the concat of strings
    DBMS_OUTPUT.PUT_LINE('the length is: ' || a_media);
END;
/

-------------------------------------------3.2.1 get the average of all invoices

create or replace function get_average
return NUMBER
is average NUMBER;
begin
    select avg(total) into average
    from invoice;
    return average;
    
END;
/

DECLARE
    a_average NUMBER;
BEGIN
    --:= is the assignment operator in SQL unlike = in most EVERY OTHER LANGUAGE....cool
    a_average := get_average;
    --  || is the concat of strings
    DBMS_OUTPUT.PUT_LINE('the average is: ' || a_average);
END;
/


--------------------------------------------3.2.2 return most expensive track

create or replace function get_max
return NUMBER
is a_max NUMBER;
begin
    select max(UNITPRICE) into a_max
    from track;
    return a_max;
    
END;
/

DECLARE
    a_max NUMBER;
BEGIN
    --:= is the assignment operator in SQL 
    a_max := get_max();
    --  || is the concat of strings
    DBMS_OUTPUT.PUT_LINE('the max is: ' || a_max);
END;
/

---------------------------------------------------3.3.1 average price of invoiceline items

create or replace function get_average_line
return NUMBER
is average NUMBER;
begin
    select round(avg(UNITPRICE), 2) into average
    from invoiceline;
    return average;

END;
/

DECLARE
    a_average NUMBER;
BEGIN
    a_average := get_average_line;
    DBMS_OUTPUT.PUT_LINE('the average is: ' || a_average);
END;
/

------------------------------------------------------3.4.1 return all employees who are born after 1968
---------------I couldn't solve the error
--create table my_table
--(empid number, ln varchar2(20 byte), fn varchar2(20 byte), 
--  title varchar2(30 byte), reports number,  birth date);
--
--create type my_tab_type as object
--(empid number, ln varchar2(20 byte), fn varchar2(20 byte), 
--  title varchar2(30 byte), reports number,  birth date);
--/
--
--create type my_tab_type_coll as table of my_tab_type;
--/
--
--
--create or replace function get_employees
--return my_tab_type_coll pipelined is
--begin
--  FOR arow in (select employeeid, lastname, firstname, title, reportsto, birthdate from EMPLOYEE where birthdate = 1968) loop
--    pipe row(my_tab_type(arow.empid,arow.ln,arow.fn,arow.title,arow.reports,arow.birth));
--  end loop;
--  return;
--end;
--/
--
--SELECT * FROM table(get_employees);

------------4.1.1 Basic Stored Procedure, first and last names of employees

CREATE OR REPLACE PROCEDURE names
AS
BEGIN
    for aname in (select firstname, lastname
    from EMPLOYEE
    where firstname is not null
    and lastname is not null)
    loop
        DBMS_OUTPUT.PUT_LINE(aname.firstname || ' ' || aname.lastname);
    end loop;
end;
/

BEGIN
    names;
END;
/

----4.2.1 updates employeee information

CREATE OR REPLACE PROCEDURE title_update(id in NUMBER, repo in NUMBER)
AS
BEGIN
    UPDATE employee
    set employee.reportsto = repo
    where employee.employeeid = id;
end;
/


EXECUTE title_update(10, 2);


-----4.2.2 get the managers of an employee -> but procedures don't return...
Create or replace procedure getManager(empid in number)
as 
begin
    for amanager in(
    select firstname
    from Employee
    where employeeid = (select reportsto from employee where employeeid = empid))
    loop
        DBMS_OUTPUT.PUT_LINE(amanager.firstname); 
    end loop;
end;
/

execute getManager(4);

-----4.3.1 return the name and company of a customer

Create or replace procedure getNameAndCompany(cusid in number, cocat out varchar2)
as 
begin
select (firstname || ' ' || company) into cocat from customer where customerid = cusid;
end;
/

DECLARE
    a_name_and_company varchar2(100);
BEGIN
    getNameAndCompany(10, a_name_and_company);
    DBMS_OUTPUT.PUT_LINE(a_name_and_company); 
END;
/


--------------------------------------------------------5.1.1 delete invoices using transaction

create or replace procedure deleteInvoice(inid in NUMBER)
as
begin
DELETE From(
select * from invoiceline 
where invoiceid = inid);
                    
DELETE From(
select * from invoice
where invoiceid = inid);

commit;
end;
/

execute deleteInvoice(1);

------------------------------------------5.1.2 insert a new customer using transaction in a procedure

CREATE OR REPLACE PROCEDURE new_customer
AS
BEGIN
    INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) Values(62, 'Doom', 'Guy', 'Doom@Steam.com');
    commit;
end;
/

execute new_customer;

/

-----------------6.1 employee insert trigger

CREATE OR REPLACE TRIGGER employee_trigger
  after insert on employee             
  for each row  
begin   
    DBMS_OUTPUT.PUT_LINE('employee triggered');  
end; 
/

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (30, 'Edwards', 'Nancy', 'Sales Manager', 1, TO_DATE('1958-12-8 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-5-1 00:00:00','yyyy-mm-dd hh24:mi:ss'), '825 8 Ave SW', 'Calgary', 'AB', 'Canada', 'T2P 2T3', '+1 (403) 262-3443', '+1 (403) 262-3322', 'nancy@chinookcorp.com');

-----------------6.1.2 album update trigger

CREATE OR REPLACE TRIGGER album_trigger
  after update on album             
  for each row  
begin   
    DBMS_OUTPUT.PUT_LINE('album triggered');  
end; 
/

update album
set title = 'Load2'
where title = 'Load';


----------------------------------------------6.1.3 customer delete after trigger

CREATE OR REPLACE TRIGGER customer_trigger
  after delete on customer            
  for each row  
begin   
    DBMS_OUTPUT.PUT_LINE('customer triggered');  
end; 
/

DELETE From(
select * from invoiceline 
where invoiceid in
(select invoiceid from invoice
where customerid = (select customerid from customer
                    where FIRSTNAME = 'Jack' and LASTNAME = 'Smith')));
                    
DELETE From(
select * from invoice
where customerid = (select customerid from customer
                    where FIRSTNAME = 'Jack' and LASTNAME = 'Smith'));
    
DELETE FROM Customer WHERE FIRSTNAME = 'Jack' and LASTNAME = 'Smith';

------------------------------9.0
/*
used export wizard to back up database. 
*/
