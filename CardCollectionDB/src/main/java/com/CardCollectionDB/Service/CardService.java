package com.CardCollectionDB.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardCollectionDB.Entity.Card;
import com.CardCollectionDB.Entity.Deck;
import com.CardCollectionDB.Repository.CardRepository;

@Service
public class CardService {
	@Autowired
	private CardRepository repository;
	
	public Card getCardByCardname(String id) {
		return repository.findCardByCardname(id);
	}
	
	public List<Card> getAllByCardname(String set) {
		return repository.findAllByCardname(set); 
	}
	
	public List<Card> getCardBySet(String set) {
		return repository.findAllByCardset(set); 
	}
}
