-- Dropping Schema
--DROP USER trms_db;

-- Creating Schema trms_db
--CREATE USER trms_db IDENTIFIED BY p4ssw0rd;
--GRANT CONNECT, RESOURCE, CREATE VIEW, DBA TO trms_db;

DROP TABLE employee;
DROP TABLE department;

DROP SEQUENCE dpt_seq;
DROP SEQUENCE emp_seq;


CREATE SEQUENCE emp_seq;
CREATE SEQUENCE dpt_seq;


CREATE TABLE department (
    dptID INT NOT NULL,
    dptName VARCHAR(50),
    dptHead INT,
    PRIMARY KEY (dptID)
    );


CREATE TABLE employee (
    empID INT NOT NULL,
    dptID INT NOT NULL,
    firstName  VARCHAR2(50),
    lastName VARCHAR2(50),
    jobTitle VARCHAR2(50),
    email VARCHAR2(50) NOT NULL UNIQUE,
    password VARCHAR2(50)NOT NULL,
    reportto INT,
    phoneNumber VARCHAR2(50),
    reFund NUMBER(8,2) DEFAULT 1000,
    PRIMARY KEY (empID),
    FOREIGN KEY (dptID) REFERENCES department (dptID)
    );




TRUNCATE TABLE employee;
TRUNCATE TABLE department;

INSERT INTO department VALUES (dpt_seq.nextval,'IT',1);
--INSERT INTO department VALUES (dpt_seq.nextval,'HR',4);


INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Andrew', 'Adams', 'IT Head', 'andrew@mail.com', 'p4ssw0rd', null, '555 555 1234', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Daan', 'Peters', 'IT Manager', 'daan@mail.com', 'p4ssw0rd', 1, '555 555 1235', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'John', 'Gorden', 'IT Staff', 'john@mail.com', 'p4ssw0rd', 2, '555 555 1236', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Frank', 'Stevens', 'IT Staff', 'frank@mail.com', 'p4ssw0rd', 2, '555 555 1237', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Robert', 'Brown', 'IT Staff', 'robert@mail.com', 'p4ssw0rd', 2, '555 555 1238', 1000);

COMMIT;
--
--SELECT * FROM employee, department WHERE employee.dptid = department.dptid;
--SELECT firstname, lastname FROM employee,department WHERE dpthead = empid;

SELECT empid, firstname, lastname, jobtitle, dptname, email, password, reportto, dptHead, phonenumber, refund FROM employee,department;


SELECT * FROM employee WHERE email='andrew@mail.com';


--joins employee and department
SELECT
employee.empid,
employee.dptid,
employee.firstname,
employee.lastname,
employee.jobtitle,
employee.email,
employee.password,
employee.reportto,
employee.phonenumber,
employee.refund,
department.dptname
FROM employee
INNER JOIN department ON employee.dptid = department.dptid WHERE email = 'andrew@mail.com';
    

