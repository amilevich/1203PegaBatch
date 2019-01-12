CREATE TABLE login(
    user_id INTEGER,
    user_name VARCHAR2(50) UNIQUE NOT NULL,
    pass_word VARCHAR2(50) NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO login VALUES(2, 'KPeterson321', 'las12son');

CREATE TABLE Employee(
    employee_id INTEGER,
    first_name VARCHAR2(20),
    last_name VARCHAR2(20),
    email VARCHAR2(50),
    department_id INTEGER,
    manager_id INTEGER,
    event_id INTEGER,
    event_location VARCHAR2(20),
    event_cost NUMBER,
    avai_reimbursement NUMBER,
    app_status VARCHAR2(20),
    event_date DATE,
    user_id INTEGER,
    work_justification VARCHAR2(30),
    attachment BLOB,
    approved_attachment BLOB,
    PRIMARY KEY (employee_id),
    FOREIGN KEY(user_id) REFERENCES login (user_id),
    FOREIGN KEY(event_id) REFERENCES Event (event_id),
    FOREIGN KEY(department_id) REFERENCES Department (department_id),
    FOREIGN KEY(manager_id) REFERENCES Management (manager_id)
);

DROP TABLE employee;

CREATE SEQUENCE employee_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE Event(
    event_id INTEGER,
    event_type VARCHAR2(20),
    event_coverage NUMBER(1,1),
    PRIMARY KEY (event_id)
);

CREATE TABLE Department(
    department_id INTEGER,
    department_name VARCHAR2(20),
    PRIMARY KEY (department_id)
);

CREATE TABLE Management(
    manager_id INTEGER,
    manager_name VARCHAR2(20),
    PRIMARY KEY (manager_id)
);

CREATE OR REPLACE PROCEDURE INSERTINFO(first_name IN VARCHAR2, last_name IN VARCHAR2, email IN VARCHAR2, 
department_id IN INTEGER, manager_id IN INTEGER, event_id IN INTEGER, event_location IN VARCHAR2,
event_cost IN NUMBER, avai_reimbursement IN NUMBER, app_status VARCHAR2, event_date IN DATE, user_id IN INTEGER,
work_justification VARCHAR2, attachment IN BLOB, approved_attachment IN BLOB)
AS
BEGIN
    INSERT INTO employee VALUES(employee_seq.NEXTVAL, first_name, last_name, email, 
                department_id, manager_id, event_id, event_location,event_cost, avai_reimbursement, 
                app_status, event_date, user_id, work_justification, attachment, approved_attachment);
    COMMIT;
END;
/