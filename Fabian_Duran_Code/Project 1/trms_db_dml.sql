insert into rei_type 
values (0, 'University Course');

insert into rei_type 
values (1, 'Seminar');

insert into rei_type 
values (2, 'Certification Preparation Class');

insert into rei_type 
values (3, 'Certification');

insert into rei_type 
values (4, 'Technical Training Class');

insert into rei_type 
values (5, 'Other');

insert into rei_state
values (1, 'Submitted');

insert into rei_state
values (2, 'Pending');

insert into rei_state
values (3, 'Denied');

insert into rei_state
values (4, 'Approved');

Update rei_state
set rs_id = 5
where rs_def = 'Cancelled';

insert into employee
values (1, 'Fabian', 'D', 'Duran', '12702 Bruce B Downs Blvd',
'Tampa', 'FL', 33612, 1234567890, 'NEC', 10, 11, 1000, 0, 'p4ssw0rd');

SELECT emp_id, password_ FROM Employee WHERE emp_id=1;

insert into reimbursement (rei_id, emp_id, rei_type, awd_req, awd_grnt, sub_date, evnt_stdate, urgent, description)
values(1, 1, 3, 500, 0, 1546368060000, 1546972860000, 0,'blarb');
commit;

Update reimbursement set evnt_fndate = null where rei_id = 2;
--0 is employee, 1 is DS, 2 is DH, 3 is BC, 4 is BCDS, 5 BCDH, 6 CEO
insert into emp_table
values (0, 'Employee');

insert into emp_table
values (1, 'Direct Supervisor');

insert into emp_table
values (2, 'Department Head');

insert into emp_table
values (3, 'Benefits Coordinator');

insert into emp_table
values (4, 'Benefits Coordinator Direct Supervisor');

insert into emp_table
values (5, 'Benefits Coordinator Department Head');
commit;

select * from emp_view;

update reimbursement
set rei_state = 1
where rei_id = 2;

--RT_DEF,--Rei_type ON rei_type = rt_id
select
REI_ID,
EMP_ID,
AWD_REQ,
AWD_GRNT,
SUB_DATE,
EVNT_STDATE,
EVNT_FNDATE,
COMP_DATE,
DS_APPR,
DS_ID,
DH_APPR,
DH_ID,
BC_APPR,
BC_ID,
FINAL_APPR,
FINAL_ID,
RS_DEF,-- Rei_state ON rei_state = rs_id
GRADE_ID,
URGENT,
DESCRIPTION
FROM Reimbursement
LEFT JOIN rei_state
ON rei_state = rs_id;
select * from tempview;
select REI_ID,
EMP_ID,
AWD_REQ,
rt_def,
AWD_GRNT,
SUB_DATE,
EVNT_STDATE,
EVNT_FNDATE,
COMP_DATE,
DS_APPR,
DS_ID,
DH_APPR,
DH_ID,
BC_APPR,
BC_ID,
FINAL_APPR,
FINAL_ID,
RS_DEF,
GRADE_ID,
URGENT,
DESCRIPTION
from tempview
left join
rei_type
on rei_type=rt_id;
select * from tempview;
insert into rei_state 
values (6, 'Waiting on Response');
commit;

insert into grade_format 
values (1, 'Letter Grade');

insert into grade_format 
values (2, 'Pass Fail');

insert into grade_format 
values (3, 'Presentation');

insert into grade_format 
values (4, 'Other');

select * from view_reimbursement;

select v.rei_id, e.emp_firstname, e.emp_lastname, v.emp_id, v.rt_def
from view_reimbursement v
Left Join employee e
on v.emp_id = e.emp_id;
select * from grade_format;
select grade_id, re_id, eval_id, gf_def, satisfactory
From grade
left join grade_format
on grade_format = gf_id;
commit;
Update reimbursement set rei_state = 1 where rei_id = 1;
select * From VIEW_REIMBURSEMENT
WHERE emp_id = 1 AND rs_def = 'Submitted';

insert into rei_year 
values (2019);

update employee set emp_remainingaward = 1000;

INSERT INTO reimbursement (rei_id, emp_id, rei_type, awd_req, awd_grnt, sub_date, evnt_stdate, rei_state, urgent, description) 
VALUES (1, 1, 3, 500, 0, '2019-01-29', '2019-01-29', 1, 0, 'blarb');

Update employee set DIRECT_SUPERID = 9, DEPT_HEADID = 10 where emp_id = 1;
delete from reimbursement where emp_id =1;
insert into rei_state values (7, 'Waiting Final Approval');
insert into rei_state values (8, 'Waiting Event Completion');
insert into rei_state values (9, 'Change in Award');
Update reimbursement set rei_state = 2;
SELECT * FROM view_reimbursement WHERE emp_id = 1 AND rs_def = 'Pending';
delete from grade where grade_id =1;
INSERT INTO additional_info (ai_id, rei_id, from_id, to_id, request)
VALUES (ADD_INFO_SEQ.NEXTVAL,8, 15,10,'Please provide more information');
Update additional_info set to_id = 15, from_id = 10;
commit;
SELECT * FROM additional_info WHERE to_id = 8 AND state = 0;