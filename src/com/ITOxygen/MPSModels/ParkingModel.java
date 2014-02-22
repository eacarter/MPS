package com.ITOxygen.MPSModels;

import java.util.Date;

import com.parse.ParseObject;

public class ParkingModel {
 
	public int number;
	
	public int state;
	
	public Date updated;
	
	public String id;
	
	public ParkingModel(ParseObject object){
		this.number = object.getInt("ParkingLot");
		this.state = object.getInt("Spaces");
		this.updated = object.getUpdatedAt();
		this.id = object.getObjectId();
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

