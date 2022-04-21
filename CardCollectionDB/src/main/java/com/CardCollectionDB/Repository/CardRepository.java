package com.CardCollectionDB.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CardCollectionDB.Entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String>{
	List<Card> findAllByCardset(String cardset);
	
	//Custom query
	@Query(value = "select * from cards c where c.cardname like %:cardname% ", nativeQuery = true)
	List<Card> findAllByCardname(@Param("cardname") String cardname);
	
	Card findCardByCardname(String cardname);
}
