package project.persistence.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import project.persistence.model.Author;

@Stateless
public class AuthorDAO {
	
	@PersistenceContext(unitName = "persistence_unit_wapj")
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
	
	public List<Author> getAuthorsByFirstName(String firstName) {
		TypedQuery<Author> query = em.createNamedQuery("getAuthorsByFirstName", Author.class);
		query.setParameter("firstName", firstName);
		return query.getResultList();
	}
	
	public List<Author> getAuthorsByLastName(String lastName) {
		TypedQuery<Author> query = em.createNamedQuery("getAuthorsByLastName", Author.class);
		query.setParameter("firstName", lastName);
		return query.getResultList();
	}
	
	public List<Author> getAllAuthors() {
		TypedQuery<Author> query = em.createNamedQuery("getAllAuthors", Author.class);
		return query.getResultList();
	}
	
	public Author getAuthorByID(int id) {
		TypedQuery<Author> query = em.createNamedQuery("getAuthorByID", Author.class);
		query.setParameter("id", id);
		if(query.getResultList().size() == 0) return null;
		return query.getSingleResult();
	}
	
}