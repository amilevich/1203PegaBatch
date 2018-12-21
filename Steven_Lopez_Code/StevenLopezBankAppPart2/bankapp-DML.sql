-------------------------bank_user inserts---------------------------------------
INSERT INTO bank_user VALUES(null, 'jdo1', 'word123','CUSTOMER');
INSERT INTO bank_user VALUES(null, 'mike89', 'word123','CUSTOMER');
INSERT INTO bank_user VALUES(null, 'meme_king', 'word123','CUSTOMER');
INSERT INTO bank_user VALUES(null, 'randomuser', 'word123','CUSTOMER');
INSERT INTO bank_user VALUES(null, 'jdoe2', 'word123','EMPLOYEE');
INSERT INTO bank_user VALUES(null, 'employee', 'word123','EMPLOYEE');
INSERT INTO bank_user VALUES(null, 'chiefCommander', 'word123','EMPLOYEE');
INSERT INTO bank_user VALUES(null, 'admin1', 'word123','ADMIN');


-------------------------customer info inserts-------------------------------------

INSERT INTO customer_info VALUES(1, 'John', 'First', 'Doe', '5555555555');
INSERT INTO customer_info VALUES(2, 'John', 'Second', 'Doe', '5555555555');
INSERT INTO customer_info VALUES(3, 'Michael', 'Gary', 'Scott', '5555555555');
INSERT INTO customer_info VALUES(4, 'John',null, 'Cena', '5555555555');
--------------------------bank_accounts inserts------------------------------------

INSERT INTO bank_account VALUES(null, 100.00,'APPROVED', 0, 4, '15-DEC-18');
INSERT INTO bank_account VALUES(null, 0, 'DENIED', 0, null, '');
INSERT INTO bank_account VALUES(null, 50000.00, 'APPROVED', 0, 6, '01-DEC-18');
INSERT INTO bank_account VALUES(null, 1000.00, 'APPROVED', 0, 7, '10-DEC-18');
INSERT INTO bank_account VALUES(null, 0, 'CANCELED', 0, 4, '05-DEC-18');

----------------------------account_user-------------------------------------------

INSERT INTO account_user VALUES(1,2453);
INSERT INTO account_user VALUES(2,2473);
INSERT INTO account_user VALUES(3,2455);
INSERT INTO account_user VALUES(4,2456);
INSERT INTO account_user VALUES(1,2457);

---------------------------bank_transaction-----------------------------------------
INSERT INTO bank_transaction VALUES(null, 2457, 1, 100.00, 'WITHDRAWL', '24-MAY-15');
INSERT INTO bank_transaction VALUES(null, 2453, 1, 100.00, 'DEPOSIT', '10-NOV-18');

INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '02-MAR-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '06-APR-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '04-MAY-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '01-JUN-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '06-JUL-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '03-AUG-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '07-SEP-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '05-OCT-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '02-NOV-18');
INSERT INTO bank_transaction VALUES(null, 2455, 3, 5000.00, 'DEPOSIT', '07-DEC-18');

INSERT INTO bank_transaction VALUES(null, 2473, 2, 543.0, 'DEPOSIT', '14-DEC-18');
INSERT INTO bank_transaction VALUES(null, 2473, 0, 543.0, 'WITHDRAWL', '14-DEC-18');

INSERT INTO bank_transaction VALUES(null, 2456, 4, 1000.00, 'DEPOSIT', '03-SEP-17');

COMMIT;

