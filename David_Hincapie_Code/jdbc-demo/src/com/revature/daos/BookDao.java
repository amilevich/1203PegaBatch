package com.revature.daos;

import com.revature.models.Book;

/**
 * 
 * @author davidhincapie
 * 
 *         Data Access Object
 * 
 *         That this is the interface through which we intend to interact with
 *         our persistency layer for this kind of object.
 *
 */
public interface BookDao {

	public Book saveBook(Book book);

}
