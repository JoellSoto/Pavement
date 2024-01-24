package com.Pavement.pavement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pavement.pavement.entities.Roles;


public interface RoleRepository  extends JpaRepository<Roles,Integer>{

	Optional <Roles> findByName(String string);

}
