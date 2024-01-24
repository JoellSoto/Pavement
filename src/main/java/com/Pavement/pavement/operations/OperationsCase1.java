package com.Pavement.pavement.operations;

public class OperationsCase1 {
	public float nr(int mov) {
		
		return (float) (mov/0.05);
	}
	
	
	public boolean codeFRm(float pcn,float acn,float code) {
		//code 1.10 or 1.05
		if(pcn*code>=acn && acn <pcn )
			return true;
		return false;
	}
	
	
	public boolean codeFRM(float pcn,float acn,float code) {
		//code 1.10 or 1.05
		if(pcn*code<acn )
			return true;
		return false;
	}
	
	
	public float p0(float oew,float mrw,float pcn,float acnO,float acnM) {
		return oew +(mrw-oew)*((pcn-acnO)/(acnM-acnO));
	}
	
	public float PLinha(float alfa,float pt ) {
        return alfa*pt;		
	}
	
	public float tLinha (float n, float pLinha,float p0) {
		
		float exp=5*((pLinha/p0)-1);
		return n*(float)Math.pow(10,exp);
		
	} 
}
