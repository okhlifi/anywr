package com.anywr.oki.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	@NotBlank(message = "userName is mandatory")
	private String userName;
	@NotBlank(message = "password is mandatory")
	private String password;
	private String firstName;
	private String lastName;
	@Email(message = "Invalid email")
	@NotBlank(message = "Email is mandatory")
	private String email;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public User(String firstName, String lastName, String email, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Current user :" + userName + " [firstName = " + firstName + " , lastName = " + lastName + ", email = "
				+ email + "]";

	}
}