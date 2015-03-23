package uk.ac.le.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "journeys")
public class Journey extends BaseModel {

	@Column
	private String firstName;

	@Column
	private String lastName;

	public Journey() {
	}

	public Journey(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return super.toString() + " name = " + firstName + " " + lastName
				+ " id = " + getId();
	}
}
