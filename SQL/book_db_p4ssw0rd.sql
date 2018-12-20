CREATE TABLE book(
id INTEGER,
author VARCHAR2(40),
title VARCHAR2(40),
isbn VARCHAR2(13),
page_count INTEGER,
PRIMARY KEY (id)
);

CREATE SEQUENCE book_seq;

--CREATE SEQUENCE
SELECT book_seq.nextval FROM dual;

SELECT * FROM BOOK;

