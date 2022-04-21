package com.CardCollectionDB.Entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "user")
public class User {
	@Id
	private String email;
	private String password;
	
	public User() {
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	/* GETTERS */
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	/* SETTERS */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/* EQUALS MEHTOD */
	public boolean equals(User user) {
		if((this.password.equals(user.getPassword())) && this.email.equals(user.getEmail())) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
