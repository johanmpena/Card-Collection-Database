package com.CardCollectionDB.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CardCollectionDB.Entity.Card;
import com.CardCollectionDB.Entity.Deck;
import com.CardCollectionDB.Entity.Decklist;
import com.CardCollectionDB.Entity.User;
import com.CardCollectionDB.Service.DeckService;
import com.CardCollectionDB.Service.DecklistService;
import com.CardCollectionDB.Service.CardService;

@Controller
@Scope("singleton")
@RequestMapping("/user")
public class UserController {
	User currentUser = null;
	Deck deckEdit = null;
	String currentSet = null;
	String currentDeck = null;
	
	/* USE TO KEEP A TEMP DECK UNTIL 
	 * USER SAVES, THEN COMMIT TO DATABASE
	 * WHEN USER HITS SAVE! */
	List<Card> tempDeck = new ArrayList<Card>();
	
	@Autowired
	DeckService service;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	DecklistService decklistService;
	
	@GetMapping("/index")
	public String getIndexPage(@ModelAttribute("user") User data, RedirectAttributes redirect) {
		/* SET CURRENT USER */
		System.out.println("In getIndexPage in User controller: " + data);
		if(currentUser == null) {
			currentUser = data;
		}
		return "index";
	}
	
	@GetMapping("/sets")
	public String getSetPage() {
		return "sets";
	}
	
	@GetMapping("/search")
	public String getSearchPage(Model model, String card) {
		
		if(card != null) {
			System.out.println("IN SEARCH: " + card);
			List<Card> cards = cardService.getAllByCardname(card);
			model.addAttribute("cards", cards);
		}
		return "search";
	}
	
	@GetMapping("/decks")
	public String getDeckPage(Model model) {
		List<Deck> decks = service.getDeckByUsers(currentUser.getEmail());
		System.out.println("IN USER CONTROLLER, GOING TO PRINT LIST.");
		for(Deck name: decks) {
			System.out.println(name.getDeckname());
		}
		model.addAttribute("decks", decks);
		
		return "decks";
	}
	
	@GetMapping("/createDeck")
	public String createDeck(Model theModel) {
		/* CREATE MODEL ATTRIBUTE TO BIND FORM DATA */
		Deck deck = new Deck();
		
		/* ADD THE DECK TO THE MODEL */
		theModel.addAttribute("decks", deck);
		
		return "createDeck";
	}
	
	@PostMapping("/saveDeck")
	public String saveDeck(@ModelAttribute("decks") Deck theDeck){
		
		Deck newDeck = new Deck(theDeck.deckname, currentUser.getEmail());
		newDeck = service.saveDeck(newDeck);
		
		return "redirect:/user/decks";
	}
	
	@GetMapping("/updateDeck")
	public String update() {
		System.out.println("In update deck!");
		
		
		for(Card name: tempDeck) {
			Decklist deck = new Decklist(currentUser.getEmail(), name.getCardname(), deckEdit.deckname);
			
			decklistService.saveDeck(deck);
		}
		return "redirect:/user/decks";
	}
	
	@GetMapping("/backToList")
	public String removeFromDeck( RedirectAttributes redirect) {

		
		return "redirect:/user/decks";
	}
	
	@GetMapping("/remove")
	public String removeFromDeck(@RequestParam int id, RedirectAttributes redirect) {
		decklistService.deleteFromList(id);
//		
		System.out.println("IN REMOVE");
		System.out.println(currentDeck);
		redirect.addAttribute("theDeck", currentDeck);
		
		return "redirect:/user/deckList";
	}
	
	@GetMapping("/deckList")
	public String deckList(@RequestParam String theDeck, Model model) {
		/* PRINT OUT DECK LIST */
		System.out.println(currentDeck);
		List<Decklist> list = decklistService.getList(currentUser.getEmail(), theDeck);
		currentDeck = theDeck;
		model.addAttribute("cards", list);
		
		return "deckList";
	}
	
	@GetMapping("/deleteDeck")
	public String deleteDeck(@RequestParam int theDeck) {

		System.out.println(theDeck);
		service.deleteDeckById(theDeck);
		
		return "redirect:/user/decks";
	}
	
	@GetMapping("editDeck")
	public String updateDeck(@RequestParam int theDeck){
		/* JOHAN STOP HERE */
		/* SET deckEdit TO WHATEVER WAS PASSED */
		deckEdit = service.getDeckById(theDeck);
		tempDeck = new ArrayList<Card>();
		
		/* AFTER ADD OR UPDATE THE LIST */
		/* THEN WANT TO USE A SAVE LIST FUNCTION */
		return "redirect:/user/sets";
	}
	
	@GetMapping("addCard")
	public String addCard(@RequestParam String cardname) {
		
		Card card = cardService.getCardByCardname(cardname);
	
		tempDeck.add(card);
		
		return "redirect:/user/cards";
	}
	
	@GetMapping("/cards")
	public String getCardsPage(Model model) {
		List<Card> cards = cardService.getCardBySet("Kanugawa");
		model.addAttribute("cards", cards);
		return "cards";
	}
	
	@GetMapping("/sign-in")
	public String getSignoutPage() {
		currentUser = null;
		deckEdit = null;
		currentSet = null;
		currentDeck = null;
		tempDeck = null;
		return "redirect:/";
	}
}
