package com.siemens.ats.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;



@Entity
@Builder
@Table(name ="PassengerDetails")
public class  Passenger {
	@Id
	@SequenceGenerator(name = "id",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonIgnore
	private Long id;
	
	@Column(name= " Passenger_Name", nullable = false)
	@NotNull
	@NotBlank
	@Size(min = 3,message= "Name should be atleat 3 charecters")
	@JsonView(Views.MyResponseViews.class)
	private   String passengerName;
	
	@NotBlank
	@Email
	@Column(name = "Emailid",unique = true)
	@JsonView(Views.MyResponseViews.class)
	private String email;
	
	@NotBlank
	@NotNull
	@Column(name= " Train_Name",nullable = false )
	private   String trainName;
	
	@NotNull
	@NotBlank
	@Column(name= "Seat_No",nullable = false)
	private  String seatNumber;
	
	
 
	public   String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public  String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public  String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Passenger() {
		
	}

	public Passenger(Long id, String passengerName, String email, String trainName, String seatNumber) {
		super();
		this.id = id;
		this.passengerName = passengerName;
		this.email = email;
		this.trainName = trainName;
		this.seatNumber = seatNumber;
	}

	
	
	
	

	
	
}
