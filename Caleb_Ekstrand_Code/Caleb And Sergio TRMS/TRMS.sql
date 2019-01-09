DROP TABLE communication_db;
DROP TABLE document_db;
DROP TABLE attatchment_db;
DROP TABLE ticket_db;
DROP TABLE grading_format_db;
DROP TABLE login_db;
DROP TABLE dep_head_db;
DROP TABLE dep_benco_db;
DROP TABLE user_db;
DROP TABLE department_db;
DROP SEQUENCE ticket_seq;
CREATE SEQUENCE ticket_seq
START WITH 1
INCREMENT BY 1
ORDER;
CREATE TABLE department_db(
    dep_id INTEGER,
    dep_name VARCHAR2(30),
    PRIMARY KEY(dep_id)
);
CREATE TABLE user_db(
    user_id INTEGER,
    first_name VARCHAR2(20) NOT NULL,
    last_name VARCHAR2(20) NOT NULL,
    ds_id INTEGER NOT NULL,
    dep_id INTEGER NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(dep_id) REFERENCES department_db(dep_id)
);
CREATE TABLE dep_head_db(
    dep_head_id INTEGER,
    dep_id INTEGER,
    FOREIGN KEY(dep_head_id) REFERENCES user_db(user_id),
    FOREIGN KEY(dep_id) REFERENCES department_db(dep_id)
);
CREATE TABLE dep_benco_db(
    dep_benco_id INTEGER,
    dep_id INTEGER,
    FOREIGN KEY(dep_benco_id) REFERENCES user_db(user_id),
    FOREIGN KEY(dep_id) REFERENCES department_db(dep_id)
);
CREATE TABLE login_db(
    user_id INTEGER,
    username VARCHAR2(20) UNIQUE NOT NULL,
    password VARCHAR2(20) NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(user_id) REFERENCES user_db(user_id)
);
CREATE TABLE grading_format_db(
    gf_id INTEGER,
    gf VARCHAR2(20),
    PRIMARY KEY(gf_id)
);
CREATE TABLE ticket_db(
    ticket_id INTEGER DEFAULT ticket_seq.nextval,
    event_date VARCHAR2(50) NOT NULL,
    event_time VARCHAR2(50) NOT NULL,
    event_loc VARCHAR2(100) NOT NULL,
    event_desc VARCHAR2(500) NOT NULL,
    event VARCHAR2(50) NOT NULL,
    event_cost NUMBER NOT NULL,
    gf_id INTEGER NOT NULL,
    gf_passing VARCHAR2(15) NOT NULL,
    justification VARCHAR2(4000) NOT NULL,
    user_id INTEGER NOT NULL,
    status VARCHAR2(10) DEFAULT 'pending',
    stage VARCHAR2(10) DEFAULT 'request',
    PRIMARY KEY(ticket_id),
    FOREIGN KEY(user_id) REFERENCES user_db(user_id),
    FOREIGN KEY(gf_id) REFERENCES grading_format_db(gf_id)
);
CREATE TABLE document_db(
    ticket_id INTEGER,
    doc CLOB,
    FOREIGN KEY(ticket_id) REFERENCES ticket_db(ticket_id)
);
CREATE TABLE attatchment_db(
    ticket_id INTEGER,
    attatchment BLOB,
    FOREIGN KEY(ticket_id) REFERENCES ticket_db(ticket_id)
);
CREATE TABLE communication_db(
    ticket_id INTEGER,
    comm_num INTEGER,
    sent_by INTEGER,
    comm CLOB,
    FOREIGN KEY(ticket_id) REFERENCES ticket_db(ticket_id),
    FOREIGN KEY(sent_by) REFERENCES user_db(user_id)
);
CREATE SEQUENCE ticket_seq
START WITH 1
INCREMENT BY 1
ORDER;