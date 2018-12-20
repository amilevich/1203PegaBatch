--Teacher
--Classroom

--Each teacher has one classroom, but this classroom may be shared with many teacher.
-- one-to-many relationship (classroom to teachers)
--In a one-to-many relationship the foriegn key will generally be held on the many side
--In this situation, a teacher will hold a foriegn key to a classroom


-- What is a classroom? what would be important for us to know about a classroom?

-- CLASSROOM
-- id : INTEGER (PRIMARY KEY)
-- Room number : VARCHAR2
-- OCCUPANCY : INTEGER
-- Roomtype : VARCHAR2 *
-- Building : VARCHAR2 *

CREATE TABLE classrooms (
id INTEGER,
room_number VARCHAR2(10) NOT NULL,
occupancy INTEGER,
room_type VARCHAR2(25) DEFAULT('standard'),
building_type VARCHAR2(50),
PRIMARY KEY(id)
);


--TEACHER
-- id : INTEGER (PRIMARY KEY)
-- first_name : VARCHAR2(50)

CREATE TABLE teachers(
id INTEGER,
first_name VARCHAR2(50),
last_name VARCHAR2(50) NOT NULL,
title VARCHAR2(30),
classroom_id INTEGER,
PRIMARY KEY (id),
FOREIGN KEY (classroom_id) REFERENCES classrooms (id)
-- subject VARCHAR2(30)
);


--INSERT INTO classrooms(column names) VALUES (values by cloumn)
INSERT INTO classrooms(id, room_number, occupancy, building_type)
--VALUES(1,100,25,'NEC');
--VALUES(2,101,20,'NEC');
--VALUES(3,102,30,'NEC');
VALUES(4,103,25,'NEC');


INSERT INTO teachers(id,first_name,last_name,title,classroom_id)
--VALUES(1,'Abby','Adams','Admiral',2);
--VALUES(2,'Billy','Barker','Baron',2);

VALUES(3,'Cathy','Carter','Coach',4);
-- we cannot insert this row with id 4, because classroom_id is a foreign key column
-- which references the column classrooms.id and this column has no value of 4.


SELECT * FROM classrooms;
SELECT * FROM teachers;

-- We want to join the classrooms and teachers tables
--where do we start?
SELECT first_name, last_name, room_number, building_type FROM teachers
LEFT JOIN classrooms ON classrooms.id = teachers.classroom_id;


--CREATE VIEW
CREATE VIEW teacher_classrooms AS
SELECT first_name, last_name, room_number, building_type FROM teachers
LEFT JOIN classrooms ON classrooms.id = teachers.classroom_id;


