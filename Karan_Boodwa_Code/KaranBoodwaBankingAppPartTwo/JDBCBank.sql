--- Admin + DCL ---

CREATE USER bank_db IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE, DBA TO bank_db;


--- DDL ---

-- DROPS (convenience)
DROP TABLE useracc_bankacc;
DROP TABLE bankacc;
DROP TABLE application;
DROP TABLE useracc;
DROP TABLE user_type;


DROP SEQUENCE useracc_id_seq;
DROP SEQUENCE app_id_seq;

DROP TRIGGER useracc_id_trigger;
DROP TRIGGER bankacc_id_trigger;

CREATE SEQUENCE useracc_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE app_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE TABLE user_type(
    type_id INT,
    type VARCHAR2 (20) NOT NULL,
    PRIMARY KEY(type_id)
);

CREATE TABLE useracc(
    user_id INT,
    username VARCHAR2(20) UNIQUE NOT NULL,
    password VARCHAR2(20) NOT NULL,
    user_type_id INT DEFAULT(1) NOT NULL,
    FOREIGN KEY(user_type_id) REFERENCES user_type(type_id),
    PRIMARY KEY(user_id)
); 

CREATE TABLE application(
    app_id INT,
    user_id INT NOT NULL,
    user_id2 INT,
    PRIMARY KEY(app_id),
    FOREIGN KEY(user_id) REFERENCES useracc(user_id),
    FOREIGN KEY(user_id2) REFERENCES useracc(user_id)
);


CREATE TABLE bankacc(
     acc_id INT,
     balance NUMBER(11,2) DEFAULT(0) CHECK(balance>=0),
     PRIMARY KEY(acc_id)
);

-- JUNCTION TABLE
CREATE TABLE useracc_bankacc(
    user_id INT,
    acc_id INT,
    FOREIGN KEY(user_id) REFERENCES useracc(user_id),
    FOREIGN KEY(acc_id) REFERENCES bankacc(acc_id),
    PRIMARY KEY (user_id,acc_id)
);

-- AUTO INCREMENT TRIGGER for useracc table 
CREATE OR REPLACE TRIGGER  useracc_id_trigger
  before insert on useracc              
  for each row  
begin   
  if :new.user_id is null then 
    select useracc_id_seq.nextval into :new.user_id from dual; 
  end if; 
end; 
/

CREATE OR REPLACE TRIGGER  app_id_trigger
  before insert on application             
  for each row  
begin   
  if :new.app_id is null then 
    select app_id_seq.nextval into :new.app_id from dual; 
  end if; 
end; 
/



-- DML (testing + fake data) -- 
INSERT INTO user_type VALUES(1, 'Customer'); 
INSERT INTO user_type VALUES(2, 'Employee');
INSERT INTO user_type VALUES(3, 'Admin');
INSERT INTO user_type VALUES(4, 'Super');


INSERT INTO useracc VALUES(null, 'superuser','superpassword',4); -- id: 1
INSERT INTO useracc VALUES(null, 'admin','adminpassword',3); -- id: 2
INSERT INTO useracc VALUES(null, 'employee','employeepassword',2); -- id: 3 
INSERT INTO useracc VALUES(null, 'customer','customerpassword',1); -- id: 4
INSERT INTO useracc VALUES(null, 'customer2','customerpassword2',1); -- id: 5
INSERT INTO useracc VALUES(null, 'customer4','customerpassword3',1); -- id: 6

INSERT INTO bankacc VALUES(8675309, 200.30); -- id: 1
INSERT INTO bankacc VALUES(5551234, 103400.60); -- id: 2
INSERT INTO bankacc VALUES(5318008, 2368990.83); -- id: 3

INSERT INTO useracc_bankacc VALUES(4,8675309); -- customer -> acc 1
INSERT INTO useracc_bankacc VALUES(5,8675309); -- customer2 -> acc 1
INSERT INTO useracc_bankacc VALUES(5,5551234); -- customer2 -> acc 2
INSERT INTO useracc_bankacc VALUES(6,5318008); -- customer3 -> acc 3

COMMIT;

-- Joins/Views

CREATE OR REPLACE VIEW user_bank_accs AS
SELECT username, bankacc.acc_id Account, Balance FROM useracc
INNER JOIN useracc_bankacc ON useracc_bankacc.user_id = useracc.user_id
INNER JOIN bankacc ON useracc_bankacc.acc_id=bankacc.acc_id;


CREATE OR REPLACE VIEW user_view AS
SELECT user_id, username, password, type FROM useracc
LEFT JOIN user_type ON useracc.user_type_id = user_type.type_id;


-- MATT HULL'S GENIUS MIND:
CREATE OR REPLACE VIEW app_view AS
SELECT app_id1, user_id holder, col_one, user_id2 second_holder, col_two FROM(
(SELECT app_id as app_id1, application.user_id, username as col_one FROM useracc INNER JOIN application ON useracc.user_id=application.user_id)a
LEFT JOIN
(SELECT app_id as app_id2, application.user_id2, username as col_two FROM useracc INNER JOIN application ON useracc.user_id=application.user_id2)b
ON a.app_id1 = b.app_id2
);


-- Stored procedure to delete a user from all tables
create or replace PROCEDURE delete_user(id IN useracc.user_id%type)
AS
BEGIN
    DELETE FROM useracc_bankacc WHERE user_id = id;
    DELETE FROM bankacc WHERE acc_id NOT IN (SELECT acc_id FROM useracc_bankacc); -- delete orphan accounts
    DELETE FROM application WHERE user_id = id OR user_id2 = id;
    DELETE FROM useracc WHERE user_id = id;
    COMMIT; -- commit makes this a transaction (makes changes above PERMANENT!)
END;


create or replace PROCEDURE delete_orphan_accs
AS
BEGIN
    DELETE FROM bankacc WHERE acc_id NOT IN (SELECT acc_id FROM useracc_bankacc);
END;





COMMIT;