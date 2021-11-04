package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {


	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	
	private static final Logger L = LogManager.getLogger(DepartementServiceImpl.class);
	
	@Override
	public int ajouterDepartement(Departement departement) {
		departementRepository.save(departement);
		return departement.getId();
	}

	@Override
	public String getDepartementNameById(int departementId) {
		Departement dep = departementRepository.findById(departementId).get();
		return dep.getName();
	}

	@Override
	public Departement getDepartementById(int departementId) {
		Departement dep = departementRepository.findById(departementId).get();
		return dep;
	}
	
	@Override
	public List<Departement> getAllDepartement() {
		return (List<Departement>) departementRepository.findAll();
	}

	@Override
	public void deleteDepartementById(int departementId) {
		Departement departement= departementRepository.findById(departementId).get();
		departementRepository.delete(departement);
		
	}

	@Override
	public void deleteAllDepartements() {
		departementRepository.deleteAll();
		
	}


	

	


	
	

	
	
}
