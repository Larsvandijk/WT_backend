package nl.workingtalent.wtacademy.book;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import nl.workingtalent.wtacademy.author.Author;
import nl.workingtalent.wtacademy.bookcopy.BookCopy;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String description;
	
	@Column(length = 125)
	private String imageLink;
	
	@Column(length = 11)
	private int publisherId;
	
	private LocalDateTime publishingDate;
	
	@Column(length = 450)
	private String title;

	@ManyToMany(mappedBy = "books")
	private List<Author> authors;

	@OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
	private List<BookCopy> bookCopies;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public LocalDateTime getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(LocalDateTime publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	
	public List<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}
	
	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

}
