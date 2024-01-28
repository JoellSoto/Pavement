package com.Pavement.pavement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pavement.pavement.dto.Case1DTO;
import com.Pavement.pavement.entities.Admissibility;
import com.Pavement.pavement.records.AdmissibilityRecord;
import com.Pavement.pavement.services.AdmissibilityService;

@RestController
@RequestMapping("/api/v1/admissibility")
public class AdmissibilityController {

	private AdmissibilityService admissibilityService;
	
	@Autowired
	public AdmissibilityController(AdmissibilityService admissibilityService) {
		this.admissibilityService=admissibilityService;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Admissibility> getRawInput(@PathVariable("id") int admissibilityId){
		return ResponseEntity.ok(admissibilityService.getRawInput(admissibilityId));
	}
	
	@PostMapping("/create-admissibility-case1")
	public ResponseEntity <Case1DTO>postcreateAdmissibilitycase1(@RequestBody AdmissibilityRecord admissibility) {
		return ResponseEntity.ok(admissibilityService.createAdmissibilitycase1(admissibility));
	}
	
	@GetMapping("/get-admissibility-case1/{userId}")
	public List<Case1DTO> getAdmissibilityCase1(@PathVariable("userId") int userId){
		return admissibilityService.getAllCase1(userId);
	}
	
	
}
