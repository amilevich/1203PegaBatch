package com.trms.dao;
import com.trms.model.Employee2;
public interface Employee2Dao {

	//CREATE
	public int insertEmployee2(Employee2 employee2);
	
	//READ
	public Employee2 selectEmployee2ByScreenname(String screenname);
	
	//UPDATE
	//public int UpdatePet(Pet p);
	
	//DELETE
	//public int deletePet(Pet p);
	
}
