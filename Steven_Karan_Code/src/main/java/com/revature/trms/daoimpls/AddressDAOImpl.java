package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.AddressDAO;
import com.revature.trms.models.Address;
import com.revature.trms.util.ConnFactory;

public class AddressDAOImpl implements AddressDAO {

	private static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public boolean insertAddress(Address addr) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO address VALUES(null,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(2, addr.getStreet_number());
			ps.setString(3, addr.getRoute());
			ps.setString(4, addr.getCity());
			ps.setString(5, addr.getState());
			ps.setString(6, addr.getCountry());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean address_exists(Address addr) {
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM address WHERE city = ? AND state= ? AND country = ? "
					+ "AND street_number = ? AND route = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, addr.getCity());
			ps.setString(2, addr.getState());
			ps.setString(3, addr.getCountry());
			ps.setString(4, addr.getStreet_number());
			ps.setString(5, addr.getRoute());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	/*@Override
	public Address getAddress(int id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public boolean updateAddress(Address addr) {
		// TODO Auto-generated method stub
		return false;
	}*/

	/*@Override
	public boolean deleteAddress(int id) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
