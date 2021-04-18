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
import project.persistence.model.Author;
import project.persistence.model.EGender;

@ViewScoped
@Named
public class NewAuthorController implements Serializable {
	
	private static final long serialVersionUID = 1537087746952895843L;
	
	private String firstName;
	private String lastName;
	private int yearOfBirth = 1980;

	@Inject
	private AuthorDAO authorDAO;

	public void addAuthor() {
		
		if(firstName.length() == 0 || lastName.length() == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Chyba pri vykonávaní akcie");
	        PrimeFaces.current().dialog().showMessageDynamic(message);
	        return;
		}
		
		Author author = new Author();
		
		author.setFirstName(firstName);
		author.setLastName(lastName);
		author.setCountry(null);
		author.setYearOfBirth(yearOfBirth);
		author.setGender(EGender.WTF);
		
		authorDAO.create(author);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Operácia úspešne vykonaná");
        PrimeFaces.current().dialog().showMessageDynamic(message);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String mainPage() {
		return "index.xhtml?faces-redirect=true";
	}
	
	public void refresh() throws IOException {
    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }


}