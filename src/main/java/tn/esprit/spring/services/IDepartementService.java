package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;


public interface IDepartementService {
	
	
	public List<Departement> getAllDepartements();

	void deleteDepartementById(int departementId);

	List<Departement> getAllDepartement();

	void deleteAllDepartements();

	String addDepartement(Departement d);

	List<Departement> retrieveAllDepartement();

	int ajouterDepartement(Departement departement);

	String getDepartementNameById(int departementId);


	
	
	

	
}
