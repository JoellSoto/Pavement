package com.Pavement.pavement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/addAdmissibility")
	public ResponseEntity <AdmissibilityRecord>postAddAdmissibility(@RequestBody AdmissibilityRecord admissibility) {
		return ResponseEntity.ok(admissibilityService.addAdmissibility(admissibility));
	}
}
