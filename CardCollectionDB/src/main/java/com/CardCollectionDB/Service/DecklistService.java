package com.CardCollectionDB.Service;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardCollectionDB.Entity.Decklist;
import com.CardCollectionDB.Repository.DecklistRepository;

@Service
public class DecklistService {
	@Autowired
	private DecklistRepository repository;
	
	public void saveDeck(Decklist list) {
		repository.save(list);
	}
	
	public void deleteFromList(int list) {
		repository.deleteById(list);
	}
	
	public List<Decklist> getList(String email, String deckname){
		return repository.findByEmailAndDeckname(email, deckname);
	}
}
