package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.SuperHero;
import com.revature.dao.SuperHeroDAO;
import com.revature.util.ConnFactory;

public class SuperHeroDAOImpl implements SuperHeroDAO{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void createSuperHero(String heroName) throws SQLException {
		Connection conn =  cf.getConnection();
		String sql = "{ call INSERTSUPERHERO(?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, heroName);
		call.execute();
		String sql1= "SELECT HEROID FROM SUPERHERO WHERE HERONAME= ?";
		PreparedStatement ps= conn.prepareStatement(sql1);
		ps.setString(1,heroName);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("The ID of this superhero is :"
		+rs.getInt(1));
		}
	}
		

	@Override
	public List<SuperHero> getSuperHeroList() throws SQLException {
		List<SuperHero> superHeroList=
				new ArrayList<SuperHero>();
		Connection conn= cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = 
				stmt.executeQuery("SELECT * FROM SUPERHERO");
		SuperHero s =null;
		while(rs.next()) {
			s= new SuperHero(rs.getInt(1),rs.getString(2));
			superHeroList.add(s);
		}
		conn.close();
		return superHeroList;
	}

}
