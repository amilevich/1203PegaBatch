CREATE TABLE bank_user(
id INTEGER,
username VARCHAR2(30) UNIQUE NOT NULL,
pw VARCHAR2(30) NOT NULL,
account_type INTEGER DEFAULT 1 NOT NULL,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(account_type) REFERENCES account_type
);

INSERT INTO bank_user VALUES(99999,'superuser','superuser',4,'The','Superb');

CREATE TABLE bank_account(
account_number INTEGER,
balance NUMBER(20,2) DEFAULT 0,
PRIMARY KEY(account_number),
FOREIGN KEY(account_owner) REFERENCES bank_user(id)
);


CREATE TABLE x_action(
id INTEGER,
acc_user INTEGER NOT NULL,
acc_number INTEGER NOT NULL,
amount NUMBER(30,2) NOT NULL,
transaction_time TIMESTAMP NOT NULL,
resulting_balance NUMBER(30,2) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(acc_user) REFERENCES bank_user(id),
FOREIGN KEY(acc_number) REFERENCES bank_account(account_number)

);
 

CREATE TABLE account_user(
account_number INTEGER,
user_id INTEGER,
PRIMARY KEY(account_number, user_id),
FOREIGN KEY (account_number) REFERENCES bank_account,
FOREIGN KEY(user_id) REFERENCES bank_user
);

CREATE TABLE user_type(
id INTEGER,
descript VARCHAR(30),
PRIMARY KEY(id)
);

INSERT INTO user_type VALUES(1,'Customer');
INSERT INTO user_type VALUES(2,'Employee');
INSERT INTO user_type VALUES(3,'Admin');
INSERT INTO user_type VALUES(4,'Superuser');

CREATE TABLE account_application(
id INTEGER,
user_id INTEGER,
PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES bank_user
);

CREATE OR REPLACE PROCEDURE kill_orphans
AS
BEGIN
DELETE FROM bank_account WHERE account_number NOT IN(SELECT account_number FROM account_user);

END kill_orphans;
/
CREATE SEQUENCE bank_account_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE bank_user_id_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE x_action_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE SEQUENCE account_application_id_seq
    START WITH 1
    INCREMENT BY 1;
    

CREATE OR REPLACE TRIGGER  bank_user_id_trigger
  before insert on bank_user              
  for each row  
begin   
  if :new.id is null then 
    select bank_user_id_seq.nextval into :new.id from dual; 
  end if; 
end; 
/

CREATE OR REPLACE TRIGGER  bank_account_id_trigger
  before insert on bank_account              
  for each row  
begin   
  if :new.account_number is null then 
    select bank_account_id_seq.nextval into :new.account_number from dual; 
  end if; 
end; 
/


CREATE OR REPLACE TRIGGER  x_action_id_trigger
  before insert on x_action              
  for each row  
begin   
  if :new.id is null then 
    select x_action_id_seq.nextval into :new.id from dual; 
  end if; 
end; 
/

CREATE OR REPLACE TRIGGER  account_application_id_trigger
  before insert on account_application              
  for each row  
begin   
  if :new.id is null then 
    select account_application_id_seq.nextval into :new.id from dual; 
  end if; 
end; 
/