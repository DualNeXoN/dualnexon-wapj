package project.ui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import project.persistence.dao.AuthorDAO;
import project.persistence.model.Author;

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
	
	@PostConstruct
	private void init() {}
	
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
	
	public List<Author> getAllAuthors() {
		return authorDAO.getAllAuthors();
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
	
}
