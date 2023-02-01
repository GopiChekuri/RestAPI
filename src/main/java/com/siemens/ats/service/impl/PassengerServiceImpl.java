package com.siemens.ats.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.siemens.ats.exception.ResourceNotFoundException;
import com.siemens.ats.exception.SqlException;
import com.siemens.ats.model.Passenger;
import com.siemens.ats.model.PassengerResponse2;
import com.siemens.ats.repository.PassengerRepository;
import com.siemens.ats.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

//passing passengerRepository as a constructor for injection	 
	private PassengerRepository passengerRepository;

	public PassengerServiceImpl(PassengerRepository passengerRepository) {
		super();
		this.passengerRepository = passengerRepository;
	}

	// Method to create a passenger data
	@Override
	public Passenger savePassenger(Passenger passenger) {

		try {
			passengerRepository.save(passenger);
		} catch (DataIntegrityViolationException ex) {

			throw new SqlException("Entered email was already exists");
		}
		return passenger;
	}

	// List to get all passengers
	@Override
	public List<Passenger> getAllPassengers() {
		return passengerRepository.findAll();

	}

	@Override // to get a passenger by his id
	public Passenger getPassengerById(Long id) {
		return passengerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger", "Id", id));
	}

	@Override // update a existing passenger
	public Passenger updatePassenger(Passenger passenger) {
		Passenger existingPassenger;
		try { // we need to check passenger id with existing database
			existingPassenger = passengerRepository.findById(passenger.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Passenger", "Id", passenger.getId()));
			existingPassenger.setPassengerName(passenger.getPassengerName());
			existingPassenger.setTrainName(passenger.getTrainName());
			existingPassenger.setSeatNumber(passenger.getSeatNumber());
			existingPassenger.setEmail(passenger.getEmail());
			passengerRepository.save(existingPassenger);
		} catch (DataIntegrityViolationException ex) {

			throw new SqlException("Entered email was already exists");
		}
		return existingPassenger;
	}

	// delete pssenger by his id
	@Override
	public Passenger deletePassenger(Long id) {
		// check weather a passenger is present in database or not
		passengerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger", "id", id));
		passengerRepository.deleteById(id);
		return null;

	}

	// get passenger by sorting
	@Override
	public List<Passenger> findPassengerWithSorting(String field) {

		return passengerRepository.findAll(Sort.by(Sort.Direction.ASC, field));

	}

	// get passenger without id
	@Override
	public List<PassengerResponse2> getPassengersWithoutid() {
		List<Passenger> passengers = passengerRepository.findAll();
		List<PassengerResponse2> passengerResponseList = new ArrayList<>();
		PassengerResponse2 passengerResponse;
		for (Passenger e : passengers) {

			passengerResponse = new PassengerResponse2();
			passengerResponse.setEmail(e.getEmail());
			passengerResponse.setPassengerName(e.getPassengerName());
			passengerResponse.setTrainName(e.getTrainName());
			passengerResponse.setSeatNumber(e.getSeatNumber());
			passengerResponseList.add(passengerResponse);
		}
		return passengerResponseList;

	}

	@Override
	public List<Map<String, Object>> getPassengerFields() {
		List<Passenger> allPassengers = passengerRepository.findAll();
		List<Map<String, Object>> collectFields = new ArrayList<Map<String, Object>>();
		Map<String, Object> mappingTwofields;
		for (Passenger passenger : allPassengers) {
			mappingTwofields = new HashMap<String, Object>();
			mappingTwofields.put("passengerName", passenger.getPassengerName());
			mappingTwofields.put("email", passenger.getEmail());
			collectFields.add(mappingTwofields);
		}

		return collectFields;
	}

}
