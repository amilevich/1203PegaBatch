package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.FlashCard;

public class FlashCardDaoImpl implements FlashCardDao {

	/**
	 * JDBC Requirements for a Connection:
	 * 
	 * -Driver(the ojdbc.jar) -Username -Password -URL(location of the database)
	 * 
	 * 
	 */

	private static String USERNAME = "flashcard_db";
	private static String PASSWORD = "p4ssw0rd";
	private static String URL = "jdbc:oracle:thin:@octocatdb.cwpdzsvf6rnu.us-east-2.rds.amazonaws.com:1521:ORCL";

	public static void main(String[] args) {
		System.out.println("MAIN");
		FlashCard fc = new FlashCardDaoImpl().getFlashCardById(1);
		System.out.println(fc);

		List<FlashCard> flashCards = new FlashCardDaoImpl().getFlashCards();
		if (flashCards != null) {
			flashCards.forEach(e->{
				System.out.println(e);
			});
		}else {
			System.out.println("No Flashcards");
		}
		
	}

	public int createFlashCard(FlashCard fc) {

		return 0;
	}

	public FlashCard getFlashCardById(int id) {
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			String sql = "SELECT * FROM flashcard WHERE fc_id = ?";

			// PreparedStatement
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new FlashCard(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public FlashCard getFlashCardByQuestion(String question) {

		return null;
	}

	public List<FlashCard> getFlashCards() {
		List<FlashCard> flashCards = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			String sql = "SELECT * FROM flashcard";

			// PreparedStatement
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flashCards.add(new FlashCard(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			if (flashCards.size() > 0) {
				return flashCards;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

}
