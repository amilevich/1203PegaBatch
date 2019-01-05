package com.revature.trms.dao;

import java.util.ArrayList;

import com.revature.trms.models.Signature;

public interface SignatureDAO {
	//CRUDE methods
			//Create
			public boolean insertSignature(Signature sign);
			//Read
			public ArrayList<Signature> getAllSignatureByEmp(int emp);
			public ArrayList<Signature> getAllSignatureByReimb(int reimb);
			
			//Update
			public boolean updateSignature(Signature sign);
			//Delete
			public boolean deleteSignature(int id);
}
