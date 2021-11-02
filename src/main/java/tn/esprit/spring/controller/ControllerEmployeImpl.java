package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.services.IEmployeService;

@Scope(value = "session")
@Controller(value = "employeController")
@ELBeanName(value = "employeController")
@Join(path = "/", to = "/login.jsf")
public class ControllerEmployeImpl {
	
	String url="/login.xhtml?faces-redirect=true";

	@Autowired
	IEmployeService employeService;

	private String loginUser;
	private String passwordUser;
	private Boolean loggedIn;

	private Employe authenticatedUser = null;
	private String prenomUser;
	private String nomUser;
	private String emailUser;
	private boolean actifUser;
	private Role roleUser;

	private List<Employe> employes;

	private Integer employeIdToBeUpdated; // getter et setter

	public String doLogin() {

		String navigateTo = "null";
		authenticatedUser = employeService.authenticate(loginUser, passwordUser);
		if (authenticatedUser != null && authenticatedUser.getRole() == Role.ADMINISTRATEUR) {
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
			loggedIn = true;
		}

		else {

			FacesMessage facesMessage = new FacesMessage(
					"Login Failed: Please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		}
		return navigateTo;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return url;
	}

	public String addEmploye() {

		if (authenticatedUser == null || !loggedIn)
			return url;

		employeService.addOrUpdateEmploye(new Employe(nomUser, prenomUser, emailUser, passwordUser, actifUser, roleUser));
		return "null";
	}

	public String removeEmploye(int employeId) {
		String navigateTo = "null";
		if (authenticatedUser == null || !loggedIn)
			return url;

		employeService.deleteEmployeById(employeId);
		return navigateTo;
	}

	public String displayEmploye(Employe empl) {
		String navigateTo = "null";
		if (authenticatedUser == null || !loggedIn)
			return url;

		this.setPrenomUser(empl.getPrenom());
		this.setNomUser(empl.getNom());
		this.setActifUser(empl.isActif());
		this.setEmailUser(empl.getEmail());
		this.setRoleUser(empl.getRole());
		this.setPasswordUser(empl.getPassword());
		this.setEmployeIdToBeUpdated(empl.getId());

		return navigateTo;

	}

	public String updateEmploye() {
		String navigateTo = "null";

		if (authenticatedUser == null || !loggedIn)
			return url;

		employeService.addOrUpdateEmploye(new Employe(employeIdToBeUpdated, nomUser, prenomUser, emailUser, passwordUser, actifUser, roleUser));

		return navigateTo;

	}

	// getters and setters

	public IEmployeService getEmployeService() {
		return employeService;
	}

	public void setEmployeService(IEmployeService employeService) {
		this.employeService = employeService;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getPrenomUser() {
		return prenomUser;
	}

	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public boolean isActifUser() {
		return actifUser;
	}

	public void setActifUser(boolean actifUser) {
		this.actifUser = actifUser;
	}

	public Role getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(Role roleUser) {
		this.roleUser = roleUser;
	}

	public int ajouterEmploye(Employe employe) {
		employeService.addOrUpdateEmploye(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		employeService.mettreAjourEmailByEmployeId(email, employeId);

	}

	public void affecterEmployeADepartement(int employeId, int depId) {
		employeService.affecterEmployeADepartement(employeId, depId);

	}

	public void desaffecterEmployeDuDepartement(int employeId, int depId) {
		employeService.desaffecterEmployeDuDepartement(employeId, depId);
	}

	public int ajouterContrat(Contrat contrat) {
		employeService.ajouterContrat(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		employeService.affecterContratAEmploye(contratId, employeId);
	}

	public String getEmployePrenomById(int employeId) {
		return employeService.getEmployePrenomById(employeId);
	}

	public void deleteEmployeById(int employeId) {
		employeService.deleteEmployeById(employeId);

	}

	public void deleteContratById(int contratId) {
		employeService.deleteContratById(contratId);
	}

	public int getNombreEmployeJPQL() {

		return employeService.getNombreEmployeJPQL();
	}

	public List<String> getAllEmployeNamesJPQL() {

		return employeService.getAllEmployeNamesJPQL();
	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeService.getAllEmployeByEntreprise(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeService.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		employeService.deleteAllContratJPQL();

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeService.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeService.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return employeService.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getEmployes() {
		employes = employeService.getAllEmployes();
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public Integer getEmployeIdToBeUpdated() {
		return employeIdToBeUpdated;
	}

	public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
		this.employeIdToBeUpdated = employeIdToBeUpdated;
	}

	public Employe getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(Employe authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

}
