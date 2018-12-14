package com.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import com.example.models.Book;

public class BookDaoImpl implements BookDao {
	private static Connection getConnection() {
		// Way more secure as environment variables
		String username = "book_db"; // System.getenv("jdbc_user")
		String password = "womBat";
		String url = "jdbc:oracle:thin:@octocatdb.cwpwwksk2urc.us-east-2.rds.amazonaws.com:1521:ORCL";

		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

//	@Override
//	public Book saveBook(Book book) {
//		/*
//		 * Connection is a major interface of JDBC
//		 * 
//		 */
//		Connection conn = getConnection();
//
//		String query = "INSERT INTO book (id, title, author, isbn, page_count) "
//				+ String.format(" VALUES (book_seq.nextval, '%s', '%s','%s', %d)", book.getTitle(), book.getAuthor(),
//						book.getIsbn(), book.getPageCount());
//		System.out.println(query);
//
//		try {
//			Statement statement = conn.createStatement();
//			statement.execute(query);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return book;
//	}
 @Override
 public Book saveBook(Book book) {
	 Connection conn = getConnection();
	 String query = "INSERT INTO book (id, title, author, page_count, isbn) " + "VALUES (book_seq.nextval, ?, ?, ?, ?)";

	 try {
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setInt(3, book.getPageCount());
		ps.setString(4, book.getIsbn());
		ps.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return book;
 }
}
