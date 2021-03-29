package project.ui.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import project.persistence.dao.AuthorDAO;
import project.persistence.dao.IBookDAO;
import project.persistence.model.Author;
import project.persistence.model.Book;

@Named("bookController")
@ViewScoped
public class BookController implements Serializable {
	
	private static final long serialVersionUID = -2940249706037322191L;
	
	private String title;
	private String authorItem;
	
	@Inject @Named("validBookDAO")
	private IBookDAO bookDAO;
	
	@Inject
	private AuthorDAO authorDAO;
	
	@PostConstruct
	private void init() {}
	
	public void addBook() {
		
		Book book = new Book();
		
		Author author = null;
		try{
			author = authorDAO.getAuthorByID(getIdFromAuthor());
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		
		book.setTitle(title);
		book.setAuthor(author);
		bookDAO.createBook(book);
		
		System.out.println("Adding book with title: " + title);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthorItem() {
		return authorItem;
	}
	
	public void setAuthorItem(String authorItem) {
		this.authorItem = authorItem;
	}
	
	private int getIdFromAuthor() {
		int id = Integer.parseInt((authorItem).split("-")[0]);
		if(id >= 0) return id;
		else return -1;
	}
	
}