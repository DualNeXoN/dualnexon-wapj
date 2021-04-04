package project.persistence.dto;

import project.persistence.model.Author;
import project.persistence.model.Book;

public class TOBook {
	
	private Integer id;
	private String title;
	private Author author;
	private boolean editMode;
	
	public TOBook(Book book) {
		id = book.getId(); 
		title = book.getTitle();
		author = book.getAuthor();
		editMode = false;
	}
	
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
}
