package com.CardCollectionDB.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CardCollectionDB.Entity.Card;
import com.CardCollectionDB.Entity.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Integer>{

//	void saveDeckById(Deck deck);
	
	List<Deck> findByUser(String deckname);
	
	List<Card> findAllCardsById(int id);
	
	Deck getDeckById(int id);
}
