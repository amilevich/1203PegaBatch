package com.example.daos;

import com.example.models.Book;

/*
 * Data Access Object
 * 
 * That is is the interface through which we intend to 
 * interact with out persistence layer for this kind of object.
 * 
 * 
 * 
 */
public interface BookDao {

	public Book saveBook(Book book);
}
