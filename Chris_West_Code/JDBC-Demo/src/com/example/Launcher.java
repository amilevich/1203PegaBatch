package com.example;

import java.util.Scanner;

import com.example.daos.BookDao;
import com.example.daos.BookDaoImpl;
import com.example.models.Book;

public class Launcher {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		BookDao bookDao = new BookDaoImpl();
		Book book = launcher.getBookInfo();
		bookDao.saveBook(book);
		System.out.println(book);
	}

	public Book getBookInfo() {
		Book book = new Book();
		System.out.println("Enter Author's name: ");
		String author = getStringInput();
		System.out.println("Enter book's name: ");
		String title = getStringInput();
		System.out.println("Enter ISBN number: ");
		String isbn = getStringInput();
		System.out.println("Enter page count: ");
		int pageCount = getIntInput();
		
		book.setAuthor(author);
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setPageCount(pageCount);
		
		return book;
	}

	public static String getStringInput() {
		String string = input.nextLine();
		return string;
	}

	public static int getIntInput() {
		Integer value = null;
		while (value == null) {
			if (input.hasNextInt())
				value = input.nextInt();
			else
				input.nextLine();
		}
		return value;
	}
}
