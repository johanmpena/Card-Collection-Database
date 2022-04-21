package com.CardCollectionDB.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CardCollectionDB.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
