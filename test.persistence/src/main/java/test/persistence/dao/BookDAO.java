package test.persistence.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import test.persistence.model.Book;

@Stateless
public class BookDAO {
	
	@PersistenceContext(unitName = "wapjPU")
	private EntityManager em;
	
	@PostConstruct
	private void init() {
		System.out.println("Instance " + getClass().getSimpleName() + " created");
	}
	
	@PreDestroy
	private void destroy() {
		System.out.println("Instance " + getClass().getSimpleName() + " destroyed");
	}
	
	public Book create(Book instance) {
		em.persist(instance);
		return instance;
	}
	
	public List<Book> getBooksByTitle(String value) {
		TypedQuery<Book> query = em.createNamedQuery("Book_findByTitle", Book.class);
		query.setParameter("title", value);
		return query.getResultList();
	}
	
}
