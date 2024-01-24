package com.Pavement.pavement.records;

public record Case1Record(String state,float p0,float pLinha,float tLinha,float nr) {

	public Case1Record() {
		this("",0, 0, 0, 0);
	}

}
