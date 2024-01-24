package com.Pavement.pavement.services.impl;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Pavement.pavement.dto.Case1DTO;
import com.Pavement.pavement.entities.Admissibility;
import com.Pavement.pavement.entities.User;
import com.Pavement.pavement.exceptions.ResourceNotFoundException;
import com.Pavement.pavement.operations.OperationsCase1;
import com.Pavement.pavement.records.AdmissibilityRecord;
import com.Pavement.pavement.records.Case1Record;
import com.Pavement.pavement.repositories.AdmissibilityRepository;
import com.Pavement.pavement.repositories.UserRepository;
import com.Pavement.pavement.services.AdmissibilityService;

@Service
public class AdmissibilityServiceImpl implements AdmissibilityService{

	private final UserRepository userRepository;
	private final AdmissibilityRepository admissibilityRepository; 
	
	public AdmissibilityServiceImpl(UserRepository userRepository, AdmissibilityRepository admissibilityRepository) {
		this.userRepository = userRepository;
		this.admissibilityRepository = admissibilityRepository;	
	}
	
	public AdmissibilityRecord addAdmissibility(AdmissibilityRecord admissibilityRecord) {
		User currentUser= userRepository.findById(admissibilityRecord.userId()).orElseThrow(()-> new ResourceNotFoundException("User Management",HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name() , "User Not Found"));
		
		Admissibility admissibility=new Admissibility();
		admissibility.setAcnM(admissibilityRecord.acnM());
		admissibility.setAcnO(admissibilityRecord.acnO());
		admissibility.setAircraft(admissibilityRecord.aircraft());
		admissibility.setArea(admissibilityRecord.area());
		admissibility.setUser(currentUser);
		admissibility.setMovements(admissibilityRecord.movements());
		admissibility.setMrw(admissibilityRecord.mrw());
		admissibility.setPavement(admissibilityRecord.pavement());
		admissibility.setPcn(admissibilityRecord.pcn());
		admissibilityRepository.save(admissibility);
		
		return admissibilityRecord;
	}
	
	public Case1DTO getCase1(Admissibility admissibility) {
		Case1DTO case1 =new Case1DTO();
		OperationsCase1 operationsCase1= new OperationsCase1();
		
		if(admissibility.getPcn()>= admissibility.getAcnM())
		{
			case1.setState("PCN is higher than ACN");
			return case1;
		}
		else {
			case1.setP0(operationsCase1.p0(admissibility.getOem(), admissibility.getMrw(), admissibility.getPcn(), admissibility.getAcnO(), admissibility.getAcnM()));	
			case1.setNr((int)admissibility.getMovements());
			case1.setpLinha(operationsCase1.PLinha(admissibility.getArea(),admissibility.getMrw()));
			
			if(case1.getP0()>=case1.getpLinha())
				case1.setState("P0 > pLinha");
			else {
				
			}
		}
		
		return case1;
		
	}
	
	public Admissibility getRawInput(int AdmissibilityId) {
		return admissibilityRepository.findById(AdmissibilityId).orElseThrow(()-> new ResourceNotFoundException("Admissibility Input",HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name() , "Admissibility input data not found"));
	}


}