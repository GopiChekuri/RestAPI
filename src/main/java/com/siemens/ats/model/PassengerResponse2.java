package com.siemens.ats.model;

public class PassengerResponse2 {
	private   String passengerName;
	private String email;
	private   String trainName;
	private  String seatNumber;
	
	
	public PassengerResponse2(String passengerName, String email, String trainName, String seatNumber) {
		super();
		this.passengerName = passengerName;
		this.email = email;
		this.trainName = trainName;
		this.seatNumber = seatNumber;
	}
	
	public PassengerResponse2() {
		super();
	}

	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getEmail () {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
		
	
	
}
