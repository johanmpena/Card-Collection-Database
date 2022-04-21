package com.CardCollectionDB.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="decks")
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String deckname;
	public String user;
	
	public Deck() {
	}
	
	public Deck(String deckname, String user) {
		this.deckname = deckname;
		this.user = user;
	}
	
	/* GETTERS */
	public int getId() {
		return id;
	}
	
	public String getDeckname() {
		return deckname;
	}
	
	public String getUser() {
		return user;
	}
	
	/* SETTERS */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDeckname(String deckname) {
		this.deckname = deckname;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
}
