package com.example.dao;

import com.example.model.Pet;

public interface PetDao {
	
	//CRUD
	
	//CREATE
	public int insertPet(Pet p);
	
	//READ
	public Pet selectByName(String name);
	
	//UPDATE
	//public int updatePet(Pet p);
	
	//DELETE
	//public int deletePet(Pet p);
	

}
