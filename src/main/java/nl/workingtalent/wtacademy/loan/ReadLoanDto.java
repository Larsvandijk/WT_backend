package nl.workingtalent.wtacademy.loan;

import java.time.LocalDate;
import nl.workingtalent.wtacademy.book.Book;
import nl.workingtalent.wtacademy.bookcopy.BookCopy;
import nl.workingtalent.wtacademy.bookcopy.State;
import nl.workingtalent.wtacademy.reservation.Reservation;

public class ReadLoanDto {

	private long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private State conditionStart;
	private State conditionEnd;
	private long userId;
	private Long reservationId;
	private Long bookCopyId;
	private Long bookId;
	private Boolean isActive;

	public ReadLoanDto(Loan loan) {
		id = loan.getId();
		startDate = loan.getStartDate();
		endDate = loan.getEndDate();
		conditionStart = loan.getConditionStart();
		conditionEnd = loan.getConditionEnd();
		userId = loan.getUser().getId();
		isActive = loan.isActive();
		// reservation en bookcopy moeten null kunnen zijn
		Reservation reservation = loan.getReservation();
		reservationId = (reservation != null) ? reservation.getId() : null;
		BookCopy bookCopy = loan.getBookCopy();
		bookCopyId = (bookCopy != null) ? bookCopy.getId() : null;
		Book book = loan.getBookCopy().getBook();
		bookId = (book != null) ? book.getId() : null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public State getConditionStart() {
		return conditionStart;
	}

	public void setConditionStart(State conditionStart) {
		this.conditionStart = conditionStart;
	}

	public State getConditionEnd() {
		return conditionEnd;
	}

	public void setConditionEnd(State conditionEnd) {
		this.conditionEnd = conditionEnd;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getBookCopyId() {
		return bookCopyId;
	}

	public void setBookCopyId(Long bookCopyId) {
		this.bookCopyId = bookCopyId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
