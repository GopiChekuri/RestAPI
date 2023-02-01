package com.siemens.ats.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.siemens.ats.model.Passenger;
import com.siemens.ats.model.PassengerResponse2;
import com.siemens.ats.model.Views;
import com.siemens.ats.service.PassengerService;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;

	// build to create Passenger with post method
	// http:url://localhost:8080/api/passengers
	@PostMapping
	public ResponseEntity<Passenger> savePassenger(@RequestBody @Valid Passenger passenger) throws PSQLException {

		return new ResponseEntity<Passenger>(passengerService.savePassenger(passenger), HttpStatus.CREATED);

	}

	// build to get all passengers with get method
	// url:http://localhost:8080/api/passengers
	@GetMapping("/getAll")
	@JsonView(Views.MyResponseViews.class)
	public List<Passenger> getAllPassengers() {

		return passengerService.getAllPassengers();
	}

	// build to get passenger by his id using get method
	// url:http://localhost:8080/api/passengers/1
	@GetMapping("{id}")
	public ResponseEntity<Passenger> getpassengerById(@PathVariable("id") Long passengerId) {
		return new ResponseEntity<Passenger>(passengerService.getPassengerById(passengerId), HttpStatus.OK);
	}

	// build to update an passenger by id using update method
	// http://localhost:8080/api/passengers/update
	@PutMapping("/update")
	public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger) {

		return new ResponseEntity<Passenger>(passengerService.updatePassenger(passenger), HttpStatus.CREATED);
	}

	// build to delete a passenger by id using delete method
	// http://localhost:8080/api/passengers/delete/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePassenger(@PathVariable("id") Long id) {
		passengerService.deletePassenger(id);
		return new ResponseEntity<String>("Passenger deleted successfully!.", HttpStatus.OK);
	}

	// Method To sort the details by order
	// Url: http://localhost:8080/api/passengers/byfield/email
	@GetMapping("/byfield/{field}")
	public ResponseEntity<List<Passenger>> getPassengerWithSort(@PathVariable("field") String field) {

		return new ResponseEntity<List<Passenger>>(passengerService.findPassengerWithSorting(field), HttpStatus.OK);
	}

	// Method to get list without id
	// Url :http://localhost:8080/api/passengers/withoutId
	@GetMapping("/withoutId")
	public List<PassengerResponse2> getPassengers() {

		return passengerService.getPassengersWithoutid();
	}

	@GetMapping("/withTwoFields")
	public List<Map<String, Object>> getPassengerwithFields() {
//		List<Map<String, Object>> allPassengers = passengerService.getPassengerFields();
//		return allPassengers;
		return passengerService.getPassengerFields();
	}

}
