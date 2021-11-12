package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;


public interface IDepartementService {
	
	
	

	void deleteDepartementById(int departementId);

	List<Departement> getAllDepartement();

	void deleteAllDepartements();

	

	

	int ajouterDepartement(Departement departement);

	String getDepartementNameById(int departementId);

	Departement getDepartementById(int departementId);


	
	
	

	
}
