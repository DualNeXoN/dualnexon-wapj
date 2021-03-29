package project.persistence.dao;

import java.util.List;

import project.persistence.model.Book;

public interface IBookDAO {
	
	public Book createBook(Book book);
	public Book editBook(Book book);
	public void deleteBook(Book book);
	public List<Book> getAllBooks();
	public Book getRandomBook();
	public Book getBookById(Integer id);
	
}
