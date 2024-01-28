package com.Pavement.pavement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Pavement.pavement.entities.Admissibility;

@Repository
public interface AdmissibilityRepository  extends JpaRepository<Admissibility,Integer>{

	Admissibility save(Admissibility admissibility);
	
	@Query("select a from Admissibility a where a.user.id = :userId and a.caseType = :caseType")
	List<Admissibility> findAdmissibilities(@Param("userId")int userId, @Param("caseType") String caseType);
}
