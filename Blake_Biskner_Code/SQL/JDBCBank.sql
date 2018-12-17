-------------------- Bank Application Database --------------------

---------- DDL ----------
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
    account_type INT,
    account_balance NUMBER(38,2),
    PRIMARY KEY(account_id),
    FOREIGN KEY(account_type) REFERENCES LookupType(type_id)
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
    PRIMARY KEY(customer_username),
    FOREIGN KEY(customer_account) REFERENCES BankAccount(account_id),
    FOREIGN KEY(customer_status) REFERENCES LookupStatus(status_id)
);
COMMIT;

---------- DML ----------
----- Populate Lookup Tables -----

INSERT INTO LookUpStatus (status_id, status_definition) VALUES(1,'Under Review');


