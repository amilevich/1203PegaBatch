package com.revature;

import java.util.Scanner;

import com.revature.daos.BookDao;
import com.revature.daos.BookDaoImpl;
import com.revature.models.Book;

public class Launcher {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Launcher launcher = new Launcher();
		Book book = launcher.getBookInfo();
		BookDao bookDao = new BookDaoImpl();

		bookDao.saveBook(book);

	}

	public Book getBookInfo() {
		Book book = new Book();
		System.out.println("Please enter the author's name.");
		String author = getStringInput();

		System.out.println("Please enter the book's name.");
		String title = getStringInput();

		System.out.println("Please insert the ISBN number.");
		String isbn = getStringInput();

		System.out.println("Please insert the page count.");
		int pageCount = getIntInput();

		book.setAuthor(author);
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setPageCount(pageCount);

		return book;
	}

	public static String getStringInput() {
		String string = scanner.nextLine();
		return string;
	}

	public static int getIntInput() {
		Integer value = null;
		while (value == null) {
			if (scanner.hasNext()) {
				value = scanner.nextInt();
			} else {
				scanner.nextLine();
			}
		}
		return value;
	}

}
