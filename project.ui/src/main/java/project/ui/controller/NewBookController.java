package project.ui.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import project.persistence.dao.AuthorDAO;
import project.persistence.dao.IBookDAO;
import project.persistence.model.Author;
import project.persistence.model.Book;

@ViewScoped
@Named
public class NewBookController implements Serializable {

	private static final long serialVersionUID = -5743367052341108192L;
	
	private String title;
	private String authorItem;
	
	@Inject @Named("validBookDAO")
	private IBookDAO bookDAO;

	@Inject
	private AuthorDAO authorDAO;

	public void addBook() {
		
		if(title.length() == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Chyba pri vykonávaní akcie");
	        PrimeFaces.current().dialog().showMessageDynamic(message);
	        return;
		}
		
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
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Operácia úspešne vykonaná");
        PrimeFaces.current().dialog().showMessageDynamic(message);
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
	
	public String mainPage() {
    	return "index.xhtml?faces-redirect=true";
    }
	
	public void refresh() throws IOException {
    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }


}