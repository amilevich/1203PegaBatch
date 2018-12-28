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
    employee_type INT,
    PRIMARY KEY (emp_id)
);

CREATE TABLE Reimbursement(
  rei_id INT,
  emp_id INT,
  rei_type VARCHAR(20) NOT NULL, --look up table?
  awd_req INT NOT NULL,
  awd_grnt INT,
  sub_date DATE NOT NULL, --date reimbursement was submitted
  evnt_stdate DATE NOT NULL, --date event starts
  evnt_fndate DATE, --event completed date
  comp_date DATE, --OPTIONAL
  ds_appr INT, --0 for false, 1 true, auto completed if only DH
  ds_id INT,--can be filled by DH if completed by DH
  dh_appr INT,--0 for false, 1 true for Dept Head
  dh_id INT,
  bc_appr INT,--0 for false, 1 true for BenCo
  bc_id INT,
  final_appr INT, --0 for false, 1 true can be done by DS/DH or BC
  final_id INT, --id of whoever finalizes proof of satisfactory work
  rei_state INT, --1 submitted, 2 pending, 3 denied, 4 approved, 5 cancelled [look up table]
  grade_id INT, --FK from grade table
  wrk_just BLOB, --any attachments used as proof
  rvw_mat BLOB,
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
  rei_id INT,
  den_id INT,
  reason VARCHAR2(250) NOT NULL,
  PRIMARY KEY (rei_id),
  FOREIGN KEY (rei_id) REFERENCES Reimbursement (rei_id)
);

CREATE TABLE Grade(
    grade_id INT,
    re_id INT,
    type VARCHAR2(40),
    proof BLOB,
    pass_fail INT,--0 no, 1 yes
    lettergrade VARCHAR2(1),
    eval_id INT,
    PRIMARY KEY (grade_id),
    FOREIGN KEY (re_id) REFERENCES Reimbursement (rei_id)
  );
 ALTER TABLE Reimbursement ADD FOREIGN KEY (grade_id) REFERENCES Grade(grade_id); 
