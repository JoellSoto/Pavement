package com.Pavement.pavement.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;



@Entity
public class Admissibility {

	@Id
	@SequenceGenerator(
			name="Admissibility_sequence",
			sequenceName="Admissibility_sequence",
			allocationSize =1
			)
	
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator ="Admissibility_sequence"
			)
	private int id;
	
	@Column
	private String aircraft,caseType;
	
	@Column
	private float oem,mrw,pcn,acnM,acnO,movements,area,pavement;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value="admissibility_User")
	@JoinColumn(name = "user_id",nullable=false,unique=false)
	private User user;
	
	public Admissibility(String aircraft, float area, float pavement, float oem, float mrw, float pcn, float acnM,float acnO, float movements) {
		
		this.aircraft = aircraft;
		this.area = area;
		this.pavement = pavement;
		this.oem = oem;
		this.mrw = mrw;
		this.pcn = pcn;
		this.acnM = acnM;
		this.acnO = acnO;
		this.movements = movements;
	}
	
	public Admissibility() {
		this("",0,0,0,0,0,0,0,0);
	}
	
	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public float getPavement() {
		return pavement;
	}
	public void setPavement(float pavement) {
		this.pavement = pavement;
	}
	public float getOem() {
		return oem;
	}
	public void setOem(float oem) {
		this.oem = oem;
	}
	public float getMrw() {
		return mrw;
	}
	public void setMrw(float mrw) {
		this.mrw = mrw;
	}
	public float getPcn() {
		return pcn;
	}
	public void setPcn(float pcn) {
		this.pcn = pcn;
	}
	public float getAcnM() {
		return acnM;
	}
	public void setAcnM(float acnM) {
		this.acnM = acnM;
	}
	public float getAcnO() {
		return acnO;
	}
	public void setAcnO(float acnO) {
		this.acnO = acnO;
	}
	public float getMovements() {
		return movements;
	}
	public void setMovements(float movements) {
		this.movements = movements;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	
}
