--CREATE USER bank_admin IDENTIFIED BY p4ssw0rd;
--GRANT CONNECT TO bank_admin;
--GRANT CONNECT, RESOURCE, DBA TO bank_admin;
DROP TABLE bank_account;

DROP TABLE bank_customer;

DROP TABLE bank_user;
/

DROP SEQUENCE userseq;
--DROP SEQUENCE CUSTOMERSEQ;;
--DROP SEQUENCE ACCOUNTSEQ;

CREATE SEQUENCE userseq;
--CREATE SEQUENCE CUSTOMERSEQ;
--CREATE SEQUENCE ACCOUNTSEQ;

CREATE TABLE bank_user (
    buid           INTEGER,
    userid         VARCHAR2(50) NOT NULL UNIQUE,
    firstname      VARCHAR2(50),
    lastname       VARCHAR2(50),
    username       VARCHAR2(50) NOT NULL UNIQUE,
    userpassword   VARCHAR2(50) NOT NULL,
    usertype       VARCHAR2(20) DEFAULT ( 'USER' ) NOT NULL CHECK ( usertype IN (
        'USER',
        'CUSTOMER',
        'EMPLOYEE',
        'ADMIN'
    ) ),
    PRIMARY KEY ( buid )
);

--CREATE TABLE bank_customer (
--    buid       INTEGER,
--    userid     VARCHAR2(50) NOT NULL UNIQUE,
--    approved   VARCHAR(20) DEFAULT ( 'false' ) NOT NULL CHECK ( approved IN (
--        'true',
--        'false'
--    ) ),
--    PRIMARY KEY ( buid ),
--    FOREIGN KEY ( buid )
--        REFERENCES bank_user ( buid )
--);

CREATE TABLE bank_customer (
    buid       INTEGER,
    approved   NUMBER(1) DEFAULT ( 0 ) NOT NULL CHECK ( approved IN (
        1,
        0
    ) ),
    PRIMARY KEY ( buid ),
    FOREIGN KEY ( buid )
        REFERENCES bank_user ( buid )
);

CREATE TABLE bank_account (
    buid        INTEGER,
    accountid   VARCHAR2(50),
    balance     NUMBER,
    isjoint     INTEGER NOT NULL CHECK ( isjoint IN (
        1,
        0
    ) ),
    PRIMARY KEY ( buid ),
    FOREIGN KEY ( buid )
        REFERENCES bank_customer ( buid )
);

--joins bank_user and bank_customer

SELECT
    bank_user.buid,
    bank_user.userid,
    bank_user.firstname,
    bank_user.lastname,
    bank_user.username,
    bank_user.userpassword,
    bank_user.usertype,
    bank_customer.approved
FROM
    bank_user
    FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid;
    
--joins customer and account
SELECT
    bank_customer.buid,
    bank_customer.approved,
    bank_account.accountid.
    bank_account.balance,
    bank_account.isjoint
FROM
    bank_customer
    FULL OUTER JOIN bank_account ON bank_customer.buid = bank_account.buid;

----joins bank_customer and bank_account

SELECT
    bank_user.buid,
    bank_user.userid,
    bank_user.firstname,
    bank_user.lastname,
    bank_user.username,
    bank_user.userpassword,
    bank_user.usertype,
    bank_customer.approved,
    bank_account.accountid,
    bank_account.balance,
    bank_account.isjoint
FROM
    bank_user
    FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid
    FULL OUTER JOIN bank_account ON bank_customer.buid = bank_account.buid;