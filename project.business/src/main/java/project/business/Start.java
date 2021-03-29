package project.business;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Start {
	
	/*
	@Inject @Named("validBookDAO")
	private IBookDAO bookDAO;
	
	@Inject
	private AuthorDAO authorDAO;
	*/
	
	@PostConstruct
	private void init() {
		
	}
	
}