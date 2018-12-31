package com.revature.trms.daoimpls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.revature.trms.dao.AddressDAO;
import com.revature.trms.models.Address;
import com.revature.trms.util.ConnFactory;

public class AddressDAOImpl implements AddressDAO {

	private static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public int insertAddress(Address addr) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			String sql = "BEGIN INSERT INTO address VALUES(null,?,?,?,?,?,?,?) RETURNING address_id INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, addr.getAddress_text());
			cs.setString(2, addr.getStreet_number());
			cs.setString(3, addr.getRoute());
			cs.setString(4, addr.getCity());
			cs.setString(5, addr.getState());
			cs.setString(6, addr.getZipcode());
			cs.setString(7, addr.getCountry());
			cs.registerOutParameter(8, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(8);
			return rowAffected;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
	}

	@Override
	public boolean address_exists(Address addr) {
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM address WHERE address_text = ? AND city = ? AND state= ? AND country = ? "
					+ "AND street_number = ? AND route = ? AND zipcode = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, addr.getAddress_text());
			ps.setString(2, addr.getCity());
			ps.setString(3, addr.getState());
			ps.setString(4, addr.getCountry());
			ps.setString(5, addr.getStreet_number());
			ps.setString(6, addr.getRoute());
			ps.setString(7, addr.getZipcode());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Address getAddressById(int id) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM address WHERE address_id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Address address = null;
			if (rs.next()) {
				address = new Address();
				address.setAddress_id(id);
				address.setAddress_text(rs.getString("address_text"));
				address.setStreet_number(rs.getString("street_number"));
				address.setRoute(rs.getString("route"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setZipcode(rs.getString("zipcode"));
				address.setCountry(rs.getString("country"));
			}
			return address;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getAddressId(Address addr) {
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM address WHERE address_text = ? AND city = ? AND state= ? AND country = ? "
					+ "AND street_number = ? AND route = ? AND zipcode = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, addr.getAddress_text());
			ps.setString(2, addr.getCity());
			ps.setString(3, addr.getState());
			ps.setString(4, addr.getCountry());
			ps.setString(5, addr.getStreet_number());
			ps.setString(6, addr.getRoute());
			ps.setString(7, addr.getZipcode());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("address_id");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}

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
	
	public static void main(String[] args) {
		System.out.println("hello");
		System.out.println(new AddressDAOImpl().insertAddress(new Address("This is a text","this is another text", "this is not a text","this is a test", "This is a state", "zipco", "this is a country")));
	}

}
