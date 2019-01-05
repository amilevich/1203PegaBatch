-- Dropping Schema
--DROP USER trms_db;

-- Creating Schema trms_db
--CREATE USER trms_db IDENTIFIED BY p4ssw0rd;
--GRANT CONNECT, RESOURCE, CREATE VIEW, DBA TO trms_db;

DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE department CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE request CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;
DROP TABLE gradingformat CASCADE CONSTRAINTS;
DROP TABLE eventtype CASCADE CONSTRAINTS;
DROP TABLE eventlocation CASCADE CONSTRAINTS;
DROP TABLE moreinfo CASCADE CONSTRAINTS;
DROP TABLE attachment CASCADE CONSTRAINTS;


--Sequence

DROP SEQUENCE dpt_seq;
DROP SEQUENCE emp_seq;
DROP SEQUENCE event_seq;
DROP SEQUENCE request_seq;
DROP SEQUENCE gradeformat_seq;
DROP SEQUENCE grade_seq;
DROP SEQUENCE eventtype_seq;
DROP SEQUENCE eventlocation_seq;
DROP SEQUENCE moreinfo_seq;
DROP SEQUENCE attachment_seq;

CREATE SEQUENCE emp_seq;
CREATE SEQUENCE dpt_seq;
CREATE SEQUENCE event_seq;
CREATE SEQUENCE request_seq;
CREATE SEQUENCE gradeformat_seq;
CREATE SEQUENCE eventtype_seq;
CREATE SEQUENCE eventlocation_seq;
CREATE SEQUENCE grade_seq;
CREATE SEQUENCE moreinfo_seq;
CREATE SEQUENCE attachment_seq;
--End Sequence

--Tables
--Department Table
CREATE TABLE department (
    dptid     INT NOT NULL,
    dptname   VARCHAR(50),
    dpthead   INT,
    PRIMARY KEY ( dptid )
);

--Employee Table

CREATE TABLE employee (
    empid           INT NOT NULL,
    dptid           INT NOT NULL,
    firstname       VARCHAR2(50),
    lastname        VARCHAR2(50),
    jobtitle        VARCHAR2(50),
    email           VARCHAR2(50) NOT NULL UNIQUE,
    password        VARCHAR2(50) NOT NULL,
    reportto        INT,
    phonenumber     VARCHAR2(50),
    refund          NUMBER(8, 2) DEFAULT 1000,
    refundpending   NUMBER(8, 2) DEFAULT 0,
    PRIMARY KEY ( empid ),
    FOREIGN KEY ( dptid )
        REFERENCES department ( dptid )
);

--Grading Format Table

CREATE TABLE gradingformat (
    gformatid   INT,
    gradetype   VARCHAR2(35),
    PRIMARY KEY ( gformatid )
);

--Event Type Table

CREATE TABLE eventtype (
    eventtypeid             INT NOT NULL,
    eventtypename           VARCHAR2(50),
    reimbursementcoverage   INT,
    PRIMARY KEY ( eventtypeid )
);
--Event Location

CREATE TABLE eventlocation (
    locationid      INT,
    streetaddress   VARCHAR2(50),
    city            VARCHAR2(50),
    state           VARCHAR2(50),
    zipcode         VARCHAR2(25),
    country         VARCHAR2(50),
    PRIMARY KEY ( locationid )
);

--Event Table

CREATE TABLE event (
    eventid            INT NOT NULL,
    eventtype          INT,
    gradingformat      INT,
    eventlocation      INT,
    eventdescription   VARCHAR2(250),
    eventcost          NUMBER(8, 2),
    eventstart         DATE,
    eventend           DATE,
    PRIMARY KEY ( eventid ),
    FOREIGN KEY ( gradingformat )
        REFERENCES gradingformat ( gformatid ),
    FOREIGN KEY ( eventtype )
        REFERENCES eventtype ( eventtypeid ),
    FOREIGN KEY ( eventlocation )
        REFERENCES eventlocation ( locationid )
);

--grade Table

CREATE TABLE grade (
    gradeid              INT,
    passinggrade         VARCHAR2(50),
    finalgrade           VARCHAR2(25),
    presentation         INT,
    presentationattach   BLOB,
    PRIMARY KEY ( gradeid )
);



--Request Table

CREATE TABLE request (
    requestid       INT NOT NULL,
    empid           INT NOT NULL,
    eventid         INT,
    approvalid      INT,
    gradeid         INT,
    datecompleted   DATE,
    requeststatus   VARCHAR2(25),
    moreinfo        INT,
    justification   VARCHAR2(250),
     directmanagerapproved    INT,
    departmentheadapproved   INT,
    bencoapproved            INT,
    requestdenied   INT,
    deniedreason VARCHAR2(250),
    preapproved              INT,--Id of from who
    approvalattachment       BLOB,
    projectedaward      NUMBER(8, 2),
    awardchangedbenco   NUMBER(8, 2) DEFAULT 0,
    exceedavilable      INT,
    PRIMARY KEY ( requestid ),
    FOREIGN KEY ( eventid )
        REFERENCES event ( eventid ),
    FOREIGN KEY ( empid )
        REFERENCES employee ( empid ),
    FOREIGN KEY (gradeid)
        REFERENCES grade (gradeid)
);

--more info table

CREATE TABLE moreinfo (
    moreinfoid     INT,
    requestid      INT,
    moreinfowho    INT,
    moreinfofrom   INT,
    moreinfoq      VARCHAR2(250),
    moreinfoa      VARCHAR2(250),
    PRIMARY KEY ( moreinfoid ),
    FOREIGN KEY ( requestid )
        REFERENCES request ( requestid )
);

--attachmenttable

CREATE TABLE attachment (
    attachmentid   INT,
    requestid      INT,
    attachment     BLOB,
    PRIMARY KEY ( attachmentid ),
    FOREIGN KEY ( requestid )
        REFERENCES request ( requestid )
);
--End Creating Tables

--Poulating Department Table
INSERT INTO department VALUES (dpt_seq.nextval,'IT',1);
INSERT INTO department VALUES (dpt_seq.nextval,'HR',2);
INSERT INTO department VALUES (dpt_seq.nextval,'Benafits', 3);
--End Populating Department

--Populating Employee Table
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Andrew', 'Adams', 'IT Head', 'andrew@mail.com', 'p4ssw0rd', null, '555 555 1234', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Daan', 'Peters', 'HR Head', 'daan@mail.com', 'p4ssw0rd', null, '555 555 1235', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'John', 'Gorden', 'Benco Head', 'john@mail.com', 'p4ssw0rd', null, '555 555 1236', 500,250);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Frank', 'Stevens', 'IT Manager', 'frank@mail.com', 'p4ssw0rd', 1, '555 555 1237', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Robert', 'Brown', 'HR Manager', 'robert@mail.com', 'p4ssw0rd', 2, '555 555 1238', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Mark', 'Steves', 'Benco Manager', 'mark@mail.com', 'p4ssw0rd', 3, '555 555 1238', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Bob', 'Brains', 'IT Staff', 'bob@mail.com', 'p4ssw0rd', 4, '555 555 1238', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Twain', 'Stirt', 'IT Staff', 'twain@mail.com', 'p4ssw0rd', 4, '555 555 1238', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Kate', 'Beric', 'HR Staff', 'kate@mail.com', 'p4ssw0rd', 5, '555 555 1238', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'William', 'Beat', 'HR Staff', 'william@mail.com', 'p4ssw0rd', 5, '555 555 1238', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Bert', 'Simpson', 'Benco Staff', 'bert@mail.com', 'p4ssw0rd', 6, '555 555 1238', 1000,0);
INSERT INTO employee VALUES (emp_seq.nextval, 1, 'Sandy', 'Beach', 'Benco Staff', 'sandy@mail.com', 'p4ssw0rd', 6, '555 555 1238', 1000,0);
--End Populating Employee table

--Populating grade Fromat table
INSERT INTO gradingformat VALUES (gradeFormat_seq.nextval,'Letter Grade');
INSERT INTO gradingformat VALUES (gradeFormat_seq.nextval,'Percentage');
INSERT INTO gradingformat VALUES (gradeFormat_seq.nextval,'Pass/Fail');
INSERT INTO gradingformat VALUES (gradeFormat_seq.nextval,'Presentation');
--End Populating grade Fomrat Table

--Populating event Type table
INSERT INTO eventType VALUES (eventType_seq.nextval,'University Course', 80);
INSERT INTO eventType VALUES (eventType_seq.nextval,'Seminars', 60);
INSERT INTO eventType VALUES (eventType_seq.nextval,'Certification Preparation Classes', 75);
INSERT INTO eventType VALUES (eventType_seq.nextval,'Certification', 100);
INSERT INTO eventType VALUES (eventType_seq.nextval,'Technical Training', 80);
INSERT INTO eventType VALUES (eventType_seq.nextval,'Other', 30);
--End populating event Type table

--Populating Loation Table
INSERT INTO eventLocation VALUES (eventlocation_seq.nextval, '12420 N Florida Ave','Tampa','FL','33612','United States');
--END Populating Location Table

--Populating event table
INSERT INTO event VALUES (event_seq.nextval,1,1,1,'For Dummies',250,'12-OCT-19','12-DEC-19');
INSERT INTO event VALUES (event_seq.nextval,1,1,1,'For Dummies',250,'12-OCT-19','12-DEC-19');
--END

--Populating grade table
INSERT INTO grade VALUES (grade_seq.nextval,'C+','A',0,'');
--END


--Populating request table
INSERT INTO request VALUES (request_seq.nextval,5,1,1,1,'12-DEC-19','Processing',0,'Just Cause', 0,0,0,0,'',0,'',190,0,0);
INSERT INTO request VALUES (request_seq.nextval,5,1,1,1,'12-DEC-19','Processing',0,'Just Cause', 0,0,0,0,'',0,'',190,0,0);
INSERT INTO request VALUES (request_seq.nextval,5,1,1,1,'12-DEC-19','Processing',0,'Just Cause', 1,0,0,0,'',0,'',190,0,0);
INSERT INTO request VALUES (request_seq.nextval,5,1,1,1,'12-DEC-19','Processing',0,'Just Cause', 1,1,0,0,'',0,'',190,0,0);
--END

--Populating more info
INSERT INTO moreinfo VALUES (moreinfo_seq.nextval,1,0,0,'','');
--END

--Populating Attachemnt
INSERT INTO attachment VALUES (attachment_seq.nextval,1,'');

--Dropping Views
DROP VIEW empInfo;

--Creating Views
CREATE VIEW empInfo AS SELECT empid, firstname, lastname, jobtitle, dptname, email, password, reportto, dptHead, phonenumber, refund, refundpending 
FROM employee,department 
WHERE employee.dptid=department.dptid;

-- View to see employee info [ combines both employee and department table ]
DROP VIEW requestinfo;
CREATE VIEW requestinfo AS
SELECT request.requestid, request.empid, request.datecompleted, request.requeststatus, request.moreinfo, request.justification,
       request.directmanagerapproved, request.departmentheadapproved, request.bencoapproved, request.requestdenied, 
       request.deniedreason, request.preapproved, request.approvalattachment,
       request.projectedaward, request.awardchangedbenco, request.exceedavilable,
       grade.passinggrade, grade.finalgrade, grade.presentation, grade.presentationattach,
       event.eventdescription, event.eventcost, event.eventstart, event.eventend,
       eventtype.eventtypename, eventtype.reimbursementcoverage,
       eventlocation.streetaddress,eventlocation.city, eventlocation.state, eventlocation.country, eventlocation.zipcode,
       gradingformat.gradetype
FROM request
INNER JOIN grade ON request.gradeid=grade.gradeid
INNER JOIN event ON request.eventid=event.eventid
INNER JOIN eventtype ON event.eventtype=eventtype.eventtypeid
INNER JOIN eventlocation ON event.eventlocation=eventlocation.locationid
INNER JOIN gradingformat ON event.gradingformat=gradingformat.gformatid;



SELECT * FROM empInfo;
SELECT * FROM requestinfo;