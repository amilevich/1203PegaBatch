-------------------- Bank Application Database --------------------

---------- DDL ----------
----- Create Sequences -----
CREATE SEQUENCE EMP_SEQ;
CREATE SEQUENCE ACCT_SEQ;

----- Create Lookup Tables -----
CREATE TABLE LookupLevel(
    level_id INT,
    level_definition VARCHAR2(100),
    PRIMARY KEY(level_id)
);

CREATE TABLE LookupType(
    type_id INT,
    type_definition VARCHAR2(100),
    PRIMARY KEY(type_id)
);

CREATE TABLE LookupStatus(
    status_id INT,
    status_definition VARCHAR2(100),
    PRIMARY KEY(status_id)
);

----- Customer Account and Employee Table Creation -----
CREATE TABLE BankAccount(
    account_id INT,
    account_balance NUMBER(38,2) DEFAULT 0.0,
    PRIMARY KEY(account_id)
);

CREATE TABLE BankEmployee(
    employee_id INT,
    employee_level INT,
    PRIMARY KEY(employee_id),
    FOREIGN KEY(employee_level) REFERENCES LookupType(type_id)
);

CREATE TABLE BankCustomer(
    customer_username VARCHAR2(100),
    customer_password VARCHAR2(100),
    customer_first VARCHAR2(100),
    customer_last VARCHAR2(100),
    customer_age INT,
    customer_social VARCHAR2(10),
    customer_type INT,
    customer_status INT,
    customer_account INT,
    PRIMARY KEY(customer_username),
    FOREIGN KEY (customer_account) REFERENCES BankAccount(account_id),
    FOREIGN KEY(customer_status) REFERENCES LookupStatus(status_id),
    FOREIGN KEY(customer_type) REFERENCES LookupType(type_id)
);
COMMIT;

----- Create Junction Table of Usernames and Account Numbers -----
CREATE TABLE JunctionUsernameAccount(
    customer_username VARCHAR2(100),
    account_id INT,
    PRIMARY KEY(customer_username, account_id),
    FOREIGN KEY(customer_username) REFERENCES BankCustomer(customer_username),
    FOREIGN KEY(account_id) REFERENCES BankAccount(account_id)
);
COMMIT;

---------- DML ----------
----- Populate Lookup Tables -----
-- LookupStatus
INSERT INTO LookUpStatus (status_id, status_definition) VALUES(1,'Under Review');
INSERT INTO LookupStatus (status_id, status_definition) VALUES(2,'Approved');
INSERT INTO LookupStatus (status_id, status_definition) VALUES(3,'Denied');

-- LookupLevel
INSERT INTO LookupLevel (level_id, level_definition) VALUES(1,'Admin');
INSERT INTO LookupLevel (level_id, level_definition) VALUES(2,'Employee');

-- LookupType
INSERT INTO LookupType (type_id, type_definition) VALUES(1,'Personal');
INSERT INTO LookupType (type_id, type_definition) VALUES(2,'Joint');
COMMIT;

----- Insert Rows into Employee -----
-- Since we do not need to hire employees I will just manually populate this table
INSERT INTO BankEmployee(employee_id,employee_level) VALUES (EMP_SEQ.NEXTVAL,1);
INSERT INTO BankEmployee(employee_id,employee_level) VALUES (EMP_SEQ.NEXTVAL,2);
COMMIT;

---------- Create Stored Procedures ----------
----- Insert New Customer -----
CREATE OR REPLACE PROCEDURE Insert_Customer(
    new_username IN VARCHAR2,
    new_password IN VARCHAR2,
    new_first IN VARCHAR2,
    new_last IN VARCHAR2,
    new_age IN INT,
    new_social IN VARCHAR2,
    new_type IN INT,
    new_status IN INT)
AS
    account_num INT;
BEGIN
-- As I will use the account number twice I must only increment once
-- Thus I cannot use ACCT_SEQ.NEXTVAL bot times
    account_num:=ACCT_SEQ.NEXTVAL;
-- Add new account
    INSERT INTO BankAccount(account_id)
    VALUES(account_num);
-- Add new customer
    INSERT INTO BankCustomer
    VALUES(
        new_username,
        new_password,
        new_first,
        new_last,
        new_age,
        new_social,
        new_type,
        new_status,
        account_num);
-- Add new row to junction table
    INSERT INTO JunctionUsernameAccount
    VALUES(
        new_username,
        account_num);

    COMMIT;
END;
/

---------- Insert New Joint Holder ----------
CREATE OR REPLACE PROCEDURE Insert_Jtcustomer(
    jt_username IN VARCHAR2,
    jt_password IN VARCHAR2,
    jt_first IN VARCHAR2,
    jt_last IN VARCHAR2,
    jt_age IN INT,
    jt_social IN VARCHAR2,
    jt_type IN INT,
    jt_status IN INT,
    jt_account IN INT)
AS
BEGIN
-- Add new customer
    INSERT INTO BankCustomer VALUES(
        jt_username,
        jt_password,
        jt_first,
        jt_last,
        jt_age,
        jt_social,
        jt_type,
        jt_status,
        jt_account
    );
-- Add new row to junction table
    INSERT INTO JunctionUsernameAccount VALUES(
        jt_username,
        jt_account);

    COMMIT;
END;
/
