-- Dropping Schema
--DROP USER trms_db;

-- Creating Schema trms_db
--CREATE USER trms_db IDENTIFIED BY p4ssw0rd;
--GRANT CONNECT, RESOURCE, CREATE VIEW, DBA TO trms_db;

DROP TABLE employee CASCADE CONSTRAINTS ;
DROP TABLE department CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE request CASCADE CONSTRAINTS;
DROP TABLE attachment CASCADE CONSTRAINTS;
DROP TABLE gradingFormat CASCADE CONSTRAINTS;
DROP TABLE eventType CASCADE CONSTRAINTS;

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

CREATE TABLE gradingFormat(
   gFormatID INT,
   gradeFormat VARCHAR2(35),
   PRIMARY KEY (gFormatID)
);


CREATE TABLE eventType(
    eventTypeID INT NOT NULL,
    eventTypeName VARCHAR2(50),
    eventTypeReimbursement INT,
    PRIMARY KEY (eventTypeID)
);

CREATE TABLE event (
    eventID INT NOT NULL,
    eventName VARCHAR2(50),
    eventDescription VARCHAR2(250),
    eventCost NUMBER(8,2),
    eventStart DATE,
    eventEnd DATE,
    eventLocation VARCHAR2(100),
    gradingFormat INT,
    eventType INT,
    PRIMARY KEY (eventID),
    FOREIGN KEY (gradingFormat) REFERENCES gradingFormat (gFormatID),
    FOREIGN KEY (eventType) REFERENCES eventType (eventTypeID)
);



CREATE TABLE request (
    requestID INT NOT NULL,
    empID INT NOT NULL,
    eventID INT,
    requestUrgent INT,
    requestStatus VARCHAR2(25),
    requestCompleted DATE,
    justification VARCHAR2(250),
    supervisorApproval INT,
    dptHeadApproval INT,
    benCoApproval INT,
    PRIMARY KEY (requestID),
    FOREIGN KEY (eventID) REFERENCES event (eventID)
);

CREATE TABLE attachment(
    attachmentID INT NOT NULL,
    requestID INT NOT NULL,
    attachmentType VARCHAR2(20),
    attachment BLOB,
    PRIMARY KEY (attachmentID),
    FOREIGN KEY (requestID) REFERENCES request (requestID)
);





INSERT INTO department VALUES (dpt_seq.nextval,'IT',1);
--INSERT INTO department VALUES (dpt_seq.nextval,'HR',4);


INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Andrew', 'Adams', 'IT Head', 'andrew@mail.com', 'p4ssw0rd', null, '555 555 1234', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Daan', 'Peters', 'IT Manager', 'daan@mail.com', 'p4ssw0rd', 1, '555 555 1235', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'John', 'Gorden', 'IT Staff', 'john@mail.com', 'p4ssw0rd', 2, '555 555 1236', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Frank', 'Stevens', 'IT Staff', 'frank@mail.com', 'p4ssw0rd', 2, '555 555 1237', 1000);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Robert', 'Brown', 'IT Staff', 'robert@mail.com', 'p4ssw0rd', 2, '555 555 1238', 1000);


INSERT INTO gradingFormat VALUES (1,'GRADE');
INSERT INTO eventtype VALUES (1, 'University Course', 80);
INSERT INTO event VALUES (1,'MAT1234','Math for Dummies', 250.00, '12-DEC-18', '30-JAN-19', 'USF', 1, 1);
INSERT INTO request VALUES (1, 5, 1, 0, 'pending','18-DEC-1992', 'For Certification', 0, 0, 0);

INSERT INTO attachment VALUES ( 1, 1, 'Grade', null);
--
--SELECT * FROM employee, department WHERE employee.dptid = department.dptid;
--SELECT firstname, lastname FROM employee,department WHERE dpthead = empid;
DROP VIEW empInfo;
CREATE VIEW empInfo AS SELECT empid, firstname, lastname, jobtitle, dptname, email, password, reportto, dptHead, phonenumber, refund FROM employee,department;

-- View to see employee info [ combines both employee and department table ]
SELECT * FROM empInfo;
SELECT * FROM empInfo WHERE email = 'andrew@mail.com';

SELECT * FROM request;
SELECT * FROM event;

SELECT * FROM request,event WHERE request.eventid= event.eventid;

SELECT attachment.attachment FROM attachment,request WHERE attachment.requestid=request.requestid;
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
    

