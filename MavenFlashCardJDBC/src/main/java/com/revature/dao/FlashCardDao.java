package com.revature.dao;

import java.util.List;

import com.revature.beans.FlashCard;

public interface FlashCardDao {

	// ONLY CRUD METHODS
	
	// CREATE
	public int createFlashCard(FlashCard fc);
	
	// READ
	public FlashCard getFlashCardById(int id);
	public FlashCard getFlashCardByQuestion(String question);
	public List<FlashCard> getFlashCards();
	// UPDATE
	
	
	// DELETE
	
	
}
