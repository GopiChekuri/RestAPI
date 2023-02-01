package com.siemens.ats;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import com.siemens.ats.model.Passenger;
import com.siemens.ats.model.PassengerResponse2;
import com.siemens.ats.repository.PassengerRepository;

import com.siemens.ats.service.impl.PassengerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MockitoPassengerServiceTest {

	@InjectMocks
	private PassengerServiceImpl passImpl;

	@Mock
	private PassengerRepository passengerRepository;

	private Passenger passenger;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		passenger = new Passenger();
		passenger.setId(1L);
		passenger.setPassengerName("Gopi Krishna");
		passenger.setEmail("Gopi@gmail.com");
		passenger.setSeatNumber("S6,10");
		passenger.setTrainName("Chennai Express");
	}

	@Test
	@Order(1)
	@DisplayName("we should save  the passenger details to database")
	void savePassenger() throws DataIntegrityViolationException {

		when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
		Passenger newPassenger = passImpl.savePassenger(passenger);
		assertThat(newPassenger).isNotNull();
		assertThat(newPassenger.getPassengerName()).isEqualTo("Gopi Krishna");
	}

	@Test
	@Order(2)
	@DisplayName("we should get all the passengers list")
	void getAllPassengers() {

		Passenger passenger2 = new Passenger(2L, "Gopi Krishna", "Gopikrishna@gmail.com", "Chennai Express", "S6,26");

		List<Passenger> list = new ArrayList<>();
		list.add(passenger);
		list.add(passenger2);

		when(passengerRepository.findAll()).thenReturn(list);
		List<Passenger> passengers = passImpl.getAllPassengers();
		assertEquals(2, passengers.size());
		assertNotNull(passengers);

	}
 
	@Test 
	@Order(3)
	@DisplayName(" we should get single passenger by ID")
	void getPassengerById() {

		when(passengerRepository.findById(anyLong())).thenReturn(Optional.of(passenger));
		Passenger passenger2 = passImpl.getPassengerById(1L);
		assertNotNull(passenger2);
		assertEquals("Gopi Krishna", passenger2.getPassengerName());
	}

	@Test
	@Order(4)
	@DisplayName("we can update existing passenger details")
	void updatePassenger() {

		when(passengerRepository.findById(anyLong())).thenReturn(Optional.of(passenger));
		when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
		Passenger Updatedpassenger = passImpl.updatePassenger(passenger);
		assertNotNull(Updatedpassenger);
		assertThat(Updatedpassenger.getPassengerName()).isEqualTo("Gopi Krishna");

	} 

	@Test
	@Order(5)
	@DisplayName("we can delete the existing database")
	void deletePassenger() {

		when(passengerRepository.findById(anyLong())).thenReturn(Optional.of(passenger));
		passImpl.deletePassenger(passenger.getId());
		verify(passengerRepository, times(1)).deleteById(passenger.getId());

	}

	@Test
	@Order(6)
	@DisplayName("we should get all the passengers list")
	void getPassengersWithoutId() {

		Passenger passenger = new Passenger(1L, "Gopi krishna", "Gopi@gmail.com", "Chennai Express", "S6,30");
		Passenger passenger2 = new Passenger(2L, "Gopi krishna", "Gopi@gmail.com", "Chennai Express", "S6,30");
		List<Passenger> list = new ArrayList<>();
		list.add(passenger);
		list.add(passenger2);  

		when(passengerRepository.findAll()).thenReturn(list);
		List<PassengerResponse2> passengers = passImpl.getPassengersWithoutid();
		assertEquals(2, passengers.size());
		assertNotNull(passengers);

	}

	@Test
	@Order(7)
	@DisplayName("we can sort  the existing database")
	public void findPassengerWithSorting() {

		String field = "passengerName";
		Passenger passenger = new Passenger(1L, "Gopi Krishna", "Gopikrishna@gmail.com", "Chennai Express", "S6,26");
		Passenger passenger2 = new Passenger(2L, "Abishek", "Gopikrishna@gmail.com", "Chennai Express", "S6,26");

		List<Passenger> list = new ArrayList<>();
		list.add(passenger);
		list.add(passenger2);

		when(passengerRepository.findAll(Sort.by(Sort.Direction.ASC, field))).thenReturn(list);
		List<Passenger> result = passImpl.findPassengerWithSorting(field);

		assertNotNull(result);
		assertEquals(2, list.size());

	}

}
