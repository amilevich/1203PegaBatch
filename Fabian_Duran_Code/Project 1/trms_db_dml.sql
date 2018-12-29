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