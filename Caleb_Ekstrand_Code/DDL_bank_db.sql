DROP TABLE customer_acc_db;
DROP TABLE user_db;
DROP TABLE account_db;
DROP SEQUENCE user_seq;
DROP SEQUENCE acc_seq;

CREATE SEQUENCE acc_seq;
CREATE SEQUENCE user_seq;
CREATE TABLE user_db (
  username VARCHAR2(20),
  pass VARCHAR2(20) NOT NULL,
  name VARCHAR2(20) NOT NULL,
  phone VARCHAR2(15),
  address VARCHAR2(300),
  type VARCHAR2(20) NOT NULL,
  PRIMARY KEY(username)
);
CREATE TABLE account_db (
    acc_num INT,
    balance NUMBER(38, 2) DEFAULT 0,
    acc_type VARCHAR2(10),
    activated VARCHAR2(5) DEFAULT 'false',
    PRIMARY KEY(acc_num)
);

CREATE TABLE customer_acc_db (
    username VARCHAR2(20),
    acc_num INT,
    PRIMARY KEY(username, acc_num),
    FOREIGN KEY(username) REFERENCES user_db(username),
    FOREIGN KEY(acc_num) REFERENCES account_db(acc_num)
);

--INSERT INTO account_db(acc_type) VALUES('boom'); 
--INSERT INTO user_db(name, username, pass, type) VALUES('Bob', 'bobbiboi', 'pickles', 'customer');
--INSERT INTO account_db(acc_num, acc_type) VALUES(1, 'standard');
--INSERT INTO customer_acc_db VALUES('bobbiboi', 1);
--
--INSERT INTO user_db(name, username, pass, type) VALUES('Bob2', 'bobbibo', 'pickles', 'customer');
--INSERT INTO account_db(acc_num, acc_type) VALUES(2, 'standard');
--INSERT INTO customer_acc_db VALUES('bobbibo', 2);
--
--SELECT account_db.acc_num, balance, acc_type, activated FROM account_db
--INNER JOIN customer_acc_db ON account_db.acc_num = customer_acc_db.acc_num
--INNER JOIN user_db ON user_db.username = customer_acc_db.username
--WHERE user_db.username = 'bobbiboi';
COMMIT;