package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.FlashCard;
/*
 * JDBC Requirements for a Connection:
 * 	- Driver (the jar)
 * 	- USERNAME
 * 	- PASSWORD
 * 	- URL
 */

public class FlashCardDaoImpl implements FlashCardDao {
	private static final String USERNAME = "flashcard_db";
	private static final String PASSWORD = "p4ssw0rd";
	private static final String URL = "jdbc:oracle:thin:@octocatdb.cwpwwksk2urc.us-east-2.rds.amazonaws.com:1521:ORCL";

	@Override
	public int createFlashCard(FlashCard fc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FlashCard getFlashCardById(int id) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM flashcard WHERE fc_id = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new FlashCard(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FlashCard getFlashCardByQuestion(String question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlashCard> getFlashCards() {
		List<FlashCard> flashcards = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM flashcard ";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flashcards.add(new FlashCard(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			if (flashcards.size() > 0) {

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		FlashCard fc = new FlashCardDaoImpl().getFlashCardById(1);
		System.out.println(fc);
		
		
		List<FlashCard> myFlashcards = new FlashCardDaoImpl().getFlashCards();
		if(myFlashcards != null) {
			myFlashcards.forEach( e-> {
				System.out.println(e);
			});
			
		}else
				System.out.println("No Flashcards!");
	}

}
