package com.Pavement.pavement.dto;

public class Case2DTO {
	
	private float p0, pLinha, tLinha, n, weightFactor;
	private String state;
	
	
	public float getP0() {
		return p0;
	}
	public void setP0(float p0) {
		this.p0 = p0;
	}
	public Case2DTO(float p0, float pLinha, float tLinha, float n,float weightFactor, String state) {
		this.p0 = p0;
		this.pLinha = pLinha;
		this.tLinha = tLinha;
		this.n = n;
		this.weightFactor=weightFactor;
		this.state = state;
	}
	public Case2DTO() {
		this(0, 0,0, 0, 0,"");
	}
	public float getpLinha() {
		return pLinha;
	}
	public void setpLinha(float pLinha) {
		this.pLinha = pLinha;
	}
	public float gettLinha() {
		return tLinha;
	}
	public void settLinha(float tLinha) {
		this.tLinha = tLinha;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public float getN() {
		return n;
	}
	public void setN(float n) {
		this.n = n;
	}
	public float getWeightFactor() {
		return weightFactor;
	}
	public void setWeightFactor(float weightFactor) {
		this.weightFactor = weightFactor;
	}
	
	

}
