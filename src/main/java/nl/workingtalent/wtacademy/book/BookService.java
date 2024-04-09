package nl.workingtalent.wtacademy.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private IBookRepository repository;
	
	
	public List<Book> getAllBooks(){
		return repository.findAll();
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

	public List<Book> searchBookByTitle(String title) {		
		return repository.findByTitleContaining(title);
	}
	
	public List<Book> searchByCategories(List<String> categories){
		return repository.findByCategoriesIn(categories);
	}
	
	public List<Book> searchByAuthors(List<String> authors){
		return repository.findByAuthors(authors);
	}
	
	public List<Book> getByIds(List<Long> ids){
		return repository.findByIdIn(ids);
	}
}
