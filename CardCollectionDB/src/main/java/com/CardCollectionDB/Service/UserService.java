package com.CardCollectionDB.Service;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardCollectionDB.Entity.User;
import com.CardCollectionDB.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public User getUserById(String email) {
		return repository.findById(email).orElse(null);
	}
}
