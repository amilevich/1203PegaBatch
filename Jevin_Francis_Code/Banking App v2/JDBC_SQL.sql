DROP TABLE customer;
DROP TABLE newcustomer;
    
CREATE TABLE customer(
    id	INT,
    fname	VARCHAR2(20),
    lname	VARCHAR2(20),
    uname	VARCHAR2(20),
    pword	VARCHAR2(20),
    abalance	NUMBER,
    jaccount	VARCHAR2(20),
    jauname	VARCHAR2(20)
    );
    
CREATE TABLE newcustomer(
    id	INT,
    fname	VARCHAR2(20),
    lname	VARCHAR2(20),
    uname	VARCHAR2(20),
    pword	VARCHAR2(20),
    abalance	NUMBER,
    jaccount	VARCHAR2(20),
    jauname	VARCHAR2(20)
    );

--CREATE TABLE cInfo (
--    cid INT,
--    cfname VARCHAR2(50),
--    clname VARCHAR2(50),
--    PRIMARY KEY (cid)
--);
--DROP TABLE cInfo;
--
--CREATE TABLE cAcc (
--    cid INT,
--    cUser VARCHAR2(50),
--    cPass VARCHAR2(50),
--    cBal NUMBER,
--    cJAcc VARCHAR2(10),
--    cJAUser VARCHAR2(50),
--    
--    PRIMARY KEY (cid),
--    FOREIGN KEY (cid) REFERENCES cInfo
--    );
--
--DROP TABLE cAcc;

--SELECT cinfo.cid,cinfo.cfname,cinfo.clname,cacc.cuser,cacc.cpass,cacc.cbal,cacc.cjacc,cacc.cjauser FROM cInfo,cAcc WHERE cinfo.cid = cacc.cid;
--
--SELECT * FROM customer;

CREATE OR REPLACE PROCEDURE getName(
cid IN INT,
fname OUT VARCHAR2)
IS
BEGIN
   SELECT customer.fname INTO fname FROM customer WHERE customer.id = cid;
END getName;
/
--DECLARE
--id INT;
--name VARCHAR2(50);
--Begin
--    id:= 1;
--    getName(id,name);
--    DBMS_OUTPUT.PUT_LINE(name);
--end;
--/

Truncate table newcustomer;
Truncate table customer;

DROP SEQUENCE customer_seq;
DROP SEQUENCE customernew_seq;
CREATE SEQUENCE customer_seq
START WITH     1
INCREMENT BY   1;

CREATE SEQUENCE customernew_seq
START WITH     1
INCREMENT BY   1;
COMMIT;