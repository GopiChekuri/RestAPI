package com.siemens.ats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.siemens.ats.model.Passenger;


public interface PassengerRepository  extends JpaRepository<Passenger, Long>{

	

}
