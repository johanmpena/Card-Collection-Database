package com.CardCollectionDB.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "decklist")
public class Decklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String email;
	public String cardname;
	public String deckname;
	
	public Decklist() {
		
	}
	
	public Decklist(String email, String cardname, String deckname) {
		this.email = email;
		this.cardname = cardname;
		this.deckname = deckname;
	}
	
	
	/* GETTERS */
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCardname() {
		return cardname;
	}
	
	public String getDeckname() {
		return deckname;
	}
	
	/* SETTERS */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	
	public void setDeckname(String deckname) {
		this.deckname = deckname;
	}
}
