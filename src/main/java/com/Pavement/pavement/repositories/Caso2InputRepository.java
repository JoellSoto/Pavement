package com.Pavement.pavement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pavement.pavement.entities.Admissibility;

@Repository
public interface Caso2InputRepository  extends JpaRepository <Admissibility,Integer>{

}
