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

import project.persistence.dao.IBookDAO;
import project.persistence.dto.TOBook;
import project.persistence.model.Book;

@Named("bookController")
@ViewScoped
public class BookController implements Serializable {
	
	private static final long serialVersionUID = -2940249706037322191L;
	
	@Inject @Named("validBookDAO")
	private IBookDAO bookDAO;
	
	private List<TOBook> list = new ArrayList<TOBook>();
	
	@PostConstruct
	private void init() {
		list = bookDAO.getAllBooksTO();
	}
	
	public List<TOBook> getList() {
		return list;
	}
	
	public void editBook(TOBook toBook) {
		toBook.setEditMode(true);
	}
	
	public void saveBook(TOBook toBook) {
		toBook.setEditMode(false);
		Book book = bookDAO.getBookById(toBook.getId());
		if(book != null) {
			book.setTitle(toBook.getTitle());
			bookDAO.editBook(book);
		}
	}
	
	public void deleteBook(TOBook toBook) {
		Book book = bookDAO.getBookById(toBook.getId());
		
		bookDAO.deleteBook(book);
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String newBook() {
    	return "newBook.xhtml?faces-redirect=true";
    }
	
	public void refresh() throws IOException {
    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
	
}