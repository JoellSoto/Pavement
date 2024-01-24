package com.Pavement.pavement.services;

import com.Pavement.pavement.dto.Case1DTO;
import com.Pavement.pavement.entities.Admissibility;
import com.Pavement.pavement.records.AdmissibilityRecord;
import com.Pavement.pavement.records.Case1Record;

public interface AdmissibilityService {

	AdmissibilityRecord addAdmissibility(AdmissibilityRecord admissibility);
	Admissibility getRawInput(int AdmissibilityId);
	Case1DTO getCase1(Admissibility admissibility);
}
