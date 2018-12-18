SELECT * FROM Accounts;

CREATE TABLE Accounts (
    account_number NUMBER,
    name       VARCHAR2(40),
    password   VARCHAR2(40),
    balance    number (13,2) default 0,
    admin      INT default 0,
    approved   INT default 0,
    CONSTRAINT primary_key PRIMARY KEY (account_number)
    );

-- -test commands

TRUNCATE TABLE Accounts;
-- tests
INSERT INTO user_account VALUES ('Kerry','123', 10.00, 0, 0);
INSERT INTO user_account VALUES ('Crystal','234', 0.00, 1, 1);
commit;

SELECT * FROM Accounts;

INSERT INTO Accounts (name) VALUES ('Patty');
DELETE FROM Accounts WHERE name = 'Patty';	

UPDATE Accounts SET balance = 4.0, approved = 0 WHERE name = 'Patty';
    
        
        
    commit;





-- INSERT trigger to auto-increment primary key
CREATE OR REPLACE TRIGGER auto_incrememnt_account_number
BEFORE INSERT ON Accounts
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN 
        SELECT pokemon_sequence.nextval INTO :NEW.id FROM dual;
    END IF;
END;
/


-- Create a Stored Procedure to INSERT a new Pokemon
CREATE OR REPLACE PROCEDURE insert_user(ACCOUNT_NUMBER IN NUMBER,
                NANE IN VARCHAR2, PASSWORD IN VARCHAR2, BALANCE IN VARCHAR2, ADMIN IN INTEGER, APPROVED IN INTEGER)
AS
BEGIN    
    INSERT INTO ACCOUNTS VALUES(10, "Marc", "v5MQ", 0.00, 0, 1);
    COMMIT;
END;
/
