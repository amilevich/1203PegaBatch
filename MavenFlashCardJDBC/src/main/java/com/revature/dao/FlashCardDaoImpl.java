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

	/*
	 *	JDBC Requirements for a Connection:
	 *		- Driver ( the jar)
	 *		- Username
	 *		- Password
	 *		- URL (location of the database)
	 */
	private static final String USERNAME = "flashcard_db";
	private static final String PASSWORD = "p4ssw0rd";
	private static final String URL = "jdbc:oracle:thin:@octocatdb.cdupfru9241v.us-east-1.rds.amazonaws.com:1521:ORCL";
	
	@Override
	public int createFlashCard(FlashCard fc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FlashCard getFlashCardById(int id) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){
			String sql = "SELECT * FROM flashcard WHERE fc_id = ?";
			
			// PreparedStatement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new FlashCard(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			
		} catch (SQLException e) {
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
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){
			String sql = "SELECT * FROM flashcard";
			
			// PreparedStatement
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				flashcards.add(new FlashCard(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			if(flashcards.size() > 0) {
				return flashcards;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//		FlashCard fc = new FlashCardDaoImpl().getFlashCardById(1);
//		System.out.println(fc);
		
		List<FlashCard> myFlashcards = new FlashCardDaoImpl().getFlashCards();
		if(myFlashcards != null) {
			myFlashcards.forEach( e-> {
			e.setFcAnswer("bobbert2");
			System.out.println(e);
			});
		}else {
			System.out.println("No Flashcards");
		}
		for(FlashCard fc : myFlashcards) {
			fc.setFcAnswer("bobbert");
			System.out.println(fc);
		}
	}
	
}
