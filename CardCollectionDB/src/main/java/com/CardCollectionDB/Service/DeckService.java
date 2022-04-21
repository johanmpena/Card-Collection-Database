package com.CardCollectionDB.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardCollectionDB.Entity.Card;
import com.CardCollectionDB.Entity.Deck;
import com.CardCollectionDB.Repository.DeckRepository;

@Service
public class DeckService {
	@Autowired
	private DeckRepository repository;
	
	public Deck saveDeck(Deck deck) {
		return repository.save(deck);
	}
	
//	public void update(Deck deck) {
//		repository.saveDeckById(deck);
//	}
//	
	public void deleteDeck(Deck deck) {
		repository.delete(deck);
	}
	
	public Deck updateDeck(Deck list) {
		return repository.save(list);
		
	}
	
	public Deck getDeckById(int id) {
		return repository.getDeckById(id);
	}
	
	public void deleteDeckById(int id) {
		repository.deleteById(id);
	}
	
	public List<Deck> getDeckByUsers(String user) {
		return repository.findByUser(user); 
	}
}
