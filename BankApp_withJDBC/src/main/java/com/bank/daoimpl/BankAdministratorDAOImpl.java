package com.bank.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bank.beans.BankAdministrator;
import com.bank.dao.BankAdministratorDAO;

public class BankAdministratorDAOImpl implements BankAdministratorDAO {

	public void createAccount(String accountid) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public List<BankAdministrator> getBankAdministratorList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public static ConnFactory cf = ConnFactory.getInstance();
	 * 
	 * public List<SuperHero> getSuperHeroList() throws SQLException { //array list
	 * that holds super hero objects List<SuperHero> superHeroList = new
	 * ArrayList<SuperHero>(); Connection conn = cf.getConnection(); Statement stmt
	 * = conn.createStatement(); ResultSet rs =
	 * stmt.executeQuery("SELECT * FROM SUPERHERO"); //getting superhero objects
	 * from the database. store them in arraylist. SuperHero s = null;
	 * while(rs.next()) { s = new SuperHero(rs.getInt(1), rs.getString(2)); //id,
	 * name superHeroList.add(s); } conn.close(); return superHeroList;
	 * 
	 * }
	 * 
	 * public void createSuperHero(String heroName) throws SQLException { // TODO
	 * Auto-generated method stub Connection conn = cf.getConnection(); String sql =
	 * "{ call INSERTSUPERHERO(?)"; CallableStatement call = conn.prepareCall(sql);
	 * call.setString(1, heroName); call.execute(); String sql1 =
	 * "SELECT HEROID FROM SUPERHERO WHERE HERONAME = ?"; PreparedStatement ps =
	 * conn.prepareStatement(sql1); ps.setString(1, heroName); //ResultSet rs =
	 * ps.executeQuery(); //while(rs.next()) {
	 * //System.out.println("Id of this superhero is : " + rs.getInt(1)); //} }
	 */
}
