package nl.workingtalent.wtacademy.bookcopy;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import nl.workingtalent.wtacademy.book.Book;
import nl.workingtalent.wtacademy.loan.Loan;
import nl.workingtalent.wtacademy.user.User;

@Entity
public class BookCopy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private State state;

	private String WTId;

	@ManyToOne
	private Book book;

	@OneToMany(mappedBy = "bookCopy")
	private List<Loan> loans;
	
	private boolean isAvailable;

	private LocalDateTime dateAdded;

	@ManyToOne
	private User addedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	
	public String getWTId() {
		return WTId;
	}

	public void setWTId(String wTId) {
		WTId = wTId;
	}

	public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}
}
