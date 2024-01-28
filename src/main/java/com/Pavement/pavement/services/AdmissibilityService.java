package com.Pavement.pavement.services;

import java.util.List;

import com.Pavement.pavement.dto.Case1DTO;
import com.Pavement.pavement.entities.Admissibility;
import com.Pavement.pavement.records.AdmissibilityRecord;
import com.Pavement.pavement.records.Case1Record;

public interface AdmissibilityService {

	AdmissibilityRecord createAdmissibility(AdmissibilityRecord admissibility);
	Case1DTO createAdmissibilitycase1(AdmissibilityRecord admissibilityRecord);
	AdmissibilityRecord updateAdmissibility(AdmissibilityRecord admissibilityRecord,int admissibilityid);
	void deleteAdmissibility(int admissibilityid) ;
	Admissibility getRawInput(int AdmissibilityId);
	Case1DTO getCase1(Admissibility admissibility);
	List<Case1DTO> getAllCase1(int userId);
}
