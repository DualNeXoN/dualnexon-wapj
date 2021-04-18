package project.ui.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import project.persistence.dao.AuthorDAO;
import project.persistence.dao.IBookDAO;
import project.persistence.model.Author;
import project.persistence.model.Book;

@Named("authorController")
@ViewScoped
public class AuthorController implements Serializable {

	private static final long serialVersionUID = -2682569607753961770L;
	
	private String firstName;
	private String lastName;
	private String country;
	private Integer yearOfBirth;
	
	@Inject
	private AuthorDAO authorDAO;
	
	@Inject @Named("validBookDAO")
	private IBookDAO bookDAO;
	
	private List<Author> listAuthors = new ArrayList<Author>();
	
	@PostConstruct
	private void init() {
		listAuthors = authorDAO.getAllAuthors();
	}
	
	public List<String> getList() {
		List<String> list = new ArrayList<String>();
		List<Author> listAuthors = new ArrayList<Author>();
		
		list.add("None");
		
		listAuthors = authorDAO.getAllAuthors();
		for(Author a : listAuthors) {
			list.add(a.getId() + "-" + a.getFirstName() + " " + a.getLastName());
		}
		
		return list;
	}
	
	public List<Author> getListAuthors() {
		return listAuthors;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getCountry() {
		return country;
	}
	
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}
	
	public void deleteAuthor(Author author) {
		
		List<Book> listBooks = bookDAO.getAllBooks();
		for(int index = listBooks.size()-1; index >= 0; index--) {
			Book b = listBooks.get(index);
			if(b.getAuthor().getId() == author.getId()) {
				b.setAuthor(null);
				bookDAO.editBook(b);
				System.out.println("Found book \"" + b.getTitle() + "\" with this author! Author of this book is now NULL");
			}
		}
		
		authorDAO.deleteAuthor(author);
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() throws IOException {
    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
	
	public String newAuthor() {
		return "newAuthor.xhtml?faces-redirect=true";
	}
	
}
