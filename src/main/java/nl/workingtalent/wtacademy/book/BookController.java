package nl.workingtalent.wtacademy.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.wtacademy.author.AuthorService;

@RestController
@CrossOrigin(maxAge = 3600)
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping("books/all")
	public List<Book> getAllBooks(){
		return service.getAllBooks();
	}
	
	@RequestMapping("books/{id}")
	public Optional<Book> getBookById(@PathVariable("id") int id){
		return service.getBookById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "books/create")
	public void addBook(@RequestBody SaveBookDto saveBookDto) {
		
		Book dbBook = new Book();
		dbBook.setTitle(saveBookDto.getTitle());
		dbBook.setDescription(saveBookDto.getDescription());
//		dbBook.setImageLink(newBook.get("imageLink").asText());
//		dbBook.setPublisherId(newBook.get("publisherId").asInt());
//		dbBook.setPublishingDate(LocalDateTime.parse(newBook.get("publishingDate").asText()));
		
		// Authors langs gaan
		for (String authorName : saveBookDto.getAuthors()) {
			// Bestaat de author al in de db -> voeg author toe aan boek
			// Niet bestaat dan aanmaken en toevoegen aan lijst authord

//			authorService.addAuthor(author);
		}
		
		service.addBook(dbBook);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "books/update/{id}")
	public boolean updateBook(@RequestBody Book newBook, @PathVariable("id") int id) {
		
		Optional<Book> optional = service.getBookById(id);
		
		if(optional.isEmpty()) {
			return false;
		}
		
		Book book = optional.get();
		
		//Check whether all data is filled
		book.setDescription(newBook.getDescription());
		book.setImageLink(newBook.getImageLink());
		book.setPublisherId(newBook.getPublisherId());
		book.setPublishingDate(newBook.getPublishingDate());
		book.setTitle(newBook.getTitle());
		
		service.updateBook(book);
		return true;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "books/delete/{id}")
	public void deleteBookById(@PathVariable("id") int id) {
		service.deleteBookById(id);
	}
}
