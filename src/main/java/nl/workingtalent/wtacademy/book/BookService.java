package nl.workingtalent.wtacademy.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private IBookRepository repository;
	
	
	public List<Book> getAllBooks(){
		// Bouw een filtering + sortering
		Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "title"));
		
		// Vind allemaal met de filtering erbij
		Page<Book> page = repository.findAll(pageable);
		
		// Geef de lijst terug
		return page.toList();
	}
	
	public Optional<Book> getBookById(long id){
		return repository.findById(id);
	}
	
	public void addBook(Book book) {
		repository.save(book);
	}
	
	public void updateBook(Book book) {
		repository.save(book);
	}
	
	public void deleteBookById(long id) {
		repository.deleteById(id);
	}
}
