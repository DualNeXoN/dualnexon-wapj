package test.persistence.business;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import test.persistence.model.Author;
import test.persistence.model.Book;
import test.persistence.model.EGender;

@Singleton
@Startup
public class Start {
	
	@PostConstruct
	private void init() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("wapjPU");
		EntityManager em = emf.createEntityManager();
		
		Book book = new Book();
		book.setTitle("Nice sexy book of JEE!");
		em.persist(book);
		
		Author author = new Author();
		author.setFirstName("Pavel");
		author.setLastName("Herout");
		author.setGender(EGender.WTF);
		
		em.persist(author);
		
	}
	
}
