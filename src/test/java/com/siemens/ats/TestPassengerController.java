package com.siemens.ats;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.ats.controller.PassengerController;
import com.siemens.ats.model.Passenger;
import com.siemens.ats.model.PassengerResponse2;
import com.siemens.ats.service.impl.PassengerServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PassengerController.class)
public class TestPassengerController {
	
	@InjectMocks
	private PassengerController passengerController;
	@MockBean
	private PassengerServiceImpl service;
	
	@Autowired
	private MockMvc mockmvc;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	
	Passenger passenger1;
	Passenger passenger2;
	Passenger passenger3;
	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		 passenger1 = new Passenger(1L, "Gopi krishna", "gopi@gmail.com", "Chennai Express", "S6,10");
		 passenger2 = new Passenger(1L, "Siva krishna", "siva@gmail.com", "Chennai Express", "S6,10");
		 passenger3 = new Passenger(1L, "Sai krishna", "sai@gmail.com", "Chennai Express", "S6,10");
		
	} 
	@Test
	@DisplayName("Get Method testing to get all passengers list")
	public void getAllPassengers() throws Exception {
		
		 List<Passenger> listOfPassengers = new ArrayList<>();
		 
			listOfPassengers.add(passenger1);
			listOfPassengers.add(passenger2); 
			listOfPassengers.add(passenger3);
			Mockito.when(service.getAllPassengers()).thenReturn(listOfPassengers);

		String url = "/api/passengers/getAll";
		MvcResult mvcResult =   mockmvc.perform(get(url)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(status().isOk()).andReturn();
		
		String actualJson = mvcResult.getResponse().getContentAsString();
		String ExpectedJson = mapper.writeValueAsString(actualJson);
		assertThat(actualJson).isEqualToNormalizingPunctuationAndWhitespace(ExpectedJson);
	
	
	}
	 @Test
	 @DisplayName("Get method testing in controller Layer")
	 public void getPassengerById() throws Exception {
		 Mockito.when(service.getPassengerById(passenger1.getId())).thenReturn(passenger1);
		 String url = "/api/passengers/1";
		 
			MvcResult mvcResult =   mockmvc.perform(get(url)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(status().isOk()).andReturn();
			
			String actualJson = mvcResult.getResponse().getContentAsString();
			String ExpectedJson = mapper.writeValueAsString(actualJson);
			assertThat(actualJson).isEqualToNormalizingPunctuationAndWhitespace(ExpectedJson);
			
	 }
	 @Test
	 @DisplayName("Post method testing in controller layer")
	 public void createPassenger() throws Exception {
		Mockito.when(service.savePassenger(passenger1)).thenReturn(passenger1);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/passengers")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(passenger1));
		 mockmvc.perform(mockRequest).andExpect(status().isCreated());
		 assertNotNull(mockRequest);
	 }
	 @Test
	 @DisplayName("Delete Method test to delete the passenger")
	 public void deletePassengerById() throws Exception {
		 Mockito.when(service.deletePassenger(passenger1.getId())).thenReturn(passenger1);
		 mockmvc.perform(MockMvcRequestBuilders
		            .delete("/api/passengers/delete/1") 
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk());
	 }
	 @Test
	 @DisplayName("Put Method testing passenger to update")
	 public void updatePassenger() throws Exception {
		 Mockito.when(service.updatePassenger( passenger1)).thenReturn(passenger1);
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/passengers/update")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(passenger1));
		 mockmvc.perform(mockRequest).andExpect(status().isCreated());		
		assertNotNull(mockRequest);
	 }
	 @Test
	 @DisplayName("Get Method: testing passengers with sorting")
	 public void getPassengerwithSorting() throws Exception {
		 String field ="passengerName";
		 List<Passenger> listOfPassengers = new ArrayList<>();
		 
			listOfPassengers.add(passenger1);
			listOfPassengers.add(passenger2); 
			listOfPassengers.add(passenger3);
			Mockito.when(service.findPassengerWithSorting(field)).thenReturn(listOfPassengers);
			
			String url = "/api/passengers/byfield/passengerName";
			 MvcResult mvcResult= mockmvc.perform(get(url)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(3)))
			.andExpect(status().isOk()).andReturn();
			
			String actualJson = mvcResult.getResponse().getContentAsString();
			String ExpectedJson = mapper.writeValueAsString(actualJson); 
			assertThat(actualJson).isEqualToNormalizingPunctuationAndWhitespace(ExpectedJson);
			assertNotNull(ExpectedJson);
	 }
	 @Test
	 @DisplayName("GEt Method: testing passengers without Id")
	 public void getPassengerWithoutId() throws Exception {
		 
		 List<PassengerResponse2> listOfPassengers = new ArrayList<>();
		 PassengerResponse2 passengerResponse = new PassengerResponse2("Gopi krishna", "gopi@gmail.com",
				 "Chennai Express", "S6,10");
		 PassengerResponse2 passengerResponse2 = new PassengerResponse2();
		 passengerResponse2.setPassengerName("Prasad Chekuri");
		 passengerResponse2.setEmail("prasad@gmail.com");
		 passengerResponse2.setTrainName("Chennai Express");
		 passengerResponse2.setSeatNumber("S6,10");
		 
		 
			listOfPassengers.add(passengerResponse);
			listOfPassengers.add(passengerResponse2); 
			
			Mockito.when(service.getPassengersWithoutid()).thenReturn(listOfPassengers);
			
			String url = "/api/passengers/withoutId";
			MvcResult mvcResult =   mockmvc.perform(get(url)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(status().isOk()).andReturn();
			
			String actualJson = mvcResult.getResponse().getContentAsString();
			String ExpectedJson = mapper.writeValueAsString(actualJson);
			assertThat(actualJson).isEqualToNormalizingPunctuationAndWhitespace(ExpectedJson);
			
	 }
	 
		 
}
