CREATE TABLE Employee(
    emp_id INT,
    emp_firstName VARCHAR2(20) NOT NULL,
    emp_middleInitial VARCHAR2(1),
    emp_lastName VARCHAR2(20) NOT NULL,
    emp_address VARCHAR2(70),
    emp_city VARCHAR2(40),
    emp_state VARCHAR2(40),
    emp_zip INT,
    emp_phoneNumber INT,
    emp_building VARCHAR2(70) NOT NULL,
    direct_superID INT,
    dept_headID INT,
    emp_remainingAward INT NOT NULL,
    employee_type INT,--0 is employee, 1 is DS, 2 is DH, 3 is BC, 4 is BCDS, 5 BCDH, 6 CEO, won't need look up table as this will never be displayed...anywhere
    PRIMARY KEY (emp_id)
);
commit;

CREATE TABLE Reimbursement(
  rei_id INT,
  emp_id INT,
  rei_type VARCHAR(20) NOT NULL, --look up table?
  awd_req INT NOT NULL,
  awd_grnt INT,
  sub_date number NOT NULL, --date reimbursement was submitted
  evnt_stdate number NOT NULL, --date event starts
  evnt_fndate number, --event completed date
  comp_date number, --OPTIONAL
  ds_appr INT, --Direct Supervisor approval 0 for false, 1 true, auto completed if only DH
  ds_id INT,--can be filled by DH if completed by DH
  dh_appr INT,--Department Head approval, 0 for false, 1 true for Dept Head
  dh_id INT,--Department head ID
  bc_appr INT,--0 for false, 1 true for BenCo
  bc_id INT,--BenCo ID
  final_appr INT, --Final approval, 0 for false, 1 true can be done by DS/DH or BC
  final_id INT, --id of whoever finalizes proof of satisfactory work
  rei_state INT, --1 submitted, 2 pending, 3 denied, 4 approved, 5 cancelled [look up table]
  grade_id INT, --FK from grade table
  wrk_just BLOB, --any attachments used as proof
  rvw_mat BLOB,--any more material that is asked to be reviewed
  sat_proof BLOB, --proof of satisfactory work
  urgent INT, --0 no, 1 yes
  PRIMARY KEY (rei_id),
  FOREIGN KEY (emp_id) REFERENCES Employee (emp_id)
);

CREATE SEQUENCE reim_id_seq
START WITH 1
INCREMENT BY 1
MINVALUE 1
CACHE 5;

CREATE TABLE DeniedRequests(
  rei_id INT,--id of the denied reimbursement
  den_id INT,--id of the reviewer who denied reimbursement
  reason VARCHAR2(250) NOT NULL,
  PRIMARY KEY (rei_id),
  FOREIGN KEY (rei_id) REFERENCES Reimbursement (rei_id)
);

CREATE TABLE Grade(
    grade_id INT,--references to a specific reimbursement
    re_id INT,--here that id
    proof BLOB,
    pass_fail INT,--0 no, 1 yes
    lettergrade VARCHAR2(1),
    eval_id INT,--reviewer that assigned grade
    PRIMARY KEY (grade_id),
    FOREIGN KEY (re_id) REFERENCES Reimbursement (rei_id)
  );
 ALTER TABLE Reimbursement ADD FOREIGN KEY (grade_id) REFERENCES Grade(grade_id);
 ALTER TABLE Employee ADD username VARCHAR2(20) UNIQUE NOT NULL;
 ALTER TABLE Employee ADD password_ VARCHAR2(20) NOT NULL;
 
 ALTER TABLE Reimbursement DROP COLUMN username;
 alter table reimbursement drop column password_;
 
 create table rei_type(--reimbursement type look up table
 rt_id INT,
 rt_def VARCHAR2 (30)
 );
 
 create table rei_state(--reimbursement state look up table
 rs_id INT,
 rs_def VARCHAR2 (15)
 );
 
 alter table reimbursement modify evnt_stdate number;
  alter table reimbursement modify sub_date number;
   alter table reimbursement modify evnt_fndate number;
    alter table reimbursement modify comp_date number;

CREATE SEQUENCE grade_id_seq
START WITH 1
INCREMENT BY 1
MINVALUE 1
CACHE 5;

 commit;