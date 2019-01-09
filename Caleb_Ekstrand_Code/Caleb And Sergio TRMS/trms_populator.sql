INSERT INTO department_db VALUES(1, 'Administration');
INSERT INTO department_db VALUES(2,'Billing');
INSERT INTO department_db VALUES(3,'BenCo');
COMMIT;

INSERT INTO user_db VALUES(1,'Ben','Ford',3,3);
INSERT INTO user_db VALUES(2,'Alice','Moon',9,3);
INSERT INTO user_db VALUES(3,'Harvey','Luxington',2,3);
INSERT INTO user_db VALUES(4,'Bob','Rosenberg',3,3);
INSERT INTO user_db VALUES(5,'Josh','R-Child',6,2);
INSERT INTO user_db VALUES(6,'Alec','Lamb',8,2);
INSERT INTO user_db VALUES(7,'Betty','Tea',8,2);
INSERT INTO user_db VALUES(8,'Sergio','Sergio',9,2);
INSERT INTO user_db VALUES(9,'Mark','Coalfoot',0,1);
INSERT INTO user_db VALUES(10,'Sam','Bloom',9,1);
INSERT INTO user_db VALUES(11,'Zimmy','Goose',10,1);
COMMIT;

INSERT INTO login_db VALUES(1,'bford','p4ssw0rd');
INSERT INTO login_db VALUES(2,'amoon','p4ssw0rd');
INSERT INTO login_db VALUES(3,'hluxington','p4ssw0rd');
INSERT INTO login_db VALUES(4,'brosenberg','p4ssw0rd');
INSERT INTO login_db VALUES(5,'jrchild','p4ssw0rd');
INSERT INTO login_db VALUES(6,'alamb','p4ssw0rd');
INSERT INTO login_db VALUES(7,'btea','p4ssw0rd');
INSERT INTO login_db VALUES(8,'ssergio','p4ssw0rd');
INSERT INTO login_db VALUES(9,'mcoalfoot','p4ssw0rd');
INSERT INTO login_db VALUES(10,'sbloom','p4ssw0rd');
INSERT INTO login_db VALUES(11,'zgoose','p4ssw0rd');
COMMIT;
INSERT INTO grading_format_db VALUES(1, 'A-F');
INSERT INTO grading_format_db VALUES(2, '100-0');
INSERT INTO grading_format_db VALUES(3, 'Pass/Fail');
COMMIT;
INSERT INTO dep_head_db VALUES(9,1);
INSERT INTO dep_head_db VALUES(8,2);
INSERT INTO dep_head_db VALUES(2,3);
COMMIT;

SELECT CONCAT(b.first_name, b.last_name) AS BossOf, a.first_name, a.last_name FROM user_db a
    INNER JOIN user_db b ON b.user_id = a.ds_id;