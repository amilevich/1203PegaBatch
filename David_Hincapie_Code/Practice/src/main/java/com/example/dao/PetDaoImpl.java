package com.example.dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Pet;

public class PetDaoImpl implements PetDao {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String url = "jdbc:oracle:thin:@octocatdb.cwpdzsvf6rnu.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static String username = "pet_db";
	private static String password = "p4ssw0rd";

	@Override
	public int insertPet(Pet p) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			PreparedStatement ps = connection.prepareStatement("INSERT INTO Pets VALUES(?,?)");
			ps.setString(1, p.getName());
			ps.setString(2, p.getType());
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Pet selectByName(String name) {
		Pet pet = null;
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Pets WHERE Name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pet = new Pet(rs.getString("name"), rs.getString("type"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pet;
	}

}
