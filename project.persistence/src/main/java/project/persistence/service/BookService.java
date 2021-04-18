package project.persistence.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import project.persistence.dao.IBookDAO;
import project.persistence.dto.TOBook;
import project.persistence.model.Book;

@Stateless
public class BookService {
	
	@Inject @Named("validBookDAO")
    private IBookDAO bookDAO;

    public TOBook editBook(TOBook toBook) {
        Book book = bookDAO.getBookById(toBook.getId());
        
        if(book == null) {
            System.err.println("[EDIT] Failed due to: id \"" + toBook.getId() + "\" not found");
            return toBook;
        }
        
        System.out.println("this:" + toBook.getTitle());
        
        book.setTitle(toBook.getTitle());
        
        System.out.println("second:" + toBook.toString());
        
        return new TOBook (bookDAO.editBook(book));
    }
    
    public void deleteBook(TOBook toBook) throws Exception {
        Book book = bookDAO.getBookById(toBook.getId());
        if(book == null) throw new Exception("[DELETE] Failed duo to: id \"" + toBook.getId() + "\" not found");
        bookDAO.deleteBook(book);
        
    }
}