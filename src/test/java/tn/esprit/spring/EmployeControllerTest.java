package tn.esprit.spring;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;



@WebMvcTest(controllers = RestControlEmploye.class)

public class EmployeControllerTest {
	
	private static final Logger L = LogManager.getLogger();

	
	@MockBean
	private IEmployeService EemplSrv2;
	
	/*
	@MockBean
	EmployeServiceImpl EmplSrvImpl;
	
	@MockBean
	EmployeRepository EmplRepo;*/
	
	@MockBean
	private IEntrepriseService iEntrepriseService;
	
	@MockBean
	private ITimesheetService itimesheetservice;
	
	@Autowired
	private MockMvc mockmvc;
	
	
	 @Autowired
	    private WebApplicationContext context;

	    @BeforeEach
	    void beforeEach() {
	        mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	    }
	
	@Test
	public void test_ControgetAllEmployes() throws Exception{
		
List<Employe> MesEmployes= new ArrayList<Employe>();

        L.log(Level.DEBUG, () ->"creation de la liste");
		
		MesEmployes.add(new Employe(1,"oussama","issaoui","oussama.issaoui1@esprit.tn","passw",true,Role.CHEF_DEPARTEMENT));
		
		MesEmployes.add(new Employe(2,"oussama2","issaoui2","oussama.issaoui2@esprit.tn","passw2",true,Role.TECHNICIEN));
		
		when(EemplSrv2.getAllEmployes()).thenReturn(MesEmployes);
		
		 mockmvc.perform(MockMvcRequestBuilders.get("/getAllEmployes")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(
				 mvcResult -> {
					 String json = mvcResult.getResponse().getContentAsString();
					 
					 if (json!=null) {
						 L.log(Level.INFO, () ->"L'API est fonctionnell avec un statut 200:OK et la liste des employés est " + json);
					 }
					 else {
						 L.log(Level.FATAL, () ->"L'API est non fonctionnel");
					 }
				 }
				 );
		
		
		//
		//status().isOk()
	}
}
