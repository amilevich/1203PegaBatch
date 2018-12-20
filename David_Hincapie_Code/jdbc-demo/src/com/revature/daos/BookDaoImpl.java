package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.Book;

public class BookDaoImpl implements BookDao {

	private static Connection getConnection() {
		String user = "book_db";
		String password = "p4ssw0rd";
		String url = "jdbc:oracle:thin:@octocatdb.cwpdzsvf6rnu.us-east-2.rds.amazonaws.com:1521:ORCL";
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Book saveBook(Book book) {
		Connection conn = getConnection();
		String query = "INSERT INTO book(id,title,author, page_count, isbn) " + "VALUES (book_seq.nextval, ?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getPageCount());
			ps.setString(4, book.getIsbn());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Book saveBookBad(Book book) {
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();

		// DO NOT EVER ACTUALLY DO THIS
		String query = "INSERT INTO book(id,title,author, page_count, isbn)"
				+ String.format(" VALUES(book_seq.nextval, '%s','%s','%d','%s')", book.getTitle(), book.getAuthor(),
						book.getPageCount(), book.getIsbn());
		System.out.println(query);

		try {
			java.sql.Statement statement = conn.createStatement();
			statement.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

}
