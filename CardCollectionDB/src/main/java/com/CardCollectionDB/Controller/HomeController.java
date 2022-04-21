package com.CardCollectionDB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CardCollectionDB.Entity.Card;
import com.CardCollectionDB.Entity.Deck;
import com.CardCollectionDB.Entity.User;
import com.CardCollectionDB.Service.CardService;
import com.CardCollectionDB.Service.UserService;

@Controller
@RequestMapping("/")
public class HomeController implements ErrorController{
	String currentSet = null;
	
	@Autowired
	UserService service;
	
	@Autowired
	CardService cardService;



    public String getErrorPath() {
        return "sign-in";
    }
    
	@GetMapping("/index")
	@ResponseBody
	public String getHomePage() {
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

	
	/* NEW SIGN IN CODE */
	@GetMapping("/sign-in")
	public String getSignIn(Model model) {
		model.addAttribute("user", new User());
		return "sign-in";
	}
	
	@PostMapping("/currentUser")
	public String getCurrentUser(@ModelAttribute User user, RedirectAttributes redirect) {
		
		User check = service.getUserById(user.getEmail());
		if(check == null) {
			System.out.println("GOT NULL JOHAN");
			return "sign-in";
		}
		else if(check.equals(user)) {
			System.out.println("GOT A MATCH!");
			redirect.addFlashAttribute("user", user);
			return "redirect:/user/index";
		}
		else {
			System.out.println("NO LUCK");
			return "sign-in";
		}
	}
	
	@GetMapping("/sign-up")
	public String getSignUp(Model model){
		
		model.addAttribute("user", new User());
		return "sign-up";
	}
	
	@PostMapping("/sign-up")
	public String getSignUp(@ModelAttribute User user) {
		user = service.saveUser(user);
		return "sign-in";
	}
	
	@GetMapping("/cards")
	public String getCardsPage(Model model) {
		List<Card> cards = cardService.getCardBySet("Kanugawa");
		model.addAttribute("cards", cards);
		return "home-cards";
	}
}
