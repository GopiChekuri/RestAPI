//package com.siemens.ats;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//import java.util.List;
//
//import org.assertj.core.api.AssertionsForClassTypes;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Sort;
//
////import org.assertj.core.api.junit.jupiter.*;
//import com.siemens.ats.model.Passenger;
//import com.siemens.ats.repository.PassengerRepository;
//import org.junit.jupiter.api.condition.*;
//
//
//
//
//@SpringBootTest
//class ApplicationTests {
//	
//	@Autowired
//	 PassengerRepository pRepo;
//
//	@Test
//	@Order(1)
//	// test for save method > REST API POST
//	 public void testCreate() {
//		Passenger passenger = new Passenger(1L,"Gopi Krishna","Gopikrishna@gmail.com","Chennai Express","S6,26");
//		
//		pRepo.save(passenger);
//		assertThat(pRepo.findById(1L).get());
//	}
//	@Test
//	@Order(2)
//	// test for get method to get all passengers >REST API GET
//	public void testReadAll() {
//		List<Passenger> list = pRepo.findAll();
//		assertThat(list).size().isGreaterThan(0);
//	}
//	@SuppressWarnings("unlikely-arg-type")
//	@Test
//	@Order(3)
//	// test for to get single passenger details > REST API GET
//	
//	public void testSinglePassenger() {
//		Passenger passenger = pRepo.findById(1L).get();
//		assertThat(passenger.equals(passenger.getId()));
//	}
//	@SuppressWarnings("unlikely-arg-type")
//	@Test
//	@Order(4)
//	//test for update a passenger details > REST API PUT
//	public void TestUpade() {
//		Passenger passenger = pRepo.findById(1L).get();
//		passenger.setPassengerName("Gopi");
//		Passenger passenger2 =pRepo.save(passenger);
//		assertThat(passenger2.equals(passenger2.getPassengerName()));
//
//	}
////	@Test
////	@Order(5)
////	
////	public void TestDelete() {
////		pRepo.deleteById(22L);
////		assertThat(pRepo.existsById(22L));	
//		
////	}
//	
//		 
//	 
//	
//}
