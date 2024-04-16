package nl.workingtalent.wtacademy.loan;

import java.time.LocalDate;

public class CreateLoanDto {

	private LocalDate startDate;
	private LocalDate endDate;
	private String conditionStart;
	private String conditionEnd;
	private long userId;

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

	public String getConditionStart() {
		return conditionStart;
	}

	public void setConditionStart(String conditionStart) {
		this.conditionStart = conditionStart;
	}

	public String getConditionEnd() {
		return conditionEnd;
	}

	public void setConditionEnd(String conditionEnd) {
		this.conditionEnd = conditionEnd;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
