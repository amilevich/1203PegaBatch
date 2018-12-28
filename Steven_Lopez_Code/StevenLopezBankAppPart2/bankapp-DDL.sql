------------------------------------------------------User--------------------------------------------------------------
CREATE TABLE bank_user(
    user_id INTEGER,
    username VARCHAR2(50)NOT NULL,
    user_password VARCHAR2(50)NOT NULL,
    role VARCHAR2(20),
    PRIMARY KEY(user_id)
);
/
---------------------------------user sequence-----------------------------------
CREATE SEQUENCE user_id_seq;
/
---------------------------------user trigger------------------------------------
CREATE OR REPLACE TRIGGER  user_id_trigger
  BEFORE INSERT ON bank_user              
  FOR EACH ROW
BEGIN   
  IF :NEW.user_id IS NULL THEN 
    SELECT user_id_seq.NEXTVAL INTO :NEW.user_id FROM DUAL; 
  END IF;
END; 
/
------------------------------------------------------Account----------------------------------------------------------
CREATE TABLE bank_account(
    account_id INTEGER,
    balance NUMBER(10,2) NOT NULL,
    account_state VARCHAR2(20) DEFAULT 'PENDING' NOT NULL,
    joint_check SMALLINT NOT NULL, 
    approved_by INTEGER,
    approved_date DATE,
    CONSTRAINT check_joint CHECK (joint_check IN (0,1)),
    CONSTRAINT check_balance CHECK (balance >= 0),
    PRIMARY KEY(account_id)
);
/
----------------------------------account sequence--------------------------------------
CREATE SEQUENCE account_id_seq
START WITH 2453;
/
----------------------------------account trigger---------------------------------------
CREATE OR REPLACE TRIGGER account_id_trigger
  BEFORE INSERT ON bank_account             
  FOR EACH ROW
BEGIN   
  IF :NEW.account_id IS NULL THEN 
    SELECT account_id_seq.NEXTVAL INTO :NEW.account_id FROM DUAL; 
  END IF;
END; 
/


---------------------------------------------------------account_user---------------------------------------------------

CREATE TABLE account_user(
    user_id INTEGER NOT NULL,
    account_id INTEGER NOT NULL,
    PRIMARY KEY(account_id, user_id),
    FOREIGN KEY(user_id) REFERENCES bank_user(user_id),
    FOREIGN KEY(account_id) REFERENCES bank_account(account_id)
);
/


---------------------------------------------------------transaction----------------------------------------------------

CREATE TABLE bank_transaction(
    trans_id INTEGER,
    account_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    amount NUMBER(10,2) NOT NULL,
    trans_type VARCHAR2(20) NOT NULL,
    trans_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT check_amount CHECK (amount > 0.001),
    CONSTRAINT check_type CHECK (trans_type IN ('DEPOSIT','WITHDRAWL','TRANSFER')),
    PRIMARY KEY(trans_id),
    FOREIGN KEY(account_id) REFERENCES bank_account(account_id)
);
/  
-----------------------------------transaction sequence----------------------------
CREATE SEQUENCE trans_id_seq;
/
-----------------------------------transaction trigger-----------------------------
CREATE OR REPLACE TRIGGER trans_id_trigger
  BEFORE INSERT ON bank_transaction             
  FOR EACH ROW
BEGIN   
  IF :NEW.trans_id IS NULL THEN 
    SELECT trans_id_seq.NEXTVAL INTO :NEW.trans_id FROM DUAL; 
  END IF;
END; 
/

-----------------------------------------------------------customer information----------------------------------------
CREATE TABLE customer_info(
    user_id INTEGER NOT NULL,
    first_name VARCHAR2(50) NOT NULL,
    middle_name VARCHAR2(20),
    last_name VARCHAR2(50) NOT NULL,
    phone VARCHAR2(10) NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(user_id) REFERENCES bank_user (user_id)
);
/

----------------------------------------Customer View-----------------------------------------------------------------
CREATE OR REPLACE VIEW bank_customer AS
    SELECT c.user_id, username, user_password, role, first_name, middle_name, last_name, phone
    FROM bank_user u JOIN customer_info c 
    ON u.user_id = c.user_id;  
/
----------------------------------------------------------------------------------------------------------------------
    
/*
DROP TABLE bank_transaction;
DROP TABLE account_user;
DROP TABLE bank_account;
DROP TABLE bank_user;
DROP TABLE account_user;

DROP SEQUENCE user_id_seq;
DROP SEQUENCE trans_id_seq;
DROP SEQUENCE account_id_seq;
