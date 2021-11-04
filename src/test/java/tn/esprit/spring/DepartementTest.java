package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.DepartementServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementTest {

	private static final org.apache.logging.log4j.Logger l= LogManager.getLogger(DepartementServiceImpl.class);
	@Autowired
	DepartementServiceImpl DepartementServiceImpl;
	
	@Autowired
	DepartementRepository DepartementRepo;
	
	@Test(timeout=5000)
	@Order(1)
	public void testAddDepartement() {
		try {
		long start = System.currentTimeMillis();
		Departement Departement = new Departement("IT");
		int Id = DepartementServiceImpl.ajouterDepartement(Departement);
		assertNotEquals(0, Id);
		DepartementServiceImpl.deleteDepartementById(Id);
	    long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution time: " + elapsedTime + " milliseconds.");
		l.info("Add Departement works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test(timeout=5000)
	@Order(2)
	public void testUpdateDepartement() {
		try {
		long start = System.currentTimeMillis();
		Departement d = new Departement("IT");
		int Id = DepartementServiceImpl.ajouterDepartement(d);
		d.setName("RH");
		DepartementServiceImpl.ajouterDepartement(d); 
		String depname = DepartementServiceImpl.getDepartementNameById(Id);
		assertEquals("RH",depname);
		DepartementServiceImpl.deleteDepartementById(Id);
		long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution time: " + elapsedTime + " milliseconds.");
		l.info("Update Departement works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test(timeout=5000)
	@Order(3)
	public void testDeleteDepartementById() {
		try {
		long start = System.currentTimeMillis();
		Departement d = new Departement("IT");
		int Id = DepartementServiceImpl.ajouterDepartement(d);
		int len = DepartementServiceImpl.getAllDepartement().size();
		DepartementServiceImpl.deleteDepartementById(Id);
		assertEquals(len-1 , DepartementServiceImpl.getAllDepartement().size());
		long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution time: " + elapsedTime + " milliseconds.");
		l.info("Delete Departement works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	
	@Test(timeout=5000)
	@Order(4)
	public void testGetDepartementNameById(){
		try {
		long start = System.currentTimeMillis();
		Departement d = new Departement("IT");
		int Id = DepartementServiceImpl.ajouterDepartement(d);
		String depname= DepartementServiceImpl.getDepartementNameById(Id);
		assertNotNull(depname);
		DepartementServiceImpl.deleteDepartementById(Id);
		long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution time: " + elapsedTime + " milliseconds.");
		l.info("Get Departement name by id works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test(timeout=5000)
	@Order(5)
	public void testGetDepartementById(){
		try {
		long start = System.currentTimeMillis();
		Departement d = new Departement("IT");
		int Id = DepartementServiceImpl.ajouterDepartement(d);
		Departement dep= DepartementServiceImpl.getDepartementById(Id);
		assertNotNull(dep);
		DepartementServiceImpl.deleteDepartementById(Id);
		long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution time: " + elapsedTime + " milliseconds.");
		l.info("Get Departement by id works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test(timeout=5000)
	@Order(6)
	public void testGetAllDepartement(){
		try {
		long start = System.currentTimeMillis();
		Departement d = new Departement("IT");
		int Id = DepartementServiceImpl.ajouterDepartement(d);
		Departement d1 = new Departement("RH");
		int Id1 = DepartementServiceImpl.ajouterDepartement(d1);
		List<Departement> dep= DepartementServiceImpl.getAllDepartement();
		assert dep.size()>= 2;
		DepartementServiceImpl.deleteDepartementById(Id);
		DepartementServiceImpl.deleteDepartementById(Id1);
		long elapsedTime = System.currentTimeMillis() - start;
		l.info("Method execution time: " + elapsedTime + " milliseconds.");
		l.info("Get ALLDepartement works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
}

