package com.revature.trms.dao;

import com.revature.trms.models.Address;

public interface AddressDAO {

	//CRUDE methods
		//Create
		public boolean insertAddress(Address addr);
		
		//Read
		public Address getAddressById(int id);
		public boolean address_exists(Address addr);
		
		//Update
		//public boolean updateAddress(Address addr);
		
		//Delete
		//public boolean deleteAddress(int id);
		
}
