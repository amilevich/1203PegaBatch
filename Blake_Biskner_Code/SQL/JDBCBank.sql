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
    account_balance NUMBER(38,2),
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
    customer_social INT,
    customer_status INT,
    customer_account INT,
    customer_type INT,
    PRIMARY KEY(customer_username),
    FOREIGN KEY(customer_account) REFERENCES BankAccount(account_id),
    FOREIGN KEY(customer_status) REFERENCES LookupStatus(status_id),
    FOREIGN KEY(customer_type) REFERENCES LookupType(type_id)
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

