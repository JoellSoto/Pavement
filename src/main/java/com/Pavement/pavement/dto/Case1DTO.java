package com.Pavement.pavement.dto;

public class Case1DTO {
	
	private float p0, pLinha, tLinha, nr;
	private String state;
	
	
	public float getP0() {
		return p0;
	}
	public void setP0(float p0) {
		this.p0 = p0;
	}
	public Case1DTO(float p0, float pLinha, float tLinha, float nr, String state) {
		this.p0 = p0;
		this.pLinha = pLinha;
		this.tLinha = tLinha;
		this.nr = nr;
		this.state = state;
	}
	public Case1DTO() {
		this(0, 0, 0, 0,"");
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
	public float getNr() {
		return nr;
	}
	public void setNr(float nr) {
		this.nr = nr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
