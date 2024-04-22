package nl.workingtalent.wtacademy.book;

import java.time.LocalDate;
import java.util.List;

public class CreateBookDto {

	private String title;

	private String description;

	private LocalDate publishingDate;

	private String imageLink;

	private String isbn;

	private List<String> authors;

	private List<String> categories;

	private List<String> states;

	public LocalDate getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public List<String> getStates() {
		return states;
	}

}