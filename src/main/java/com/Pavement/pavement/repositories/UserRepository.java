package com.Pavement.pavement.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Pavement.pavement.entities.User;


public interface UserRepository extends JpaRepository<User,Integer> {

	Optional<User> findByEmail(String email);
	
}
