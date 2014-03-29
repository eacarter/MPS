package com.ITOxygen.MPSModels;

import java.util.Date;

import com.parse.ParseObject;

public class ParkingModel {
 
	public int number;
	
	public int state;
	
	public Date updated;
	
	public String id;
	
	public String lotType;
	
	public ParkingModel(ParseObject object){
		this.number = object.getInt("ParkingLot");
		this.state = object.getInt("Spaces");
		this.updated = object.getUpdatedAt();
		this.id = object.getObjectId();
		this.lotType = object.getString("Type");
	}
	public String getLotType() {
		return lotType;
	}
	public void setLotType(String lotType) {
		this.lotType = lotType;
	}
	public int getNumber(){
		return number;
	}
	
	public int getState(){
		return state;
	}
	
	public Date getUpdated(){
		return updated;
	}
	
	public void setState(int s){
		state = s;
	}
	
}

