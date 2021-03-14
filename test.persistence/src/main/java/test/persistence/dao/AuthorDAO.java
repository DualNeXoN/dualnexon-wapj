package test.persistence.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import test.persistence.model.Author;

@Stateless
public class AuthorDAO {
	
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
	
	public Author create(Author instance) {
		em.persist(instance);
		return instance;
	}
	
	public List<Author> getBooksByLastName(String value) {
		TypedQuery<Author> query = em.createNamedQuery("Author_findByLastName", Author.class);
		query.setParameter("lastName", value);
		return query.getResultList();
	}
	
}
