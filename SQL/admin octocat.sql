--DCL

CREATE USER flashcard_db IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO flashcard_db; --allows user to connect

CREATE USER hw_db IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO hw_db; --allows user to connect
--Don't forget to check -DBA role for new users


CREATE USER media_db IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO media_db;

CREATE USER book_db IDENTIFIED BY p4ssw0rd;
GRANT CONNECT, RESOURCE TO book_db;

GRANT UNLIMITED TABLESPACE TO book_db;