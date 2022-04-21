package com.CardCollectionDB.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CardCollectionDB.Entity.Decklist;

@Repository
public interface DecklistRepository extends JpaRepository<Decklist, Integer>{

	List<Decklist> findByEmailAndDeckname(String email, String deckname);
}
