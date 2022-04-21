package com.CardCollectionDB.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cards")
public class Card {

	@Id
	public String cardname;
	public String color;
	public String cardnumber;
	public String cardrank;
	public String cardset;
	
	public Card() {
		
	}
	
	public Card(String cardname, String color, String cardnumber, String cardrank, String cardset) {
		this.cardname = cardname;
		this.color = color;
		this.cardnumber = cardnumber;
		this.cardrank = cardrank;
		this.cardset = cardset;
	}
	
	/* GETTERS */
	public String getCardname() {
		return  cardname;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getCardnumber() {
		return cardnumber;
	}
	
	public String getCardrank() {
		return cardrank;
	}
	
	public String getCardset() {
		return cardset;
	}
	
	/* SETTERS */
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	public void setCardrank(String cardrank) {
		this.cardrank = cardrank;
	}
	
	public void setCardset(String cardset) {
		this.cardset = cardset;
	}
}
