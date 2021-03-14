package test.persistence.business;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import test.persistence.dao.AuthorDAO;
import test.persistence.dao.BookDAO;
import test.persistence.model.Author;
import test.persistence.model.Book;
import test.persistence.model.EGender;


@Singleton
@Startup
public class Start {
	
	@Inject
	private BookDAO bookDAO;
	
	@Inject
	private AuthorDAO authorDAO;
	
	@PostConstruct
	private void init() {
		
		Book book = new Book();
		book.setTitle("Nice sexy book of JEE!");
		bookDAO.create(book);
		
		Author author = new Author();
		author.setFirstName("Pavel");
		author.setLastName("Herout");
		author.setGender(EGender.WTF);
		authorDAO.create(author);
		
	}
	
}
