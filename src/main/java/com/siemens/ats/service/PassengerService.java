package com.siemens.ats.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.siemens.ats.model.Passenger;
import com.siemens.ats.model.PassengerResponse2;

@Service
@Component("PassengerService")
public interface PassengerService {

	Passenger savePassenger(Passenger passenger);

	List<Passenger> getAllPassengers();

	Passenger getPassengerById(Long id);

	Passenger updatePassenger(Passenger passenger);

	Passenger deletePassenger(Long id);

	List<Passenger> findPassengerWithSorting(String field);

	List<PassengerResponse2> getPassengersWithoutid();

	List<Map<String, Object>> getPassengerFields();

	// All this methods are implements in PassengerServiceImp class

}
