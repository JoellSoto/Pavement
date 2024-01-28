package com.Pavement.pavement.services.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Pavement.pavement.dto.Case1DTO;
import com.Pavement.pavement.dto.Case2DTO;
import com.Pavement.pavement.entities.Admissibility;
import com.Pavement.pavement.entities.CaseTypes;
import com.Pavement.pavement.entities.User;
import com.Pavement.pavement.exceptions.ResourceNotFoundException;
import com.Pavement.pavement.operations.OperationsCase1;
import com.Pavement.pavement.operations.OperationsCase2;
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
	
	
	public AdmissibilityRecord createAdmissibility(AdmissibilityRecord admissibilityRecord) {
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
		admissibility = admissibilityRepository.save(admissibility);
		
		return admissibilityRecord;
	}
	
	
	public Case1DTO createAdmissibilitycase1(AdmissibilityRecord admissibilityRecord) {
		User currentUser= userRepository.findById(admissibilityRecord.userId()).orElseThrow(()-> new ResourceNotFoundException("User Management",HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name() , "User Not Found"));
		Case1DTO case1;		
		Admissibility admissibility=new Admissibility();
		admissibility=addAdmissibility(admissibility, admissibilityRecord,currentUser,CaseTypes.Case1.name());
		case1=getCase1(admissibility);
		return case1;
	}
	
	
	public List<Case1DTO> getAllCase1(int userId){
		List<Admissibility> admissibilities=admissibilityRepository.findAdmissibilities(userId, CaseTypes.Case1.name());
		List<Case1DTO> listCase1=new ArrayList<Case1DTO>();
		
		for (Admissibility admissibility : admissibilities) 
			listCase1.add(getCase1(admissibility));	
		return listCase1;
	}
	

	public AdmissibilityRecord updateAdmissibility(AdmissibilityRecord admissibilityRecord,int admissibilityid) {
		Admissibility admissibility=admissibilityRepository.findById(admissibilityid).orElseThrow(()-> new ResourceNotFoundException("Admissibility Update",HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name() , "Admissibility Not found"));
		
		addAdmissibility(admissibility,admissibilityRecord, admissibility.getUser(),admissibility.getCaseType());
		
		return admissibilityRecord;
			
	}
	
	
	public void deleteAdmissibility(int admissibilityid) {
		admissibilityRepository.deleteById(admissibilityid);
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
			case1.setNr(operationsCase1.nr((int) admissibility.getMovements()));		
		}
		return case1;
		
	}
	
	
	public Case2DTO getCase2(Admissibility admissibility) {
		Case2DTO case2 =new Case2DTO();
		
		OperationsCase2 operationsCase2= new OperationsCase2();
		
		if(admissibility.getPcn()>= admissibility.getAcnM())
		{
			case2.setState("PCN is higher than ACN");
			return case2;
		}
		else {
			case2.setP0(operationsCase2.p0(admissibility.getOem(), admissibility.getMrw(), admissibility.getPcn(), admissibility.getAcnO(), admissibility.getAcnM()));	
			case2.setpLinha(operationsCase2.PLinha(admissibility.getMrw(),admissibility.getArea()));
			case2.setN(operationsCase2.n(0, 0));
			
		}
		
		return case2;
	}
	
	
	private Admissibility addAdmissibility(Admissibility admissibility, AdmissibilityRecord admissibilityRecord,User currentUser,String CaseType) {
		

		admissibility.setAcnM(admissibilityRecord.acnM());
		admissibility.setAcnO(admissibilityRecord.acnO());
		admissibility.setAircraft(admissibilityRecord.aircraft());
		admissibility.setArea(admissibilityRecord.area());
		admissibility.setUser(currentUser);
		admissibility.setCaseType(CaseTypes.Case1.name());
		admissibility.setMovements(admissibilityRecord.movements());
		admissibility.setMrw(admissibilityRecord.mrw());
		admissibility.setPavement(admissibilityRecord.pavement());
		admissibility.setPcn(admissibilityRecord.pcn());
		admissibility = admissibilityRepository.save(admissibility);
		
		return admissibility;
	}
	
	public Admissibility getRawInput(int AdmissibilityId) {
		return admissibilityRepository.findById(AdmissibilityId).orElseThrow(()-> new ResourceNotFoundException("Admissibility Input",HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name() , "Admissibility input data not found"));
	}


}