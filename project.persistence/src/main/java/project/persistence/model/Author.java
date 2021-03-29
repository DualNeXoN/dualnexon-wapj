package project.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "getAuthorsByFirstName", query = "SELECT a FROM Author a WHERE a.firstName = :firstName"),
	@NamedQuery(name = "getAuthorsByLastName", query = "SELECT a FROM Author a WHERE a.lastName = :lastName"),
	@NamedQuery(name = "getAuthorByID", query = "SELECT a FROM Author a WHERE a.id = :id"),
	@NamedQuery(name = "getAllAuthors", query = "SELECT a FROM Author a")
})
@Table(name = "author")
public class Author implements Serializable {
	
	private static final long serialVersionUID = -3008802297739675419L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "yearOfBirth")
	private Integer yearOfBirth;
	
	@Column(name = "gender")
	private String gender;
	
	@OneToMany(mappedBy = "author")
	private List<Book> books;
	
	public Author() {
		books = new ArrayList<Book>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(EGender gender) {
		this.gender = gender.string;
	}
	
}