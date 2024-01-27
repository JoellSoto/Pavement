package com.Pavement.pavement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Pavement.pavement.entities.Admissibility;

@Repository
public interface AdmissibilityRepository  extends JpaRepository<Admissibility,Integer>{

	Admissibility save(Admissibility admissibility);
	
	
	@Query("select a from Admission a where :user in a.users and a.casetype = :casetype")
	List<Admissibility> getAdmissibilities();
}
