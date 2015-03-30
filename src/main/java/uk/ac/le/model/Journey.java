package uk.ac.le.model;

import javax.persistence.*;

@Entity(name = "journeys")
public class Journey extends BaseModel {

	@Column
	private String firstName;

	@Column
	private String lastName;

    @Column
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

	@Override
	public String toString() {
		return super.toString() + " name = " + firstName + " " + lastName
				+ " id = " + getId();
	}
}
