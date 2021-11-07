package tn.esprit.spring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {EmployeServiceTest.class})
//@WebMvcTest(controllers = RestControlEmploye.class)
public class EmployeServiceTest {
	
	
	@Mock
	EmployeRepository EmplRepo;
	
	@InjectMocks
	EmployeServiceImpl EmplSrvImpl;
	
	//@MockBean
	//EmployeServiceImpl EemplSrv2;
	
	//@Autowired
	//private MockMvc mockmvc;
	
	List<Employe> MesEmployes;
	
	
	
	
	// Service Layer Tests
	
	@Test
	@Order(1)
	public void test_getAllEmployes() {
		
		List<Employe> MesEmployes= new ArrayList<Employe>();
		
		MesEmployes.add(new Employe(1,"oussama","issaoui","oussama.issaoui1@esprit.tn","passw",true,Role.CHEF_DEPARTEMENT));
		
		MesEmployes.add(new Employe(2,"oussama2","issaoui2","oussama.issaoui2@esprit.tn","passw2",true,Role.TECHNICIEN));
		
		when(EmplRepo.findAll()).thenReturn(MesEmployes);
		
		assertEquals(2,EmplSrvImpl.getAllEmployes().size());
	}
	
	@Test
	@Order(2)
	public void test_addOrUpdateEmployee() {
		
		Employe employe = new Employe(1,"oussama","issaoui","oussama.issaoui1@esprit.tn","passw",true,Role.CHEF_DEPARTEMENT);
		
		when(EmplRepo.save(employe)).thenReturn(employe);
		EmplSrvImpl.addOrUpdateEmploye(employe);
		
		assertEquals(1,EmplSrvImpl.addOrUpdateEmploye(employe));
	}
	
	/*
	@Test
	@Order(3)
	@DisplayName("Should verify if the the Repo Metdhod was hit once when service is called")
	public void test_deleteEmployeById() {
		
		
		Employe employe = new Employe(1,"oussama","issaoui","oussama.issaoui1@esprit.tn","passw",true,Role.CHEF_DEPARTEMENT);
		EmplSrvImpl.deleteEmployeById(1);
		verify(EmplRepo,times(1)).delete(employe);
		
		
	}

	// Controller Layer Tests
	
	
	@Test
	@Order(4)
	public void test_ControgetAllEmployes() throws Exception{
		
		mockmvc.perform(MockMvcRequestBuilders.get("/SpringMVC/servlet/getAllEmployes")).andExpect(MockMvcResultMatchers.status().is(200));
	}
	*/
	
	
}
