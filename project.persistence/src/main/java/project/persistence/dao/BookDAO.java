package project.persistence.dao;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import project.persistence.model.Book;
import javax.inject.Named;

@Named
@Stateless
public class BookDAO implements IBookDAO {
	
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
	
	public List<Book> findBooksByTitle(String value) {
		TypedQuery<Book> query = em.createNamedQuery("findBooksByTitle", Book.class);
		query.setParameter("title", value);
		return query.getResultList();
	}

	@Override
	public Book createBook(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Book editBook(Book book) {
		// Ospravedlnujem sa, ale neviem, co je momentalne myslene pod akciou "edit". (kod doplnim neskor)
		// TODO Doplnit editBook logiku
		return null;
	}

	@Override
	public void deleteBook(Book book) {
		System.out.println("Mazem instanciu");
		em.remove(book);
	}

	@Override
	public Book getRandomBook() {
		List<Book> books = getAllBooks();
		int randomNum = new Random().nextInt(((books.size()-1) - 0) + 1) + 0;
		return books.get(randomNum);
	}

	@Override
	public Book getBookById(Integer id) {
		TypedQuery<Book> query = em.createNamedQuery("getBookByID", Book.class);
		query.setParameter("id", id);
		return query.getResultList().get(0);
	}

	@Override
	public List<Book> getAllBooks() {
		TypedQuery<Book> query = em.createNamedQuery("getAllBooks", Book.class);
		return query.getResultList();
	}
	
}