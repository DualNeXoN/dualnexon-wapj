package project.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import project.persistence.dao.AuthorDAO;
import project.persistence.dao.IBookDAO;
import project.persistence.model.Author;
import project.persistence.model.Book;
import project.persistence.model.EGender;
import javax.inject.Named;

@Singleton
@Startup
public class Start {
	
	@Inject @Named("bookDAO")
	private IBookDAO bookDAO;
	
	@Inject
	private AuthorDAO authorDAO;
	
	@PostConstruct
	private void init() {
		
		Author author = new Author();
		author.setFirstName("Pavel");
		author.setLastName("Herout");
		author.setGender(EGender.WTF);
		authorDAO.create(author);
		
		Book book = new Book();
		book.setTitle("Nice sexy book of JEE!");
		book.setAuthor(author);
		bookDAO.createBook(book);
		
		List<Book> books = bookDAO.getAllBooks();
		for(Book b : books) {
			System.out.println("Book \"" + b.getTitle() + "\" by author \"" + b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName() + "\"");
		}
		
	}
	
}