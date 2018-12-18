package com.revature.dao;

import java.util.List;

import com.revature.beans.FlashCard;

/*
 * CRUD METHODS ONLY
 * 
 * 1. CREATE
 * 
 * 2. READ
 * 
 * 3. UPDATE
 * 
 * 4. DELETE
 * 
 */
public interface FlashCardDao {

	public int createFlashCard(FlashCard fc); // Look at 1

	public FlashCard getFlashCardById(int id); // Look at 2

	public FlashCard getFlashCardByQuestion(String question);

	public List<FlashCard> getFlashCards();

}
